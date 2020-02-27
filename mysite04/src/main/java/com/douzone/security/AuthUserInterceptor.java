package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthUserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
		
		//1.handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 경우(보통, assets의 정적 자원 접근)
			return true;
		}
		//2. casting 
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
				
		//4. Method에 @Auth가 없으면 Type에 붙어 있는지 확인한다(과제)
		if(auth == null) {
			//auth = <- 
			//admin controller에 가서?
			
		}
		
		//5. Type이나 Method 둘다 @Auth가 적용이 안되어 있는경우,
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 붙어 있기 때문에 인증(Authentification)여부확인
		HttpSession session = request.getSession();	
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		//6. 권한(Authorization) 체크를 위해서 role 가져오기("USER","ADMIN")
		
		String role = auth.value();
		System.out.println("role:"+role);
		
		//7. @Auth의 role이 "USER"인 경우에는
		//	 authUser의 role이 "USER" 이든 "ADMIN"이든 상관이 없음.
		if("USER".equals(role)) {
			return true;
		}
		//8. @AuthUser의 role이 "ADMIN"인경우에는 반드시 authUser의 role이 "ADMIN"이어야 한다.
		if("ADMIN".equals(role)==false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// @Auth의 롤 => "ADMIN"
		// authUser의 role => "ADMIN"
		// 관리자 권한이 확인
		
		//인증 확인되었으므로 핸들러 메소드 실행
		return true;
	}
}
