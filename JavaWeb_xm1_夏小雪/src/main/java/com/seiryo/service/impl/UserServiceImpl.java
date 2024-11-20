package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserDao;
import com.seiryo.dao.impl.UserDaoImpl;
import com.seiryo.entity.MyUser;
import com.seiryo.service.UserService;

/**
 *@ClassName: UserServiceImpl
 *@Description: 用户服务层接口实现类
 *@author 夏小雪
 *@date 2024-10-10
 */
public class UserServiceImpl implements UserService{

	//接口指向实现类
	private UserDao userDao=new UserDaoImpl();
	
	/**
	 *@MethodName: userLogin
	 *@Description: 用户登陆的方法
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public boolean userLogin(String userName, String userPass) {
		//获取数据库中的所有用户信息
		List<MyUser> userList=userDao.selectAllUser();
		
		//判断所有用户信息是否获取到
		if(userList.size()>0) {
			//判断账号密码是否正确
			for(MyUser user:userList) {
				if(user.getUserName().equals(userName)) {
					if(user.getUserPass().equals(userPass)) {
						return true;
					}
				}
				
			}
			
		}
		
		return false;
	}

	/**
	 *@MethodName: getAllUserInfo
	 *@Description: 登陆成功后获取所有用户信息
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public List<MyUser> getAllUserInfo() {
		//登陆成功后获取所有用户信息
		return userDao.selectAllUser();
	}

	/**
	 *@MethodName: userRegist
	 *@Description: 用户注册的方法
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	public boolean userRegist(String userName, String userPass) {
		//获取数据库中的所有用户信息
		List<MyUser> userList=userDao.selectAllUser();
		//设置变量判断是否可以注册
		boolean bo=true;
		//判断所有用户信息是否获取到
		if(userList.size()>0) {
			//判断账号是否重复
			for(MyUser user:userList) {
				if(user.getUserName().equals(userName)) {
					    //账号重复
						bo=false;
						break;
				}
				
			}
			
		}
		//账号不重复
		if(bo) {
			//调用注册的方法
			bo=userDao.insertUser(userName,userPass);
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
	public boolean userUpdate(Integer userId, String userPass) {
		//调用修改用户信息的方法
		boolean bo=userDao.userUpdate(userId,userPass);
		
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
		//调用修改用户信息的方法
		boolean bo=userDao.userDelete(userId);
		
		return bo;
	}

	

}
