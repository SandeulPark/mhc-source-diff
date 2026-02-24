package kr.go.mhc.mhcapp.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlRightPanelService;

/**
 * @Class Name : GnrlRightPanelController.java
 * @Description : 보편건강 App에서 사용하는 우측메뉴를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.08.30		이태석			최초생성
 * 		
 *
 * @author thejoin
 * @since 2019.08.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/gn")
public class GnrlRightPanelController extends DMultiActionController{ 

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@Resource(name="mhcapp.gn.GnrlRightPanelService")
	private GnrlRightPanelService gnrlRightPanelService;
	
	/**
	 * 개인정보 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/updatePrivacyInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updatePrivacyInfo(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		gnrlRightPanelService.updatePrivacyInfo(param);
		
		session.setAttribute("SESS_USER_NM", param.get("USER_NM"));
		session.setAttribute("SESS_BIRTH"  , param.get("BIRTH"));
		session.setAttribute("SESS_GENDER" , param.get("GENDER"));
		session.setAttribute("SESS_HEIGHT" , param.get("HEIGHT"));
		session.setAttribute("SESS_WEIGHT" , param.get("WEIGHT"));
		
		rsMap.put("SESS_USER_NM", param.get("USER_NM"));
		rsMap.put("SESS_BIRTH"	, param.get("BIRTH"));
		rsMap.put("SESS_GENDER"	, param.get("GENDER"));
		rsMap.put("SESS_HEIGHT"	, param.get("HEIGHT"));
		rsMap.put("SESS_WEIGHT"	, param.get("WEIGHT"));
		
		return rsMap;
	}	
	
	/**
	 * 목표설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectObjSet.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectObjSet(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			rsMap = gnrlRightPanelService.selectObjSet(param);   
						
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 목표설정 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateObjSet.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateObjSet(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String chkYn = "N";
		try{
			gnrlRightPanelService.updateObjSet(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
				
		return rsMap;
	}	
	
	/**
	 * 알림내역 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/notificationList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNotificationList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = gnrlRightPanelService.selectNotificationList(param);
		
		gnrlRightPanelService.updateNotification(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put(MESSAGE_NAME, getMsg("common.list.succ"));
		return rsMap;
	}
	
	/**
	 * 알림내역 삭제
	 */
	@RequestMapping( value="/notificationDel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> notificationDel(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = gnrlRightPanelService.notificationDel(param);
		rsMap.put("rsList", rsCount);
		return rsMap;
	}
	
	/**
	 * 비밀번호 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myInfoPwdUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> myInfoPwdUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		if("Y".equals(param.get("keySec"))){
			decryptMap(param,"PRE_PW");
			decryptMap(param,"PW");			
		}
		int rsCount = gnrlRightPanelService.myInfoPwdUpdate(param);   
		rsMap.put("rsList", rsCount);
		return rsMap;
	}	
	
	/**
	 * 비밀번호 수정 전 정보 조회 (참, 거짓 판별)
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myInfoPwChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectChkMyInfo(@ModelAttribute Map<String, String> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> result = new HashMap<String,Object>();
		
		if("Y".equals(param.get("keySec"))){
			decryptMap(param,"PRE_PW");
			decryptMap(param,"CUR_PW");
			decryptMap(param,"CHK_CUR_PW");
		}
		
		List<Map<String,String>> rsList = gnrlRightPanelService.selectChkMyInfo(param);   
		String prePw = (String) param.get("PRE_PW");
		String curPw = (String) param.get("CUR_PW");
		String chkCurPw = (String) param.get("CHK_CUR_PW");
		String msg = "";
		String rsCd = "";
		String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,12}$";		                   
		String pwPattern2 = "(.)\\1\\1\\1";
		
		Matcher matcher = Pattern.compile(pwPattern).matcher(curPw);
		
		Matcher matcher2 = Pattern.compile(pwPattern2).matcher(curPw);
		
		if(rsList.size() > 0){
			if(prePw.equals(curPw)){
				msg = "같은 비밀번호는 사용할 수 없습니다.";
				rsCd = "02";
			}else if(!curPw.equals(chkCurPw)){
				msg = "새 비밀번호, 비밀번호 확인 항목이 다릅니다.";
			    rsCd = "03";
			}else if(!matcher.matches()){
				msg = "숫자와 영문자 특수문자 조합으로 8~12자리를 사용해야 합니다.";
				rsCd = "04";
			}else if(matcher2.find()){
			    msg = "비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.";
			    rsCd = "05";
			}else if(curPw.contains(" ")){
				msg = "비밀번호에 공백을 넣을 수 없습니다.";
			    rsCd = "06";
			}
		}else{
			msg = "비밀번호를 확인하세요.";
			rsCd = "01";
		}
		
		result.put("msg", msg);
		result.put("rsCd", rsCd);
		
		rsMap.put("rsList", result);
		return rsMap;
	}	
	
	/**
	 * 새로온 게시물 갯수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectNewCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNewCnt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,String> rsList = gnrlRightPanelService.selectNewCnt(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateSetting.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSetting(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		gnrlRightPanelService.updateSetting(param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String tagNm = ((String) param.get("RDO_TAG_CLF")).toUpperCase();
		if("BODYACT_AUTOUPD".endsWith(tagNm)){
			tagNm = "SESS_BODYACT_AUTOUPD_YN";
		}else{
			tagNm = "SESS_" + tagNm + "_SET_YN";
		}
		session.setAttribute(tagNm, param.get("RDO_SET_YN"));
		
		rsMap.put("RDO_TAG_CLF", param.get("RDO_TAG_CLF"));
		rsMap.put("RDO_SET_YN", param.get("RDO_SET_YN"));
		return rsMap;
	}
	
	/**
	 * 개인 프로필 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateThumbnail.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateThumbnail(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		gnrlRightPanelService.updateThumbnail(param);
		
		session.setAttribute("SESS_THUMB_ATTCH_FILE_SN"	, 	param.get("ATTCH_FILE_SN").toString());
		session.setAttribute("SESS_THUMB_LOCAL_FILE_NM"	, 	param.get("LOCAL_FILE_NM").toString());
		session.setAttribute("SESS_THUMB_SVR_FILE_NM"	, 	param.get("SVR_FILE_NM").toString());
		session.setAttribute("SESS_THUMB_SVR_FILE_PATH"	, 	param.get("SVR_FILE_PATH").toString());
		
		return rsMap;
	}

	/**
	 * 자체활동측정여부
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/updateActselfmeasr.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateActselfmeasr(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		gnrlRightPanelService.updateActselfmeasr(param);

		return rsMap;
	}
	
	/**
	 * 기기 정보 초기화
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping( value="/resetEquipInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> resetEquipInfo(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		gnrlRightPanelService.resetEquipInfo(param);

		return rsMap;
	}
	
	/**
	 * 활동량계 정보 유무 확인
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectExistActEquipInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectExistActEquipInfo(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap = gnrlRightPanelService.selectExistActEquipInfo(param);

		return rsMap;
	}

}
