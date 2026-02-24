package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface RankService {

	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankList(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 공통미션 내점수 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankMyScoreList1(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 자체미션 내점수 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankMyScoreList2(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 전국 통계 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankStatData1(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 성별 통계 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankStatData2(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 연령 통계 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankStatData3(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 보건소 통계 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankStatData4(Map<String, Object> param) throws Exception;
	
	/**
	 * 상세 나의 랭킹 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankDtlsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 포인트 이력 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPointList(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActRankStatData(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActRankList(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActRankChart(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMissionScore(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 월 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankYm(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 주 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankWeek(Map<String, Object> param) throws Exception;
	
	/**
	 * 달성 유무 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMissionChk(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 최대 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMissionAchvScore(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션 달성 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertMissionAchvDta(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량계 업데이트 건수
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_00270028(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량계 연속 업데이트 건수
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0029(Map<String, Object> param) throws Exception;
	
	/**
	 * 일일 목표걸음수 달성
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0030(Map<String, Object> param) throws Exception;
	
	/**
	 * 식생활 미션 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_00310032(Map<String, Object> param) throws Exception;
	
	/**
	 * 식생활 미션 연속 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0033(Map<String, Object> param) throws Exception;
	
	/**
	 * 식사기록 연속 등록 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0038(Map<String, Object> param) throws Exception;
	
	/**
	 * 게시판 덧글달기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0040(Map<String, Object> param) throws Exception;
	
	/**
	 * 게시판 감정표현
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0041(Map<String, Object> param) throws Exception;
	
	/**
	 * 게시판 SNS 공유
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0042(Map<String, Object> param) throws Exception;
	
	/**
	 * 랭킹 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRankInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 차트 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActRankChartInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 걸음수 랭킹 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActRankInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 뱃지 획득 현황
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, Object> selectBadgeAchvSttus(Map<String, Object> param) throws Exception;
	
	/**
	 * 뱃지 획득 상세 목록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectBadgeAchvDtlsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 2022 랭킹 간소화 
	 * 일일 활동량계 업데이트
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0100(Map<String, Object> param) throws Exception;
	
	/**
	 * 2022 랭킹 간소화 
	 * 식생활 미션 여부 확인
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0101(Map<String, Object> param) throws Exception;
	
	/**
	 * 2022 랭킹 간소화
	 * 식사 일기 등록
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMission_0102(Map<String, Object> param) throws Exception;
	
	/**
	 * 2022 랭킹 간소화
	 * 게시판 댓글 달기(증진원 게시물만)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMission_0108(Map<String, Object> param) throws Exception;
	
		
	/**
	 * 
	 * 미션코드중복제거 0100~0108
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectCompleteMission(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * 미션코드 0105 ~0107 점수 기준 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectStandardPointSts(Map<String, Object> param) throws Exception;
	
	
}
