package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String name = request.getParameter("name");
//		String no = request.getParameter("userno");
////		int userNo = Integer.parseInt(no);
////		Long userNo = Long.parseLong(request.getParameter("userno"));
//		
//		
//		
////		System.out.println("!!!!"+name+"!!!!"+no);
//		System.out.println("!!!!"+name);
//		System.out.println("!!!!"+no+"!!!!");
//		
//		BoardVo vo = new BoardVo();
//		vo.setTitle(title);
//		vo.setContents(content);
//		vo.setName(name);		
//		vo.setHit(0);		
//		vo.setGroupNo(1);		
//		vo.setGroupOrNo(1);		
//		vo.setDepth(1);		
//		vo.setUserNo(1);
//		Boolean a = new BoardRepository().insert(vo);
//	
//		
//		request.setAttribute("vo", vo);
		String childCheck = request.getParameter("child");
		String no = request.getParameter("no");
		
		request.setAttribute("child", childCheck);
		request.setAttribute("no", no);
		WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
	}

}
