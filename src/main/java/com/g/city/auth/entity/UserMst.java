package com.g.city.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("USER_MST")
public class UserMst extends BaseEntity {

    @TableId
    @TableField("user_id")
    private String userId;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
}
