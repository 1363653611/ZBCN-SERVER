package com.zbcn.microauth2auth.security.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;

    private String username;

    private String password;

    private Integer status;

    private List<String> roles;
}
