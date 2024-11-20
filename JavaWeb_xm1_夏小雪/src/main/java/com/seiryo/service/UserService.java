package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.MyUser;

/**
 *@ClassName: UserService
 *@Description: 用户服务层接口
 *@author 夏小雪
 *@date 2024-10-10
 */
public interface UserService {
	//用户登陆的方法
	boolean userLogin(String userName,String userPass);
	
	//获取所有的用户信息
	List<MyUser> getAllUserInfo();
	
	//用户注册的方法
	boolean userRegist(String userName,String userPass);

	//修改用户信息的方法
	boolean userUpdate(Integer userId, String userPass);

	//删除用户
	boolean userDelete(Integer userId);
	

}
