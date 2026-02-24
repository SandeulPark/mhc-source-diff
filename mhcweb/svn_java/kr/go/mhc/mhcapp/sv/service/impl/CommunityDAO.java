package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CommunityDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 커뮤니티에 DataBase 연동 관리하는 Class
 * @Modification Information @ @ 수정일 수정자 수정내용 
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.08		허광일			최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.CommunityDAO")
public class CommunityDAO extends DMultiEgovAbstractMapper {
	
	/**
	 * 커뮤니티 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmnty(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectCmnty", param);
		return rsList;
	}
	
	/**
	 * 커뮤니티 전체 게시물 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContent(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectContent", param);
		return rsList;
	}
	
	/**
	 * 커뮤니티 전체 게시물 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertCmnty(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("mhcapp.sv.community.insertCmnty", param);
	}
	
	/**
	 * 커뮤니티 상세 페이지 조회(글번호)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailContent(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectDetailContent", param);
		return rsList;
	}
	
	/**
	 * 커뮤니티 상세페이지 댓글 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectDetailComment", param);
		return rsList;
	}
	
	/**
	 * 커뮤니티 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCheckAddFiles(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectCheckAddFiles", param);
		return rsList;
	}
	
	/**
	 * 게시물 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.sv.community.updateCont", param);
	}
	
	
	/**
	 * 커뮤니티 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateGood(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.sv.community.updateGood", param);
		update("mhcapp.sv.community.updateGoodCNFM",param);
	}
	
	/**
	 * 커뮤니티 스크랩 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateClip(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.sv.community.updateClip", param);	
	}

	/**
	 * 커뮤니티 댓글 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("mhcapp.sv.community.insertComment", param);
		update("mhcapp.sv.community.updateComment", param);
	}
	
	/**
	 * 댓글 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmntAddFiles(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectCmntAddFiles", param);
		return rsList;
	}
	
	/** 
	 * 커뮤니티 댓글 사진 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertPhotoComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("mhcapp.sv.community.insertPhotoComment", param);
		insert("mhcapp.sv.community.insertComment", param);
		update("mhcapp.sv.community.updateComment", param);
	}
	
	/**
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void checkingList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = selectOne("mhcapp.sv.community.selectCmntyNotice", param);
		if(nCnt == 0){
			insert("mhcapp.sv.community.insertCmntyNotice", param);
		}  
	}

	public void updateDetailComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.sv.community.updateDetailComment", param);
		update("mhcapp.sv.community.updateDetailCommentCnt", param);
		
	}

	/**
	 * 커뮤니티 카카오톡 공유 글 내용 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	public List<Map<String, String>> selectDetailContentForKakao(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectDetailContentForKakao", param);
		return rsList;
	}

	/**
	 * 커뮤니티 카카오톡 공유 글 첨부파일 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCheckAddFilesForKakao(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.community.selectCheckAddFilesForKakao", param);
		return rsList;
	}
	
}
