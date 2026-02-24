package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MissionMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 미션설정 관리업무 DataBase 연동 관리하는 Class
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

@Repository("web.gn.GnrlMissionMngtDAO")
public class GnrlMissionMngtDAO extends DMultiEgovAbstractMapper{

	/**
	 * 미션설정관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int getMissionMngtListCount(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.gn.gnrlmissionmngt.selectMissionCount", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.selectMissionList", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 상세
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getMissionMngtDtls(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.gn.gnrlmissionmngt.selectMissionDtls", param);
		return rsMap;
	}

	/**
	 * 미션설정관리 게시구분 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionSelGubun(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.missionSelGubun", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 미션 선택시
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getSelMission(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.gn.gnrlmissionmngt.selMission", param);
		return rsMap;
	}
	
	/**
	 * 미션설정관리 게시구분 선택시
	 * @param param 게시구분 코드
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionPostGubun(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = null; 
		if("11".equals(param.get("SCLAS_CD")) ) {
			rsList = selectList("mhc.web.gn.gnrlmissionmngt.missionPostMain", param);
		} else if ("12".equals(param.get("SCLAS_CD")) ) {
			rsList = selectList("mhc.web.gn.gnrlmissionmngt.missionPostCmnty", param);
		}
		return rsList;
	}
	
	/**
	 * 미션설정관리 참여대상수 조회
	 * @param param GCLAS_CD(메인) GRP_SN(커뮤니티)
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getMissionJoinInquire(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = null; 
		if( "11".equals(param.get("SCLAS_CD")) ) {
			rsMap = selectOne("mhc.web.gn.gnrlmissionmngt.missionJoinInquireMain", param);
		} else if ( "12".equals(param.get("SCLAS_CD")) ) {
			rsMap = selectOne("mhc.web.gn.gnrlmissionmngt.missionJoinInquireCmntyUserId", param);
		}
		return rsMap;
	}

	/**
	 * 미션설정관리 순번 조회
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	public int getMissionInquireSN(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("mhc.web.gn.gnrlmissionmngt.missionInquireSN", param);
		return rsInt;
	}
	
	/**
	 * 미션설정관리 미션 설정 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMission(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmissionmngt.insertMission", param);
	}
	
	/**
	 * 미션설정관리 미션 설정 그룹 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionGrp(Map<String, Object> param) throws Exception {
		String allData = param.get("GRP_SN").toString();
		String eachData[] = allData.split(",");
		int cnt = Integer.parseInt(param.get("find_grp").toString());
		for(int i=0; i<cnt; i++){
			param.put("GRP_SN2", eachData[i]);
			insert("mhc.web.gn.gnrlmissionmngt.insertMissionGrp", param);
		}	
	}
	
	/**
	 * 미션설정관리 게시판 순번 조회
	 * @param param 미션코드
	 * @return 
	 * @throws Exception
	 */
	public int getSelBoardSn(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("mhc.web.gn.gnrlmissionmngt.selBoardSn", param);
		return rsInt;
	}
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertCmntyBoard(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmissionmngt.insertCmntyBoard", param);
	}

	/**
	 * 미션설정관리 적용그룹 등록(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertBoardGrp(Map<String, Object> param) throws Exception {
		String allData = param.get("GRP_SN").toString();
		String eachData[] = allData.split(",");
		int cnt = Integer.parseInt(param.get("find_grp").toString());
		
		
		System.out.println("param :::::::::: " + param);
		
		for(int i=0; i<cnt; i++){
			param.put("GRP_SN1", eachData[i]);
			insert("mhc.web.gn.gnrlmissionmngt.insertPostGrp", param);
		}
	}

	/**
	 * 미션 코드 관리 코드 내용 변경
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateMission(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlmissionmngt.updateMission", param);
	}
	
	/**
	 * 미션설정관리 미션 설정 그룹 테이블 UPDATE
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void deleteMissionGrp(Map<String, Object> param) throws Exception {
		delete("mhc.web.gn.gnrlmissionmngt.deleteMissionGrp", param);
	}
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateCmntyBoard(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlmissionmngt.updateCmntyBoard", param);
	}
	
	/**
	 * 미션설정관리 적용그룹 삭제(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void deletePostGrp(Map<String, Object> param) throws Exception {
		delete("mhc.web.gn.gnrlmissionmngt.deletePostGrp", param);
	}

	/**
	 * 미션설정관리 게시구분 선택 시 적용그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getDtlsSelGrp(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = null;
		if("11".equals(param.get("POST_CLF")) ) {
			rsList = selectList("mhc.web.gn.gnrlmissionmngt.dtlsSelMain", param);
		} else if("12".equals(param.get("POST_CLF")) ) {
			rsList = selectList("mhc.web.gn.gnrlmissionmngt.dtlsSelCmnty", param);
		}
		return rsList;
	}
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getNotSelMainGrp(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.notSelMainGrp", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (커뮤니티)
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNotSelCmntyGrp(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.notSelCmntyGrp", param);
		return rsList;
	}
	
	/**
	 * 게시구분이 메인 인 경우(참여자 목록)
	 * 메인 인 경우
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelBottomList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.selMainBottomList", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelReList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.selCmntyBottomList", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 전체 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getCmntyReCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.gn.gnrlmissionmngt.cmntyReCnt", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getCmntyExpCnt(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.gn.gnrlmissionmngt.cmntyExpCnt", param);
		return rsList;
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelExpressionList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList =selectList("mhc.web.gn.gnrlmissionmngt.selExpressionList", param);
		return rsList;
	}

	/**
	 * 미션설정관리 답변달기(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionReply(Map<String, Object> param) throws Exception {
		param.put("CMNTY_CD", "T001001");
		param.put("LST_DML_ID", param.get("SESS_USER_ID"));
		param.put("WRITER_ID", param.get("SESS_USER_ID"));
		
		String allData = param.get("targetInfo").toString();
		int cnt = Integer.parseInt(param.get("targetCNT").toString());
		String eachData[] = allData.split(",");
		
		int j=0;
		for(int i=0; i<cnt; i++){
			param.put("UPPER_CMMNT_SN", eachData[i+j]);
			param.put("BOARD_SN", eachData[i+j+1]);
			insert("mhc.web.gn.gnrlmissionmngt.insertMissionReply", param);
			j += 2;
		}
	}
	
	/**
	 * 미션설정관리 게시여부(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updatePostYN(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlmissionmngt.updatePostYN", param);
	}

	/**
	 * 미션점수 업데이트 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateMissionScore(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.gn.gnrlmissionmngt.updateMissionScore", param);
	}

	/**
	 * 미션점수 등록 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void insertMissionScore(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		String[] reCmmntTgrtInfo = param.get("reCmmntTgrtInfo").toString().split(",");
		int reCmmntTgrtCnt = Integer.parseInt(param.get("reCmmntTgrtCnt").toString());	
		int j=0;
		for(int i=0; i < reCmmntTgrtCnt; i++){
			param.put("CMMNT_SN", reCmmntTgrtInfo[i+j]);
			param.put("WRITER_ID", reCmmntTgrtInfo[i+j+1]);
			insert("mhc.web.gn.gnrlmissionmngt.insertMissionScore", param);
			j += 1;
		}
		
	}
	
	/**
	 * 첨부파일 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmissionmngt.selectAttchFileList", param);		
		return rsList;  
	}
}
