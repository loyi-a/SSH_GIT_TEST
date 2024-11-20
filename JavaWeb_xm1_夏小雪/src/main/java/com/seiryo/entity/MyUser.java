package com.seiryo.entity;

/**
 *@ClassName: MyUser
 *@Description: 用户实体类
 *@author 夏小雪
 *@date 2024-10-10
 */
public class MyUser {
	
	//用户编号
	private Integer userId;
	
	//用户名称
	private String userName;
	
	//用户密码
	private String userPass;

	//以下是属性的 GET/SET 方法
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**
	 *@Constructor: MyUser
	 *@Description: MyUser构造函数
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	public MyUser(Integer userId, String userName, String userPass) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
	}

	/**
	 *@Constructor: MyUser
	 *@Description: MyUser构造函数
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	public MyUser() {
		super();
	}

	/**
	 *@MethodName: toString
	 *@Description: toString方法
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public String toString() {
		return "MyUser [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + "]";
	}
}
