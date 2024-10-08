package com.green.greengram.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greengram.common.AppProperties;
import com.green.greengram.security.MyUser;
import com.green.greengram.security.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/*
JWT 생성, Request(요청)의 Header에서 token 얻기, 확인(Validate : 토큰 변질이 없었나, 만료시간이 지났나?), Claim(데이터) 넣고 빼기
*/

@Slf4j
@Component  // 빈 등록 + 싱글톤
//@RequiredArgsConstructor
public class JwtTokenProviderV2 {

    private final ObjectMapper om;
    private final AppProperties appProperties;
    private final SecretKey secretKey;  // final 붙은 애들은 생성자에서 초기화가 되어야 한다.
                                        // @RequiredArgsConstructor 주석처리 + 하단 생성자 추가 + secretKey 생성자 안으로 삽입

                                        // JwtTokenProvider랑 JwtTokenProviderV2의 차이점 : SecretKey에 final 유무

    public JwtTokenProviderV2(ObjectMapper om, AppProperties appProperties) {
        this.om = om;
        this.appProperties = appProperties;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(appProperties.getJwt().getSecret()));  // 암호화, 복호화할 때 사용하는 키를 생성하는 부분
                                                                                                             // decode 메서드에 argument 값은 우리가 설정한 문자열
    }

//    @PostConstruct  // 생성자 호출 이후에 한번 실행하는 메서드
//    public void init() {
////        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(appProperties.getJwt().getSecret()));
//    }

    public String generateAccessToken(MyUser myUser) {
        return generateToken(myUser, appProperties.getJwt().getAccessTokenExpiry());
        // yaml 파일에서 app.jwt.access-token-expiry 내용을 가져오는 부분
    }

    public String generateRefreshToken(MyUser myUser) {
        return generateToken(myUser, appProperties.getJwt().getRefreshTokenExpiry());
        // yaml 파일에서 app.jwt.refresh-token-expiry 내용을 가져오는 부분
    }

    private String generateToken(MyUser myUser, long tokenValidMilliSecond) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))     // JWT 생성일시 (payload 에 저장)  (new Date(System.currentTimeMillis()) 현재 시간)
                .expiration(new Date(System.currentTimeMillis() + tokenValidMilliSecond))       // JWT 만료 일시    (new Date(System.currentTimeMillis() + tokenValidMilliSecond) => 현재 시간을 ms로 변경 + 만료시간)

                .claims(createClaims(myUser))      // claims는 payload에 저장하고 싶은 내용을 저장

                .signWith(secretKey, Jwts.SIG.HS512)    // 서명 (JWT 암호화 선택, 위변조 검증)
                .compact();                             // 토큰 생성

        // .메서드(호출).메서드(호출).메서드(호출) -> 체이닝 기법, 원리는 메서드 호출 시 자신의 주소값 리턴을 하기 때문
        // issuedAt, expiration, claims, signWith 리턴 타입은 JwtBuilder 이지만
        // 마지막 compact()의 리턴 타입은 String 이므로 private "String" generateToken 이다. (java 수업 day 69 참고)
    }

    private Claims createClaims(MyUser myUser) {
        try {
            String json = om.writeValueAsString(myUser); // 객체 to JSON
            return Jwts.claims().add("signedUser", json).build();   // Claims에 JSON 저장
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Claims getClaims(String token) {      // 암호화가 된 값을 token 에 넣어서 payload 를 추출한다.
        return Jwts
                .parser()
                .verifyWith(secretKey)              // 똑같은 키로 복호화
                .build()
                .parseSignedClaims(token)
                .getPayload();  // JWT 안에 들어있는 payload(Claims)를 리턴한다.
    }

    public UserDetails getUserDetailsFromToken(String token) {
        try {
            Claims claims = getClaims(token);    // JWT(인증코드(문자열))에 저장되어 있는 Claims를 얻어온다.
            String json = (String)claims.get("signedUser");     // Claims에 저장되어 있는 값을 얻어온다. (그것이 JSON(데이터(문자열))
            MyUser myUser = om.readValue(json, MyUser.class);     // om : Object Mapper
                                                                  // JSON -> 객체로 변환 (그것이 UserDetails, 정확히는 MyUserDetails)

            MyUserDetails myUserDetails = new MyUserDetails();
            myUserDetails.setMyUser(myUser);
            return myUserDetails;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }


    // SpringContextHolder에 저장할 자료를 세팅 (나중에 Service단에서 빼서 사용할 값, 로그인 처리, 인가 처리를 위해)
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);   // MyUserDetails 객체 주소값
        return userDetails == null ? null : new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // userDetails => AuthenticationFacade에서 getPrincipal                                                                                                                // userDetails.getAuthorities() => 인가(권한)

        // UsernamePasswordAuthenticationToken 객체를 SpringContextHolder에 저장하는 자체만으로도 인증완료
        // userDetails는 로그인한 사용자의 정보를 controller or service 단에서 빼서 사용하기 위함
        // userDetails.getAuthorities()는 인가(권한)부분 세팅, 현재는 권한은 하나만 가질 수 있음, 다수 권한 가능
    }

    public boolean isValidateToken(String token) {
        try {
            // (Original) 만료시간이 안 지났으면 리턴 false , 지났으면 리턴 true
//            return getClaims(token).getExpiration().before(new Date());  // new Date() : 현재 시간

            return !getClaims(token).getExpiration().before(new Date());  // new Date() : 현재 시간으로 데이터 객체가 만들어짐
            // (변환) 만료시간이 안 지났으면 리턴 true, 지났으면 리턴 false



        } catch (Exception e) {
            return false;
        }
    }

    // 요청이 오면 JWT를 열어보는 부분 -> header에서 토큰(JWT)을 꺼낸다
    public String resolveToken(HttpServletRequest req) {
        // 프론트가 백엔드에 요청을 보낼 때 (로그인을 했다면) 항상 JWT를 보낼건데 header에 서로 약속한 key에 저장해서 보낸다.
        String jwt = req.getHeader(appProperties.getJwt().getHeaderSchemaName());  // yaml 파일에서 header-schema-name: authorization
//        String auth = req.getHeader("authorization"); 이렇게 적은 것과 같다.

        if(jwt == null) {
            return null;
        }
        // 위 if를 지나쳤다면 프론트가 header에 authorization 키에 데이터를 담아서 보냈다는 뜻.
        // auth에는 "Bearer JWT" 문자열이 있을 것이다. 문자열이 'Bearer'로 시작하는 지 체크

        // authorization : Bearer JWT 문자열

        if(!(jwt.startsWith(appProperties.getJwt().getTokenType()))) {        // if(auth.startsWith("Bearer")) -> yaml 파일에서 token-type: Bearer
            return null;                                                      // 프론트와 약속을 만들어야 함.
        }

        // Bearer JWT 문자열 에서 순수한 JWT 문자열만 뽑아내기 위한 문자열 자르기
        return jwt.substring(appProperties.getJwt().getTokenType().length()).trim();   // .trim : 문자열 앞뒤 공백제거 메서드
                                    // (빈칸) + JWT
                                    // appProperties.getJwt().getTokenType() 까지 Bearer 문자열 (Bearer 은 6개 문자 index:0~5)
                                    // Jwt() 객체 주소값
                                    // jwt.substring(6).trim()
//        return jwt.substring(appProperties.getJwt().getTokenType().length() + 1);
//                  .trim() 대신 문자열 + 1로 해서 빈칸을 제외할 수도 있다. but 프론트가 앞뒤로 공백을 보낼 경우 .trim()을 써야 함




    }
}