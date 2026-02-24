package kr.go.mhc.mhcapp.ms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.BlobView;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcapp.ms.service.GnrlMeasrMastrService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value="/ms/gn")
public class GnrlMeasrMastrController extends DMultiActionController{ 
	
	@Resource(name="ms.gnrlMeasrMastrService")
	private GnrlMeasrMastrService gnrlMeasrMastrService;


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
			
			rsMap = gnrlMeasrMastrService.insertBloodPress(param);
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
			
			rsMap = gnrlMeasrMastrService.updateBloodPress(param);			
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

			rsMap = gnrlMeasrMastrService.deleteBloodPress(param);			
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
			
			rsMap = gnrlMeasrMastrService.insertBloodSugar(param);
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
			
			rsMap = gnrlMeasrMastrService.updateBloodSugar(param);		
			
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
			
			rsMap = gnrlMeasrMastrService.deleteBloodSugar(param);		
			
			chkYn = "Y";
			
		}catch(Exception e){
			
		}
		
		rsMap.put("chkYn", chkYn);

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
			rsMap = gnrlMeasrMastrService.insertBodyCompManu(param);
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
			rsMap = gnrlMeasrMastrService.deleteBodyCompManu(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug(e);
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
			rsMap = gnrlMeasrMastrService.checkLastData(param);
			chkYn = "Y";
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		if(rsMap == null){
			rsMap = new HashMap<String,Object>();
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
				param.put("MEASR_TRGT_CLF", "10"); 	//측정대상구분_활동 : MS001_10
				rtInt = gnrlMeasrMastrService.insertAct(param);
			}
			else if ("TN_MS_BODY_COMP".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "20"); 	//측정대상구분_체성분 : MS001_20
				rtInt = gnrlMeasrMastrService.insertBodyComp(param);
			}
			else if ("TN_MS_BLOOD_PRESS".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "30"); 	//측정대상구분_혈압 : MS001_30
				rsMap = gnrlMeasrMastrService.insertBloodPress(param);
				rtInt = Integer.parseInt(StringUtil.nvl(rsMap.get("insertCnt"),"0"));
			}
			else if ("TN_MS_BLOOD_SUGAR".equals(tblNm)) {
				param.put("MEASR_TRGT_CLF", "40"); //측정대상구분_혈당 : MS001_40  
				rsMap = gnrlMeasrMastrService.insertBloodSugar(param);	
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
	
	/**
	 * 기기 셋팅 유저정보
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/deviceUserInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deviceUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		try{		
			rsMap = gnrlMeasrMastrService.deviceUserInfo(param);			
			chkYn = "Y";
			
		}catch(Exception e){
			LOG.debug("exception>>>> "+ e.getMessage());
		}
		
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}

}
