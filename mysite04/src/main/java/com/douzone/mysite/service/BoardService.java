package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public void boardList(Model model) {
		List<BoardVo> list = boardRepository.pagingList(0,5);
		model.addAttribute("list", list);	
	}
	
	public void pagingList(Model model,  @PathVariable("pageNo") int pageNo) {
		List<BoardVo> list = boardRepository.pagingList((pageNo-1)*5,5);
		model.addAttribute("list", list);	
	}

	public void boardView(Long no, Model model) {
		BoardVo list = boardRepository.findByNo(no);
		model.addAttribute("vo", list);		
	}

	public void boardDelete(Long no, Model model) {
		Boolean delete = boardRepository.delete(no);
		
	}

	public void boardWrite(@PathVariable("userNo") Long userNo, BoardVo vo) {
		vo.setGroupNo(boardRepository.findMaxGNo()+1);
		vo.setUserNo(userNo);		
		BoardVo list = boardRepository.insert(vo);
		
	}

	public void boardUpdate(@PathVariable("vo.no") Long no, BoardVo vo) {
		vo.setNo(no);
		boardRepository.update(vo);
				
	}

	public void boardHit(BoardVo vo, Long no) {
		vo.setNo(no);
		boardRepository.hit(vo);		
	}

	public int totalCount() {
		int total = boardRepository.getTotalCount();
		return total;
		
		
	}

	public double maxCount() {
		int tot = boardRepository.getTotalCount();
		double maxPage = tot/5.0;
		maxPage = Math.ceil(maxPage);
		return maxPage;		
	}



}