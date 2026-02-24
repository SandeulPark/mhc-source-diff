package kr.or.khealth.smhc.smhcapp.sv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcapp.sv.service.DistributeDeviceService;

/**
 * @Class Name : CommunityController.java
 * @Description : APP 디바이스 배포정보 기준한  컨트롤러
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2020.11.04		김태일	       최초생성
 *
 * @author  theJOIN
 * @since   2020.11.04
 * @version 1.0
 * @see
 * 
 */

@Controller
@RequestMapping(value = "/smhcapp/sv")
public class DistributeDeviceController extends DMultiActionController {

	@Resource(name = "smhcapp.sv.DistributeDeviceService")
	private DistributeDeviceService deviceService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 대상자 디바이스 배포정보 조회
	 * @param USER_ID(필수), SVC_NO(필수)
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selectPymntDeviceInf.do")
	public @ResponseBody Map<String, Object> selectPymntDeviceInf(@ModelAttribute Map<String, Object> param)throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> deviceList  = new ArrayList<Map<String,Object>>();//
		try {
			deviceList = deviceService.selectPymntDeviceInf(param);
			rsMap.put("deviceList", deviceList);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
}
