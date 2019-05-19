package com.yongxing912.web;


import com.yongxing912.Dao.ProductDao;
import com.yongxing912.model.Product;
import com.yongxing912.util.DateUtil;
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
import java.text.ParseException;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 22:09
 */
public class ProductSaveServlet extends HttpServlet {
    com.yongxing912.Dao.ProductDao ProductDao = new ProductDao();
    private DbUtil dbUtil = new DbUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String productName = request.getParameter("productName");
        String pnMask = request.getParameter("pnMask");
        String pnSpec = request.getParameter("pnSpec");
        String pnSize = request.getParameter("pnSize");
        String grossDie = request.getParameter("grossDie");
        String customerId = request.getParameter("customerId");
        String joinDay = request.getParameter("joinDay");
        String pnDescp = request.getParameter("pnDescp");
        String productId = request.getParameter("productId");
        String regTime="2019-05-01";
        String regUser="bret.miao";


        Product Product = null;
        try {
            Product = new Product(productName, pnMask, pnSpec, pnSize, grossDie, Integer.parseInt(customerId),DateUtil.formatString(joinDay,"yyyy-MM-dd"),pnDescp, regTime, regUser);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (StringUtil.isNotEmpty(productId)) {
            Product.setProductId(Integer.parseInt(productId));
        }

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int saveNums = 0;
            JSONObject result = new JSONObject();

            if (StringUtil.isNotEmpty(productId)) {
                saveNums = ProductDao.productModify(con, Product);
            } else {
                saveNums = ProductDao.productAdd(con, Product);
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
