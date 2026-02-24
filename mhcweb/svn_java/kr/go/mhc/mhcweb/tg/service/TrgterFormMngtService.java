package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface TrgterFormMngtService {
	/**
	 * 대상자 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> getTrgterFormMngtList(Map<String, Object> param)throws Exception;

	
	
	/**
	 * 대상자 전자서명 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> getTrgterFormViewInfo(Map<String, Object> param)throws Exception;
}
