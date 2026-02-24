package kr.go.mhc.mhcapp.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlGroupMngtService;

/**
 * @Class Name : GnrlGroupMngtController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 그룹관리를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	     수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.7		이태석			최초생성
 * 		
 *
 * @author thejoin
 * @since 2019.10.7
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/gn")
public class GnrlGroupMngtController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.gn.GnrlGroupMngtService")
	private GnrlGroupMngtService gnrlGroupMngtService;

	@ModelAttribute
	public Map<String, Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 그룹 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGroupList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectGroupList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			List<Map<String,String>> rsList = gnrlGroupMngtService.selectGroupList(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}
	
	/**
	 * 그룹 참여 신청
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/insertJoinGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object>  insertVisitReservationInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		/**
		 * 그룹참여전에 이미 가입되어있는지 확인하고 가입이 안되어있을경우에만 처리 (최대길 : 2020-10-08)
		 * params : {CMNTY_CD:joinCmntyCd, GRP_SN:joinGrpSn}
		 */
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			System.out.println("### GRP_SN : " + param.get("GRP_SN"));
			System.out.println("### IDENTIFY_NUMBER : " + param.get("IDENTIFY_NUMBER"));
			System.out.println("### insertJoinGroup init");
			
			String cmntyCd = gnrlGroupMngtService.isExistGroup(param);
			
			if(StringUtils.isEmpty(cmntyCd)) {	// 가입한 그룹이 없다
				System.out.println("### cmntyCd isEmpty");
				String grpIdnfr = gnrlGroupMngtService.isExistGrpIdnfr(param);
				
				if(StringUtils.isEmpty(grpIdnfr)) {	// 중복 등록된 소속식별번호가 없다	
					System.out.println("### grpIdnfr isEmpty");
					gnrlGroupMngtService.insertJoinGroup(param); 
					gnrlGroupMngtService.insertGrpIndfr(param);
					rsMap.put("exist", "N");
					rsMap.put("grpIdnfrExist", "N");
				} else {
					System.out.println("### grpIdnfr isNotEmpty");
					rsMap.put("grpIdnfrExist", "Y");
				}
			} else {
				System.out.println("### cmntyCd isNotEmpty");
				 // 가입한 커뮤니티코드와 파라미터의 커뮤니티코드를 비교
				if(!param.get("CMNTY_CD").equals(cmntyCd)) {	// 가입한 그룹이 있는데 다른 그룹을 가입하려 한다
					rsMap.put("exist", "Y");
					System.out.println("### param.get(\"CMNTY_CD\") == cmntyCd");
				} else {	// 동일한 커뮤니티의 세부그룹에 가입하려 한다.
					System.out.println("### param.get(\"CMNTY_CD\") != cmntyCd");
					String grpSn = gnrlGroupMngtService.isExistGrpSn(param);
					
					if(StringUtils.isEmpty(grpSn)) {	// 가입한 세부그룹이 없다.
						System.out.println("### no sub cmntyCd");
						gnrlGroupMngtService.insertJoinGroup(param); 
						//gnrlGroupMngtService.insertGrpIndfr(param);
						rsMap.put("exist", "N");
					} else {
						rsMap.put("exist", "Y");		
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;		
		
	}	
	
	/**
	 * 참여 그룹 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectJoinGroupList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectJoinGroupList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			List<Map<String,String>> rsList = gnrlGroupMngtService.selectJoinGroupList(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}
	
	/**
	 * 참여 그룹 탈퇴
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteJoinGroup.do", method = RequestMethod.POST)
	public void deleteJoinGroup(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		gnrlGroupMngtService.deleteJoinGroup(param);
		gnrlGroupMngtService.deleteGrpIdnfr(param);
	}

	/**
	 * 보편 기관 개인정보 제3자 동의서 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectOrgPrivacyAgree.do", method= RequestMethod.POST)
	public Map<String,Object> selectOrgAgreeCont(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String,String>> rsList = gnrlGroupMngtService.selectOrgPrivacyAgree(param);
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}

}
