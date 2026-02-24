package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

@Repository("gn.gnrlCommunityDAO")
public class GnrlCommunityDAO extends DMultiEgovAbstractMapper{

	/**
	 * 커뮤니티 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmnty(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectCmnty", param);
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
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectContent", param);
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
		insert("mhcapp.gn.community.insertCmnty", param);
	}
	
	/**
	 * 커뮤니티 상세 페이지 조회(글번호)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailContent(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectDetailContent", param);
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
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectDetailComment", param);
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
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectCheckAddFiles", param);
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
		update("mhcapp.gn.community.updateCont", param);
	}
	
	
	/**
	 * 커뮤니티 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateGood(Map<String, Object> param) throws Exception {
		update("mhcapp.gn.community.updateGood", param);
		update("mhcapp.gn.community.updateGoodCNFM",param);
	}
	
	/**
	 * 커뮤니티 스크랩 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateClip(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.gn.community.updateClip", param);
		update("mhcapp.gn.community.updateClipCount", param);
	}
	
	/**
	 * 공유 여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.gn.community.updateSharedCont", param);
	}
	
	/**
	 * 공유 확인 수 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCnfmCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.gn.community.updateSharedCnfmCnt", param);
	}

	/**
	 * 커뮤니티 댓글 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("mhcapp.gn.community.insertComment", param);
		update("mhcapp.gn.community.updateComment", param);
	}
	
	/**
	 * 댓글 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmntAddFiles(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectCmntAddFiles", param);
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
		insert("mhcapp.gn.community.insertPhotoComment", param);
		insert("mhcapp.gn.community.insertComment", param);
		update("mhcapp.gn.community.updateComment", param);
	}
	
	/**
	 * 글 확인 여부 체크 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void checkingList(Map<String, Object> param) throws Exception {
		int nCnt = selectOne("mhcapp.gn.community.selectCmntyNotice", param);
		if(nCnt == 0){
			insert("mhcapp.gn.community.insertCmntyNotice", param);
		}  
	}

	/** 
	 * 커뮤니티 댓글 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateDetailComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhcapp.gn.community.updateDetailComment", param);
		update("mhcapp.gn.community.updateDetailCommentCnt", param);
		
	}

	/**
	 * 커뮤니티 카카오톡 공유 글 내용 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	
	public List<Map<String, String>> selectDetailContentForKakao(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectDetailContentForKakao", param);
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
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectCheckDtlsAddFiles", param);
		return rsList;
	}
	
	/**
	 * 메인 컨텐츠 팝업 유무
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertMainPopYn(Map<String, Object> param) throws Exception {
		insert("mhcapp.gn.community.insertMainPopYn", param);
	}
	
	/**
	 * 대상자 게시글 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertTrgterBoard(Map<String, Object> param) throws Exception {
		if(param.get("BOARD_SN").toString().equals("")){
			int boardsn = selectOne("mhcapp.gn.community.selectBoardSn",param);
			param.put("BOARD_SN",boardsn);			
			insert("mhcapp.gn.community.insertCmntyBoardPostG", param);
		}
		update("mhcapp.gn.community.insertNewHealthInfo", param);
	}
	
	/**
	 *  대상자 게시글 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectTrgterBoardConts(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.gn.community.selectTrgterBoardConts", param);
		return rsList;
	}	
}
