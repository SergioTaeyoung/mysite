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
		List<BoardVo> list = boardRepository.findAll();
		model.addAttribute("list", list);	
	}

	public void boardView(Long no, Model model) {
		BoardVo list = boardRepository.findByNo(no);
		model.addAttribute("vo", list);		
	}

	public void boardDelete(Long no, Model model) {
		Boolean delete = boardRepository.delete(no);
		
	}

//	public void boardWrite(BoardVo vo) {
////		int maxGno = boardRepository.findMaxGNo();		
////		
////		vo.setTitle(title);
////		vo.setContents(content);
////		vo.setName(name);
////		vo.setHit(0);
////		vo.setGroupNo(maxGno + 1);
////		vo.setGroupOrNo(1);
////		vo.setDepth(0);
////		vo.setUserNo(userNo);		
//		
//		BoardVo list = boardRepository.insert(vo);			
//	}

	public void boardWrite(@PathVariable("userNo") Long userNo, BoardVo vo) {
		vo.setGroupNo(boardRepository.findMaxGNo());
		vo.setUserNo(userNo);
		System.out.println("!!!"+userNo);
		BoardVo list = boardRepository.insert(vo);
		
	}



}
