package kr.or.khealth.smhc.common.crontab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;
import javax.sql.DataSource;

import kr.or.khealth.smhc.common.service.SchedulerService;
import kr.or.khealth.smhc.common.util.PushMessageUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import kr.or.khealth.smhc.smhcweb.cm.service.impl.PushDAO;
import kr.or.khealth.smhc.smhcweb.sv.service.ForecastService;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler implements ApplicationContextAware{

	@Resource(name="common.schedulerService")
	protected SchedulerService schedulerService;

	@Resource(name="common.pushService")
	private PushService pushService;

	@Resource(name = "pushMessageUtil")
	protected PushMessageUtil pushMessageUtil;
	
	@Resource(name="pushDAO")
    private PushDAO pushDAO;
	
	@Autowired
	private PushBatchSender pushBatchSender;
	
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
	
	private final AtomicBoolean isRunning = new AtomicBoolean(false);

	private ApplicationContext applicationContext; 
//    @Scheduled(fixedRate = 10000)
//    public void reportCurrentTime() {
//        System.out.println("The time is now " + dateFormat.format(new Date()));
//    }   

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext; 
	}

	/**
	 * 1. 정각, 30분 마다 호출이 되는 스케쥴러 
	 */
/*	@Scheduled(cron = "0 0/30 * * * *")
	public void CALL_PRC_TN_MS_ACT_UPD(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();

			rsMap.put("sndSttus", "12");
			rsMap.put("reqClf", "20");
			rsMap.put("autoManuClf", "A");
			rsMap.put("isScheduled", "true");
			List<Map<String,Object>> sendList = pushService.selectSendList(rsMap);
			
			Date time = new Date();
			String time1 = dateFormat.format(time);	
			
			System.out.println("30분마다 SCHEDULAR [ " +time1+ " ] / sendListSize ===>" + sendList.size());
			
			if(sendList != null){
				
				if(sendList.size() > 0){
					if(pushMessageUtil.sendPushList(sendList)){// 푸시 전송
						pushService.updatePushHisScheduler(pushMessageUtil.getResultMap()); // 오라클 커서 에러 처리
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/


	/**
	 * 2. 15분, 45분 마다 호출이 되는 스케쥴러
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
	 * 3. 오전 02:00:00에 호출이 되는 스케쥴러 
	 */
	/*
	@Scheduled(cron = "0 00 2 * * *")
	public void CALL_PRC_TN_SV_MONTH_TOT_POINT(){
		try {
			Map<String,Object> rsMap = new HashMap<String,Object>();
			schedulerService.CALL_PRC_TN_SV_MONTH_TOT_POINT(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * 4. 1분마다 호출이 되는 스케쥴러 
	 */
	@Scheduled(cron = "0 * * * * *")
	public synchronized void CALL_PRC_TAI_PUSH(){
		long start = System.currentTimeMillis(); // 측정
		try {
			Date time = new Date();
			String time1 = dateFormat.format(time);
			
			if (!isRunning.compareAndSet(false, true)) {
				System.out.println("#################################################################");
				System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date()) + " ] 스케줄러 이미 실행 중. 이번 주기는 skip.");
				System.out.println("#################################################################");
				return;
		    }

			Map<String,Object> rsMap = new HashMap<String,Object>();
			Map<String, Object> param = new HashMap<String, Object>();

			rsMap.put("sndSttus", "12");
			rsMap.put("reqClf", "20");
			rsMap.put("autoManuClf", "A");
			rsMap.put("isScheduled", "true");
			rsMap.put("isLinked", "true");
			System.out.println("#################################################################");
			System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date())+ " ] / sendListSize start");
			List<Map<String,Object>> sendList = pushService.selectSendList(rsMap);
			System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date())+ " ] / sendListSize ===>" + sendList.size());
			System.out.println("#################################################################");
			
			if (sendList != null && !sendList.isEmpty()) {
				pushMessageUtil.setPushDao(pushDAO);
				for (Map<String, Object> sendMap : sendList) {
					sendMap.put("sndSttus", "15");
					sendMap.put("sndUserId", sendMap.get("LST_DML_ID"));
					sendMap.put("sndSn", sendMap.get("SND_SN"));
					sendMap.put("rcvUserId", sendMap.get("RCV_USER_ID"));
				}
				param.put("userInfoList", sendList);
				pushService.updatePushHis(param);
				System.out.println("#################################################################");
				System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date()) + " ] / sendPushInBatches start");
				Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);
				System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date()) + " ] / sendPushInBatches finish");
				System.out.println("#################################################################");
				System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date()) + " ] / updatePushHisSchedulerForMulti start");
				pushService.updatePushHisSchedulerForMulti(resultMap);
				System.out.println("### PUSH SCHEDULAR [ " + dateTimeFormat.format(new Date()) + " ] / updatePushHisSchedulerForMulti finish");
				System.out.println("#################################################################");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			isRunning.set(false);
		}
		
		long end = System.currentTimeMillis(); // 측정 종료
		long totalTime = end - start;
		System.out.println("### 전체 푸시 처리 시간(ms): " + totalTime);
	}
	
	
	
	
	/**
	 * 5. 매 시간 45분 스케쥴러 걸음수 미션 미완료 처리된 인원 미션 완료 처리
	 */			
	@Scheduled(cron = "0 45 * * * *")	
	public void CALL_PRC_ACT_MISSION_SUCC_INS(){
		try {
			
			Date time = new Date();
			String time1 = timeFormat.format(time);	
			
			Map<String,Object> rsMap = new HashMap<String,Object>();	
			System.out.println("#################################################################");
			System.out.println("### SCHEDULAR(CALL_PRC_ACT_MISSION_SUCC_INS) [ " +time1+ " ] ");
			System.out.println("#################################################################");
			schedulerService.CALL_PRC_ACT_MISSION_SUCC_INS(rsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 5. 매월 1일 새벽 2시 월간리포트 생성
	 */	
	@Scheduled(cron = "0 0 02 1 * *")
	public void CALL_PRC_TN_SV_MONTH_REPORT(){
		try {
			
			Date time = new Date();
			String time1 = timeFormat.format(time);	
			
			Map<String,Object> param = new HashMap<String,Object>();	
			System.out.println("#################################################################");
			System.out.println("### SCHEDULAR(CALL_PRC_TN_SV_MONTH_REPORT) [ " +time1+ " ] ");
			System.out.println("#################################################################");
			
			DateFormat df = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();		
	        cal.setTime(time);
	        
	        String CURR_YM = df.format(cal.getTime());
	        String CURR_MONTH = String.valueOf(Integer.parseInt(CURR_YM.substring(4,6)));
	        
	        cal.add(cal.MONTH, -1);
	        String RANK_YM = df.format(cal.getTime());
	        String RANK_MONTH = String.valueOf(Integer.parseInt(RANK_YM.substring(4,6)));
	       	
	        System.out.println("### CURR_YM === > " + CURR_YM);
	        System.out.println("### CURR_MONTH === > " + CURR_MONTH);
	        System.out.println("### RANK_YM === > " + RANK_YM);
	        System.out.println("### RANK_MONTH === > " + RANK_MONTH);
	        
	        param.put("RANK_YM", RANK_YM);
	        param.put("RANK_MONTH", RANK_MONTH);
	        param.put("CURR_YM", CURR_YM);
	        param.put("CURR_MONTH", CURR_MONTH);
	        List<Map<String,Object>> rsList = schedulerService.getMinMaxWeekDate(param);
	        
	        param.put("WEEK1_MIN_DE", rsList.get(0).get("MIN_DE"));
	        param.put("WEEK1_MAX_DE", rsList.get(0).get("MAX_DE"));
	        param.put("WEEK2_MIN_DE", rsList.get(1).get("MIN_DE"));
	        param.put("WEEK2_MAX_DE", rsList.get(1).get("MAX_DE"));
	        param.put("WEEK3_MIN_DE", rsList.get(2).get("MIN_DE"));
	        param.put("WEEK3_MAX_DE", rsList.get(2).get("MAX_DE"));
	        param.put("WEEK4_MIN_DE", rsList.get(3).get("MIN_DE"));
	        param.put("WEEK4_MAX_DE", rsList.get(3).get("MAX_DE"));
	        
	        if(rsList.size() > 4) {
	        	param.put("WEEK5_MIN_DE", rsList.get(4).get("MIN_DE"));
	        	param.put("WEEK5_MAX_DE", rsList.get(4).get("MAX_DE"));
	        }
	        
	        param.put("RESULT", null);
	        
	        // 월간리포트 프로시저 호출로 변경 24.08.14
	        schedulerService.CALL_PRC_TN_SV_MONTH_REPORT(param);
	        String rsStr = String.valueOf(param.get("RESULT"));
	        
	        System.out.println("rsStr ==> " + rsStr);
	        	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "0 0 05 28-31 * *")
	public void CALL_PRC_TN_CM_CONNECT_LOG_CRE(){
		System.out.println("=========================== CALL_PRC_TN_CM_CONNECT_LOG_CRE ===========================");
		try {
			final Calendar calendar = Calendar.getInstance();
			System.out.println("calendar.get(Calendar.DATE): " + calendar.get(Calendar.DATE));
			System.out.println("calendar.getActualMaximum(Calendar.DATE): " + calendar.getActualMaximum(Calendar.DATE));
			if(calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DATE)) {
				System.out.println("CALL_PRC_TN_CM_CONNECT_LOG_CRE EXEC!!");
				schedulerService.CALL_PRC_TN_CM_CONNECT_LOG_CRE();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Scheduled(cron = "0 0 0 * * *")
	public void resetUserPrivacyData(){
		try {
			Date time = new Date();
			String time1 = timeFormat.format(time);	
			System.out.println("#################################################################");
			System.out.println("### SCHEDULAR(RESET_USER_PRIVACY_DATA) [ " +time1+ " ] ");
			System.out.println("#################################################################");
			//schedulerService.initCertificationCnt();
			schedulerService.resetExpiredTokens();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




