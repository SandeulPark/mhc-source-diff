package kr.go.mhc.mhcweb.sm.controller;

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
import kr.go.mhc.mhcweb.sm.service.MngterRegMngtService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Class Name : MngterRegMngtController.java
 * @Description : 관리자 WEB에서 사용하는 관리자 정보를 등록 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2017.03.15		이태석		최초생성
 * @author theJoin
 * @since 2017.03.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class MngterRegMngtController extends DMultiActionController{
	
	public static final int BUFF_SIZE = 2048;
	@Resource(name = "web.sm.MngterRegMngtService")
	private MngterRegMngtService mngterRegMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 관리자 등록 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mngterRegMngt.do")
	public String mngterRegMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/sm/mngterRegMngt";
	}
	
	/**
	 * 등록된 관리자 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getRegMngterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRegMngterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = mngterRegMngtService.getRegMngterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 관리자 등록 및 수정
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/saveMngter.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveMngter(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.saveMngter(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 *  관리자 중복 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/managerDuplicationCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getManagerDuplicationCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = mngterRegMngtService.getManagerDuplicationCnt(param);
						
		return rsMap;
	}
	
	/**
	 *  관리자 중복 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/managerDuplicationCnt2.do", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getManagerDuplicationCnt2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = mngterRegMngtService.getManagerDuplicationCnt(param);
		return rsMap;
	}
	
	/**
	 * 사업 담당자 정보보안각서 출력
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mngterRegitPrint.do", method = RequestMethod.GET)
	public String preTrgterRegitPrint(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		return "web/sm/mngterRegitPrint";
	}
	
	/**
	 * 관리자 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mngterRegMngtPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/sm/mngterRegMngtPop";
	}
	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updateApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 공인인증서1 제한 해제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatedn1Use.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatedn1Use(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updatedn1Use(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 공인인증서2 제한 해제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatedn2Use.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatedn2Use(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updatedn2Use(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 등록 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mngterMenuAuth.do")
	public String menuAuth(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {				
 		return "web/sm/mngterMenuAuth";
	}
	
	/**
	 * 관리자 등록 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mngterRegMenuAuth.do")
	public String mngterRegMenuAuth(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {				
 		return "web/sm/mngterRegMenuAuth";
	}
	
	/**
	 * 권한 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/permissionsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> permissionsList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = mngterRegMngtService.permissionsList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}
	
	/**
	 * 권한 관리자 목록
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userAuthList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> authMngterList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = mngterRegMngtService.userAuthList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}
	
	/**
	 * 유저권한 삭제  
	 * @param param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userAuthDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> userAuthDelete(@ModelAttribute Map<String, Object> param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String data = (String) param.get("USER_ID");
		String [] users = data.split(",");
		
		for(int i=0; i<users.length; i++) {
			param.put("USER_ID", users[i]);
			mngterRegMngtService.userAuthDelete(param);
		}
				
		
		
				
	
		return resultMap;
	}
	
	/**
	 * 권한 목록 상세 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/permissionsTrgterList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> permissionsTrgterList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {			
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = mngterRegMngtService.permissionsTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}
	
	/**
	 * 메뉴 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/perMenuList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> perMenuList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {			
		Map<String, Object> rsMap = new HashMap<String, Object>();
		param.put("SESS_USER_ID", param.get("SESS_USER_ID"));

		List<Map<String, String>> rsList = mngterRegMngtService.perMenuList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}
	
	
	/**
	 * 권한 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/perInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> perInsert(@ModelAttribute Map<String, Object> param, ModelMap model,String jsonData) throws Exception {
		
		
		String insertUpdClf = (String) param.get("insertUpdClf");
		param.put("loginId", (String) param.get("loginId"));
		
		if(insertUpdClf==null) {
			
		String datSet = jsonData.replaceAll("&quot;", "\"");     //UPDATE 데이터 처리		
		JSONArray array = JSONArray.fromObject(datSet);
		
		//List<Map<String, String>> rsList = mngterRegMngtService.selectPerMenuList(param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		for(int i=0; i<array.size(); i++){
			
	        JSONObject obj = (JSONObject)array.get(i);
	        resultMap.put("OPR_GRP_NM", obj.get("OPR_GRP_NM"));
	        resultMap.put("OPR_GRP_DC", obj.get("OPR_GRP_DC"));
	        resultMap.put("OPR_GRP_CD", obj.get("OPR_GRP_CD"));
	        resultMap.put("MENU_CD", obj.get("MENU_CD"));
	        resultMap.put("AUTH_CR", obj.get("AUTH_CR"));
	        resultMap.put("AUTH_RE", obj.get("AUTH_RE"));
	        resultMap.put("AUTH_UP", obj.get("AUTH_UP"));
	        resultMap.put("AUTH_DE", obj.get("AUTH_DE"));
	        resultMap.put("AUTH_DN", obj.get("AUTH_DN"));	            			
	        mngterRegMngtService.authListUpdate(resultMap);
	        
	    }
			resultMap.put("SESS_USER_ID",param.get("SESS_USER_ID"));
			mngterRegMngtService.authListUpdate2(resultMap);//권한목록 수정
			
		}else if(insertUpdClf.equals("insert")) {//등록시
			param.put("OPR_GRP_NM", param.get("OPR_GRP_NM"));
			param.put("OPR_GRP_DC", param.get("OPR_GRP_DC"));
			
			Map<String, Object> param2 = new HashMap<String, Object>();
			
			List<Map<String, String>> rsList = mngterRegMngtService.selectPerMenuList(param);
			
			mngterRegMngtService.authListInsert(param);//권한목록 등록
			
			List<Map<String, String>> rsList2 = mngterRegMngtService.selectAuthKey(param);//등록한 권한 키조회
			
			param2.put("OPR_GRP_CD", rsList2.get(0).get("OPR_GRP_CD"));//KEY 파라미터
			param2.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			for(int i=0; i<rsList.size(); i++) {//메뉴 등록
				param2.put("MENU_CD", rsList.get(i).get("MENU_CD"));				
				mngterRegMngtService.perMenuGInsert(param2);
			}
			
			Map<String, Object> rsMap = new HashMap<String, Object>();		
			
			rsMap.put("rsList", rsList);
			rsMap.put("id", param.get("id"));
			return rsMap;
		}
			
		
		
				
		return model;
	}
	
	/**
	 * 권한 삭제  
	 * @param param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/authDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> authDelete(@ModelAttribute Map<String, Object> param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		param.put("authYn","Y");
		mngterRegMngtService.authDelete(param);
		mngterRegMngtService.authList(param);
		mngterRegMngtService.userAuthDelete(param);
	
		return resultMap;
	}
	
	/**
	 * 보건소목록 및 권한 목록 팝업 호출
	 */
	@RequestMapping(value= "/orgAuthListPop.do", method = RequestMethod.GET)
	public String orgAuthListPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sm/regOrgAuthListPop";
	}
	
	/**
	 * 보건소 목록 및 권한 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/orgAuthList.do")
	public @ResponseBody Map<String, Object> orgAuthList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String,Object>> rsList2 = mngterRegMngtService.checkRegUser(param);													
		List<Map<String,Object>> rsList = mngterRegMngtService.getOrgAuthList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("rsList2", rsList2);

		return rsMap;
	}
	
	/**
	 * 관리자 권한 등록
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orgAuthInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> orgAuthInsert(@ModelAttribute Map<String, Object> param, ModelMap model,String jsonData) throws Exception {
		
		String usersData = (String) param.get("usersData");			
		String datSet = usersData.replaceAll("&quot;", "\"");     //UPDATE 데이터 처리		
		JSONArray array = JSONArray.fromObject(datSet);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		  for(int i=0; i<array.size(); i++){		  
			JSONObject obj = (JSONObject)array.get(i);
			resultMap.put("LOGIN_ID",obj.get("LOGIN_ID"));//유저 ID
			resultMap.put("USER_ID", obj.get("USER_ID"));
	        resultMap.put("ORG_NM", obj.get("ORG_NM"));
	        resultMap.put("USER_NM", obj.get("USER_NM"));
	        resultMap.put("OPR_GRP_CD", obj.get("OPR_GRP_CD"));
	        resultMap.put("JOB_NM", obj.get("JOB_NM"));
	        	            			
	        mngterRegMngtService.orgAuthUsersInsert(resultMap);
		  
		  }
				
		return model;
	}
	
	/**
	 * 오류리포트 탭메뉴
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/errorReport.do")
	public String noticeMngtDel(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/sm/errorReport";
	}
	
	/**
	 * 오류리포트  list
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/slectErrReport.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> slectErrReport( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String,Object>> rsList = mngterRegMngtService.slectErrReport(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		return rsMap;
	}
	

}
