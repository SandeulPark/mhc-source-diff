package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.DeviceDistrbtMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 배포 관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.11		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.04.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class DeviceDistrbtMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.DeviceDistrbtMngtService")
	private DeviceDistrbtMngtService deviceDistrbtMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 디바이스 배포 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceDistrbtMngt.do", method= RequestMethod.GET)
	public String deviceDistrbtMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		return "web/tg/deviceDistrbtMngt";
	}
	
	/**
	 * 디바이스 배포 건수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceDistrbtCnt.do")
	public @ResponseBody Map<String, Object> getDeviceDistrbtCnt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = deviceDistrbtMngtService.getDeviceDistrbtCnt(param);
		
		return rsMap;
	}
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceDistrbtMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDeviceDistrbtMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = deviceDistrbtMngtService.getDeviceDistrbtMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 디바이스 배포 상세 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceDistrbtDtlsPop.do", method= RequestMethod.GET)
	public String deviceDistrbtDtlsPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("SVC_MNGT_NO", param.get("SVC_MNGT_NO"));
		return "web/tg/deviceDistrbtDtlsPop";
	}
	
	/**
	 * 디바이스 배포 상세 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceDistrbtDtls.do")
	public @ResponseBody Map<String, Object> getDeviceDistrbtDtls(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = deviceDistrbtMngtService.getDeviceDistrbtDtls(param);
		
		return rsMap;
	}
	
	/**
	 * 디바이스 배포 완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateDeviceDistrbt.do")
	public void updateDeviceDistrbt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		deviceDistrbtMngtService.updateDeviceDistrbt(param);
	}
	
}