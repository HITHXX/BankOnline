package com.hxx.action;

import com.hxx.dao.UserDaoImpl;
import com.hxx.domain.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LoginAction extends ActionSupport {
    private String user = "";
    private String password = "";
    private String message = "";
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private List<Log> logList = new LinkedList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public String execute() {
        //获取ActionContext对象
        ActionContext context = ActionContext.getContext();
        //获取表单数据
        HttpParameters parameters = context.getParameters();

        user = String.valueOf(parameters.get("user"));
        password = String.valueOf(parameters.get("password"));

        try {
            if (userDaoImpl.verifyPwd(user, SecurityAction.md5(password,user))) {
                Map session = ActionContext.getContext().getSession();
                session.put("user", user);
                Map<String,String> map = new HashMap<>();
                map = RSAUtils.createRSAKeys();
                session.put("publicKey",map.get("public"));
                session.put("privateKey",map.get("private"));


                try {
                    session.put("balance", userDaoImpl.queryBalance(user));
                    String sql = "select * from transfer where src='"+user+"' or dest='"+user+"' order by date desc";
                    System.out.println(sql);
                    ResultSet rs = userDaoImpl.executeQuery(sql);
                    while(rs.next()){
                        logList.add(new Log(rs.getString("src"),
                                            rs.getString("dest"),
                                            rs.getString("amount"),
                                            rs.getString("date")));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    return "success";
                }

            } else {
                message = "密码错误";
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message="加密发生错误";
            return "error";
        }

    }
}
