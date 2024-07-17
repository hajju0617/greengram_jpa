package com.green.greengram.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
public class MyResponse<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

}
