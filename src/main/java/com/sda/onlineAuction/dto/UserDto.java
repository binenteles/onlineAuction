package com.sda.onlineAuction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String email;
    private String firstName;
    private String lastname;
    private String password;
    private String userRole;
}
