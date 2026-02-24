package kr.go.mhc.mhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sv.service.CnslReqMngService;
import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : CnslReqMngController.java
 * @Description : 관리자 WEB에서 사용하는 실시간 상담업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.09		장슬기			최초생성
 *
 * @author gst
 * @since 2016.08.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class CnslReqMngController extends DMultiActionController{
	
	@Resource(name = "web.sv.CnslReqMngService")
	private CnslReqMngService cnslReqMngService;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private TrgterInfoMngtService trgterInfoMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 실시간 상담관리화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslReqMain.do")
	public String cnslReqMain(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/cnslReqMng";
	}
	
	/**
	 * 상담요청목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslReqList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cnslReqList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = cnslReqMngService.getCnslReqList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 *  1:1 상담요청목록화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslReqMngReqList_pop.do")
	public String cnslReqMngReqList(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/cnslReqMngReqList";
	}
	
	/**
	 *  1:1 상담요청목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/one_on_one_cnsl_List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> one_on_one_cnsl_List(@ModelAttribute Map param, ModelMap model) throws Exception{
		System.out.println("PARAM::::::::::::::::::::"+param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = cnslReqMngService.getOne_on_one_cnsl_List(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 조회 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgt_chatInquire_pop.do", method = RequestMethod.GET)
	public String trgt_chatInquire(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		String schsel = (String) param.get("schSel");
		String req = (String) param.get("req");
		String user_id = (String) param.get("USER_ID");
		String sel = (String) param.get("schSel");
		String seq = (String) param.get("CHAT_SN");
		if("req".equals(req)){
			cnslReqMngService.cnslChatMasterUp(param);
			cnslReqMngService.cnslCnslerInsert(param);
			cnslReqMngService.realtimeCnslIngUpdate(param);
		}else if("ing".equals(req)){
			List<Map<String, String>> rsList = cnslReqMngService.cnslInquireCont(param);
			model.addAttribute("rsList",rsList);
		}else{
			int getSeq =  cnslReqMngService.getChatSeq(param);
			param.put("CHAT_SN", getSeq);
			int getCnslSeq = cnslReqMngService.getChatCnslSeq(param);
			param.put("CNSL_SN", getCnslSeq);
			param.put("CNSL_ITEM_CLF", schsel);
			cnslReqMngService.chatInsertCnslTB(param);
			cnslReqMngService.chatMasterInsert(param);
			cnslReqMngService.chatCnslerInsert(param); 
			cnslReqMngService.chatTrgterInsert(param);
		}
		Map<String, String> rsMap = cnslReqMngService.getTrgtInfo(param);
		model.addAttribute("rsMap", rsMap);
	//	model.addAttribute("chatSeq", chatSeq);
//		return "web/sv/cnslReqMngPopChat";
		return "web/tg/trgterInfoMngtPop2";
	}
	
	/**
	 * 1:1 상담 중복 체크
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/countRealTimeCnslChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> countRealTimeCnslChk(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int realCnslChk = trgterInfoMngtService.countRealTimeCnslChk(param);
		rsMap.put("realCnslChk", realCnslChk);
		rsMap.put("USER_ID", param.get("USER_ID"));
		rsMap.put("CNSL_ITEM_CLF", param.get("CNSL_ITEM_CLF"));
		rsMap.put("SVC_MNGT_NO", param.get("SVC_MNGT_NO"));
		rsMap.put("GRID_ID", param.get("id"));
		return rsMap;
	}
	
	/**
	 *  1:1 상담화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslReqMngPopChat.do")
	public String cnslReqMngPopChat(@ModelAttribute Map param, ModelMap model) throws Exception {
//		return "web/sv/cnslReqMngPopChat";
		return "web/tg/trgterInfoMngtPop2";
		
	}
	
	/**
	 *  상담이력목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslhist.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cnslhist(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = cnslReqMngService.getCnslhist(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 수정 정보 업데이트 
	 * @param param 수정 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslEnd.do", method = RequestMethod.POST)
	public String cnslEnd(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		cnslReqMngService.cnslEndChatMasterUp(param);
		cnslReqMngService.realtimeCnslCompUpdate(param);
		model.addAttribute("rsMap", param);
//		return "web/sv/cnslReqMngPopChat";
		return "web/tg/trgterInfoMngtPop2";
	}

	/**
	 * 채팅 내용 저장하기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertWebMessage.do" , method = RequestMethod.POST)
	public void insertChatMessage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		cnslReqMngService.insertWebMessage(param); // 마스터에 넣기
	}		
	
	/**
	 * 채팅방 대화내용 조회하기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectWebTalkList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectWebTalkList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = cnslReqMngService.selectWebTalkList(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
}
