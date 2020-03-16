package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public void mainInsert(SiteVo vo) {
		adminRepository.insert(vo);		
	}

	public SiteVo List() {
		return adminRepository.findAll();
		
	}

}
