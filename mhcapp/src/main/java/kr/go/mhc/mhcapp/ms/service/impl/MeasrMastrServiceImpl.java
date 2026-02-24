package kr.go.mhc.mhcapp.ms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.util.SimpleDateUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcapp.ms.service.MeasrMastrService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ms.measrMastrService")
public class MeasrMastrServiceImpl extends EgovAbstractServiceImpl implements MeasrMastrService{
	
	protected Logger LOG = LoggerFactory.getLogger(MeasrMastrServiceImpl.class);
	
	@Resource(name="ms.measrMastrDAO")
    private MeasrMastrDAO measrMastrDAO;
	
	@Resource(name="ms.gnrlMeasrMastrDAO")
    private GnrlMeasrMastrDAO gnrlMeasrMastrDAO;

	@Override
	public Map<String,Object> getMeasrSeq() throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.getMeasrSeq();
	}
	
	@Override
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		if(param.get("insList") != null){
			int rtInt = 0;
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				int dupleChk = measrMastrDAO.duplChkBloodPress(insMap);
				if(dupleChk==0){
					insMap.put("JUDGE_VAL", insMap.get("BLOOD_PRESS_MAX"));
					Map<String,Object> measrJudge = selectMeasrJudge(insMap);
					
					if(measrJudge!=null&&measrJudge.size()>0)
						insMap.putAll(measrJudge);	

					if (Long.parseLong(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")))
							> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
						rtnMap.putAll(insMap);
					}

					insMap.putAll(getMeasrSeq());
					measrMastrDAO.insertMeasrMas(insMap);
					measrMastrDAO.insertBloodPress(insMap);
					rtInt++;
				}
			}
			rtnMap.put("insertCnt", rtInt);
			return rtnMap;
		} else {
			param.put("USER_ID", param.get("SESS_USER_ID"));
			int dupleChk = measrMastrDAO.duplChkBloodPress(param);
			if(dupleChk==0){
				param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
				Map<String,Object> measrJudge = selectMeasrJudge(param);
				
				if(measrJudge!=null&&measrJudge.size()>0)
					param.putAll(measrJudge);	

				param.putAll(getMeasrSeq()); 
				measrMastrDAO.insertMeasrMas(param);
				measrMastrDAO.insertBloodPress(param);
			}
			return param;
		}
	}
	
	@Override
	public Map<String,Object> updateBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		
		param.put("USER_ID", param.get("SESS_USER_ID"));
		rtInt = measrMastrDAO.updateMeasrMas(param);
		param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
		Map measrJudge = selectMeasrJudge(param);
		
		if(measrJudge!=null&&measrJudge.size()>0)
			param.putAll(measrJudge);	
		
		rtInt = measrMastrDAO.updateBloodPress(param);	
		
		return param;
	}
	
	@Override
	public Map<String,Object> deleteBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = measrMastrDAO.deleteBloodPress(param);	
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		
		return param;
	}
	
	@Override
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rtnMap = new HashMap<String,Object>();
		if(param.get("insList") != null){
			int rtInt = 0;
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				int dupleChk = measrMastrDAO.duplChkBloodSugar(insMap);
				if(dupleChk==0){
					insMap.put("JUDGE_VAL", insMap.get("BLOOD_SUGAR"));
					Map<String,Object> measrJudge = selectMeasrJudge(insMap);
					if(measrJudge!=null&&measrJudge.size()>0)
						insMap.putAll(measrJudge);	
					
					if (Long.parseLong(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")))
							> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
						rtnMap.putAll(insMap);
					}

					insMap.putAll(getMeasrSeq());
					measrMastrDAO.insertMeasrMas(insMap);
					measrMastrDAO.insertBloodSugar(insMap);
					rtInt++;
				}
			}
			rtnMap.put("insertCnt", rtInt);
			return rtnMap; 
		} else {
			if(param.get("arr_MEASR_DE")!=null){
				String[] arr_measrDe = (String[]) param.get("arr_MEASR_DE");
				String[] arr_measrTm = (String[]) param.get("arr_MEASR_TM");
				String[] arr_bloodSugar = (String[]) param.get("arr_BLOOD_SUGAR");
				String[] arr_meal = (String[]) param.get("arr_MEAL_CLF");
				
				param.put("USER_ID", param.get("SESS_USER_ID"));
				if(arr_measrDe.length == arr_bloodSugar.length){
					for(int i=0; i<arr_measrDe.length; i++){					
						
						param.put("MEASR_DE", arr_measrDe[i]);
						param.put("MEASR_TM", arr_measrTm[i]);
						param.put("BLOOD_SUGAR", arr_bloodSugar[i]);
						param.put("MEAL_CLF", arr_meal[i]);
						
						int dupleChk = measrMastrDAO.duplChkBloodSugar(param);
						if(dupleChk==0){
							param.put("JUDGE_VAL", param.get("BLOOD_SUGAR"));
							Map<String,Object> measrJudge = selectMeasrJudge(param);
							if(measrJudge!=null&&measrJudge.size()>0)
								param.putAll(measrJudge);		

							if (Long.parseLong(StringUtil.nvl(param.get("MEASR_DE")) + StringUtil.nvl(param.get("MEASR_TM")))
									> Long.parseLong(StringUtil.nvl(rtnMap.get("MEASR_DE"),"0") + StringUtil.nvl(rtnMap.get("MEASR_TM"),"0"))) {
								rtnMap.putAll(param);
							}

							param.putAll(getMeasrSeq());
							measrMastrDAO.insertMeasrMas(param);
							measrMastrDAO.insertBloodSugar(param);
						}

						System.out.println("blood sugar ==="+param);
					}
				}
			}
			return param; 
		}
	}
	
	@Override
	public Map<String,Object> updateBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		
		Map measrJudge = selectMeasrJudge(param);
		
		if(measrJudge!=null&&measrJudge.size()>0)
			param.putAll(measrJudge);	
		
		rtInt = measrMastrDAO.updateBloodSugar(param);	
		
		return param;
	}
	
	@Override
	public int updateBloodSugarMealClf(Map<String, Object> param) throws Exception {
		return measrMastrDAO.updateBloodSugarMealClf(param);
	}
	
	@Override
	public Map<String,Object> deleteBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = measrMastrDAO.deleteBloodSugar(param);	
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		
		return param;
	}
	
	@Override
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map rtMap = new HashMap<String, Object>();
		if(param.get("JUDGE_VAL")!=null&&!"".equals(param.get("JUDGE_VAL"))){ 
			rtMap = measrMastrDAO.selectMeasrJudge(param);
		}
		return rtMap;
	}
	

	@Override
	public int insertActDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertActDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertActDta(param);
		}
		return nCnt;
	}
	

	@Override
	public int insertHRArrDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = insMap.get("HEARTRATES").toString();
				insMap.put("HEARTRATES", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertHRArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertHRArrDta(param);
		}
		return nCnt;
	}
	
	@Override
	public int insertHRDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertHRDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertHRDta(param);
		}
		return nCnt;
	}
	
	//boram s	
	
	public int insertRunningStatusDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String stateList = String.valueOf(insMap.get("STATELIST"));
				insMap.put("STATELIST", stateList.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningStatusDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningStatusDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningHRArrDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEARTRATES"));
				insMap.put("HEARTRATES", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningHRArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRArrDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningHRDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningHRDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRDta(param);
		}
		return nCnt;
	}
	
	public int insertRunningCalorieArrDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String calories = String.valueOf(insMap.get("CALORIES"));
				insMap.put("CALORIES", calories.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorieArrDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningHRArrDta(param);
		}
		return nCnt;	  
	}
	
	public int insertRunningCalorieDta(Map<String, Object> param) throws Exception {
		
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorieDta(insMap);
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			measrMastrDAO.insertRunningCalorieDta(param);
		}
		return nCnt;	  	  
	}
	//boram e
	

	@Override
	public int insertBodyCompDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub	
		
		
		return measrMastrDAO.insertBodyCompDta(param);
	}
	
	@Override
	public int insertBodyComp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		param.put("USER_ID", param.get("SESS_USER_ID"));
		param.putAll(getMeasrSeq());
		measrMastrDAO.insertMeasrMas(param);
//		rtInt = measrMastrDAO.insertBodyCompDta(param);
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertBodyComp(insMap);
				nCnt++;
			}
		}else{
			nCnt = 1;
			measrMastrDAO.insertBodyComp(param);
		}
		return nCnt;
	}
	
	@Override
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = -1;
		
		if(measrMastrDAO.userTypeCheck(param).equals("GU")){
			rtInt = gnrlMeasrMastrDAO.updatePairDeviceInfo(param);
		}else{
			rtInt = measrMastrDAO.updatePairDeviceInfo(param);
		}
		
		if(!param.get("EQUIP_CD").equals("Gmate") && !param.get("EQUIP_CD").equals("")){
			String deviceNm = (String) param.get("EQUIP_CD");
			System.out.println("페어링 data 확인 param.get(\"EQUIP_CD\") " + param.get("EQUIP_CD") +"deviceNm :" +deviceNm +" param.get(\"EQUIP_CD\")" +param.get("EQUIP_CD"));									
			if(deviceNm.contains("Mambo")) {
				param.put("EQUIP_CO_CD","Mambo");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("203B0")){
				param.put("EQUIP_CO_CD","203B0");
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("AND")){
				param.put("EQUIP_CO_CD","AND");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("AViTA")){
				param.put("EQUIP_CO_CD","AViTA");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("A&amp;D")){
				param.put("EQUIP_CO_CD","A&amp;D");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("Auto-Chek")){
				param.put("EQUIP_CO_CD","Auto-Chek");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("QN-Scale")){
				param.put("EQUIP_CO_CD","QN-Scale");
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("CareSens")){
				param.put("EQUIP_CO_CD","CareSens");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("icomon")){
				param.put("EQUIP_CO_CD","icomon");
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("GluNEO")){
				param.put("EQUIP_CO_CD","GluNEO");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("Chipsea-BLE")){
				param.put("EQUIP_CO_CD","Chipsea-BLE");
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("HEM")){
				param.put("EQUIP_CO_CD","HEM");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("BLEsmart")){
				param.put("EQUIP_CO_CD","BLEsmart");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("BP170B")){
				param.put("EQUIP_CO_CD","BP170B");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("ABM-513S")){
				param.put("EQUIP_CO_CD","ABM-513S");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("11585B"))
			{
				param.put("EQUIP_CO_CD","11585B");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("01597B")){
				param.put("EQUIP_CO_CD","01597B");
				param.put("EQUIP_CLF", "30");
			}else if(deviceNm.contains("01GM27")){
				param.put("EQUIP_CO_CD","01GM27");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("Dabit-Scale")){
				param.put("EQUIP_CO_CD","Dabit-Scale");
				param.put("EQUIP_CLF", "20");
			}else if(deviceNm.contains("FA20GA00015")){
				param.put("EQUIP_CO_CD","FA20GA00015");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("Gmate")){
				param.put("EQUIP_CO_CD","Gmate");
				param.put("EQUIP_CLF", "40");
			}else if(deviceNm.contains("PWB")){
				param.put("EQUIP_CO_CD","PWB");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("ziva plus")){
				param.put("EQUIP_CO_CD","ziva plus");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("LS405")){
				param.put("EQUIP_CO_CD","LS405");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("M5S")){
				param.put("EQUIP_CO_CD","M5S");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("SE_HR")){//여기부터
				param.put("EQUIP_CO_CD","SE_HR");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("InLabWATCH")){
				param.put("EQUIP_CO_CD","InLabWATCH");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("BMHC")){
				param.put("EQUIP_CO_CD","BMHC");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("HC92")){
				param.put("EQUIP_CO_CD","HC92");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("LSBand")){
				param.put("EQUIP_CO_CD","LSBand");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("LSWatch")){
				param.put("EQUIP_CO_CD","LSWatch");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("InBodyBand")){
				param.put("EQUIP_CO_CD","InBodyBand");
				param.put("EQUIP_CLF", "10");
			}else if(deviceNm.contains("SE_HL")){
				param.put("EQUIP_CO_CD","SE_HL");
				param.put("EQUIP_CLF", "10");
			}else {
				param.put("EQUIP_CO_CD", deviceNm);
			}
			
			/* measrMastrDAO.deletePairDeviceInfo(param); */
			rtInt = measrMastrDAO.mergeEquipInfo(param);
		}
		
		return rtInt;
	}
	
	@Override
	public String callProcActIns(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		return measrMastrDAO.callProcActIns(param);
	}
	
	public int mergeEquipInfo(Map<String, Object> param) throws Exception {
		
		return measrMastrDAO.mergeEquipInfo(param);
		
	}
	
	public Map<String,Object> searchSerialNo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.searchSerialNo(param);
	}
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> rsMap = new HashMap<String,Object>();
		if(measrMastrDAO.userTypeCheck(param).equals("GU")){
			rsMap = gnrlMeasrMastrDAO.deviceUserInfo(param);
		}else{
			rsMap = measrMastrDAO.deviceUserInfo(param);
		}
		return rsMap;
	}
	
	public List<Map<String,Object>> searchSerialNoList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.searchSerialNoList(param);
	}
	

	@Override
	public Map<String,Object> insertBodyCompManu(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = 0;
		try{
			String MEASR_SN = param.get("MEASR_SN")==null?"":(String)param.get("MEASR_SN");
			//신장정보
			Map<String, Object> hMap = measrMastrDAO.selectHeightInfo(param);
			String height = hMap.get("HEIGHT")==null?"":hMap.get("HEIGHT").toString();
			if(!"".equals(height)){
				param.put("USER_ID", param.get("SESS_USER_ID"));
				param.put("HEIGHT", height);
				param.put("MEASR_TRGT_CLF", "20");
				param.put("MEASR_RSLT", "S");
				param.put("AUTO_MANU_CLF", "M");
				System.out.println("service param>>"+param);
				//신규
				if("".equals(MEASR_SN)){
					param.putAll(getMeasrSeq());
					rtInt = measrMastrDAO.insertMeasrMas(param);
				}else{
					System.out.println("update");
					rtInt = measrMastrDAO.updateMeasrMas(param);
				}
				rtInt = measrMastrDAO.insertBodyCompManu(param);
				param.put("rtInt", rtInt);
			}
		}catch(Exception e){
			LOG.debug(e.toString());
		}
		return param;
	}
	
	@Override
	public Map<String, Object> deleteBodyCompManu(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		int rtInt = 0;
		
		rtInt = measrMastrDAO.deleteMeasrMas(param);
		rtInt = measrMastrDAO.deleteBodycomp(param);
		param.put("rtInt", rtInt);
		return param;
	}
	
	

	/********************************** OpenApi 적용 관련 START ***************************************/
	@Override
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.checkLastData(param);
	}

	@Override
	public int insertAct(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");

			Map<String, Object> subParam = new HashMap<String,Object>();
			subParam.put("USER_ID", insList.get(0).get("USER_ID"));
			Map<String, Object> putParam = measrMastrDAO.checkLastData(subParam);
			if(putParam != null){
				subParam.putAll(putParam);
			}
			String measrSn = StringUtil.nvl(subParam.get("MEASR_SN"));
			String startDt = StringUtil.nvl(subParam.get("START_DT"));
			if(!"".equals(startDt)){
				startDt = startDt.substring(0, 10);
			}
			int actCnt = Integer.parseInt(StringUtil.nvl(subParam.get("TOT_ACT_CNT"),"0"));
			
			Map<String,Object> insMap =null;
			for (int i = 0; i < insList.size(); i++) {
				insMap = insList.get(i);
				insMap.putAll(param);
				
//				System.out.println("insMap.get(MEASR_DE) : "+insMap.get("MEASR_DE")+", insMap.get(MEASR_TM) : "+insMap.get("MEASR_TM")+", last subParam.get(MEASR_TM) : "+subParam.get("MEASR_TM")+", startDt : "+startDt
//						+"\nlast tot_act_cnt : "+actCnt+", insMap.get(TOT_ACT_CNT) : "+insMap.get("TOT_ACT_CNT"));
//				if ( !"".equals(measrSn) && !"".equals(startDt) ) {
////					if (Integer.parseInt(startDt) > Integer.parseInt(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")).substring(0, 2))) {
////						continue;
////					}
//					System.out.println("no continue!!!!!!!!");
//					//저장되어있는 최종 측정일시와 수신받은 데이터 비교하여 시간까지 같으면
//					if ( startDt.equals(StringUtil.nvl(insMap.get("MEASR_DE")) + StringUtil.nvl(insMap.get("MEASR_TM")).substring(0, 2)) ) {
//						//저장되어 있는 최종 측정시간이 5959가 아니면
//						if ( !(StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("2959")) && !(StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("5959")) ) {
//							//저장되어 있는 총보수와 측정시간이 수신받은 총보수와 측정시간과 비교하여 수신받은 데이터가 높으면 기존 데이터 삭제 후 신규 추가
//							if( (actCnt < Integer.parseInt(StringUtil.nvl(insMap.get("TOT_ACT_CNT"),"0")) 
//									&& (Integer.parseInt(StringUtil.nvl(subParam.get("MEASR_TM"),"1")) < Integer.parseInt(StringUtil.nvl(insMap.get("MEASR_TM"),"0")))) ) {
//								measrMastrDAO.deleteAct(subParam);
//								insMap.put("MEASR_SN", subParam.get("MEASR_SN"));
//								System.out.println("delete act!!!!!!!!!!!!!");
//							}
//						}
//						if ( StringUtil.nvl(subParam.get("MEASR_TM")).endsWith("5959") && StringUtil.nvl(insMap.get("MEASR_TM")).endsWith("5959")) {
//							if (actCnt < Integer.parseInt(StringUtil.nvl(insMap.get("TOT_ACT_CNT"),"0"))) {
//								measrMastrDAO.deleteAct(subParam);
//								insMap.put("MEASR_SN", subParam.get("MEASR_SN"));
//								System.out.println("5959 delete act!!!!!!!!!!!!!");
//							}
//						}
//					}
//				}
				if ("".equals(StringUtil.nvl(insMap.get("MEASR_SN"),""))) {
					param.putAll(getMeasrSeq());
					insMap.put("MEASR_SN", param.get("MEASR_SN"));
					measrMastrDAO.insertMeasrMas(insMap);
				}
//				System.out.println("insert act!!!!!!!!!  insMap == "+insMap);
				// 채움건강앱 10만보이상 걷는 대상자 발생으로 인해
				// 검토결과 13만보이상일때 TN_MS_ACT_DTLS_ERR_HIS  테이블에 insert 될 수있도록 조건 변경 - 20240507 박양수 주임 요청
				if(Integer.parseInt(String.valueOf(insMap.get("TOT_ACT_CNT"))) > (int)130000) {
					insMap.put("TOT_ACT_CNT_STS", "ERROR");
					measrMastrDAO.insertAct(insMap);
				}else {
					insMap.put("TOT_ACT_CNT_STS", "");
					measrMastrDAO.insertAct(insMap);
				}
//				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")){
//					measrMastrDAO.callProcInbodyActData(insMap);
//				}
			}
			if(insMap!=null){
				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")||(insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH")&&!insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH2020"))){
					//measrMastrDAO.callProcInbodyActData(insMap);
					measrMastrDAO.updateInbodyTotalCnt(insMap);
				}
//				else if(insMap.get("MEASR_MODEL_NM").toString().contains("HC92")){
//					measrMastrDAO.updateMeasrTotalCnt(insMap);
//				}
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			measrMastrDAO.insertAct(param);
		}
		
//		measrMastrDAO.insertActNew(param);
		measrMastrDAO.callProcActDataNew(param);
		
		return nCnt;
	}
	
	@Override
	public int insertHeartRate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String measrTm = (String) insMap.get("MEASR_TM");
				String chkStr = measrTm.substring(3,6);
				
				if(!"459".equals(chkStr) && !"959".equals(chkStr)){
					int min = Integer.parseInt(measrTm.substring(3,4));
					int sec = Integer.parseInt(measrTm.substring(4,6));
					
					if(min < 5){
						min = 4;
					}else{
						min = 9;
					}					
					sec = 59;
					
					measrTm = measrTm.substring(0,3) + Integer.toString(min) + Integer.toString(sec);
					insMap.put("MEASR_TM", measrTm);
				}
				
				insMap.putAll(param);
				measrMastrDAO.insertHeartRate(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningStatus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			Map<String,Object> insMap = null;
			for (int i = 0; i < insList.size(); i++) {
				insMap = insList.get(i);
				insMap.putAll(param);
				
//				int excsCnt = Integer.parseInt(StringUtil.nvl(insMap.get("EXCS_CNT"),"0"));
//				1. 잘못된 운동보수 데이터(100만 이상) 인지 판단 
//				if (excsCnt > 1000000) {
////					1-1. 잘못된 운동보수 데이터인 경우 TN_MS_RUNNING_STATUS_DISORD 테이블에 INSERT       
//					measrMastrDAO.insertRunningStatusDisOrd(insMap);
//				}
				
//				2. 이틀에 걸친 데이터 인지 판단
//				2-1. 이틀에 걸친 데이터 인 경우 하루씩 TN_MS_RUNNING_STATUS 테이블 INSERT
//				if (!StringUtil.nvl(insMap.get("EXCS_START_DE")).equals(StringUtil.nvl(insMap.get("EXCS_END_DE")))) {
////					2-1-1. 종료일자 0시부터 종료시간까지 활동량 합계 계산
//					Map<String,Object> endCntMap = new HashMap<String,Object>();
////					endCntMap.put("SESS_USER_ID", param.get("USER_ID"));
////					endCntMap.put("EXCS_END_DE", param.get("EXCS_END_DE"));
////					endCntMap.put("EXCS_END_TM", param.get("EXCS_END_TM"));
////					endCntMap = measrMastrDAO.checkActCnt(endCntMap);
//					long restTm = Integer.parseInt(StringUtil.nvl(insMap.get("REST_TM"),"0"));
//					int endCnt = 0;//Integer.parseInt(StringUtil.nvl(endCntMap.get("ACT_CNT"),"0"));
////					if (endCnt > excsCnt) {
////						endCnt = excsCnt;
////					}
//					
//					String endBgnDt = StringUtil.nvl(insMap.get("EXCS_END_DE")) + "000000";
//					String endEndDt = StringUtil.nvl(insMap.get("EXCS_END_DE")) + StringUtil.nvl(insMap.get("EXCS_END_TM"));
//					long endActTm = SimpleDateUtil.getFromToDiffForSeconed(endBgnDt, endEndDt);
//					
////					2-1-1-1. 167만보의 경우 평균 걸음수로 활동량 계산
////					if (excsCnt > 1000000) {
//						int actCnt_167 = (int)((endActTm / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
////						if (endCnt > actCnt_167) {
//							endCnt = actCnt_167;
////						}
////					}
//					
//					if(endActTm < restTm){
//						restTm = endActTm;
//					}		
//							
////					2-1-2. 당일 데이터 TN_MS_RUNNING_STATUS 테이블에 INSERT
//					endCntMap = new HashMap<String,Object>();
//					endCntMap.putAll(insMap);
//					endCntMap.put("EXCS_START_DE", insMap.get("EXCS_END_DE"));
//					endCntMap.put("EXCS_START_TM", "000000");
//					endCntMap.put("EXCS_CNT", endCnt);
//					endCntMap.put("EXCS_TM", endActTm);
//					endCntMap.put("REST_TM", restTm);
//					measrMastrDAO.insertRunningStatus(endCntMap);
//					
//					
////					2-1-3. 시작시간부터 시작일자 23시 59분 59초까지 활동량 합계 계산
//					Map<String,Object> startCntMap = new HashMap<String,Object>();
////					startCntMap.put("SESS_USER_ID", param.get("USER_ID"));
////					startCntMap.put("EXCS_START_DE", param.get("EXCS_START_DE"));
////					startCntMap.put("EXCS_START_TM", param.get("EXCS_START_TM"));
////					startCntMap = measrMastrDAO.checkActCnt(startCntMap);
//
//					int startCnt = 0;//Integer.parseInt(StringUtil.nvl(startCntMap.get("ACT_CNT"),"0"));
////					if (startCnt > excsCnt) {
////						startCnt = excsCnt;
////					}
//					
//					String startBgnDt = StringUtil.nvl(insMap.get("EXCS_START_DE")) + StringUtil.nvl(insMap.get("EXCS_START_TM"));
//					String startEndDt = StringUtil.nvl(insMap.get("EXCS_START_DE")) + "235959";
//					long startActTm = SimpleDateUtil.getFromToDiffForSeconed(startBgnDt, startEndDt);
//					
////					2-1-3-1. 167만보의 경우 평균 걸음수로 활동량 계산
//					if (excsCnt > 1000000) {
//						actCnt_167 = (int)((endActTm / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
////						if (startCnt > actCnt_167) {
//							startCnt = actCnt_167;
////						}
//					}
//					else {
//						startCnt = excsCnt - endCnt;
//					}
//					
//					if(startActTm < restTm){
//						restTm = startActTm;
//					}
//					
////					2-1-4. 전일 데이터 TN_MS_RUNNING_STATUS 테이블에 INSERT
//					startCntMap = new HashMap<String,Object>();
//					startCntMap.putAll(insMap);
//					startCntMap.put("EXCS_END_DE", insMap.get("EXCS_START_DE"));
//					startCntMap.put("EXCS_END_TM", "235959");
//					startCntMap.put("EXCS_CNT", startCnt);
//					startCntMap.put("EXCS_TM", startActTm);
//					startCntMap.put("REST_TM", restTm);
//					measrMastrDAO.insertRunningStatus(startCntMap);
//				}
////				2-2. 하루짜리 데이터 인 경우 TN_MS_RUNNING_STATUS 테이블 INSERT
//				else {
//					2-2-1. 167만보의 경우 평균 걸음수로 활동량 계산
//					if (excsCnt > 1000000) {
//						int actCnt_167 = (int)((Integer.parseInt(StringUtil.nvl(insMap.get("EXCS_TM"),"0")) / 60.0) * Integer.parseInt(StringUtil.nvl(insMap.get("AVG_PITCH"),"0")));
////						시간대 활동량 보다 작은 경우만 적용 
//						insMap.put("EXCS_CNT", actCnt_167);
//					}
					
					measrMastrDAO.insertRunningStatus(insMap);
//				}
				
				
			}//end-for
			
			if(insMap!=null){
				if(insMap.get("MEASR_MODEL_NM").toString().equals("InBodyBand2")||(insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH")&&!insMap.get("MEASR_MODEL_NM").toString().contains("InLabWATCH2020"))){
					Map actMaxSn = measrMastrDAO.selectActMaxSn(insMap);
					actMaxSn.put("SESS_USER_ID", insMap.get("SESS_USER_ID"));
					measrMastrDAO.updateInbodyTotalCnt(actMaxSn);
					measrMastrDAO.callProcActDataNew(param);
				}
//				else if(insMap.get("MEASR_MODEL_NM").toString().contains("HC92")){
//					Map actMaxSn = measrMastrDAO.selectActMaxSn(insMap);
//					actMaxSn.put("SESS_USER_ID", insMap.get("SESS_USER_ID"));
//					measrMastrDAO.updateMeasrTotalCnt(actMaxSn);
//					measrMastrDAO.callProcActDataNew(param);
//				}
			}
			
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningCalorie(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningCalorie(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertRunningHR(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				measrMastrDAO.insertRunningHR(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	@Override
	public int insertHeartRateArr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEART_RATE_LIST"));
				insMap.put("HEART_RATE_LIST", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertHeartRateArr(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	@Override
	public int insertRunningHrArr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			
			param.put("USER_ID", insList.get(0).get("USER_ID"));
			param.putAll(getMeasrSeq());
			measrMastrDAO.insertMeasrMas(param);
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				String heartRate = String.valueOf(insMap.get("HEART_RATE_LIST"));
				insMap.put("HEART_RATE_LIST", heartRate.replaceAll("\\|", "\\,"));
				insMap.putAll(param);
				measrMastrDAO.insertRunningHrArr(insMap);
			}
			nCnt = insList.size();
		}
		return nCnt;
	}
	
	/********************************** OpenApi 적용 관련 END ***************************************/
	
	public List<Map<String,Object>> selectDeviceSerial(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectDeviceSerial(param);
	}
	
	public List<Map<String,Object>> selectEquipItem(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectEquipItem(param);
	}
	
	public List<Map<String,Object>> selectUserOta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.selectUserOta(param);
	}
	
	public int insertUserOta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.insertUserOta(param);
	}

}
