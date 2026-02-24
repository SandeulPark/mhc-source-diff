package kr.or.khealth.smhc.smhcapp.sv.controller;

import java.util.ArrayList;
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

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcapp.sv.service.CommunityService;

/**
 * @Class Name : CommunityController.java
 * @Description : APP 커뮤니티  컨트롤러
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2020.11.04		김태일	       최초생성
 *
 * @author  theJOIN
 * @since   2020.11.04
 * @version 1.0
 * @see
 * 
 */


@Controller
@RequestMapping(value = "/smhcapp/sv")
public class CommunityController extends DMultiActionController {
	@Resource(name = "smhcapp.sv.CommunityService")
	private CommunityService communityService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 조건에 맞는 커뮤니티 게시글 조회
	 * @param USER_ID(필수), SVC_NO(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectCommunityList.do")
	public @ResponseBody Map<String, Object> selectCommunityList(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			
			int page = Integer.parseInt(String.valueOf(param.get("page")));
			System.out.println("selectCommunityList.do / page ===> " + page);
			
			if(page == 1) {
				param.put("START_NUM", 1);
				param.put("END_NUM", 3);
			}else {
				param.put("START_NUM", page+(2*(page-1)));
				param.put("END_NUM", page*3);
			}		
			List<Map<String, Object>> communityList = communityService.selectCommunityList(param);			
			//조회 수 1 증가
			if(communityList.size() > 0) {
				param.put("communityList", communityList);
				communityService.updateRdCnt(param);
			}
			
			rsMap.put("communityList", communityList);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}
	
	/**
	 * 커뮤니티 게시글 상세조회  + 댓글 조회
	 * @param ORG_CD(필수), BOARD_SN(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectCommunityDtls.do")
	public @ResponseBody Map<String, Object> selectCommunityDtls(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsCont  = new HashMap<String,Object>();//커뮤니티 콘텐츠	
		List<Map<String,Object>> rsComment  = new ArrayList<Map<String,Object>>();//댓글 목록	
		try {
			rsCont = communityService.selectCommunityCont(param);
			rsComment = communityService.selectCommunityCmmnt(param);
			
			rsMap.put("rsCont", rsCont);
			rsMap.put("rsComment", rsComment);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 댓글 등록 + 댓글 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/insertComment.do")
	public @ResponseBody Map<String, Object> insertComment(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsComment  = new ArrayList<Map<String,Object>>();
		try {
			int rsInt  = 0;
			
			rsInt += communityService.insertComment(param);
			rsComment = communityService.selectCommunityCmmnt(param);
			rsMap.put("rsComment", rsComment);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 커뮤니티 댓글 불러오기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectCommunityCmmnt.do")
	public @ResponseBody Map<String, Object> selectCommunityCmmnt(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsComment  = new ArrayList<Map<String,Object>>();
		try {
			rsComment = communityService.selectCommunityCmmnt(param);
			rsMap.put("rsComment", rsComment);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 커뮤니티 댓글 좋아요 + 특정 댓글 조회 (CMMNT_SN 필요)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mergeCmmntLike.do")
	public @ResponseBody Map<String, Object> mergeCmmntLike(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsComment  = new ArrayList<Map<String,Object>>();
		try {
			int rsInt = 0; 
			rsInt = communityService.mergeCmmntLike(param);
			rsComment = communityService.selectCommunityCmmnt(param);
			rsMap.put("rsComment", rsComment);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 댓글 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateCmmnt.do")
	public @ResponseBody Map<String, Object> updateCmmnt(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = 0;
		try {
			rsInt += communityService.updateCmmnt(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	
	/**
	 * 게시글 좋아요 및 게시글 확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/checkCommunityInfo.do")
	public @ResponseBody Map<String, Object> checkCommunityInfo(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		try {
			int rsInt = 0;
			int rdInt = 0;
			Map<String, Object> sttusMap = new HashMap<String,Object>();
			rsInt += communityService.checkCommunityInfo(param);
			if(param.containsKey("paramJob")){
				if(param.get("paramJob").toString().equals("likeYn")){
					sttusMap = communityService.getCommunitySttus(param);
					rsMap.put("communitySttus", sttusMap);
				//사용안함	
				//}else if(param.get("paramJob").toString().equals("info_Cnfm")){
				//	rdInt = communityService.updateRdCnt(param);
				}
			}
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 댓글 신고 기능
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/commentRptSubmit.do")
	public @ResponseBody Map<String, Object> commentRptSubmit(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		try {
			communityService.commentRptSubmit(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
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
			communityService.userBlockSubmit(param); 			
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();			
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
}
