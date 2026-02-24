package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.CommunityService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


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
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * 커뮤니티 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityMain.do", method = RequestMethod.GET)
	public String communityMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/cmnty/communityMain";
	}	
	
	/**
	 * 커뮤니티 상세 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityDetail.do", method = RequestMethod.GET)
	public String communityDetail(@ModelAttribute Map param, ModelMap model) throws Exception{
//		String BOARD_SN = (String) param.get("BOARD_SN");
//		model.addAttribute("BOARD_SN",BOARD_SN);
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
	public String communityNotice(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/cmnty/communityNotice";
	}	
	
	/**
	 * 커뮤니티 카카오 연동 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/kakao/communityKakao.do", method = RequestMethod.GET)
	public String communityKakao(@ModelAttribute Map param, ModelMap model) throws Exception{
//		String BOARD_SN = (String) param.get("BOARD_SN");
//		model.addAttribute("BOARD_SN",BOARD_SN);
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
		
		List<Map<String,String>> rsName = communityService.selectCmnty(param); 
		List<Map<String,String>> rsList = communityService.selectContent(param); 
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFiles(param); 
		
		rsMapTmp.put("rsName", rsName);
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListFile", rsListFile);
		
		rsMap.put("rsList", rsMapTmp);
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		communityService.checkingList(param);
		communityService.updateCont(param);

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		
			
		List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
		List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFiles(param); 
		List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListCmnt", rsListCmnt);
		rsMapTmp.put("rsListFile", rsListFile);
		rsMapTmp.put("rsCmntFile", rsCmntFile);
		rsMapTmp.putAll(param);
		
		rsMap.put("rsList", rsMapTmp);
		
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
			
		List<Map<String,String>> rsList = communityService.selectDetailContentForKakao(param); 
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFilesForKakao(param); 
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListFile", rsListFile);
		rsMapTmp.putAll(param);
		
		rsMap.put("rsList", rsMapTmp);
		
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}
	

	/**
	 * 커뮤니티 상세 게시물의 댓글불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityDetailComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityDetailComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		List<Map<String,String>> rsList = communityService.selectDetailComment(param); 
		rsMap.putAll(param);
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	
	/**
	 * 커뮤니티 상세 게시물의 댓글삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	
	@RequestMapping( value="/updateDetailComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateDetailComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		communityService.updateDetailComment(param);

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		
			
		List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
		List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFiles(param); 
		List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListCmnt", rsListCmnt);
		rsMapTmp.put("rsListFile", rsListFile);
		rsMapTmp.put("rsCmntFile", rsCmntFile);
		rsMapTmp.putAll(param);
		
		rsMap.put("rsList", rsMapTmp);
		
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}
			
	/**
	 * 커뮤니티 첨부파일 확인 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityCheckAddFiles.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityCheckAddFiles(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		List<Map<String,String>> rsList = communityService.selectCheckAddFiles(param); 
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 커뮤니티 좋아요 count add ,  Y/N update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityGood.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityGood(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		//메인 컨텐츠에서 누를때
		if(param.containsKey("MAIN_CMNTY_CD") && param.containsKey("MAIN_ORG_CD")){
			param.put("SESS_CMNTY_CD", (String)param.get("MAIN_CMNTY_CD"));
			param.put("SESS_ORG_CD", (String)param.get("MAIN_ORG_CD"));
		}
		communityService.checkingList(param);
		communityService.updateGood(param);
//		rsMap.put("msg", getMsg("common.write.succ"));
		
		return rsMap;
	}	
	
	/**
	 * 스크랩 on off
	 * @param communityGood
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityClipping.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityClipping(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		
		
		communityService.checkingList(param);
		communityService.updateClip(param);
		
		List<Map<String,String>> rsList = communityService.selectContent(param); 
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFiles(param); 
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListFile", rsListFile);
		
		rsMap.put("rsList", rsMapTmp);
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}		
		return rsMap;
	}	
	
	/**
	 * 댓글 입력 및 댓글 첨부파일 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityAddComment.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> communityAddComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
		
		communityService.insertComment(param);
		
		List<Map<String,String>> rsList = communityService.selectDetailContent(param); 
		List<Map<String,String>> rsListCmnt = communityService.selectDetailComment(param);
		List<Map<String,String>> rsListFile = communityService.selectCheckAddFiles(param); 
		List<Map<String,String>> rsCmntFile = communityService.selectCmntAddFiles(param); 
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListCmnt", rsListCmnt);
		rsMapTmp.put("rsListFile", rsListFile);
		rsMapTmp.put("rsCmntFile", rsCmntFile);
		rsMapTmp.putAll(param);
		rsMap.put("rsList", rsMapTmp);
		
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 메인 컨텐츠 확인 insert
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/communityCnfmCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> communityCnfmCheck(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		param.put("SESS_CMNTY_CD", (String)param.get("MAIN_CMNTY_CD"));
		communityService.checkingList(param);
		
		return rsMap;
	}
	
}
