package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.01		이은주			최초생성
 *
 * @author gst
 * @since 2016.09.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.TrgterInfoMngtDAO")
public class TrgterInfoMngtDAO extends EgovAbstractMapper{
	
	//대상자정보관리 대상자 목록 조회
	public List<Map<String, Object>> trgterInfoMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.trgterInfoMngtList", param);
		return rsList;
	}
	
	//대상자정보관리 대상자 상세 조회
	public Map<String, Object> trgterInfoMngtDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.trgterInfoMngtDtls", param);
		return rsMap;
	}
	
	//대상자정보관리 대상자 저장
	public void updateTrgterInfo(Map<String, Object> param) throws Exception {
		/*
		if(!"".equals(param.get("DROP_STND")) && param.get("DROP_STND") != null) {
			if(!"N".equals(param.get("drop_hist_YN"))) {
				insert("mhc.web.tg.trgterinfomngt.insertTrgterHist", param);		//현재 상태 이력에 저장				
			}
		}
		*/
		
		Map<String, Object> rsMap = selectOne("common.cmmn.selectPwChangeChk", param);
		param.put("PW_CNT", rsMap.get("PW_CNT"));
		update("mhc.web.tg.trgterinfomngt.updateTrgterInfoUSER", param);
		update("mhc.web.tg.trgterinfomngt.updateTrgterInfoTRGTER", param);		//+중도탈락시킴
		update("mhc.web.tg.trgterinfomngt.updatePreTrgterDtlsInfo", param);
		if(!"".equals(param.get("DROP_STND")) && param.get("DROP_STND") != null) {
			update("mhc.web.tg.trgterinfomngt.updatePreTrgterInfo", param);		//예비대상자 상태 변경 '50'-대상자, '80'-중도탈락
			//2016.10.25 이태석 추가
			//보건소 커뮤니티 모든 그룹 맴버 삭제 
			delete("mhc.web.tg.trgterinfomngt.trgterAllCmntyGrpMbDel", param);	
			
		}
	}
	
	//대상자정보관리 신체활동 탭 활동목표
	public List<Map<String, Object>> selTrgterActList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterActList", param);
		return rsList;
	}
	
	//대상자정보관리 신체활동 탭 일자별
	public List<Map<String, Object>> selTrgterActDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterActDEList", param);
		return rsList;
	}
	
	//대상자정보관리 신체활동 탭 일자별 평균
	public Map<String, Object> selTrgterActDEAVG(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterActDEAVG", param);
		return rsMap;
	}
	
	//대상자정보관리 신체활동 탭 요일별
	public List<Map<String, Object>> selTrgterActDYList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterActDYList", param);
		return rsList;
	}
	
	//대상자정보관리 신체활동 탭 누적, 평균
	public List<Map<String, Object>> selTrgterTotActCnt(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterTotActCnt", param);
		return rsList;
	}
	
	//대상자정보관리 신체활동 탭 주차별 현황
	public List<Map<String, Object>> selTrgterWKInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterWKInfo", param);
		return rsList;
	}
	
	//대상자정보관리 체성분 탭 체중목표
	public List<Map<String, Object>> selTrgterWeightList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterWeightList", param);
		return rsList;
	}
	
	//대상자정보관리 체성분 탭 측정값 변화 (일자별)
	public List<Map<String, Object>> selTrgterWeightDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterWeightDEList", param);
		return rsList;
	}
	
	//대상자정보관리 체성분 탭 체성분 시작, 종료 비교
	public Map<String, Object> selTrgterWeightSTED(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterWeightSTED", param);
		return rsMap;
	}
	
	//대상자정보관리 체성분 탭 서비스 주차별 현황
	public List<Map<String, Object>> selTrgterWeightWKInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterWeightWKInfo", param);
		return rsList;
	}
	
	//대상자정보관리 혈압 최근 측정현황
	public Map<String, Object> selTrgterBldPressList(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterBldPressList", param);
		return rsMap;
	}
	
	//대상자정보관리 혈압 측정값 변화 (일자별)
	public List<Map<String, Object>> selTrgterBldPressDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterBldPressDEList", param);
		return rsList;
	}
	
	//대상자정보관리 혈압 기간 평균, 최초 측정 비교
	public Map<String, Object> selTrgterBldPressSTAVG(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterBldPressSTAVG", param);
		return rsMap;
	}
	
	//대상자정보관리 혈압 서비스 주차별 현황
	public List<Map<String, Object>> selTrgterBldWKInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterBldWKInfo", param);
		return rsList;
	}
	
	//대상자정보관리 혈압 이상 측정정보 (그리드)
	public List<Map<String, Object>> selPressDisorderExamInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selPressDisorderExamInfo", param);
		return rsList;
	}
	
	//대상자정보관리 혈압 및 혈당 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 
	public void updateDisorderExamProc(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.trgterinfomngt.updateDisorderExamProc", param);
	}
	
	//대상자정보관리 혈당 최근 측정현황 (테이블)
	public Map<String, Object> selTrgterBldSugarList(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterBldSugarList", param);
		return rsMap;
	}
	
	//대상자정보관리 혈당 측정값 변화 (차트)
	public List<Map<String, Object>> selTrgterBldSugarDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterBldSugarDEList", param);
		return rsList;
	}
	
	//대상자정보관리 혈당 기간 평균, 최초 측정 비교 (테이블)
	public Map<String, Object> selTrgterBldSugarSTAVG(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterBldSugarSTAVG", param);
		return rsMap;
	}
	
	//대상자정보관리 혈당 서비스 주차별 현황 (그리드)
	public List<Map<String, Object>> selTrgterBldSugarWKInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterBldSugarWKInfo", param);
		return rsList;
	}

	//대상자정보관리 혈당 이상 측정정보 (그리드)
	public List<Map<String, Object>> selSugarDisorderExamInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selSugarDisorderExamInfo", param);
		return rsList;
	}
	//대상자정보관리 검진기록 검진결과 (그리드)
	public List<Map<String, Object>> selTrgterExamRsltList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterExamRsltList", param);
		return rsList;
	}
	
	//대상자정보관리 검진기록 검사결과 (테이블)
	public Map<String, Object> selTrgterExamRslt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterExamRslt", param);
		return rsMap;
	}

	//대상자정보관리 서비스참여 탭 건강정보 측정 상세(테이블)
	public List<Map<String, Object>> selTrgterSvcHealthInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selTrgterSvcHealthInfo", param);
		return rsList;
	}
	
	//대상자정보관리 서비스참여 탭 건강정보 실천률(테이블)
	public Map<String, Object> selTrgterSvcHealthInfoPracRate(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selTrgterSvcHealthInfoPracRate", param);
		return rsMap;
	}
	
	// 대상자정보관리 상담 탭 방문상담 목록 조회
	public List<Map<String, Object>> selectVisitCnslList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectVisitCnslList", param);
		return rsList;
	}
	
	//대상자정보관리 상담 탭 집중상담 목록 조회
	public List<Map<String, Object>> selectFocusCnslList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectFocusCnslList", param);
		return rsList;
	}
	
	//대상자정보관리 상담 탭 일반상담 목록 조회
	public List<Map<String, Object>> selectGeneralCnslList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectGeneralCnslList", param);
		return rsList;
	}
	
	//대상자정보관리 상담 탭 실시간상담 목록 조회
	public List<Map<String, Object>> selectRealtimeCnslList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectRealtimeCnslList", param);
		return rsList;
	}
	
	//대상자정보관리 상담 탭 일반상담 상담확인 팝업
	public Map<String, Object> selectGeneralCnsl(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selectGeneralCnsl", param);
		return rsMap;
	}
	
	//대상자정보관리 상담 탭 일반상담 저장 여부 체크
	public Map<String, Object> selectGeneralCnslChk(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selectGeneralCnslChk", param);
		return rsMap;
	}
	
	//대상자정보관리 상담 탭 일반상담 상담저장, 상담게시
	public void updateGeneralCnsl(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.trgterinfomngt.updateGeneralCnsl", param);
	}
	
	//대상자정보관리 상담 탭 실시간 상담 1:1 상담 요청  중복 체크
	public int countRealTimeCnslChk(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("mhc.web.tg.trgterinfomngt.countRealTimeCnslChk", param);
		return rsInt;
	}
	
	//대상자정보관리 중도탈락 업데이트
	public void updateDrop(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.trgterinfomngt.insertTrgterHist", param);		//현재 상태 이력에 저장
		update("mhc.web.tg.trgterinfomngt.updateDrop", param);				//중도탈락시킴
		update("mhc.web.tg.trgterinfomngt.updateJoinMngtDrop", param);		//서비스참여 상태 변경
		update("mhc.web.tg.trgterinfomngt.updatePreTrgterInfo", param);		//예비대상자 상태 변경 '50'
		//2016.10.25 이태석 추가
		//보건소 커뮤니티 모든 그룹 맴버 삭제 
		delete("mhc.web.tg.trgterinfomngt.trgterAllCmntyGrpMbDel", param);	
	}
	
	//대상자정보관리 중도탈락 조회
	public Map<String, Object> selectDrop(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selectDrop", param);
		return rsMap;
	}
	
	//대상자정보관리 중도탈락 취소
	public Map<String, Object> cancelDrop(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.trgterinfomngt.selecTrgterHist", param);	//중도탈락 이전 TRGTER_STTUS 값 가져옴. 
		insert("mhc.web.tg.trgterinfomngt.insertTrgterHist", param);		//현재 상태 이력에 저장
		param.put("TRGTER_STTUS", rsMap.get("TRGTER_STTUS"));
		update("mhc.web.tg.trgterinfomngt.cancelDrop", param);				//중도탈락 취소
		update("mhc.web.tg.trgterinfomngt.updateJoinMngtCancelDrop", param);//서비스참여 상태 변경
		update("mhc.web.tg.trgterinfomngt.updatePreTrgterInfo", param);		//예비대상자 상태 변경 '80'
		//2016.10.25 이태석 추가
		//보건소 커뮤니티 기본 그룹 맴버 추가 
		insert("mhc.web.tg.trgterinfomngt.trgterCmntyGrpMbAdd", param);
		return rsMap;
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 활동량 일자별 현황 (그리드)
	public List<Map<String, Object>> getDayActDataList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectDayActDataList", param);
		return rsList;
	}
	
	//2017.02.23 이태석 추가	
	//대상자정보관리 체성분 일자별 현황 (그리드)
	public List<Map<String, Object>> getDayBodyCompDataList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectDayBodyCompDataList", param);
		return rsList;
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 혈압 일자별 현황 (그리드)
	public List<Map<String, Object>> getDayBloodPressDataList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectDayBloodPressDataList", param);
		return rsList;
	}
	
	//2017.02.23 이태석 추가
	//대상자정보관리 혈당 일자별 현황 (그리드)
	public List<Map<String, Object>> getDayBloodSugarDataList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectDayBloodSugarDataList", param);
		return rsList;
	}
	
	//2017.06.16 추가
	//대상자정보관리 심박수 탭 심박목표 (그리드)		
	public List<Map<String, Object>> selHeartGoal(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectHeartGoal", param);
		return rsList;
	}	
	
	//2017.06.16 추가
	//대상자정보관리 심박수 탭 목표심박달성률 (그리드)		
	public List<Map<String, Object>> selHeartAchiRate(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectHeartAchiRate", param);
		return rsList;
	}		
	
	//2017.06.16 추가
	//대상자정보관리 심박수 탭 일자별 심박측정정보 (그리드)		
	public List<Map<String, Object>> selDayHeartData(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectDayHeartData", param);
		return rsList;
	}	
	
	//2017.06.16 추가
	//대상자정보관리 심박수 탭 시간대별 심박측정정보 (그리드)		
	public List<Map<String, Object>> selHourHeartData(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectHourHeartData", param);
		return rsList;
	}	
	
	// 2017.08.30 추가
	//대상자 모니터링 메모 조회
	public List<Map<String, Object>> selectMemoDtls(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectMemoDtls", param);
		return rsList;
	}
	
	// 2017.08.30 추가
	// 대상자 모니터링 메모 신규 저장
	public int insertMemo(Map<String,Object> param) throws Exception{
		String insGb = (String) param.get("INS_GB") == null ? "I" : (String) param.get("INS_GB");
		String sql = "I".equals(insGb) ? "insertMemo" : "updateMemo";
		return update("mhc.web.tg.trgterinfomngt."+sql, param);
	}
	
	//2018.06.19 유준영 추가
		//대상자이력정보 활동량 다운로드용 조회 (그리드)
		public List<Map<String, Object>> getTrgterMeasrInfoExcelActList(Map<String, Object> param) throws Exception {
			List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectMeasrInfoExcelActList", param);
			return rsList;
		}
	//2018.06.19 유준영 추가
		//대상자이력정보 이력정보 체성분 다운로드용 조회 (그리드)	
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBodyCompList(Map<String, Object> param) throws Exception {
			List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectMeasrInfoExcelBodyCompList", param);
			return rsList;
		}
		
	//2018.06.19 유준영 추가
		//대상자이력정보 혈압 다운로드용 조회 (그리드)	
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodPressList(Map<String, Object> param) throws Exception {
			List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectMeasrInfoExcelBloodPressList", param);
			return rsList;
			}
	//2018.06.19 유준영 추가
		//대상자이력정보 혈당 다운로드용 조회 (그리드)	
		public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodSugarList(Map<String, Object> param) throws Exception {
			List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectMeasrInfoExcelBloodSugarList", param);
			return rsList;
			}		
	//대상자 통합검색 리스트 조회(대상자 검색 팝업)
	public List<Map<String, Object>> trgterCbSearchList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectCbSearchList", param);
		return rsList;
	}
	
	//대상자 통합검색 관리목표-신체활동
	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.bodyGoalList", param);
		return rsList;
	}
	
	//대상자 통합검색 관리목표-영양
	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.nutriGoalList", param);
		return rsList;
	}
	
	//대상자 통합검색 정보 조회
	public List<Map<String, Object>> trgterCbInfo(Map<String, Object> param) throws Exception{
		String sql = param.get("trgtSql").toString();
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.select"+sql+"InfoList", param);
		return rsList;
	}

	public List<Map<String, Object>> selHourHeartData2(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.trgterinfomngt.selectHourHeartData2", param);
		return rsList;
	}

}
