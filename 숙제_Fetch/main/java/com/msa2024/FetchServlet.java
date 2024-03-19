package com.msa2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;


@Data
class User {
	private String name;
	private int age; 
}
/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/fetch")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("name = " + name);
		
		
		//10초 대기 
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if ("text".equals(name)) {
			//응답결과 text로 전달
			response.getWriter().append("hongjildong,20");
		} else if ("json".equals(name)) {
			//응답을 json을 전달 
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append("{\"result\":true, \"name\":\"홍길동\", \"age\" : 20}");
		} else {
			response.getWriter().append("fetch 호출 결과 ");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		
		System.out.println("contentType->" + contentType);
		
		//브라우저에서 전달한 값 얻기 
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		//브라우저에서 전달한 값 출력 
		System.out.println("type = " + type);
		System.out.println("name = " + name);
		System.out.println("age = " + age);
		
		if (contentType.startsWith("application/x-www-form-urlencoded")) {
			if ("text".equals(type)) {
				//응답 데이터 텍스트로 전달 
				response.getWriter().append("1");
			} else if("json".equals(type)) {
				//응답을 json을 전달 
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append("{\"result\":true, \"name\":\"홍길동\", \"age\" : 20}");
			}
		} else if (contentType.startsWith("application/json")) {
			//json을 전달된 문자열 처리

			//byte를 char로 변환해줌, 처리 속도를 빠르게 하기위해 버퍼링 한다  
			//lines() 함수를 호출하면 모든 내용을 라인 단위로 읽어 배열(stream) 로 구성함
			//하나의 긴 문자열로 변환하는 것
//			String jsonText = new BufferedReader(new InputStreamReader(request.getInputStream()))
//			.lines()
//			.collect(Collectors.joining());
//			//jsonText = {"name":"hong", "age":10};
//			
//			System.out.println("jsonText => " + jsonText);
//			
//			//자바에서 json 문자열을 객체로 변환하는 방법 
//			ObjectMapper objectMapper = new ObjectMapper();
//			User user = objectMapper.readValue(jsonText, User.class);
			
			//자바에서 stream에 있는 문자열을 객체로 변환하는 방법 
			ObjectMapper objectMapper = new ObjectMapper();
			User user = objectMapper.readValue(request.getInputStream(), User.class);
			
			System.out.println("name =>" + user.getName());
			System.out.println("age =>" + user.getAge());
			
//			//10초 대기 
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			//응답을 json을 전달 
			response.setContentType("application/json;charset=UTF-8");
			//response.getWriter().append("{\"result\":true, \"name\":\"홍길동\", \"age\" : 20}");
			//map 객체를 json 문자열로 변환 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", true);
			map.put("name", "홍길동");
			map.put("age", 20);
			response.getWriter().append(objectMapper.writeValueAsString(map));
			
		}
	}

}







