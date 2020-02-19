package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
//		
		String pageNo = request.getParameter("page");
		
		
		


		List<BoardVo> list = new BoardRepository().pagingList(0,5);
		int totalCount = new BoardRepository().getTotalCount();
		request.setAttribute("list", list);
		request.setAttribute("pageNo", 1);
		request.setAttribute("totalCount", totalCount);
		

		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
	}
}
