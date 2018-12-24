package com.hxx.dao;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    Connection con = null;
    Statement stat = null;
    ResultSet rs = null;

    public UserDaoImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=GBK", "root", "pk188488");
            System.out.println("Success Connecting to Database");
            stat = con.createStatement();
        } catch (Exception e) {
            System.out.println("fail Connecting to Database");
            // TODO: handle exception
            con = null;
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        try {
            stat.executeUpdate(sql);
            return 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return -1;
    }

    @Override
    public boolean verifyPwd(String user, String pwd) {
        String sql = "select * from user where username='"+user+"' and password='"+pwd+"'";
        //System.out.println(sql);
        rs = executeQuery(sql);
        try {
            if(rs.next()){
                return true;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String queryBalance(String user) throws SQLException {
        String sql = "select * from user where username ='"+user+"'";
        rs=executeQuery(sql);
        if(rs.next()){
            return rs.getString("balance");
        }
        else{
            return "未查询到余额";
        }
    }

    @Override
    public boolean verifyVCode(String user, String vCode) {
        return false;
    }
}
