package com.yongxing912.web;

import com.yongxing912.Dao.CustomerDao;
import com.yongxing912.Dao.ProductDao;
import com.yongxing912.util.DbUtil;
import com.yongxing912.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 22:09
 */
public class CustomerDeleteServlet extends HttpServlet {
    private DbUtil dbUtil=new DbUtil();
    CustomerDao customerDao=new CustomerDao();
    ProductDao productDao=new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delIds=request.getParameter("delIds");

        Connection con=null;
        try{

            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            String[] str=delIds.split(",");

            for(int i=0;i<str.length;i++){
                boolean f=productDao.getProductByCustomerId(con,str[i]);
                if(f){
                    result.put("errorIndex",i);
                    result.put("errorMsg","客户下面有产品，不能删除！");
                    ResponseUtil.write(response,result);
                    return;
                }
            }

            int delNums=customerDao.customerDelete(con,delIds);
            if(delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else{
                result.put("errorMsg","删除失败");
            }
            ResponseUtil.write(response,result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
