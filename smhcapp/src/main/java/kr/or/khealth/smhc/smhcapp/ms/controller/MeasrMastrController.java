package kr.or.khealth.smhc.smhcapp.ms.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcapp.ms.service.MeasrMastrService;

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
	 * 활동량 관련 테이블 데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertActivityLink.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertActivity(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			int rsInt  = measrMastrService.insertActivityLink(getArrayParamToList(param)); 
			if(rsInt>0){
				rsMap.put("chkYn", "Y");
			}else{
				rsMap.put("chkYn", "N");
			}
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

		return rsMap;
	}
	
	/**
	 * 페어링 완료정보 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/updatePairDeviceInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updatePairDeviceInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{
			param.put("APP_VERSION", ((String)param.get("APP_VERSION")).replaceAll("[-_].*$", ""));
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
	@RequestMapping( value="/insertBloodPressLink.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBloodPressLink(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			int rsInt  = measrMastrService.insertBloodPressLink(getArrayParamToList(param)); 
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

		return rsMap;
	}
	
	/**
	 * 디바이스 마지막 데이터 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectLastData.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectLastData(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
						
		try{			
			rsMap =  measrMastrService.selectLastData(param);			
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

		return rsMap;
	}
	
	/**
	 * 혈당데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertBloodSugarLink.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBloodSugarLink(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			int rsInt  = measrMastrService.insertBloodSugarLink(getArrayParamToList(param)); 
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

		return rsMap;
	}
	
	/**
	 * 혈압 수동입력 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/insertBloodSugarManu.do")
	public @ResponseBody Map<String,Object> insertBloodSugarManu(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			if("A".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF", "M");
			}
			rsMap  = measrMastrService.insertBloodSugarManu(param); 
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			LOG.debug(e);			
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	
	/**
	 * 체성분 데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertBodyCompLink.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBodyCompLink(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			int rsInt  = measrMastrService.insertBodyCompLink(getArrayParamToList(param)); 
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

		return rsMap;
	}
	
	/**
	 * 수면 데이터 인서트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertSleepLink.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertSleepLink(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			int rsInt  = measrMastrService.insertSleepLink(getArrayParamToList(param)); 
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}

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
		try{		
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			
			rsMap = measrMastrService.insertBloodPress(param);
			rsMap.putAll(param);
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	
	
	/**
	 * 혈압데이터 업데이트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateBloodPress.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateBloodPress(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{		
			
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패	
			
			rsMap = measrMastrService.updateBloodPress(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 혈압데이터 삭제
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/deleteBloodPress.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteBloodPress(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{		

			rsMap = measrMastrService.deleteBloodPress(param);			
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
		try{		
			param.put("MEASR_TRGT_CLF", "40"); //측정대상구분_혈당 : MS001_40  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패	
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			
			rsMap = measrMastrService.insertBloodSugar(param);
			rsMap.putAll(param);
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 혈당데이터 업데이트
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateBloodSugar.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateBloodSugar(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{		
			param.put("MEASR_TRGT_CLF", "40"); //측정대상구분_혈당 : MS001_40  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패		
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			
			rsMap = measrMastrService.updateBloodSugar(param);		
			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 혈당데이터 삭제
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/deleteBloodSugar.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteBloodSugar(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{		
			
			rsMap = measrMastrService.deleteBloodSugar(param);		
			
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
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
				param.put("MEASR_MODEL_NM","Mambo");
			}
			if(param.get("inAppData") != null){
				String str = param.get("inAppData").toString();
				List<Map<String,String>> list = StringUtil.makeStringToIterator(str.replaceAll("\\}\\,\\{", "^"), "^", ",", ":");
				param.put("insList", list);
			}
			rtInt = measrMastrService.insertActDta(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);
		rsMap.put("insTableNm", param.get("insTableNm"));

		return rsMap;
	}
	
	/**
	 * 활동량 심박수 Temp저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertHRArrDta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertHRArrDta(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			if(param.get("inAppData") != null){
				String str = param.get("inAppData").toString();
				List<Map<String,String>> list = StringUtil.makeStringToIterator(str.replaceAll("\\}\\,\\{", "^"), "^", ",", ":");
				param.put("insList", list);
			}
			rtInt = measrMastrService.insertHRArrDta(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);
		rsMap.put("insTableNm", param.get("insTableNm"));

		return rsMap;
	}

	/**
	 * 활동량 심박수 Temp저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertHRDta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertHRDta(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		try{		
			if(param.get("inAppData") != null){
				String str = param.get("inAppData").toString();
				List<Map<String,String>> list = StringUtil.makeStringToIterator(str.replaceAll("\\}\\,\\{", "^"), "^", ",", ":");
				param.put("insList", list);
			}
			rtInt = measrMastrService.insertHRDta(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", "0");
//		rsMap.put("insertCnt", rtInt);
		rsMap.put("insTableNm", param.get("insTableNm"));

		return rsMap;
	}
	
	/**
	 * Mambo 2 운동데이터 관련 Temp저장
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping( value="/runningData.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> runningData(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = -1;
		String insTableNm ="";
		try{		

			if(param.get("insTableNm")!=null&&!"".equals(param.get("insTableNm"))){
				insTableNm = param.get("insTableNm").toString();
			
				if(param.get("inAppData") != null){
					String str = param.get("inAppData").toString();
					List<Map<String,String>> list = StringUtil.makeStringToIterator(str.replaceAll("\\}\\,\\{", "^"), "^", ",", ":");
					param.put("insList", list);
					
					if("TN_MS_RUNNING_STATUS_DTA".equals(insTableNm)){
						rtInt = measrMastrService.insertRunningStatusDta(param);		
					}else if("TN_MS_RUNNING_HR_ARR_DTA".equals(insTableNm)){
							rtInt = measrMastrService.insertRunningHRArrDta(param);	
					}else if("TN_MS_RUNNING_HR_DTA".equals(insTableNm)){
						rtInt = measrMastrService.insertRunningHRDta(param);	
					}else if("TN_MS_RUNNING_CALORIE_ARR_DTA".equals(insTableNm)){
						rtInt = measrMastrService.insertRunningCalorieArrDta(param);	
					}else if("TN_MS_RUNNING_CALORIE_DTA".equals(insTableNm)){
						rtInt = measrMastrService.insertRunningCalorieDta(param);	
					}					
					
					chkYn = "Y";
				}			
			}
			
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);
		rsMap.put("insTableNm", param.get("insTableNm"));

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
			
			if(!"M".equals(param.get("AUTO_MANU_CLF"))){
				param.put("AUTO_MANU_CLF","A");
			}
			param.put("MEASR_TRGT_CLF", "20"); 	//측정대상구분_BMI : MS001_80  
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			measrMastrService.insertBodyComp(param);
			rsMap.putAll(param);
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);

		return rsMap;
	}
	
	/**
	 * 체성분 수동입력 저장
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping( value="/insertBodyCompManu.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertBodyCompManu(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			rsMap = measrMastrService.insertBodyCompManu(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug(e);
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 체성분 수동입력 삭제
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping( value="/deleteBodyCompManu.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteBodyCompManu(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			rsMap = measrMastrService.deleteBodyCompManu(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug(e);
		}
		rsMap.put("chkYn", chkYn);
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
		try{		
			
			rsMap = measrMastrService.searchSerialNo(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	@RequestMapping( value="/deviceUserInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deviceUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{		
			rsMap = measrMastrService.deviceUserInfo(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	@RequestMapping( value="/searchSerialNoList.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> searchSerialNoList(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> scanList = new ArrayList<Map<String,Object>>();
		String chkYn = "N";
		try{		
			
			if(param.get("scanStr")!=null){
				String[] scanStr = param.get("scanStr").toString().split(",");
				param.put("items", scanStr);
				scanList = measrMastrService.searchSerialNoList(param);			
				rsMap.put("scanList", scanList);
				chkYn = "Y";
			}else{
				LOG.debug("scanStr>>>> is Null ");
			}
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	

	/********************************** OpenApi 적용 관련 START ***************************************/
	/**
	 * 마지막 입력 데이터 확인
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/checkLastData.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkLastData(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			rsMap = measrMastrService.checkLastData(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		rsMap.put("chkYn", chkYn);
		
		return rsMap;
	}
	
	/**
	 * 측정정보 저장
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertMeasrData.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertMeasrData(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		int rtInt = 0;
		try{
			param.put("MEASR_RSLT","S"); 		//측정결과 : S성공, F실패
			if(param.get("inAppData") != null){
				String str = param.get("inAppData").toString();
				List<Map<String,String>> list = StringUtil.makeStringToIterator(str.replaceAll("\\}\\,\\{", "^"), "^", ",", ":");
				param.put("insList", list);
				param.remove("inAppData");
			}
			String tblNm = StringUtil.nvl(param.get("insTableNm"));
			if ("TN_MS_ACT".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertAct(param);
			}
			else if ("TN_MS_HEART_RATE".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertHeartRate(param);
			}
			else if ("TN_MS_RUNNING_STATUS".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertRunningStatus(param);
			}
			else if ("TN_MS_RUNNING_CALORIE".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertRunningCalorie(param);
			}
			else if ("TN_MS_RUNNING_HR".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertRunningHR(param);
			}
			else if ("TN_MS_HEART_RATE_ARR".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				//LOG.debug("TN_MS_HEART_RATE_ARR=="+param);
				rtInt = measrMastrService.insertHeartRateArr(param);
			}
			else if ("TN_MS_RUNNING_HR_ARR".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_혈압 : MS001_30  
				rtInt = measrMastrService.insertRunningHrArr(param);
			}
			else if ("TN_MS_BODY_COMP".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "20"); 	//측정대상구분_BMI : MS001_80
				rtInt = measrMastrService.insertBodyComp(param);
			}
			else if ("TN_MS_BLOOD_PRESS".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30  
				rsMap = measrMastrService.insertBloodPress(param);
				rtInt = Integer.parseInt(StringUtil.nvl(rsMap.get("insertCnt"),"0"));
			}
			else if ("TN_MS_BLOOD_SUGAR".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "40"); //측정대상구분_혈당 : MS001_40  
				rsMap = measrMastrService.insertBloodSugar(param);	
				rtInt = Integer.parseInt(StringUtil.nvl(rsMap.get("insertCnt"),"0"));
			}
			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);
		rsMap.put("insertCnt", rtInt);
		rsMap.put("insTableNm", param.get("insTableNm"));
		return rsMap;
	}
	/********************************** OpenApi 적용 관련 END ***************************************/
	
	@RequestMapping( value="/selectDeviceSerial.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectDeviceSerial(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> serialList = new ArrayList<Map<String,Object>>();
		String chkYn = "N";
		try{
			serialList = measrMastrService.selectDeviceSerial(param);			
			rsMap.put("serialList", serialList);
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	@RequestMapping( value="/selectEquipItem.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectEquipItem(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> equipList = new ArrayList<Map<String,Object>>();
		String chkYn = "N";
		try{
			equipList = measrMastrService.selectEquipItem(param);			
			rsMap.put("equipList", equipList);
			chkYn = "Y";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	@RequestMapping( value="/selectUserOta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectUserOta(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> userOtaList = new ArrayList<Map<String,Object>>();
		String chkYn = "N";
		try{
			userOtaList = measrMastrService.selectUserOta(param);			
			rsMap.put("userOtaList", userOtaList);
			chkYn = "Y";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	@RequestMapping( value="/insertUserOta.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertUserOta(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{
			measrMastrService.insertUserOta(param);
			chkYn = "Y";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}

	@RequestMapping(value = "/updateLstBloodSugar.do")
	public @ResponseBody Map<String, Object> updateLstBloodSugar(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try {
			rsInt += measrMastrService.updateLstBloodSugar(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping(value = "/getMeasureResultInf.do")
	public @ResponseBody Map<String, Object> getMeasureResultInf(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		try {
			
			rsMap.put("RPT_RSLT", measrMastrService.getMeasureResultInf(param));
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 체성분, 혈압, 혈당 수기 입력
	 * @param request
	 * @param param
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value= "/writeMeasure.do")
	public @ResponseBody Map<String, Object> writeMeasure(HttpServletRequest request, @ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		System.out.println("### writeMeasure.do");
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.println(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println("#####################");	
		
		String gubn = param.get("gubn").toString();
		String chkYn = "N";
		
		try {
		
			//체중		
			if(gubn.equals("weight")) {								
				rsMap = measrMastrService.insertBodyCompManu(param);
				chkYn = "Y";
			}			
			//혈압
			if(gubn.equals("press")) {
				rsMap = measrMastrService.insertBloodPressManu(param);		
				chkYn = "Y";
			}			
			//혈당
			if(gubn.equals("sugar")) {
				rsMap = measrMastrService.insertBloodSugarManu(param);
				chkYn = "Y";				
			}
		}catch(Exception e) {
			e.printStackTrace();			
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);			
		return rsMap;
	}
	
	
	/**
	 * 
	 * 
	 */
	@RequestMapping(value= "/lstBloodSugarSaveChk.do")
	public @ResponseBody Map<String, Object> lstbloodSugarSaveChk(HttpServletRequest request, @ModelAttribute Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		System.out.println("### bloodSugarSaveChk.do");
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.println(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println("#####################");	
		rsMap = measrMastrService.lstBloodSugarSaveChk(param);
				
		return rsMap;
	}
	
	@RequestMapping(value = "/updateLstBloodSugarClf.do")
	public @ResponseBody Map<String, Object> updateLstBloodSugarClf(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try {
			rsInt += measrMastrService.updateLstBloodSugarClf(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
}
