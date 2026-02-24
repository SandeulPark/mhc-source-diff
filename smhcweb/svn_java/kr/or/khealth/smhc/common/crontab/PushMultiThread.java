package kr.or.khealth.smhc.common.crontab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;

//public class PushMultiThread implements Runnable {
public class PushMultiThread extends Thread {
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	private int sendListSize;
		
	public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	

	public static final String API_KEY = "AAAA8cb0I_w:APA91bHpCzuAD5E0eltLtop17nGr7I2cmp5oQvHUlWAFS7Gyjf-fPyqjR-Ze5Rh2H3s6W9wFfuU7-jvSEgCANesoBvAvEDh8EJleo68EGL8M7flQXjLBFYkU4eH2nLqGab5XmWLrj7D0";
    public static final String USER_LIST = "userInfoList";
    public static final String LINK_PAGE = "/index.html";	
	
	public static final String RCVCLF_A = "A";  /* 전체 */
	public static final String RCVCLF_H = "H";  /*보건소 */
	public static final String RCVCLF_I = "I";  /*개인별 */

	public static final String NOTICECLF_A = "A";  /*전체 */
	public static final String NOTICECLF_N = "N";  /*알림 */
	public static final String NOTICECLF_P = "P";  /*푸시 */
	
	public static final String AUTOMANUCLF_M = "M";  /* 수동 */
	public static final String AUTOMANUCLF_A = "A";  /* 자동 */

	public static final int ERR_PUSH_SETVAL = 0;  //푸쉬 메시지 셋팅값 오류 null
	public static final int ERR_PUSH_EXCEPTION = 1;  //푸쉬 메시지 전송 오류
	
	public String sndSn          ;     /* 전송_순번              */
	private String lstDmlDt       ;     /* 최종_수정_일시         */
	private String lstDmlId       ;     /* 최종_수정_ID           */
	private String noticeClf      ;     /* 알림_구분 (SV021)      */
	private String sndOrgCd       ;     /* 전송_기관_코드         */
	private String sndUserId      ;     /* 전송_사용자_ID         */
	private String sndDt          ;     /* 전송_일시              */
	public String sndSttus       ;     /* 전송_상태 (CM004)      */
	public int    sndCnt         ;     /* 전송_건수              */
	private String msgClf         ;     /* 메시지_구분 (SV012)    */
	private String pushTitle      ;     /* 푸시_제목              */
	private String pushCont       ;     /* 푸시_내용              */
	private String noticeTitle    ;     /* 알림_제목              */
	private String noticeCont     ;     /* 알림_내용              */
	private String noticeEndDe    ;     /* 알림_종료일            */
	private String rcvClf         ;     /* 수신_구분 (SV013)      */
	private String reqClf         ;     /* 요청_구분 (CM019)      */
	private String autoManuClf    ;     /* 자동_수동_구분 (SV014) */
	private String pushLinkPage   ;     /* 링크_페이지            */
	private String noticeLinkPage ;     /* 링크_페이지            */
	private String noticeSetNo    ;     /* 알림_설정_번호         */
	private String resrvtDe    ;    	/* 예약_일자         */
	private String resrvtTm    ;     	/* 알림_시간         */
	private String boardSn;				/* 게시물_순번 */
	////
	

	public String getPushLinkPage() {
		return pushLinkPage;
	}
	public void setPushLinkPage(String pushLinkPage) {
		this.pushLinkPage = pushLinkPage;
	}
	public String getNoticeLinkPage() {
		return noticeLinkPage;
	}
	public void setNoticeLinkPage(String noticeLinkPage) {
		this.noticeLinkPage = noticeLinkPage;
	}
	public String getSndSn() {
		return sndSn;
	}
	public String getReqClf() {
		return reqClf;
	}
	public void setReqClf(String reqClf) {
		this.reqClf = reqClf;
	}
	public String getNoticeClf() {
		return noticeClf;
	}
	public void setNoticeClf(String noticeClf) {
		this.noticeClf = noticeClf;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeCont() {
		return noticeCont;
	}
	public void setNoticeCont(String noticeCont) {
		this.noticeCont = noticeCont;
	}
	
	public String getNoticeEndDe() {
		return noticeEndDe;
	}
	public void setNoticeEndDe(String noticeEndDe) {
		this.noticeEndDe = noticeEndDe;
	}
		

	public String getNoticeSetNo() {
		return noticeSetNo;
	}
	public void setNoticeSetNo(String noticeSetNo) {
		this.noticeSetNo = noticeSetNo;
	}
	public void setSndSn(String sndSn) {
		this.sndSn = sndSn;
	}
	public String getLstDmlDt() {
		return lstDmlDt;
	}
	public void setLstDmlDt(String lstDmlDt) {
		this.lstDmlDt = lstDmlDt;
	}
	public String getLstDmlId() {
		return lstDmlId;
	}
	public void setLstDmlId(String lstDmlId) {
		this.lstDmlId = lstDmlId;
	}
	public String getSndOrgCd() {
		return sndOrgCd;
	}
	public void setSndOrgCd(String sndOrgCd) {
		this.sndOrgCd = sndOrgCd;
	}
	public String getSndUserId() {
		return sndUserId;
	}
	public void setSndUserId(String sndUserId) {
		this.sndUserId = sndUserId;
	}
	public String getSndDt() {
		return sndDt;
	}
	public void setSndDt(String sndDt) {
		this.sndDt = sndDt;
	}
	public String getSndSttus() {
		return sndSttus;
	}
	public void setSndSttus(String sndSttus) {
		this.sndSttus = sndSttus;
	}
	public String getMsgClf() {
		return msgClf;
	}
	public void setMsgClf(String msgClf) {
		this.msgClf = msgClf;
	}
	public String getPushTitle() {
		return pushTitle;
	}
	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}
	public String getPushCont() {
		return pushCont;
	}
	public void setPushCont(String pushCont) {
		this.pushCont = pushCont;
	}
	public String getRcvClf() {
		return rcvClf;
	}
	public void setRcvClf(String rcvClf) {
		this.rcvClf = rcvClf;
	}
	public String getAutoManuClf() {
		return autoManuClf;
	}
	public void setAutoManuClf(String autoManuClf) {
		this.autoManuClf = autoManuClf;
	}

//	20170905 푸시 예약 추가(이태석)
	public String getResrvtDe() {
		return resrvtDe;
	}
	public void setResrvtDe(String resrvtDe) {
		this.resrvtDe = resrvtDe;
	}
	
	public String getResrvtTm() {
		return resrvtTm;
	}
	public void setResrvtTm(String resrvtTm) {
		this.resrvtTm = resrvtTm;
	}
	public String getBoardSn(){
		return boardSn;
	}
	public void setBoardSn(String boardSn){
		this.boardSn = boardSn;
	}
	////
	
	private String[] userId;
	private String[] grpSn;
	private String[] tokens;

	private List<Map<String,Object>> pushInfo;	
	private Map<String,Object> resultMap;	
	private String chkYn;
	private String errorMsg;
	private int errorCode;
	private ApplicationContext applicationContext;

	public String[] getUserId() {
		return userId;
	}
	public void setUserId(String[] userId) {
		this.userId = userId;
	}
	public String[] getGrpSn() {
		return grpSn;
	}
	public void setGrpSn(String[] grpSn) {
		this.grpSn = grpSn;
	}
	public String[] getTokens() {
		return tokens;
	}
	public void setToken(String[] tokens) {
		this.tokens = tokens;
	}
	
	
	public String getChkYn() {
		return chkYn;
	}
	public void setChkYn(String chkYn) {
		this.chkYn = chkYn;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}	
	public int getSndCnt() {
		return sndCnt;
	}
	public void setSndCnt(int sndCnt) {
		this.sndCnt = sndCnt;
	}
	
	public Map<String,Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String,Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public List<Map<String,Object>> getPushInfo() {
		return pushInfo;
	}
	public void setPushInfo(List<Map<String,Object>> pushInfo) {
		this.pushInfo = pushInfo;
	}
	public PushMultiThread(){
		resultMap = new HashMap<String,Object>();
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { 
		this.applicationContext = applicationContext; 		
	} 
	
		
	private List<Map<String, Object>> uMap = null;
	
	public PushMultiThread(List<Map<String, Object>> sendList, ApplicationContext applicationContext) {
		try {
			uMap = sendList;
			sendListSize = sendList.size();
			this.applicationContext = applicationContext;
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	

	@Override
	public void run() {
				
		String threadName = Thread.currentThread().getName();
		Map<String, Object> threadMap = new HashMap<String, Object>();
		
		PushService pushService = (PushService)applicationContext.getBean("common.pushService");  
		
		if(threadName.equals("1")) {
			
			//System.out.println("### thradName : " + threadName + "START");
			
			List<Map<String,Object>> userInfoList = new ArrayList<Map<String,Object>>();
			
			int nCnt = 0;
			String sndSn = "";	
						
			try{
			
				for(int i=0; i<sendListSize/4; i++) {				
					Map<String,Object> userInfo = new HashMap<String,Object>();
					//Map<String,Object> notification = new HashMap<String,Object>();
					Map<String,Object> sendMap = uMap.get(i);
					
					String token = StringUtil.nvl(String.valueOf(sendMap.get("TOKEN")));
					String setYn = StringUtil.nvl(String.valueOf(sendMap.get("PUSH_NOTICE_SET_YN")));
					String noticeYn = StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_YN")));
					String badgeCnt = StringUtil.nvl(String.valueOf(sendMap.get("BADGE_CNT")));
					
					userInfo.put("sndSn",		(String)sendMap.get("SND_SN"));
					userInfo.put("sndUserId",	(String)sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId",	(String)sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum",		StringUtil.nvl(String.valueOf(sendMap.get("RN"))));
					userInfo.put("noticeClf", 	StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_CLF"))));	//푸시(P)인 경우 뱃지 카운터 제외
					userInfo.put("badge",		Integer.parseInt(badgeCnt));
					userInfo.put("title",		(String)sendMap.get("PUSH_TITLE"));
					userInfo.put("body",		(String)sendMap.get("PUSH_CONT"));
					userInfo.put("linkPage",	(String)sendMap.get("PUSH_LINK_PAGE"));
					
					if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
						userInfo.put("click_action",	"community");
						//notification.put("click_action",	"community");
					}
					
					if("".equals(token)){
			        	userInfo.put("sndSttus", "30"); //실패
			        	userInfo.put("errorMsg", "푸시 전송을 위한 대상자 앱 토큰(주소)이 없습니다."); //실패
			        	userInfoList.add(userInfo);
			        	continue;
					}
					if(!"Y".equals(setYn)){	//푸시 미수신 설정 사용자 여부
						if(!"Y".equals(noticeYn)){	//모바일 공지 푸시 여부
							userInfo.put("errorMsg", "대상자가 앱에서 푸시 수신 여부를 미수신으로 설정했습니다."); //실패
						}else{
							//userInfo.put("errorMsg", "푸시 미수신 설정 사용자 모바일 공지 발송."); //실패
							userInfo.put("errorMsg", "수동알림 배찌 카운터 발송");
						}
						userInfo.put("sndSttus", "30"); //실패
						userInfoList.add(userInfo);
						continue;
					}
								        
			        String to = "";
			        String rcvClf = sendMap.get("RCV_CLF").toString();
			        if(RCVCLF_A.equals(rcvClf)){
			        	to = "/topics/news";
			        }else if(RCVCLF_H.equals(rcvClf)){
			        	to = "/topics/" + sendMap.get("SND_ORG_CD").toString();
			        }else if(RCVCLF_I.equals(rcvClf)){
			        	to = URLDecoder.decode((String)sendMap.get("TOKEN"),"UTF-8");
			        }
			        
			        // FCM 메시지 생성
	                /*Notification notification = Notification.builder()
	                        .setTitle((String) sendMap.get("PUSH_TITLE"))
	                        .setBody((String) sendMap.get("PUSH_CONT"))
	                        .build();

	                Message.Builder messageBuilder = Message.builder()
	                        .setToken(token)
	                        .setNotification(notification)
	                        .putData("badge", badgeCnt);*/
	                
			        Message.Builder messageBuilder = Message.builder()
	                        .setToken(token)
	                		.putData("body", (String) sendMap.get("PUSH_CONT"))
	                		.putData("badge", badgeCnt);
	                
	                if(sendMap.get("PUSH_TITLE") != null && sendMap.get("PUSH_TITLE").toString().trim().equals("")) {
	                	messageBuilder.putData("title", (String) sendMap.get("PUSH_TITLE"));
	                }
	                
	                if(sendMap.get("PUSH_LINK_PAGE") != null && !sendMap.get("PUSH_LINK_PAGE").toString().trim().equals("")) {
	                	messageBuilder.putData("linkPage", (String) sendMap.get("PUSH_LINK_PAGE"));
	                }
	                        
	                Message message = messageBuilder.build();
			        
			        try {
	                    String response = FirebaseMessaging.getInstance().send(message);
	                    System.out.println("FCM Success result ==> [ " + i + " ] " +response);
	                    
	                    userInfo.put("sndSttus", "20"); // 대기
	                    userInfoList.add(userInfo);
	                    nCnt++;
	                } catch (Exception e) {
	                	System.out.println("FCM Fail result ===> " + e.getMessage());
	                    userInfo.put("sndSttus", "30"); // 실패
	                    userInfo.put("errorMsg", e.getMessage()); // 실패
	                    userInfoList.add(userInfo);
	                }		        			        
				}		
				
				threadMap.put("sndSttus", "S");
				threadMap.put(USER_LIST, userInfoList);
				threadMap.put("flag", true);
												
				pushService.updatePushHisSchedulerForMulti(threadMap);
				
			}catch(Exception e){
				System.out.println("catch(Exception e) ======================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 에러 :: "+e.getMessage());
				e.printStackTrace();				
				threadMap.put("sndSttus", "F");
				threadMap.put("flag", false);				
			}	
		
		}else if(threadName.equals("2")) {
			
			//System.out.println("### thradName : " + threadName + "START");
			
			List<Map<String,Object>> userInfoList = new ArrayList<Map<String,Object>>();
			
			int nCnt = 0;
			String sndSn = "";		
			
			try {
			
				for(int i=sendListSize/4; i<sendListSize/2; i++) {
					Map<String,Object> userInfo = new HashMap<String,Object>();
					//Map<String,Object> notification = new HashMap<String,Object>();
					Map<String,Object> sendMap = uMap.get(i);
					
					String token = StringUtil.nvl(String.valueOf(sendMap.get("TOKEN")));
					String setYn = StringUtil.nvl(String.valueOf(sendMap.get("PUSH_NOTICE_SET_YN")));
					String noticeYn = StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_YN")));
					String badgeCnt = StringUtil.nvl(String.valueOf(sendMap.get("BADGE_CNT")));
					
					userInfo.put("sndSn",		(String)sendMap.get("SND_SN"));
					userInfo.put("sndUserId",	(String)sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId",	(String)sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum",		StringUtil.nvl(String.valueOf(sendMap.get("RN"))));
					userInfo.put("noticeClf", 	StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_CLF"))));	//푸시(P)인 경우 뱃지 카운터 제외
					userInfo.put("badge",		Integer.parseInt(badgeCnt));
					userInfo.put("title",		(String)sendMap.get("PUSH_TITLE"));
					userInfo.put("body",		(String)sendMap.get("PUSH_CONT"));
					userInfo.put("linkPage",	(String)sendMap.get("PUSH_LINK_PAGE"));
					
					if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
						userInfo.put("click_action",	"community");
						//notification.put("click_action",	"community");
					}
					
					if("".equals(token)){
			        	userInfo.put("sndSttus", "30"); //실패
			        	userInfo.put("errorMsg", "푸시 전송을 위한 대상자 앱 토큰(주소)이 없습니다."); //실패
			        	userInfoList.add(userInfo);
			        	continue;
					}
					if(!"Y".equals(setYn)){	//푸시 미수신 설정 사용자 여부
						if(!"Y".equals(noticeYn)){	//모바일 공지 푸시 여부
							userInfo.put("errorMsg", "대상자가 앱에서 푸시 수신 여부를 미수신으로 설정했습니다."); //실패
						}else{
							//userInfo.put("errorMsg", "푸시 미수신 설정 사용자 모바일 공지 발송."); //실패
							userInfo.put("errorMsg", "수동알림 배찌 카운터 발송");
						}
						userInfo.put("sndSttus", "30"); //실패
						userInfoList.add(userInfo);
						continue;
					}
								        
			        String to = "";
			        String rcvClf = sendMap.get("RCV_CLF").toString();
			        if(RCVCLF_A.equals(rcvClf)){
			        	to = "/topics/news";
			        }else if(RCVCLF_H.equals(rcvClf)){
			        	to = "/topics/" + sendMap.get("SND_ORG_CD").toString();
			        }else if(RCVCLF_I.equals(rcvClf)){
			        	to = URLDecoder.decode((String)sendMap.get("TOKEN"),"UTF-8");
			        }
			        
			     // FCM 메시지 생성
	                Notification notification = Notification.builder()
	                        .setTitle((String) sendMap.get("PUSH_TITLE"))
	                        .setBody((String) sendMap.get("PUSH_CONT"))
	                        .build();

	                Message.Builder messageBuilder = Message.builder()
	                        .setToken(token)
	                        .setNotification(notification)
	                        .putData("badge", badgeCnt);
	                
	                if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
	                	messageBuilder.putData("click_action", "community");
					}
	                        
	                Message message = messageBuilder.build();
			       
			        
			        try {
	                    String response = FirebaseMessaging.getInstance().send(message);
	                    System.out.println("FCM Success result ==> [ " + i + " ] " +response);
	                    
	                    userInfo.put("sndSttus", "20"); // 대기
	                    userInfoList.add(userInfo);
	                    nCnt++;
	                } catch (Exception e) {
	                	System.out.println("FCM Fail result ===> " + e.getMessage());
	                    userInfo.put("sndSttus", "30"); // 실패
	                    userInfo.put("errorMsg", e.getMessage()); // 실패
	                    userInfoList.add(userInfo);
	                }		        			        
				}		
				
				threadMap.put("sndSttus", "S");
				threadMap.put(USER_LIST, userInfoList);
				threadMap.put("flag", true);
												
				pushService.updatePushHisSchedulerForMulti(threadMap);
				
				
			}catch(Exception e){
				System.out.println("catch(Exception e) ======================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 에러 :: "+e.getMessage());
				e.printStackTrace();				
				threadMap.put("sndSttus", "F");
				threadMap.put("flag", false);				
			}	
		}else if(threadName.equals("3")) {
			
			//System.out.println("### thradName : " + threadName + "START");
			
			List<Map<String,Object>> userInfoList = new ArrayList<Map<String,Object>>();
			
			int nCnt = 0;
			String sndSn = "";		
			
			try {
			
				for(int i=sendListSize/2; i<sendListSize*3/4; i++) {
					Map<String,Object> userInfo = new HashMap<String,Object>();
					//Map<String,Object> notification = new HashMap<String,Object>();
					Map<String,Object> sendMap = uMap.get(i);
					
					String token = StringUtil.nvl(String.valueOf(sendMap.get("TOKEN")));
					String setYn = StringUtil.nvl(String.valueOf(sendMap.get("PUSH_NOTICE_SET_YN")));
					String noticeYn = StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_YN")));
					String badgeCnt = StringUtil.nvl(String.valueOf(sendMap.get("BADGE_CNT")));
					
					userInfo.put("sndSn",		(String)sendMap.get("SND_SN"));
					userInfo.put("sndUserId",	(String)sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId",	(String)sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum",		StringUtil.nvl(String.valueOf(sendMap.get("RN"))));
					userInfo.put("noticeClf", 	StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_CLF"))));	//푸시(P)인 경우 뱃지 카운터 제외
					userInfo.put("badge",		Integer.parseInt(badgeCnt));
					userInfo.put("title",		(String)sendMap.get("PUSH_TITLE"));
					userInfo.put("body",		(String)sendMap.get("PUSH_CONT"));
					userInfo.put("linkPage",	(String)sendMap.get("PUSH_LINK_PAGE"));
					
					if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
						userInfo.put("click_action",	"community");
						//notification.put("click_action",	"community");
					}
					
					if("".equals(token)){
			        	userInfo.put("sndSttus", "30"); //실패
			        	userInfo.put("errorMsg", "푸시 전송을 위한 대상자 앱 토큰(주소)이 없습니다."); //실패
			        	userInfoList.add(userInfo);
			        	continue;
					}
					if(!"Y".equals(setYn)){	//푸시 미수신 설정 사용자 여부
						if(!"Y".equals(noticeYn)){	//모바일 공지 푸시 여부
							userInfo.put("errorMsg", "대상자가 앱에서 푸시 수신 여부를 미수신으로 설정했습니다."); //실패
						}else{
							//userInfo.put("errorMsg", "푸시 미수신 설정 사용자 모바일 공지 발송."); //실패
							userInfo.put("errorMsg", "수동알림 배찌 카운터 발송");
						}
						userInfo.put("sndSttus", "30"); //실패
						userInfoList.add(userInfo);
						continue;
					}
								        
			        String to = "";
			        String rcvClf = sendMap.get("RCV_CLF").toString();
			        if(RCVCLF_A.equals(rcvClf)){
			        	to = "/topics/news";
			        }else if(RCVCLF_H.equals(rcvClf)){
			        	to = "/topics/" + sendMap.get("SND_ORG_CD").toString();
			        }else if(RCVCLF_I.equals(rcvClf)){
			        	to = URLDecoder.decode((String)sendMap.get("TOKEN"),"UTF-8");
			        }
			        
			     // FCM 메시지 생성
	                Notification notification = Notification.builder()
	                        .setTitle((String) sendMap.get("PUSH_TITLE"))
	                        .setBody((String) sendMap.get("PUSH_CONT"))
	                        .build();

	                Message.Builder messageBuilder = Message.builder()
	                        .setToken(token)
	                        .setNotification(notification)
	                        .putData("badge", badgeCnt);
	                
	                if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
	                	messageBuilder.putData("click_action", "community");
					}
	                        
	                Message message = messageBuilder.build();
			       
			        
			        try {
	                    String response = FirebaseMessaging.getInstance().send(message);
	                    System.out.println("FCM Success result ==> [ " + i + " ] " +response);
	                    
	                    userInfo.put("sndSttus", "20"); // 대기
	                    userInfoList.add(userInfo);
	                    nCnt++;
	                } catch (Exception e) {
	                	System.out.println("FCM Fail result ===> " + e.getMessage());
	                    userInfo.put("sndSttus", "30"); // 실패
	                    userInfo.put("errorMsg", e.getMessage()); // 실패
	                    userInfoList.add(userInfo);
	                }		        			        
				}		
				
				threadMap.put("sndSttus", "S");
				threadMap.put(USER_LIST, userInfoList);
				threadMap.put("flag", true);
												
				pushService.updatePushHisSchedulerForMulti(threadMap);
			
			}catch(Exception e){
				System.out.println("catch(Exception e) ======================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 에러 :: "+e.getMessage());
				e.printStackTrace();				
				threadMap.put("sndSttus", "F");
				threadMap.put("flag", false);	
			}
		}else if(threadName.equals("4")) {
			
			//System.out.println("### thradName : " + threadName + "START");
			
			List<Map<String,Object>> userInfoList = new ArrayList<Map<String,Object>>();
			
			int nCnt = 0;
			String sndSn = "";		
			
			try {
				
				for(int i=sendListSize*3/4; i<sendListSize; i++) {
					Map<String,Object> userInfo = new HashMap<String,Object>();
					//Map<String,Object> notification = new HashMap<String,Object>();
					Map<String,Object> sendMap = uMap.get(i);
					
					String token = StringUtil.nvl(String.valueOf(sendMap.get("TOKEN")));
					String setYn = StringUtil.nvl(String.valueOf(sendMap.get("PUSH_NOTICE_SET_YN")));
					String noticeYn = StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_YN")));
					String badgeCnt = StringUtil.nvl(String.valueOf(sendMap.get("BADGE_CNT")));
					
					userInfo.put("sndSn",		(String)sendMap.get("SND_SN"));
					userInfo.put("sndUserId",	(String)sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId",	(String)sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum",		StringUtil.nvl(String.valueOf(sendMap.get("RN"))));
					userInfo.put("noticeClf", 	StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_CLF"))));	//푸시(P)인 경우 뱃지 카운터 제외
					userInfo.put("badge",		Integer.parseInt(badgeCnt));
					userInfo.put("title",		(String)sendMap.get("PUSH_TITLE"));
					userInfo.put("body",		(String)sendMap.get("PUSH_CONT"));
					userInfo.put("linkPage",	(String)sendMap.get("PUSH_LINK_PAGE"));
					
					if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
						userInfo.put("click_action",	"community");
						//notification.put("click_action",	"community");
					}
					
					if("".equals(token)){
			        	userInfo.put("sndSttus", "30"); //실패
			        	userInfo.put("errorMsg", "푸시 전송을 위한 대상자 앱 토큰(주소)이 없습니다."); //실패
			        	userInfoList.add(userInfo);
			        	continue;
					}
					if(!"Y".equals(setYn)){	//푸시 미수신 설정 사용자 여부
						if(!"Y".equals(noticeYn)){	//모바일 공지 푸시 여부
							userInfo.put("errorMsg", "대상자가 앱에서 푸시 수신 여부를 미수신으로 설정했습니다."); //실패
						}else{
							//userInfo.put("errorMsg", "푸시 미수신 설정 사용자 모바일 공지 발송."); //실패
							userInfo.put("errorMsg", "수동알림 배찌 카운터 발송");
						}
						userInfo.put("sndSttus", "30"); //실패
						userInfoList.add(userInfo);
						continue;
					}
								        
			        String to = "";
			        String rcvClf = sendMap.get("RCV_CLF").toString();
			        if(RCVCLF_A.equals(rcvClf)){
			        	to = "/topics/news";
			        }else if(RCVCLF_H.equals(rcvClf)){
			        	to = "/topics/" + sendMap.get("SND_ORG_CD").toString();
			        }else if(RCVCLF_I.equals(rcvClf)){
			        	to = URLDecoder.decode((String)sendMap.get("TOKEN"),"UTF-8");
			        }
			        
			     // FCM 메시지 생성
	                Notification notification = Notification.builder()
	                        .setTitle((String) sendMap.get("PUSH_TITLE"))
	                        .setBody((String) sendMap.get("PUSH_CONT"))
	                        .build();

	                Message.Builder messageBuilder = Message.builder()
	                        .setToken(token)
	                        .setNotification(notification)
	                        .putData("badge", badgeCnt);
	                
	                if(String.valueOf(sendMap.get("PUSH_LINK_PAGE")).equals("/page/sv/community.html")) {
	                	messageBuilder.putData("click_action", "community");
					}
	                        
	                Message message = messageBuilder.build();
			       
			        
			        try {
	                    String response = FirebaseMessaging.getInstance().send(message);
	                    System.out.println("FCM Success result ==> [ " + i + " ] " +response);
	                    
	                    userInfo.put("sndSttus", "20"); // 대기
	                    userInfoList.add(userInfo);
	                    nCnt++;
	                } catch (Exception e) {
	                	System.out.println("FCM Fail result ===> " + e.getMessage());
	                    userInfo.put("sndSttus", "30"); // 실패
	                    userInfo.put("errorMsg", e.getMessage()); // 실패
	                    userInfoList.add(userInfo);
	                }		        			        
				}		
				
				threadMap.put("sndSttus", "S");
				threadMap.put(USER_LIST, userInfoList);
				threadMap.put("flag", true);
												
				pushService.updatePushHisSchedulerForMulti(threadMap);
			
			}catch(Exception e){
				System.out.println("catch(Exception e) ======================================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 에러 :: "+e.getMessage());
				e.printStackTrace();
				threadMap.put("sndSttus", "F");
				threadMap.put("flag", false);	
			}
		}		
		
		
	}
	private JSONObject setHttpRequest(JSONObject pushData) throws Exception {
		
		JSONObject resultObj = null;
		HttpURLConnection conn = null;
		BufferedReader in = null;
		OutputStream os = null;
		
		try {
	        URL url = new URL("https://fcm.googleapis.com/fcm/send");
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "key=" + API_KEY);	 
	        conn.setDoOutput(true);
	        System.out.println("pushData>>>>"+pushData.toString());
	        
	        os = conn.getOutputStream();
	        os.write(pushData.toString().getBytes("UTF-8"));
	        os.flush();
	        os.close();
	 
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        String result = "";
	 
	        while ((inputLine = in.readLine()) != null) {
	        	result += inputLine;
	        }
	        // print result
	        
	        resultObj = new JSONObject(result);
	        
	        System.out.println("result>>>>"+result);
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null){conn.disconnect();}
			if(in != null){in.close();}
			if(os != null){os.close();}
		}
		
		return resultObj;
	}

}


