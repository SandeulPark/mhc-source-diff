package kr.go.mhc.common.crontab;

import com.google.firebase.messaging.*;

import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.impl.PushDAO;

import java.util.*;
import java.util.concurrent.*;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class PushBatchSender {

    private static final int THREAD_COUNT = 20;
    private static final int BATCH_SIZE = 100;

    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    
    @Resource(name="pushDAO")
    private PushDAO pushDAO;

    public Map<String, Object> sendPushInBatches(List<Map<String, Object>> sendList) throws InterruptedException {
    	    	
        List<Future<BatchResult>> results = new ArrayList<Future<BatchResult>>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean isSuccess = true;
        
        for (int i = 0; i < sendList.size(); i += BATCH_SIZE) {
            final List<Map<String, Object>> batch = sendList.subList(i, Math.min(i + BATCH_SIZE, sendList.size()));

            results.add(executor.submit(new Callable<BatchResult>() {
                @Override
                public BatchResult call() {
                    return sendBatch(batch);
                }
            }));
        }

        List<Map<String, Object>> allUserInfo = new ArrayList<Map<String, Object>>();
        for (Future<BatchResult> result : results) {
            try {
                allUserInfo.addAll(result.get().userInfoList);
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
            }
        }
        resultMap.put("sndSttus", isSuccess ? "S" : "F");
        resultMap.put("flag", isSuccess);
        resultMap.put("userInfoList", allUserInfo);
                
        return resultMap;
    }

	private BatchResult sendBatch(List<Map<String, Object>> batch) {

		List<Map<String, Object>> userInfoList = new ArrayList<Map<String, Object>>();
		List<Message> messages = new ArrayList<>();
		List<Map<String, Object>> eligibleSendMapList = new ArrayList<>();

		for (Map<String, Object> sendMap : batch) {
			try {
				String token = (String) sendMap.get("TOKEN");
				String setYn = String.valueOf(sendMap.get("PUSH_NOTICE_SET_YN")); // 푸시, 알림 수신 여부
				String noticeClf = String.valueOf(sendMap.get("NOTICE_CLF")); // 푸시 : P, 알림 : N, 모두 : 'A'
				String badgeCnt = String.valueOf(sendMap.get("BADGE_CNT"));
				String title = (String) sendMap.get("PUSH_TITLE");
				String body = (String) sendMap.get("PUSH_CONT");
				String linkPage = (String) sendMap.get("PUSH_LINK_PAGE");
				String mobileOs = StringUtil.nvl(String.valueOf(sendMap.get("MOBILE_OS")));
				
				// 푸시 토큰값이 없거나, 푸시/알림 수신 여부를 미수신으로 설정한 경우 필터링
				if (token == null || token.isEmpty() || !"Y".equals(setYn)){
					Map<String, Object> userInfo = new HashMap<String, Object>();
					userInfo.put("sndSn", sendMap.get("SND_SN"));
					userInfo.put("sndUserId", sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId", sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum", String.valueOf(sendMap.get("RN")));
					userInfo.put("noticeClf", String.valueOf(sendMap.get("NOTICE_CLF")));
					userInfo.put("badge", Integer.parseInt(badgeCnt));
                    userInfo.put("title", title);
                    userInfo.put("body", body);
                    userInfo.put("linkPage", linkPage);
                    userInfo.put("sndSttus", "30");
                    
                    // 토큰 값이 없는 경우
    				if (token == null || token.isEmpty()) {
    					userInfo.put("errorMsg", "푸시 전송을 위한 대상자 앱 토큰(주소)이 없습니다.");
    				// 푸시/알림 수신 여부를 미수신으로 설정한경우
    				} else if (!"Y".equals(setYn)) {
    					userInfo.put("errorMsg", "대상자가 앱에서 푸시 수신 여부를 미수신으로 설정했습니다.");
    				}
    				userInfoList.add(userInfo);
    				continue;

    			//푸시 속성이 알림인 경우 필터링(NOTICE_CLF = 'N')
    			}else if("N".equals(noticeClf)) {
					Map<String, Object> userInfo = new HashMap<String, Object>();
					userInfo.put("sndSn", sendMap.get("SND_SN"));
					userInfo.put("sndUserId", sendMap.get("SND_USER_ID"));
					userInfo.put("rcvUserId", sendMap.get("RCV_USER_ID"));
					userInfo.put("rowNum", String.valueOf(sendMap.get("RN")));
					userInfo.put("noticeClf", String.valueOf(sendMap.get("NOTICE_CLF")));
					userInfo.put("badge", Integer.parseInt(badgeCnt));
                    userInfo.put("title", title);
                    userInfo.put("body", body);
                    userInfo.put("linkPage", linkPage);
                    userInfo.put("sndSttus", "20");
                    userInfo.put("errorMsg", "수동 알림 배지 카운트 발송");
                    userInfoList.add(userInfo);
    				continue;
				}

				Message.Builder messageBuilder = Message.builder()
						.setToken(token)
						.putData("sndSn", (String) sendMap.get("SND_SN"))
                        .putData("sndUserId", (String) sendMap.get("SND_USER_ID"))
                        .putData("rcvUserId", (String) sendMap.get("RCV_USER_ID"))
                        .putData("rowNum", StringUtil.nvl(String.valueOf(sendMap.get("RN"))))
                        .putData("pushYn", setYn)
                        .putData("noticeClf", StringUtil.nvl(String.valueOf(sendMap.get("NOTICE_CLF"))))
                        .putData("click_action", "kr.go.khealthmhc.MyClickAction")
                        .putData("badge", badgeCnt);				
				
				if("AND".equals(mobileOs)) {
					messageBuilder.putData("title", (String) sendMap.get("PUSH_TITLE"));
					messageBuilder.putData("body", (String) sendMap.get("PUSH_CONT"));
					messageBuilder.putData("linkPage", linkPage.replace("=", "%3D"));
					//Doze모드 대응
					messageBuilder.setAndroidConfig(AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build());
				}
				
				// AOS 에는 Notification 추가시 백그라운드 푸시 링크 페이지 이동 안되는 현상으로 iOS 에만 Notification 추가
				if("IOS".equals(mobileOs)) {
					Notification notification = Notification.builder()
							.setTitle((String) sendMap.get("PUSH_TITLE"))
							.setBody((String) sendMap.get("PUSH_CONT"))
							.build();

					messageBuilder.setNotification(notification);
					messageBuilder.putData("linkPage", linkPage);
				}
				
				Message message = messageBuilder.build();
				messages.add(message);
				eligibleSendMapList.add(sendMap); // 매핑용

				System.out.println(">>> sendBatch 대상 필터링 후 메시지 수: " + messages.size());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//빈 메시지 리스트 체크
		if (messages.isEmpty()) {
			System.out.println(">>> 전송할 메시지가 없어 FCM 호출 생략됨.");
			return new BatchResult(userInfoList);
		}

		try {
			System.out.println(">>> sendAll() 호출 시작됨, 메시지 수: " + messages.size());
			//자바 1.8 이상만 사용 가능하여 주석 처리
			/*BatchResponse response = FirebaseMessaging.getInstance().sendEach(messages);
			List<SendResponse> responses = response.getResponses();*/

			//for (int i = 0; i < responses.size(); i++) {
			for (int i = 0; i < messages.size(); i++) {
				Message message = messages.get(i);
				Map<String, Object> userInfo = new HashMap<String, Object>();
				Map<String, Object> sendMap = eligibleSendMapList.get(i);
				String badgeCnt = String.valueOf(sendMap.get("BADGE_CNT"));
				
				userInfo.put("sndSn", sendMap.get("SND_SN"));
				userInfo.put("sndUserId", sendMap.get("SND_USER_ID"));
				userInfo.put("rcvUserId", sendMap.get("RCV_USER_ID"));
				userInfo.put("rowNum", String.valueOf(sendMap.get("RN")));
				userInfo.put("noticeClf", String.valueOf(sendMap.get("NOTICE_CLF")));
				userInfo.put("badge", Integer.parseInt(badgeCnt));
				userInfo.put("title", sendMap.get("PUSH_TITLE"));
				userInfo.put("body", sendMap.get("PUSH_CONT"));
				userInfo.put("linkPage", sendMap.get("PUSH_LINK_PAGE"));
								
				try {
					String messageId = FirebaseMessaging.getInstance().send(message);
					userInfo.put("sndSttus", "20");
					System.out.println("FCM Success result ==> [ " + i + " ] " + messageId);
				} catch (FirebaseMessagingException e) {          
                	String errorCode = String.valueOf(e.getMessagingErrorCode());
                	switch(errorCode) {
                	case "UNREGISTERED":
                		userInfo.put("errorMsg", "유효하지 않은 토큰입니다. 앱을 재실행하여 토큰을 재생성해주세요.");
                		break;
                	case "INVALID-ARGUMENT":
                		userInfo.put("errorMsg", "푸시 요청 매개변수가 잘못되었습니다.");
                		break;
                	case "QUOTA_EXCEEDED":
                		userInfo.put("errorMsg", "메시지 대상에 대한 전송 한도를 초과하였습니다.");
                		break;
                	case "UNAVAILABLE":
                		userInfo.put("errorMsg", "서버가 과부하되었습니다.");
                		break;
                	case "INTERNAL":
                		userInfo.put("errorMsg", "알 수 없는 내부 오류가 발생하였습니다.");
                		break;
                	case "THIRD_PARTY_AUTH_ERROR":
                		userInfo.put("errorMsg", "APN인증서 또는 웹 푸시 인증키가 잘못되었거나 누락되었습니다.");
                		break;
                	default:
                		userInfo.put("errorMsg", e.getMessage());
                	} 
                	System.out.println(">>> FCM FirebaseMessagingException / Message ===> " + errorCode + " : " + e.getMessage());
                	userInfo.put("sndSttus", "30");
                	userInfoList.add(userInfo);
				} catch(Exception e) {
					userInfo.put("errorMsg", e.getMessage());
					userInfo.put("sndSttus", "30");
					System.out.println(">>> FCM Fail Exception ===> [ " + i + " ] " + e.getMessage());
				}
				userInfoList.add(userInfo);
			}
		} catch (Exception e) {

			System.out.println(e);

			for (Map<String, Object> sendMap : batch) {
				Map<String, Object> userInfo = new HashMap<String, Object>();
				String badgeCnt = String.valueOf(sendMap.get("BADGE_CNT"));

				userInfo.put("sndSn", sendMap.get("SND_SN"));
				userInfo.put("sndUserId", sendMap.get("SND_USER_ID"));
				userInfo.put("rcvUserId", sendMap.get("RCV_USER_ID"));
				userInfo.put("rowNum", String.valueOf(sendMap.get("RN")));
				userInfo.put("noticeClf", String.valueOf(sendMap.get("NOTICE_CLF")));
				userInfo.put("badge", Integer.parseInt(badgeCnt));
				userInfo.put("title", sendMap.get("PUSH_TITLE"));
				userInfo.put("body", sendMap.get("PUSH_CONT"));
				userInfo.put("linkPage", sendMap.get("PUSH_LINK_PAGE"));
				userInfo.put("sndSttus", "30");
				userInfo.put("errorMsg", e.getMessage());				
			    userInfoList.add(userInfo);
			}
		}

		return new BatchResult(userInfoList);
    }
    
    @PreDestroy
    public void shutdownExecutor() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    static class BatchResult {
        List<Map<String, Object>> userInfoList;
        BatchResult(List<Map<String, Object>> list) {
            this.userInfoList = list;
        }
    }
    
}