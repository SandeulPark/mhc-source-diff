package kr.go.mhc.mhcapp.ms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.util.SimpleDateUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcapp.ms.service.GnrlMeasrMastrService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ms.gnrlMeasrMastrService")
public class GnrlMeasrMastrServiceImpl extends EgovAbstractServiceImpl implements GnrlMeasrMastrService{
	
	protected Logger LOG = LoggerFactory.getLogger(MeasrMastrServiceImpl.class);
	
	@Resource(name="ms.gnrlMeasrMastrDAO")
    private GnrlMeasrMastrDAO gnrlMeasrMastrDAO;

	@Override
	public Map<String,Object> getMeasrSeq() throws Exception {
		// TODO Auto-generated method stub
		return gnrlMeasrMastrDAO.getMeasrSeq();
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
				
				int dupleChk = gnrlMeasrMastrDAO.duplChkBloodPress(insMap);
					
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
					gnrlMeasrMastrDAO.insertMeasrMas(insMap);
					gnrlMeasrMastrDAO.insertBloodPress(insMap);
					rtInt++;
				}
			}
			rtnMap.put("insertCnt", rtInt);
			return rtnMap;
		} else {
			param.put("USER_ID", param.get("SESS_USER_ID"));
			int dupleChk = gnrlMeasrMastrDAO.duplChkBloodPress(param);
			if(dupleChk==0){
				param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
				Map<String,Object> measrJudge = selectMeasrJudge(param);
				
				if(measrJudge!=null&&measrJudge.size()>0)
					param.putAll(measrJudge);	

				param.putAll(getMeasrSeq()); 
				gnrlMeasrMastrDAO.insertMeasrMas(param);
				gnrlMeasrMastrDAO.insertBloodPress(param);
			}
			return param;
		}
	}
	
	@Override
	public Map<String,Object> updateBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		
		param.put("USER_ID", param.get("SESS_USER_ID"));
		rtInt = gnrlMeasrMastrDAO.updateMeasrMas(param);
		param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
		Map measrJudge = selectMeasrJudge(param);
		
		if(measrJudge!=null&&measrJudge.size()>0)
			param.putAll(measrJudge);	
		
		rtInt = gnrlMeasrMastrDAO.updateBloodPress(param);	
		
		return param;
	}
	
	@Override
	public Map<String,Object> deleteBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = gnrlMeasrMastrDAO.deleteBloodPress(param);	
		rtInt = gnrlMeasrMastrDAO.deleteMeasrMas(param);
		
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
				int dupleChk = gnrlMeasrMastrDAO.duplChkBloodSugar(insMap);
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
					gnrlMeasrMastrDAO.insertMeasrMas(insMap);
					gnrlMeasrMastrDAO.insertBloodSugar(insMap);
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
						
						int dupleChk = gnrlMeasrMastrDAO.duplChkBloodSugar(param);
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
							gnrlMeasrMastrDAO.insertMeasrMas(param);
							gnrlMeasrMastrDAO.insertBloodSugar(param);
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
		
		rtInt = gnrlMeasrMastrDAO.updateBloodSugar(param);	
		
		return param;
	}
	
	@Override
	public Map<String,Object> deleteBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub

		int rtInt = -1;		

		rtInt = gnrlMeasrMastrDAO.deleteBloodSugar(param);	
		rtInt = gnrlMeasrMastrDAO.deleteMeasrMas(param);
		
		return param;
	}
	
	@Override
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map rtMap = new HashMap<String, Object>();
		if(param.get("JUDGE_VAL")!=null&&!"".equals(param.get("JUDGE_VAL"))){ 
			rtMap = gnrlMeasrMastrDAO.selectMeasrJudge(param);
		}
		return rtMap;
	}

	//체성분 추가
	@Override
	public int insertBodyComp(Map<String, Object> param) throws Exception {
		int nCnt = 0;
		param.put("USER_ID", param.get("SESS_USER_ID"));
		param.putAll(getMeasrSeq());
		gnrlMeasrMastrDAO.insertMeasrMas(param);

		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");
			for (int i = 0; i < insList.size(); i++) {
				Map<String,Object> insMap = insList.get(i);
				insMap.putAll(param);
				gnrlMeasrMastrDAO.insertBodyComp(insMap);
				nCnt++;
			}
		}else{
			nCnt = 1;
			gnrlMeasrMastrDAO.insertBodyComp(param);
		}
		return nCnt;
	}



	@Override
	public Map<String,Object> insertBodyCompManu(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = 0;
		try{
			String MEASR_SN = param.get("MEASR_SN")==null?"":(String)param.get("MEASR_SN");
			//신장정보
			String height = param.get("SESS_HEIGHT")==null?"":param.get("SESS_HEIGHT").toString();
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
					rtInt = gnrlMeasrMastrDAO.insertMeasrMas(param);
				}else{
					System.out.println("update");
					rtInt = gnrlMeasrMastrDAO.updateMeasrMas(param);
				}
				rtInt = gnrlMeasrMastrDAO.insertBodyCompManu(param);
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
		
		rtInt = gnrlMeasrMastrDAO.deleteMeasrMas(param);
		rtInt = gnrlMeasrMastrDAO.deleteBodycomp(param);
		param.put("rtInt", rtInt);
		return param;
	}
	
	/********************************** OpenApi 적용 관련 START ***************************************/
	@Override
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlMeasrMastrDAO.checkLastData(param);
	}
	
	@Override
	public int insertAct(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int nCnt = 0;
		
		if(param.get("insList") != null){
			List<Map<String,Object>> insList = (List<Map<String,Object>>)param.get("insList");
			param.remove("insList");

			Map<String,Object> insMap =null;
			for (int i = 0; i < insList.size(); i++) {
				insMap = insList.get(i);
				insMap.putAll(param);
				
				if ("".equals(StringUtil.nvl(insMap.get("MEASR_SN"),""))) {
					param.putAll(getMeasrSeq());
					insMap.put("MEASR_SN", param.get("MEASR_SN"));
					gnrlMeasrMastrDAO.insertMeasrMas(insMap);
				}
				
				gnrlMeasrMastrDAO.insertAct(insMap);
				
			}
			nCnt = insList.size();
		}else{
			nCnt = 1;
			param.putAll(getMeasrSeq());
			gnrlMeasrMastrDAO.insertMeasrMas(param);
			gnrlMeasrMastrDAO.insertAct(param);
		}
		
		gnrlMeasrMastrDAO.callProcActData(param);
		
		return nCnt;
	}
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception {
		return gnrlMeasrMastrDAO.deviceUserInfo(param);
	}
	
}
