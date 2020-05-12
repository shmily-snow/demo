package com.example.demo.entity;

import com.example.demo.comm.validator.Groups;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 4607938547375648378L;
    // 用户ID
    private Long userId;
    // 用户名
    @NotBlank(message = "用户名不能为空", groups = Groups.AddGroup.class)
    private String userName;
    // 真实姓名
    private String cname;
    // 密码
    @NotBlank(message = "用户密码不能为空", groups = Groups.UpdateGroup.class)
    private String password;
    // 盐
    private String salt;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0：禁用 1：正常
    private Integer status;
    // 部门ID
    private Long deptId;
    // 创建时间
    private Date createTime;
    // 部门名称
    private String deptName;

    // 用户创建者userId
    private Long createUserId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

}
