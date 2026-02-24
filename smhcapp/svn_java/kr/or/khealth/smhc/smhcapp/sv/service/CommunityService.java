package kr.or.khealth.smhc.smhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface CommunityService {
	
	public List<Map<String, Object>> selectCommunityList(Map<String, Object> param) throws Exception;
	
	public int checkCommunityInfo(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getCommunitySttus(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectCommunityCont(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectCommunityCmmnt(Map<String, Object> param) throws Exception;
	
	public int insertComment(Map<String, Object> param) throws Exception;
	
	public int mergeCmmntLike(Map<String, Object> param) throws Exception;
	
	public int updateRdCnt(Map<String, Object> param) throws Exception;
	
	public int updateCmmnt(Map<String, Object>param) throws Exception;

	public void commentRptSubmit(Map<String, Object> param) throws Exception;

	public void userBlockSubmit(Map<String, Object> param) throws Exception; 
	
}
