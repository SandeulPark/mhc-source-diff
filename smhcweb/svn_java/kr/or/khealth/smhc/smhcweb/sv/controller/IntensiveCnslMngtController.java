package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.or.khealth.smhc.smhcweb.sv.service.IntensiveCnslMngtService;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorTrgterInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : IntensiveCnslMngtController.java
 * @Description : 관리자 WEB에서 사용하는 집중 상담업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01		장슬기			최초생성
 *
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class IntensiveCnslMngtController extends DMultiActionController{
	
	@Resource(name = "web.sv.IntensiveCnslMngtService")
	private IntensiveCnslMngtService intensiveCnslMngtSerivce;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private SeniorTrgterInfoService trgterInfoMngtService;
	
	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 집중상담리스트 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/intensiveCnslList.do", method = RequestMethod.GET)
	public String intensiveCnslMngtList(@ModelAttribute Map param, ModelMap model) throws Exception {
		//Map<String, String> rsMap = intensiveCnslMngtSerivce.getCountIntensiveCnsl(param);	
		//model.addAttribute("rsMap", rsMap);
		if(param.get("mainTotalYn") != null){
			model.addAttribute("mainTotalYn", param.get("mainTotalYn"));
			model.addAttribute("mainTrgtYY",param.get("mainTrgtYY"));
			if(param.get("mainTotalYn").toString().equals("Y")){
				model.addAttribute("mainTotalBgnDe", param.get("mainTotalBgnDe"));			
			}
		}
		return "web/sv/intensiveCnslMngtList";
	}
	
	/**
	 * 집중상담 리스트 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/intensiveCnslMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getIntensiveCnslMngtList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getIntensiveCnslMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
	/**
	 * 집중상담 상세 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/intensiveCnslDetail.do", method = RequestMethod.POST)
	public String selectUserList(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		String menuCd = (String) param.get("menuCd");
		List<Map<String,String>> menuList = cmmnService.selectCmmnMenu(param);
		model.addAttribute("menuList", menuList);
		
		model.addAttribute("leftMenuCd", menuCd);
		model.addAttribute("topMenuCd", menuCd.substring(0, 4)+"00");
			
		Map<String,String> menuInfo = cmmnService.selectCmmnMenuInfo(param);
		
		if(menuCd.equals("NSV222")){
			menuInfo.put("MENU_URL", "/sv/intenseNutriObesityCnsl.do");			
		}else if(menuCd.equals("NSV223")){
			menuInfo.put("MENU_URL", "/sv/intenseBodyActObstyCnsl.do");
		}else if(menuCd.equals("NSV224")){
			menuInfo.put("MENU_URL", "/sv/intenseHealthMngtCnsl.do");
		}else if(menuCd.equals("NHM131")){			//20191205 양현우 추가
			menuInfo.put("MENU_URL", "/st/mthlyHealthRptDtls.do");
		}
		
		model.addAttribute("USER_ID", param.get("USER_ID"));
		model.addAttribute("CNSL_SN", param.get("CNSL_SN"));
		model.addAttribute("svc_mngt_no",param.get("SVC_MNGT_NO"));
		model.addAttribute("menuInfo", menuInfo);
		
		
		return "web/pageNavi";
	}
	
	/**
	 * 건강관리 상담 상세 화면 호출
	 */
	@RequestMapping(value= "/intenseHealthMngtCnsl.do", method = RequestMethod.POST)
	public String intenseHealthMngtCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, String> rsMap = intensiveCnslMngtSerivce.selectHealthCnslDtls(param);
		List<Map<String, Object>> attchFiles = intensiveCnslMngtSerivce.selectHealthCnslAttchFiles(param);
		List<Map<String, Object>> bottomList = intensiveCnslMngtSerivce.selectHealthCnslBottomList(param);
		
		model.addAttribute("rsMap",rsMap);
		model.addAttribute("attchFiles",attchFiles);
		model.addAttribute("bottomList",bottomList);

		return "web/sv/intenseHealthMngtCnsl";
	}
	
	/**
	 * 건강관리 상담 상세 화면 하단 그리드 호출
	 * @return 
	 */
	@RequestMapping(value= "/intenseHealthMngtCnslInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> intenseHealthMngtCnslInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();			
		List<Map<String, Object>> bottomList = intensiveCnslMngtSerivce.selectHealthCnslBottomList(param);
		
		rsMap.put("rsList", bottomList);
		rsMap.put("id", param.get("id"));	
		
		
		return rsMap;
	}
	
	
	/**
	 * 영양비만 상담 화면 호출
	 */
	@RequestMapping(value= "/intenseNutriObesityCnsl.do", method = RequestMethod.POST)
	public String intenseNutriObesityCnsl(@ModelAttribute Map param, ModelMap model) throws Exception {
		param.put("TEMPLATE_CLF", "20");
		
		//Map<String, String> userInfo = intensiveCnslMngtSerivce.getBasicUserInfo(param);
		Map<String ,String> manager = intensiveCnslMngtSerivce.getManagerType(param);
//		Map<String, Object> userInfo = trgterInfoMngtService.trgterInfoMngtDtls(param);
		Map<String, String> cnslHistory = intensiveCnslMngtSerivce.getCnslHistory(param);
		List<Map<String, Object>> meal_reg_de = intensiveCnslMngtSerivce.getMealRegDe(param);
		List<Map<String, Object>> cnsl_de = intensiveCnslMngtSerivce.getCnslDe(param);
		List<Map<String, String>> evalActive = intensiveCnslMngtSerivce.getEvalActive(param);
		List<Map<String, Object>> cnslTemplateNm = intensiveCnslMngtSerivce.getCnslTemplateNm(param);
		
		//건강정보 목록 조회(집중상담-신체활동 기능 호출)
		List<Map<String, Object>> healthExam = intensiveBodyActObstyCnslService.getHelthExam(param);
		//상담 차수별 현황 종합
		List<Map<String, Object>> prdTotalInfo  = intensiveCnslMngtSerivce.getPrdTotalinfo(param);
	
		model.addAttribute("cnsl_sn",param.get("CNSL_SN"));
		model.addAttribute("cnsl_no", param.get("CNSL_NO"));  //20191206양현우 추가
		model.addAttribute("cnsl_sttus",param.get("CNSL_STTUS"));
		model.addAttribute("user_id",param.get("USER_ID"));
		model.addAttribute("svc_mngt_no",param.get("SVC_MNGT_NO"));
		model.addAttribute("cnsl_item_clf",param.get("CNSL_ITEM_CLF"));
//		model.addAttribute("userInfo",userInfo);
		model.addAttribute("cnslHistory",cnslHistory);
		model.addAttribute("meal_reg_de", meal_reg_de);
		model.addAttribute("cnsl_de", cnsl_de);
		model.addAttribute("evalActive",evalActive);
		model.addAttribute("cnslTemplateNm", cnslTemplateNm);
		model.addAttribute("mangerinfo", manager);
		model.addAttribute("healthExam", healthExam);// 2017.03.30 이태석 추가(건강정보 조회)	
		
		model.addAttribute("prdTotalInfo",prdTotalInfo);
		model.addAllAttributes(param);
		return "web/sv/intenseNutriObesityCnsl";
	}
	
	/**
	 * 식사 일자 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealRegDe.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMealRegDe(@ModelAttribute Map param, ModelMap model) throws Exception{
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getMealRegDe(param);
		return rsList;
	}
	
	
	/**
	 * 관리목표 리스트 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getGoalMngt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getGoalMngt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getGoalMngt(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	

	/**
	 * 주차별 식사일기 식품군 현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getWeekTotalnfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getWeekTotalnfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsList  = intensiveCnslMngtSerivce.getSvWeekTotalnfo(param);				
		rsMap.put("rsWeek", rsList);
		return rsMap;
	}		
	
	/**
	 * 요일별 식사일기 칼로리 및 탄단지 현황, 끼니별 평균 칼로리
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getMealDiaryDay.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBodyActDEList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();

		List<Map<String, Object>> rsCalTotal = intensiveCnslMngtSerivce.getCalTotalInfo(param);
		List<Map<String, Object>> rsCFRTotal = intensiveCnslMngtSerivce.getCRFTotalnfo(param);
		List<Map<String, Object>> rsMealTotal = intensiveCnslMngtSerivce.getMealAvgInfo(param);
		//식사일기 섭취현황 종합
		//List<Map<String, Object>> rsAssayTotal  = intensiveCnslMngtSerivce.getMealAssayTotalInfo(param);		
		//식사일기 유지 및 당류, 주류 섭취비율 종합
		//List<Map<String, Object>> rsEtcTotal  = intensiveCnslMngtSerivce.getMealEtcTotalInfo(param);		
		//식사일기 영양섭취평가 종합
		List<Map<String, Object>> rsNutriTotal  = intensiveCnslMngtSerivce.getMealNutriTotalInfo(param);			
		
		rsMap.put("rsCalTotal", rsCalTotal);
		rsMap.put("rsCFRTotal", rsCFRTotal);
		rsMap.put("rsMealTotal", rsMealTotal);
		
		//rsMap.put("rsAssayTotal", rsAssayTotal);
		//rsMap.put("rsEtcTotal",   rsEtcTotal);
		rsMap.put("rsNutriTotal", rsNutriTotal);


		return rsMap;
	}	
	
	
    
	/**
	 * 식사정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealDietInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMealDietInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = intensiveCnslMngtSerivce.getMealDietInfo(param);	
		return rsMap;
	}
	

	/**
	 * 식단 이미지 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mealInfoImg.do", method = RequestMethod.GET)
	public String mealInfoImg(@ModelAttribute Map param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		model.addAttribute("mealInfoUrl", param.get("mealInfoUrl"));
		model.addAttribute("whenMealInfo", param.get("whenMealInfo"));		
		return "web/sv/mealInfoImgPop";
	}
	
	/**
	 * 섭취현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getIntakeSttus.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getIntakeSttus(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String, String> intakeSttus = intensiveCnslMngtSerivce.getIntakeSttus(param);
		
		//2017.03.03 이태석 추가(파일첨부)
		List<Map<String, Object>> cnslAttchList = intensiveCnslMngtSerivce.getCnslAttchList(param);
				
		if(intakeSttus != null){
			rsMap.put("intakeSttus", intakeSttus);
			if(cnslAttchList != null){
				rsMap.put("cnslAttchList", cnslAttchList);				
			}
		}else{
			rsMap.put("intakeSttus", "0");
		}		
		return rsMap;
	}
	
	/**
	 * 섭취현황_식행동평가_종합평가 update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateIntensiveCnslEval.do", method = RequestMethod.POST)
	public @ResponseBody int updateIntensiveCnslEval(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveCnslMngtSerivce.updateIntensiveCnslEval(param);	
		return rsInt;
	}
	
	/**
	 * 발송update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateSubmit.do", method = RequestMethod.POST)
	public @ResponseBody int updateSubmit(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveCnslMngtSerivce.updateSubmit(param);	
		return rsInt;
	}
	
	/**
	 * 섭취현황_식행동평가_종합평가 delete
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deleteIntensiveCnslEval.do", method = RequestMethod.POST)
	public @ResponseBody int deleteIntensiveCnslEval(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveCnslMngtSerivce.deleteIntensiveCnslEval(param);	
		return rsInt;
	}
	
	/**
	 * 목표관리 상세 보기 팝업 화면 호출
	 */
	@RequestMapping(value= "/intenseNutriGoalMngtDtlsPop.do", method = RequestMethod.GET)
	public String intenseNutriGoalMngtDtlsPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/intenseNutriGoalMngtDtlsPop";
	}
	
	/**
	 * 실천미션 결과 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/practMissionRslt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPractMissionRslt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getPractMissionRslt(param);
		rsMap.put("rsRslt", rsList);
		return rsMap;
	}
	
	
	/**
	 * 식사일기 목록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealDiaryList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getMealDiaryList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> mealDiary = intensiveCnslMngtSerivce.getMealDiaryList(param);				
		rsMap.put("mealDiary", mealDiary);	
		return rsMap;
	}	
	
	
	/**
	 * 식사일기 상세팝업 호출
	 */
	@RequestMapping(value= "/mealDiaryDtlsPop.do", method = RequestMethod.GET)
	public String mealDiaryDtlsPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getMealDiaryInputInfo(param);		
		
		model.addAllAttributes(param);		
		model.addAttribute("mealList",rsList);
		
		return "web/sv/intenseNutriMealDiaryPop";
	}	
	
	
	/**
	 * 식사일기 입력정보(팝업) 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealDiaryInputInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMealDiaryInputInfo(@ModelAttribute Map param, ModelMap model) throws Exception{		
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		List<Map<String, Object>> rsList = intensiveCnslMngtSerivce.getMealDiaryInputInfo(param);
		rsMap.put("mealList", rsList);	
		return rsMap;		
	}		
	
	/**
	 * 식사일기 차트 데이터 정보(팝업)
	 */
	@RequestMapping(value= "/mealDiaryChart.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mealDiaryChart(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		
		//탄단지 비율
		List<Map<String, Object>>rsCRF    = intensiveCnslMngtSerivce.getCRFPerRslt(param);			
		//끼니별 칼로리
		List<Map<String, Object>> rsCal   = intensiveCnslMngtSerivce.getMealDivCal(param);			
		//영양섭취평가
		List<Map<String, Object>> rsNutri = intensiveCnslMngtSerivce.getMealNutriRslt(param);	

		rsMap.put("rsCRF",   rsCRF);	
		rsMap.put("rsCal",   rsCal);	
		rsMap.put("rsNutri", rsNutri);		
		
		return rsMap;		
	}		
	
	/**
	 * 식사일기 섭취현황(팝업)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealAssayRslt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMealAssayRslt(@ModelAttribute Map param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String, Object> rsAssay  = intensiveCnslMngtSerivce.getMealAssayRslt(param);		

		rsMap.put("rsAssay", rsAssay);		

		return rsMap;		
	}		
    	
	
	/**
	 * 기타 섭취현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMealEtcRslt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getMealEtcRslt(@ModelAttribute Map param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String, Object> rsEtc = intensiveCnslMngtSerivce.getMealEtcRslt(param);	
		rsMap.put("rsEtc", rsEtc);		

		return rsMap;		
	}		
	
	/**
	 * 종합평가 이력 팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cnslContHistPop.do", method=RequestMethod.GET)
	public String cnslContHistPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sv/cnslContHistPop";
	}
	
	@RequestMapping(value = "/getCnslContHist.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCnslContHist(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = intensiveCnslMngtSerivce.getCnslContHist(param);
		rsMap.put("rsList",rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 일괄전송update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateNutriAllSubmit.do", method = RequestMethod.POST)
	public @ResponseBody int updateNutriAllSubmit(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveCnslMngtSerivce.updateNutriAllSubmit(param);	
		return rsInt;
	}
	/**
	 * 문의사항 팝업 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectintensiveCnslReqPop.do", method=RequestMethod.GET)
	public String selectIntensiveCnslMngtListPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String,Object>> rsList = intensiveCnslMngtSerivce.selectIntensiveCnslMngtListPop(param);
		model.addAllAttributes(param);
		model.addAttribute("rsList",rsList);
		return "web/sv/intensiveCnslReqPop";
	}	
	/**
	 * 답장 정보 변경
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateIntensiveCnslMngtListPop.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>updateIntensiveCnslMngtListPop(@ModelAttribute Map<String,Object> param , ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = intensiveCnslMngtSerivce.updateIntensiveCnslMngtListPop(param);
		if(rsInt ==0) {
			rsMap.put("update", "N");
		}else{
			rsMap.put("update", "Y");
		}
		return rsMap;
	}
	
}
