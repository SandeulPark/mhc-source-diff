package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : BoardDAO.java
 * @Description : 관리자 WEB에서 사용하는 게시판 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.BoardDAO")
public class BoardDAO extends DMultiEgovAbstractMapper {

	/**
	 * 질의응답 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getBoardQnaList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.board.selectBoardQnaList", param);	
		return rsList;  
	}
	
	/**
	 * 질의응답 목록 전체건수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int getBoardQnaListCount(Map<String, Object> param) throws Exception {
		return selectOne("mhc.web.sm.board.selectBoardQnaListCount", param);
	}
	
	/**
	 * 질의 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getQueDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sm.board.selectQueDtls", param);	
		return rsMap;  
	}
	
	/**
	 * 응답 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getAnsDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sm.board.selectAnsDtls", param);	
		return rsMap;  
	}
	
	
	/**
	 * 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */	
	public int saveBoardQna(Map<String, Object> param) throws Exception {
		String queryId = "";
		int rsInt = 0;
		
		if(param.get("BOARD_SN") == null || "".equals(param.get("BOARD_SN"))){
			queryId = "mhc.web.sm.board.insertQna";
		}else{
			queryId = "mhc.web.sm.board.updateQna";
		}
		
		rsInt = insert(queryId, param);
		return rsInt;
	}	
	
	
	/**
	 * 질의응답 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getQnaAttchFileList(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> rsList = selectList("mhc.web.sm.board.selectAttchFileList", param);	
		return rsList;  
	}
	
	/**
	 * 질의응답 조회수 update
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void setBoardInquireCnt(Map<String, Object> param) throws Exception {
		update("mhc.web.sm.board.updateBoardInquireCnt", param);
	}
	
	/**
	 * 질의응답 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteBoard(Map<String, Object> param) throws Exception {
		update("mhc.web.sm.board.deleteBoard", param);
	}
}
