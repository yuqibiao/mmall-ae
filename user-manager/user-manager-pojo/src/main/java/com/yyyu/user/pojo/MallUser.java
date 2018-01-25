package com.yyyu.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class MallUser {
    private Long userId;

    private String username;

    @JsonIgnore
    private String password;

    private String phone;

    private String email;

    private Short status;

    @JsonIgnore
    private String question;

    @JsonIgnore
    private String answer;

    private Date createTime;

    private Date updateIme;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateIme() {
        return updateIme;
    }

    public void setUpdateIme(Date updateIme) {
        this.updateIme = updateIme;
    }
}