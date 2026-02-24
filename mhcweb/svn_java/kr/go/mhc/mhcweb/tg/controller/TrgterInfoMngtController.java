package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import kr.go.mhc.mhcweb.sv.service.CnslReqMngService;
import kr.go.mhc.mhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.go.mhc.mhcweb.sv.service.IntensiveCnslMngtService;
import kr.go.mhc.mhcweb.tg.service.DeviceDistrbtMngtService;
import kr.go.mhc.mhcweb.tg.service.HealthMngtCnslService;
import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterInfoMngtController.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01		이은주			최초생성
 * @	2016.12.09		이은주		설문 구분값 수정.
 *
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class TrgterInfoMngtController extends DMultiActionController {

	@Resource(name= "web.tg.TrgterInfoMngtService")
	private TrgterInfoMngtService trgterInfoMngtService;
	
	@Resource(name= "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;
	
	@Resource(name = "web.sv.CnslReqMngService")
	private CnslReqMngService cnslReqMngService;
	
	@Resource(name = "web.sv.IntensiveCnslMngtService")
	private IntensiveCnslMngtService intensiveCnslMngtSerivce;	
	
	@Resource(name= "web.tg.DeviceDistrbtMngtService")
	private DeviceDistrbtMngtService deviceDistrbtMngtService;
	
	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자정보관리 대상자 정보관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInfoMngt.do")
	public String tgrterInfoMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/tg/trgterInfoMngt";
	}
	
	/**
	 * 대상자정보관리 대상자 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInfoMngtList.do")
	public @ResponseBody Map<String, Object> trgterInfoMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String searchInfo = StringUtil.nvl((String)param.get("REQ_SEARCH_INFO"));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		List<Map<String, Object>> rsList = trgterInfoMngtService.trgterInfoMngtList(param);

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 대상자 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterInfoMngtDtls.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterInfoMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = trgterInfoMngtService.trgterInfoMngtDtls(param);
		param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		Map<String, Object> deviceMap = deviceDistrbtMngtService.getDeviceDistrbtDtls(param);
		
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("deviceMap", deviceMap);
		return model;
	}
	
	/**
	 * 대상자정보관리 대상자 저장
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateTrgterInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, String> updateTrgterInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		trgterInfoMngtService.updateTrgterInfo(param);
		Map<String, String> rsMap = new HashMap<String, String>();
		rsMap.put("succ", "저장 완료"); 
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 신체활동 탭 활동목표
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterActList.do")
	public @ResponseBody Map<String, Object> selTrgterActList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListAct = trgterInfoMngtService.selTrgterActList(param);
		
		rsMap.put("rsList", rsListAct);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 신체활동 탭 날짜별, 요일별
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterActDEList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterActDEList(@ModelAttribute Map<String, Object> param, ModelMap	model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		//System.out.println("param================================= " + param);		
		
		
		List<Map<String, Object>> rsListDE = trgterInfoMngtService.selTrgterActDEList(param);
		
		//System.out.println("rsListDE >>>>>>>>>> " + rsListDE);
		
		
		List<Map<String, Object>> rsListDY = trgterInfoMngtService.selTrgterActDYList(param);
		
		//System.out.println("rsListDY >>>>>>>>>> " + rsListDY);		
		Map<String, Object> rsMapDEAVG = trgterInfoMngtService.selTrgterActDEAVG(param);
		
		//System.out.println("rsMapDEAVG >>>>>>>>>> " + rsMapDEAVG);			

		
		rsMap.put("rsListDE", rsListDE);
		rsMap.put("rsListDY", rsListDY);
		rsMap.put("rsMapDEAVG", rsMapDEAVG);
		
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 신체활동 탭  구분, 평균
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterTotActCnt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterTotActCnt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selTrgterTotActCnt(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	
	/**
	 * 대상자정보관리 신체활동 탭 주차별 현황
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterActDYList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListWK = trgterInfoMngtService.selTrgterWKInfo(param);
		rsMap.put("rsList", rsListWK);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 체성분 탭 체중목표(그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterWeightList.do")
	public @ResponseBody Map<String, Object> selTrgterWeightList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListWeight = trgterInfoMngtService.selTrgterWeightList(param);
		rsMap.put("rsList", rsListWeight);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 체성분 탭 측정값 변화(일자별)(차트)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterWeightDEList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterWeightDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListWeightDE = trgterInfoMngtService.selTrgterWeightDEList(param);
		
		rsMap.put("rsListWeightDE", rsListWeightDE);
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 체성분 탭 체성분 시작, 종료 비교
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterWeightSTED.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterWeightSTED(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapSTED = trgterInfoMngtService.selTrgterWeightSTED(param);
		return rsMapSTED;
	}
	
	/**
	 * 대상자정보관리 체성분 탭 서비스 주차별 현황(그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterWeightWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterWeightWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListWK = trgterInfoMngtService.selTrgterWeightWKInfo(param);
		rsMap.put("rsList", rsListWK);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈압 최근 측정현황
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldPressList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldPressList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapBldPress = trgterInfoMngtService.selTrgterBldPressList(param);
		return rsMapBldPress;
	}
	
	/**
	 * 대상자정보관리 혈압 측정값 변화 (차트)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldPressDEList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldPressDEList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListBldPressDE = trgterInfoMngtService.selTrgterBldPressDEList(param);
		rsMap.put("rsListBldPressDE", rsListBldPressDE);
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈압 기간 평균, 최초 측정 비교
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldPressSTAVG.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldPressSTAVG(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapBldPressSTAVG = trgterInfoMngtService.selTrgterBldPressSTAVG(param);
		return rsMapBldPressSTAVG;
	}
	
	/**
	 * 대상자정보관리 혈압 서비스 주차별 현황
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterBldWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListBldWKInfo = trgterInfoMngtService.selTrgterBldWKInfo(param);
		rsMap.put("rsList", rsListBldWKInfo);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈압 이상 측정정보						20161012 이은주 추가
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/pressDisorderExamInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> disorderExamInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListpressDisorderExamInfo = trgterInfoMngtService.selPressDisorderExamInfo(param);
		rsMap.put("rsList", rsListpressDisorderExamInfo);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈압 및 혈당 이상 측정정보 처리내역저장 팝업창 호출				20161012 이은주 추가.
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/pressProcContSave_pop.do", method= RequestMethod.GET)
	public String pressProcContSave_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("MEASR_SN", param.get("MEASR_SN"));
		model.addAttribute("PROC_CONT", param.get("PROC_CONT"));
		return "web/tg/trgterInfoMngtPop3";
	}
	
	/**
	 * 대상자정보관리 혈압 및 혈당 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 			20161012 이은주 추가.
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateDisorderExamProc.do", method= RequestMethod.POST)
	public @ResponseBody void updateDisorderExamProc(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		trgterInfoMngtService.updateDisorderExamProc(param);
	}

	/**
	 * 대상자정보관리 혈당 최근 측정현황 (테이블)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldSugarList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldSugarList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapBldSugar = trgterInfoMngtService.selTrgterBldSugarList(param);
		return rsMapBldSugar;
	}
	
	/**
	 * 대상자정보관리 혈당 측정값 변화 (차트)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldSugarDEList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldSugarDEList(@ModelAttribute Map<String, Object> param, ModelMap model)	throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListBldSugarDE = trgterInfoMngtService.selTrgterBldSugarDEList(param);
		rsMap.put("rsListBldSugarDE", rsListBldSugarDE);
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈당 기간 평균, 최초 측정 비교 (테이블)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldSugarSTAVG.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterBldSugarSTAVG(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapBldSugarSTAVG = trgterInfoMngtService.selTrgterBldSugarSTAVG(param);
		return rsMapBldSugarSTAVG;
	}
	
	/**
	 * 대상자정보관리 혈당 서비스 주차별 현황 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterBldSugarWKInfo.do")
	public @ResponseBody Map<String, Object> selTrgterBldSugarWKInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListBldSugarWK = trgterInfoMngtService.selTrgterBldSugarWKInfo(param);
		rsMap.put("rsList", rsListBldSugarWK);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈당 이상 측정정보				20161012 이은주 추가
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sugarDisorderExamInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> sugarDisorderExamInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListSugarDisorderExamInfo = trgterInfoMngtService.selSugarDisorderExamInfo(param);
		rsMap.put("rsList", rsListSugarDisorderExamInfo);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 혈압 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 			20161012 이은주 추가.
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateSugarProc.do", method= RequestMethod.POST)
	public @ResponseBody void updateSugarProc(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
	}
	
	/**
	 * 대상자정보관리 검진기록 검진결과 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterExamRsltList.do")
	public @ResponseBody Map<String, Object> selTrgterExamRsltList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListExamRsList = trgterInfoMngtService.selTrgterExamRsltList(param);
		rsMap.put("rsList", rsListExamRsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 검진기록 검사결과 (테이블)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterExamRslt.do")
	public @ResponseBody Map<String, Object> selTrgterExamRslt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapExamRslt = trgterInfoMngtService.selTrgterExamRslt(param);
		return rsMapExamRslt;
	}
	
	/**
	 * 대상자정보관리 설문기록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/serveyResultView.do", method= RequestMethod.POST)
	public String serveyResultView(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, String> serveyMap = healthMngtCnslService.selectServeyList(param);
		
//		param.put("MONTH_CD", "L1");
		List<Map<String, String>> preServeyList = healthMngtCnslService.selectServeyResultList(param);
		
//		param.put("SERVEY_TYPE", "10");
//		param.put("MONTH_CD", "L2");
		List<Map<String, String>> lifeHabitList = healthMngtCnslService.selectServeyResultList(param);
		
//		param.put("SERVEY_TYPE", "20");
//		param.put("MONTH_CD", "L3");
		List<Map<String, String>> satisfList = healthMngtCnslService.selectServeyResultList(param);
		
		
		model.addAttribute("serveyMap", serveyMap);
		model.addAttribute("preServeyList", preServeyList);
		model.addAttribute("lifeHabitList", lifeHabitList);
		model.addAttribute("satisfList", satisfList);
		
		return "web/tg/serveyResultViewPop";
			
	}
	
	/**
	 * 대상자정보관리 서비스참여 탭 건강정보 측정 상세
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selTrgterSvcHealthInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selTrgterSvcHealthInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsListSvcHealthInfo = trgterInfoMngtService.selTrgterSvcHealthInfo(param);
		Map<String, Object> rsMapPracRate = trgterInfoMngtService.selTrgterSvcHealthInfoPracRate(param);
		rsMap.put("rsListSvcHealthInfo", rsListSvcHealthInfo);
		rsMap.put("rsMapPracRate", rsMapPracRate);
		return rsMap;
		
	}
	
	/**
	 * 대상자정보관리 상담 탭 방문상담 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/visitCnslList.do")
	public @ResponseBody Map<String, Object> visitCnslList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectVisitCnslList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 상담 탭 집중상담 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/focusCnslList.do")
	public @ResponseBody Map<String, Object> focusCnslList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectFocusCnslList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 상담 탭 일반상담 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/generalCnslList.do")
	public @ResponseBody Map<String, Object> generalCnslList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectGeneralCnslList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 상담 탭 실시간상담 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/realtimeCnslList.do")
	public @ResponseBody Map<String, Object> realtimeCnslList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectRealtimeCnslList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 상담 탭 일반상담 상담확인 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/generalCnslPop.do")
	public String generalCnslPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
//		model.addAttribute("cnslSn", param.get("cnslSn"));
//		model.addAttribute("cnslSttus", param.get("cnslSttus"));			//20161017 이은주. 저장 및 게시 버튼 활성화/비활성화를 위한 값 구분.
		model.addAllAttributes(param);	//20161026 윤봉훈 - 화면에서 전달하는 파람 모두 전달.
		return "web/tg/trgterInfoMngtPop";	
	}
	
	/**
	 * 대상자정보관리 상담 탭 일반상담 상담확인 팝업 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getGeneralCnsl.do")
	public @ResponseBody Map<String, Object> getGeneralCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = trgterInfoMngtService.selectGeneralCnsl(param);
		return rsMap;		
	}
	
	@RequestMapping(value="/chkGeneralCnsl.do")
	public @ResponseBody Map<String, Object> chkGeneralCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = trgterInfoMngtService.selectGeneralCnslChk(param);
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 상담 탭 일반상담 상담확인 팝업 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateGeneralCnsl.do")
	public void updateGeneralCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		trgterInfoMngtService.updateGeneralCnsl(param);
	}
	
	/**
	 * 실시간 상담 상담조회 팝업 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/realtimeCnslPop.do")
	public String realtimeCnslPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		String schsel = (String) param.get("schSel");
		String req = (String) param.get("req");
		String user_id = (String) param.get("USER_ID");
		String sel = (String) param.get("schSel");
		String seq = (String) param.get("CHAT_SN");

		String trgtChk = (String) param.get("trgtChk");
		String edtYn = (String) param.get("edtYn");
		
		if("Y".equals(trgtChk) && ("Y".equals(edtYn) || "P".equals(edtYn))){
			if("req".equals(req)){
				cnslReqMngService.cnslChatMasterUp(param);
				cnslReqMngService.cnslCnslerInsert(param);
				cnslReqMngService.realtimeCnslIngUpdate(param);
				List<Map<String, String>> rsList = cnslReqMngService.cnslInquireCont(param);
				model.addAttribute("rsList",rsList);
			}else if("ing".equals(req)){
				List<Map<String, String>> rsList = cnslReqMngService.cnslInquireCont(param);
				model.addAttribute("rsList",rsList);
			}else{
				int getSeq =  cnslReqMngService.getChatSeq(param);
				param.put("CHAT_SN", getSeq);
				cnslReqMngService.chatMasterInsert(param);
				cnslReqMngService.chatCnslerInsert(param);
				cnslReqMngService.chatTrgterInsert(param);
			}
		}else{
			if("ing".equals(req)){
				List<Map<String, String>> rsList = cnslReqMngService.cnslInquireCont(param);
				model.addAttribute("rsList",rsList);
			}
		}
		
		Map<String, String> rsMap = cnslReqMngService.getTrgtInfo(param);
		model.addAttribute("rsMap", rsMap);
		model.addAllAttributes(param);
		return "web/tg/trgterInfoMngtPop2";
	}
	
	/**
	 * 중도탈락 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDrop.do", method= RequestMethod.POST)
	public void updateDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		trgterInfoMngtService.updateDrop(param);
	}
	
	/**
	 * 중도탈락 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectDrop.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapDrop = trgterInfoMngtService.selectDrop(param);
		return rsMapDrop;
	}
	
	/**
	 * 중도탈락 취소
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelDrop.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapPreDropInfo = trgterInfoMngtService.cancelDrop(param);
		return rsMapPreDropInfo;
	}
	
	/**
	 * 2017.02.23 이태석 추가
	 * 대상자정보관리 활동량 일자별 현황 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/dayActDataList.do")
	public @ResponseBody Map<String, Object> getDayActDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> dayActData = trgterInfoMngtService.getDayActDataList(param);
		rsMap.put("rsList", dayActData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 2017.02.23 이태석 추가
	 * 대상자정보관리 체성분 일자별 현황 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/dayBodyCompDataList.do")
	public @ResponseBody Map<String, Object> getDayBodyCompDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> dayBodyCompData = trgterInfoMngtService.getDayBodyCompDataList(param);
		rsMap.put("rsList", dayBodyCompData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 2017.02.23 이태석 추가
	 * 대상자정보관리 혈압 일자별 현황 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/dayBloodPressDataList.do")
	public @ResponseBody Map<String, Object> getDayBloodPressDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> dayBloodSugarData = trgterInfoMngtService.getDayBloodPressDataList(param);
		rsMap.put("rsList", dayBloodSugarData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 2017.02.23 이태석 추가
	 * 대상자정보관리 혈당 일자별 현황 (그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/dayBloodSugarDataList.do")
	public @ResponseBody Map<String, Object> getDayBloodSugarDataList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> dayBloodSugarData = trgterInfoMngtService.getDayBloodSugarDataList(param);
		rsMap.put("rsList", dayBloodSugarData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 식사일기 탭
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealDiaryList.do")
	public @ResponseBody Map<String, Object> getList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> mealDiary = intensiveCnslMngtSerivce.getMealDiaryList(param);				
		rsMap.put("mealDiary", mealDiary);	
		return rsMap;
	}

	
	/**
	 * 대상자정보관리 심박수 탭 심박목표(그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selHeartGoal.do")
	public @ResponseBody Map<String, Object> selHeartGoal(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selHeartGoal(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자정보관리 심박수 탭 목표심박달성률(그리드)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selHeartAchiRate.do")
	public @ResponseBody Map<String, Object> selHeartAchiRate(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selHeartAchiRate(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}	
	
	/**
	 * 대상자정보관리 심박수 탭 일자별 심박측정정보 변화(차트)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selDayHeartData.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selDayHeartData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsDHeart = trgterInfoMngtService.selDayHeartData(param);
		
		rsMap.put("rsDHeart", rsDHeart);
		return rsMap;
	}	
	
	/**
	 * 대상자정보관리 심박수 탭 시간대별 심박측정정보 변화(차트)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selHourHeartData.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selHourHeartData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsHHeart = trgterInfoMngtService.selHourHeartData(param);
		
		rsMap.put("rsHHeart", rsHHeart);
		return rsMap;
	}		
	
	/**
	 * 대상자 모니터리 메모 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/trgterInfoMngtMonitPop.do", method=RequestMethod.GET)
	public String trgterInfoMngtMonitPop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/tg/trgterInfoMngtMonitPop";
	}
	
	/**
	 * 대상자 모니터링 메모 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/trgterMemoDtls.do")
	public @ResponseBody Map<String, Object> trgterMemoDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectMemoDtls(param);
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 대상자 모니터링 메모 신규 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trgterMemoInsert.do")
	public @ResponseBody Map<String, Object> trgterMemoInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		
		try{
			trgterInfoMngtService.insertMemo(param);
		}catch(Exception e){
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	@RequestMapping( value="/historyExcelPop.do")
	public String historyExcel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/tg/historyExcelDown";
	}
	
	@RequestMapping(value ="/historyExcelDown.do")
	public @ResponseBody Map<String, Object> historyExcelDown(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		return rsMap;
	}
	
	
	/**
	 * 2018.06.19 유준영 추가
	 * 대상자 이력정보활동량 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterMeasrInfoExcelAct.do")
	public @ResponseBody Map<String, Object> getTrgterMeasrInfoExcelAct(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> measrInfoData = trgterInfoMngtService.getTrgterMeasrInfoExcelAct(param);
		rsMap.put("rsList",measrInfoData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 2018.06.19 유준영 추가
	 * 대상자 이력정보체성분 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterMeasrInfoExcelBodyComp.do")
	public @ResponseBody Map<String, Object> getTrgterMeasrInfoExcelBodyComp(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> measrInfoData = trgterInfoMngtService.getTrgterMeasrInfoExcelBodyComp(param);
		rsMap.put("rsList",measrInfoData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 2018.06.19 유준영 추가
	 * 대상자 이력정보혈압 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterMeasrInfoExcelBloodPress.do")
	public @ResponseBody Map<String, Object> getTrgterMeasrInfoExcelBloodPress(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> measrInfoData = trgterInfoMngtService.getTrgterMeasrInfoExcelBloodPress(param);
		rsMap.put("rsList",measrInfoData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 2018.06.19 유준영 추가
	 * 대상자 이력정보혈당 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterMeasrInfoExcelBloodSugar.do")
	public @ResponseBody Map<String, Object> getTrgterMeasrInfoExcelBloodSugar(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> measrInfoData = trgterInfoMngtService.getTrgterMeasrInfoExcelBloodSugar(param);
		rsMap.put("rsList",measrInfoData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/trgterCbSearchPop.do")
	public String trgterCombinedSearchPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/tg/trgterCbSearchPop";
	}
	
	@RequestMapping(value="/trgterCbSearchList.do")
	public @ResponseBody Map<String, Object> trgterCbSearchList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.trgterCbSearchList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	@RequestMapping(value="/trgterCombinedInfo.do")
	public String trgterCombinedInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> userInfo = trgterInfoMngtService.trgterInfoMngtDtls(param);
		param.put("SVC_MNGT_NO", userInfo.get("SVC_MNGT_NO"));
		List<Map<String, Object>> healthExam = intensiveBodyActObstyCnslService.getHelthExam(param);
		List<Map<String, Object>> bodyGList = trgterInfoMngtService.bodyGoalList(param);
		List<Map<String, Object>> nutriGList = trgterInfoMngtService.nutriGoalList(param);
		
		param.put("trgtSql", "Goal");
		List<Map<String, Object>> goalList = trgterInfoMngtService.trgterCbInfo(param);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("healthExam", healthExam);
		model.addAttribute("bodyGList", bodyGList);
		model.addAttribute("nutriGList", nutriGList);
		model.addAttribute("goalList", goalList);
		return "web/tg/trgterCombinedInfo";
	}
	
	@RequestMapping(value="/trgterCbInfo.do")
	public @ResponseBody Map<String, Object> trgterCbInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.trgterCbInfo(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	@RequestMapping(value="/trgterDropPop.do")
	public String trgterDropPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/tg/trgterDropPop";
	}
	
	/**
	 * 대상자 이미지 확대 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterImg.do", method = RequestMethod.GET)
	public String trgterImg(@ModelAttribute Map param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		model.addAttribute("trgterImgUrl", param.get("trgterImgUrl"));
		return "web/tg/trgterImg";
	}
}
