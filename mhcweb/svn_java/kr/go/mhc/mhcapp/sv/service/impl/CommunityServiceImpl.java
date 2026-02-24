package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.CommunityService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : CommunityServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 커뮤니티에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.08		허광일			최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.sv.CommunityService")
public class CommunityServiceImpl extends EgovAbstractServiceImpl implements CommunityService{
	
	@Resource(name="mhcapp.sv.CommunityDAO")
    private CommunityDAO communityDAO;
	
	/**
	 * 커뮤니티 이름 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectCmnty(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCmnty(param);
	}
	
	/**
	 * 커뮤니티 글목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectContent(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectContent(param);
	}
	
	/**
	 * 커뮤니티 글 상세페이지 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDetailContent(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectDetailContent(param);
	}
	
	/**
	 * 커뮤니티 글 상세페이지 댓글 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectDetailComment(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectDetailComment(param);
	}
	
	/**
	 * 커뮤니티 글 상세페이지 댓글 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectCheckAddFiles(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCheckAddFiles(param);
	}
	
	/**
	 * 게시물 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.updateCont(param);
	}
	
	/**
	 * 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateGood(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.updateGood(param);
	}

	/**
	 * 스크랩 On, Off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateClip(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.updateClip(param);
	}

	/**
	 * 댓글 입력
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.insertComment(param);
	}

	/**
	 * 댓글 상세페이지 댓글 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectCmntAddFiles(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCmntAddFiles(param);
	}
	
	/**
	 * 댓글 사진 입력
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertPhotoComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.insertPhotoComment(param);
	}

	@Override
	public void checkingList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.checkingList(param);
	}

	/**
	 * 상세 페이지 댓글 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	
	@Override
	public void updateDetailComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.updateDetailComment(param);
	}

	/**
	 * 커뮤니티 카카오톡 공유 글 내용 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	@Override
	public List<Map<String, String>> selectDetailContentForKakao(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectDetailContentForKakao(param);
	}

	/**
	 * 커뮤니티 카카오톡 공유 글 첨부파일 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectCheckAddFilesForKakao(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCheckAddFilesForKakao(param);
	}
}
