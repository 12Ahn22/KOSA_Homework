package com.msa2024.step2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.step2.dao.UserDAO;
import com.msa2024.step2.vo.UserVO;

/*
 * MVC 
 * Model : B/L 로직을 구현하는 부분(service + dao)  
 * View  : 출력(jsp) 
 * Controller : model와 view에 대한 제어를 담당 
 */
public class UserService  {
	private static final long serialVersionUID = 1L;
      
	UserDAO usersDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<UserVO> list(UserVO user) throws ServletException, IOException {
		return usersDAO.list(user);
	}
	
	public UserVO view(UserVO user) throws ServletException, IOException {
		return usersDAO.read(user);
	}
	
	public int delete(UserVO user) throws ServletException, IOException {
		return usersDAO.delete(user);
	}
	
	public UserVO updateForm(UserVO user) throws ServletException, IOException {
		return usersDAO.read(user);
	}
	
	public int update(UserVO user) throws ServletException, IOException {
		return usersDAO.update(user);
	}
	
	public int insert(UserVO user) throws ServletException, IOException {
		return usersDAO.insert(user);
	}
	
}











