package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

public interface EduVideosMngtService {
	/**
	 * 교육 동영상 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getEduVideosMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 교육동영상 상세
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getEduVideosDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 교육동영상 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void getEduVideosMngtRegInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 교육동영상 삭제	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getEduVideosMngtDel(Map<String, Object> param) throws Exception;

}

