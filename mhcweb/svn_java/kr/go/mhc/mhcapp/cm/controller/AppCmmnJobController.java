package kr.go.mhc.mhcapp.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.cm.service.AppCmmnJobService;

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

	//나중에 삭제해주세요~
	@RequestMapping( value="/myhealth.do")
	public String myhealth(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		return "app/myhealth_test";   
	}	

	/**
	 * 공지사항 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noticeMain.do", method = RequestMethod.GET)
	public ModelAndView noticeMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("app/cm/notice");
		  modelAndView.addObject("noticeSN", param.get("noticeSN"));
		return modelAndView;
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
	@RequestMapping( value="/myInfoMain.do", method = RequestMethod.GET)
	public ModelAndView myInfoMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app/cm/myInfo");
		modelAndView.addObject("serveyInfo", param.get("serveyInfo"));
		
		return modelAndView;
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
	 * 상담 메뉴 분기 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/menuChoice.do", method = RequestMethod.GET)
	public String menuChoice(HttpServletRequest req,@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		int reportCnt = acjService.reportNullChk(param);
		HttpSession session = req.getSession();
		
		if( reportCnt > 0){ //월간 리포트 화면
			return "app/sv/monthlyReport";
		}else{//기간별 상담화면
			if("40".equals(req.getSession().getAttribute("SESS_CNSL_CLF"))){ //실시간상담
				return "app/cm/chat/chatMain";
			}else if("30".equals(req.getSession().getAttribute("SESS_CNSL_CLF"))){ //일반 상담("30")
				return "app/mr/generalCnsl";
			}else if("20".equals(req.getSession().getAttribute("SESS_CNSL_CLF"))){ //집중 상담("20") 
				return "app/sv/monthlyReport";
			}else{ //기본 - 일반 상담
				return "app/mr/generalCnsl";
			}
		}
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
		
		List<Map<String,String>> rsList = acjService.selectChkMyInfo(param);   
		String prePwChk = (String) param.get("PRE_PW");
		String pwCompare = (String) rsList.get(0).get("PW");
	
		if(!prePwChk.equals(pwCompare)){
			prePwChk = "false";
		} 
		else {
			prePwChk = "true";
		}
		
		rsMap.put("rsList", prePwChk );
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
	 * 푸쉬 상태 on off
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updatePushSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updatePushSttus(HttpSession session, HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		acjService.updatePushSttus(param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		session.setAttribute("SESS_PUSH_NOTICE_SET_YN", param.get("PUSH_YN"));
		return rsMap;
	}	
	
	/**
	 * 푸쉬 상태 on off
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateBodySttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateBodySttus(HttpSession session, HttpServletRequest req ,@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		acjService.updateBodySttus(param);
		Map<String,Object> rsMap = new HashMap<String,Object>();
		session.setAttribute("SESS_BODYACT_AUTOUPD_YN", param.get("BODY_YN"));
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
		session.setAttribute("SESS_THUMB_ATTCH_FILE_SN"	, 	param.get("ATTCH_FILE_SN"));
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
	
}
