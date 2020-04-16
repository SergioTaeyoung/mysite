package com.douzone.mysite.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.douzone.mysite.vo.GuestBookVo;
@Repository
public class GuestBookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> findAll() {
		return sqlSession.selectList( "guestbook.findAll" );
	}
	
	public int delete( GuestBookVo vo ) {
		return sqlSession.delete( "guestbook.delete", vo );
	}
	
	public int insert( GuestBookVo vo ) {
		return sqlSession.insert( "guestbook.insert", vo );
	}
}