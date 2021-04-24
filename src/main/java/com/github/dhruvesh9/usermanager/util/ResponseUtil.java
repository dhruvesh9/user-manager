package com.github.dhruvesh9.usermanager.util;

import com.github.dhruvesh9.usermanager.model.ResponseEntity;

public class ResponseUtil {
    public static ResponseEntity createResponse(int code, String message){
        ResponseEntity response = new ResponseEntity(code,message);
        return response;
    }
}
