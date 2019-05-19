package com.yongxing912.web;

import com.yongxing912.Dao.ProductDao;
import com.yongxing912.model.PageBean;
import com.yongxing912.model.Product;
import com.yongxing912.util.DbUtil;
import com.yongxing912.util.JsonUtil;
import com.yongxing912.util.ResponseUtil;
import com.yongxing912.util.StringUtil;
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
public class ProductListServlet extends HttpServlet {
    private DbUtil dbUtil=new DbUtil();
    ProductDao productDao=new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName=request.getParameter("productName");
        String pnMask=request.getParameter("pnMask");
        String pnSize=request.getParameter("pnSize");
        String customerId=request.getParameter("customerId");
        String bjoinDay=request.getParameter("bjoinDay");
        String ejoinDay=request.getParameter("ejoinDay");

        Product product=new Product();
        if(productName!=null){
            product.setProductName(productName);
            product.setPnMask(pnMask);
            product.setPnSize(pnSize);
            if(StringUtil.isNotEmpty(customerId)){
                product.setCustomerId(Integer.parseInt(customerId));
            }
        }

        String page=request.getParameter("page");
        String rows=request.getParameter("rows");

        PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(productDao.productList(con,pageBean,product,bjoinDay,ejoinDay));
            int total=productDao.productCount(con,product,bjoinDay,ejoinDay);
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
