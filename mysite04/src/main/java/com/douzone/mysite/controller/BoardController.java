package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		String pageNo = "1";
		boardService.boardList(model);		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalcount", boardService.totalCount());
		model.addAttribute("maxPage", boardService.maxCount());
		return "board/list";
	}
	
	@RequestMapping(value = "paging/{pageNo}", method = RequestMethod.GET)
	public String pagingList( @PathVariable("pageNo") int pageNo, Model model) {		
		boardService.pagingList(model, pageNo);
		
		model.addAttribute("maxPage", boardService.maxCount());		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalcount", boardService.totalCount());
		return "board/list";
	}
	
	@RequestMapping(value = "view/{vo.no}/{vo.name}", method = RequestMethod.GET)
	public String view(@PathVariable("vo.no") Long no, @PathVariable("vo.name") String name, Model model, BoardVo vo) {
		model.addAttribute("no", no);
		model.addAttribute("name", name);
		boardService.boardView(no, model);
		boardService.boardHit(vo, no);
		return "board/view";
	}
	
	@RequestMapping(value = "delete/{vo.no}", method = RequestMethod.GET)
	public String delete(@PathVariable("vo.no") Long no, Model model) {
		model.addAttribute("no", no);
		boardService.boardDelete(no, model);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "write/{authUser.no}", method = RequestMethod.POST)
	public String write(@PathVariable("authUser.no") Long userNo, BoardVo vo, Model model) {
		model.addAttribute("userNo", userNo);
		boardService.boardWrite(userNo, vo);
		System.out.println(vo);
		return "board/write";
	}

	@RequestMapping(value = "modify/{vo.no}", method = RequestMethod.GET)
	public String modify(@PathVariable("vo.no") Long no, Model model) {
		model.addAttribute("no",no);
		return "board/modify";
	}
	
	@RequestMapping(value = "modify/{vo.no}", method = RequestMethod.POST)
	public String modify(@PathVariable("vo.no") Long no, Model model, BoardVo vo) {
		model.addAttribute("no",no);
		boardService.boardUpdate(no, vo);		
		return "board/modify";
	}
}