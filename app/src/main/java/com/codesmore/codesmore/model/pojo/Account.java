package com.codesmore.codesmore.model.pojo;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class Account {
    private Long id;
    private String username;
    private String password;
    private String parseId;

    public Long getId(){
        return id;
    }

    public void setId(Long value){
        id = value;
    }

    public String getUsername(){
        return username;
    }

    public void setUser(String value){
        username = value;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String value){
        password = value;
    }

    public String getParseId() {
        return parseId;
    }

    public void setParseId(String parseId) {
        this.parseId = parseId;
    }
}
