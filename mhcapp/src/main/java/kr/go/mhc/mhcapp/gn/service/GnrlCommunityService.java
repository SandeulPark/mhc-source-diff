package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

public interface GnrlCommunityService {
	
	/**
	 * 커뮤니티 이름 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmnty(Map<String, Object> param) throws Exception;
	
	/**
	 * 커뮤니티 글목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContent(Map<String, Object> param) throws Exception;
	
	/**
	 * 커뮤니티 글 상세페이지 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailContent(Map<String, Object> param) throws Exception;
	
	/**
	 * 커뮤니티 글 상세페이지 댓글 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailComment(Map<String, Object> param) throws Exception;		
	
	/**
	 * 커뮤니티 글의 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCheckAddFiles(Map<String, Object> param) throws Exception;	
	
	/**
	 * 게시물 확인 update
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateCont(Map<String, Object> param) throws Exception;
	
	/**
	 * 좋아요 갯수 update
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateGood(Map<String, Object> param) throws Exception;
	
	/**
	 * 스크랩 On, Off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateClip(Map<String, Object> param) throws Exception;
	
	/**
	 * 공유 여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCont(Map<String, Object> param) throws Exception;
	
	/**
	 * 공유 확인 수 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCnfmCnt(Map<String, Object> param) throws Exception;

	/**
	 * 댓글 입력
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertComment(Map<String, Object> param) throws Exception;
	
	/**
	 * 댓글의 첨부파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCmntAddFiles(Map<String, Object> param) throws Exception;	
	
	/**
	 * 댓글 사진 입력	
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertPhotoComment(Map<String, Object> param) throws Exception;

	/**
	 * 게시판 확인 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void checkingList(Map<String, Object> param) throws Exception;

	/**
	 * 상세 페이지 댓글 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateDetailComment(Map<String, Object> param) throws Exception;

	/**
	 * 커뮤니티 카카오톡 공유 글 내용 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDetailContentForKakao(Map<String, Object> param) throws Exception;

	/**
	 * 커뮤니티 카카오톡 공유 글 첨부파일 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCheckAddFilesForKakao(Map<String, Object> param) throws Exception;

	/**
	 * 메인 컨텐츠 팝업 유무
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertMainPopYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 게시글 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertTrgterBoard(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 게시글 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectTrgterBoardConts(Map<String, Object> param) throws Exception;

}
