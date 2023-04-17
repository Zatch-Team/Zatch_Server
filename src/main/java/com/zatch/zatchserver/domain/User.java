package com.zatch.zatchserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String profileImgUrl;
    private String town;
    private String imageUrl;

    /*---Constructor---*/
    public User(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
    }

    /*---Setter---*/
    public void changeProfileImg(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
    public void setTown(String town) {
        this.town = town;
    }
//    public void setImgaeUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
}
