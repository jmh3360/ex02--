package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * 
	 * log.info("list"); model.addAttribute("list",service.getList()); }
	 */
	@GetMapping("/list")
	public void list(Criteria cri ,Model model) {
		
		log.info("list");
		model.addAttribute("list",service.getList(cri));
		int total = service.getTot(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	@GetMapping("/register")
	public void register() {
		
	}
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("register : " + vo);
		
		service.register(vo);
		
		rttr.addFlashAttribute("result", vo.getBno());
		
		return "redirect:/board/list";
	}
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	@PostMapping("/modify")
	public String modify(BoardVO vo,  RedirectAttributes rttr,@ModelAttribute("cri") Criteria cri) {
		log.info("modify : "+vo);
		log.info("modify : "+cri);
		if (service.modify(vo)) rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list"+cri.getListLink();
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,RedirectAttributes rttr,@ModelAttribute("cri") Criteria cri) {
		log.info("remove .... : "+bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list"+cri.getListLink();
	}
}
