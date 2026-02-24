package kr.or.khealth.smhc.smhcapp.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcapp.cm.service.AppCmmnJobService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Class Name : AppCmmnJobController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 공통업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 * @	2016.06.30		오명빈			알림내역 추가
 * @	2016.08.01		허광일			비밀번호 변경 추가
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class AppCmmnJobController extends DMultiActionController{ 
	@Resource(name="mhcapp.cm.AppCmmnJobService")
	private AppCmmnJobService acjService;
	
//	@Resource(name="mhcapp.sv.NoSmokeCounselService")
//	private NoSmokeCounselService noSmokeCounselService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mobileMain.do", method = RequestMethod.GET)
	public String mobileMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
//		List<Map<String,Object>> rsList = testService.getList(param);   
//		model.addAttribute("rsList", rsList);	
		
		return "app/main";
	}	
	
	/**키보드보안 화면 호출
	 * 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/autoLogin.do", method = RequestMethod.GET)
	public String autoLogin(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/cm/autokeyboard";
	}	
	

	/**
	 * 사용설명서 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/manualMain.do", method = RequestMethod.GET)
	public String manualMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/cm/manual";
	}
	
	/**
	 * 사용설명서 상세 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/manualDtls.do", method = RequestMethod.GET)
	public String manualDtls(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		model.addAllAttributes(param);
		return "app/cm/manualDtls";
	}
	
	/**
	 * 사용설명서 상세 파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
//	@RequestMapping( value="/manualDtlsFileList.do", method = RequestMethod.POST)
//	public @ResponseBody Map<String,Object> manualDtlsFileList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
//		
//		Map<String,Object> rsMap = new HashMap<String,Object>();
//		List<Map<String,String>> rsListFile = noSmokeCounselService.selectNoSmokeCounselAddFiles(param); // 첨부
//		rsMap.put("rsList", rsListFile);
//		return rsMap;
//	}	

	/**
	 * 공지사항 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noticeMain.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNotice(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap.put("noticeSN", param.get("noticeSN"));
		return rsMap;
	}	

	/**
	 * 공지사항 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noticeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNoticeList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = acjService.selectNoticeList(param);  
		rsMap.put("rsList", rsList);
		rsMap.put(MESSAGE_NAME, getMsg("common.list.succ"));
		return rsMap;
	}	
	
	/**
	 * 공지사항 확인업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noticeCnfmChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> noticeCnfmChk(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		int rsCount = 0;
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rs = acjService.noticeCnfmChk(param);
		if(rs.size()==0){
			rsCount = acjService.updateNoticeCnfm(param);
		}
		rsMap.put("rsList", rsCount);
		return rsMap;
	}	

	/**
	 * 알림내역 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/notificationMain.do", method = RequestMethod.GET)
	public String notificationMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/cm/notification";
	}	
	
	/**
	 * 내정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myInfoMain.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMyInfoMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap.put("serveyInfo", param.get("serveyInfo"));
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
		/*List<Map<String,String>> rsList = acjService.selectNotificationList(param);   
//		model.addAttribute("rsList", rsList);	
		
		return rsList;*/
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = acjService.selectNotificationList(param);
		
		//20161020 윤봉훈 - 알림 확인유무 Y 값 수정
		acjService.updateNotification(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put(MESSAGE_NAME, getMsg("common.list.succ"));
		return rsMap;
	}	
	
	/**
	 * 개인정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myInfoList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMyInfoList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
	
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = acjService.selectMyInfoList(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 건강설문 실행 여부판단
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myInfoPreServey.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMyInfoServey(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = acjService.selectMyInfoServey(param);   
		rsMap.put("rsList", rsList);
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
		
//		decryptMap(param,"PRE_PW");
//		decryptMap(param,"PW");
		int rsCount = acjService.myInfoPwdUpdate(param);   
		rsMap.put("rsList", rsCount);
		/*if(rsCount>0){
			rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			rsMap.put("msg", getMsg("common.edit.err"));
		}*/
		return rsMap;
	}	
	
	/**
	 * 상담 메뉴 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cnlsList.do", method = RequestMethod.GET)
	public String cnlsList(HttpServletRequest req,@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/mr/generalCnsl";
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
		
//		decryptMap(param,"PRE_PW");
//		decryptMap(param,"CUR_PW");
//		decryptMap(param,"CHK_CUR_PW");
		
		List<Map<String,String>> rsList = acjService.selectChkMyInfo(param);   
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
		/*if(rsCount>0){
			rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			rsMap.put("msg", getMsg("common.edit.err"));
		}*/
		return rsMap;
	}	
	
	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/settingMain.do", method = RequestMethod.GET)
	public String settingMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/cm/setting";
	}	
	
	/**
	 * 기기설정화면 이동
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/msSetting.do", method = RequestMethod.GET)
	public String msSetting(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/cm/msSetting";
	}
	
	/**
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateSetting.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSetting(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		acjService.updateSetting(param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String tagNm = ((String) param.get("RDO_TAG_CLF")).toUpperCase();
		if("BODYACT_AUTOUPD".endsWith(tagNm)){
			tagNm = "SESS_BODYACT_AUTOUPD_YN";
		}else{
			tagNm = "SESS_" + tagNm + "_SET_YN";
		}
		session.setAttribute(tagNm, param.get("RDO_SET_YN"));
//		session.setAttribute("SESS_DASH_SET_ORD_ARR", param.get("DASH_SET_ORD_ARR"));
		
		rsMap.put("RDO_TAG_CLF", param.get("RDO_TAG_CLF"));
		rsMap.put("RDO_SET_YN", param.get("RDO_SET_YN"));
//		rsMap.put("DASH_SET_ORD_ARR", param.get("DASH_SET_ORD_ARR"));
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
		acjService.updateThumbnail(param);
		
//		req.getSession().setAttribute("SESS_THUMB_ATTCH_FILE_SN", param.get("ATTCH_FILE_SN").toString());
		session.setAttribute("SESS_THUMB_ATTCH_FILE_SN"	, 	param.get("ATTCH_FILE_SN").toString());
		session.setAttribute("SESS_THUMB_LOCAL_FILE_NM"	, 	param.get("LOCAL_FILE_NM").toString());
		session.setAttribute("SESS_THUMB_SVR_FILE_NM"	, 	param.get("SVR_FILE_NM").toString());
		session.setAttribute("SESS_THUMB_SVR_FILE_PATH"	, 	param.get("SVR_FILE_PATH").toString());
		
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
		Map<String,String> rsList = acjService.selectNewCnt(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	@RequestMapping( value="/testMobileDeviceInfo.do")
	public String testMobileDeviceInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/sample/sampleDeviceInfo";   
	}	
	
	@RequestMapping( value="/testMobileCamera.do")
	public String testMobileCamera(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/sample/sampleCamera";   
	}	
	
	@RequestMapping( value="/testMobileBluetooth.do")
	public String testMobileBluetooth(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/sample/sampleBluetooth";
	}	

	@RequestMapping( value="/testMobileFile.do")
	public String testMobileFile(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/sample/sampleFileUpload";
	}	

	/**
	 * 어플리케이션 다운로드 페이지 이동
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/downloadapk.do", method = RequestMethod.GET)
	public String apkDownload(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		return "app/sample/apkDownload";
	}
	
	/**
	 * 알림내역 삭제
	 */
	@RequestMapping( value="/notificationDel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> notificationDel(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = acjService.notificationDel(param);
		rsMap.put("rsList", rsCount);
		return rsMap;
	}	
	
	/**
	 * 메인대쉬 편집 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateMainEdit.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateMainEdit(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		acjService.updateMainEdit(param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		session.setAttribute("DASH_HEARTRATE_SET_YN"		, param.get("DASH_heartRate_SET_YN"));
		session.setAttribute("SESS_DASH_BODYCOMP_SET_YN"	, param.get("DASH_bodyComp_SET_YN"));
		session.setAttribute("SESS_DASH_BLOODPRESS_SET_YN"	, param.get("DASH_bloodPress_SET_YN"));
		session.setAttribute("SESS_DASH_BLOODSUGAR_SET_YN"	, param.get("DASH_bloodSugar_SET_YN"));
		session.setAttribute("SESS_DASH_DIET_SET_YN"		, param.get("DASH_diet_SET_YN"));
		session.setAttribute("SESS_DASH_EXCS_SET_YN"		, param.get("DASH_excs_SET_YN"));
		session.setAttribute("SESS_DASH_WEIGHT_SET_YN"		, param.get("DASH_weight_SET_YN"));
		session.setAttribute("SESS_DASH_SET_ORD_ARR"		, param.get("DASH_SET_ORD_ARR"));		
		
		rsMap.put("DASH_HEARTRATE_SET_YN"	, param.get("DASH_heartRate_SET_YN"));
		rsMap.put("DASH_BODYCOMP_SET_YN"	, param.get("DASH_bodyComp_SET_YN"));
		rsMap.put("DASH_BLOODPRESS_SET_YN"	, param.get("DASH_bloodPress_SET_YN"));
		rsMap.put("DASH_BLOODSUGAR_SET_YN"	, param.get("DASH_bloodSugar_SET_YN"));
		rsMap.put("DASH_DIET_SET_YN"		, param.get("DASH_diet_SET_YN"));
		rsMap.put("DASH_EXCS_SET_YN"		, param.get("DASH_excs_SET_YN"));
		rsMap.put("DASH_WEIGHT_SET_YN"		, param.get("DASH_weight_SET_YN"));
		rsMap.put("DASH_SET_ORD_ARR"		, param.get("DASH_SET_ORD_ARR"));
		
		return rsMap;
	}	
	
	/**
	 * 목표설정 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateObjSet.do", method = RequestMethod.POST)
	public void updateObjSet(@ModelAttribute Map<String, Object> param) throws Exception{
		acjService.updateObjSet(param);
	}	
	
	
	/**
	 * 닉네임 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateNickname.do", method = RequestMethod.POST)
	public void updateNicknameSet(@ModelAttribute Map param, ModelMap model) throws Exception{
		acjService.updateNickname(param);   
	}		
	
	/**
	 * 닉네임 사용 여부 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateNicknameUseYn.do", method = RequestMethod.POST)
	public void updateNicknameUseYn(@ModelAttribute Map param, ModelMap model) throws Exception{
		acjService.updateNicknameUseYn(param);   
		
	}
}
