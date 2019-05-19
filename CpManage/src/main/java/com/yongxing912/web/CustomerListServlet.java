package com.yongxing912.web;

import com.yongxing912.Dao.CustomerDao;
import com.yongxing912.model.Customer;
import com.yongxing912.model.PageBean;
import com.yongxing912.util.DbUtil;
import com.yongxing912.util.JsonUtil;
import com.yongxing912.util.ResponseUtil;
import net.sf.json.JSONArray;
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
public class CustomerListServlet extends HttpServlet {
    private DbUtil dbUtil=new DbUtil();
    CustomerDao customerDao=new CustomerDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        String customerName=request.getParameter("customerName");
        if(customerName==null){
            customerName="";
        }
        Customer customer=new Customer();
        customer.setCustomerName(customerName);

        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(customerDao.customerList(con,pageBean,customer));
            int total=customerDao.customerCount(con,customer);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
