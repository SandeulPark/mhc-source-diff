package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : BoardDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자 상태 정보 변경 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.sm.TrgterSttusInfoChgDAO")
public class TrgterSttusInfoChgDAO extends DMultiEgovAbstractMapper{
	
	/**
	 * 대상자 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.trgterSttusInfoChg.selectTrgterSttusInfoList",param);
		return rsList;  
	}
	
	/**
	 * 대상자 테스트 정보 변경
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int trgterSttusInfoTestChg(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sm.trgterSttusInfoChg.trgterSttusInfoTestChg", param);
		return rsInt;
		}
	/**
	 * 대상자 서비스 상태 정보 변경
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int trgterSttusInfoTestChange(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sm.trgterSttusInfoChg.trgterSttusInfoTestChange", param);
		return rsInt;
		}
	/**
	 * 대상자 서비스 스케줄 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoServiceSchedule(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.trgterSttusInfoChg.selectTrgterSttusInfoServiceSchedule", param);
		return rsList;
	}
	/**
	 * 대상자 정보 검진 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoExamList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.trgterSttusInfoChg.selectTrgterSttusInfoExamList",param);
		return rsList;  
	}
	/**
	 * 대상자 건강검진 정보 변경
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int trgterSttusChange(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sm.trgterSttusInfoChg.trgterSttusChange", param);
		return rsInt;
		}
	/**
	 * 대상자 스케줄 프로시저
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String trgterScheduleChg(Map<String, Object> param) {
		return selectOne("mhc.web.sm.trgterSttusInfoChg.trgterScheduleChg", param);
	}
	/**
	 * 대상자 스케줄 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int trgterScheduleDelete(Map<String, Object>param) {
		return delete("mhc.web.sm.trgterSttusInfoChg.trgterScheduleDelete", param);
	}
	/**
	 * 대상자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoExamSn(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sm.trgterSttusInfoChg.selectTrgterSttusInfoExamSn", param);
		return rsList;
	}
	/**
	 * 대상자 성별 변경시  변경
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateTrgterSttusInfoExamSn(Map<String, Object>param) {
		return delete("mhc.web.sm.trgterSttusInfoChg.updateTrgterSttusInfoExamSn", param);
	}
}
