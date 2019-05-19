package com.yongxing912.web;

import com.yongxing912.Dao.CustomerDao;
import com.yongxing912.model.Customer;
import com.yongxing912.util.DbUtil;
import com.yongxing912.util.ResponseUtil;
import com.yongxing912.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 22:09
 */
public class CustomerSaveServlet extends HttpServlet {
    CustomerDao customerDao = new CustomerDao();
    private DbUtil dbUtil = new DbUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String customerName = request.getParameter("customerName");
        String customerCode = request.getParameter("customerCode");
        String contacts = request.getParameter("contacts");
        String phone = request.getParameter("phone");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String customerDescp = request.getParameter("customerDescp");
        String regTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String regUser = request.getParameter("userName");
        String customerId = request.getParameter("customerId");

        Customer customer = new Customer(customerName, customerCode, contacts, phone, fax, email, address, customerDescp, regTime, regUser);

        if (StringUtil.isNotEmpty(customerId)) {
            customer.setCustomerId(Integer.parseInt(customerId));
        }

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int saveNums = 0;
            JSONObject result = new JSONObject();

            if (StringUtil.isNotEmpty(customerId)) {
                saveNums = customerDao.customerModify(con, customer);
            } else {
                saveNums = customerDao.customerAdd(con, customer);
            }

            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
            ResponseUtil.write(response, result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
