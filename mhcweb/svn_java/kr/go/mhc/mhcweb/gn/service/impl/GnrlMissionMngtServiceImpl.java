package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.gn.service.GnrlMissionMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :MissionMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 미션설정 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		이은주			최초생성
 
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.gn.GnrlMissionMngtService")
public class GnrlMissionMngtServiceImpl extends EgovAbstractServiceImpl implements GnrlMissionMngtService {
	
	@Resource(name="web.gn.GnrlMissionMngtDAO")
	private GnrlMissionMngtDAO gnrlMissionMngtDAO;
	
	/**
	 * 미션설정관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	@Override
	public int getMissionMngtListCount(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionMngtListCount(param);
	}
	
	/**
	 * 미션설정관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getMissionMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionMngtList(param);
	}

	/**
	 * 미션설정관리 게시구분 선택 시 적용그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getDtlsSelGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getDtlsSelGrp(param);
	}

	/**
	 * 미션설정관리 상세
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	@Override
	public Map<String, String> getMissionMngtDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionMngtDtls(param);
	}

	/**
	 * 미션설정관리 게시구분 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getMissionSelGubun(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionSelGubun(param);
	}

	/**
	 * 미션설정관리 미션 선택시
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> getSelMission(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelMission(param);
	}

	/**
	 * 미션설정관리 게시구분 선택시
	 * @param param 게시구분 코드
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getMissionPostGubun(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionPostGubun(param);
	}
	
	/**
	 * 미션설정관리 참여대상수 조회
	 * @param param GCLAS_CD(메인) GRP_SN(커뮤니티)
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> getMissionJoinInquire(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionJoinInquire(param);
	}

	/**
	 * 미션설정관리 순번 조회
	 * @param param 미션코드
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getMissionInquireSN(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getMissionInquireSN(param);
	}

	/**
	 * 미션설정관리 미션 설정 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertMission(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertMission(param);
	}
	
	/**
	 * 미션설정관리 미션 설정 그룹 테이블 INSERT
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertMissionGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertMissionGrp(param);
	}
	
	/**
	 * 미션설정관리 게시판 순번 조회
	 * @param param 미션코드
	 * @return 
	 * @throws Exception
	 */
	@Override
	public int getSelBoardSn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelBoardSn(param);
	}
	
	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertCmntyBoard(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertCmntyBoard(param);
	}
	
	/**
	 * 미션설정관리 적용그룹 등록(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertBoardGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertBoardGrp(param);
	}

	/**
	 * 미션 코드 관리 코드 내용 변경
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateMission(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.updateMission(param);
	}

	/**
	 * 미션설정관리 미션 설정 그룹 테이블 UPDATE
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteMissionGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.deleteMissionGrp(param);
	}

	/**
	 * 미션설정관리 게시판 등록 (게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateCmntyBoard(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.updateCmntyBoard(param);
	}

	/**
	 * 미션설정관리 적용그룹 삭제(게시구분이 커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deletePostGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.deletePostGrp(param);
	}
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (메인)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getNotSelMainGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getNotSelMainGrp(param);
	}
	
	/**
	 * 미션설정관리 적용안된그룹 조회 (커뮤니티)
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> getNotSelCmntyGrp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getNotSelCmntyGrp(param);
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 메인 인 경우
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSelBottomList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelBottomList(param);
	}

	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSelExpressionList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelExpressionList(param);
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSelReList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelReList(param);
	}

	/**
	 * 미션설정관리 답변달기(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertMissionReply(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertMissionReply(param);
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 댓글 전체 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getCmntyReCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getCmntyReCnt(param);
	}
	
	/**
	 * 미션설정관리 하단 목록 조회
	 * 커뮤니티 인 경우 표현 수
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getCmntyExpCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getCmntyExpCnt(param);
	}

	/**
	 * 미션설정관리 게시여부(커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updatePostYN(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.updatePostYN(param);
	}

	/**
	 * 미션점수 업데이트 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateMissionScore(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.updateMissionScore(param);
	}
	
	/**
	 * 미션점수 등록 (커뮤니티 인 경우)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertMissionScore(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlMissionMngtDAO.insertMissionScore(param);
	}
	
	/**
	 * 첨부파일 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMissionMngtDAO.getSelectAttchFileList(param);
	}

}
