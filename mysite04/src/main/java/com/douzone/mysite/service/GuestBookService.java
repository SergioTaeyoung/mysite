package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookRepository guestbookRepository;
	
	public void guestBookList(Model model) {
		List<GuestBookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);		
	}

	public void guestBookInsert(GuestBookVo vo) {
		guestbookRepository.insert(vo);		
	}

	public int guestBookDelete(Long no, String password) {
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPassword(password);
		int result = guestbookRepository.delete(vo);
		return result;		
	}

	public List<GuestBookVo> getMessageList(Long startNo) {
		return guestbookRepository.findAll(startNo);
	}
	
	public boolean writeMessage( GuestBookVo vo ) {
		int count = guestbookRepository.insert(vo);
		return count == 1;
	}
	
}