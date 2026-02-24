package kr.go.mhc.mhcweb.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : BoardController.java
 * @Description : 게시판 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class BoardController extends DMultiActionController {
	
	@Resource(name = "web.sm.BoardService")
	private BoardService boardService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 질의응답 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/boardQna.do")
	public String boardQna(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/boardQna";
	}
	
	/**
	 * 질의응답 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/boardQnaList.do")
	public @ResponseBody Map<String, Object> boardQnaList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = boardService.getBoardQnaList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 질의응답 신규 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/boardQnaReg.do")
	public String newBoardQna(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		
		return "web/sm/boardQnaReg";
	}
	
	/**
	 * 질의응답 상세화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/boardQnaDtls.do")
	public String boardQnaDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsQueMap = boardService.getQueDtls(param);
		Map<String, Object> rsAnsMap = boardService.getAnsDtls(param);
		
		
		System.out.println("detailForm ===================================== " + param);		
		
		List<Map<String, Object>> qnaAttchFileList = boardService.getQnaAttchFileList(param);
		
		String url = "web/sm/boardQnaDtls";
		String qnaPop = param.get("qnaPop")==null?"": param.get("qnaPop").toString(); //질의상세 답급달기 -> 질문보기 팝업구분
		
		if(!"Y".equals(qnaPop)){
			boardService.setBoardInquireCnt(param);
		}else if("Y".equals(qnaPop)){
			url = "web/sm/boardQnaPopup";
		}

		
		
		model.addAttribute("rsQueMap",rsQueMap);
		model.addAttribute("rsAnsMap",rsAnsMap);		
		model.addAttribute("qnaAttchFileList",qnaAttchFileList);
		
		
		
		model.addAllAttributes(param);
		return url;
	}
	
	/**
	 * 질의응답 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveBoardQna.do")
	public String saveBoardQna(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		boardService.saveBoardQna(param);
		
		return "redirect:../pageNavi.do?menuCd=NCM331";
	}
	
	/**
	 * 질의응답 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		boardService.deleteBoard(param);
		
		return "redirect:../pageNavi.do?menuCd=NCM331";
	}
	
	/**
	 * 이미지 미리보기 팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/attchPreview.do")
	public String attchPreview(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/sm/boardPopup";
	}
}
