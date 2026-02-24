package kr.or.khealth.smhc.smhcweb.cm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.crontab.PushBatchSender;
import kr.or.khealth.smhc.common.service.CommonUserService;
import kr.or.khealth.smhc.common.util.PushMessageUtil;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;

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
	 * 푸시 사용자 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/pushUserList.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectCmmnMenu(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
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
		String msgclf			= StringUtil.nvl(String.valueOf(param.get("msgclf")));
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
		pushMessageUtil.setMsgClf(msgclf);
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
			pushMessageUtil.sendPushMessage(msgclf);
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
		
		if(sendList != null){
			
			System.out.println("sendList :::::::: " + sendList);
			
			if (sendList != null && !sendList.isEmpty()) {
				Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);				
				pushService.updatePushHis(resultMap);
				rsMap.put("resultList", resultMap);
	        }
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
}
