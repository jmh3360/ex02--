package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO vo = new BoardVO();
		vo.setTitle("새로 작성하는 글");
		vo.setContent("새로 작성하는 내용");
		vo.setWriter("뉴비");
		
		service.register(vo);
		
		log.info("생선된 게시물의 번호 : "+vo.getBno());
		log.info("vo 값 : "+vo);
	}
	
	@Test
	public void testGetList() {
		
		/* service.getList().forEach(row -> log.info(row)); */
		service.getList(new Criteria(2,10)).forEach(row -> log.info(row));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(2L));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO vo  = service.get(2L);
		if (vo== null ) return ;
		
		log.info("update service.get(2L) 이후 vo는 ? : "+vo );
		vo.setTitle("제목 수정합니다.");
		log.info("MODIFY RESULT : " + service.modify(vo));
		
	}
	
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT : " +service.remove(2L));
	}
	
}
