package com.green.greengram.feedfavorite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greengram.CharEncodingConfiguration;
import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import com.green.greengram.userfollow.UserFollowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(CharEncodingConfiguration.class)
@WebMvcTest(FeedFavoriteControllerImpl.class)
class FeedFavoriteControllerTest {

    @Autowired private ObjectMapper om;     // ObjectMapper 는 자바 객체와 JSON 문자열 간의 변환을 처리하는
                                            // 라이브러리인 Jackson 에서 제공하는 클래스
    @Autowired private MockMvc mvc;
    @MockBean private FeedFavoriteService service;

    @Test
    void toggleFavorite() throws Exception {
        // given - when - then 구조

        // given
        FeedFavoriteToggleReq p = new FeedFavoriteToggleReq(1, 2);

        int resultData = 1;
        given(service.toggleFavorite(p)).willReturn(resultData);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // MultiValueMap, LinkedMultiValueMap 상속 관계 (MultiValueMap : 부모)
        params.add("feed_id", String.valueOf(p.getFeedId()));
        params.add("user_id", String.valueOf(p.getUserId()));

        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", HttpStatus.OK);
        result.put("resultMsg", "좋아요");
        result.put("resultData", resultData);

        String expectedResJson = om.writeValueAsString(result);

        //when
        mvc.perform(
                get("/api/feed/favorite").params(params)
        )
        //then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResJson))
                .andDo(print());

        verify(service).toggleFavorite(p);
    }

    @Test
    void toggleFavorite2() throws Exception {
        // given - when - then 구조

        // given
        FeedFavoriteToggleReq p = new FeedFavoriteToggleReq(1, 2);

        int resultData = 2;
        given(service.toggleFavorite(p)).willReturn(resultData);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // MultiValueMap, LinkedMultiValueMap 상속 관계 (MultiValueMap : 부모)
        params.add("feed_id", String.valueOf(p.getFeedId()));
        params.add("user_id", String.valueOf(p.getUserId()));

        Map<String, Object> result = new HashMap<>();
        result.put("statusCode", HttpStatus.OK);
        result.put("resultMsg", "좋아요");
        result.put("resultData", resultData);

        String expectedResJson = om.writeValueAsString(result);

        //when
        mvc.perform(
                        get("/api/feed/favorite").params(params)
                )
                //then
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResJson))
                .andDo(print());

        verify(service).toggleFavorite(p);
    }
}