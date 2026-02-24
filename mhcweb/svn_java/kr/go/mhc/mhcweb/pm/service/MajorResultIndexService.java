package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name :MajorResultIndexService.java
 * @Description : 관리자 WEB에서 사용하는 대상자별 주요성과 지표 실적관리 업무를 관리하는 Interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.11.21		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.11.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MajorResultIndexService {
	
	
	
	/**
	 * 개요
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSummaryList(Map<String, Object> param) throws Exception;



	/**
	 * 개요 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSummaryTrgterList(Map<String, Object> param) throws Exception;	
	
	
	
	/**
	 * 지속참여자 및 중도탈락자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterPartDropList(Map<String, Object> param) throws Exception;



	/**
	 * 지속참여자 및 중도탈락자 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterPartDropTrgterList(Map<String, Object> param) throws Exception;

	
	/**
	 * 건강행태 1개이상 개선자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthResultImpList(Map<String, Object> param) throws Exception;	

	
	
	/**
	 * 건강행태 1개이상 개선자 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthResultImpTrgterList(Map<String, Object> param) throws Exception;

	
	/**
	 * 건강위험요인 1개이상 감소자 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthDangerDecList(Map<String, Object> param) throws Exception;

	
		
	/**
	 * 건강위험요인 1개이상 감소자 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthDangerDecTrgterList(Map<String, Object> param) throws Exception;

		
	
	
	/**
	 * 만족도 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectServeySatisScoreList(Map<String, Object> param) throws Exception;

	
	
	/**
	 * 만족도 대상자 화면 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectServeySatisScoreTrgterList(Map<String, Object> param) throws Exception;


	/**
	 * 실적관리 > 주요 성과지표 > 개요 > 상세 목록 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectSummaryTrgerList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 개요 (실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSummaryListNew(Map<String, Object> param) throws Exception;
	
	/**
	 * 지속참여자 및 중도탈락자 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterPartDropListNew(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 건강행태 1개이상 개선자 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthResultImpListNew(Map<String, Object> param) throws Exception;	
	
	
	/**
	 * 건강위험요인 1개이상 감소자 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthDangerDecListNew(Map<String, Object> param) throws Exception;

	
	
	/**
	 * 만족도 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectServeySatisScoreListNew(Map<String, Object> param) throws Exception;
	
	/**
	 * 실적관리 > 주요 성과지표 > 개요 > 상세 목록 조회(실적개편)
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectSummaryTrgerListNew(Map<String, Object> param) throws Exception;


	/**
	 * 실적관리 > 주요 성과지표 > 지속참여 및 중도탈락 > 상세 목록 조회(실적개편)
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> selectTrgterPartDropTrgterListNew(Map<String, Object> param);


	/**
	 * 실적관리 > 주요 성과지표 > 건강행태 1개이상 개선자 > 상세 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthResultImpTrgterListNew(Map<String, Object> param);


	/**
	 * 실적관리 > 주요 성과지표 > 건강위험요인 1개이상 개선자 > 상세 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectHealthDangerDecTrgterListNew(Map<String, Object> param);


	/**
	 * 실적관리 > 주요 성과지표 > 만족도점수 > 상세 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectServeySatisScoreTrgterListNew(Map<String, Object> param);
}
