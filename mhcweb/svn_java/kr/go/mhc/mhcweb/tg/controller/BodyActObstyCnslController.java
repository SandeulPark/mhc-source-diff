package kr.go.mhc.mhcweb.tg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.DateUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.BodyActObstyCnslService;
import kr.go.mhc.mhcweb.tg.service.HealthMngtCnslService;
import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

/**
 * @Class Name : BodyActObstyCnslController.java
 * @Description : 관리자 WEB에서 사용하는 신체활동비만 상담 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class BodyActObstyCnslController extends DMultiActionController {

	@Resource(name = "web.tg.BodyActObstyCnslService")
	private BodyActObstyCnslService bdyActObstyCnslService;
	
	@Resource(name = "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private TrgterInfoMngtService trgterInfoMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 신체활동, 비만 상담 상세 화면 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bodyActObstyCnslDtls.do", method = RequestMethod.POST)
	public String bodyActObstyCnslDtls(@ModelAttribute Map param, ModelMap model)	throws Exception {
		String intenseFromYn = "N";
		DateUtil dateUtil = new DateUtil();
		
		if(param.get("intenseFromYn") != null){
			intenseFromYn = param.get("intenseFromYn").toString();
			model.addAttribute("intense_CNSL_SN", param.get("intense_CNSL_SN"));
			model.addAttribute("intense_CNSL_STTUS", param.get("intense_CNSL_STTUS"));
			model.addAttribute("intense_CNSL_DE", param.get("intense_CNSL_DE"));
		}
		
		Map<String, String> rsMap = bdyActObstyCnslService.getBodyActObstyCnslDtls(param);
		param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		List<Map<String,String>> cnslActRegDeList = bdyActObstyCnslService.getCnslActRegDeList(param);
		
		Map<String, Object> bodyActCnslContInfo = bdyActObstyCnslService.selectBodyActCnslContInfo(param);
		
		param.put("CNSL_NO", rsMap.get("CNSL_NO"));
		param.put("PRE_TRGTER_NO", rsMap.get("PRE_TRGTER_NO"));
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);	

		String bgnDe = dateUtil.getNextDate(-6).substring(0, 8);
		String ednDe = dateUtil.getSysDatenf();
		
		param.put("HEART_BGN_DE", bgnDe);			
		param.put("HEART_END_DE", ednDe);
		
		List<Map<String, Object>> hrList = trgterInfoMngtService.selHourHeartData2(param);

		
		//대상자 심박정보
		model.addAttribute("hrList", hrList);
		
		// 대상자정보, 건강검진 데이터
		model.addAttribute("examList", examList);

		
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("CNSL_SN", param.get("CNSL_SN"));
		model.addAttribute("CNSL_STTUS", param.get("CNSL_STTUS"));
		model.addAttribute("DEF_CNSL_NO", rsMap.get("CNSL_NO"));
		model.addAttribute("intenseFromYn", intenseFromYn);
		model.addAttribute("cnslActRegDe", cnslActRegDeList);
		model.addAttribute("maxCnslNo", cnslActRegDeList.get(0).get("CNSL_NO"));
		model.addAttribute("bodyActCnslContInfo", bodyActCnslContInfo);
		
		return "web/tg/bodyActObstyCnslDtls";
	}
	
	/**
	 * 활동_장비_연동 확인 
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/actEquipPymntY.do", method = RequestMethod.POST)
	public @ResponseBody void actEquipPymntY(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {		
		bdyActObstyCnslService.getActEquipPymntY(param);
	}
	
	/**
	 * 활동_장비_연동 확인 
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/actEquipTestYn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> actEquipTestYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {		
		Map<String,String> rsMap = bdyActObstyCnslService.getActEquipTestYn(param);
		return rsMap;
	}

	/**
	 * 상담 조회 
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslAct.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> cnslAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = bdyActObstyCnslService.getCnslAct(param);
		return rsMap;
	}
	
	/**
	 * 위험요인 목록 조회 
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/riskFactorList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> riskFactorList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = bdyActObstyCnslService.getRiskFactorList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 관리군 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/actCnslSn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> actCnslSn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = bdyActObstyCnslService.getActCnslSn(param);
		return rsMap;
	}
	
	/**
	 * 상담 저장_업데이트 
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveActCnsl.do", method = RequestMethod.POST)
	public @ResponseBody void saveActCnsl(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, String> cntMap = bdyActObstyCnslService.getCnslHistCnt(param);
		String histCnt = String.valueOf(cntMap.get("CNSL_CNT"));
		if("0".equals(histCnt)){
			bdyActObstyCnslService.updateBodyObstyCnsl(param);
		}
		bdyActObstyCnslService.getSaveActCnsl(param);
	}
	
	/**
	 * 2017.04.12 이태석 추가
	 * 운동 정보 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excsInfoPop.do", method = RequestMethod.GET)
	public String excsInfoPop(@ModelAttribute Map param, ModelMap model)	throws Exception {
		model.addAttribute("CHRONIC_DISEASES_YN", param.get("isChronic").toString());
		return "web/tg/excsInfoPop";
	}
	
	/**
	 * 2017.04.12 이태석 추가 (운동 정보 팝업)
	 * 부위 별 운동 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getBodyPartExcsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBodyPartExcsList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String searchInfo = StringUtil.nvl((String)param.get("PART_CD"));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}else{
			param.put("searchInfoList", "");
		}
		
		List<Map<String, String>> rsList = bdyActObstyCnslService.getBodyPartExcsList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 최대산소섭취량 판정
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getMaxOxyIntakeAmJudge.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> getMaxOxyIntakeAmJudge(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = bdyActObstyCnslService.getMaxOxyIntakeAmJudge(param);
		return rsMap;
	}
	
	/**
	 * 목표설정 팝업 열기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bodyActObstyCnslRegPop.do", method=RequestMethod.GET)
	public String bodyActObstyCnslRegPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		//상담_활동_이력 테이블에 정보가 있는지 확인 후 없으면 insert 먼저 실행
		Map<String, String> cntMap = bdyActObstyCnslService.getCnslHistCnt(param);
		String histCnt = String.valueOf(cntMap.get("CNSL_CNT"));
		if("0".equals(histCnt)){
			bdyActObstyCnslService.updateBodyObstyCnsl(param);
		}
		Map<String, String> rsMap = bdyActObstyCnslService.getBodyActObstyCnslDtls(param);
		Map<String, String> actMap = bdyActObstyCnslService.getCnslAct(param);
		model.addAttribute("actMap", actMap);
		model.addAttribute("WEIGHT", rsMap.get("WEIGHT"));
		model.addAllAttributes(param);
		return "web/tg/bodyActObstyCnslRegPop";
    }
	
	/**
	 * 운동 목표 설정 변경
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateBodyObstyCnsl.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateBodyObstyCnsl(@ModelAttribute Map param, ModelMap model) throws Exception{
		String chkYn = "Y";
		try{
			param.put("updateGb", "Y");
			bdyActObstyCnslService.updateBodyObstyCnsl(param);
		}catch(Exception e){
			chkYn = "N";
			LOG.debug(e);
		}
		model.addAttribute("chkYn", chkYn);
		return model;
	}
	
	
	/**
	 * 추천 운동 선정 목록 추가
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addRecomExcsInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addRecomExcsInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		String chkYn = "Y";
		try{
			bdyActObstyCnslService.addRecomExcsInfo(param);
		}catch(Exception e){
			chkYn = "N";
			LOG.debug(e);
		}
		model.addAttribute("chkYn", chkYn);
		return model;
	}	
	

	/**
	 * 추천 운동 선정 목록 삭제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delRecomExcsInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> delRecomExcsInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		String chkYn = "Y";
		try{
			bdyActObstyCnslService.deleteRecomExcsInfo(param);
		}catch(Exception e){
			chkYn = "N";
			LOG.debug(e);
		}
		model.addAttribute("chkYn", chkYn);
		
		System.out.println("model ----------------- " + model);
		
		
		return model;
	}	
	
	/**
	 * 추천 운동 선정 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRecomExcsSetList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRecomExcsSetList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = bdyActObstyCnslService.getRecomExcsSetList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 목표심박계산 나이 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getHRCalAge.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getHRCalAge(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = bdyActObstyCnslService.getHRCalAge(param);
		return rsMap;
	}
	
	/**
	 * 목표심박 범위 시간별 심박 분포 그래프 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getHRHourData.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getHRHourData(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsHHeart = trgterInfoMngtService.selHourHeartData(param);		
		rsMap.put("rsHHeart", rsHHeart);
		return rsMap;

	}	
	
	/**
	 * 운동_처방_템플릿 팝업 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getRecomExcsTemplateList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getRecomExcsTemplateList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();			
		List<Map<String, Object>> rsList = bdyActObstyCnslService.getRecomExcsTemplateList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 운동_처방_템플릿 저장 및 수정
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getRecomExcsTemplateUpdate.do", method = RequestMethod.POST)
	public @ResponseBody int getRecomExcsTemplateUpdate(@ModelAttribute Map param, ModelMap model) throws Exception {
		String searchInfo = StringUtil.nvl((String)param.get("bodyPartTempExcsCd"));
		param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		
		int rsInt = bdyActObstyCnslService.getRecomExcsTemplateUpdate(param);	
		return rsInt;
	}
	
	/** 
	 * 운동_처방_템플릿 삭제
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getRecomExcsTemplateDel.do", method = RequestMethod.POST)
	public @ResponseBody int getRecomExcsTemplateDel(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = bdyActObstyCnslService.getRecomExcsTemplateDel(param);
		return rsInt;
	}
	
	/**
	 * 운동_처방_템플릿_상담_저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getRecomExcsTemplateCnslUpdate.do", method = RequestMethod.POST)
	public @ResponseBody int getRecomExcsTemplateCnslUpdate(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = bdyActObstyCnslService.getRecomExcsTemplateCnslUpdate(param);	
		return rsInt;
	}
	
	@RequestMapping(value="/getRecomExcsTempList.do", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> getRecomExcsTempList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = bdyActObstyCnslService.getRecomExcsTempList(param);
		return rsList;
	}
	
	@RequestMapping(value= "/getRecomExcsPreCnsllUpdate.do", method = RequestMethod.POST)
	public @ResponseBody int getRecomExcsPreCnsllUpdate(@ModelAttribute Map param, ModelMap model) throws Exception {		
		int rsInt = bdyActObstyCnslService.getRecomExcsPreCnsllUpdate(param);	
		return rsInt;
	}
	
	@RequestMapping(value="/deleteRecomTempDelOne.do", method=RequestMethod.POST) 
	public @ResponseBody int getRecomExcsTempDelOne(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = bdyActObstyCnslService.getRecomExcsTempDelOne(param);
		return rsInt;
	}
}


