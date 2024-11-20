package com.seiryo.dao;

import java.util.List;

import com.seiryo.entity.MyUser;

/**
 *@ClassName: UserDao
 *@Description: 用户数据层接口
 *@author 夏小雪
 *@date 2024-10-10
 */
public interface UserDao {
	
	//获取数据库中的所有用户信息
	List<MyUser> selectAllUser();

	//注册用户
	boolean insertUser(String userName, String userPass);

	//修改用户信息
	boolean userUpdate(Integer userId,String userPass);

	//删除用户
	boolean userDelete(Integer userId);



}
