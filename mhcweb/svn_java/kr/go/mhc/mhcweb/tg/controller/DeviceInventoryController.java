package kr.go.mhc.mhcweb.tg.controller;

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

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.DeviceMngtService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 재 관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.12.08		이태윤			최초생성
 *
 * @author thejoin
 * @since 2021.12.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class DeviceInventoryController extends DMultiActionController {
	
	@Resource(name= "web.tg.DeviceMngtService")
	private DeviceMngtService deviceMngtService;
	
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
//	@RequestMapping(value= "/deviceStock.do", method= RequestMethod.GET)
//	public String deviceDistrbtMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
//		
//		return "web/tg/deviceDistrbtMngt";
//	}
		
	
	/**
	 * 디바이스 COUNT
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceCnt.do")
	public @ResponseBody Map<String, Object> getDeviceDistrbtCnt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = deviceMngtService.getDeviceCnt(param);
		
		return rsMap;
	}
	
	/**
	 * 디바이스 재고 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceStockList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDeviceStrockList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = deviceMngtService.getDeviceStockList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 디바이스 재고 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceInventoryList.do", method= RequestMethod.GET)
	public String deviceInventory(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		return "web/tg/deviceInventoryList";
	}
	
	/**
	 * 디바이스 등록 상세 팝업 호출3
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/addDevicePop.do", method= RequestMethod.GET)
	public String addDevicePop(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = deviceMngtService.getDeviceMenuList(param);
		model.addAttribute("rsList", rsList);
		return "web/tg/deviceAddPop";
	}
	
	/**
	 * 디바이스 메뉴조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectMeunStts.do", method= RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>selectMeunStts(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> rsList = null;
		if(param.get("ORDER").equals("DEVICE_STTS")) {
			rsList = deviceMngtService.getDeviceVenderList(param);			
		}else if(param.get("ORDER").equals("VENDOR")) {
			rsList = deviceMngtService.getDeviceSelectModelList(param);
		}		
		
		return rsList;
	}
	
	/**
	 * 디바이스 등록
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/addDevice.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> addDevice(@ModelAttribute Map param, ModelMap model) throws Exception {
		String jsonStr = (String) param.get("result");
		String datSet = jsonStr.replaceAll("&quot;", "\"");     //UPDATE 데이터 처리		
		JSONArray array = JSONArray.fromObject(datSet);
		Map<String, Object> rsMap = new HashMap<String, Object>();
		for(int i=0; i<array.size(); i++){
			
	        JSONObject obj = (JSONObject)array.get(i);
	        param.put("EQUIP_CLF", obj.get("EQUIP_CLF"));
	        param.put("SERIAL_NO", obj.get("SERIAL_NO"));
	        param.put("MODEL_NM", obj.get("MODEL_NM"));
	        param.put("EQUIP_CD", obj.get("EQUIP_CD"));	        
	        param.put("EQUIP_STTUS", obj.get("EQUIP_STTUS"));
	        param.put("EQUIP_PUCHAS_DE", obj.get("EQUIP_PUCHAS_DE"));	       	        	       
	        
	        	            			
	        deviceMngtService.addDevice(param);	
	        
	    }
		return rsMap;
	}
	
	/**
	 * 디바이스 반납
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/returnDevice.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> deviceReturn(@ModelAttribute Map param, ModelMap model) throws Exception {
		String jsonStr = (String) param.get("result");
		String datSet = jsonStr.replaceAll("&quot;", "\"");     //UPDATE 데이터 처리		
		JSONArray array = JSONArray.fromObject(datSet);
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			for(int i=0; i<array.size(); i++){

		        JSONObject obj = (JSONObject)array.get(i);
		        if(obj.get("UPDATE_TYPE").equals("DTLS_UPDATE")) {//상세업데이트
	    
			        param.put("EQUIP_CLF_NM", obj.get("EQUIP_CLF_NM"));
			        param.put("SERIAL_NO", obj.get("SERIAL_NO"));
			        param.put("EQUIP_CD", obj.get("EQUIP_CD"));
			        param.put("USER_ID", obj.get("firstUserId"));
			        param.put("EQUIP_STTUS", obj.get("EQUIP_STTUS"));
			        param.put("EQUIP_PUCHAS_DE", obj.get("EQUIP_PUCHAS_DE"));
			        
		
			        deviceMngtService.returnDevice(param);
			        
		        }else {
			        if(obj.get("USER_ID") == null) {
			        	rsMap.put("result", "02");
			        	throw new Exception();
					}
			        param.put("EQUIP_CLF_NM", obj.get("EQUIP_CLF_NM"));
			        param.put("SERIAL_NO", obj.get("SERIAL_NO"));
			        param.put("EQUIP_CD", obj.get("EQUIP_CD"));
			        param.put("USER_ID", obj.get("USER_ID"));
		
		
			        deviceMngtService.returnDevice(param);
			    }
			}
			rsMap.put("result", "01");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rsMap;
	}
	
	/**
	 * 디바이스 지급
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/pymntDevice.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object>  pymntDevice(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			
			deviceMngtService.pymntDevice(param);	
		} catch (Exception e) {
			rsMap.put("result", "02");
			e.printStackTrace();
		}
		return rsMap;

	}
	
	/**
	 * 디바이스 재고 상세 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/deviceInventoryPop.do", method= RequestMethod.GET)
	public String deviceInventoryPop(@ModelAttribute Map param, ModelMap model ,HttpServletRequest req) throws Exception {		

		String EQUIP_CD = (String) req.getParameter("EQUIP_CD"); 
		EQUIP_CD= new String(EQUIP_CD.getBytes("8859_1"), "UTF-8");
		
		String SERIAL_NO = (String) req.getParameter("SERIAL_NO");
		SERIAL_NO= new String(SERIAL_NO.getBytes("8859_1"), "UTF-8");
		
		System.out.println(EQUIP_CD);
		System.out.println(SERIAL_NO);
		
		param.put("EQUIP_CD", EQUIP_CD);
		param.put("SERIAL_NO", SERIAL_NO);		

		Map<String, Object> reMap=deviceMngtService.getDeviceInvenDtls(param);
		model.addAttribute("reMap", reMap);
		
		return "web/tg/deviceInventoryPop";
	}
	
	/**
	 * 디바이스 재고 상세 팝업 대상자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value= "/deviceTargetList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object>deviceTargetList(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> rsList = deviceMngtService.getDeviceTargetList(param);		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("rsList", rsList);
			
		return rsMap;
	}
	
}
