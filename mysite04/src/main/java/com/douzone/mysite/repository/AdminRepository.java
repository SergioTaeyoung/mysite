package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(SiteVo vo) {
		return sqlSession.insert("admin.insert", vo);
	}
	
	public SiteVo findAll() {		
		return sqlSession.selectOne( "admin.findAll");
	}

}
