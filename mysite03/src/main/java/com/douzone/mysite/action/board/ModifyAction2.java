package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ModifyAction2 implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long no = Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);
		
		vo = new BoardRepository().update(vo);		
		request.setAttribute("vo", vo);		
		
		WebUtil.redirect(request.getContextPath()+"/board", request, response);
		
		
	}

}
