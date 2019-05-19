package com.yongxing912.Dao;


import com.yongxing912.model.PageBean;
import com.yongxing912.model.Product;
import com.yongxing912.util.DateUtil;
import com.yongxing912.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-16 10:35
 */
public class ProductDao {

    public ResultSet productList(Connection con, PageBean pageBean, Product product,String bjoinDay,String ejoinDay) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from t_product p, t_customer c where p.customerId=c.customerId");
        if(StringUtil.isNotEmpty(product.getProductName())){
            sb.append(" and p.productName like '%"+product.getProductName()+"%'");
//            System.out.println("productName="+ product.getProductName());
        }
        if(StringUtil.isNotEmpty(product.getPnMask())){
            sb.append(" and p.pnMask like '%"+product.getPnMask()+"%'");
        }
        if(StringUtil.isNotEmpty(product.getPnSize())){
            sb.append(" and p.pnSize = '"+product.getPnSize()+"'");
        }

        if(product.getCustomerId()!=-1){
            sb.append(" and p.customerId = '"+product.getCustomerId()+"'");
        }

        if(StringUtil.isNotEmpty(bjoinDay)){
            sb.append(" and TO_DAYS(p.joinday)>=TO_DAYS('"+bjoinDay+"')");
        }
        if(StringUtil.isNotEmpty(ejoinDay)){
            sb.append(" and TO_DAYS(p.joinday)<=TO_DAYS('"+ejoinDay+"')");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int productCount(Connection con, Product product,String bjoinDay,String ejoinDay) throws SQLException {

        StringBuffer sb= new StringBuffer("select count(*) as total from t_product p, t_customer c where p.customerId=c.customerId");
        if(StringUtil.isNotEmpty(product.getProductName())){
            sb.append(" and p.productName like '%"+product.getProductName()+"%'");
        }
        if(StringUtil.isNotEmpty(product.getPnMask())){
            sb.append(" and p.pnMask like '%"+product.getPnMask()+"%'");
        }
        if(StringUtil.isNotEmpty(product.getPnSize())){
            sb.append(" and p.pnSize = '"+product.getPnSize()+"'");
        }

        if(product.getCustomerId()!=-1){
            sb.append(" and p.customerId = '"+product.getCustomerId()+"'");
        }

        if(StringUtil.isNotEmpty(bjoinDay)){
            sb.append(" and TO_DAYS(p.joinday)>=TO_DAYS('"+bjoinDay+"')");
        }
        if(StringUtil.isNotEmpty(ejoinDay)){
            sb.append(" and TO_DAYS(p.joinday)<=TO_DAYS('"+ejoinDay+"')");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    public int productDelete(Connection con, String delIds) throws SQLException {
        String sql = "delete from t_product where productId in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public int productAdd(Connection con, Product product) throws SQLException {
        String sql="insert into t_product values(null,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,product.getProductName());
        pstmt.setInt(2,product.getCustomerId());
        pstmt.setString(3,product.getPnMask());
        pstmt.setString(4,product.getPnSpec());
        pstmt.setString(5,product.getPnSize());
        pstmt.setString(6,product.getGrossDie());
        pstmt.setString(7, DateUtil.formatDate(product.getJoinDay(),"yyyy-MM-dd"));
        pstmt.setString(8,product.getPnDescp());
        pstmt.setString(9,product.getRegTime());
        pstmt.setString(10,product.getRegUser());

        return pstmt.executeUpdate();
    }

    public int productModify(Connection con, Product product) throws SQLException {
        String sql="update t_product set productName=?,customerId=?,pnMask=?,pnSpec=?,pnSize=?,grossDie=?,joinDay=?,pnDescp=?,regTime=?,regUser=? where productId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,product.getProductName());
        pstmt.setInt(2,product.getCustomerId());
        pstmt.setString(3,product.getPnMask());
        pstmt.setString(4,product.getPnSpec());
        pstmt.setString(5,product.getPnSize());
        pstmt.setString(6,product.getGrossDie());
        pstmt.setString(7, DateUtil.formatDate(product.getJoinDay(),"yyyy-MM-dd"));
        pstmt.setString(8,product.getPnDescp());
        pstmt.setString(9,product.getRegTime());
        pstmt.setString(10,product.getRegUser());
        pstmt.setInt(11,product.getProductId());
        return pstmt.executeUpdate();
    }

    public boolean getProductByCustomerId(Connection con,String customerId) throws SQLException {
        String sql="select * from t_product where customerId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,customerId);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }
}
