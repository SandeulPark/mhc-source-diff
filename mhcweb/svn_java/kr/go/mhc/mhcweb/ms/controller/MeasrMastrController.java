package kr.go.mhc.mhcweb.ms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.ms.service.MeasrMastrService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value="/ms")
public class MeasrMastrController extends DMultiActionController{ 
	
	@Resource(name="ms.measrMastrService")
	private MeasrMastrService measrMastrService;


	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 혈압데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/updatePairDeviceInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> test(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		

			String deviceNm = param.get("EQUIP_CD") != null ?param.get("EQUIP_CD").toString():"";
			if(deviceNm.contains("Mambo")){
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("203B0")){
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("AND")){
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("CareSens")){
				param.put("EQUIP_CLF", "40");
			}			
			rtInt = measrMastrService.updatePairDeviceInfo(param);
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("updateCnt", rtInt);

		return rsMap;
	}

	/**
	 * 혈압데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertBloodPress.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBloodPress(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			
			param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			param.put("MEASR_MODEL_NM",""); 	//모델명			
			
			rsMap = measrMastrService.insertBloodPress(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 혈당데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertBloodSugar.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBloodSugar(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;		
		String[] bloodSugar = req.getParameterValues("BLOOD_SUGAR");
		
		try{		
			param.put("MEASR_TRGT_CLF", "40"); //측정대상구분_혈당 : MS001_40  
			param.put("MEASR_TRGT_DTLS_CLF", "41"); //MS002   41 : 공복  42 : 식후 
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			param.put("MEASR_MODEL_NM",""); 	//모델명			
			
			rsMap = measrMastrService.insertBloodSugar(param);		
			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	
	/**
	 * 활동량 Temp저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertActDta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertActTemp(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			rtInt = measrMastrService.insertActDta(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);

		return rsMap;
	}
	
	/**
	 * 활동량 심박수 Temp저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertHRDta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertHRTemp(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			rtInt = measrMastrService.insertHRDta(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);

		return rsMap;
	}
	
	/**
	 * 체성분 저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertBodyComp.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBodyCompTemp(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			
			param.put("MEASR_TRGT_CLF", "20"); 	//측정대상구분_BMI : MS001_80  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			param.put("MEASR_MODEL_NM",""); 	//모델명	
			rsMap = measrMastrService.insertBodyComp(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);

		return rsMap;
	}
	
	/**
	 * 활동량 프로시저 호출
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/callProcActIns.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> callProcActIns(@ModelAttribute Map<String,Object> param, ModelMap model){ 
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		String rtStr = "";
		try{		
			rtStr = measrMastrService.callProcActIns(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("rtStr", rtStr);

		return rsMap;
	}
	
	@RequestMapping( value="/searchSerialNo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> searchSerialNo(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			
			rsMap = measrMastrService.searchSerialNo(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	

	
	
}
