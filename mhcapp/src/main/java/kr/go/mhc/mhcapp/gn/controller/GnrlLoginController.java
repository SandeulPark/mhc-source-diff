package kr.go.mhc.mhcapp.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlLoginService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value="/login/gn")
public class GnrlLoginController extends DMultiActionController{
	
	@Resource(name="gn.gnrlLoginService")
	private GnrlLoginService gnrlLoginService;
		
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping(value="/checkLoginId.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkLoginId(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
				
		Map<String, Object> rsMap = new HashMap<String,Object>();
		
		int rtInt = gnrlLoginService.checkLoginId(param);
		
		rsMap.put("rtInt", rtInt);
		
		return rsMap;
	}
	
	@RequestMapping(value="/userTypeCheck.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> userTypeCheck(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
				
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> userTypeInfo = gnrlLoginService.userTypeCheck(param);
		
		if(userTypeInfo != null){
			rsMap.put("userTypeInfo", userTypeInfo);
		}
		
		return rsMap;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> login(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String token = (String) param.get("token");
		
		if("Y".equals(param.get("keySec"))){
			decryptMap(param,"PW");			
		}
		
		Map<String, String> rsMap = gnrlLoginService.login(param);
		
		if(rsMap != null && "Y".equals(rsMap.get("USE_YN"))){ // 탈퇴회원 로그인 안되게 수정
			//토크 비교 후 토큰 저장
			if(!"".equals(token) && !token.equals(rsMap.get("TOKEN"))){
				param.put("USER_ID", rsMap.get("USER_ID"));
				gnrlLoginService.updateToken(param);
			}
			
			setSessionInfo(req.getSession(), rsMap);
			rsMap.put("chkLogin", "true");
		}else{
			rsMap = new HashMap<String, String>();
			rsMap.put("chkLogin", "false");
		}
		
		return rsMap;
	}
	
	@RequestMapping(value="/snsLogin.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> snsLogin(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String token = (String) param.get("token");
		Map<String, String> rsMap = gnrlLoginService.snsLogin(param);
		
		if(rsMap != null){
			//토크 비교 후 토큰 저장
			if(!"".equals(token) && !token.equals(rsMap.get("TOKEN"))){
				param.put("USER_ID", rsMap.get("USER_ID"));
				gnrlLoginService.updateToken(param);
			}
			
			setSessionInfo(req.getSession(), rsMap);
			rsMap.put("chkLogin", "true");
		}else{
			rsMap = new HashMap<String, String>();
			rsMap.put("chkLogin", "false");
		}
		
		return rsMap;
	}
		
	@RequestMapping(value="/userRegit.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> userRegit(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		String snsType = (String) param.get("SNS_TYPE");
		
		if(snsType != null && !"".equals(snsType)){
			Map<String, String> snsLoginMap = gnrlLoginService.snsLogin(param);
			if(snsLoginMap == null){
				gnrlLoginService.userRegit(param);
				chkYn = "Y";
			}
		}else if(snsType == null || "".equals(snsType)){
			Map<String, String> loginMap = gnrlLoginService.login(param);
			if(loginMap == null){
				gnrlLoginService.userRegit(param);
				chkYn = "Y";
			}
		}
		
		rsMap.put("chkYn", chkYn);
		
		return rsMap;
	}
	
	@RequestMapping(value="/findUser.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> findUser(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
				
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> userTypeInfo = gnrlLoginService.findUser(param);		
		
		if(userTypeInfo != null){
			rsMap.put("userTypeInfo", userTypeInfo);
		}
		
		return rsMap;
	}
	
	
	@RequestMapping(value="/pwUpdate.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> pwUpdate(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
				
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("msg","");
		try {
			gnrlLoginService.pwUpdate(param);	
		} catch (Exception e) {			
			rsMap.put("msg",e.getMessage());			
		}
		
		return rsMap;
	}
	
	/**
	 * 애플로그인시 사용자 정보를 가져옴
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String,Object>();
		Map<String, String> loginMap = gnrlLoginService.snsLogin(param);

		rsMap.put("apple", loginMap);
		return rsMap;
	}

	/**
	 * 회원 탈퇴
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/userDropout.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> userDropout(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();

		String chkYn = "N";
		try{
			gnrlLoginService.userDropout(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 회원 탈퇴(애플 심사용)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/withdrawCheckUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> withdrawCheckUser(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> whMap = new HashMap<String,Object>();
		
		String chkYn = "N";	
		try{
			whMap = gnrlLoginService.withdrawCheckUser(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("result", whMap);
		rsMap.put("chkYn", chkYn);

		return rsMap;
	}
	
	/**
	 * 회원 탈퇴(애플 심사용)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/withdrawUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> withdrawUser(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String chkYn = "N";	
		try{
			gnrlLoginService.withdrawUser(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 회원 탈퇴(애플 심사용)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/userWithdrawCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> userWithdrawCheck(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String chkYn = "N";	
		int cnt = 0;
		try{
			cnt = gnrlLoginService.userWithdrawCheck(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}		
		rsMap.put("chkYn", chkYn);
		rsMap.put("count", cnt);
		return rsMap;
	}
	
	/**
	 * NICE 인증 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/phoneCertification.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> phoneCertification(@ModelAttribute Map<String,Object> param, HttpSession session,HttpServletRequest req) throws Exception{
				
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		
	    String sSiteCode = "BU990";							// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "2hdQkmxplqiK ";				// NICE로부터 부여받은 사이트 패스워드
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.	                                                    	
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	   	String sAuthType = "";      						// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서	   	
	   	String popgubun 	= "N";							// Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize 	= "";							// 없으면 기본 웹페이지 / Mobile : 모바일페이지	
		String sGender = ""; 								// 없으면 기본 선택 값, 0 : 여자, 1 : 남자 

		// 개발계 코르도바
//		String mhcUrl = "http://211.175.203.8:8088";
		// 개발계 코르도바제거버전
		//String mhcUrl = "http://211.175.203.8:8089"; 
		// 운영계 코르도바
		//String mhcUrl = "http://mhc.khealth.or.kr:8089";
		// 운영계 코르도바 제거버전
		String mhcUrl = "http://mhc.khealth.or.kr:8088";

	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    String sReturnUrl = "";      // 성공시 이동될 URL		
	    String sErrorUrl = mhcUrl+"/login/gn/failCertification.do";          // 실패시 이동될 URL
	    
	    //아이디 찾기
	    if(param.get("findUserStts").equals("id")) {
	    	sReturnUrl = mhcUrl+"/login/gn/successIdCertification.do";      // 성공시 이동될 URL
	    //비밀번호 찾기
		}else if(param.get("findUserStts").equals("pw")){
			sReturnUrl = mhcUrl+"/login/gn/successPwdCertification.do";      // 성공시 이동될 URL
		//회원가입
		}else {
			sReturnUrl = mhcUrl+"/login/gn/successJoinCertification.do";      // 성공시 이동될 URL
		}
	    	    
	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + 
							"6:GENDER" + sGender.getBytes().length + ":" + sGender;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if(iReturn == 0){
	        sEncData = niceCheck.getCipherData();
	    }else{
	    	sMessage = getErrorMessage(iReturn);
	    }
	    session.setAttribute("sEncData", "sEncData");
	    param.put("sMessage", sMessage);
	    param.put("sEncData", sEncData);
	    param.put("sReturnUrl", sReturnUrl);
	    param.put("sErrorUrl", sErrorUrl);
	    param.put("value", param.get("value"));
		return param;
		
	}
	
	/**
	 * 휴대폰인증 아이디
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/successIdCertification.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String successCertification(@ModelAttribute Map<String,Object> param, HttpSession session,HttpServletRequest req,ModelMap model) throws Exception{
		return certificationHandler(req, session, model, "and/gn/checkplus_successId", "ios/gn/checkplus_successId");
	}
		
	/**
	 * 휴대폰인증 비밀번호
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/successPwdCertification.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String successPwdCertification(@ModelAttribute Map<String,Object> param, HttpSession session,HttpServletRequest req,ModelMap model) throws Exception{
		return certificationHandler(req, session, model, "and/gn/checkplus_successPwd", "ios/gn/checkplus_successPwd");
	}
	
	/**
	 * 휴대폰인증 회원가입
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/successJoinCertification.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String successJoinCertification(@ModelAttribute Map<String,Object> param, HttpSession session,HttpServletRequest req,ModelMap model) throws Exception{
		return certificationHandler(req, session, model, "and/gn/checkplus_successJoin", "ios/gn/checkplus_successJoin");
	}
	
	private String certificationHandler(HttpServletRequest req, HttpSession session, ModelMap model, String androidPage, String iosPage) throws Exception{
		String rtnPage = "";

		String header = req.getHeader("User-Agent").toLowerCase().replaceAll(" ", "");

		// 바뀐 iOS 웹뷰가 "mobile" 을 포함하지 않음.
	    //if (header.contains("mobile")) {
	        rtnPage = header.contains("android") ? androidPage : iosPage;
	    //}
		
	    NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();
	    String sEncodeData = requestReplace(req.getParameter("EncodeData"), "encodeData");
	    String sSiteCode = "BU990";
	    String sSitePassword = "2hdQkmxplqiK";

	    String sCipherTime = "";		// 복호화한 시간
	    String sRequestNumber = "";		// 요청 번호
	    String sResponseNumber = "";	// 인증 고유 번호
	    String sAuthType = "";			// 인증 수단
	    String sName = "";				// 성명
	    String sDupInfo = "";			// 중복가입 확인값(DI_64 byte)
	    String sConnInfo = "";			// 연계정보 확인값(CI_88 byte)
	    String sBirthDate = "";			// 생년월일(YYYYMMDD)
	    String sGender = "";			// 성별(0:여성, 1:남성)
	    String sNationalInfo = "";		// 내/외국인 정보(0:내국인, 1:외국인)
	    String sMobileNo = "";			// 휴대폰번호(24 byte)
	    String sMobileCo = "";			// 통신사(3 byte)
	    String sMessage = "";
	    String sPlainData = "";

	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if (iReturn == 0) {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();

	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        sRequestNumber 	= (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType 		= (String)mapresult.get("AUTH_TYPE");
	        sName 			= (String)mapresult.get("NAME");
	        //sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate 		= (String)mapresult.get("BIRTHDATE");
	        sGender 		= (String)mapresult.get("GENDER");
	        sNationalInfo 	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo 		= (String)mapresult.get("DI");
	        sConnInfo 		= (String)mapresult.get("CI");
	        sMobileNo 		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo 		= (String)mapresult.get("MOBILE_CO");

	        String session_sRequestNumber = (String) session.getAttribute("REQ_SEQ");
	        if (!sRequestNumber.equals(session_sRequestNumber)) {
	            sMessage = "세션값 불일치 오류입니다.";
	            sResponseNumber = "";
	            sAuthType = "";
	        }
	    } else {
	        sMessage = getErrorMessage(iReturn);
	    }

	    model.addAttribute("sRequestNumber", sRequestNumber);
	    model.addAttribute("sAuthType", sAuthType);
	    model.addAttribute("sName", sName);
	    model.addAttribute("sBirthDate", sBirthDate);
	    model.addAttribute("sGender", sGender);
	    model.addAttribute("sNationalInfo", sNationalInfo);
	    model.addAttribute("sDupInfo", sDupInfo);
	    model.addAttribute("sConnInfo", sConnInfo);
	    model.addAttribute("sMobileNo", sMobileNo);
	    model.addAttribute("sMobileCo", sMobileCo);
	    model.addAttribute("sMessage", sMessage);
	    
		return rtnPage;
	}
	
	private String getErrorMessage(int iReturn) {
	    switch (iReturn) {
	        case -1:
	            return "복호화 시스템 오류입니다.";
	        case -4:
	            return "복호화 처리 오류입니다.";
	        case -5:
	            return "복호화 해쉬 오류입니다.";
	        case -6:
	            return "복호화 데이터 오류입니다.";
	        case -9:
	            return "입력 데이터 오류입니다.";
	        case -12:
	            return "사이트 패스워드 오류입니다.";
	        default:
	            return "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	}
	
	public String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");    	    
        	if(gubun != "encodeData"){        		
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
   }
	
}
