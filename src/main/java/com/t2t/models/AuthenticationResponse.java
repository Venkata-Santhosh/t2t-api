/**
 * Authentication Response - response class sends token to client
 * in json format
 */
package com.t2t.models;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse() {
        super();
    }

    public AuthenticationResponse(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

