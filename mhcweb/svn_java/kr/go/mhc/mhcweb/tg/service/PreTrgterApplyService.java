package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface PreTrgterApplyService {

	public List<Map<String, String>> preTrgterApplyList(Map<String, Object> param) throws Exception;

	public int updateApprovalYn(Map<String, Object> param) throws Exception;

	public Map<String, Object> preTrgterApplyRegit(Map<String, Object> param)  throws Exception;

	
}
