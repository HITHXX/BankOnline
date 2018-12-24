package com.hxx.dao;

public interface UserDao {

    boolean verifyPwd(String user,String pwd);

    boolean verifyVCode(String user,String vCode);

}
