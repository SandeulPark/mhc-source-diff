package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : BodyActObstyCnslService.java
 * @Description : 관리자 WEB에서 사용하는 신체활동비만 상담 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface BodyActObstyCnslService {

	/**
	 * 신체_활동_비만 상담
	 * 신체_활동_비만 상담 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getBodyActObstyCnslList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담
	 * 상담 대기자수 조회
	 * @param 
	 * @return 검색된 COLUM
	 * @throws Exception
	 */	
	public int getBodyActObstyCnslCount() throws Exception;

	/**
	 * 신체_활동_비만 상담_상세
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getBodyActObstyCnslDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 활동_장비_연동 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String,String> getActEquipTestYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 활동_장비_지급_완료
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void getActEquipPymntY(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 상담 등록_일자 조회 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getCnslActRegDeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 상담 조회
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getCnslAct(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 위험요인 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getRiskFactorList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 상담순번 조회 (신규 클릭 시)
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getActCnslSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체_활동_비만 상담_상세
	 * 상담 저장_업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void getSaveActCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 2017.04.12 이태석 추가 (운동 정보 팝업)
	 * 부위 별 운동 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getBodyPartExcsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 최대산소섭취량 판정
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	public Map<String, String> getMaxOxyIntakeAmJudge(Map<String, Object> param) throws Exception;
	
	/**
	 * 운동 목표 설정 변경
	 * @param param
	 * @throws Exception
	 */
	public void updateBodyObstyCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 상담_활동_이력 카운트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getCnslHistCnt(Map<String,Object> param) throws Exception;
	
	/**
	 * 추천 운동 선정 목록 추가
	 * @param param
	 * @throws Exception
	 */
	public void addRecomExcsInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 추천 운동 선정 목록 삭제
	 * @param param
	 * @throws Exception
	 */
	public void deleteRecomExcsInfo(Map<String, Object> param) throws Exception;	


	/**
	 * 추천 운동 선정 목록
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getRecomExcsSetList(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표 심박 계산 나이 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getHRCalAge(Map<String,Object> param) throws Exception;
	
	/**
	 * 신체활동상담 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBodyActCnslContInfo(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> getRecomExcsTemplateList(Map<String, Object> param)throws Exception;
	
	public int getRecomExcsTemplateUpdate(Map<String, Object> param) throws Exception;

	public int getRecomExcsTemplateDel(Map<String, Object> param) throws Exception;
	
	public int getRecomExcsTemplateCnslUpdate(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getRecomExcsTempList(Map<String, Object> param) throws Exception;
	
	public int getRecomExcsPreCnsllUpdate(Map<String, Object> param) throws Exception;
	
	public int getRecomExcsTempDelOne(Map<String, Object> param) throws Exception;
}
