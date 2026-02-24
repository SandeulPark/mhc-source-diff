package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.common.crontab.PushBatchSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("common.pushService")
public class PushServiceImpl extends EgovAbstractServiceImpl implements PushService{
	
	@Resource(name="pushDAO")
    private PushDAO pushDAO;
	
	@Autowired
	private PushBatchSender pushBatchSender;

	@Override
	public void updateToken(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		pushDAO.updateToken(param);
		
	}

	@Override
	public Map<String, Object> getSndSn() throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getSndDn();
	}

	@Override
	public int insertPushHis(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
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
	public int insertPushHisServey(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> selPushMas = (Map<String, Object>)pushDAO.selectPushMasServey(param);
		
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
		// TODO Auto-generated method stub
//		int rtInt = pushDAO.updatePushMas(param);
//		if(rtInt > 0){
			List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
			if(userList != null){
				for(Map<String,Object> userInfo : userList){
//					rtInt += pushDAO.updatePushHis(userInfo);
					if("1".equals(userInfo.get("rowNum"))){
						param.put("sndSn", userInfo.get("sndSn"));
						param.put("sndUserId", userInfo.get("sndUserId"));
						pushDAO.updatePushMas(param);
					}
					pushDAO.updatePushHis(userInfo);
				}
			}
//		}
		
		return 0;
	}	

	@Override
	public int insertPushHisTopic(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = -1;
		rtInt = pushDAO.insertPushMas(param);
		if(rtInt>0){
			pushDAO.insertPushHisTopic(param);
		}
		
		return rtInt;
	}

	@Override
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectTokenList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectSendList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectSendList(param);
	}
	
	@Override
	public String selectGrpUserList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectGrpUserList(param);
	}
	
	@Override
	public String selectOrgUserList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectOrgUserList(param);
	}
	
	@Override
	public Map<String, String> getPushSndYn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getPushSndYn(param);
	}
	
	@Override
	public void setPushSndYn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		pushDAO.setPushSndYn(param);
	}
	
	@Override
	public List<Map<String, Object>> topicUserList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.topicUserList(param);
	}
	
	@Override
	public boolean sendPushData(PushMessageUtil pushMessageUtil, Map<String, Object> param)
			throws Exception {

		boolean rsFlag = false;
		String userId = "";
		//1. 그룹
		String grpSn = StringUtil.nvl(String.valueOf(param.get("grpList")));
		//2. 군분류
		String mclsCd =  StringUtil.nvl(String.valueOf(param.get("selMclasList")));
		//3. 만성질환
		String chronicCd = StringUtil.nvl(String.valueOf(param.get("selChronicList")));
		//4. 대상자용 설문조사
		String serveySn = StringUtil.nvl(String.valueOf(param.get("SERVEY_SN")));
		 
		System.out.println("grpSn ===> " + grpSn);
		System.out.println("mclsCd ===> " + mclsCd);
		System.out.println("chronicCd ===> " + chronicCd);
		System.out.println("serveySn ===> " + serveySn);		
		
		if(!"".equals(grpSn)){
			List<Map<String,String>> grpIter = StringUtil.makeStringToIterator(grpSn);
			param.put("grpIter", grpIter);
			
			if(!"".equals(mclsCd)) {
				List<Map<String,String>> mclsIter = StringUtil.makeStringToIterator(mclsCd);
				param.put("mclsIter", mclsIter);
			}
			if(!"".equals(chronicCd)) {
				List<Map<String,String>> chronicIter = StringUtil.makeStringToIterator(chronicCd);
				param.put("chronicIter", chronicIter);
			}
			userId = selectGrpUserList(param);
		}
		
		if(!"".equals(serveySn)) {			
			userId = selectOrgUserList(param);
			pushMessageUtil.setServeySn(serveySn);
		}
		
		// 그룹 대상자가 없을 경우, param의 userList를 userId에 넣음		
		if(userId == ""){
			userId = StringUtil.nvl(String.valueOf(param.get("userList")));
		}
				
		if(!"".equals(userId)){
			// 3-4. 대상자가 있으면 pushMessageUtil.setUserId에 값 설정
			pushMessageUtil.setUserId(userId.split(","));
			int insCnt = 0;
			// 3-5. 푸시 마스터 및 히스 테이블에 데이터 저장  
			if(pushMessageUtil.sendNotifition()){
				if(!"".equals(serveySn)) {
					insCnt = insertPushHisServey(pushMessageUtil.getResultMap());
				}else {
					insCnt = insertPushHis(pushMessageUtil.getResultMap());
				}
			}
			
			if(StringUtil.nvl(param.get("reqClf")).equals("10")){
				// 3-7. 유저 정보를 다시 조회.
				List<Map<String,String>> userIter = StringUtil.makeStringToIterator(userId);
				param.put("userIter", userIter);
				param.put("sndSn", pushMessageUtil.getSndSn());
				List<Map<String,Object>> sendList = selectSendList(param);
				
				if(sendList != null){
					if (sendList != null && !sendList.isEmpty()) {
						Map<String,Object> resultMap = pushBatchSender.sendPushInBatches(sendList);				
						updatePushHis(resultMap);
						rsFlag = true;
			        }
				}
			}
		}
		
		return rsFlag;
	}

	@Override
	public Map<String, String> getPushSndYnNotice(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getPushSndYnNotice(param);
	}
	
	@Override
	public void setNoticePushSndYn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		pushDAO.setNoticePushSndYn(param);
	}
	
	@Override
	public Map<String, String> selectGrpList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectGrpList(param);
	}
	
	@Override
	public Map<String, String> selectMclasList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return pushDAO.selectMclasList(param);
	}

	@Override
	public Map<String, String> selectChronicList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return pushDAO.selectChronicList(param);
	}

	@Override
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.updatePushStatus(param);
	}
	
	@Override
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.pushUpdateCnfm(param);
	}
	
	@Override
	public int pushChatStatus(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.pushChatStatus(param);
	}
	
	@Override
	public List<Map<String,Object>> selectPushCnslCnt(Map<String,Object> param) throws Exception{
		return pushDAO.selectPushCnslCnt(param);
	}
	
	//푸시 링크 대상자 리스트 조회
	@Override
	public List<Map<String,Object>> selectPushTgrList(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		//알림 확인 상태 값 변경 tn_sv_cnsl cnsl_alt_yn = ''
		List<Map<String,Object>> rsList = pushDAO.selectPushTgrList(param);
		/*
		if(!rsList.isEmpty()){
			Map<String, Object> csParam = new HashMap<String,Object>();
			for(int i=0;i<rsList.size();i++){
				csParam.clear();
				csParam.put("chat_sn", rsList.get(i).get("CHAT_SN"));
				pushDAO.pushChatStatus(csParam);
			}
		}
		*/
		return rsList; //trgterInfoMngtDAO.selectPushTgrList(param);
	}
	
	@Override
	public int updatePushHisScheduler(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
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
			}
		}
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> selectNoticeUserList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.selectNoticeUserList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectPushInfoBadgeList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.selectPushInfoBadgeList(param);
	}
	
	@Override
	public void deletePushMasHist(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		pushDAO.deletePushMasHist(param);
	}
	
	@Override
	public Map<String, Object> selectPushMas(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.selectPushMas(param);
	}
	
	@Override
	public Map<String, Object> selectPushMasServey(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.selectPushMasServey(param);
	}
	
	@Override
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectExcsSchSendList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectVisitSchResvrtSendCnt()
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectVisitSchResvrtSendCnt();
	}	
	
	@Override
	public List<Map<String, Object>> selectVisitSchResvrtSendList(String orgCd)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectVisitSchResvrtSendList(orgCd);
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
	public Map<String, Object> getPushSetInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.getPushSetInfo(param);
	}

	
}
