package com.g.city.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

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

    Set<GrantedAuthority> authorities;
    String[] roles;

    public UserMst() {
        setRoles(new String[]{"User"});
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }
}
