package kr.go.mhc.mhcweb.sv.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.service.CommonService;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.sv.service.SvcMngtService;


/**
 * @Class Name : SvcMngtController.java
 * @Description : 관리자 WEB에서 사용하는 서비스관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class SvcMngtController extends DMultiActionController{
	
	@Resource(name = "web.sv.SvcMngtService")
	private SvcMngtService svcMngtService;

	@Resource(name="common.pushService")
	private PushService pushService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강정보관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoMngt.do", method = RequestMethod.GET)
	public String healthInfoMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sv/healthInfoMngt";
	}
	
	/**
	 * 건강정보관리 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthInfoMngtList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = svcMngtService.getHealthInfoMngtListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}		
		List<Map<String, String>> rsList = svcMngtService.getHealthInfoMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 건강정보 상세 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoDtls.do", method = RequestMethod.GET)
	public String healthInfoDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = svcMngtService.getHealthInfoDtls(param);
		List<Map<String, String>> cmntyBoardPostGrp = svcMngtService.getAllCmntyGroupList(param);
		List<Map<String, String>> selectCmntyBoardPostGrp = svcMngtService.getCmntyGroupList(param);

		param.put("CMMN_CD", "TC_SV_HEALTH_MNGT_OBJ");
		List<Map<String, String>> cmntyBoardPostMclas = cmmnService.selectCmmnCd(param);
		List<Map<String, String>> selectCmntyBoardPostMclas = svcMngtService.getCmntyMclasList(param);
		param.remove("CMMN_CD");

		param.put("CMMN_CD", "TG015");
		List<Map<String, String>> cmntyBoardPostChronic = cmmnService.selectCmmnCd(param);
		List<Map<String, String>> selectCmntyBoardPostChronic = svcMngtService.getCmntyChronicList(param);

		List<Map<String, String>> cmntyContAttch = svcMngtService.getSelectAttchFileList(param);
		int rsGoodCnt = svcMngtService.getGoodCnt(param);
		
		model.addAttribute("rsMap",rsMap);
		model.addAllAttributes(param);
		model.addAttribute("cmntyBoardPostGrp",cmntyBoardPostGrp);
		model.addAttribute("selectCmntyBoardPostGrp",selectCmntyBoardPostGrp);

		model.addAttribute("cmntyBoardPostMclas",cmntyBoardPostMclas);
		model.addAttribute("selectCmntyBoardPostMclas",selectCmntyBoardPostMclas);

		model.addAttribute("cmntyBoardPostChronic",cmntyBoardPostChronic);
		model.addAttribute("selectCmntyBoardPostChronic",selectCmntyBoardPostChronic);

		model.addAttribute("cmntyContAttch",cmntyContAttch);
		model.addAttribute("rsGoodCnt",rsGoodCnt);
		
		return "web/sv/healthInfoDtls";
	}
	
	/**
	 * 댓글 정보 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoCmmntList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthInfoCmmntList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();			
		List<Map<String, String>> rsList = svcMngtService.getHealthInfoCmmntList(param);		
		int rsGoodCnt = svcMngtService.getGoodCnt(param);
		int rsReCnt = svcMngtService.getReCnt(param);
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			rsMap.put("gridTotalRowCount", rsReCnt);
		}
		rsMap.put("rsList", rsList);
		rsMap.put("rsReCnt", rsReCnt);
		rsMap.put("rsGoodCnt", rsGoodCnt);
		rsMap.put("id", param.get("id"));	
		return rsMap;
	}
	
	/**
	 * 표현정보 정보 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoGoodList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthInfoGoodList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();			
		List<Map<String, String>> rsList = svcMngtService.getHealthInfoGoodList(param);		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 건강정보상세 수정 
	 * @param 수정된 param 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoDtlsUpdate.do", method = RequestMethod.POST)
	public String healthInfoDtlsUpdate(@ModelAttribute Map<String, Object> param, ModelMap model,HttpServletRequest req) throws Exception {
		svcMngtService.getHealthInfoDtlsUpdate(param);

		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "redirect:../pageNavi.do?menuCd=NSV122";
	}
	
	/**
	 * 답변 달기 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoReCmmnt.do", method = RequestMethod.GET)
	public String healthInfoReCmmnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sv/healthInfoReCmmnt";
	}
	
	/**
	 * 답변 및 댓글 저장  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoReCmmntInsert.do", method = RequestMethod.POST)
	public @ResponseBody void healthInfoReCmmntInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		if(param.get("cmmntClf").equals("AC")){
			svcMngtService.getHealthInfoCmmntInsert(param);
		} else {
			svcMngtService.getHealthInfoReCmmntInsert(param);
		}
	}
	
	/**
	 * 답변 및 댓글 취소  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoReCmmntDelete.do", method = RequestMethod.POST)
	public @ResponseBody void healthInfoReCmmntDelete(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		svcMngtService.gethealthInfoReCmmntDelete(param);
	}

	/**
	 * 신규 건강정보 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoReg.do", method = RequestMethod.GET)
	public String healthInfoReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = svcMngtService.getAllCmntyGroupList(param);
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);

		model.addAllAttributes(param);
		model.addAttribute("rsList",rsList);
		model.addAttribute("selList", selList);

		return "web/sv/healthInfoReg";
	}
	
	/**
	 * 신규 건강정보 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/newHealthInfoInsert.do", method = RequestMethod.POST)
	public String newHealthInfoInsert(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		// 1. 커뮤니티 등록
		svcMngtService.getnewHealthInfoInsert(param);
		
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "redirect:../pageNavi.do?menuCd=NSV122";
	}
	
	/**
	 * 건강정보 게시여부 컨트롤  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/updateArticlePostClf.do", method = RequestMethod.POST)
	public String updateArticlePostClf(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		svcMngtService.updateArticlePostClf(param);
		
		String postYn = StringUtil.nvl(String.valueOf(param.get("POST_YN")),"N");
		if("Y".equals(postYn)){
			//그룹 조회
			Map<String,String> grpList = pushService.selectGrpList(param);
			param.put("selGroupList", grpList.get("GRP_LIST"));
			
			//군분류 조회
			Map<String,String> mclasList = pushService.selectMclasList(param);
			if(mclasList != null) param.put("selMclasList", mclasList.get("MCLAS_LIST"));
						
			//만성질환 분류 조회
			Map<String,String> chronicList = pushService.selectChronicList(param);
			if(chronicList != null) param.put("selChronicList", chronicList.get("CHRONIC_LIST"));
		}
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "web/sv/healthInfoMngt";
	}
		
	/**
	 * 글 게시시 푸시 메시지를 전송했는지 확인하고 미전송시 전송
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	private void chkPushSnd(HttpServletRequest req, Map<String, Object> param){
		
		System.out.println("SvcMngtController / chkPushSnd param ====> " + param);

		try{
			// 2. 해당 게시물 푸시가 전송 되었는지 확인.
			Map<String,String> pushSnd = pushService.getPushSndYn(param);
			if(pushSnd != null){
				if("N".equals(pushSnd.get("PUSH_SND_YN"))){
					// 1. push 정보 확인
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap = pushService.getPushSetInfo(param);
					
					if(pMap != null && !pMap.isEmpty()) {
					
						String pushTitle = StringUtil.nvl(String.valueOf(pMap.get("PUSH_TITLE")),"");
						String pushCont = StringUtil.nvl(String.valueOf(pMap.get("PUSH_CONT")),"");
						String pushLinkPage = StringUtil.nvl(String.valueOf(param.get("pushLinkPage")),String.valueOf(pMap.get("PUSH_LINK_PAGE")));
						String noticeTitle = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_TITLE")),"");
						String noticeCont = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_CONT")),"");
						String noticeLinkPage = StringUtil.nvl(String.valueOf(param.get("noticeLinkPage")),String.valueOf(pMap.get("NOTICE_LINK_PAGE")));
						String selGroupList = StringUtil.nvl(String.valueOf(param.get("selGroupList")));						
						
						// 2. 푸시 값 설정
						param.put("pushTitle", pushTitle);
						param.put("pushCont", pushCont);
						param.put("pushLinkPage", pushLinkPage);
						param.put("noticeTitle", noticeTitle);
						param.put("noticeCont", noticeCont);
						param.put("noticeLinkPage", noticeLinkPage);
						param.put("grpList", selGroupList);				
						pushMessageUtil.setReqData(req,param);
						
						boolean rsFlag = pushService.sendPushData(pushMessageUtil,param);
						if(rsFlag){
							pushService.setPushSndYn(param);
						}
					
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	
	/**
	 * 건강정보상세 삭제 
	 * @param param 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoDtlsDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> healthInfoDtlsDelete(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "Y";
		try{
			svcMngtService.healthInfoDtlsDelete(param);
		}catch(Exception e){
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
}
