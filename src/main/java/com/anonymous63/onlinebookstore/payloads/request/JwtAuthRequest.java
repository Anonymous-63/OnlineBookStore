package com.anonymous63.onlinebookstore.payloads.request;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;
}
