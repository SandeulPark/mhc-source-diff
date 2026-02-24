package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.PushSendService;

import org.springframework.stereotype.Service;


@Service("web.sv.PushSendService")
public class PushSendServiceImpl implements PushSendService{
	
	@Resource(name="web.sv.PushSendDAO")
	private PushSendDAO pushSendDAO;

	@Override
	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param) throws Exception {
		return pushSendDAO.getNoticeSttusList(param);
	}

	@Override
	public List<Map<String, String>> getReceiverList(Map<String, Object> param)throws Exception  {
		return pushSendDAO.getReceiverList(param);
	}

	@Override
	public Map<String, Object> getSttusCnt(Map<String, Object> param) throws Exception {
		return pushSendDAO.getSttusCnt(param);
	}

	@Override
	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) throws Exception {
		return pushSendDAO.getPushNoticetrgterList(param);
	}

	@Override
	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param) throws Exception {
		return pushSendDAO.getTodayManualSendList(param);
	}
	
	@Override
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param) throws Exception {
		return pushSendDAO.manualLinkPageList(param);
	}

	@Override
	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) throws Exception {
		return pushSendDAO.getTrgterGroup(param);
	}

	@Override
	public String getPushMsgSeq(Map<String, Object> param) throws Exception {
		return pushSendDAO.getPushMsgSeq(param);
	}

	@Override
	public List<Map<String, String>> getAutoPushMsgList(Map<String, Object> param) throws Exception {
		return pushSendDAO.getAutoPushMsgList(param);
	}

	@Override
	public void saveAutoPushMsg(Map<String, Object> param) throws Exception {
		pushSendDAO.saveAutoPushMsg(param);
	}

	@Override
	public List<Map<String, String>> getDetailList(Map<String, Object> param) throws Exception {
		return pushSendDAO.getDetailList(param);
	}
	
	@Override
	public int selectNoticeSndCount(Map<String, Object> param) throws Exception {
		return pushSendDAO.selectNoticeSndCount(param);
	}
	
	@Override
	public List<Map<String, String>> selectNoticeSndSttus(Map<String, Object> param) throws Exception {
		return pushSendDAO.selectNoticeSndSttus(param);
	}
	
	@Override
	public int selectTrgterNoticeSndCount(Map<String, Object> param) throws Exception {
		return pushSendDAO.selectTrgterNoticeSndCount(param);
	}
	
	@Override
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) throws Exception {
		return pushSendDAO.selectTrgterNoticeSndSttus(param);
	}
	
	@Override
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) throws Exception {
		return pushSendDAO.checkTrgterJoinInfo(param);
	}

	

}
