package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.st.service.CnslSttusService;

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
public class CnslSttusController extends DMultiActionController {

	@Resource(name= "web.st.CnslSttusService")
	private CnslSttusService cnslSttusService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 신체활동 집중상담 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/bodyActIntensiveCnslSttus.do", method=RequestMethod.GET)
	public String bodyActIntensiveCnslSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/bodyActIntensiveCnslSttus";
	}
	
	/**
	 * 신체활동 집중상담 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/bodyActIntensiveCnslSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> bodyActIntensiveCnslSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.bodyActIntensiveCnslSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 신체활동 집중상담 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/bodyActIntensiveCnslDtlsSttus.do", method=RequestMethod.POST)
	public String bodyActIntensiveCnslDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/st/bodyActIntensiveCnslDtlsSttus";
	}

	/**
	 * 신체활동 집중상담 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/bodyActIntensiveCnslDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> bodyActIntensiveCnslDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.bodyActIntensiveCnslDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 영양 집중상담 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nutriIntensiveCnslSttus.do", method=RequestMethod.GET)
	public String nutriIntensiveCnslSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/nutriIntensiveCnslSttus";
	}
	
	/**
	 * 영양 집중상담 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nutriIntensiveCnslSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> nutriIntensiveCnslSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.nutriIntensiveCnslSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 영양 집중상담 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nutriIntensiveCnslDtlsSttus.do", method=RequestMethod.POST)
	public String nutriIntensiveCnslDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/nutriIntensiveCnslDtlsSttus";
	}
	
	/**
	 * 영양 집중상담 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nutriIntensiveCnslDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> nutriIntensiveCnslDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.nutriIntensiveCnslDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 금연/절주 집중상담 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nosmokTmprrncIntensiveSttus.do", method=RequestMethod.GET)
	public String nosmokTmprrncIntensiveSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/nosmokTmprrncIntensiveSttus";
	}
	
	/**
	 * 금연/절주 집중상담 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/nosmokTmprrncIntensiveSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> nosmokTmprrncIntensiveSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.nosmokTmprrncIntensiveSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/

	/**
	 * 일반상담 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/comnCnslSttus.do", method=RequestMethod.GET)
	public String comnCnslSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/comnCnslSttus";
	}
	
	/**
	 * 일반상담 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/comnCnslSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> comnCnslSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.comnCnslSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 일반상담 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/comnCnslDtlsSttus.do", method=RequestMethod.POST)
	public String comnCnslDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/comnCnslDtlsSttus";
	}
	
	/**
	 * 일반상담 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/comnCnslDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> comnCnslDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.comnCnslDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 월간리포트 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/mthlyHealthRptSttus.do", method=RequestMethod.GET)
	public String mthlyHealthRptSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/mthlyHealthRptSttus";
	}
	
	/**
	 * 월간리포트 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/mthlyHealthRptSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mthlyHealthRptSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.mthlyHealthRptSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 월간리포트 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/mthlyHealthRptDtlsSttus.do", method=RequestMethod.POST)
	public String mthlyHealthRptDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/mthlyHealthRptDtlsSttus";
	}
	
	/**
	 * 월간리포트 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/mthlyHealthRptDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mthlyHealthRptDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = cnslSttusService.mthlyHealthRptDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 월간리포트 상세 현황  목록팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/st/mthlyHealthRptDtlsSttus_pop.do")
	public String mthlyHealthRptDtlsSttus_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/st/mthlyHealthRptDtlsSttus_pop";
	}
	
}
