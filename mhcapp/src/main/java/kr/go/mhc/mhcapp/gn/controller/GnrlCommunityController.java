package kr.go.mhc.mhcapp.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlCommunityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value="/gn/cmmnty")
public class GnrlCommunityController extends DMultiActionController{
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@Resource(name="gn.gnrlCommunityService")
	private GnrlCommunityService gnrlCommunityService;
	
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
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsName = gnrlCommunityService.selectCmnty(param);
			List<Map<String,String>> rsList = gnrlCommunityService.selectContent(param);
						
			rsMapTmp.put("rsName", rsName);
			rsMapTmp.put("rsList", rsList);
			
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
			gnrlCommunityService.checkingList(param);
			gnrlCommunityService.updateCont(param);
						
			List<Map<String,String>> rsList = gnrlCommunityService.selectDetailContent(param); 
			if(!param.get("START_NUM").toString().equals("0")){
				List<Map<String,String>> rsListCmnt = gnrlCommunityService.selectDetailComment(param);
				List<Map<String,String>> rsListFile = gnrlCommunityService.selectCheckAddFilesForKakao(param);
				List<Map<String,String>> rsCmntFile = gnrlCommunityService.selectCmntAddFiles(param); 
				
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
			gnrlCommunityService.updateSharedCnfmCnt(param);
			
			List<Map<String,String>> rsList = gnrlCommunityService.selectDetailContentForKakao(param); 
			List<Map<String,String>> rsListFile = gnrlCommunityService.selectCheckAddFilesForKakao(param); 
			
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
			List<Map<String,String>> rsListCmnt = gnrlCommunityService.selectDetailComment(param);
			List<Map<String,String>> rsCmntFile = gnrlCommunityService.selectCmntAddFiles(param); 
			
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
			gnrlCommunityService.updateDetailComment(param);
	
			List<Map<String,String>> rsList = gnrlCommunityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = gnrlCommunityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = gnrlCommunityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = gnrlCommunityService.selectCmntAddFiles(param); 
			
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
			List<Map<String,String>> rsList = gnrlCommunityService.selectCheckAddFiles(param); 
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
			gnrlCommunityService.checkingList(param);
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
			gnrlCommunityService.checkingList(param);
			gnrlCommunityService.updateGood(param);
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
			gnrlCommunityService.checkingList(param);
			gnrlCommunityService.updateClip(param);
			
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
			gnrlCommunityService.updateSharedCont(param);
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
			gnrlCommunityService.insertComment(param);
			
			List<Map<String,String>> rsList = gnrlCommunityService.selectDetailContent(param); 
			List<Map<String,String>> rsListCmnt = gnrlCommunityService.selectDetailComment(param);
			List<Map<String,String>> rsListFile = gnrlCommunityService.selectCheckAddFilesForKakao(param);
			List<Map<String,String>> rsCmntFile = gnrlCommunityService.selectCmntAddFiles(param); 
	
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
			gnrlCommunityService.insertMainPopYn(param);
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
			gnrlCommunityService.insertTrgterBoard(param);
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
			List<Map<String,String>> rsList = gnrlCommunityService.selectTrgterBoardConts(param); 
	
			rsMap.put("rsList", rsList);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}		
}
