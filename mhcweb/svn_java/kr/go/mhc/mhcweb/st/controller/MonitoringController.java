package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.st.service.MonitoringService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterSttusController.java
 * @Description : 총괄 관리 시스템 현황 및 통계 메뉴의 대상자현황 하위 메뉴들을 컨트롤하는 컨트롤러
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.21		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.12.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class MonitoringController extends DMultiActionController {

	@Resource(name= "web.st.MonitoringService")
	private MonitoringService monitoringService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 디바이스 이용 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceUtilizationSttus.do", method=RequestMethod.GET)
	public String deviceUtilizationSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/deviceUtilizationSttus";
	}
	
	/**
	 * 디바이스 이용 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceUtilizationSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> deviceUtilizationSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.deviceUtilizationSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 디바이스 이용 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceUtilizationDtlsSttus.do", method=RequestMethod.POST)
	public String deviceUtilizationDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/st/deviceUtilizationDtlsSttus";
	}

	/**
	 * 디바이스 이용 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceUtilizationDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> deviceUtilizationDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.deviceUtilizationDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 건강위험 변화 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthRiskChangeSttus.do", method=RequestMethod.GET)
	public String healthRiskChangeSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/healthRiskChangeSttus";
	}
	
	/**
	 * 건강위험 변화 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthRiskChangeSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthRiskChangeSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.healthRiskChangeSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 건강위험 변화 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthRiskChangeDtlsSttus.do", method=RequestMethod.POST)
	public String healthRiskChangeDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/healthRiskChangeDtlsSttus";
	}
	
	/**
	 * 건강위험 변화 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthRiskChangeDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthRiskChangeDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.healthRiskChangeDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 건강 설문 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthServeySttus.do", method=RequestMethod.GET)
	public String healthServeySttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/healthServeySttus";
	}
	
	/**
	 * 건강 설문 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthServeySttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthServeySttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.healthServeySttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 건강 설문 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthServeyDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthServeyDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.healthServeyDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/

	/**
	 * 주차별 컨텐츠 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/weekContent.do", method=RequestMethod.GET)
	public String weekContent(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/weekContent";
	}
	
	/**
	 * 주차별 컨텐츠 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/weekContentList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> weekContentList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.weekContentList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 보건소 컨텐츠 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthcenterContent.do", method=RequestMethod.GET)
	public String healthcenterContent(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/healthcenterContent";
	}
	
	/**
	 * 보건소 컨텐츠 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthcenterContentList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthcenterContentList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = monitoringService.healthcenterContentList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
}
