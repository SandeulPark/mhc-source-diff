package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.st.service.TrgterSttusService;

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
public class TrgterSttusController extends DMultiActionController {

	@Resource(name= "web.st.TrgterSttusService")
	private TrgterSttusService trgterSttusService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 등록 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/trgterRegSttus.do", method=RequestMethod.GET)
	public String trgterRegSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/trgterRegSttus";
	}
	
	/**
	 * 등록 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/trgterRegSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterRegSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.trgterRegSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 대상자 등록 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/trgterRegDtlsSttus.do", method=RequestMethod.POST)
	public String trgterRegDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/st/trgterRegDtlsSttus";
	}

	/**
	 * 대상자 등록 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/trgterRegDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterRegDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.trgterRegDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 서비스참여 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcJoinSttus.do", method=RequestMethod.GET)
	public String svcJoinSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/svcJoinSttus";
	}
	
	/**
	 * 서비스참여 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcJoinSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.svcJoinSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 서비스참여 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcJoinDtlsSttus.do", method=RequestMethod.POST)
	public String svcJoinDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/svcJoinDtlsSttus";
	}
	
	/**
	 * 서비스참여 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcJoinDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.svcJoinDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/

	/**
	 * 건강검진결과 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthExamRsltSttus.do", method=RequestMethod.GET)
	public String healthExamRsltSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/healthExamRsltSttus";
	}
	
	/**
	 * 건강검진결과 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthExamRsltSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthExamRsltSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.healthExamRsltSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 건강검진결과 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthExamRsltDtlsSttus.do", method=RequestMethod.POST)
	public String healthExamRsltDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/healthExamRsltDtlsSttus";
	}
	
	/**
	 * 건강검진결과 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/healthExamRsltDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healthExamRsltDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.healthExamRsltDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**********************************************************************************************************************************************************/
	
	/**
	 * 디바이스 배포 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceDistrbtSttus.do", method=RequestMethod.GET)
	public String deviceDistrbtSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/deviceDistrbtSttus";
	}
	
	/**
	 * 디바이스 배포 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceDistrbtSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> deviceDistrbtSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.deviceDistrbtSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 디바이스 배포 상세 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceDistrbtDtlsSttus.do", method=RequestMethod.POST)
	public String deviceDistrbtDtlsSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/st/deviceDistrbtDtlsSttus";
	}
	
	/**
	 * 디바이스 배포 상세 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/deviceDistrbtDtlsSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> deviceDistrbtDtlsSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.deviceDistrbtDtlsSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 시작일자별 등록 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcBgnDeRegSttus.do", method=RequestMethod.GET)
	public String svcBgnDeRegSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/st/svcBgnDeRegSttus";
	}
	
	/**
	 * 시작일자별 등록 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/svcBgnDeRegSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcBgnDeRegSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.svcBgnDeRegSttusList(param);
		List<Map<String, String>> rsTotalList = trgterSttusService.svcBgnRegSttusTotalCnt(param);

		rsMap.put("rsList", rsList);
		rsMap.put("rsTotalList", rsTotalList); // 상단 전체 총 등록 수
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 참여경로 현황 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value= "/st/trgterCnctSttus.do", method=RequestMethod.GET)
	public String trgterCnctSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return "web/st/trgterCnctSttus";
	}
	
	/**
	 * 참여경로 현황 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/st/trgterCnctSttusList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterCnctSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = trgterSttusService.trgterCnctSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
