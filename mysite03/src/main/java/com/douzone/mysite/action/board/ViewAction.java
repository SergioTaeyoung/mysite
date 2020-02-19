package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		Long no = Long.parseLong(request.getParameter("no"));	
		
		BoardVo vo = new BoardRepository().findByNo(no);
		new BoardRepository().hit(vo);
		
		request.setAttribute("name", name);
		request.setAttribute("vo", vo);
		
		
	
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
