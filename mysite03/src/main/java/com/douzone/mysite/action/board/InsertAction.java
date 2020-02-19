package com.douzone.mysite.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		String title = request.getParameter("title");		
		String contents = request.getParameter("content");		
		
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			
			
			
			new BoardRepository().insert(vo);
			WebUtil.redirect(request.getContextPath()+"/board/list.jsp", request, response);
			
			
			
			
			
			
	}

}
