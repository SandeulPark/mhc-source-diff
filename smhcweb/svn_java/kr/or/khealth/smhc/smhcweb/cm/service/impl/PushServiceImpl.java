package kr.or.khealth.smhc.smhcweb.cm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import kr.or.khealth.smhc.common.crontab.PushBatchSender;
import kr.or.khealth.smhc.common.crontab.PushMultiThread;
import kr.or.khealth.smhc.common.util.PushMessageUtil;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import oracle.sql.CLOB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("common.pushService")
public class PushServiceImpl extends EgovAbstractServiceImpl implements PushService{
	
	@Resource(name="pushDAO")
    private PushDAO pushDAO;
	
	@Autowired
	private PushBatchSender pushBatchSender;
	
	private ApplicationContext applicationContext; 

	@Override
	public void updateToken(Map<String, Object> param) throws Exception {
		pushDAO.updateToken(param);
		
	}

	@Override
	public Map<String, Object> getSndSn() throws Exception {
		return pushDAO.getSndDn();
	}

	@Override
	public int insertPushHis(Map<String, Object> param) throws Exception {
		Map<String, Object> selPushMas = (Map<String, Object>)pushDAO.selectPushMas(param);
		
		if(selPushMas != null){
			param.put("SND_SN", selPushMas.get("SND_SN"));
			pushDAO.deletePushMasHist(param);
		}
		
		int rtInt = pushDAO.insertPushMas(param);
			List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
			if(userList != null){
				for(Map<String,Object> userInfo : userList){
					rtInt += pushDAO.insertPushHis(userInfo);
				}
			}
		return rtInt;
	}

	@Override
	public int updatePushHis(Map<String, Object> param) throws Exception {
			List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
			if(userList != null){
				for(Map<String,Object> userInfo : userList){
					if("1".equals(userInfo.get("rowNum"))){
						param.put("sndSn", userInfo.get("sndSn"));
						param.put("sndUserId", userInfo.get("sndUserId"));
						pushDAO.updatePushMas(param);
					}
					pushDAO.updatePushHis(userInfo);
					pushDAO.updatePushAllHis(userInfo);
				}
			}
		
		return 0;
	}

	@Override
	public int insertPushHisTopic(Map<String, Object> param) throws Exception {
		int rtInt = -1;
		rtInt = pushDAO.insertPushMas(param);
		if(rtInt>0){
			pushDAO.insertPushHisTopic(param);
		}
		
		return rtInt;
	}

	@Override
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param) throws Exception {
		return pushDAO.selectTokenList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception {
		return pushDAO.selectSendList(param);
	}
	
	@Override
	public Map<String, String> selectGrpUserList(Map<String, Object> param) throws Exception {
		return pushDAO.selectGrpUserList(param);
	}
	
	@Override
	public Map<String, String> getPushSndYn(Map<String, Object> param) throws Exception {
		return pushDAO.getPushSndYn(param);
	}
	
	@Override
	public void setPushSndYn(Map<String, Object> param) throws Exception {
		pushDAO.setPushSndYn(param);
	}
	
	@Override
	public List<Map<String, Object>> topicUserList(Map<String, Object> param) throws Exception {
		return pushDAO.topicUserList(param);
	}
	
	@Override
	public boolean sendPushData(PushMessageUtil pushMessageUtil, Map<String, Object> param) throws Exception {

		boolean rsFlag = false;
		//Map<String,String> grpMap = null;
		String userId = "";
		String grpSn = StringUtil.nvl(String.valueOf(param.get("grpList")));
		List<Map<String,Object>> grpSendList = new ArrayList<Map<String,Object>>();
		StringBuffer buff = new StringBuffer();
		
		System.out.println("############# sendPushData #################");
		System.out.println("### 5. grpSn ===> " + grpSn);
				
		if(!"".equals(grpSn)){
			// 선택한 그룹에서 그룹에 속한 대상자를 조회한다.
			List<Map<String,String>> grpIter = StringUtil.makeStringToIterator(grpSn);
			param.put("grpIter", grpIter);
			//grpMap = selectGrpUserList(param);
			grpSendList = selectGrpUserList2(param);
			for(int i=0; i<grpSendList.size(); i++) {
				buff.append(grpSendList.get(i).get("USER_ID")).append(",");
			}
		}
		
		// 조회된 그룹 대상자가 있는지 확인
		if(!grpSendList.isEmpty()) {
			userId = String.valueOf(buff);
			userId = userId.replaceAll(",$", "");
			System.out.println("### 6-1. userId ===> " + userId);
		}else {
			userId = StringUtil.nvl(String.valueOf(param.get("userList")));
			System.out.println("### 6-2. userId ===> " + userId);
		}
//		if(grpMap != null){
//			userId = StringUtil.nvl(String.valueOf(grpMap.get("USER_LIST")));	
//		}else{
//			userId = StringUtil.nvl(String.valueOf(param.get("userList")));
//		}		
		System.out.println("### 7. reqClf ===> " + StringUtil.nvl(param.get("reqClf")));
		if(!"".equals(userId)){
			// 3-4. 대상자가 있으면 pushMessageUtil.setUserId에 값 설정
			pushMessageUtil.setUserId(userId.split(","));
			int insCnt = 0;
			// 3-5. 푸시 마스터 및 히스 테이블에 데이터 저장  
			if(pushMessageUtil.sendNotifition()){
				insCnt = insertPushHis(pushMessageUtil.getResultMap());
				System.out.println("### 8. insCnt ===> " + insCnt);
			}
			if(StringUtil.nvl(param.get("reqClf")).equals("10")){
				// 3-7. 유저 정보를 다시 조회.
				List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
				param.put("userIter", userIter);
				param.put("sndSn", pushMessageUtil.getSndSn());
				List<Map<String,Object>> sendList = selectSendList(param);
				if(sendList != null){
					System.out.println("### 9. sendList.size ===> " + sendList.size());
					// 3-8. 조회된 데이터가 있으면 푸시 전송.
					/*if(sendList.size() > 0 && sendList.size() <100){
						if(pushMessageUtil.sendPushList(sendList)){// 푸시 전송
							updatePushHis(pushMessageUtil.getResultMap());
							rsFlag = true;
						}
					}else {						
						PushMultiThread multiThread = new PushMultiThread(sendList, applicationContext);
						
						Thread T1 = new Thread(multiThread, "1");				
						T1.start();					
						Thread T2 = new Thread(multiThread, "2");					
						T2.start();		
						Thread T3 = new Thread(multiThread, "3");
						T3.start();		
						Thread T4 = new Thread(multiThread, "4");
						T4.start();
						
						rsFlag = true;
					}*/
					if (sendList != null && !sendList.isEmpty()) {
						Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);				
			            updatePushHisSchedulerForMulti(resultMap);
			        }
				}
			}
		}
		
		return rsFlag;
	}

	private List<Map<String, Object>> selectGrpUserList2(Map<String, Object> param) {		
		return pushDAO.selectGrpUserList2(param);
	}

	@Override
	public Map<String, String> getPushSndYnNotice(Map<String, Object> param) throws Exception {
		return pushDAO.getPushSndYnNotice(param);
	}
	
	@Override
	public void setNoticePushSndYn(Map<String, Object> param) throws Exception {
		pushDAO.setNoticePushSndYn(param);
	}
	
	@Override
	public Map<String, String> selectGrpList(Map<String, Object> param) throws Exception {
		return pushDAO.selectGrpList(param);
	}

	@Override
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		return pushDAO.updatePushStatus(param);
	}
	
	@Override
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		return pushDAO.pushUpdateCnfm(param);
	}
	
	@Override
	public int pushChatStatus(Map<String,Object> param) throws Exception{
		return pushDAO.pushChatStatus(param);
	}
	
	@Override
	public List<Map<String,Object>> selectPushCnslCnt(Map<String,Object> param) throws Exception{
		return pushDAO.selectPushCnslCnt(param);
	}
	
	//푸시 링크 대상자 리스트 조회
	@Override
	public List<Map<String,Object>> selectPushTgrList(Map<String,Object> param) throws Exception{
		//알림 확인 상태 값 변경 tn_sv_cnsl cnsl_alt_yn = ''
		List<Map<String,Object>> rsList = pushDAO.selectPushTgrList(param);
		return rsList; //trgterInfoMngtDAO.selectPushTgrList(param);
	}
	
	@Override
	public int updatePushHisScheduler(Map<String, Object> param) throws Exception {
		/*List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
		if(userList != null){
			for(Map<String,Object> userInfo : userList){
				if("1".equals(userInfo.get("rowNum"))){
					param.put("sndSn", userInfo.get("sndSn"));
					param.put("sndUserId", userInfo.get("sndUserId"));
					pushDAO.updatePushMas(param);
				}
			}
			for(Map<String,Object> userInfo : userList){
				pushDAO.updatePushHis(userInfo);
				pushDAO.updatePushAllHis(userInfo);
			}
		}
		return 0;*/
		List<Map<String, Object>> userInfoList = (List<Map<String, Object>>)param.get("userInfoList");
	    System.out.println("userInfoList ===> " + userInfoList);
	    if (((Boolean)param.get("flag")).booleanValue() && 
	      userInfoList != null) {
	      for (Map<String, Object> userInfo : userInfoList) {
	        if ("1".equals(userInfo.get("rowNum"))) {
	          param.put("sndSn", userInfo.get("sndSn"));
	          param.put("sndUserId", userInfo.get("sndUserId"));
	          pushDAO.updatePushMas(param);
	        } 
	      } 
	      for (Map<String, Object> userInfo : userInfoList) {
	        pushDAO.updatePushHis(userInfo);
	        pushDAO.updatePushAllHis(userInfo);
	      } 
	    } 
	    return 0;
	}
	
	@Override
	public int updatePushHisSchedulerForMulti(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> userInfoList = (List<Map<String,Object>>)param.get("USER_LIST");
		//List<Map<String,Object>> userInfoList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
		System.out.println("userInfoList ===> " + userInfoList);
		if((boolean) param.get("flag")) {		
			if(userInfoList != null){
				for(Map<String,Object> userInfo : userInfoList){
					if("1".equals(userInfo.get("rowNum"))){
						param.put("sndSn", userInfo.get("sndSn"));
						param.put("sndUserId", userInfo.get("sndUserId"));						
						pushDAO.updatePushMas(param);
					}
					pushDAO.updatePushHis(userInfo);
					pushDAO.updatePushAllHis(userInfo);
				}
			}
		}
		return 0;
	}
	
	
	@Override
	public List<Map<String, Object>> selectNoticeUserList(Map<String, Object> param) throws Exception{
		return pushDAO.selectNoticeUserList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectPushInfoBadgeList(Map<String, Object> param) throws Exception{
		return pushDAO.selectPushInfoBadgeList(param);
	}
	
	@Override
	public void deletePushMasHist(Map<String, Object> param) throws Exception{
		pushDAO.deletePushMasHist(param);
	}
	
	@Override
	public Map<String, Object> selectPushMas(Map<String, Object> param) throws Exception{
		return pushDAO.selectPushMas(param);
	}
	
	@Override
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param) throws Exception {
		return pushDAO.selectExcsSchSendList(param);
	}

	@Override
	public void insertResvrtPushMas(Map<String, Object> param) throws Exception {
		pushDAO.insertResvrtPushMas(param);
	}

	@Override
	public void insertResvrtPushHis(Map<String, Object> param) throws Exception {
		pushDAO.insertResvrtPushHis(param);
	}
	
	@Override
	public String selectOrgCd(String userId) throws Exception {
		return pushDAO.selectOrgCd(userId);
	}

	@Override
	public String selectPushMsgSeq() throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectPushMsgSeq();
	}

	@Override
	public Map<String, Object> getPushTemplete(Map<String, Object> pushTempMap) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getPushTemplete(pushTempMap);
	}

	@Override
	public void insertFcstResvrtPushHis(Map<String, Object> insPushHisMap) throws Exception {
		// TODO Auto-generated method stub
		pushDAO.insertFcstResvrtPushHis(insPushHisMap);
	}

	@Override
	public List<Map<String, Object>> getFcstPushSndSnList(int fcstSn) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getFcstPushSndSnList(fcstSn);
	}

	@Override
	public int selectPushSendHistCnt(String pushSndSn) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectPushSendHistCnt(pushSndSn);
	}

	@Override
	public void updatePushSendCnt(Map<String, Object> updPushMasMap) throws Exception {
		// TODO Auto-generated method stub
		pushDAO.updatePushSendCnt(updPushMasMap);
	}

	
}
