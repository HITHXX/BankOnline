package com.hxx.action;

import com.hxx.dao.UserDao;
import com.hxx.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TransferAction extends ActionSupport {

    private String src;
    private String dest;
    private String amount;
    private String date;
    private String message="";
    private int result;
    private UserDaoImpl userDaoImpl= new UserDaoImpl();

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute() {

        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        src = (String) session.get("user");

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        date = dateString;

        System.out.println(dest);
        dest = RSAUtils.decode(dest,(String) session.get("privateKey"));
        amount = RSAUtils.decode(amount,(String) session.get("privateKey"));
        System.out.println("dest:"+dest+" amount: "+amount);


        try {
            String sql = "select balance from user where username='"+src+"'";
            ResultSet rs = userDaoImpl.executeQuery(sql);
            int srcb,destb,amountint;
            amountint = Integer.valueOf(amount);
            if(rs.next()){
                String srcbalance = rs.getString("balance");
                srcb=Integer.valueOf(srcbalance);
                if(srcb<amountint){
                    message="余额不足";
                    result=0;
                    return "success";
                }else{
                    sql="select balance from user where username='"+dest+"'";
                    rs=userDaoImpl.executeQuery(sql);
                    if(rs.next()){
                        String destbalance = rs.getString("balance");
                        destb = Integer.valueOf(destbalance);
                    }
                    else{
                        message="账户不存在";
                        result=0;
                        return "success";
                    }

                    sql="update user set balance='"+(srcb-amountint)
                            +"' where username='"+src+"'";
                    userDaoImpl.executeUpdate(sql);

                    sql="update user set balance='"+(destb+amountint)
                            +"' where username='"+dest+"'";
                    userDaoImpl.executeUpdate(sql);

                    sql = "insert into transfer values('"+src+"','"+dest+"','"+date+"','"+amount+"')";
                    System.out.println(sql);
                    userDaoImpl.executeUpdate(sql);
                }
            }
            result=1;
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            message="转账失败";
            result=0;
            return "success";
        }

    }


}
