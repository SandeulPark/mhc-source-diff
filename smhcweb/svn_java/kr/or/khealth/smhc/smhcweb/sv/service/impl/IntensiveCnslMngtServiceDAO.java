package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("web.sv.IntensiveCnslMngtServiceDAO")
public class IntensiveCnslMngtServiceDAO  extends EgovAbstractMapper{

	public List<Map<String, Object>> getIntensiveCnslMngtList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList ;
		if(param.get("CNSL_ITEM_CLF").equals("30")){ //건강
			rsList = selectList("mhc.web.sv.intensivecnslmngt.selectIntensiveHealthCnslMngtList", param);
		}else{
			rsList = selectList("mhc.web.sv.intensivecnslmngt.selectIntensiveCnslMngtList", param);
		}
		return rsList;
		
	}

	public Map<String, String> getCountIntensiveCnsl(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.countIntensiveCnslTrgter",param);	
		return rsMap;
	}

	public Map<String, String> getBasicUserInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectBasicUserUserInfo",param);	
		return rsMap;
	}

	public List<Map<String, Object>> getGoalMngt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectGoalMngt", param);
		return rsList;
	}

	public Map<String, String> getManagerType(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectManagerType", param);
		return rsMap;
	}
//추가
	public Map<String, String> getCnslHistory(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectCnslHistory", param);
		return rsMap;
	}
	
	
	public List<Map<String, Object>> getMealRegDe(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealRegDe", param);
		return rsList;
	}

	public List<Map<String, Object>> getCnslDe(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCnslDe", param);
		return rsList;
	}

	public Map<String,Object> getMealDietInfo(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, String> mealInfoMap = selectOne("mhc.web.sv.intensivecnslmngt.selectMealInfo", param);	
		List<Map<String, String>> dietInfoList = selectList("mhc.web.sv.intensivecnslmngt.selectDietInfoList", param);
		if(mealInfoMap != null & dietInfoList != null){
			rsMap.put("mealInfo", mealInfoMap);	
			rsMap.put("dietInfo", dietInfoList);
		}else if(mealInfoMap != null & dietInfoList == null){
			rsMap.put("mealInfo", mealInfoMap);
			rsMap.put("dietInfo", "0");		
		}else if(mealInfoMap == null & dietInfoList != null){
			rsMap.put("mealInfo", "0");
			rsMap.put("dietInfo", dietInfoList);		
		}else{
			rsMap.put("mealInfo", "0");
			rsMap.put("dietInfo", "0");
		}
		return rsMap;
	}
	
	public Map<String, String> getIntakeSttus(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectIntakeSttus", param);

		if(rsMap == null){
			param.put("CNSL_PRD_BGN_DE", param.get("cnslPrdBgnDe"));
			List<Map<String, String>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectGoalMngt", param);
			int cnt = rsList.size();
			if(cnt != 0){
				rsMap = rsList.get(0);						
			}	
		}
		return rsMap;
	}
	
	public List<Map<String, String>> getEvalActive(Map<String, Object> param) {
		// TODO Auto-generated method
		List<Map<String, String>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectActiveControl", param);
		return rsList;
	}
	
	public int updateIntensiveCnslEval(Map<String, Object> param) {
		// TODO Auto-generated method
		int rsInt = 0;
		int dietInfoLength = Integer.parseInt(param.get("dietInfoLength").toString());
		for(int i=0; i < dietInfoLength; i++){
			Map<String, Object> intakeCnt = new HashMap<String, Object>();
			intakeCnt.put("GR_INTAKE_AM", param.get("GR_INTAKE_AM_"+i));
			intakeCnt.put("MT_INTAKE_AM", param.get("MT_INTAKE_AM_"+i));
			intakeCnt.put("VG_INTAKE_AM", param.get("VG_INTAKE_AM_"+i));
			intakeCnt.put("FR_INTAKE_AM", param.get("FR_INTAKE_AM_"+i));
			intakeCnt.put("MK_INTAKE_AM", param.get("MK_INTAKE_AM_"+i));
			intakeCnt.put("USER_ID", param.get("USER_ID"));
			intakeCnt.put("MEAL_REG_DE", param.get("MEAL_REG_DE_"+i));
			intakeCnt.put("MEAL_REG_SN", param.get("MEAL_REG_SN_"+i));
			intakeCnt.put("MEAL_DIET_SN", param.get("MEAL_DIET_SN_"+i));
			rsInt += update("mhc.web.sv.intensivecnslmngt.updateIntakeSttus", intakeCnt);			
		}
		rsInt += update("mhc.web.sv.intensivecnslmngt.updateTotEval", param);
		param.put("CNSL_TRGTER_ID", param.get("USER_ID"));
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectGoalMngt", param);
		int cnt = rsList.size();
		if(cnt != 0){
			rsList.get(0).remove("CNSL_SN");
			param.putAll(rsList.get(0));				
		}			
		rsInt += update("mhc.web.sv.intensivecnslmngt.updateEvalActive", param);		
		return rsInt;
	}
	
	public int updateSubmit(Map<String, Object> param) {
		// TODO Auto-generated method
		int rsInt = update("mhc.web.sv.intensivecnslmngt.updateSubmit", param);
		return rsInt;
	}
	
	public int deleteIntensiveCnslEval(Map<String, Object> param) {
		// TODO Auto-generated method
		int rsInt = delete("mhc.web.sv.intensivecnslmngt.deleteEvalActive", param);
		rsInt += update("mhc.web.sv.intensivecnslmngt.deleteTotEval", param);
		return rsInt;
	}

	public Map<String, String> selectHealthCnslDtls(Map<String, Object> param) {
		Map<String, String> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectHealthCnslDtls", param);	
		return rsMap;
	}

	public List<Map<String, Object>> selectHealthCnslAttchFiles(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectHealthCnslAttchFiles", param);
		return rsList;
	}

	public List<Map<String, Object>> selectHealthCnslBottomList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectHealthCnslBottomList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getCnslTemplateNm(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCnslTemplateNm", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getPractMissionRslt(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectPractMissionRslt", param);
		return rsList;
	}	
	
	// 2017.03.03 이태석 추가(파일첨부)
	public List<Map<String, Object>> getCnslAttchList(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectAttchFileList", param);
		return rsList;
	}
	
	//식시일기 관련 정보 추가
	public List<Map<String, Object>> getMealDiaryList(Map<String, Object> param)throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealDiaryList", param);
		return rsList;		
	}

	public List<Map<String, Object>> getMealDiaryInputInfo(Map<String, Object> param)throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealDiaryInputInfo", param);
		return rsList;			
	}	
	
	public Map<String,Object> getMealAssayRslt(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectMealAssayRslt", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> getCRFPerRslt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCRFPerRslt", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getMealDivCal(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealDivCal", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getMealNutriRslt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealNutriRslt", param);
		return rsList;		
	}		
	
	public Map<String,Object> getMealEtcRslt(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sv.intensivecnslmngt.selectMealEtcRslt", param);
		return rsMap;		
	}		
	
	//집중상담 화면 개선(2017.05.25)
	public List<Map<String, Object>> getPrdTotalinfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectPrdTotalinfo", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getSvWeekTotalnfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectSvWeekTotalnfo", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getCalTotalInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCalTotalInfo", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getCRFTotalnfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCRFTotalnfo", param);
		return rsList;		
	}		
	
	public  List<Map<String, Object>> getMealAvgInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealAvgInfo", param);
		return rsList;		
	}		
	


	
	public List<Map<String, Object>> getMealAssayTotalInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealAssayTotalInfo", param);
		return rsList;		
	}	

	public List<Map<String, Object>> getMealEtcTotalInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealEtcTotalInfo", param);
		return rsList;		
	}		
	
	public  List<Map<String, Object>> getMealNutriTotalInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectMealNutriTotalInfo", param);
		return rsList;		
	}		
	
	public List<Map<String,Object>> getCnslContHist(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectCnslContHist", param);
		return rsList;
	}
	
	
	public int updateNutriAllSubmit(Map<String, Object> param) {
		String[] cnsl = param.get("ALL_CNSL_SN").toString().split("\\,");
		int rsInt = 0;
		for(int i=0; i<cnsl.length; i++){
			param.put("CNSL_SN", cnsl[i]);
			update("mhc.web.sv.intensivecnslmngt.updateSubmit", param);
			rsInt ++; 
		}
		return rsInt;
	}
	//20191209 양현우 추가
	public List<Map<String, Object>> selectIntensiveCnslMngtListPop(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.selectintensiveCnslReqPop", param);
		return rsList;
	}
	
	public int updateIntensiveCnslMngtListPop(Map<String, Object> param) {
		int rsInt = update("mhc.web.sv.intensivecnslmngt.updateIntensiveCnslMngtListPop", param);
		return rsInt;
	}
	//20200311 양현우 추가
	public List<Map<String, Object>> getAlgoPop(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.getAlgoPop", param);
		return rsList;
	}
	//20200311 양현우 추가
	public List<Map<String, Object>> getAutoAlgoPop(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.getAutoAlgoPop", param);
		return rsList;
	}
	//20200311 양현우 추가
	public List<Map<String, Object>> getAlgoPopSetting(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhc.web.sv.intensivecnslmngt.getAlgoPopSetting", param);
		return rsList;
	}
}
