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

public class PagingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int pageNo = Integer.parseInt(request.getParameter("page"));
		if(pageNo<1) {
			pageNo =1;
		}

		
		int start = (pageNo-1)*5;		

		int totalCount = new BoardRepository().getTotalCount();
		double maxPage = totalCount/5.0;		
		maxPage = Math.ceil(maxPage);
		
		
		List<BoardVo> list = new BoardRepository().pagingList(start,5);		
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("maxPage", maxPage);
		
		
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
