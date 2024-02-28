package com.example.huanzhanapiclient.model;

import lombok.Data;

@Data
public class HuanzhanUser {
    private String userName ;

    public HuanzhanUser() {
    }

    public HuanzhanUser(String userName) {
        this.userName = userName;
    }
}
