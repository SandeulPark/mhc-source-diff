package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.HealthMngtCnslService;
import kr.go.mhc.mhcweb.tg.service.ServiceObjMngtService;

/**
 * @Class Name : ServiceObjMngtController.java
 * @Description : 관리자 WEB에서 사용하는 서비스목표관리/영양비만상담 및 목표관리 를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		장슬기			최초생성
 *
 * @author gst
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class ServiceObjMngtController extends DMultiActionController{
	
	@Resource(name = "web.tg.ServiceObjMngtService")
	private ServiceObjMngtService serviceObjMngtService;
	
	@Resource(name = "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 서비스목표관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceObjMngt.do")
	public String serviceObjMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
	 return "web/tg/serviceObjMngtList";
    }
	
	/**
	 * 서비스목표관리 화면 건수 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceObjCount.do")
	public @ResponseBody Map<String, String> serviceObjCount(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, String> rsMap = serviceObjMngtService.getCountServiceObjMngt(param);	
		
		model.addAttribute("rsMap", rsMap);

		return rsMap;
    }
	
	/**
	 * 서비스목표관리 리스트 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getServiceObjMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSerivceObjMngtList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = serviceObjMngtService.getSerivceObjMngtList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 영양비만 상담 및 목표설정 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/nutriObesityCnsl.do", method = RequestMethod.POST)
	public String preTrgterRegit(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = serviceObjMngtService.getUserInfo(param);
		String gender = rsMap.get("GENDER");
		String svcMngtNo = rsMap.get("SVC_MNGT_NO");
		String preNo = rsMap.get("PRE_TRGTER_NO");
		
		param.put("GENDER",gender);
		param.put("SVC_MNGT_NO", svcMngtNo);
		param.put("PRE_TRGTER_NO", preNo);

		Map<String, String> rsCnsl = serviceObjMngtService.getCnslInfo(param);
		List<Map<String, String>> rsList = serviceObjMngtService.getActiceCnt(param);
		List<Map<String, String>> rsList2 = serviceObjMngtService.getControlCnt(param);
		List<Map<String, String>> rsList3 = serviceObjMngtService.getActiveControl(param);		
		List<Map<String, String>> dateList = serviceObjMngtService.getDateList(param);

		Map<String, String> myWeekMap  = serviceObjMngtService.getMyWeek(param);	// key : myWeek , value : 내 현재 주차
		
		param.put("CNSL_NO", rsMap.get("CNSL_NO"));
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);						// 대상자정보, 건강검진 데이터
		model.addAttribute("examList", examList);
		//권장 탄단지 비율
		//Map<String, String> rsCRFPer = serviceObjMngtService.getRecomCRFPer(param);					
		//실천미션일정
		List<Map<String, String>> practList = serviceObjMngtService.selectPractMissionSch(param);		

		String intenseFromYn = "N";
		if(param.get("intenseFromYn") != null){
			intenseFromYn = param.get("intenseFromYn").toString();
			model.addAttribute("intense_CNSL_SN", param.get("intense_CNSL_SN"));
			model.addAttribute("intense_CNSL_STTUS", param.get("intense_CNSL_STTUS"));
			model.addAttribute("CNSL_SN", param.get("CNSL_SN"));
		}
		model.addAttribute("intenseFromYn", intenseFromYn);
		model.addAttribute("rsList",rsList);
		model.addAttribute("rsList2",rsList2);
		model.addAttribute("rsList3",rsList3);
		model.addAttribute("rsMap",rsMap);
		model.addAttribute("rsCnsl",rsCnsl);
		model.addAttribute("dateList",dateList);
		//model.addAttribute("rsCRFPer",rsCRFPer);		
		model.addAttribute("practList",practList);
		model.addAttribute("practChkYn", practList.size()>0?"Y":"N");
		
		model.addAttribute("myWeekMap",myWeekMap);
		
		model.addAllAttributes(param);
		return "web/tg/nutriObesityCnslMngt";
	
	}
	
	/**
	 * 열량계산 ajax
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/changeNeedam.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> preTrgtMngtList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		if(param.get("WEIGHT_CTRL_PER").equals("N")){
		Map<String, String> rsNeedAm = serviceObjMngtService.changeNeedam(param);
		
		System.out.println("NeedAm ===>" + rsNeedAm);
		
		rsMap.put("rsNeedAm", rsNeedAm);
		}else{
		Map<String, String> objNeedAm = serviceObjMngtService.changeObjNeedam(param);
		rsMap.put("objNeedAm", objNeedAm);
		System.out.println("objNeedAm ===>" + objNeedAm);
		}
		
		return rsMap;
	}
	
	/**
	 *  섭취현황,섭취기준,대비 목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getObjEatNeed.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getObjEatNeed(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = serviceObjMngtService.getObjEatNeed(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 섭취군 평가 목표 계산 ajax
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getRecommendEatCnt.do")
	public @ResponseBody Map<String, Object> getRecommendEatCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsEatCnt = serviceObjMngtService.getRecommendEatCnt(param);
		
		System.out.println("rsEatCnt =========== " + rsEatCnt);
		
		rsMap.put("rsEatCnt", rsEatCnt);
		
		return rsMap;
	}
	
	/**
	 *  위험요인 목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getDangerFactor.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDangerFactor(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		
		
		List<Map<String, String>> rsList = serviceObjMngtService.getDangerFactor(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * cnslInfo 정보 update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateCnslInfo.do")
	public String updateCnslInfo(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		serviceObjMngtService.updateCnslNurtInfo(param);

		return "web/tg/nutriObesityCnslMngt";
	}
	
	/**
	 * cnslInfo 정보 new insert
	 * @param 
	 * @return 
	 y* @throws Exception 
	 */
	@RequestMapping(value = "/insertNewCnslInfo.do")
	public String insertNewCnslInfo(@ModelAttribute Map param, ModelMap model) throws Exception {
	    
		serviceObjMngtService.insertNewCnslNurtInfo(param);
		
		return "web/tg/nutriObesityCnslMngt";
	}
	
	/**
	 * 연동확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/checkingIntegration.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkingIntegration(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		Map<String, String> IntegrationInfo = serviceObjMngtService.checkingIntegration(param);
		
		rsMap.put("IntegrationInfo", IntegrationInfo);
		return rsMap;
	}
	
	/**
	 * 지급완료 update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/success_pgmt.do")
	public String success_pgmt(@ModelAttribute Map param, ModelMap model) throws Exception {
	    
		serviceObjMngtService.success_pgmt(param);
		
		return "web/tg/nutriObesityCnslMngt";
	}

	
	/**
	 * 실천미션 스케줄 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectPractMissionSch.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectPractMissionSch(@ModelAttribute Map param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();	
		List<Map<String, String>> rsList = serviceObjMngtService.selectPractMissionSch(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
    }		
	
	/**
	 * 실천미션 스케줄 생성
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/createPractMissionSch.do")
	public @ResponseBody Map<String, Object> createPractMissionSch(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		 //String isCh = 	StringUtil.nvl(param.get("CHRONIC_DISEASES_YN").toString());
		String isCh = 	param.get("CHRONIC_DISEASES_YN").toString();
		int rsInt = 0;
		if(isCh.equals("Y")){
			rsInt = serviceObjMngtService.insertCreatePractMissionSchChronic(param);

			rsMap.put("updateChkCnt", rsInt);

			if(rsInt != 0){
				List<Map<String, String>> practList = serviceObjMngtService.selectPractMissionSchChronic(param);
				rsMap.put("practList", practList);
			}
		} else {
			rsInt = serviceObjMngtService.insertCreatePractMissionSch(param);

			rsMap.put("updateChkCnt", rsInt);

			if(rsInt != 0){
				List<Map<String, String>> practList = serviceObjMngtService.selectPractMissionSch(param);
				rsMap.put("practList", practList);
			}
		}
		
		return rsMap;
    }	
		
	/**
	 * 실천미션 스케줄 변경 변경 코드 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/practMissionUpdPop.do", method= RequestMethod.GET)
	public String practMissionUpdPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, String>> rsList = serviceObjMngtService.selectPractMission(param);	
		model.addAllAttributes(param);
		model.addAttribute("rsList",rsList);		

		return "web/tg/practMissionUpdPop";
    }

	/**
	 * 실천미션 스케줄 변경 변경 코드 호출 - 만성질환 전용
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/practMissionUpdChronicPop.do", method= RequestMethod.GET)
	public String practMissionUpdChronicPop(@ModelAttribute Map param, ModelMap model) throws Exception {

		List<Map<String, String>> rsList = serviceObjMngtService.selectPractMissionChronic(param);
		model.addAllAttributes(param);
		model.addAttribute("rsList",rsList);

		return "web/tg/practMissionUpdChronicPop";
	}

	/**
	 * 실천미션 스케줄 목록 조회(팝업)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/practMissionList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectPractMissionList(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		List<Map<String, String>> rsList = serviceObjMngtService.selectPractMission(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
    }		

	
	/**
	 * 실천미션 스케줄 변경 수정  
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updatePracMissionSch.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePracMissionSch(@ModelAttribute Map param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = serviceObjMngtService.updatePractMissionSch(param);
		rsMap.put("updateChkCnt", rsInt);
		
		if(rsInt != 0){
			List<Map<String, String>> practList = serviceObjMngtService.selectPractMissionSch(param);	

			rsMap.put("practList", practList);
		}
		return rsMap;
    }

	/**
	 * 실천미션 스케줄 변경 수정 - 만성
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePracMissionSchChronic.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePracMissionSchChronic(@ModelAttribute Map param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = serviceObjMngtService.updatePractMissionSchChronic(param);
		rsMap.put("updateChkCnt", rsInt);

		if(rsInt != 0){
			List<Map<String, String>> practList = serviceObjMngtService.selectPractMissionSchChronic(param);

			rsMap.put("practList", practList);
		}
		return rsMap;
	}


	/**
	 * 서비스일정 생성 여부 확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getSvcSchCreateYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSvcSchCreateYn(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = serviceObjMngtService.getSvcSchCreateYn(param);
		
		return rsMap;
	}
	
	/**
	 * 영양 목표설정 변경 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/objSetChange_pop.do", method= RequestMethod.GET)
	public String objSetChange_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
	List<Map<String, String>> rsList = serviceObjMngtService.getActiceCnt(param);
	List<Map<String, String>> rsList2 = serviceObjMngtService.getControlCnt(param);
	model.addAttribute("afterRsList",rsList);
	model.addAttribute("afterRsList2",rsList2);
	 return "web/tg/nutriObesityCnslObjSetChangePop";
    }
	
	/**
	 * 목표상담 완료시 서비스개시일 지정 여부 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCnslCompleteSvcBgnAppontYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCnslCompleteSvcBgnAppontYn(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = serviceObjMngtService.getCnslCompleteSvcBgnAppontYn(param);
		
		return rsMap;
	}
	
}
	