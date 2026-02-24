package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.ArrayList;
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
 * @Class Name : IntensiveBodyActObstyCnslController.java
 * @Description : 관리자 WEB에서 집중상담_신체활동 비만 상담을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.09.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class IntensiveBodyActObstyCnslController extends DMultiActionController{
	

	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private SeniorTrgterInfoService trgterInfoMngtService;	
	
	@Resource(name = "web.sv.IntensiveCnslMngtService")
	private IntensiveCnslMngtService intensiveCnslMngtSerivce;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 신체 활동 비만 상담 화면 호출
	 */
	@RequestMapping(value= "/intenseBodyActObstyCnsl.do", method = RequestMethod.POST)
	public String intenseBodyActObstyCnsl(@ModelAttribute Map param, ModelMap model) throws Exception {
		param.put("TEMPLATE_CLF", "30");
		
		
		
		Map<String, String> userInfo = intensiveBodyActObstyCnslService.getBasicUserInfo(param);
		
		//Map<String, Object> userInfo = trgterInfoMngtService.trgterInfoMngtDtls(param);
		Map<String, String> cnslHistory = intensiveBodyActObstyCnslService.getCnslHistory(param);
		List<Map<String, Object>> cnsl_de = intensiveBodyActObstyCnslService.getCnslDe(param);
		
		List<Map<String, Object>> prdtotalinfo = intensiveBodyActObstyCnslService.getCnslDePrdTotalInfo(param);		
		
		List<Map<String, Object>> cnslTemplateNm = intensiveBodyActObstyCnslService.getCnslTemplateNm(param);

		List<Map<String, Object>> cnslAttchList = intensiveBodyActObstyCnslService.getCnslAttchList(param);//2017.02.27 이태석 추가(파일첨부)
		
		List<Map<String, Object>> healthExam = intensiveBodyActObstyCnslService.getHelthExam(param);// 2017.03.30 이태석 추가(건강정보 조회)

		Map<String, Object> objWalkAchvPer = intensiveBodyActObstyCnslService.getObjWalkAchvPer(param);// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
		
		Map<String, Object> weekExcsAvgTmCnt = intensiveBodyActObstyCnslService.getWeekExcsAvgTmCnt(param);// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
		Map<String ,String> manager = intensiveCnslMngtSerivce.getManagerType(param);
		
		System.out.println("cnslAttchList ----------------- " + cnslAttchList);
		
		model.addAttribute("cnsl_sn",param.get("CNSL_SN"));
		model.addAttribute("cnsl_sttus",param.get("CNSL_STTUS"));
		model.addAttribute("user_id",param.get("USER_ID"));
		model.addAttribute("svc_mngt_no",param.get("SVC_MNGT_NO"));
		model.addAttribute("cnsl_item_clf",param.get("CNSL_ITEM_CLF"));
		model.addAttribute("userInfo",userInfo);
		model.addAttribute("cnslHistory",cnslHistory);
		model.addAttribute("cnsl_de", cnsl_de);
		model.addAttribute("prdtotalinfo", prdtotalinfo);
		model.addAttribute("cnslTemplateNm", cnslTemplateNm);
		model.addAttribute("saveChk", param.get("saveChk"));
		model.addAttribute("cnsl_no", param.get("CNSL_NO"));		
		
		model.addAttribute("cnslAttchList", cnslAttchList);//2017.02.27 이태석 추가(파일첨부)
		model.addAttribute("healthExam", healthExam);// 2017.03.30 이태석 추가(건강정보 조회)
		model.addAttribute("objWalkAchvPer", objWalkAchvPer);// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
		model.addAttribute("weekExcsAvgTmCnt", weekExcsAvgTmCnt);// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
		
		model.addAttribute("mangerinfo", manager);

		
		
		return "web/sv/intenseBodyActObstyCnsl";
	}
	
	/**
	 * 관리목표 리스트 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getIntensiveBodyGoalMngt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getintensiveBodyGoalMngt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getIntensiveBodyGoalMngt(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 상담일자별 기간누적/전체누적 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getPrdtotalinfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPrdtotalinfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		
		Map<String, String> totEval = intensiveBodyActObstyCnslService.getTotEval(param);
		List<Map<String, Object>> cnslAttchList = intensiveBodyActObstyCnslService.getCnslAttchList(param);//2017.02.27 이태석 추가(파일첨부)
		Map<String, Object> objWalkAchvPer = intensiveBodyActObstyCnslService.getObjWalkAchvPer(param);// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
		Map<String, Object> weekExcsAvgTmCnt = intensiveBodyActObstyCnslService.getWeekExcsAvgTmCnt(param);// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
		
		if(totEval != null){
			rsMap.put("totEval", totEval);
			rsMap.put("cnslAttchList", cnslAttchList);
		}else{
			rsMap.put("totEval", "0");
		}

		rsMap.put("objWalkAchvPer", objWalkAchvPer);// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
		rsMap.put("weekExcsAvgTmCnt", weekExcsAvgTmCnt);// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
		
		return rsMap;
	}
	
	/**
	 * 서비스 주차별 현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getCnslDePrdTotalInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCnslDePrdTotalInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getCnslDePrdTotalInfo(param);//상담차수별 조회
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	
	

	
	/**
	 * 서비스 주차별 현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getSvWeekSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSvWeekSttus(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getSvWeekSttus(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 운동기록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getActRecord.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getActRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getActRecord(param);
		List<Map<String, Object>> rsChartList = intensiveBodyActObstyCnslService.getActRecordChart(param);
		rsMap.put("rsList", rsList);
		rsMap.put("rsChartList", rsChartList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 종합평가 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getTotEvalupdate.do", method = RequestMethod.POST)
	public @ResponseBody int getTotEvalupdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		int rsInt = intensiveBodyActObstyCnslService.getTotEvalupdate(param);
		return rsInt;
	}
	
	/**
	 * 발송update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateBodyCnslSubmit.do", method = RequestMethod.POST)
	public @ResponseBody int updateBodyCnslSubmit(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveBodyActObstyCnslService.updateBodyCnslSubmit(param);	
		return rsInt;
	}
	
	/**
	 * 대상자정보관리 신체활동 탭 날짜별, 요일별
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getBodyActDEList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBodyActDEList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();

		List<Map<String, Object>> rsListDE = intensiveBodyActObstyCnslService.getBodyActDEList(param);
		List<Map<String, Object>> rsListDY = intensiveBodyActObstyCnslService.getBodyActDYList(param);
		
		rsMap.put("rsListDE", rsListDE);
		rsMap.put("rsListDY", rsListDY);
		
		return rsMap;
	}
	
	/**
	 * 심박수 시간대별 분포도
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getHeartResultList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getHeartResultList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//심박수 시간대별
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getDayHeartRateDtaList(param);				
		rsMap.put("rsListHe", rsList);
		return rsMap;
	}	
	
	/**
	 * 심박수 구간 분포도
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getHeartSeResulList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getHeartSeResulList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//심박수 구간별
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getDayHeartRateSecList(param);
		List<Map<String, Object>> rsList2 = intensiveBodyActObstyCnslService.getDayHeartRateSecList2(param);

		rsMap.put("rsListSe", rsList);
		rsMap.put("rsListSe2", rsList2);
		
		return rsMap;
	}		
	
	
	
	/**
	 * 종합평가 delete
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getTotEvalDelete.do", method = RequestMethod.POST)
	public @ResponseBody int getTotEvalDelete(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveBodyActObstyCnslService.getTotEvalDelete(param);	
		return rsInt;
	}
	
	/**
	 * 운동 기록 팝업 화면 호출
	 */
	@RequestMapping(value= "/intenseBodyActRecPop.do", method = RequestMethod.GET)
	public String intenseBodyActRecPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/intenseBodyActRecPop";
	}
	
	/**
	 * 상담_템플릿_내용 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getCnslTemplateConts.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCnslTemplateConts(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String, Object> rsMap = intensiveBodyActObstyCnslService.getCnslTemplateConts(param);
		return rsMap;
	}
	
	/**
	 * 템플릿 관리 팝업 화면 호출
	 */
	@RequestMapping(value= "/getCnslTemplateMngt_pop.do", method = RequestMethod.GET)
	public String cnslTemplateMngt_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/cnslTemplatePop";
	}
	
	/**
	 * 상담_템플릿 팝업 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getCnslTemplateList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCnslTemplateList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();			
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getCnslTemplateList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 상담 템플릿 저장 및 수정
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getCnslTemplateUpdate.do", method = RequestMethod.POST)
	public @ResponseBody int getCnslTemplateUpdate(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveBodyActObstyCnslService.getCnslTemplateUpdate(param);	
		return rsInt;
	}
	
	/** 2017.02.13
	 * 상담 템플릿 삭제
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getCnslTemplateDel.do", method = RequestMethod.POST)
	public @ResponseBody int getCnslTemplateDel(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = intensiveBodyActObstyCnslService.getCnslTemplateDel(param);
		return rsInt;
	}
	
	/**
	 * 상담_템플릿 명 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getCnslTemplateNm.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCnslTemplateNm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getCnslTemplateNm(param);		
		return rsList;
	}
	
	/** 
	 * 2017.04.12 이태석 추가
	 * 해당 일 심박수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getDayHeartRateDtaList.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDayHeartRateDtaList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getDayHeartRateDtaList(param);		
		return rsList;
	}
	
	/** 
	 * 2017.05.18 이현규 추가
	 * 목표 심박 달성률 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getObjHrSuc.do")
	public @ResponseBody Map<String, Object> getObjHrSuc(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = intensiveBodyActObstyCnslService.getObjHrSucRate(param);
		return rsMap;
	}
	
	/**
	 * 동영상 템플릿 팝업 호출
	 */
	@RequestMapping(value= "/intenseBodyActVdPop.do", method = RequestMethod.GET)
	public String intenseBodyActVdPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/intenseBodyActVdPop";
	}
	
	/** 
	 * 2017.06.26 추가
	 * 공통 동영상 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getBodyActVdTemplateList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBodyActVdTemplateList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.getBodyActVdTemplateList(param);		

		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 일괄전송update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateBodyActAllSubmit.do", method = RequestMethod.POST)
	public @ResponseBody int updateBodyActAllSubmit(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = intensiveBodyActObstyCnslService.updateBodyActAllSubmit(param);	
		return rsInt;
	}
	
	/**
	 * 인바디 체성분 정보 확인 팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/bodyCompInfoPop.do")
	public String bodyCompInfoPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/sv/bodyCompInfoPop";
	}
	
	/**
	 * 인바디 체성분 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/bodyCompInfoList.do")
	public @ResponseBody Map<String, Object> bodyCompInfoList(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = intensiveBodyActObstyCnslService.bodyCompInfoList(param); 
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
}
