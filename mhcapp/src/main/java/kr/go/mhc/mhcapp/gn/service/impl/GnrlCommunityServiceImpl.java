package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.gn.service.GnrlCommunityService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("gn.gnrlCommunityService")
public class GnrlCommunityServiceImpl extends EgovAbstractServiceImpl implements GnrlCommunityService{
	
	@Resource(name="gn.gnrlCommunityDAO")
	private GnrlCommunityDAO gnrlCommunityDAO;

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
		return gnrlCommunityDAO.selectCmnty(param);
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
		return gnrlCommunityDAO.selectContent(param);
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
		return gnrlCommunityDAO.selectDetailContent(param);
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
		return gnrlCommunityDAO.selectDetailComment(param);
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
		return gnrlCommunityDAO.selectCheckAddFiles(param);
	}
	
	/**
	 * 게시물 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.updateCont(param);
	}
	
	/**
	 * 좋아요 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateGood(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.updateGood(param);
	}

	/**
	 * 스크랩 On, Off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateClip(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.updateClip(param);
	}
	
	/**
	 * 공유 여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.updateSharedCont(param);
	}
	
	/**
	 * 공유 확인 수 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSharedCnfmCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.updateSharedCnfmCnt(param);
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
		gnrlCommunityDAO.insertComment(param);
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
		return gnrlCommunityDAO.selectCmntAddFiles(param);
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
		gnrlCommunityDAO.insertPhotoComment(param);
	}

	@Override
	public void checkingList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.checkingList(param);
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
		gnrlCommunityDAO.updateDetailComment(param);
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
		return gnrlCommunityDAO.selectDetailContentForKakao(param);
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
		return gnrlCommunityDAO.selectCheckAddFilesForKakao(param);
	}
	
	/**
	 * 메인 컨텐츠 팝업 유무
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertMainPopYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.insertMainPopYn(param);
	}
	
	/**
	 * 대상자 게시글 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertTrgterBoard(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlCommunityDAO.insertTrgterBoard(param);
	}
	
	/**
	 *  대상자 게시글 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectTrgterBoardConts(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlCommunityDAO.selectTrgterBoardConts(param);
	}
}
