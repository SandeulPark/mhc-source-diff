package kr.go.mhc.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.CommonUserService;
import kr.go.mhc.common.service.PushService;
import kr.go.mhc.common.util.StringUtil;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sun.misc.BASE64Decoder;

import com.dreamsecurity.dstoolkit.DSToolkit;
import com.dreamsecurity.dstoolkit.crypto.Cipher;
import com.dreamsecurity.dstoolkit.crypto.PrivateKey;
import com.dreamsecurity.dstoolkit.exception.DSToolkitException;
import com.dreamsecurity.dstoolkit.storage.Disk;
import com.dreamsecurity.dstoolkit.util.Base64;
import com.dreamsecurity.magickeypad.MagicKeypadServer;
import com.extrus.common.json.simple.parser.JSONParser;
import com.extrus.exafe.e2e.api.E2EApiManager;

/**
 * @Class Name : LoginController.java
 * @Description : 사용자 로그인 체크 및 WEB, APP 화면 분기 처리
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.10		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/login")
public class LoginController extends DMultiActionController{ 
	@Resource(name="common.userService")
	private CommonUserService commUserService;

	@Resource(name="common.pushService")
	private PushService pushService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginOut.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> loginOut(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String viewGbn = (String)param.get("SESS_ISMOBILE");
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);
		rsMap.put("rtnPage", "../gn/login.html#main1");
		return rsMap;
	}
	
	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginPage.do", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String rtnPage = "app/cm/login";
		String viewGbn = ""; 
		if(param.get("SESS_ISMOBILE") != null){
			viewGbn = (String)param.get("SESS_ISMOBILE"); 
		}		
		if(viewGbn.indexOf("mobile") < 0 ){
//			임시 사용
			rtnPage = "redirect:/pageNavi.do";
//			로그인 사용시 해제
//			rtnPage = "web/gn/login";
		}

		
		rtnPage = "redirect:/pageNavi.do";
		
		//2016-08-24 윤봉훈 - 다시 로그인 시 이전 데이터가 세션에 남아있는 현상 방지를 위한 세션 초기화
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);
		
		return rtnPage;
	}	
	
	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginUserCheckApp.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> userLogincheck(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
/*		System.out.println("loginPwd===="+param.get("loginPwd").toString());
		if(param.get("loginPwd")!=null){
			keypadDecrypt(param.get("loginPwd").toString()); 
		}
		return null;*/
		/**
		 * 패스워드 복호화
		 */
		
		if("Y".equals(param.get("keySec")))
			decryptMap(param,"loginPwd");


		Map<String,Object>	rtnMap		= new HashMap<String,Object>();
		Map<String,Object>	mainMap		= commUserService.checkLoginUser(param);   
		String				token		= StringUtil.nvl(String.valueOf(param.get("token")));
		Map<String,String>	innerMap	= (Map<String,String>)mainMap.get("mainMap");
		
		//로그인 처리 진행 시
		if("true".equals(mainMap.get("isLogin").toString())){
			
			//토크 비교 후 토큰 저장
			if(!"".equals(token) && !token.equals(mainMap.get("TOKEN"))){
				pushService.updateToken(param);
			}
			setSessionInfo(req.getSession(), innerMap);
			
		}

		if(!"".equals(StringUtil.nvl(String.valueOf(mainMap.get("rtnMsgCode"))))){
			if(mainMap.get("rtnMsgArr") != null){
				innerMap.put("rtnMsg", getMsg(String.valueOf(mainMap.get("rtnMsgCode")), (String[])mainMap.get("rtnMsgArr")));
			}else{
				innerMap.put("rtnMsg", getMsg(String.valueOf(mainMap.get("rtnMsgCode"))));
			}
		}
		
		innerMap.put("loginPwd",(String)param.get("loginPwd"));
		rtnMap.put("rsList", innerMap);
		
		return rtnMap;
	}
	
	
	
	/**
	 * 관리자웹_로그인 정보확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginUserCheckWeb.do", method = RequestMethod.POST)
	public String userWebLogincheck(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,String>	rsMap		= commUserService.checkWebLoginUser(param);	
		String rtnPage = "web/gn/login";	
		if(rsMap != null && !"".equals(StringUtil.nvl(rsMap.get("USER_ID")))){
			setSessionInfo(req.getSession(), rsMap);
			rtnPage = "redirect:/pageNavi.do";
		}else{
			model.addAttribute("msg", "로그인 정보가 잘못되었습니다");			
		}		
		return rtnPage;
	}
	
	/**
	 * 기기 정보 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/loginCheckAppInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkAppInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = commUserService.checkAppInfo(param);
		if(rsList!= null ){
			rsList.get(0).put("ipAddr", String.valueOf(param.get("SESS_IPADDR")));
		}
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 전자서명 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/digiSignInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> digiSignInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = commUserService.digiSignInfo(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 전자 서명
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/insertDigiSign.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertDigiSign(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> insertParam = new HashMap<String,Object>();
		
		String jsonStr = (String) param.get("data");
		jsonStr = jsonStr.replaceAll("&quot;" , "\"");
		
		insertParam = new ObjectMapper().readValue(jsonStr, Map.class);
		
		String userId = (String) param.get("userId");
		
		insertParam.put("userId", userId);
		insertParam.put("USER_ID", userId);
		insertParam.put("jsonData", jsonStr);
		
		List<Map<String,String>> rsList = commUserService.selectDigiSignChk(insertParam);
		
		if(rsList.size() > 0){
			commUserService.digiSignUpdate(insertParam);
		}else{
			commUserService.digiSignInsert(insertParam);
		}
				
		return rsMap;
	}
	
}
