package com.hxx.action;

import com.hxx.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class RegisterAction extends ActionSupport {
    private String user;
    private String password;
    private String vcode;
    private String result;
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private String message="error";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //ajax请求参数赋值
    public void setUser(String user){
        this.user = user;
    }

    //ajax返回结果
    public String getResult(){
        return result;
    }

    public String execute(){
        //获取ActionContext对象
        ActionContext context = ActionContext.getContext();
        //获取表单数据
        HttpParameters parameters = context.getParameters();
        user = String.valueOf(parameters.get("user"));
        password = String.valueOf(parameters.get("password"));
        vcode = String.valueOf(parameters.get("verifyCode"));

        try {
            if(verify(user,vcode)){

                password = SecurityAction.md5(password,user);
                String sql ="insert into user(username,password,balance) values('"+user+"','"+password+"','1000')";
                userDaoImpl.executeUpdate(sql);
                return "success";
            }else{
                return "error";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message="连接数据库错误";
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            message="加密错误";
            return "error";
        }

    }

    public boolean verify(String user,String vcode) throws SQLException {

        String sql = "select * from uservcode where user='"+user+"'";
        ResultSet rs = userDaoImpl.executeQuery(sql);
        String realVcode;
        if(rs.next()){
            realVcode = rs.getString("vcode");
        }
        else{
            message="用户未申请验证码！";
            return false;
        }
        if(realVcode.equals(vcode)){
            return true;
        }else{
            message="验证码错误！";
            return false;
        }

    }




}
