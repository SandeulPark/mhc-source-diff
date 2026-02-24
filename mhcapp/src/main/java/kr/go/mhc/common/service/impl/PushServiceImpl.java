package kr.go.mhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.service.PushService;
import kr.go.mhc.common.util.PushMessageUtil;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("common.pushService")
public class PushServiceImpl extends EgovAbstractServiceImpl implements PushService{
	
	@Resource(name="pushDAO")
    private PushDAO pushDAO;

	@Override
	public void updateToken(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		pushDAO.updateToken(param);
		
	}

	@Override
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.updatePushStatus(param);
	}
	
	@Override
	public int updateSndSnNum(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.updateSndSnNum(param);
	}
	
	@Override
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.pushUpdateCnfm(param);
	}

	@Override
	public List<Map<String,Object>> selectAdmTokenList(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		return pushDAO.selectAdmTokenList(param);
	}

	@Override
	public int insertPushHis(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = pushDAO.insertPushMas(param);
//		if(rtInt > 0){
			List<Map<String,Object>> userList = (List<Map<String,Object>>)param.get(PushMessageUtil.USER_LIST);
			param.put("sndSnNum", userList.get(0).get("sndSn"));
			if(userList != null){
				for(Map<String,Object> userInfo : userList){
					rtInt += pushDAO.insertPushHis(userInfo);
				}
			}
//		}
		
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
	public List<Map<String, Object>> selectSendList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return pushDAO.selectSendList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectVisitAllList(Map<String, Object> param) throws Exception {
		return pushDAO.selectVisitAllList(param);
	}
}
