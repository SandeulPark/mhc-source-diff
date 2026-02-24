package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.gn.service.GnrlMissionMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MissionMngtController.java
 * @Description : 관리자 WEB에서 사용하는 미션설정관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class GnrlMissionMngtController extends DMultiActionController {
	
	@Resource(name="web.gn.GnrlMissionMngtService")
	private GnrlMissionMngtService gnrlMissionMngtService;
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 미션설정관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/gn/missionMngt.do", method= RequestMethod.GET)
	public String missionMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/gn/missionMngt";
	}
	
	/**
	 * 미션설정관리 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/missionMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> missionMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("pagingSet[gridRowsPerPage]") != null){
			int gridTotalRowCount = gnrlMissionMngtService.getMissionMngtListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = gnrlMissionMngtService.getMissionMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
		
	}
	
	/**
	 * 미션설정관리 상세 화면
	 * @param param 기관코드, 미션코드, 순번
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionMngtDtls.do", method= RequestMethod.GET)
	public String missionMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("SCLAS_CD", param.get("POST_CLF"));
		List<Map<String, String>> rsList = gnrlMissionMngtService.getDtlsSelGrp(param);
		Map<String, String> rsMap = gnrlMissionMngtService.getMissionMngtDtls(param);
		List<Map<String, String>> rsListGrp = gnrlMissionMngtService.getDtlsSelGrp(param);
		//첨부파일 조회
		param.put("BOARD_SN", rsMap.get("BOARD_SN"));
		param.put("CMNTY_CD", rsMap.get("CMNTY_CD"));
		List<Map<String, String>> rsContAttch = gnrlMissionMngtService.getSelectAttchFileList(param);

		if("11".equals(param.get("POST_CLF"))){
			//적용안된그룹 메인updateMission
			List<Map<String, String>> rsListPostGubun = gnrlMissionMngtService.getNotSelMainGrp(param);
			model.addAttribute("rsListPostGubun", rsListPostGubun);
		} else if("12".equals(param.get("POST_CLF"))) {
			//적용안된그룹 커뮤니티
			List<Map<String, String>> rsListPostGubun = gnrlMissionMngtService.getMissionPostGubun(param);
			model.addAttribute("rsListPostGubun", rsListPostGubun);
			List<Map<String, String>> rsListPostDtlsGubun = gnrlMissionMngtService.getDtlsSelGrp(param);
			model.addAttribute("rsListPostDtlsGubun",rsListPostDtlsGubun);
		} 
		int rsReCnt = gnrlMissionMngtService.getCmntyReCnt(param);
		int rsExpCnt = gnrlMissionMngtService.getCmntyExpCnt(param);
		
		model.addAttribute("rsList", rsList);
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("rsListGrp", rsListGrp);
		model.addAttribute("rsContAttch", rsContAttch);
		model.addAttribute("rsReCnt", rsReCnt);
		model.addAttribute("rsExpCnt", rsExpCnt);

//		 && "진행중".equals(param.get("MISSION_STTU_NM")) && ("0" == param.get("CMNTY_GRP_MB_CNT")))
		if("11".equals(param.get("POST_CLF"))){			
			return "web/gn/missionMngtMain";			
		} else {
			return "web/gn/missionMngtCmnty";
		} 
		
	}
	
	/**
	 * 미션설정관리 커뮤니티 인 경우 답변 달기 화면 띄우기
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/reply_pop.do", method= RequestMethod.GET)
	public String replyPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/gn/missionPop";
	}
	
	/**
	 * 미션설정관리 커뮤니티 인 경우 게시 여부 update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/updatePostYN.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePostYN(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		param.put("LST_DML_ID", param.get("SESS_USER_ID"));
		Map<String, Object> rsMap = new HashMap<String, Object>();
		gnrlMissionMngtService.updatePostYN(param);
		rsMap.put("POST_YN", param.get("POST_YN"));
		return rsMap;
	}
	
	/**
	 * 미션설정관리 미션추가 화면
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionMngtReg.do", method= RequestMethod.GET)
	public String missionMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = gnrlMissionMngtService.getMissionSelGubun(param);
		model.addAttribute("rsList",rsList);
		model.addAllAttributes(param);
		
		return "web/gn/missionMngtReg";
	}
	
	/**
	 * 미션설정관리 미션선택 시 미션명, 미션설정 상세
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/selMission.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, String> selMssion(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = gnrlMissionMngtService.getSelMission(param);
		return rsMap;
		
	}
	
	/**
	 * 미션설정관리 게시구분 선택시 적용그룹 조회
	 * @param param 게시구분코드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionPostGubun.do", method= RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> missionPostGubun(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList2 = gnrlMissionMngtService.getMissionPostGubun(param);
		return rsList2;
		
	}
	
	/**
	 * 미션설정관리 참여대상수 조회
	 * @param param GCLAS_CD(메인) GRP_SN(커뮤니티)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionJoinInquire.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, String> missionJoinInquire(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		String groupSn = StringUtil.nvl(String.valueOf(param.get("GRP_SN")));
		List<Map<String,String>> grpIter = StringUtil.makeStringToIterator(groupSn);
		Map<String, String> rsMap = null;
		if(grpIter.size() > 0){
			param.put("grpIter", grpIter);
			rsMap = gnrlMissionMngtService.getMissionJoinInquire(param);
		}
		return rsMap;
		
	}

	/**
	 * 미션설정관리 미션설정 insert
	 * @param param 미션코드(순번조회)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/insertMission.do", method= RequestMethod.POST)
	public void insertMission(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req) throws Exception {
		
		param.put("BGN_DE", param.get("BGN_DE").toString().replace("-", ""));
		param.put("END_DE", param.get("END_DE").toString().replace("-", ""));
		param.put("BOARD_SN", param.get("BOARD_SN"));
		int missionSN = gnrlMissionMngtService.getMissionInquireSN(param);
		param.put("SN", missionSN);
		
		if("12".equals(param.get("POST_CLF"))){
			//커뮤니티 인 경우
			int selBoardSN = gnrlMissionMngtService.getSelBoardSn(param);
			param.put("BOARD_SN", selBoardSN);
			gnrlMissionMngtService.insertBoardGrp(param);
			gnrlMissionMngtService.insertCmntyBoard(param);
		}
		
		gnrlMissionMngtService.insertMission(param);		
		gnrlMissionMngtService.insertMissionGrp(param);

		
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
//		return "redirect:../gn/missionMngt.do";
		
	}
	
	/**
	 * 미션설정관리 미션설정 update
	 * @param param 미션코드(순번조회)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/updateMission.do", method= RequestMethod.POST)
	public void updateMission(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req) throws Exception {
		param.put("BGN_DE", param.get("BGN_DE").toString().replace("-", ""));
		param.put("END_DE", param.get("END_DE").toString().replace("-", ""));
		param.put("BOARD_SN", param.get("BOARD_SN"));
		param.put("MISSION_CD", param.get("MISSION_CD"));
		
		if("12".equals(param.get("POST_CLF"))){
//		커뮤니티 인 경우
			gnrlMissionMngtService.deletePostGrp(param);
			gnrlMissionMngtService.insertBoardGrp(param);
			gnrlMissionMngtService.updateCmntyBoard(param);
			gnrlMissionMngtService.updateMissionScore(param);
			gnrlMissionMngtService.updatePostYN(param);
		}
		
		gnrlMissionMngtService.deleteMissionGrp(param);
		gnrlMissionMngtService.insertMissionGrp(param);
		gnrlMissionMngtService.updateMission(param);	

		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
//		return "web/gn/missionMngt";
		
	}

	/**
	 * 신규 건강정보 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gn/insertMissionScore.do", method = RequestMethod.POST)
	public String insertMissionScore(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlMissionMngtService.insertMissionScore(param);
		return "web/gn/missionMngtCmnty";
	}
	
	/**
	 * 푸시 메시지를 전송했는지 확인하고 미전송시 전송
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	private void chkPushSnd(HttpServletRequest req, Map<String, Object> param){
		
		System.out.println("GnrlMissionMngtController / chkPushSnd param ====> " + param);
		
		try{
			// 2. 해당 게시물 푸시가 전송 되었는지 확인.
			Map<String,String> pushSnd = pushService.getPushSndYn(param);
			param.put("boardSn", param.get("BOARD_SN"));
			
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
			}else{
				// 게시 전 게시물 게시 취소 시 예약 발송 푸시 내역 삭제
				if(!"Y".equals(param.get("POST_YN")) && "20".equals(param.get("reqClf"))){
					param.put("sndOrgCd", param.get("SESS_ORG_CD"));
					Map<String, Object> selPushMas = (Map<String, Object>)pushService.selectPushMas(param);
					if(selPushMas != null){
						param.put("SND_SN", selPushMas.get("SND_SN"));
						pushService.deletePushMasHist(param);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 미션설정관리 점수 등록 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gn/missionScorePop.do", method= RequestMethod.GET)
	public String visitCnslDeUpdPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/gn/missionScorePop";
    }	
	
	/**
	 * 미션코드관리  팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gn/missionMngtCodePop.do", method= RequestMethod.GET)
	public String missionMngtCodePop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/gn/missionMngtCodePop";
    }	
	
}
