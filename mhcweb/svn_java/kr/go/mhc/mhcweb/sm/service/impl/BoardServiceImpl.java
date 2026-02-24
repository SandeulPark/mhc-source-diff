package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sm.service.BoardService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :HealthDisValMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 게시판에 필요한 DAO와 연동 관리하는 Class
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

@Service("web.sm.BoardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {

	@Resource(name="web.sm.BoardDAO")
	private BoardDAO boardDAO;
	
	@Override
	public List<Map<String, Object>> getBoardQnaList(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardQnaList(param);
	}
	
	@Override
	public int getBoardQnaListCount(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardQnaListCount(param);
	}
	
	@Override
	public Map<String, Object> getQueDtls(Map<String, Object> param) throws Exception {
		return boardDAO.getQueDtls(param);
	}
	
	@Override
	public Map<String, Object> getAnsDtls(Map<String, Object> param) throws Exception {
		return boardDAO.getAnsDtls(param);
	}	
	
	@Override
	public int saveBoardQna(Map<String, Object> param) throws Exception {
		return boardDAO.saveBoardQna(param);
	}
	
	@Override
	public List<Map<String, Object>> getQnaAttchFileList(Map<String, Object> param) throws Exception {
		return boardDAO.getQnaAttchFileList(param);
	}
	
	@Override
	public void setBoardInquireCnt(Map<String, Object> param) throws Exception {
		boardDAO.setBoardInquireCnt(param);
	}
	
	@Override
	public void deleteBoard(Map<String, Object> param) throws Exception {
		boardDAO.deleteBoard(param);
	}
}
