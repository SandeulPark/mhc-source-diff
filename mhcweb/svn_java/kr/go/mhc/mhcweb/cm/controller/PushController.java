package kr.go.mhc.mhcweb.cm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.crontab.PushBatchSender;
import kr.go.mhc.common.service.CommonUserService;
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.cm.service.impl.PushServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping(value="/push")
public class PushController extends DMultiActionController{ 
	
	
	@Resource(name="common.userService")
	private CommonUserService userService;
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	@Autowired
	private PushBatchSender pushBatchSender;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	
	/**
	 * 푸시 타이틀, 내용 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/getPushSetInfo.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getPushSetInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = pushService.getPushSetInfo(param);
		
		 if (rsMap == null) {
		        rsMap = new HashMap<String,Object>();
		    }
		
		
		return rsMap;
	}
		
	
	/**
	 * 푸시 사용자 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/pushUserList.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUserList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//토큰이 등록된 사용자만 불러옴
		param.put("isPushUser", "Y");
		List<Map<String,String>> userList = userService.selectUserList(param);
		LOG.debug("userList===="+userList);
		rsMap.put("userList", userList);
		return rsMap;
	}
	
	/**
	 * 푸시 토큰 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectTokenList.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectTokenList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String userId = StringUtil.nvl(String.valueOf(param.get("USER_ID")));
		List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
		List<Map<String,Object>> tokenList = null;
		if(userIter.size() > 0){
			param.put("userIter", userIter);
			tokenList = pushService.selectTokenList(param);
			rsMap.put("tokenList", tokenList);
		}
		return rsMap;
	}
	
	
	@RequestMapping( value="/pushUpdateToken.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUpdateToken(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//토큰이 등록된 사용자만 불러옴
		try{
			LOG.debug("token===="+param.get("token"));
			String token = param.get("token")!=null?param.get("token").toString():null;
			if(token != null){
				LOG.debug("update Token");
				pushService.updateToken(param);
			}
		}catch(Exception e){
			
		}
		return rsMap;
	}
	
	//푸시메시지 전송
	@RequestMapping( value="/pushFcmSend.do", method=RequestMethod.POST )
//	public @ResponseBody Map<String,Object> fcmSend(HttpServletRequest req, @ModelAttribute Map param, ModelMap model) throws Exception{
	public String fcmSend(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		String pushTitle		= StringUtil.nvl(String.valueOf(param.get("pushTitle")));
		String pushCont			= StringUtil.nvl(String.valueOf(param.get("pushCont")));		
		String noticeTitle		= StringUtil.nvl(String.valueOf(param.get("noticeTitle")));
		String noticeCont		= StringUtil.nvl(String.valueOf(param.get("noticeCont")));		
		String noticeEndDe		= StringUtil.nvl(String.valueOf(param.get("noticeEndDe")));				
		String msgClf			= StringUtil.nvl(String.valueOf(param.get("msgClf")));
		String rcvClf			= StringUtil.nvl(String.valueOf(param.get("rcvClf")),PushMessageUtil.RCVCLF_I);
		String sndOrgCd			= StringUtil.nvl(String.valueOf(param.get("sndOrgCd")),param.get("SESS_ORG_CD").toString());		
		String sndUserId		= StringUtil.nvl(String.valueOf(param.get("sndUserId")),param.get("SESS_USER_ID").toString());	
		String autoManuClf		= StringUtil.nvl(String.valueOf(param.get("autoManuClf")),PushMessageUtil.AUTOMANUCLF_M);
		String pushLinkPage		= StringUtil.nvl(String.valueOf(param.get("pushLinkPage")),PushMessageUtil.LINK_PAGE);
		String noticeLinkPage	= StringUtil.nvl(String.valueOf(param.get("noticeLinkPage")),PushMessageUtil.LINK_PAGE);
		String noticeSetNo		= StringUtil.nvl(String.valueOf(param.get("noticeSetNo")));
//		20170905 푸시 예약 추가(이태석)
		String reqClf		= StringUtil.nvl(String.valueOf(param.get("reqClf")));
		String resrvtDe		= StringUtil.nvl(String.valueOf(param.get("resrvtDe")));
		String resrvtTm		= StringUtil.nvl(String.valueOf(param.get("resrvtTm")));
		
		String rtnPage			= StringUtil.nvl(String.valueOf(param.get("rtnPage")),"redirect:/");
		
		String[] userId			= req.getParameterValues("userId") != null ? req.getParameterValues("userId") : req.getParameterValues("userId[]");
		String[] tokens			= req.getParameterValues("token") != null ? req.getParameterValues("token") : req.getParameterValues("token[]");

		
		if(!"".equals(pushTitle) && !"".equals(noticeTitle)){
			pushMessageUtil.setNoticeClf(PushMessageUtil.NOTICECLF_A);
		}else if(!"".equals(pushTitle) && "".equals(noticeTitle)){
			pushMessageUtil.setNoticeClf(PushMessageUtil.NOTICECLF_P);
		}else if("".equals(pushTitle) && !"".equals(noticeTitle)){
			pushMessageUtil.setNoticeClf(PushMessageUtil.NOTICECLF_N);
		}
		pushMessageUtil.setSndOrgCd(sndOrgCd);
		pushMessageUtil.setSndUserId(sndUserId);
		pushMessageUtil.setMsgClf(msgClf);
		pushMessageUtil.setPushTitle(pushTitle);
		pushMessageUtil.setPushCont(pushCont);
		pushMessageUtil.setNoticeTitle(noticeTitle);
		pushMessageUtil.setNoticeCont(noticeCont);
		pushMessageUtil.setRcvClf(rcvClf);
		pushMessageUtil.setAutoManuClf(autoManuClf);
		pushMessageUtil.setPushLinkPage(pushLinkPage);
		pushMessageUtil.setNoticeLinkPage(noticeLinkPage);
		pushMessageUtil.setNoticeSetNo(noticeSetNo);
		
//		20170905 푸시 예약 추가(이태석)
		pushMessageUtil.setReqClf(reqClf);
		pushMessageUtil.setResrvtDe(resrvtDe);
		pushMessageUtil.setResrvtTm(resrvtTm);
		
		pushMessageUtil.setUserId(userId);
		pushMessageUtil.setTokens(tokens);
		
		pushMessageUtil.setNoticeEndDe(noticeEndDe);

		List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
		param.put("userIter", userIter);
		List<Map<String,Object>> userInfoList = pushService.selectPushInfoBadgeList(param);
		pushMessageUtil.setPushInfo(userInfoList);
		
		if(PushMessageUtil.NOTICECLF_N.equals(pushMessageUtil.getNoticeClf())){
			pushMessageUtil.sendNotifition();
		}else{
			pushMessageUtil.sendPushMessage(msgClf);
		}
		
		if("true".equals(StringUtil.nvl(String.valueOf(param.get("pushInsert"))))){	// 20161012 윤봉훈 - 채팅일때는 푸시만 날리고 저장 안함.
			if(rcvClf.equals(PushMessageUtil.RCVCLF_A)){
				pushService.insertPushHisTopic(pushMessageUtil.getResultMap());
			}else if(rcvClf.equals(PushMessageUtil.RCVCLF_H)){
				pushService.insertPushHisTopic(pushMessageUtil.getResultMap());
			}else if(rcvClf.equals(PushMessageUtil.RCVCLF_I)){
				pushService.insertPushHis(pushMessageUtil.getResultMap());
			}
		}

		return rtnPage;
//		return rsMap;
	}
	
	//푸시메시지 전송
	@RequestMapping( value="/insertPushData.do", method=RequestMethod.POST )
	public @ResponseBody Map<String,Object> insertPushData(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		pushMessageUtil.setReqData(req,param);
		
		if(pushMessageUtil.sendNotifition()){
			pushService.insertPushHis(pushMessageUtil.getResultMap());
		}
		return pushMessageUtil.getResultMap();
	}
	
	//푸시메시지 전송
	@RequestMapping( value="/sendPushUsers.do", method=RequestMethod.POST )
	public @ResponseBody Map<String,Object> sendPushUsers(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		String userId = StringUtil.nvl(String.valueOf(param.get("userId")));
		if(!"".equals(userId)){
			List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
			param.put("userIter", userIter);
		}
		List<Map<String,Object>> sendList = pushService.selectSendList(param);
		
		if (sendList != null && !sendList.isEmpty()) {
			Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);				
			pushService.updatePushHis(resultMap);
			rsMap.put("resultList", resultMap);
        }
				
		return rsMap;
	}
	
	
	//상담 알림 링크 페이지 이동
	@RequestMapping( value="/linkPage.do")
	public String linkPage(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String url = "";
		String sessUserId = StringUtil.nvl((String) req.getSession().getAttribute("SESS_USER_ID")); 
		String cnslClf = StringUtil.nvl(String.valueOf(param.get("cnslClf")));
		String chatClf = StringUtil.nvl(String.valueOf(param.get("chatClf")));
		String userId = StringUtil.nvl(String.valueOf(param.get("userId")));
		String chatSn = StringUtil.nvl(String.valueOf(param.get("chat_sn")));
		String cnslSn = StringUtil.nvl(String.valueOf(param.get("cnsl_sn")));
		
		if("".equals(sessUserId)){
			url = "forward:/login/loginPage.do";	
		}else{
			LOG.debug("rcvUserId===="+param.get("rcvUserId"));
			pushService.pushUpdateCnfm(param);
			
			//pushService.pushChatStatus(param);	//상담 글 등록 시 업데이트
			FlashMap flashMap = RequestContextUtils.getOutputFlashMap(req);
			flashMap.put("cnslClf", cnslClf);
			flashMap.put("chatClf", chatClf);
			flashMap.put("userId", userId);
			flashMap.put("chatSn", chatSn);
			flashMap.put("cnslSn", cnslSn);
			flashMap.put("pushLinkYn", "Y");
			
			url = "redirect:/pageNavi.do?menuCd=WSV200";
		}
		return url;
	}
	
	
	@RequestMapping( value="/pushUpdateStatus.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUpdateStatus(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model){
		String sessUserId = StringUtil.nvl((String) req.getSession().getAttribute("SESS_USER_ID")); 
		Map<String,Object> rsMap = new HashMap<String,Object>();

		try{
			if("".equals(sessUserId)){
				rsMap.put("chkYn", "N");
			}else{
				LOG.debug("rcvUserId===="+param.get("rcvUserId"));
				pushService.updatePushStatus(param);
				rsMap.put("chkYn", "Y");
			}
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/pushUpdateCnfm.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushUpdateCnfm(@ModelAttribute Map<String,Object> param, ModelMap model){
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			LOG.debug("rcvUserId===="+param.get("rcvUserId"));
			pushService.pushUpdateCnfm(param);
			rsMap.put("chkYn", "Y");
			
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/selectPushCnslCnt.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectPushCnslCnt(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try {
			List<Map<String,Object>> pushCnslCntList = pushService.selectPushCnslCnt(param);
			rsMap.put("pushCnslCntList", pushCnslCntList);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/pushChatStatus.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> pushChatStatus(@ModelAttribute Map<String,Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			pushService.pushChatStatus(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}

	@RequestMapping( value="/pushTest.do", method=RequestMethod.GET)
	public void pushTest() {
		try {

			String sndUserId = "VISIT_SCH_RESVRT_PUSH";
			// 1. 기관별 푸시 대상자(건수) 가져오기
			List<Map<String,Object>> sendCntList = pushService.selectVisitSchResvrtSendCnt();
			
			if(sendCntList.size() > 0) {

				for(Map<String, Object> sendCntMap: sendCntList) {
					
					if(sendCntMap.get("SEND_DE").toString().equals("N"))	// 푸시전송일자가 아니면 SKIP
						continue;
					
					Date from = new Date();
					SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					String sndSn = transFormat.format(from);

					Map<String, Object> insPushMasMap = new HashMap<>();
					insPushMasMap.put("sndSn", sndSn);
					insPushMasMap.put("noticeClf", "A");	// A: 전체, N: 알림, P: 푸시
					insPushMasMap.put("sndUserId", sndUserId);
					insPushMasMap.put("sndOrgCd", sendCntMap.get("ORG_CD").toString());
					insPushMasMap.put("sndUserId", "VISIT_SCH_RESVRT_PUSH");
					insPushMasMap.put("sndSttus", "S");	// S: 성공, F: 실패
					insPushMasMap.put("sndCnt", sendCntMap.get("PUSH_CNT").toString());
					insPushMasMap.put("msgClf", "NT");	// NO: 공지, NT: 알림, OB: 목표
					insPushMasMap.put("pushTitle", (String)sendCntMap.get("PUSH_TITLE"));
					insPushMasMap.put("pushCont", (String)sendCntMap.get("PUSH_CONT"));
					insPushMasMap.put("noticeTitle", (String)sendCntMap.get("NOTICE_TITLE"));
					insPushMasMap.put("noticeCont", (String)sendCntMap.get("NOTICE_CONT"));
					insPushMasMap.put("noticeLinkPage", (String)sendCntMap.get("NOTICE_LINK_PAGE"));
					insPushMasMap.put("rcvClf", "I");	// A: 전체, H: 보건소, I: 개인
					insPushMasMap.put("autoManuClf", "A");	// A: 자동, M: 수동, R: 재전송
					insPushMasMap.put("pushLinkPage", (String)sendCntMap.get("PUSH_LINK_PAGE"));
					insPushMasMap.put("reqClf", "20");	// 10: 직접, 20: 예약
					insPushMasMap.put("resrvtDe", sendCntMap.get("SEND_DE").toString());	// 푸시보낼날짜
					insPushMasMap.put("resrvtTm", "080000");	// 시 분 초
					
					// 푸시전송마스터(TN_SV_PUSH_SND_MASTR)테이블 insert
					pushService.insertResvrtPushMas(insPushMasMap);

					// 2. 기관별 푸시 대상자(리스트) 가져오기
					List<Map<String,Object>> sendList = pushService.selectVisitSchResvrtSendList(sendCntMap.get("ORG_CD").toString());		
					for(Map<String, Object> sendListMap: sendList) {
						
						if(sendListMap.get("SEND_DE").toString().equals("N"))	// 푸시전송일자가 아니면 SKIP
							continue;
						
						Map<String, Object> insPushHisMap = new HashMap<>();
						insPushHisMap.put("sndSn", sndSn);
						insPushHisMap.put("rcvUserId", sendListMap.get("USER_ID").toString());
						insPushHisMap.put("sndUserId", sndUserId);
						insPushHisMap.put("sndSttus", "12");	// SND_STTUS 10: 대기, 12: 예정, 20: 성공, 30: 실패
						
						// 푸시전송히스토리(TH_SV_PUSH_SND_HIST)테이블 insert
						pushService.insertResvrtPushHis(insPushHisMap);
					}
				
//					Thread.sleep(1000 * 60);	// 기관별로 푸시대상자한테 푸시를 다 보내면 1분간 sleep
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}
