package com.zatch.zatchserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Long user_id;
    private String addr_name;
    private String addr_x;
    private String addr_y;

    public Address(){};

    public Address(Long user_id, String addr_name, String addr_x, String addr_y) {
        this.user_id = user_id;
        this.addr_name = addr_name;
        this.addr_x = addr_x;
        this.addr_y = addr_y;
    }
}
