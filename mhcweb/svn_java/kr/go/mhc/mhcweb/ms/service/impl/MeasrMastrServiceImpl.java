package kr.go.mhc.mhcweb.ms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.ms.service.MeasrMastrService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ms.measrMastrService")
public class MeasrMastrServiceImpl extends EgovAbstractServiceImpl implements MeasrMastrService{
	
	protected Logger LOG = LoggerFactory.getLogger(MeasrMastrServiceImpl.class);
	
	@Resource(name="ms.measrMastrDAO")
    private MeasrMastrDAO measrMastrDAO;

	@Override
	public Map<String,Object> getMeasrSeq() throws Exception {
		// TODO Auto-generated method stub
		return measrMastrDAO.getMeasrSeq();
	}

	@Override
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.putAll(getMeasrSeq()); 
		int rtInt = -1;		
		
		int dupleChk = measrMastrDAO.duplChkBloodPress(param);
		if(dupleChk==0){
			rtInt = measrMastrDAO.insertMeasrMas(param);
			param.put("JUDGE_VAL", param.get("BLOOD_PRESS_MAX"));
			Map measrJudge = selectMeasrJudge(param);
			
			if(measrJudge!=null&&measrJudge.size()>0)
				param.putAll(measrJudge);	
			
			rtInt = measrMastrDAO.insertBloodPress(param);	
		}
		
		return param;
	}
	
	@Override
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		int rtInt = -1;
		
		if(param.get("arr_MEASR_DT")!=null){
			String[] arr_measrDt = (String[]) param.get("arr_MEASR_DT");
			String[] arr_bloodSugar = (String[]) param.get("arr_BLOOD_SUGAR");
			if(arr_measrDt.length == arr_bloodSugar.length){
				for(int i=0; i<arr_measrDt.length; i++){					
					
					param.put("MEASR_DT", arr_measrDt[i]);
					param.put("BLOOD_SUGAR", arr_bloodSugar[i]);
					param.put("JUDGE_VAL", param.get("BLOOD_SUGAR"));
					Map measrJudge = selectMeasrJudge(param);
					if(measrJudge!=null&&measrJudge.size()>0)
						param.putAll(measrJudge);		
					
					int dupleChk = measrMastrDAO.duplChkBloodSugar(param);
					if(dupleChk==0){
						
						param.putAll(getMeasrSeq());
						rtInt = measrMastrDAO.insertMeasrMas(param);
						rtInt = measrMastrDAO.insertBloodSugar(param);
					}
				}
			}
		}	
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
		
		return measrMastrDAO.insertActDta(param);
	}
	

	@Override
	public int insertHRDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		return measrMastrDAO.insertHRDta(param);
	}
	

	@Override
	public int insertBodyCompDta(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub	
		
		
		return measrMastrDAO.insertBodyCompDta(param);
	}
	
	@Override
	public Map<String,Object> insertBodyComp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		param.putAll(getMeasrSeq());
		
		int rtInt = -1;
		rtInt = measrMastrDAO.insertMeasrMas(param);
		rtInt = measrMastrDAO.insertBodyCompDta(param);
		rtInt = measrMastrDAO.insertBodyComp(param);
		
		return param;
	}
	
	@Override
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rtInt = -1;
		rtInt = measrMastrDAO.updatePairDeviceInfo(param);
		rtInt = measrMastrDAO.mergeEquipInfo(param);
		
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


}
