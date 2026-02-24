package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.gn.service.GnrlRankService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("gn.gnrlRankService")
public class GnrlRankServiceImpl extends EgovAbstractServiceImpl implements GnrlRankService{

	@Resource(name="gn.gnrlRankDAO")
	private GnrlRankDAO gnrlRankDAO;
	
	/**
	 * 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankList(param);
	}
	/**
	 * 랭킹 공통미션 내점수 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankMyScoreList1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankMyScoreList1(param);
	}
	
	/**
	 * 랭킹 자체미션 내점수 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankMyScoreList2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankMyScoreList2(param);
	}
	
	/**
	 * 랭킹 전국 통계데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankStatData1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankStatData1(param);
	}
	
	/**
	 * 랭킹 성별 통계데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankStatData2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankStatData2(param);
	}
	
	/**
	 * 랭킹 연령 통계데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankStatData3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankStatData3(param);
	}
	
	/**
	 * 랭킹 보건소 통계데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankStatData4(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankStatData4(param);
	}
	
	/**
	 * 상세 나의 랭킹 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankDtls(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankDtls(param);
	}
	
	/**
	 * 상세 랭킹 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankDtlsList(param);
	}
	
	/**
	 * 포인트 이력 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectPointList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectPointList(param);
	}
	
	/**
	 * 걸음수 랭킹 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActRankStatData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectActRankStatData(param);
	}
	
	/**
	 * 걸음수 랭킹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActRankList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectActRankList(param);
	}
	
	/**
	 * 걸음수 랭킹 차트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActRankChart(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectActRankChart(param);
	}
	
	/**
	 * 미션 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMissionScore(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMissionScore(param);
	}
	
	/**
	 * 랭킹 월 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankYm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankYm(param);
	}
	
	/**
	 * 랭킹 주 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankWeek(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankWeek(param);
	}
	
	/**
	 * 달성 유무 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMissionChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMissionChk(param);
	}
	
	/**
	 * 랭킹 최대 점수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMissionAchvScore(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMissionAchvScore(param);
	}
	
	/**
	 * 미션 달성 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertMissionAchvDta(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		gnrlRankDAO.insertMissionAchvDta(param);
	}
	
	/**
	 * 활동량계 업데이트 건수
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_00270028(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_00270028(param);
	}
	
	/**
	 * 활동량계 연속 업데이트 건수
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0029(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0029(param);
	}
	
	/**
	 * 일일 목표걸음수 달성
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0030(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0030(param);
	}
	
	/**
	 * 식생활 미션 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_00310032(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_00310032(param);
	}
	
	/**
	 * 식생활 미션 연속 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0033(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0033(param);
	}
	
	/**
	 * 식사기록 연속 등록 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0038(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0038(param);
	}
	
	/**
	 * 게시판 덧글달기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0040(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0040(param);
	}
	
	/**
	 * 게시판 감정표현
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0041(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0041(param);
	}
	
	/**
	 * 게시판 SNS 공유
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectMission_0042(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectMission_0042(param);
	}
	
	/**
	 * 랭킹 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRankInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectRankInfo(param);
	}
	
	/**
	 * 걸음수 랭킹 차트 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActRankChartInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectActRankChartInfo(param);
	}
	
	/**
	 * 걸음수 랭킹 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectActRankInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return gnrlRankDAO.selectActRankInfo(param);
	}
	
	/**
	 * 뱃지 획득 현황
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	@Override
	public Map<String, Object> selectBadgeAchvSttus(Map<String, Object> param) throws Exception {
		return gnrlRankDAO.selectBadgeAchvSttus(param);
	}
	
	/**
	 * 뱃지 획득 상세 목록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */		
	@Override
	public List<Map<String, Object>> selectBadgeAchvDtlsList(Map<String, Object> param) throws Exception {
		return gnrlRankDAO.selectBadgeAchvDtlsList(param);
	}
}
