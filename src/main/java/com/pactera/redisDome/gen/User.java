package com.pactera.redisDome.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zwk
 * @since 2024年01月07日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("nickname")
    private String nickname;

    @TableField("password")
    private String password;

    @TableField("slat")
    private String slat;

    @TableField("head")
    private String head;

    @TableField("register_date")
    private Date registerDate;

    @TableField("last_login_date")
    private Date lastLoginDate;

    @TableField("login_count")
    private Integer loginCount;
}
