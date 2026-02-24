package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.st.MthlyHealthRptServiceDAO")
public class MthlyHealthRptServiceDAO extends DMultiEgovAbstractMapper{
	
	//월간리포트제공 리스트 조회
	public List<Map<String, Object>> mthlyHealthRptList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.mthlyHealthRptList", param);
		return rsList;
	}
	
	//월간리포트제공 차수 및 일자 리스트 조회
	public List<Map<String, Object>> mthlyHealthRptDateList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.mthlyHealthRptDateList", param);
		return rsList;
	}
	
	//신체활동 관리목표 리스트 조회
	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.bodyGoalList", param);
		return rsList;
	}
	
	//영양 관리목표 리스트 조회
	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.nutriGoalList", param);
		return rsList;
	}
	
	//상담 템플릿 조회
	public List<Map<String, Object>> selectCnslTemplateNmList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectCnslTemplateNmList", param);
		return rsList;
	}
	
	//월간리포트 상담 내용 조회
	public Map<String, Object> selectMonthlyCnsl(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectMonthlyCnsl", param);
		return rsMap;
	}
	
	//월간리포트 첨부파일 조회
	public List<Map<String, Object>> selectMonthlyCnslAttchFiles(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyCnslAttchFiles", param);
		return rsList;
	}
	
	//요일별 평균 걸음수 차트
	public List<Map<String, Object>> selectDayExceActList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectDayExceActList", param);
		return rsList;
	}
	
	//목표걸음달성, 평균걸음수조회
	public Map<String, Object> selectObeActSuc(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectObeActSuc", param);
		return rsMap;
	}
	
	// 종합 체중변화 차트 데이터 조회
	public List<Map<String, Object>> selectWeightChartList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectWeightChartList", param);
		return rsList;
	}
	
	//종합 현재/목표 체중
	public List<Map<String, Object>> selectWeightList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectWeightList", param);
		return rsList;
	}
	
	// 요일별, 끼니별 칼로리 조회
	public List<Map<String, Object>> selectWeekMealCalList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectWeekMealCalList", param);
		return rsList;
	}
	
	// 식사기록일수, 평균섭취칼로리 조회
	public Map<String, Object> selectDayMealCal(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectDayMealCal", param);
		return rsMap;
	}
	
	// 종합 월별 체성분 차트 데이터 조회
	public List<Map<String, Object>> selectMonthlyBodyCompChartList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyBodyCompChartList", param);
		return rsList;
	}
	
	// 종합 월별 체성분 각 비교값 데이터 조회
	public List<Map<String, Object>> selectMonthlyWeightList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyWeightList", param);
		return rsList;
	}
	
	// 혈압 차트 데이터 조회
	public List<Map<String, Object>> selectMonthlyBloodPressChartList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyBloodPressChartList", param);
		return rsList;
	}
	
	// 혈압 평균 데이터 조회
	public List<Map<String, Object>> selectMonthlyBloodPressData(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyBloodPressData", param);
		return rsList;
	}
	
	// 혈당 차트 데이터 조회
	public List<Map<String, Object>> selectMonthlyBloodSugarChartList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyBloodSugarChartList", param);
		return rsList;
	}
	
	// 혈당 평균 데이터 조회
	public List<Map<String, Object>> selectMonthlyBloodSugarData(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyBloodSugarData", param);
		return rsList;
	}
	
	// 칼로리, 운동시간 조회
	public List<Map<String, Object>> selectMonthlyKcalTime(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.st.mthlyhealthrpt.selectMonthlyKcalTime", param);
		return rsList;
	}
	
	// 영양미션 실천율 조회
	public Map<String, Object> selectNurtMissionSucPer(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectNurtMissionSucPer", param);
		return rsMap;
	}

	// 복약미션 실천율 조회
	public Map<String, Object> selectDrugMissionSucPer(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectDrugMissionSucPer", param);
		return rsMap;
	}

	// 의사 지시내용 차수별 조회
	public Map<String, Object> selectRqstDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectRqstDtls", param);
		return rsMap;
	}

	//월간리포트 저장
	public int updateMonthlyCnsl(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.st.mthlyhealthrpt.updateMonthlyCnsl", param);
		return rsInt;
	}
	
	//월간리포트 삭제
	public int deleteMonthlyCnsl(Map<String, Object> param) throws Exception{
		int rsInt = delete("mhc.web.st.mthlyhealthrpt.deleteMonthlyCnsl", param);
		return rsInt;
	}
	
	//종합평가 update
	public int updateTotEval(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.st.mthlyhealthrpt.updateTotEval", param);
		return rsInt;
	}
	
	//월간리포트 발송
	public int updateSubmit(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.st.mthlyhealthrpt.updateSubmit", param);
		return rsInt;
	}
	
	//종합평가 delete
	public int deleteTotEval(Map<String, Object> param) throws Exception{
		int rsInt = update("mhc.web.st.mthlyhealthrpt.deleteTotEval", param);
		return rsInt;
	}
	
	// 금연절주컨텐츠 수신여부 조회
		public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception{
			Map<String, Object> rsMap = selectOne("mhc.web.st.mthlyhealthrpt.selectCnslYnList", param);
			return rsMap;
		}
	
}
