package com.yongxing912.util;

/**
 * 定义程序中需要的全部变量 GlobalConfig.java TODO 2018年11月8日 下午12:24:25
 *
 * @author Ricky
 */
public class ConfigValueUtil {

    // 清空数据库TRUNCATE t_codename;TRUNCATE t_cp;
    // TRUNCATE t_customer; TRUNCATE t_lotinfo;
    // TRUNCATE t_testername;TRUNCATE t_testertype;
    // TRUNCATE t_testflow;TRUNCATE t_user;
//
//	// tp方面配置 浏览器访输入地址为：\\127.0.0.1\TPFolder20181113
    //server共享文件路径：\\192.168.100.200

    // 数据库端配置
    public static String jdbcName = "com.mysql.cj.jdbc.Driver";
    public static String dbUser = "root";// 数据库用户名
    public static String dbPwd = "root456";// 数据库密码

//    //日期 版本信息
//    public static String upDate = "20190404";
//    public static final String version = "version_" + upDate;

    //本机选择下面设置 1/2
//    public static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_gt_cp?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    public static String dbUrl = "jdbc:mysql://192.168.100.200:3306/db_gt_cp?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//    public static String serverTpFolder = "//127.0.0.1/TPFolder";// 服务器端tp程序文件夹
//    public static String serverTpBakeFolder = "//127.0.0.1/TPFolder/TpBakeFolder";

    //ftp settings
//    public static String ftpHost="127.0.0.1";
//    public static String ftpUserName="cpadmin";
//    public static String ftpPassword="123456";
//    public static int ftpPort=21;

    //海门车间服务器选择下面设置 2/2
//    public static String dbUrl = "jdbc:mysql://192.168.100.200:3306/db_cp" + upDate + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
//    public static String serverTpFolder = "//192.168.100.200/TPFolder_test";// 服务器端tp程序文件夹
//    public static String serverTpBakeFolder = "//192.168.100.200/TPFolder_test/TpBakeFolder";

    //ftp settings
//    public static String ftpHost="192.168.100.200";
//    public static String ftpUserName="cpadmin";
//    public static String ftpPassword="123456";
//    public static int ftpPort=21;

//    public static String ftpPath="/";
//    public static String localPath="c:\\eva\\program";
//    String fileName="BJTH001A_YJ_EVA100_CP1_32S_V01_201801051440.zip";

//    public static final String localTpFolder = "c:\\eva\\program";//测试程序下载到测试机的本地存放路径
}