package com.zatch.zatchserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String addr_name;
    private String addr_x;
    private String addr_y;

    public Address(){};

    public Address(String addr_name, String addr_x, String addr_y) {
        this.addr_name = addr_name;
        this.addr_x = addr_x;
        this.addr_y = addr_y;
    }
}
