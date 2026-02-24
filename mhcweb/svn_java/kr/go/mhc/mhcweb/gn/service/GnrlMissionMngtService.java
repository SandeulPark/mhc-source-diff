package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MissionMngtService.java
 * @Description : 관리자 WEB에서 사용하는 미션설정관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlMissionMngtService {
	
	/**
	 * 미션설정관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int getMissionMngtListCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getMissionMngtList(Map<String, Object> param) throws Exception;	
	
	/**
	 * 미션설정관리 상세
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getMissionMngtDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시구분 선택 시 적용그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getDtlsSelGrp(Map<String, Object> param) throws Exception;

	/**
	 * 미션설정관리 게시구분 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionSelGubun(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 미션 선택시
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getSelMission(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시구분 선택시
	 * @param param 게시구분 코드
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionPostGubun(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 참여대상수 조회
	 * @param param GCLAS_CD(메인) GRP_SN(커뮤니티)
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getMissionJoinInquire (Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 순번 조회
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	public int getMissionInquireSN(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 미션 설정 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMission(Map<String, Object> param) throws Exception;

	/**
	 * 미션설정관리 미션 설정 그룹 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시판 순번 조회
	 * @param param 미션코드
	 * @return 
	 * @throws Exception
	 */
	public int getSelBoardSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertCmntyBoard(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 적용그룹 등록(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertBoardGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateMission(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 미션 설정 그룹 테이블 UPDATE
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void deleteMissionGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateCmntyBoard(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 적용그룹 삭제(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void deletePostGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNotSelMainGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (커뮤니티)
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNotSelCmntyGrp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 메인 인 경우
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelBottomList(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 전체 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getCmntyReCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelReList(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getCmntyExpCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelExpressionList(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 답변달기(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionReply(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션설정관리 게시여부(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updatePostYN(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션점수 업데이트 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateMissionScore(Map<String, Object> param) throws Exception;

	/**
	 * 미션점수 등록 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionScore(Map<String, Object> param) throws Exception;
	
	/**
	 * 첨부파일 조회
	 * @param param 정보
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception;
}
