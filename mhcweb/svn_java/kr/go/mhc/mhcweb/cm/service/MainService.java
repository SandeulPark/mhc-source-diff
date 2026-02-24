package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	/**
	 * 대상자 참여현황 
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getTrgterJoinSttus(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스 지속참여율
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getSvcContinuePer(Map<String, Object> param) throws Exception;
	
	/**
	 * 주간 서비스 참여율
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getWeekSvcJoinPer(Map<String, Object> param) throws Exception;
	
	/**
	 * 금일 서비스 참여자수
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getTodaySvcJoinCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 실시간 상담
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getRealTimeCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 일반상담
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getNormalCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 집중상담 (신체활동 / 영양)
	 * 검색 조건으로 단일 ROW 조회
	 * 2025년 포함 이전
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getIntenseCnsl(Map<String, Object> param) throws Exception;

	/**
	 * 집중상담 (신체활동 / 영양)
	 * 검색 조건으로 단일 ROW 조회
	 * 2026년 이후
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getIntenseCnslNew(Map<String, Object> param) throws Exception;

	/**
	 * 방문상담
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getVisitCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지사항 조회
	 * 검색 조건으로 다중 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public List<Map<String,Object>> getNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * LOGIN_ID 유효성 검사
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public int selectLoginIdCheck(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 중복검사
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public int selectTrgterCheck(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강 이상 정보 대상자 조회
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public int selectHealthDisorderInfoTrgter(Map<String, Object> param) throws Exception;
	
	/**
	 * 최근 5일 이상 미사용자 조회
	 * 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 */
	public int selectSvcNoJoinTrgter(Map<String, Object> param) throws Exception;
	
	/**
	 * 서버 시간 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getServerTime(Map<String,Object> param) throws Exception;
	
	/**
	 * 서비스 미생성 대상자 카운트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int selectSvcSchNotCreateCnt(Map<String, Object> param) throws Exception;
	

	/**
	 * 대상자 특이 사항 사용자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getTrgterInfo(Map<String,Object> param) throws Exception;
	
	/**
	 * 대상자 특이 사항 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getTrgterSpecialNote(Map<String,Object> param) throws Exception;
	
	/**
	 * 대상자 목표상담 메모 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getObjCnslMemo(Map<String,Object> param) throws Exception;
	
	/**
	 * 대상자 집중상담 메모 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getIntenseCnslMemo(Map<String,Object> param) throws Exception;
	
	/**
	 * 대상자 특이사항 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updateSpecialNote(Map<String,Object> param) throws Exception;
	
	/**
	 * 대상자 특이사항 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int deleteSpecialNote(Map<String,Object> param) throws Exception;
	
	/**
	 * 즐겨찾기 메뉴 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getFavoriteMenu(Map<String,Object> param) throws Exception;

	/**
	 * 즐겨찾기 메뉴 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int saveFavoriteMenu(Map<String,Object> param) throws Exception;
	
	/**
	 * 즐겨찾기 메뉴 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int deleteFavoriteMenu(Map<String,Object> param) throws Exception;	
	
	/**
	 * 메인 팝업공지 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPopNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 팝업공지 확인 업데이트(다시보지않기 설정 시)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updPopNoticeCnfm(Map<String, Object> param) throws Exception;
	
	/**
	 * 보건소 권한 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception;

	/**
	 * 메인화면 통합관제 소명 자료 팝업공지 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getPopCallingMaterialNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 메인 팝업공지 설문할목 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPopServeyList(Map<String, Object> param) throws Exception;

	/**
	 * 누적 서비스 참여자수
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getAccumulateSvcJoinCnt(Map<String, Object> param) throws Exception;

	/**
	 * 누적 서비스 탈락자수
	 * 검색 조건으로 단일 ROW 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 */
	public Map<String, Object> getAccumulateSvcDropCnt(Map<String, Object> param) throws Exception;
}
