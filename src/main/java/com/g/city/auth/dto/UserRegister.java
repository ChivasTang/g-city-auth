package com.g.city.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegister extends BaseDTO {
    @Serial
    private static final long serialVersionUID = 3975751171870317419L;

    String username;
    String password;
    String confirm;
}
