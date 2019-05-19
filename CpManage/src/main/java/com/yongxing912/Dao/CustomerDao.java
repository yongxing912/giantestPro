package com.yongxing912.Dao;

import com.yongxing912.model.Customer;
import com.yongxing912.model.PageBean;
import com.yongxing912.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 22:37
 */
public class CustomerDao {

    public ResultSet customerList(Connection con, PageBean pageBean, Customer customer) throws SQLException {
        StringBuffer sb = new StringBuffer("select * from t_customer");
        if (customer!=null && StringUtil.isNotEmpty(customer.getCustomerName())) {
            sb.append(" and customerName like '%" + customer.getCustomerName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int customerCount(Connection con,Customer customer) throws SQLException {

        StringBuffer sb= new StringBuffer("select count(*) as total from t_customer");
        if(StringUtil.isNotEmpty(customer.getCustomerName())){
            sb.append(" and customerName like '%" + customer.getCustomerName() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    /**
     * delete from tableName whre field in(1,3,5)
     *
     * @param con
     * @param delIds
     * @return
     */
    public int customerDelete(Connection con, String delIds) throws SQLException {
        String sql = "delete from t_customer where customerId in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /**
     * 添加客户
     * @param con
     * @param customer
     * @return
     * @throws Exception
     */
    public int customerAdd(Connection con, Customer customer) throws Exception {
        String sql = "insert into t_customer values(null,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, customer.getCustomerName());
        pstmt.setString(2, customer.getCustomerCode());
        pstmt.setString(3, customer.getContacts());
        pstmt.setString(4, customer.getPhone());
        pstmt.setString(5, customer.getFax());
        pstmt.setString(6, customer.getEmail());
        pstmt.setString(7, customer.getAddress());
        pstmt.setString(8, customer.getCustomerDescp());
        pstmt.setString(9, customer.getRegTime());
        pstmt.setString(10, customer.getRegUser());
        return pstmt.executeUpdate();
    }

    public int customerModify(Connection con,Customer customer) throws SQLException {
        String sql="update t_customer set customerName=?,customerCode=?,contacts=?,phone=?,fax=?,email=?,address=?,customerDescp=?,regTime=?,regUser=? where customerId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, customer.getCustomerName());
        pstmt.setString(2, customer.getCustomerCode());
        pstmt.setString(3, customer.getContacts());
        pstmt.setString(4, customer.getPhone());
        pstmt.setString(5, customer.getFax());
        pstmt.setString(6, customer.getEmail());
        pstmt.setString(7, customer.getAddress());
        pstmt.setString(8, customer.getCustomerDescp());
        pstmt.setString(9, customer.getRegTime());
        pstmt.setString(10, customer.getRegUser());
        pstmt.setInt(11, customer.getCustomerId());
        return pstmt.executeUpdate();
    }
}
