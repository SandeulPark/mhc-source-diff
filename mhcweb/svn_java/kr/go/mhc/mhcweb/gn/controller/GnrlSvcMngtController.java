package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.gn.service.GnrlSvcMngtService;
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
@RequestMapping(value = "/gn")
public class GnrlSvcMngtController extends DMultiActionController{
	
	@Resource(name = "web.gn.GnrlSvcMngtService")
	private GnrlSvcMngtService gnrlSvcMngtService;

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
		return "web/gn/healthInfoMngt";
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
			int gridTotalRowCount = gnrlSvcMngtService.getHealthInfoMngtListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}		
		List<Map<String, String>> rsList = gnrlSvcMngtService.getHealthInfoMngtList(param);
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
		Map<String, Object> rsMap = gnrlSvcMngtService.getHealthInfoDtls(param);
		List<Map<String, String>> cmntyBoardPostGrp = gnrlSvcMngtService.getAllCmntyGroupList(param);
		List<Map<String, String>> selectCmntyBoardPostGrp = gnrlSvcMngtService.getCmntyGroupList(param);
		List<Map<String, String>> cmntyContAttch = gnrlSvcMngtService.getSelectAttchFileList(param);
		int rsGoodCnt = gnrlSvcMngtService.getGoodCnt(param);
		
		model.addAttribute("rsMap",rsMap);
		model.addAllAttributes(param);
		model.addAttribute("cmntyBoardPostGrp",cmntyBoardPostGrp);
		model.addAttribute("selectCmntyBoardPostGrp",selectCmntyBoardPostGrp);
		model.addAttribute("cmntyContAttch",cmntyContAttch);
		model.addAttribute("rsGoodCnt",rsGoodCnt);
		
		return "web/gn/healthInfoDtls";
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
		List<Map<String, String>> rsList = gnrlSvcMngtService.getHealthInfoCmmntList(param);		
		int rsGoodCnt = gnrlSvcMngtService.getGoodCnt(param);
		int rsReCnt = gnrlSvcMngtService.getReCnt(param);
		
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
		List<Map<String, String>> rsList = gnrlSvcMngtService.getHealthInfoGoodList(param);		
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
		gnrlSvcMngtService.getHealthInfoDtlsUpdate(param);

		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "redirect:../pageNavi.do?menuCd=NGN132";
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
		return "web/gn/healthInfoReCmmnt";
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
			gnrlSvcMngtService.getHealthInfoCmmntInsert(param);
		} else {
			gnrlSvcMngtService.getHealthInfoReCmmntInsert(param);
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
		gnrlSvcMngtService.gethealthInfoReCmmntDelete(param);
	}

	/**
	 * 신규 건강정보 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthInfoReg.do", method = RequestMethod.GET)
	public String healthInfoReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = gnrlSvcMngtService.getAllCmntyGroupList(param);
		model.addAllAttributes(param);
		model.addAttribute("rsList",rsList);
		return "web/gn/healthInfoReg";
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
		gnrlSvcMngtService.getnewHealthInfoInsert(param);
		
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "redirect:../pageNavi.do?menuCd=NGN132";
	}
	
	/**
	 * 건강정보 게시여부 컨트롤  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/updateArticlePostClf.do", method = RequestMethod.POST)
	public String updateArticlePostClf(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlSvcMngtService.updateArticlePostClf(param);
		
		String postYn = StringUtil.nvl(String.valueOf(param.get("POST_YN")),"N");
		if("Y".equals(postYn)){
			Map<String,String> grpList = pushService.selectGrpList(param);
			param.put("selGroupList", grpList.get("GRP_LIST"));
		}
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "web/gn/healthInfoMngt";
	}

	/**
	 * 글 게시시 푸시 메시지를 전송했는지 확인하고 미전송시 전송
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	private void chkPushSnd(HttpServletRequest req, Map<String, Object> param){
		
		System.out.println("GnrlSvcMngtController / chkPushSnd param ====> " + param);

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
			gnrlSvcMngtService.healthInfoDtlsDelete(param);
		}catch(Exception e){
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
}
