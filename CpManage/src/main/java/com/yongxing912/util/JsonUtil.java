package com.yongxing912.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 22:41
 */
public class JsonUtil {
    public static JSONArray formatRsToJsonArray(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int num = md.getColumnCount();
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValue = new JSONObject();
            for (int i = 1; i <= num; i++) {

                Object o = rs.getObject(i);
                if (o instanceof Date) {
                    String date=DateUtil.formatDate((Date) o, "yyyy-MM-dd");
                    mapOfColValue.put(md.getColumnName(i),date);
                } else {
                    mapOfColValue.put(md.getColumnName(i), rs.getObject(i));
                }
            }
            array.add(mapOfColValue);
        }
        return array;
    }
}

