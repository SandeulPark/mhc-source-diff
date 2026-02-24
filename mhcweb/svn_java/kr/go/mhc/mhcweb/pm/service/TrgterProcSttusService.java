package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterProcSttusService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 진행 현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 *
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface TrgterProcSttusService {

	/**
	 * 대상자 등록현황 일반 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterProcSttusList(Map<String, Object> param) throws Exception;

	/**
	 * 추후관리 스케줄 생성
	 * @param param
	 * @return
	 */
	public void trgterProcSttusAfterSchedule(Map<String, Object> param)throws Exception;

	/**
	 * 추후관리 스케줄 생성 후 마스터 업데이트
	 * @param param
	 * @return
	 */
	public int updateTrgterProcSttus(Map<String, Object> param) throws Exception;
}
