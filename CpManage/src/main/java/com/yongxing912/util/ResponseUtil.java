package com.yongxing912.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yongxing912
 * @site www.giantest.cn
 * @company GianTest
 * @create 2019-05-14 23:21
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
