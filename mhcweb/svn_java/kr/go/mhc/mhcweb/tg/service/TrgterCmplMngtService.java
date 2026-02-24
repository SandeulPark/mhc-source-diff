package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface TrgterCmplMngtService {

	/**
	 * 완료 대상자 관리 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>>  trgterCmplMngtList(Map<String, Object> param) throws Exception;
}
