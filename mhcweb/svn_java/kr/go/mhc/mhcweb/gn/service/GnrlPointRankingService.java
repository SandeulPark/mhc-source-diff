package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : PointRankingService.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 * 
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlPointRankingService {	
	
	/**
	 * 포인트 및 랭킹 메세지 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPointRankingMsg(Map<String, Object> param) throws Exception;
	
	/**
	 * 포인트 및 랭킹 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPointRankingList(Map<String, Object> param) throws Exception;
	
	/**
	 * 순위 별 건강포인트 전체 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getRankingAllPointList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상여부_초기화 및 선정 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int updatepymntTrgtY(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 메세지 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStepRankingMsg(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception;
}
