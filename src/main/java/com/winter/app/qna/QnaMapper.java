package com.winter.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaMapper {

	public List<QnaVO> getList(Pager pager)throws Exception;
	
	public int add(QnaVO qnaVO)throws Exception;
}
