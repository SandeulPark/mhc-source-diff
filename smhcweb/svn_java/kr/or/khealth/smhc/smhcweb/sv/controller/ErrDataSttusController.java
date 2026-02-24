package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.ErrDataSttusService;

/**
 * @Class Name : ErrDataSttusController.java
 * @Description : 에러 데이터 현황 정보를 조회하는 컨트롤러 class
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class ErrDataSttusController extends DMultiActionController {

	@Resource(name= "web.sv.ErrDataSttusService")
	private ErrDataSttusService errDataSttusService;

	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 에러 데이터 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/errDataSttus.do", method=RequestMethod.GET)
	public String errDataSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/sv/errDataSttus";
	}

	/**
	 * 에러 데이터 현황 연동 건수별 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/errDataSttusSyncList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> errDataSttusSyncList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = errDataSttusService.errDataSyncList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 에러 데이터 현황 앱 버전별 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/errDataSttusAppVerList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> errDataSttusAppVerList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = errDataSttusService.errDataAppVerList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}


	/**
	 * 에러 데이터 현황 업체별 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/errDataSttusModelList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> errDataSttusModelList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = errDataSttusService.errDataModelList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

    /**
   	 * 에러 데이터 현황 오류 코드별 리스트 조회
   	 * @param
   	 * @return
   	 * @throws Exception
   	 */
   	@RequestMapping(value= "/sv/errDataSttusErrCodeList.do", method= RequestMethod.POST)
   	public @ResponseBody Map<String, Object> errDataSttusErrCodeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
   		Map<String, Object> rsMap = new HashMap<String, Object>();
   		List<Map<String, String>> rsList = errDataSttusService.errDataCodeList(param);

   		rsMap.put("rsList", rsList);
   		rsMap.put("id", param.get("id"));
   		return rsMap;
   	}

}
