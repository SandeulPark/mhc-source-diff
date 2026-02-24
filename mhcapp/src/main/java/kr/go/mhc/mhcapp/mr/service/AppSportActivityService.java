package kr.go.mhc.mhcapp.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : AppSportsActivitylService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 스포츠활동 인증 서비스 Class
 */

public interface AppSportActivityService {

	public int insertSportActivityUserInfo(Map<String, Object> param) throws Exception;

	public Map<String, Object> chkExistSportActivityUserInfo(Map<String, Object> param) throws Exception;

	public void updateSportActivityUserAgree(Map<String, Object> param)  throws Exception;

	public void updateSportActivityCertWrite(Map<String, Object> param) throws Exception;

	public void delUserInfo(Map<String, Object> param) throws Exception;

	public Map<String, Object> getSportActivityWalkCnt(Map<String, Object> param) throws Exception;

	public Map<String, Object> getSportActivityMeasureYmd(Map<String, Object> param)  throws Exception;
	
	public Map<String, Object> userInfo(String param) throws Exception;
	
	public List<Map<String, Object>> chkGnGroupActivityUserInfo(String param) throws Exception;
	

}
