package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.MthlyHealthRptService;

import org.springframework.stereotype.Service;

@Service("web.st.MthlyHealthRptService")
public class MthlyHealthRptServiceImpl implements MthlyHealthRptService{
	
	@Resource(name="web.st.MthlyHealthRptServiceDAO")
	private MthlyHealthRptServiceDAO mthlyHealthRptServiceDAO;

	//월간리포트제공 리스트 조회
	@Override
	public List<Map<String,Object>> mthlyHealthRptList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.mthlyHealthRptList(param);
	}
	
	//월간리포트제공 차수 및 일자 리스트 조회
	@Override
	public List<Map<String, Object>> mthlyHealthRptDateList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.mthlyHealthRptDateList(param);
	}

	//신체활동 관리목표 리스트 조회
	@Override
	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.bodyGoalList(param);
	}

	//영양 관리목표 리스트 조회
	@Override
	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.nutriGoalList(param);
	}
	
	//상담 템플릿 조회
	@Override
	public List<Map<String, Object>> selectCnslTemplateNmList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectCnslTemplateNmList(param);
	}
	
	//월간리포트 상담 내용 조회
	@Override
	public Map<String, Object> selectMonthlyCnsl(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyCnsl(param);
	}
	
	//월간리포트 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectMonthlyCnslAttchFiles(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyCnslAttchFiles(param);
	}

	//요일별 평균 걸음수 차트
	@Override
	public List<Map<String, Object>> selectDayExceActList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectDayExceActList(param);
	}

	//목표걸음달성, 평균걸음수조회
	@Override
	public Map<String, Object> selectObeActSuc(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectObeActSuc(param);
	}
	
	//종합 체중변화 차트 데이터 조회
	@Override
	public List<Map<String, Object>> selectWeightChartList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectWeightChartList(param);
	}

	//종합 현재/목표 체중
	@Override
	public List<Map<String, Object>> selectWeightList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectWeightList(param);
	}

	//요일별, 끼니별 칼로리 조회
	@Override
	public List<Map<String, Object>> selectWeekMealCalList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectWeekMealCalList(param);
	}

	//식사기록일수, 평균섭취칼로리 조회
	@Override
	public Map<String, Object> selectDayMealCal(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectDayMealCal(param);
	}

	//종합 월별 체성분 차트 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyBodyCompChartList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyBodyCompChartList(param);
	}

	//종합 월별 체성분 각 비교값 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyWeightList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyWeightList(param);
	}

	//혈압 차트 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyBloodPressChartList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyBloodPressChartList(param);
	}

	//혈압 평균 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyBloodPressData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyBloodPressData(param);
	}

	//혈당 차트 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyBloodSugarChartList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyBloodSugarChartList(param);
	}

	//혈당 펴균 데이터 조회
	@Override
	public List<Map<String, Object>> selectMonthlyBloodSugarData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyBloodSugarData(param);
	}

	//칼로리, 운동시간 조회
	@Override
	public List<Map<String, Object>> selectMonthlyKcalTime(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectMonthlyKcalTime(param);
	}

	//영양미션 실천율 조회
	@Override
	public Map<String, Object> selectNurtMissionSucPer(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		return mthlyHealthRptServiceDAO.selectNurtMissionSucPer(param);
	}

	// 복약미션 실천율 조회
	@Override
	public Map<String, Object> selectDrugMissionSucPer(Map<String, Object> param) throws Exception {
		return mthlyHealthRptServiceDAO.selectDrugMissionSucPer(param);
	}

	// 의사 지시내용 차수별 조회
	@Override
	public Map<String, Object> selectRqstDtls(Map<String, Object> param) throws Exception {
		return mthlyHealthRptServiceDAO.selectRqstDtls(param);
	}

	//월간리포트 저장
	@Override
	public int updateMonthlyCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = 0;
		rsInt = mthlyHealthRptServiceDAO.updateMonthlyCnsl(param);
		if(rsInt!=0){
			rsInt = mthlyHealthRptServiceDAO.updateTotEval(param);
		}
		return rsInt;
	}

	//월간리포트 삭제
	@Override
	public int deleteMonthlyCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = 0;
		rsInt = mthlyHealthRptServiceDAO.deleteMonthlyCnsl(param);
		if(rsInt != 0){
			mthlyHealthRptServiceDAO.deleteTotEval(param);
		}
		return rsInt;
	}
	
	//월간리포트 발송
	@Override
	public int updateSubmit(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		int rsInt = 0;
		rsInt = mthlyHealthRptServiceDAO.updateSubmit(param);
		
		return rsInt;
	}
	
	//금연절주콘텐츠 수신여부 조회
		@Override
		public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception {
			// TODO Auto-generated method stub
			return mthlyHealthRptServiceDAO.selectCnslYnList(param);
		}
	
	
}
