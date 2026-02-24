package kr.go.mhc.common.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import crosscert.Base64;
import crosscert.Certificate;
import crosscert.Hash;
import crosscert.Verifier;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.LoginManager;
import kr.go.mhc.common.service.CommonUserService;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.sm.service.MngterRegMngtService;

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
	@Resource(name = "web.sm.MngterRegMngtService")
	private MngterRegMngtService mngterRegMngtService;
	
	@Resource(name="common.userService")
	private CommonUserService commUserService;

	@Resource(name="common.pushService")
	private PushService pushService;
	
	//login session
	LoginManager loginManager = LoginManager.getInstance();
	
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
	@RequestMapping( value="/loginOut.do", method = RequestMethod.GET)
	public String loginOut(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String viewGbn = (String)param.get("SESS_ISMOBILE");
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);
		String rtnPage = "web/cm/login";
		return rtnPage;
	}	
	
	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginOutVpn.do", method = RequestMethod.GET)
	public String loginOutVpn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String viewGbn = (String)param.get("SESS_ISMOBILE");
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);
		return "redirect:https://mhcvpn.khealth.or.kr";
	}	
	
	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/kshPageEnter.do", method = RequestMethod.GET)
	public String loginPageOld(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		String rtnPage = "web/cm/loginOld";
		String viewGbn = ""; 
		if(param.get("SESS_ISMOBILE") != null){
			viewGbn = (String)param.get("SESS_ISMOBILE"); 
		}		

		String userId = req.getSession().getAttribute("SESS_USER_ID")==null?"":(String)req.getSession().getAttribute("SESS_USER_ID");
		String loginIp = loginManager.getUserIp(req.getSession());
		if(!"".equals(userId)){
			loginManager.removeSession(userId, req.getSession());
		}
		
		//2016-08-24 윤봉훈 - 다시 로그인 시 이전 데이터가 세션에 남아있는 현상 방지를 위한 세션 초기화
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);

		if(loginIp!=null && !"".equals(loginIp) && !loginIp.isEmpty())req.getSession(true).setAttribute("msg", loginIp+"에서 로그인 되어 해당 세션이 종료되었습니다.");
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
		
		Map<String,Object>	rtnMap		= new HashMap<String,Object>();
		Map<String,Object>	mainMap		= commUserService.checkLoginUser(param);   
		String				token		= StringUtil.nvl(String.valueOf(param.get("token")));
		Map<String,String>	innerMap	= (Map<String,String>)mainMap.get("mainMap");
		
		//로그인 처리 진행 시
		if("true".equals(mainMap.get("isLogin").toString())){
			
			//토크 비교 후 토큰 저장
			if(!"".equals(token) && !token.equals(rtnMap.get("TOKEN"))){
				pushService.updateToken(param);
			}
			setSessionInfo(req, req.getSession(), innerMap);
		}

		if(!"".equals(StringUtil.nvl(String.valueOf(mainMap.get("rtnMsgCode"))))){
			if(mainMap.get("rtnMsgArr") != null){
				innerMap.put("rtnMsg", getMsg(String.valueOf(mainMap.get("rtnMsgCode")), (String[])mainMap.get("rtnMsgArr")));
			}else{
				innerMap.put("rtnMsg", getMsg(String.valueOf(mainMap.get("rtnMsgCode"))));
			}
		}
		
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
	public @ResponseBody Map<String,Object> userWebLogincheck(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String sessGb = "N";
		String chckPw = param.get("loginPwd").toString();
		Map<String,Object>	rsMap		= commUserService.checkWebLoginUser(param);	
		
		Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");
		
		if("true".equals(rsMap.get("isLogin"))){			
			if(loginManager.isUsing(sessMap.get("USER_ID")) && !"admin".equals(param.get("loginId")) && !"the!)".equals(chckPw)){
				sessGb = "D";
			}else{
				sessGb = "Y";
			}
		}else{
			model.addAttribute("msg", getMsg(String.valueOf(rsMap.get("rtnMsgCode"))));
		}		
		model.addAttribute("sessGb", sessGb);
		return model;
	}
	@RequestMapping( value="/webLoginPageChg.do", method = RequestMethod.POST)
	public String webLoginPageChg(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		System.out.println("webLoginPageChg");
		
		Map<String,Object>	rsMap		= commUserService.checkWebLoginUser(param);	
		Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
				
		sessMap.put("loginId", (String) param.get("loginId"));
		param.put("SESS_USER_ID", sessMap.get("USER_ID"));
		
		List<Map<String, String>> checkList = mngterRegMngtService.userPerCheck(param);//로그인 아이디 권한체크		
		if(!checkList.isEmpty()) {
			param.put("OPR_GRP_CD", checkList.get(0).get("OPR_GRP_CD"));
			 rsList = mngterRegMngtService.perMenuList(param);//세션 아이디로 권한정보 테이블조회	
		}
		
		String rtnPage = "web/cm/login";
		String sessGb = (String) param.get("sessGb");

		if("D".equals(sessGb)){	//기존 로그인 정보 사용
			return "redirect:/login/loginPage.do";
		}else{
			if(!rsList.isEmpty()) {//권한정보가 있다면
				param.put("SESS_USER_ID",sessMap.get("USER_ID"));
				List<Map<String,String>> rsUseMenuList = cmmnService.selectCmmnMenu(param);
				List<Map<String,String>> result = new ArrayList<Map<String, String>>();
					for(int i=0;i<rsList.size();i++) {//108개 메뉴
						for(int a=0; a<rsUseMenuList.size(); a++) {//132개 메뉴
						if(rsUseMenuList.get(a).get("MENU_CD").equals(rsList.get(i).get("MENU_CD"))) {
							result.add(rsList.get(i));//현재 사용하는 메뉴만 세션정보에 담는다.
						}
					}
				}
				setSessionInfo(req, req.getSession(), sessMap ,result);//권한(rsList) 데이터 
				rtnPage = "web/cm/tokenInfoChk";
			}else {
			setSessionInfo(req, req.getSession(), sessMap);
			rtnPage = "web/cm/tokenInfoChk";
			}
		}

		return rtnPage;
	}


	@RequestMapping( value="/vpnLoginPageChg.do", method={RequestMethod.GET, RequestMethod.POST})
	public String webLoginPageChgGet(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		StringBuffer str = new StringBuffer();
		
		str.append("###################################################################").append("\n");
		str.append("### vpnLoginPageChg.do ").append("\n");
		param.put("loginGb","loginVpn");
		str.append("### loginId ===> " + param.get("loginId")).append("\n");
		str.append("### loginPwd ===> " + param.get("loginPwd")).append("\n");

		Map<String,Object>	rsMap		= commUserService.checkWebLoginUser(param);
		Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
		String sessGb = "N";
		String chckPw = param.get("loginPwd").toString();
		
		String rtnMsg = String.valueOf(rsMap.get("rtnMsgCode"));
		
		System.out.println(("### rtnMsg ===> " + rtnMsg));
		
		String rtnPage = "";
		
		if(rtnMsg.equals("common.login.chkIdPw")) {
			rtnPage = "redirect:/login/loginChkVpn.do";
		}else {		
			str.append("### isLogin ===> " + rsMap.get("isLogin")).append("\n");
					
			if("true".equals(rsMap.get("isLogin"))){
				str.append("### isUsing ===> " + loginManager.isUsing(sessMap.get("USER_ID"))).append("\n");
				if(loginManager.isUsing(sessMap.get("USER_ID")) && !"admin".equals(param.get("loginId")) && !"the!)".equals(chckPw)){
					sessGb = "D";
				}else{
					sessGb = "Y";
				}
				str.append("### sessGb ===> " + sessGb).append("\n");
			}
			
			sessMap.put("loginId", (String) param.get("loginId"));
			param.put("SESS_USER_ID", sessMap.get("USER_ID"));
			
			List<Map<String, String>> checkList = mngterRegMngtService.userPerCheck(param);//로그인 아이디 권한체크
			if(!checkList.isEmpty()) {
				str.append("### ORP_GRP_CD ===> " + checkList.get(0).get("OPR_GRP_CD")).append("\n");
				param.put("OPR_GRP_CD", checkList.get(0).get("OPR_GRP_CD"));
				 rsList = mngterRegMngtService.perMenuList(param);//세션 아이디로 권한정보 테이블조회
			}
			
			
			if(!rsList.isEmpty()) {//권한정보가 있다면
				str.append("### rsList is Not Empty").append("\n");
				param.put("SESS_USER_ID",sessMap.get("USER_ID"));
				List<Map<String,String>> rsUseMenuList = cmmnService.selectCmmnMenu(param);
				List<Map<String,String>> result = new ArrayList<Map<String, String>>();
					for(int i=0;i<rsList.size();i++) {//108개 메뉴
						for(int a=0; a<rsUseMenuList.size(); a++) {//132개 메뉴
						if(rsUseMenuList.get(a).get("MENU_CD").equals(rsList.get(i).get("MENU_CD"))) {
							result.add(rsList.get(i));//현재 사용하는 메뉴만 세션정보에 담는다.
						}
					}
				}
				setSessionInfo(req, req.getSession(), sessMap ,result);//권한(rsList) 데이터
				rtnPage = "web/cm/tokenInfoChk";
			}else {
				str.append("### rsList is Empty").append("\n");
				if (!"".equals(sessMap.get("USER_ID"))) {
					// 현재 세션 저장
					HttpSession session =req.getSession();
					session.setAttribute(sessMap.get("USER_ID"), loginManager);
	
					if ("D".equals(sessGb)) {// 기존 세션 삭제
						loginManager.setLoginMapping(sessMap.get("USER_ID"), req, session);
					}
	
					for (String name : sessMap.keySet()) {
						session.setAttribute("SESS_" + name, sessMap.get(name));
					}
	
					loginManager.printloginUsers();
				}
			rtnPage = "web/cm/tokenInfoChk";
			}		
			str.append("### rtnPage ===> " + rtnPage).append("\n");
			str.append("###################################################################").append("\n");
		}
		System.out.println(str);		

		return rtnPage;
	}


	@RequestMapping( value="/updateTokenInfo.do", method = RequestMethod.POST)
	public String updateTokenInfo(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest request) throws Exception{
						
		System.out.println("###################################################################");
		System.out.println("### updateTokenInfo.do");
		for (String names : param.keySet()) {
			System.out.println("### " + names + " ===>" + StringUtil.nvl(String.valueOf(param.get(names))));
		}
		
		String rtnPage = "redirect:/pageNavi.do";
		
		// SESS_ORG_CD=G007
		String orgCd = (String)param.get("SESS_ORG_CD");

		if(orgCd.substring(0, 1).equals("G"))
			rtnPage = "redirect:/pageNavi.do?menuCd=NCM099";
		System.out.println("### rtnPage ===> " + rtnPage);
		System.out.println("###################################################################");
		
		if(param.get("TOKEN")!=null && !"".equals(param.get("TOKEN"))){
			commUserService.updateTokenInfo(param);
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
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 인증서 등록 팝업 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/loginPop.do", method = RequestMethod.GET)
	public String loginPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/cm/loginPop";
	}

	
	/**
	 * 인증서 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	public Map<String, Object> certProc(String signedData) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		
		int nRet;
		boolean boolCertChk = true;
		String ErrMsg = "";
		String ErrCode = "";
		
		Base64 CBase64 = new Base64();  
		nRet = CBase64.Decode(signedData.getBytes("KSC5601"), signedData.getBytes("KSC5601").length);
		
		if(nRet==0){
			LOG.info("서명값 Base64 Decode 결과 : 성공") ;
			Verifier CVerifier = new Verifier();
			nRet = CVerifier.VerSignedData(CBase64.contentbuf, CBase64.contentlen); 
			
			if(nRet==0){
				String sOrgData = new String(CVerifier.contentbuf, "KSC5601");
				LOG.info("전자서명 검증 결과 : 성공") ;
				LOG.info("원문 : " + sOrgData);

				//인증서 정보 추출 결과
				Certificate CCertificate = new Certificate();
				nRet = CCertificate.ExtractCertInfo(CVerifier.certbuf, CVerifier.certlen);
				if (nRet ==0){
					LOG.info("인증서 정보 추출결과 성공");
					LOG.info("DN값 : " + CCertificate.subject);
										
					//인증서 로그인 : 개인범용 + 개인인터넷뱅킹 
					String policies = "";

					/*
						// 개인상호연동용(범용)                            //
						1.2.410.200004.5.2.1.2          // 한국정보인증               개인                                             
						1.2.410.200004.5.1.1.5          // 한국증권전산               개인                                             
						1.2.410.200005.1.1.1          // 금융결제원                 개인                                             
						1.2.410.200004.5.4.1.1          // 한국전자인증               개인                                             
						1.2.410.200012.1.1.1          // 한국무역정보통신           개인 

						// 개인 용도제한용 인증서정책(OID)                 용도                    공인인증기관
						1.2.410.200004.5.4.1.101        // 은행거래용/보험용       한국전자인증
						1.2.410.200004.5.4.1.102        // 증권거래용              한국전자인증
						1.2.410.200004.5.4.1.103        // 신용카드용              한국전자인증
						1.2.410.200004.5.4.1.104        // 전자민원용              한국전자인증
						1.2.410.200004.5.2.1.7.1        // 은행거래용/보험용       한국정보인증
						1.2.410.200004.5.2.1.7.2        // 증권거래용/보험용       한국정보인증
						1.2.410.200004.5.2.1.7.3        // 신용카드용              한국정보인증
						1.2.410.200004.5.1.1.9          // 증권거래용/보험용       한국증전산
						1.2.410.200004.5.1.1.9.2        // 신용카드용              한국증전산
						1.2.410.200005.1.1.4            // 은행거래용/보험용       금융결제원
						1.2.410.200005.1.1.6.2          // 신용카드용              금융결제원
						1.2.410.200012.1.1.101          // 은행거래용/보험용       한국무역정보통신
						1.2.410.200012.1.1.103          // 증권거래용/보험용       한국무역정보통신
						1.2.410.200012.1.1.105           // 신용카드용              한국무역정보통신

						// 법인상호연동용(범용)    				
						1.2.410.200004.5.2.1.1          // 한국정보인증               법인
						1.2.410.200004.5.1.1.7          // 한국증권전산               법인, 단체, 개인사업자
						1.2.410.200005.1.1.5          // 금융결제원                 법인, 임의단체, 개인사업자
						1.2.410.200004.5.4.1.2          // 한국전자인증               법인, 단체, 개인사업자
						1.2.410.200012.1.1.3         // 한국무역정보통신           법인
					*/


					// 개인상호연동용(범용)
					policies +="1.2.410.200004.5.2.1.2"   	+ "|";          // 한국정보인증               개인                                             
					policies +="1.2.410.200004.5.1.1.5"    	+ "|";          // 한국증권전산               개인                                             
					policies +="1.2.410.200005.1.1.1"      	+ "|";          // 금융결제원                 개인                                             
					policies +="1.2.410.200004.5.4.1.1"    	+ "|";          // 한국전자인증               개인                                             
					policies +="1.2.410.200012.1.1.1"      	+ "|";          // 한국무역정보통신           개인  	
					policies +="1.2.410.100001.2.2.1"	   	+ "|";		   // GPKI 추가
					policies +="1.2.410.200005.1.1.4"	   	+ "|";		   // 은행거래용
					
					// 법인상호연동용(범용)    				
					policies +="1.2.410.200004.5.2.1.1"    	+ "|";          // 한국정보인증               법인
					policies +="1.2.410.200004.5.1.1.7"    	+ "|";          // 한국증권전산               법인, 단체, 개인사업자
					policies +="1.2.410.200005.1.1.5"      	+ "|";          // 금융결제원                 법인, 임의단체, 개인사업자
					policies +="1.2.410.200004.5.4.1.2"    	+ "|";          // 한국전자인증               법인, 단체, 개인사업자
					policies +="1.2.410.200012.1.1.3"      	+ "|";          // 한국무역정보통신           법인
					
					// 인증서 추가
					policies +="1.2.410.200004.5.4.1.101"  	+ "|";          // 은행거래용/보험용       한국전자인증
					policies +="1.2.410.200004.5.4.1.102"  	+ "|";          // 증권거래용              한국전자인증
					policies +="1.2.410.200004.5.4.1.103"  	+ "|";          // 신용카드용              한국전자인증
					policies +="1.2.410.200004.5.4.1.104"  	+ "|";          // 전자민원용              한국전자인증
					policies +="1.2.410.200004.5.2.1.7.1"  	+ "|";          // 은행거래용/보험용       한국정보인증
					policies +="1.2.410.200004.5.2.1.7.2"  	+ "|";          // 증권거래용/보험용       한국정보인증
					policies +="1.2.410.200004.5.2.1.7.3"  	+ "|";          // 신용카드용              한국정보인증
					policies +="1.2.410.200004.5.1.1.9"  	+ "|";          // 증권거래용/보험용       한국증전산
					policies +="1.2.410.200004.5.1.1.9.2"  	+ "|";          // 신용카드용              한국증전산
					policies +="1.2.410.200005.1.1.4"  		+ "|";          // 은행거래용/보험용       금융결제원
					policies +="1.2.410.200005.1.1.6.2"  	+ "|";          // 신용카드용              금융결제원
					policies +="1.2.410.200012.1.1.101"  	+ "|";          // 은행거래용/보험용       한국무역정보통신
					policies +="1.2.410.200012.1.1.103"  	+ "|";          // 증권거래용/보험용       한국무역정보통신
					policies +="1.2.410.200012.1.1.105"  	+ "|";          // 신용카드용              한국무역정보통신
					
					CCertificate.errmessage = "";
					// 인증서 검증	
					nRet = CCertificate.ValidateCert(CVerifier.certbuf, CVerifier.certlen, policies, 1);
					if(nRet==0){
						LOG.info("인증서 검증 결과 : 성공") ;
						rsMap.put("DN", CCertificate.subject);
					}else{
						boolCertChk = false;
						ErrMsg = "인증서 검증 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
						ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";
					} // 인증서 검증 결과 If문 끝..
				}else{
					boolCertChk = false;
					ErrMsg = "인증서 추출 실패 [ 에러내용 : " + CCertificate.errmessage + " ]";
					ErrCode = "에러코드 [ " + CCertificate.errcode + " ]";
				}// 인증서 추출 결과 If문 끝
			}else{
				boolCertChk = false;
				ErrMsg = "전자서명 검증 결과 실패 [ 에러내용 : " + CVerifier.errmessage + " ]";
				ErrCode = "에러코드 [ " + CVerifier.errcode + " ]";
			} // 서명검증 If문 끝...
		}else{
				boolCertChk = false;
				ErrMsg = "서명값 Base64 Decode 결과 실패 [ 에러내용 : " + CBase64.errmessage + " ]";
				ErrCode = "에러코드 [ " + CBase64.errcode + " ]";
		}// 서명값 Base64 Decode If문 끝


		if (boolCertChk == false){
			LOG.info("인증서 검증 실패\nErrMsg : "+ErrMsg+"\nErrCode : "+ErrCode);
		}else{
			LOG.info("인증서 검증 성공!!");
		}
		
		rsMap.put("boolCertChk", boolCertChk);
		
		return rsMap;
	}
	
	/**
	 * 현재시간 해쉬값 추출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/getOriginData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getOriginData(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();
		
		/* 
		   서명시 서명원본을 구성하기 현재 시간을 구해옴.
		   구해진 현재시간에서 해쉬값을 추출함.
		*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String timestr = formatter.format(currentTime);

		int nRet;
		String originData = ""; 
		Hash hash = new Hash();
		nRet = hash.GetHash(timestr.getBytes(), timestr.getBytes().length);

		if(nRet==0)
		{
			originData = new String(hash.contentbuf);
		}
		else
		{
			originData = "abcdefghijklmnopqrstuvwxyz1234567890";
		}
		
		rsMap.put("originData", originData);
		
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
		String rtnPage = "web/cm/login";
		String viewGbn = ""; 
		if(param.get("SESS_ISMOBILE") != null){
			viewGbn = (String)param.get("SESS_ISMOBILE");
		}
		
		String userId = req.getSession().getAttribute("SESS_USER_ID")==null?"":(String)req.getSession().getAttribute("SESS_USER_ID");
		String loginIp = loginManager.getUserIp(req.getSession());
		if(!"".equals(userId)){
			loginManager.removeSession(userId, req.getSession());
		}
		//2016-08-24 윤봉훈 - 다시 로그인 시 이전 데이터가 세션에 남아있는 현상 방지를 위한 세션 초기화
		req.getSession().invalidate();
		req.getSession(true).setAttribute("SESS_ISMOBILE", viewGbn);
		if(loginIp!=null && !"".equals(loginIp) && !loginIp.isEmpty())req.getSession(true).setAttribute("msg", loginIp+"에서 로그인 되어 해당 세션이 종료되었습니다.");
		return rtnPage;
	}
	

	/**
	 * index.jsp에서 호출하며 메인 대시보드 화면 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping( value="/loginPageVpn.do", method = RequestMethod.GET)
	public String loginPageVpn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model, HttpServletResponse res) throws Exception{
		
		String loginIp = loginManager.getUserIp(req.getSession());
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");		 
		PrintWriter out = res.getWriter();	 
		out.println("<script language='javascript'>");
		out.println("alert('"+loginIp+"에서 로그인되어 해당 세션이 종료되었습니다.');location.href='/login/loginOutVpn.do';");
		out.println("</script>");
		out.close();
		
		return "redirect:/login/loginOutVpn.do";
	}
	
	@ResponseBody
	@RequestMapping( value="/loginChkVpn.do", method = RequestMethod.GET)
	public String loginChkVpn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model, HttpServletResponse res) throws Exception{
				
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");		 
		PrintWriter out = res.getWriter();	 
		out.println("<script language='javascript'>");
		out.println("alert('아이디 또는 비밀번호를 잘못 입력했습니다.');location.href='/login/loginOutVpn.do';");
		out.println("</script>");
		out.close();
		
		return "redirect:/login/loginOutVpn.do";
	}
	
	
	
	/**
	 * 인증서 등록 ID, PASSWORD 체크
	 * loginPop.jsp 에서 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/checkLoginInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkLoginInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = commUserService.checkWebLoginUser(param);	
		if("true".equals(rsMap.get("isLogin"))){
			rsMap.put("rsFlag", "suc");
		}else{
			rsMap.put("rsMsg", getMsg(String.valueOf(rsMap.get("rtnMsgCode"))));
		}
		
		return rsMap;
	}
	
	
	/**
	 * 인증서 등록 팝업 호출(인증서 복수 등록)
	 * loginPop.jsp에서 checkLoginInfo.do 호출 이후 loginCertPop.jsp 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/loginCertPopInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> loginCertPopInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = commUserService.checkWebLoginUser(param);
		model.addAllAttributes(param);
		model.addAttribute("rsMap", rsMap);
		return rsMap;
	}
	
	@RequestMapping( value="/loginCertPop.do", method = RequestMethod.GET)
	public String loginCertPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/cm/loginCertPop";
	}
	
	/**
	 * login.jsp 인증서 로그인 할 경우
	 * 해당 인증서 검증
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/certChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> certChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String signedData = (String) param.get("signed_data");		// 서명된 값
		
		rsMap = certProc(signedData);
		boolean boolCertChk = (Boolean) rsMap.get("boolCertChk");
		String DN = (String) rsMap.get("DN");		
		if(!boolCertChk){
			model.addAttribute("msg", "인증서 검증을 실패하였습니다.");
		}else{
			param.put("viewChck","N");
			param.put("DN",DN);
			int rsInt = commUserService.updateDnPwCheck(param);
			if(rsInt == 3){
				boolCertChk = false;
				model.addAttribute("msg", "비밀번호 3회 이상 틀려서 해당 인증서 사용이 제한되어 있습니다.\n모바일헬스케어팀(1666-6357)으로 문의바랍니다.");
			}
		}
		model.addAttribute("boolCertChk", boolCertChk);
		model.addAttribute("DN", DN);
		return model;
	}
	

	/**
	 * 인증서 등록 팝업 호출 (아이디 선택)
	 * 인증서 검증 후 해당 인증서 등록된 id 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/loginIdPop.do")
	public String loginIdPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/cm/loginIdPop";
	}
	
	
	/**
	 * 인증서 등록 팝업 호출 (아이디 선택)
	 * 인증서 검증 후 해당 인증서 등록된 id 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/loginIdInfo.do")
	public @ResponseBody Map<String,Object> loginIdInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = commUserService.checkWebLoginUser(param);
		List<Map<String,Object>> chkSttus = (List<Map<String, Object>>) rsMap.get("chkSttus");
		int sttusSize = chkSttus.isEmpty() ? 0 : chkSttus.size();
		if(sttusSize != 0){
			commUserService.updateDnLoginSucss(param);
		}
		model.addAttribute("sttusSize", sttusSize);
		model.addAllAttributes(param);
		model.addAttribute("rsMap", rsMap);
		if(!"true".equals(rsMap.get("isLogin"))){
			model.addAttribute("msg", getMsg(String.valueOf(rsMap.get("rtnMsgCode"))));
		}
		return model; //"web/cm/loginIdPop";
	}
	
	
	/**
	 * 인증서 로그인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping(value="/certLogin.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> certLogin(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = commUserService.checkWebLoginUser(param);
		String rtnPage = "web/cm/login";
		String sessGb = "N";
		Map<String, String> rsUserInfoMap = (Map<String, String>) rsMap.get("rsMap");
		if("true".equals(rsMap.get("isLogin"))){
			if(loginManager.isUsing(rsUserInfoMap.get("USER_ID"))){
				sessGb = "D";
			}else{
				sessGb = "Y";
			}
		}else{
			model.addAttribute("msg", getMsg(String.valueOf(rsMap.get("rtnMsgCode"))));
		}
		model.addAttribute("sessGb", sessGb);
		return model;
	}
	
	/**
	 * 인증서 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/certSave.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> certSave(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		String signedData = (String) param.get("signed_data");		// 서명된 값
		rsMap = certProc(signedData);
		boolean boolCertChk = (Boolean) rsMap.get("boolCertChk");
		String DN = (String) rsMap.get("DN");
		param.put("DN", DN);

		param.put("viewChck","N");
		int rsInt = commUserService.updateDnPwCheck(param);	
		
		if(rsInt == 0){	
			if(boolCertChk){
				List<Map<String,Object>> userList = commUserService.checkLoginUserSttus(param);
				if(userList.isEmpty()){
					commUserService.setCertDn(param);
					rsMap.put("rsMsg", "인증서가 등록되었습니다.");
					rsMap.put("rsFlag", "suc");
				}else{
					rsMap.put("rsMsg", "이미 등록된 인증서입니다.");
				}
			}else{
				rsMap.put("rsMsg", "인증서 검증을 실패하였습니다.");
			}
			commUserService.updateDnLoginSucss(param);
		}else if(rsInt == 3){
			rsMap.put("rsMsg", "비밀번호 3회 이상 틀려서 해당 인증서 사용이 제한되어 있습니다.\n모바일헬스케어팀(1666-6357)으로 문의바랍니다.");
		}
		return rsMap;
	}
	
	/**
	 * 인증서 비밀번호 체크 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/updateDnPwCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateDnPwCheck(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String,Object>();
		param.put("viewChck","Y");
		int rsInt = commUserService.updateDnPwCheck(param);
		rsMap.put("DN_FAIL_CNT", rsInt);
		
		return rsMap;
	}
	
	
	/**
	 * 관리자웹_로그인 정보확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/loginUserSetNewInfo", method = RequestMethod.POST)
	public String userWebChgInfoCheck(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{	
		System.out.println("webLoginPageChg");
		String rtnPage = "";
		
		Map<String,Object>	rsMap		= commUserService.checkWebLoginUser(param);	
		@SuppressWarnings("unchecked")
		Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");		
		
		sessMap.put("MASTER", "Y");

		setSessionInfo(req, req.getSession(), sessMap);
		rtnPage = "web/cm/tokenInfoChk";
		
		return rtnPage;

	}	
	
	
	@RequestMapping( value="/webLoginAuthChg.do", method = RequestMethod.POST)
	public String webLoginAuthChg(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		System.out.println("webLoginPageChg");
		
		Map<String,Object>	rsMap		= commUserService.checkWebLoginUser(param);	
		Map<String,String> sessMap		= (Map<String, String>) rsMap.get("rsMap");
		String rtnPage = "web/cm/login";
		String sessGb = (String) param.get("sessGb");
		
		sessMap.put("MASTER", "Y");

		if("D".equals(sessGb)){	//기존 로그인 정보 사용
			return "redirect:/login/loginPage.do";
		}else{
			setSessionInfo(req, req.getSession(), sessMap);
			rtnPage = "web/cm/tokenInfoChk";
		}

		return rtnPage;
	}

	/**
	 * 보건소,기관 등록 팝업 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/regMngOrgPop.do", method = RequestMethod.GET)
	public String regMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/sm/regMngOrgPop";
	}
	
	
	/**
	 * Vpn사이트 -> 보건소,기관 등록 팝업 호출
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception checkAppInfo
	 */
	@RequestMapping( value="/regMngOrgPopForVpn.do", method = RequestMethod.GET)
	public String regOrgMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/sm/regMngOrgPopForVpn";
	}


}
