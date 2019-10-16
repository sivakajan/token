package com.exampletokener.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdToken {
    private String token;


    @Id
   private int userid;

    public IdToken() {
    }

    public IdToken(String token, int userid) {
        this.token = token;
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return userid;
    }

    public void setId(int userid) {
        this.userid = userid;
    }
}
