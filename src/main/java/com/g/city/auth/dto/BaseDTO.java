package com.g.city.auth.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2774757283290029342L;

    private String cUserId;
    private Date cTime;
    private String uUserId;
    private Date uTime;
    private Short deleted;
}
