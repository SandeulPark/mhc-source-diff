package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;


public interface CnslReqMngService {

	public List<Map<String, String>> getCnslReqList(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getOne_on_one_cnsl_List(Map<String, Object>param) throws Exception;

	public int getChatSeq(Map<String, Object> param)throws Exception;

	public Map<String, String> getTrgtInfo(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getCnslhist(Map<String, Object>param) throws Exception;

	public void chatMasterInsert(Map<String, Object> param)throws Exception;

	public void chatCnslerInsert(Map<String, Object> param)throws Exception;

	public void chatTrgterInsert(Map<String, Object> param)throws Exception;

	public void cnslEndChatMasterUp(Map<String, Object> param)throws Exception;

	public void cnslChatMasterUp(Map<String, Object> param)throws Exception;

	public void cnslCnslerInsert(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> cnslInquireCont(Map<String, Object> param)throws Exception;

	public void insertWebMessage(Map<String, Object> param);

	public List<Map<String, String>> selectWebTalkList(Map<String, Object> param)  throws Exception;
	
	public void realtimeCnslIngUpdate(Map<String, Object> param) throws Exception;
	
	public void realtimeCnslCompUpdate(Map<String, Object> param) throws Exception;
	
	public int getChatCnslSeq(Map<String, Object> param) throws Exception;
	
	public void chatInsertCnslTB(Map<String, Object> param) throws Exception;
}
