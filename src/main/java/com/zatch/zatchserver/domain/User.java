package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String profileImgUrl;
    private String town;

    /*---Constructor---*/
    public User(String name, String nickname, String email, String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    /*---Setter---*/
    public void changeProfileImg(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
    public void setTown(String town) {
        this.town = town;
    }
}
