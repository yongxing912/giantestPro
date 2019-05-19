package com.yongxing912.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-16 11:24
 */
public class DateUtil {

    public static String formatDate(Date date, String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result=sdf.format(date);
        }
        return result;
    }


    public static Date formatString(String str,String format) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}