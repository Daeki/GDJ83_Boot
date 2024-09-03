package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.util.Pager;

@Service
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}

}
