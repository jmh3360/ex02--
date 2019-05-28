package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	/* @Select("select * from tbl_board where bno > 0") */
	public List<BoardVO> getList();
	
	public int getTotCount(Criteria cri);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO vo);
	
	public void insertSelectKey(BoardVO vo);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO vo);
	
	
}
