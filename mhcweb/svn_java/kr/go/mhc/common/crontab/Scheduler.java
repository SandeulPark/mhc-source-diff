package kr.go.mhc.common.crontab;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import kr.go.mhc.common.service.SchedulerService;
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class Scheduler {

	@Resource(name="common.schedulerService")
	protected SchedulerService schedulerService;

	@Resource(name="common.pushService")
	private PushService pushService;

	@Resource(name = "pushMessageUtil")
	protected PushMessageUtil pushMessageUtil;
	
	@Autowired
	private PushBatchSender pushBatchSender;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private final AtomicBoolean isRunning = new AtomicBoolean(false);

//    @Scheduled(fixedRate = 10000)
//    public void reportCurrentTime() {
//        System.out.println("The time is now " + dateFormat.format(new Date()));
//    }    
	
	/**
	 * 1. 오전 00:01:00에 호출이 되는 스케쥴러 
	 * 스케줄 생성 프로시저 
	 */
	@Scheduled(cron = "0 00 1 * * *")
	public void CALL_PRC_TN_SV_CNSL_INS_NEW(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_TN_SV_CNSL_INS_NEW(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 2. 오전 02:00:00에 호출이 되는 스케쥴러
	 * 랭킹 프로시저
	 * 22.06.22 > linux shell script crontab으로 이동
	 * @author : chyoon 
	 */
//	@Scheduled(cron = "0 00 2 * * *")
//	public void CALL_PRC_TN_SV_MISSION_ACHV_INS(){
//		try {
//			Map<String,Object> rsMap = new HashMap<String,Object>();
//			schedulerService.CALL_PRC_TN_SV_MISSION_ACHV_INS(rsMap);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
    
	/**
	 * 3. 정각, 30분 마다 호출이 되는 스케쥴러  
	 * 푸시 전송 프로시저
	 * 22.06.22 5분마다로 변경
	 * 23.06.26 메소드 명 변경(CALL_PRC_TN_MS_ACT_UPD -> CALL_PRC_AUTO_PUSH_SND
	 * @author : chyoon
	 */
//	@Scheduled(cron = "0 0/30 * * * *")
	@Scheduled(cron = "0 0/5 * * * *")
	public synchronized void CALL_PRC_AUTO_PUSH_SND(){
		long start = System.currentTimeMillis(); // 측정
		
		try {
			Date time = new Date();
			String time1 = dateFormat.format(time);				
			
			if (!isRunning.compareAndSet(false, true)) {
				System.out.println("#################################################################");
				System.out.println("### CALL_PRC_AUTO_PUSH_SND [ " + dateFormat.format(new Date()) + " ] 스케줄러 이미 실행 중. 이번 주기는 skip.");
				System.out.println("#################################################################");
				return;
		    }
		
			Map<String,Object> rsMap = new HashMap<String,Object>();

			rsMap.put("sndSttus", "12");
			rsMap.put("reqClf", "20");
			rsMap.put("autoManuClf", "A");
			rsMap.put("isScheduled", "true");
			long t1 = System.currentTimeMillis();
			List<Map<String,Object>> sendList = pushService.selectSendList(rsMap);
			long t2 = System.currentTimeMillis();
			
			System.out.println("#################################################################");
			System.out.println("### CALL_PRC_AUTO_PUSH_SND [ " +time1+ " ] / sendListSize ===>" + sendList.size());
			System.out.printf("### 대상자 조회 시간: %.2f 초%n", (t2 - t1) / 1000.0);
			
			if (sendList != null && !sendList.isEmpty()) {
				long t3 = System.currentTimeMillis();
				Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);		
				long t4 = System.currentTimeMillis();
	            System.out.printf("### FCM 전송 시간(sendPushInBatches): %.2f 초%n", (t4 - t3) / 1000.0);
	            
	            long t5 = System.currentTimeMillis();
	            pushService.updatePushHisScheduler(resultMap);
	            long t6 = System.currentTimeMillis();
	            System.out.printf("### 히스토리 반영 시간: %.2f 초%n",  (t6 - t5) / 1000.0);
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			isRunning.set(false);
			long end = System.currentTimeMillis(); // [E] 전체 종료
	        System.out.printf("### 전체 푸시 처리 시간: %.2f 초%n", (end - start) / 1000.0);
	        System.out.println("#################################################################");
		}
	}

	/**
	 * 4. 15분, 45분 마다 호출이 되는 스케쥴러
	 * 자동 알림 푸시 생성 프로시저
	 */	
	@Scheduled(cron = "0 15/30 * * * *")
	public void CALL_PRC_AUTO_PUSH_INS(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_AUTO_PUSH_INS(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * 5. 오전 00:10:00에 호출이 되는 스케쥴러 
	 * 일자별 정리해야하는 데이터 처리 프로시저
	 */	
	@Scheduled(cron = "0 10 0 * * *")
	public void CALL_PRC_DAILY_JOB_UPD(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_DAILY_JOB_UPD(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * 6. 오전 00:10:00에 호출이 되는 스케쥴러
	 * 처리되지 않는 심박수 생성 프로시저
	 */	
	@Scheduled(cron = "0 0/10 * * * *")
	public void CALL_PRC_TN_MS_HEART_RATE_INS(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
//			schedulerService.CALL_PRC_TN_MS_HEART_RATE_INS(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    
	/**
	 * 7. 정각마다 호출이 되는 스케쥴러 
	 */
	@Scheduled(cron = "0 0 * * * *")
	public void CALL_PRC_AUTO_RETRY_PUSH_INS(){
//		try {
//			Map<String,Object> rsMap = new HashMap<String,Object>();
//			schedulerService.CALL_PRC_AUTO_RETRY_PUSH_INS(rsMap);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 8. 한시간마다 호출이 되는 스케쥴러
	 * 아이폰 운동 칼로리 업데이트 프로시저
	 */
	@Scheduled(cron = "0 55 * * * *")
	public void CALL_PRC_TN_MS_RUNNING_CALORIE_UPD(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
//			schedulerService.CALL_PRC_TN_MS_RUNNING_CALORIE_UPD(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 9. 오전 03:00:00에 호출이 되는 스케쥴러
	 * 실적 프로시저
	 * 22.07.10 > linux shell script crontab으로 이동
	 * @author : chyoon  
	 */
//	@Scheduled(cron = "0 00 3 * * *")
//	public void CALL_PRC_TM_PM_STATS_ALL_INS(){
//		try {
//			Map<String,Object> rsMap = new HashMap<String,Object>();
//			schedulerService.CALL_PRC_TM_PM_STATS_ALL_INS(rsMap);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
	
	/**
	 * 10. 오전 00:05:00에 호출이 되는 스케쥴러
	 * 중도 탈락 프로시저
	 */
	@Scheduled(cron = "0 5 0 * * *")
	public void CALL_PRC_AUTO_DROP_UPD(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_AUTO_DROP_UPD(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * 11. 10분마다 호출이 되는 스케쥴러 
	 */
//	@Scheduled(cron = "* */10 * * * *")
//	public void CALL_PRC_EXCS_SCH_PUSH(){
//		try {
//			Map<String,Object> rsMap = new HashMap<String,Object>();			
//			List<Map<String,Object>> sendList = pushService.selectSendList(rsMap);
//			schedulerService.CALL_PRC_EXCS_SCH_PUSH(rsMap);
//			if(sendList != null){
//				if(sendList.size() > 0){
//					if(pushMessageUtil.sendPushList(sendList)){
//						pushService.updatePushHisScheduler(pushMessageUtil.getResultMap()); // 오라클 커서 에러 처리
//					}
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	
	
	
	/**
	 * 12. 매주 월요일 오전8시에 호출이 되는 스케쥴러 (중간검진 방문예약대상자 푸시전송) 
	 * @author 최대길 (2020-07-17)
	 */
	@Scheduled(cron = "0 0 8 * * 1")
	public void CALL_PRC_VISIT_SCH_RESVRT_PUSH(){
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
					int rNum = (int)(Math.random()*9999);
					sndSn += Integer.toString(rNum);
					
					Map<String, Object> insPushMasMap = new HashMap<>();
					insPushMasMap.put("sndSn", sndSn);
					insPushMasMap.put("noticeClf", "A");	// A: 전체, N: 알림, P: 푸시
					insPushMasMap.put("sndUserId", sndUserId);
					insPushMasMap.put("sndOrgCd", sendCntMap.get("ORG_CD").toString());
					insPushMasMap.put("sndSttus", "S");	// S: 성공, F: 실패
					insPushMasMap.put("sndCnt", sendCntMap.get("PUSH_CNT").toString());
					insPushMasMap.put("msgClf", "NT");	// NO: 공지, NT: 알림, OB: 목표
					insPushMasMap.put("pushTitle", (String)sendCntMap.get("PUSH_TITLE"));		// 푸시제목
					insPushMasMap.put("pushCont", (String)sendCntMap.get("PUSH_CONT"));			// 푸시내용
					insPushMasMap.put("noticeTitle", (String)sendCntMap.get("NOTICE_TITLE"));	// 노티제목
					insPushMasMap.put("noticeCont", (String)sendCntMap.get("NOTICE_CONT"));		// 노티내용
					insPushMasMap.put("noticeLinkPage", (String)sendCntMap.get("PUSH_LINK_PAGE"));	// 눌렀을때 링크주소
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
				
					Thread.sleep(1000 * 60);	// 기관별로 푸시대상자한테 푸시를 다 보내면 1분간 sleep
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 13. 정각마다 호출이 되는 스케쥴러
	 * 최근 미접속 업데이트 프로시저 
	 * TN_CM_CONNECT_LOG > TN_CM_USER_LST_CONNECT 에서 TRIGGER 처리 부분  프로시저로 변경
	 * 2023.06.09
	 * @author : chyoon 
	 */ 
	@Scheduled(cron = "0 0 * * * *")
	public void CALL_PRC_AUTO_LST_CONNECT_UPD() {
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_AUTO_LST_CONNECT_UPD(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 14. 매월 말일 오후 11시 50분 호출이 되는 프로시저 -> 매월 1일 새벽 1시로 변경(25.11.26)
	 * 관리자 계정 휴면 처리(3개월 미접속) 및 계정 삭제(6개월 미접속) 프로시저
	 * 2025.02.07
	 * @author : chyoon 
	 */ 
	@Scheduled(cron = "0 0 1 * * ?")
	public void CALL_PRC_AUTO_MNGR_MNGT() {
		Calendar today = Calendar.getInstance();

	    Calendar firstDay = Calendar.getInstance();
	    firstDay.set(Calendar.DAY_OF_MONTH, 1);

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
	    if (sdf.format(today.getTime()).equals(sdf.format(firstDay.getTime()))) {	    
			try {
				Map<String,Object> rsMap = new HashMap<String,Object>();
				schedulerService.CALL_PRC_AUTO_MNGR_MNGT(rsMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	    }
		
	}

}
