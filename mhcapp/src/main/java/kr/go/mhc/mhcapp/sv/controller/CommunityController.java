package kr.go.mhc.mhcapp.sv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.CommunityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : CommunityController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 커뮤니티를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.08		허광일			최초생성
 * 		
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class CommunityController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.CommunityService")
	private CommunityService communityService;

	@ModelAttribute
	public Map<String, Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * 커뮤니티 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityMain.do", method = RequestMethod.GET)
	public String communityMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		return "app/sv/cmnty/communityMain";
	}	
	
	/**
	 * 커뮤니티 상세 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityDetail.do", method = RequestMethod.GET)
	public String communityDetail(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/cmnty/communityDtls";
	}	
	
	/**
	 * 커뮤니티 공지 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityNotice.do", method = RequestMethod.GET)
	public String communityNotice(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "app/sv/cmnty/communityNotice";
	}	
	
	/**
	 * 커뮤니티 카카오 연동 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/kakao/communityKakao.do", method = RequestMethod.GET)
	public String communityKakao(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/cmnty/communityKakao";
	}	

	/**
	 * 커뮤니티 전체 게시물 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityTotalContent.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityTotalContent(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		List<Map<String,String>> attList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsListFile = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsName = communityService.selectCmnty(param); 
			List<Map<String,String>> rsList = communityService.selectContent(param);
			
//			for(int i = 0; i < rsList.size(); i++){
//				Map<String,String> attMap = new HashMap<String,String>();
//				String attchFileSn = rsList.get(i).get("ATTCH_FILE_SN")==null?"":(String)rsList.get(i).get("ATTCH_FILE_SN");
//				if(!"".equals(attchFileSn)){
//					attMap.put("ATTCH_FILE_SN", rsList.get(i).get("ATTCH_FILE_SN"));
//					attList.add(attMap);
//				}
//			}
//			if(attList.size() > 0){
//				param.put("ATTCH_LIST", attList);
//				rsListFile = communityService.selectCheckAddFiles(param); 
//			}
			
			rsMapTmp.put("rsName", rsName);
			rsMapTmp.put("rsList", rsList);
//			rsMapTmp.put("rsListFile", rsListFile);
			
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 커뮤니티 상세 게시물 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityDetailContent.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityDetailContent(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.checkingList(param);
			communityService.updateCont(param);
						
			List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
			if(!param.get("START_NUM").toString().equals("0")){
				List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
				List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param);
				List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
				
				rsMapTmp.put("rsListCmnt", rsListCmnt);
				rsMapTmp.put("rsListFile", rsListFile);
				rsMapTmp.put("rsCmntFile", rsCmntFile);
			}
			
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.putAll(param);
			
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 커뮤니티 상세 게시물 불러오기 (카카오톡)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/kakao/communityDetailContentForKakao.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityDetailContentForKakao(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			param.put("SESS_USER_ID", param.get("SHARE_ID"));
			communityService.updateSharedCnfmCnt(param);
			
			List<Map<String,String>> rsList = communityService.selectDetailContentForKakao(param); 
			List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param); 
			
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.put("rsListFile", rsListFile);
			rsMapTmp.putAll(param);
			
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	

	/**
	 * 커뮤니티 상세 게시물의 댓글불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityDetailComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityDetailComment(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
					
		try{
			List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
			List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
			
			rsMapTmp.put("rsListCmnt", rsListCmnt);
			rsMapTmp.put("rsCmntFile", rsCmntFile);
			rsMapTmp.putAll(param);
			
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	/**
	 * 커뮤니티 상세 게시물의 댓글삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	
	@RequestMapping( value="/updateDetailComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateDetailComment(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.updateDetailComment(param);
	
			List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
			
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.put("rsListCmnt", rsListCmnt);
			rsMapTmp.put("rsListFile", rsListFile);
			rsMapTmp.put("rsCmntFile", rsCmntFile);
			rsMapTmp.putAll(param);	
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
			
	/**
	 * 커뮤니티 첨부파일 확인 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityCheckAddFiles.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityCheckAddFiles(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList = communityService.selectCheckAddFiles(param); 
			rsMap.put("rsList", rsList);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 커뮤니티 글 확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityCnfm.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityCnfm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.checkingList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 커뮤니티 좋아요 count add ,  Y/N update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityGood.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityGood(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.checkingList(param);
			communityService.updateGood(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 스크랩 on off
	 * @param communityGood
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityClipping.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityClipping(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.checkingList(param);
			communityService.updateClip(param);
			
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 공유 여부 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateSharedCont.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSharedCont(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			communityService.updateSharedCont(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 댓글 입력 및 댓글 첨부파일 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityAddComment.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityAddComment(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.insertComment(param);
			
			List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
	
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.put("rsListCmnt", rsListCmnt);
			rsMapTmp.put("rsListFile", rsListFile);
			rsMapTmp.put("rsCmntFile", rsCmntFile);
			rsMapTmp.putAll(param);
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 메인 컨텐츠 팝업 유무
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertMainPopYn.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertMainPopYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.insertMainPopYn(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 대상자 게시글 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertTrgterBoard.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertTrgterBoard(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.insertTrgterBoard(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 댓글 입력 및 댓글 첨부파일 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectTrgterBoardConts.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterBoardConts(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList = communityService.selectTrgterBoardConts(param); 
	
			rsMap.put("rsList", rsList);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 댓글 신고
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	
	@RequestMapping( value="/commentRptSubmit.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> commentRptSubmit(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			communityService.commentRptSubmit(param); 
	
			List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
			
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.put("rsListCmnt", rsListCmnt);
			rsMapTmp.put("rsListFile", rsListFile);
			rsMapTmp.put("rsCmntFile", rsCmntFile);
			rsMapTmp.putAll(param);	
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	
	/**
	 * 사용자 차단
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	
	@RequestMapping( value="/userBlockSubmit.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> userBlockSubmit(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			String userId = String.valueOf(param.get("USER_ID"));
			String blockUserId = String.valueOf(param.get("BLOCK_USER_ID"));
			
			if(userId != blockUserId) communityService.userBlockSubmit(param); 
	
			List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
			
			rsMapTmp.put("rsList", rsList);
			rsMapTmp.put("rsListCmnt", rsListCmnt);
			rsMapTmp.put("rsListFile", rsListFile);
			rsMapTmp.put("rsCmntFile", rsCmntFile);
			rsMapTmp.putAll(param);	
			rsMap.put("rsList", rsMapTmp);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	
}
