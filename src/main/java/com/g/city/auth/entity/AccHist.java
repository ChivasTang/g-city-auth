package com.g.city.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ACC_HIST")
public class AccHist extends BaseEntity {

    @TableId
    @TableField("acc_id")
    private String accId;

    @TableField("url")
    private String url;

    @TableField("user_id")
    private String userId;
}
