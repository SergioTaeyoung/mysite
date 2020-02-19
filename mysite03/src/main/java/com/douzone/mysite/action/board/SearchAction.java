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

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String keyWord = request.getParameter("kwd");
		String radioValue = request.getParameter("chk_info");
		List<BoardVo> list = new BoardRepository().search(keyWord,radioValue);
		
		request.setAttribute("list", list);
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);	
	}

}
