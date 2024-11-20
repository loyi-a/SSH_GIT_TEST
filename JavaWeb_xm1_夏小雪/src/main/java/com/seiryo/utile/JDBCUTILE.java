package com.seiryo.utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *@ClassName: JDBCUTILE
 *@Description: JDBC 工具类
 *@author carry
 *@date 2024-09-11 10:07:49
 */
public class JDBCUTILE {
	
	//设置 MYSQL 的 JDBC 驱动
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	//MYSQL 数据库连接路径
	private static final String URL = "jdbc:mysql://localhost:3306/web_xm1?characterEncoding=UTF-8";
	
	//MYSQL 账号
	private static final String USER_NAME = "root";
	
	//MYSQL 密码
	private static final String USER_PASS = "123123";
	
	//连接数据库
	private static Connection con = null;
	
	//创建 PreparedStatement 对象
	private static PreparedStatement prepar = null;
	
	//创建 ResultSet 对象
	private static ResultSet rst = null;
	
	/**
	 *@MethodName: INITDATABASE
	 *@Description: 初始化数据库
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:35:38
	 */
	private static void INITDATABASE() throws Exception{
		
		//1.加载数据库驱动
		Class.forName(DRIVER);
		
		//2.连接数据库
		con =  DriverManager.getConnection(URL,USER_NAME,USER_PASS);
		
	}
	
	/**
	 *@MethodName: prepareStatement
	 *@Description: 组装 prepareStatement
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:39:10
	 */
	private static void prepareStatement(String sql,Object ...objects) throws Exception{
		
		//3.通过 prepareStatement 实现数据库增加
		prepar = con.prepareStatement(sql);
		
		//4.插入相关数据
		for(int i = 0; i < objects.length; i++) {
			prepar.setObject(i + 1, objects[i]);
		}
		
	}
	
	/**
	 *@MethodName: JDBCUPDATA
	 *@Description: JDBC - 增删改
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:10:27
	 *@object Object ...objects 代表可变长度的数组
	 */
	public static boolean JDBCUPDATA(String sql,Object ...objects) throws Exception{
		
		//调用初始化数据库的方法
		INITDATABASE();
		
		//组装 prepareStatement
		prepareStatement(sql,objects);
		
		//当占位符设置完之后，再执行sql	
		boolean bo = prepar.executeUpdate() > 0?true:false;
		
		//关闭使用资源
		THISCLOSEDATA(con,prepar,rst);
		
		return bo;
		
	}
	
	/**
	 *@MethodName: JDBCSELECT
	 *@Description: JDBC - 查询
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:11:24
	 */
	public static ResultSet JDBCSELECT(String sql,Object ...objects) throws Exception{
		
		//调用初始化数据库的方法
		INITDATABASE();
		
		//组装 prepareStatement
		prepareStatement(sql,objects);
		
		//执行SQL并通过 ResultSet 接收返回值 并进行遍历
		rst = prepar.executeQuery();
		
		return rst;
		
	}
	
	/**
	 *@MethodName: THISCLOSEDATA
	 *@Description: 关闭本类中使用的数据资源
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:43:02
	 */
	private static void THISCLOSEDATA(Connection con,PreparedStatement prepar,ResultSet rst) throws Exception{
		
		if(rst != null) {
			rst.close();
		}
		
		if(prepar != null) {
			prepar.close();
		}
		
		if(con != null) {
			con.close();
		}
		
	} 
	
	/**
	 *@MethodName: CLOSEDATA
	 *@Description: 关闭外部使用的数据资源
	 *@return void
	 *@author carry
	 *@date 2024-09-11 10:52:22
	 */
	public static void CLOSEDATA(Connection con,PreparedStatement prepar,ResultSet rst) throws Exception{
		
		if(rst != null) {
			rst.close();
		}
		
		if(prepar != null) {
			prepar.close();
		}
		
		if(con != null) {
			con.close();
		}
		
	}

}
