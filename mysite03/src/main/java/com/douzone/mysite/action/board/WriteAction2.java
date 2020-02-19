package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction2 implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		Long userNo = Long.parseLong(request.getParameter("userno"));
		int maxGno = new BoardRepository().findMaxGNo();
		
		
		
		
		
		
		String childCheck = request.getParameter("child");
		

		if ("y".equals(childCheck)) {
			int no = Integer.parseInt(request.getParameter("no"));			
			int gNo = new BoardRepository().findGNo(no);
			int orderNo = new BoardRepository().findONo(no);
			
			BoardVo vo = new BoardVo();
			
			vo.setTitle(title);
			vo.setContents(content);
			vo.setName(name);
			vo.setHit(0);
			vo.setGroupNo(gNo);
			vo.setGroupOrNo(orderNo+1);
			vo.setDepth(orderNo);
			vo.setUserNo(userNo);
			new BoardRepository().updateOno(gNo, orderNo+1);
			Boolean a = new BoardRepository().insert(vo);
			request.setAttribute("vo", vo);
		}

		else {
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(content);
			vo.setName(name);
			vo.setHit(0);
			vo.setGroupNo(maxGno + 1);
			vo.setGroupOrNo(1);
			vo.setDepth(0);
			vo.setUserNo(userNo);
			Boolean a = new BoardRepository().insert(vo);
			request.setAttribute("vo", vo);
		}
		WebUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
