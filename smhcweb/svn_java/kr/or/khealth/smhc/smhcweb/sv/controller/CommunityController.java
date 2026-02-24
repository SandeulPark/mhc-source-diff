package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.CommunityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : CommunityController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.07		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class CommunityController extends DMultiActionController{

	@Resource(name="web.sv.CommunityService")
	private CommunityService communityService;
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 커뮤니티 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/communityList.do")
	public String missionImplStatus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		return "web/sv/community";
	}
	
	/**
	 * 커뮤니티 보건소 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectCommunityMyTabList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectCommunityMyTabList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("ORG_CD") != null) {
			if(param.get("ORG_CD").equals("")) {
				param.put("ORG_CD", "ALL");
			}
		}
		if(param.get("SIDO_CD") != null) {
			param.put("SIDO_CD", param.get("SIDO_CD"));
		}
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = communityService.selectCommunityMyTabListCount(param);
			System.out.println("######### gridTotalRowCount ===> " + gridTotalRowCount);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = communityService.selectCommunityMyTabList(param);
			System.out.println("######## rsList ===> " + param.get("rsList") );
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 커뮤니티 개발원 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectCommunityOtherTabList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectCommunityOtherTabList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("ORG_CD") != null) {
			if(param.get("ORG_CD").equals("")) {
				param.put("ORG_CD", "ALL");
			}
		}
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = communityService.selectCommunityOtherTabListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = communityService.selectCommunityOtherTabList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 커뮤니티  등록페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/communityInsertPage.do", method = RequestMethod.POST)
	public String communityInsertPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/communityInsert";
	}
	
	/**
	 * 커뮤니티  상세페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/communityDtlsPage.do", method = RequestMethod.POST)
	public String communityDtlsPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/communityDtls";
	}
	
	/**
	 * 커뮤니티 게시물  등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/communityBoardReg.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityBoardReg(@ModelAttribute Map<String, Object> param , ModelMap model, HttpServletRequest req) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		System.out.println("######## communityBoardReg.do START #######");
		
		int rsInt = 0;
		
		String sendTarget = StringUtil.nvl((String)param.get("sendTarget"));
		param.put("SEND_TARGET_LIST", StringUtil.makeStringToIterator(sendTarget));
		
		System.out.println("### 1. sendTarget ===> " + sendTarget);
		
		try{
			//커뮤니티 게시글 등록
			int selBoardSN = communityService.getBoardSn(param);
			System.out.println("### 2. selBoardSN ===> " + selBoardSN);
			param.put("BOARD_SN", selBoardSN);			
			rsInt = communityService.communityBoardReg(param);
			System.out.println("### 3. rsInt ===> " + rsInt);
			//push등록
			param.put("boardSn", param.get("BOARD_SN"));
			Map<String,String> pushSnd = pushService.getPushSndYn(param);
			System.out.println("### 3. pushSnd ===> " + pushSnd);
			if("N".equals(pushSnd.get("PUSH_SND_YN"))){
				//String linkPage = "";
				String linkPage = "/page/sv/community.html";
				String selGroupList = StringUtil.nvl(String.valueOf(param.get("sendTarget")));
				
				param.put("pushLinkPage", linkPage);//이동할 페이지
				param.put("grpList", selGroupList);
				pushMessageUtil.setReqData(req,param);
				boolean rsFlag = pushService.sendPushData(pushMessageUtil,param);
				System.out.println("### 4. rsFlag ===> " + rsFlag);
				//if(rsFlag){
					pushService.setPushSndYn(param);
				//}
			}
			
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}
	
	/**
	 * 커뮤니티  수정 페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/communityEditPage.do", method = RequestMethod.POST)
	public String communityEditPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/communityInsert";
	}
	/**
	 * 커뮤니티  수정시 기본데이터 가저오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectBoardData.do")
	public @ResponseBody Map<String,Object> selectBoardData(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			Map<String,Object> data = communityService.selectBoardData(param);
			List<Map<String, Object>> imgData = communityService.selectimgBoardData(param);
			rsMap.put("data", data);
			rsMap.put("imgData", imgData);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//게시물수정
	@RequestMapping(value= "/communityBoardEdit.do")
	public @ResponseBody Map<String,Object> communityBoardEdit(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object >();
		int rsInt = 0;
		
		String asisTarget = StringUtil.nvl((String)param.get("asisTarget"));
		param.put("ASIS_TARGET", StringUtil.makeStringToIterator(asisTarget));
		
		String sendTarget = StringUtil.nvl((String)param.get("sendTarget"));
		param.put("SEND_TARGET", StringUtil.makeStringToIterator(sendTarget));
		
		try{
			rsInt = communityService.communityBoardEdit(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//수정시 기존이미지 삭제의경우
	@RequestMapping(value = "/defAttchFile.do")
	public @ResponseBody Map<String, Object> defAttchFile(@ModelAttribute Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String,Object>();
		int rsInt = 0;
		try {
			rsInt = communityService.defAttchFile(param);
			resultMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("chkYn", "N");
		}
		return resultMap;
	}
	
	//커뮤니티 상세 게시글 삭제(flag Y->N)
	@RequestMapping(value= "/deleteBoard.do")
	public @ResponseBody Map<String,Object> deleteBoard(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try{
			rsInt = communityService.deleteBoard(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//커뮤니티 상세 내역(내용&이미지)
	@RequestMapping(value= "/selectCommunityDetailBoard.do")
	public @ResponseBody Map<String,Object> selectCommunityDetailBoard(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			Map<String, Object> boardData = communityService.selectCommunityDetailBoardData(param);
						
			List<Map<String, Object>> boardImg = communityService.selectCommunityDetailBoardImg(param);
			List<Map<String, Object>> boardVideo = communityService.selectCommunityDetailBoardVideo(param);
						
			rsMap.put("boardData", boardData);
			rsMap.put("boardImg", boardImg);
			rsMap.put("boardVideo", boardVideo);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//커뮤니티 상세 내역(댓글)
	@RequestMapping(value= "/selectCommunityDetailCmmnt.do")
	public @ResponseBody Map<String,Object> selectCommunityDetailCmmnt(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String, Object>> cmmntList = communityService.selectCommunityDetailCmmnt(param);
			
			rsMap.put("cmmntList", cmmntList);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	//커뮤니티 상세 내역(좋아요)
	@RequestMapping(value= "/selectCommunityDetailLike.do")
	public @ResponseBody Map<String,Object> selectCommunityDetailLike(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String, Object>> cmmntList = communityService.selectCommunityDetailLike(param);
			
			rsMap.put("cmmntList", cmmntList);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//단일댓글 등록
	@RequestMapping(value= "/insertCommunitySingleCmmnt.do")
	public @ResponseBody Map<String,Object> insertCommunitySingleCmmnt(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try{
			rsInt = communityService.insertCommunitySingleCmmnt(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	//단일댓글 수정
	@RequestMapping(value= "/updateCommunitySingleCmmnt.do")
	public @ResponseBody Map<String,Object> updateCommunitySingleCmmnt(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try{
			rsInt = communityService.updateCommunitySingleCmmnt(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	//멀티댓글 등록
	@RequestMapping(value= "/insertCommunityMultiCmmnt.do")
	public @ResponseBody Map<String,Object> insertCommunityMultiCmmnt(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		
		String UPPER_CMMNT_SN = StringUtil.nvl((String)param.get("UPPER_CMMNT_SN"));
		param.put("UPPER_CMMNT_SN_LIST", StringUtil.makeStringToIterator(UPPER_CMMNT_SN));
		
		try{
			rsInt = communityService.insertCommunityMultiCmmnt(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
}
