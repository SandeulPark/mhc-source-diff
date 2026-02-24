package kr.go.mhc.mhcapp.mr.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.mr.service.AppSportActivityService;

/**
 * @Class Name : AppSportsActivityController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 스포츠활동 인증

 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/mr")
public class AppSportActivityController extends DMultiActionController{	
	@Resource(name="mhcapp.mr.AppSportActivityService")
	private AppSportActivityService sportService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 국민체력100 로그인 정보 있는지 확인
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/chkExistSportActivityUserInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> chkExistSportActivityUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		System.out.println("chkExistSportActivityUserInfo.do START");				
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{			
			rsMap = sportService.chkExistSportActivityUserInfo(param);			
			printMap(rsMap, "chkExistSportActivityUserInfo.do");	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("chkExistSportActivityUserInfo.do END");
		return rsMap;
	}	

	/**
	 * 국민체력100 로그인 성공 후 리턴 정보 insert
	 * @param 
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertSportActivityUserInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertSportActivityUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//Map<String,Object> userInfo = new HashMap<String, Object>();
		String chkYn = "N";		
		
		try{			
			printMap(param, "insertSportActivityUserInfo.do");
			//userInfo=sportService.userInfo((String) param.get("USER_ID"));
			//param.put("TRGTER_MANAGER_CLF", userInfo.get("TRGTER_MANAGER_CLF"));
			sportService.insertSportActivityUserInfo(param); 
			chkYn = "Y";
			printMap(rsMap, "insertSportActivityUserInfo.do");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	@RequestMapping( value="/updateSportActivityUserAgree.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSportActivityUserAgree(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";		
		
		try{			
			sportService.updateSportActivityUserAgree(param); 
			chkYn = "Y";
			printMap(rsMap, "updateSportActivityUserAgree.do");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	@RequestMapping( value="/updateSportActivityCertWrite.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSportActivityCertWrite(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";		
		
		try{			
			sportService.updateSportActivityCertWrite(param); 
			chkYn = "Y";
			printMap(rsMap, "updateSportActivityCertWrite.do");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	
	@RequestMapping( value="/getSportActivityWalkCnt.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSportActivityWalkCnt(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";		
		String sportCertYn = String.valueOf(param.get("SPORT_CERT_YN"));
		
		try{
						
			rsMap = sportService.getSportActivityWalkCnt(param);			
			chkYn = "Y";
			
			if(sportCertYn.equals("N")) {
				String Url = "https://nfa.kspo.or.kr/mobile/sportsApp/sportsAppCertWrite.do?req_device=MH&memb_seq="
						   +rsMap.get("MEMBER_SN")+"&memb_hash="+rsMap.get("MEMBER_HASH");
				
				String response = getRequest(Url);
				
				JSONObject jObject = new JSONObject(response);
				JSONObject jSubObject = jObject.getJSONObject("sportsAppVo");
				
				String spoCertSeq = jSubObject.getString("spo_cert_seq");
				String measureStdYmd = jSubObject.getString("measure_std_ymd");
				
				rsMap.put("SPORT_CERT_SEQ", spoCertSeq);
				rsMap.put("MEASURE_STD_YMD", measureStdYmd);
				
				sportService.updateSportActivityCertWrite(rsMap);
			}
			
	
			String Url = "https://nfa.kspo.or.kr/mobile/sportsApp/sportsCertResultWrite.do?req_device=MH&memb_seq="
						+rsMap.get("MEMBER_SN")+"&memb_hash="+rsMap.get("MEMBER_HASH")+"&spo_cert_seq="
						+rsMap.get("SPORT_CERT_SEQ")+"&wk_01="+rsMap.get("WK01")+"&wk_02="+rsMap.get("WK02")
						+"&wk_03="+rsMap.get("WK03")+"&wk_04="+rsMap.get("WK04");

			
			String response = getRequest(Url);
						
			JSONObject jObject = new JSONObject(response);
			
			JSONObject jSubObject = jObject.getJSONObject("sportsAppVo");
			String rtnCode = jSubObject.getString("rtn_code");
			String rtnMsg = jSubObject.getString("rtn_msg");
			
			rsMap.put("rqUrl", Url); // 로그용
			rsMap.put("rtnCode", rtnCode);
			rsMap.put("rtnMsg", rtnMsg);
			
			printMap(rsMap, "getSportActivityWalkCnt.do");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	
	public static String getRequest(String targetUrl) {
		
		String response = "";
		
		try {
			
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);
			
	        System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	        System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	        System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			
			String inputLine;			
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
			
			response = sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	@RequestMapping( value="/delUserInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";		
		
		try{			
			sportService.delUserInfo(param); 
			chkYn = "Y";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	

	
	
	public static void printMap(Map<String,Object> map, String method){
	    Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
	    Entry<String,Object> entry = null;
	    System.out.println("-----------printMap : "+method+ "--------------------\n");
	    while(iterator.hasNext()){
	        entry = iterator.next();
	        System.out.println("key : "+entry.getKey()+",\tvalue : "+entry.getValue());
	    }
	    System.out.println("");
	    System.out.println("------------------------------------------------\n");
	}
	

	/**
	 * 스포츠 활동 인증(보편) 그룹가입 여부
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/chkGnGroupActivityUserInfo.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> chkGnGroupActivityUserInfo(@ModelAttribute Map param, ModelMap model) throws Exception{		
		List<Map<String,Object>> rsMap = new ArrayList<Map<String,Object>>();
			
		try {
			rsMap = sportService.chkGnGroupActivityUserInfo((String) param.get("SESS_USER_ID"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return rsMap;	
					
	}
	
}
