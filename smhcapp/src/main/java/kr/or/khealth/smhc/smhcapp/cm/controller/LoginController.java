package kr.or.khealth.smhc.smhcapp.cm.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.service.CommonUserService;
import kr.or.khealth.smhc.common.util.CryptoUtil;
import kr.or.khealth.smhc.smhcapp.cm.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginController extends DMultiActionController{ 
	@Resource(name="smhcapp.cm.LoginService")
	private LoginService loginService;
	
	@Resource(name="common.userService")
	private CommonUserService commUserService;
	
	//LoginManager loginManager = LoginManager.getInstance();
	
	private static final Map<String, Map<String, String>> CERTIFICATION_MAP = new ConcurrentHashMap<>();
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/login.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> userLogincheck(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		CryptoUtil.decryptMap(param,"pw");
		
		Map<String,String> rtnMap = loginService.selectUser(param);
		
		//loginManager.registerLoginSession(rtnMap.get("USER_ID"), req.getSession());
		rtnMap.put("SESS_ID", req.getSession().getId());
		setSessionInfo(req.getSession(), rtnMap);
		
		return rtnMap;
	}
	
	@RequestMapping( value="/updateLoginFailCnt.do", method = RequestMethod.POST)
	public void updateLoginFailCnt(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		if((String) param.get("USER_ID") == null){
			if(param.get("LOGIN_FAIL_CNT") != null && "0".equals(String.valueOf(param.get("LOGIN_FAIL_CNT")))) {
				loginService.insertUnlockHist(param);
			}else{
				param.put("LOGIN_FAIL_CNT", loginService.getLoginFailCnt(param) + 1);
			}
		}
		loginService.updateLoginFailCnt(param);
	}
	
	@RequestMapping( value="/insertLastConnectDt.do", method = RequestMethod.POST)
	public void insertLastConnectDt(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		loginService.insertLastConnectDt(param);
	}
	
	@RequestMapping( value="/insertUserMobileInfo.do", method = RequestMethod.POST)
	public void insertUserMobileInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		loginService.updateUserDupMobileInfo(param);				
		loginService.insertUserMobileInfo(param);
	}
	
	/**
	 * 2022.08.23 기존 소스 > 현재 AI 스피커에서만 사용
	 * */
	@RequestMapping( value="/changeUserIdentify.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changeUserIdentify(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			rsMap = loginService.changeUserIdentify(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 2022.08.23 change > reg로 변경
	 * */	
	@RequestMapping( value="/regUserIdentify.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> regUserIdentify(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			CryptoUtil.decryptMap(param,"NEW_PW");
			rsMap = loginService.regUserIdentify(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/getUserInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserInfo(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			Map<String, Object> userInfo = loginService.getUserInfo(param);
			rsMap.put("userInfo", userInfo);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/chkExistNotice.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> chkExistNotice(HttpServletRequest request, @ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> noticeInfo = loginService.chkExistNotice(param);
		rsMap.put("noticeInfo", noticeInfo);
				
		return rsMap;		
	}
	
	@RequestMapping( value="/insertDoNotShowNotice.do", method = RequestMethod.POST)
	public void insertDoNotShowNotice(HttpServletRequest request, @ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		loginService.insertDoNotShowNotice(param);
	}
	
	@RequestMapping( value="/searchIdentify.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> searchIdentify(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			rsMap = loginService.searchIdentify(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/changePwd.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePwd(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			CryptoUtil.decryptMap(param,"PW");
			rsMap = loginService.changePwd(param);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/selectUserCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectUserCnt(@ModelAttribute Map<String, Object> param, ModelMap modal) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			CryptoUtil.decryptMap(param,"pw");
			Integer selectUserCnt = loginService.selectUserCnt(param);
			rsMap.put("userCnt", selectUserCnt);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			rsMap.put("chkYn", "N");
		}
		return rsMap;
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
		rsMap.put("rsList", rsList);
		return rsMap;
	}

	/**
	 * 사용자 세션 ID 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/checkUserSession.do", method = RequestMethod.POST)
	public @ResponseBody boolean checkUserSession(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		param.put("LCLAS_CD", "CM045");
		Map<String,Object> rsCmmnCdMap = cmmnService.selectCmmnCdUseYn(param);
		
		try {
			if(rsCmmnCdMap.get("USE_YN").equals("Y")) {
				Map<String,String> rsMap = loginService.selectUserMobileInfo(param);
				String token = rsMap.get("TOKEN");
				if(token == null) return false;
				else if(token.equals(param.get("SESS_TOKEN"))) return false;
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * NICE 인증 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	/*@RequestMapping( value="/phoneCertification.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> phoneCertification(@ModelAttribute Map<String,Object> param, HttpSession session,HttpServletRequest req) throws Exception{
				
		NiceID.Check.CPClient niceCheck = new NiceID.Check.CPClient();

		String sSiteCode = "BU990";							// NICE로부터 부여받은 사이트 코드
		String sSitePassword = "2hdQkmxplqiK ";				// NICE로부터 부여받은 사이트 패스워드
		String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.	                                                    	
		sRequestNumber = niceCheck.getRequestNO(sSiteCode);
		session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		String sAuthType = "";      						// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서	   	
		String popgubun 	= "N";							// Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize 	= "";							// 없으면 기본 웹페이지 / Mobile : 모바일페이지	
		String sGender = ""; 								// 없으면 기본 선택 값, 0 : 여자, 1 : 남자 

		// 개발계
		String mhcUrl = "http://211.175.203.8:8071";
		// 운영계 WAS#1,2
		//String mhcUrl = "http://mhc.khealth.or.kr:8576"; 
		// 운영계 WAS#3,4
		//String mhcUrl = "http://mhc.khealth.or.kr:8578"; 

		// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
		String sReturnUrl = mhcUrl+"/login/certification.do";      // 성공시 이동될 URL		
		String sErrorUrl = mhcUrl+"/login/failCertification.do";          // 실패시 이동될 URL

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

		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("findUserStts", (String) param.get("findUserStts"));
		CERTIFICATION_MAP.put(sRequestNumber, userInfo);

		session.setAttribute("sEncData", "sEncData");
		param.put("sMessage", sMessage);
		param.put("sEncData", sEncData);
		param.put("sReturnUrl", sReturnUrl);
		param.put("sErrorUrl", sErrorUrl);
		param.put("value", param.get("value"));
		return param;
	}*/

	/*@RequestMapping( value="/certification.do", method = {RequestMethod.POST,RequestMethod.GET})
	//private String certificationHandler(HttpServletRequest req, HttpSession session, ModelMap model, String androidPage, String aiPage) throws Exception{
	private String certificationHandler(@ModelAttribute Map<String,Object> param, HttpServletRequest req, HttpSession session, ModelMap model) throws Exception{
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

		Map<String, String> userInfo = new HashMap<>();
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

			param.put("USER_NM", sName);
			param.put("BIRTH", sBirthDate);
			//param.put("MOBILENO", sMobileNo);
			userInfo = CERTIFICATION_MAP.get(sRequestNumber);
			Integer certificationCnt = loginService.getCertificationCnt(param);

			if(certificationCnt != null) {
				if(certificationCnt > 4) userInfo.put("findUserStts", "over");
				else {
					param.put("CERTIFICATION_CNT", certificationCnt += 1);
					loginService.updateCertificationCnt(param);
				}
			}

			if (userInfo != null) {
				userInfo.put("sName", sName);
				userInfo.put("sBirthDate", sBirthDate);
				//userInfo.put("sMobileNo", sMobileNo);
			}
		} else {
			sMessage = getErrorMessage(iReturn);
		}

		String header = req.getHeader("User-Agent").toLowerCase().replaceAll(" ", "");

		model.addAttribute("sRequestNumber", sRequestNumber);
		model.addAttribute("sAuthType", sAuthType);
		model.addAttribute("sName", sName);
		model.addAttribute("sBirthDate", sBirthDate);
		model.addAttribute("findUserStts", userInfo.get("findUserStts"));

		if (header.contains("mobile") && header.contains("android")) return "and/cm/certification_result";
		else return "and/cm/certificationResult";
	}*/

	/*private String getErrorMessage(int iReturn) {
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
	}*/

	// 비밀번호 확인 
	@RequestMapping(value="/checkMyinfo.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> verifyPasswordForMyInfo(
            HttpServletRequest req, 
            @ModelAttribute Map<String, Object> param, 
            ModelMap model) throws Exception {
        
        // 입력받은 비밀번호 복호화
		CryptoUtil.decryptMap(param,"pw");
      
        Map<String, Object> rsMap = new HashMap<>();
        
        try {
            rsMap = loginService.verifyUserPassword(param);
            rsMap.put("chkYn", "Y");
        } catch (Exception e) {
            rsMap.put("chkYn", "N");
        }
        
        return rsMap;
    }
	
	
	// 비밀번호 확인 
	@RequestMapping(value="/checkPassword.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> checkPassword(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = validatePassword(param);      
        return rsMap;
    }
	

    /**
     * 비밀번호 유효성 검사
     * @param pwd 검증할 비밀번호
     * @return 유효하면 true, 아니면 false
     */
	public static Map<String, Object> validatePassword(Map<String, Object> param) {
				
		//복호화
		Map<String, Object> decMap = CryptoUtil.decryptMap(param,"pw");
	    String pwd = (String) decMap.get("pw");
	    String id = (String) param.get("id");
		
        Map<String, Object> result = new HashMap<>();

        if (pwd == null || pwd.length() < 4) {
            result.put("valid", false);
            result.put("message", "비밀번호는 4자리 이상 입력해주세요.");
            return result;
        }
        
        if (pwd.contains(id)) {
        	result.put("valid", false);
            result.put("message", "비밀번호에 아이디와 동일한 글자는 입력할 수 없습니다.");
            return result;
        }

        // 2) 허용 문자 정규식 체크 (영문/숫자/특수문자, 공백·한글 불가)
        String regex = "^(?=.*\\S)[a-zA-Z0-9`~!@#$%^&*()\\-_=+\\|{};:'\",.<>/?\\[\\]]{4,}$";
        if (!pwd.matches(regex)) {
            result.put("valid", false);
            result.put("message", "비밀번호는 영문, 숫자, 특수문자만 입력해주세요.\n한글, 공백은 입력할 수 없습니다.");
            return result;
        }

        // 3) 동일 문자 반복 체크
        String oneCharRegex = "^(.)\\1+$";
        if (pwd.matches(oneCharRegex)) {
            result.put("valid", false);
            result.put("message", "비밀번호는 같은 문자만 연속 입력할 수 없습니다.");
            return result;
        }

        // 4) 연속된 문자 3개 이상 체크
        for (int i = 0; i < pwd.length() - 2; i++) {
            char c1 = pwd.charAt(i);
            char c2 = pwd.charAt(i + 1);
            char c3 = pwd.charAt(i + 2);

            boolean isDesc = (c1 - c2 == 1) && (c2 - c3 == 1);       // 321, cba
            boolean isAsc  = (c1 - c2 == -1) && (c2 - c3 == -1);     // 123, abc

            if (isAsc || isDesc) {
                result.put("valid", false);
                result.put("message", "비밀번호는 연속된 문자 3개 이상 입력할 수 없습니다.\nex)123, abc");
                return result;
            }
        }

        // 5) 통과
        result.put("valid", true);
        result.put("message", "사용 가능한 비밀번호입니다.");
        return result;
    }
	
	@RequestMapping(value="/compPassword.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> compPassword(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<>();
		Map<String, Object> decMap = CryptoUtil.decryptMap(param,"pw");
		Map<String, Object> decMap2 = CryptoUtil.decryptMap(param,"pw2");
		String pwd = (String) decMap.get("pw");
		String pwd2 = (String) decMap2.get("pw2");

		if(!pwd.equals(pwd2)) {
			rsMap.put("valid", false);
			rsMap.put("message", "비밀번호가 일치하지 않습니다. 다시 입력하세요.");
		} else {
			rsMap.put("valid", true);
		}
		return rsMap;
	}
}
