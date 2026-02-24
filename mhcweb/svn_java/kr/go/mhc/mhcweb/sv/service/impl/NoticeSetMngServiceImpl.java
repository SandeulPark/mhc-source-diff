package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.sv.service.NoticeSetMngService;

@Service("web.sv.NoticeSetMngService")
public class NoticeSetMngServiceImpl implements NoticeSetMngService{
	
	@Resource(name="web.sv.NoticeSetMngServiceDAO")
	private NoticeSetMngServiceDAO noticeSetMngServiceDAO;

	@Override
	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getNoticeSttusList(param);
	}

	@Override
	public List<Map<String, String>> getReceiverList(Map<String, Object> param)throws Exception  {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getReceiverList(param);
	}

	@Override
	public Map<String, Object> getSttusCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getSttusCnt(param);
	}

	@Override
	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getPushNoticetrgterList(param);
	}

	@Override
	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getTodayManualSendList(param);
	}
	
	@Override
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.manualLinkPageList(param);
	}

	@Override
	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getTrgterGroup(param);
	}

	@Override
	public String getPushMsgSeq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getPushMsgSeq(param);
	}

	@Override
	public List<Map<String, String>> getAutoPushMsgList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getAutoPushMsgList(param);
	}

	@Override
	public void saveAutoPushMsg(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		noticeSetMngServiceDAO.saveAutoPushMsg(param);
	}

	@Override
	public List<Map<String, String>> getDetailList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getDetailList(param);
	}
	
	@Override
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.selectTrgterNoticeSndSttus(param);
	}
	
	@Override
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.checkTrgterJoinInfo(param);
	}

	@Override
	public int getNoticeSttusListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.getNoticeSttusListCount(param);
	}

	@Override
	public void deletePushInfoHis(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		noticeSetMngServiceDAO.deletePushInfoHis(param);
	}

	@Override
	public void updatePushInfoHis(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		noticeSetMngServiceDAO.updatePushInfoHis(param);
	}

	@Override
	public int insertPushInfoHis(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.insertPushInfoHis(param);
	}

	@Override
	public List<Map<String, String>> selectPushInfoHisList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return noticeSetMngServiceDAO.selectPushInfoHisList(param);
	}

	

}
