package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.sv.service.CnslReqMngService;

@Service("web.sv.CnslReqMngService")
public class CnslReqMngServiceImpl implements CnslReqMngService{
	
	@Resource(name="web.sv.CnslReqMngServiceDAO")
	private CnslReqMngServiceDAO cnslReqMngServiceDAO;

	@Override
	public List<Map<String, String>> getCnslReqList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.getCnslReqList(param);
	}

	@Override
	public List<Map<String, String>> getOne_on_one_cnsl_List(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.getOne_on_one_cnsl_List(param);
	}

	@Override
	public Map<String, String> getTrgtInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("trgtInfoParam==>" + param);
		return cnslReqMngServiceDAO.getTrgtInfo(param);
		
	}

	@Override
	public int getChatSeq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.getChatSeq(param);
	}

	@Override
	public List<Map<String, String>> getCnslhist(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.getCnslhist(param);
	}

	@Override
	public void chatMasterInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.chatMasterInsert(param);
		System.out.println("chatMasterparam" + param);
	}

	@Override
	public void chatCnslerInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.chatCnslerInsert(param);
		System.out.println("chatCnslerparam" + param);
	}

	@Override
	public void chatTrgterInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.chatTrgterInsert(param);
		System.out.println("chatTrgterparam" + param);
	}

	@Override
	public void cnslEndChatMasterUp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.cnslEndChatMasterUp(param);
	}

	@Override
	public void cnslChatMasterUp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.cnslChatMasterUp(param);
		
	}

	@Override
	public void cnslCnslerInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.cnslCnslerInsert(param);
		System.out.println("cnslCnslerInsert == > " +param);
	}

	@Override
	public List<Map<String, String>> cnslInquireCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.cnslInquireCont(param);
	}

	@Override
	public void insertWebMessage(Map<String, Object> param) {
		cnslReqMngServiceDAO.insertWebMessage(param);
		
	}

	public List<Map<String, String>> selectWebTalkList(Map<String, Object> param) 
			throws Exception {
		return cnslReqMngServiceDAO.selectWebTalkList(param);
	}
	
	@Override
	public void realtimeCnslIngUpdate(Map<String, Object> param) throws Exception {
		cnslReqMngServiceDAO.realtimeCnslIngUpdate(param);
	}
	
	@Override
	public void realtimeCnslCompUpdate(Map<String, Object> param) throws Exception {
		cnslReqMngServiceDAO.realtimeCnslCompUpdate(param);
	}

	@Override
	public int getChatCnslSeq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return cnslReqMngServiceDAO.getChatCnslSeq(param);
	}
	
	@Override
	public void chatInsertCnslTB(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		cnslReqMngServiceDAO.chatInsertCnslTB(param);
	}

	
}
