package com.msa2024.step2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.UsersServlet;
import com.msa2024.step2.dao.UserDAO;
import com.msa2024.step2.vo.UserVO;

public class UserController {
	private static final long serialVersionUID = 1L;

	// xml 또는 어노터이션 처리하면 스프링
	// 어노터이션 처리하면 스프링 부트
	UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String list(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("목록");

		// 1. 처리
		List<UserVO> list = userService.list(user);

		// 2. jsp출력할 값 설정
		request.setAttribute("list", list);

		return "list";
	}

	public String view(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("상세보기");
		// String userid = request.getParameter("userid");
		// 1. 처리

		// 2. jsp출력할 값 설정
		request.setAttribute("user", userService.view(user));
		return "view";
	}

	public String delete(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("삭제");
		Map<String, Object> map = new HashMap<>();

		if (Objects.isNull(user.getUserid()) || user.getUserid().length() == 0) {
			// 아이디가 없음
			map.put("status", -1);
			map.put("statusMessage", "유효한 요청이 아닙니다.");
		} else {
			int updated = userService.delete(user);

			if (updated == 1) {
				map.put("status", 0);
			} else {
				map.put("status", -99);
				map.put("statusMessage", "삭제 실패");
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);
	}

	public String updateForm(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("수정화면");
		// 1. 처리
		// usersDAO.read(user);

		// 2. jsp출력할 값 설정
		request.setAttribute("user", userService.updateForm(user));

		return "updateForm";
	}

	public String update(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("수정");
		Map<String, Object> map = new HashMap<>();

		if (Objects.isNull(user.getUserid()) || user.getUserid().length() == 0) {
			// 아이디가 없음
			map.put("status", -1);
			map.put("statusMessage", "유효한 요청이 아닙니다.");
		} else {
			int updated = userService.update(user);

			if (updated == 1) {
				map.put("status", 0);
				map.put("userid", user.getUserid()); // view 이동을 위한 id값 추가
			} else {
				map.put("status", -99);
				map.put("statusMessage", "수정 실패");
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);
	}

	public String insertForm(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("등록화면");
		// 1. 처리

		// 2. jsp출력할 값 설정
		return "insertForm";
	}

	public String insert(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("등록");
		Map<String, Object> map = new HashMap<>();

		// 검증 체크
		if (Objects.isNull(user.getUserid()) || user.getUserid().length() == 0) {
			// 아이디가 없음
			map.put("status", -1);
			map.put("statusMessage", "사용자 아이디는 Null이거나 길이가 0인 문자열은 안됩니다.");
		} else {
			// 1. 처리
			int updated = userService.insert(user);
			if (updated == 1) {
				map.put("status", 0);
			} else {
				map.put("status", -99);
				map.put("statusMessage", "등록 실패");
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);
	}

	public String existUserId(HttpServletRequest request, UserVO userVO) throws ServletException, IOException {
		// 1. 처리
		System.out.println("existUserId userid->" + userVO.getUserid());
		UserVO existUser = userService.view(userVO);
		Map<String, Object> map = new HashMap<>();
		System.out.println(existUser);

		if (existUser == null) { // 사용가능한 아이디
			map.put("existUser", false);
		} else { // 사용 불가능 아아디
			map.put("existUser", true);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);
	}

}
