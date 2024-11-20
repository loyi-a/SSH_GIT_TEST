package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.entity.MyUser;
import com.seiryo.utile.JDBCUTILE;

/**
 *@ClassName: UserDaoImpl
 *@Description: 用户数据层接口实现类
 *@author 夏小雪
 *@date 2024-10-10
 */
public class UserDaoImpl implements UserDao{

	/**
	 *@MethodName: selectAllUser
	 *@Description: 获取数据库中的所有用户信息
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public List<MyUser> selectAllUser() {
		//创建集合用来保存用户信息
		List<MyUser> userList=new ArrayList<MyUser>();
		//创建ResultSet对象
		ResultSet rst=null;
		//创建SQL语句
		String sql="SELECT * FROM MyUser";
		
		try {
			rst=JDBCUTILE.JDBCSELECT(sql);
			
			while(rst.next()) {
				
				MyUser user=new MyUser();
				user.setUserId(rst.getInt("UserId"));
				user.setUserName(rst.getString("UserName"));
				user.setUserPass(rst.getString("UserPass"));
				userList.add(user);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				//关闭资源
				JDBCUTILE.CLOSEDATA(null, null, rst);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		return userList;
	}

	/**
	 *@MethodName: insertUser
	 *@Description: 注册用户
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public boolean insertUser(String userName, String userPass) {
		boolean bo = false;
		//创建SQL语句
		String sql = "INSERT INTO MyUser(userName,userPass) VALUES (?, ?)";

		try {
			  bo=JDBCUTILE.JDBCUPDATA(sql,userName,userPass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bo;
	}

	/**
	 *@MethodName: userUpdate
	 *@Description: 修改用户信息
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public boolean userUpdate(Integer userId,String userPass) {
		boolean bo = false;
		//创建SQL语句
		String sql = "UPDATE MyUser SET userPass=? WHERE userId=?";

		try {
			  bo=JDBCUTILE.JDBCUPDATA(sql,userPass,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bo;

	}

	/**
	 *@MethodName: userDelete
	 *@Description: 删除用户
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public boolean userDelete(Integer userId) {
		boolean bo = false;
		//创建SQL语句
		String sql = "DELETE FROM MyUser WHERE userId=?";

		try {
			  bo=JDBCUTILE.JDBCUPDATA(sql,userId);
		} catch (Exception e) {
			e.printStackTrace();
			bo=false;
		}

		return bo;
	}

	

}
