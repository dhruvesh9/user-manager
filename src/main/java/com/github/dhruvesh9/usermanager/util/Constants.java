package com.github.dhruvesh9.usermanager.util;

public class Constants {
    public static class ResponseStatusCodes{
        public static final int NOT_OK = 1;
        public static final int OK = 0;
    }

    public static class ResponseMessages{
        public static final String CREATE_USER_BAD_REQUEST = "400 - Bad request, please update the request and try again!";
        public static final String CREATE_USER_OK = "200 - OK, User created successfully with token {token}";
        
        public static final String GET_USER_INVALID_TOKEN = "400 - Bad request, invalid token, please check the token and try again! Incase you have forgotten your token, please request for new token.";
        public static final String GET_USER_VALID_TOKEN = "200 - OK, Great!";
    }

    public static class MessagePlaceholders{
        public static final String TOKEN = "{token}";
    }
}
