package com.g.city.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLogin extends BaseDTO {
    @Serial
    private static final long serialVersionUID = 7951159580098257900L;
    private String userId;
    private String username;
    private String password;
    private String token;
}