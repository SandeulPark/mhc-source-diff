package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.sv.CnslReqMngServiceDAO")
public class CnslReqMngServiceDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> getCnslReqList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.cnslreqmng.selectCnslReqList",param);
		return rsList;
	}

	public List<Map<String, String>> getOne_on_one_cnsl_List(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.cnslreqmng.selectOne_on_one_cnsl_List",param);
		return rsList;
	}
	
	public Map<String, String> getTrgtInfo(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.sv.cnslreqmng.selectTrgtInfo",param);	
		return rsMap;
	}

	public int getChatSeq(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		int rsMap = selectOne("mhc.web.sv.cnslreqmng.selectChatSeq",param);	
		System.out.println("getRsMap==> " + rsMap);
		return rsMap;
	}

	public List<Map<String, String>> getCnslhist(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.cnslreqmng.selectCnslhist",param);
		return rsList;
	}

	public void chatMasterInsert(Map<String, Object> param)throws Exception{
		// TODO Auto-generated method stub
		insert("mhc.web.sv.cnslreqmng.insertChatMaster", param);
	}

	public void chatCnslerInsert(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		insert("mhc.web.sv.cnslreqmng.insertChatCnsler", param);
	}

	public void chatTrgterInsert(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		insert("mhc.web.sv.cnslreqmng.insertChatTrgter", param);
	}

	public void cnslEndChatMasterUp(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.sv.cnslreqmng.updateChatMaster", param);
	
	}

	public void cnslChatMasterUp(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.sv.cnslreqmng.updatecnslChatMaster", param);
		System.out.println("updateParam" + param);
	}

	public void cnslCnslerInsert(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("mhc.web.sv.cnslreqmng.insertcnslChatCnsler", param);
	}

	public List<Map<String, String>> cnslInquireCont(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList  = selectList("mhc.web.sv.cnslreqmng.selectCnslCont",param);	
		return rsList;
	}

	public void insertWebMessage(Map<String, Object> param) {
		insert("mhc.web.sv.cnslreqmng.insertWebMessage", param);
		
	}

	public List<Map<String, String>> selectWebTalkList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.sv.cnslreqmng.selectWebTalkList", param);	
		return rsList;  
	}
	
	public void realtimeCnslIngUpdate(Map<String, Object> param){
		update("mhc.web.sv.cnslreqmng.realtimeCnslIngUpdate", param);
	}
	
	public void realtimeCnslCompUpdate(Map<String, Object> param){
		update("mhc.web.sv.cnslreqmng.realtimeCnslCompUpdate", param);
	}
	
	//실시간채팅 상담테이블 순번 조회
	public int getChatCnslSeq(Map<String, Object> param) {
		int rsInt = selectOne("mhc.web.sv.cnslreqmng.selectCnslSeq", param);
		return rsInt;
	}
	
	//실시간채팅 상담테이블 생성
	public void chatInsertCnslTB(Map<String, Object> param) {
		insert("mhc.web.sv.cnslreqmng.chatInsertCnslTB", param);
	}

}
