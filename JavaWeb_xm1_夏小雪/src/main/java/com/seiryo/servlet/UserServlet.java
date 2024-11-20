package com.seiryo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seiryo.entity.MyUser;
import com.seiryo.service.UserService;
import com.seiryo.service.impl.UserServiceImpl;

/**
 *@ClassName: UserServlet
 *@Description: 用户控制层
 *@author 夏小雪
 *@date 2024-10-10
 */
public class UserServlet extends HttpServlet{
	
	//接口指向实现类
	private UserService userService=new UserServiceImpl();
	
	/**
	 *@MethodName: service
	 *@Description:用户控制层服务方法
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解决乱码的方法
		//优先对请求进行编码规划（解决乱码:让界面上的编码格式与后端的编码格式一致 就是解决乱码）
		req.setCharacterEncoding("UTF-8");
		
		//其次再对相应进行编码规划
		resp.setContentType("text/html;charset=UTF-8");
		
		//登陆注册都要跳转到UserServlet，设置一个属性判断是登录/注册
		String pd=req.getParameter("pd");
		//判断登录/注册
		if("userLogin".equals(pd)){
			//调用登陆的方法
		    userLogin(req, resp);
			
		}else if("userRegist".equals(pd)){
			//调用注册的方法
			userRegist(req,resp);
			
		}else if("userUpdate".equals(pd)) {
			//调用修改信息的方法
			userUpdate(req,resp);
			
		}else if("userDelete".equals(pd)) {
			//调用删除信息的方法
			userDelete(req,resp);
			
		}else if("userViewTzUpdate".equals(pd)) {
			//调用一个跳转界面修改的方法
			tzUserUpdate(req,resp);
			
		}
		
	}
	
	/**
	 *@MethodName: userLogin
	 *@Description: 登陆的方法
	 *@return void
	 *@author 夏小雪
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-10-10
	 */
	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户输入的账号、密码
		String userName=req.getParameter("userName");
		String userPass=req.getParameter("userPass");
		
		//将获取到的信息提交到service进行验证
		boolean bo=userService.userLogin(userName,userPass);
		if(bo) {
			//跳转到登陆成功后的界面
			//登陆成功后获取数据库中的所有用户信息
			List<MyUser> userList = userService.getAllUserInfo();
			//将userList以key,value存到req里，让success.sjp使用
            req.setAttribute("userList", userList);
			
			//跳转到登录成功的界面
			req.getRequestDispatcher("success.jsp").forward(req, resp);
			
			
		}else {
			//跳转到登陆失败的界面，给提示信息
			req.setAttribute("massages","账号或密码错误，请重新登录");
			//请求转发到登陆界面
			req.getRequestDispatcher("login.jsp").forward(req,resp);
			
		}
	}
	
	/**
	 *@MethodName: userRegist
	 *@Description: 注册的方法
	 *@return void
	 *@author 夏小雪
	 * @throws IOException 
	 * @throws ServletException 
	 *@date 2024-10-10
	 */
	private void userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户输入的账号、密码
		String userName=req.getParameter("userName");
		String userPass=req.getParameter("userPass");
		
		//将获取到的信息提交到service进行验证
		boolean bo=userService.userRegist(userName,userPass);
		
		if(bo) {
			
            //账号不重复，跳转到登陆界面
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}else {
			//账号重复，跳转到注册失败的界面，给提示信息
			req.setAttribute("massages","账号重复，请重新注册");
			//请求转发到注册界面
			req.getRequestDispatcher("regist.jsp").forward(req,resp);
			
		}
		
	}
	
	/**
	 *@MethodName: tzUserUpdate
	 *@Description: 跳转修改界面的方法
	 *@return void
	 *@author 夏小雪
	 *@date 2024-10-11
	 */
	private void tzUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    //获取用户登录时输入的数据
	    String userId = req.getParameter("userId");
	    String userName = req.getParameter("userName");
	    String userPass = req.getParameter("userPass");

	    //校验用户ID是否为空 userId.isEmpty 这个方法代表如果长度是0 则返回true 如果不是0 则返回false
	    if (userId == null || userId.isEmpty()) {
	        throw new ServletException("用户信息不存在，无法修改");
	    }

	    //创建用户对象储存数据
	    MyUser user = new MyUser(Integer.parseInt(userId), userName, userPass);

	    //将用户对象放到req里面
	    req.setAttribute("user", user);

	    //跳转到修改界面
	    req.getRequestDispatcher("update.jsp").forward(req, resp);
	}

	
	
	/**
	 *@MethodName: userUpdate
	 *@Description: 修改信息的方法
	 *@return void
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	private void userUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户登录时输入的数据
		Integer userId=Integer.parseInt(req.getParameter("userId"));
		String userPass=req.getParameter("userPass");
			
		//判断用户输入的是否为空
		if(userPass.isEmpty()) {
			//没有输入时给提示信息
			req.setAttribute("massages","密码不能为空，请重新输入");
			//修改失败，跳转到修改界面
			req.getRequestDispatcher("update.jsp").forward(req, resp);
			
		}else {
			//设置变量判断是否修改成功
			boolean bo=userService.userUpdate(userId,userPass);
			
			if(bo) {
				//给提示信息
				req.setAttribute("massages","修改成功");
				
				//修改成功后获取数据库中的所有用户信息，刷新一下信息
				List<MyUser> userList = userService.getAllUserInfo();
				
				//将userList以key,value存到req里，让success.jsp使用
	            req.setAttribute("userList", userList);
	            
				//修改成功，跳转到登录的界面
				req.getRequestDispatcher("success.jsp").forward(req, resp);
				
			}else {
				//给提示信息
				req.setAttribute("massages","修改失败");
				//修改失败，跳转到修改界面
				req.getRequestDispatcher("update.jsp").forward(req, resp);
				
			}
		}
    	
    
	}
	
	/**
	 *@MethodName: userDelete
	 *@Description: 删除用户
	 *@return void
	 *@author 夏小雪
	 *@date 2024-10-10
	 */
	private void userDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户输入的id,HTTP请求中获取的都是String类型，要进行类型转换
		Integer userId=Integer.parseInt(req.getParameter("userId"));
	
    	//调用删除用户的方法
    	boolean bo=userService.userDelete(userId);
    	if (bo) {
    	    //删除成功，给提示信息
    	    req.setAttribute("massages", "用户删除成功");
    	    //删除成功后获取数据库中的所有用户信息，刷新一下信息
			List<MyUser> userList = userService.getAllUserInfo();
			
			//将userList以key,value存到req里，让success.jsp使用
            req.setAttribute("userList", userList);
    	    req.getRequestDispatcher("success.jsp").forward(req, resp);
    	    
    	} else {
    	    //删除失败，给提示信息
    	    req.setAttribute("massages", "用户删除失败，请重试");
    	    req.getRequestDispatcher("delete.jsp").forward(req, resp);
    	}
	}
	

}
