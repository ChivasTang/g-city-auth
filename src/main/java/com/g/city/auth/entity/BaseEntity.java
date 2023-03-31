package com.g.city.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @TableField("c_user_id")
    private String cUserId;
    @TableField("c_time")
    private Date cTime;
    @TableField("u_user_id")
    private String uUserId;
    @TableField("u_time")
    private Date uTime;
    @TableField("deleted")
    private Short deleted;
}
