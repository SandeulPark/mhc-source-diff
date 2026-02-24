package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("web.sv.IntensiveBodyActObstyCnslDAO")
public class IntensiveBodyActObstyCnslDAO extends EgovAbstractMapper{

	public Map<String, String> getBasicUserInfo(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectBasicUserUserInfo",param);	
		return rsMap;
	}
	
	public List<Map<String, Object>> getIntensiveBodyGoalMngt(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectIntensiveBodyGoalMngt", param);
		return rsList;
	}
	
	public Map<String, String> getCnslHistory(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectCnslHistory", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> getCnslDe(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectCnslDe", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getCnslDePrdTotalInfo(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectCnslDePrdTotalInfo", param);
		return rsList;
	}
	
	public Map<String, String> getTotEval(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectgetTotEval",param);	
		return rsMap;
	}
	
	public List<Map<String, Object>> getSvWeekSttus(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectSvWeekSttus", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getActRecord(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectActRecord", param);
		return rsList;
	}
	
	public int getTotEvalupdate(Map<String, Object> param)throws Exception {
		
		int rsInt = update("mhc.web.sv.intensivebodyactobstycnsl.updateCnslSttus", param);
		param.put("CNSL_TRGTER_ID", param.get("USER_ID"));
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectIntensiveBodyGoalMngt", param);
		int cnt = rsList.size();
		if(cnt != 0){
			rsList.get(cnt-1).remove("CNSL_SN");
			param.putAll(rsList.get(cnt-1));				
		}			
		rsInt += update("mhc.web.sv.intensivebodyactobstycnsl.updateTotEval", param);	
		return rsInt;
	}
	
	public int updateBodyCnslSubmit(Map<String, Object> param) {
		int rsInt = update("mhc.web.sv.intensivebodyactobstycnsl.updateBodyCnslSubmit", param);	
		return rsInt;
	}
	
	public List<Map<String, Object>> getBodyActDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectBodyActDEList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getBodyActDYList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectBodyActDYList", param);
		return rsList;
	}	
	
	public int getTotEvalDelete (Map<String, Object> param) {
		int rsInt = delete("mhc.web.sv.intensivebodyactobstycnsl.deleteTotEval", param);
		rsInt += delete("mhc.web.sv.intensivebodyactobstycnsl.deleteTotEval", param);
		rsInt += update("mhc.web.sv.intensivebodyactobstycnsl.deleteCnslSttus", param);
		return rsInt;
	}
	
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectCnslTemplateNm", param);
		return rsList;
	}
	
	public Map<String, Object> getCnslTemplateConts(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectCnslTemplateConts", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> getCnslTemplateList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectCnslTemplateList", param);
		return rsList;
	}
	
	public int getCnslTemplateUpdate (Map<String, Object> param) {
		update("mhc.web.sv.intensivebodyactobstycnsl.updateTemplateClfReset", param);
		int rsInt = update("mhc.web.sv.intensivebodyactobstycnsl.updateCnslTemplate", param);
		return rsInt;
	}
	
	public int getCnslTemplateDel (Map<String, Object> param) {
		int rsInt = delete("mhc.web.sv.intensivebodyactobstycnsl.deleteCnslTemplate", param);
		return rsInt;
	}
	
	// 2017.02.27 이태석 추가(파일첨부)
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectAttchFileList", param);
		return rsList;
	}
	
	// 2017.03.30 이태석 추가(건강정보 조회)
	public List<Map<String, Object>> getHelthExam(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectHelthExam", param);
		return rsList;
	}
	
	// 2017.03.31 이태석 추가(목표 걸음수 달성율 조회)
	public Map<String, Object> getObjWalkAchvPer(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectObjWalkAchvPer", param);
		return rsMap;
	}
	
	// 2017.04.03 이태석 추가(주 평균 운동시간,횟수 조회)
	public Map<String, Object> getWeekExcsAvgTmCnt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectWeekExcsTmCnt", param);
		return rsMap;
	}
	
	// 2017.04.12 이태석 추가(해당 일 심박수 조회)
	public List<Map<String, Object>> getDayHeartRateDtaList(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectDayHeartRateDtaList", param);
		return rsList;
	}
	
	// 2017.05.18 추가(심박수 구간별 조회)
	public List<Map<String, Object>> getDayHeartRateSecList(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectDayHeartRateSecList", param);
		return rsList;
	}

	// 2017.05.18 추가(심박수 구간별 조회)
	public List<Map<String, Object>> getDayHeartRateSecList2(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectDayHeartRateSecList2", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getActRecordChart(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectActRecordChart", param);
		return rsList;
	}
	
	public Map<String, Object> getObjHrSucRate(Map<String, Object> param) throws Exception {
		return selectOne("mhc.web.sv.intensivebodyactobstycnsl.selectObjHrSucRate", param);
	}
	
	// 2017.06.26 추가(공통 동영상 목록 조회)
	public List<Map<String, Object>> getBodyActVdTemplateList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList =  selectList("mhc.web.sv.intensivebodyactobstycnsl.selectBodyActVdTemplateList", param);
		return rsList;
	}	
	
	public int updateBodyActAllSubmit(Map<String, Object> param) {
		String[] cnsl = param.get("ALL_CNSL_SN").toString().split("\\,");
		int rsInt = 0;
		for(int i=0; i<cnsl.length; i++){
			param.put("CNSL_SN", cnsl[i]);
			update("mhc.web.sv.intensivebodyactobstycnsl.updateBodyCnslSubmit", param);	
			rsInt ++; 
		}
		return rsInt;
	}
	
	// 인바디 체성분 정보 조회
	public List<Map<String, Object>> bodyCompInfoList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectBodyCompInfoList", param);
		return rsList;
	}

	/**
	 * 중강도 운동  종목 공통코드 가져오기
	 */
	public List<Map<String, Object>> getExcsClfCodeList(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectExcsClfCodeList", param);
		return rsList;
	}

	/**
	 * 중강도 운동 종목별 평균 심박수 가져오기
	 */
	public List<Map<String, Object>> getRunninSttusHeartRate(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectRunninSttusHeartRate", param);
		return rsList;
	}

	/**
	 * 측정된 중강도 운동 리스트 가져오기
	 */
	public List<Map<String, Object>> getRunningSttusList(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectRunningSttusList", param);
		return rsList;
	}

	/**
	 * 측정된 중강도 운동 심박 데이터 가져오기
	 */
	public List<Map<String, Object>> getRunningHrArr(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivebodyactobstycnsl.selectRunningHrArr", param);
		return rsList;
	}
}
