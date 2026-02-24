package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.cm.service.NutriCodeMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("web.cm.NutriCodeMngtService")
public class NutriCodeMngtServiceImpl extends EgovAbstractServiceImpl implements NutriCodeMngtService{
	@Resource(name="web.cm.NutriCodeMngtServiceDAO")
	private NutriCodeMngtServiceDAO nutriCodeMngtServiceDAO;

	@Override
	public List<Map<String, String>> getNutriCodeList(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.getNutriCodeList(param);
	}

	@Override
	public List<Map<String, String>> getNutrientList(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.getNutrientList(param);
	}	

	@Override
	public List<Map<String, String>> getNutriCodeReqMngtList(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.getNutriCodeReqMngtList(param);
	}

	@Override
	public List<Map<String, String>> getNutrientDupList(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.getNutrientDupList(param);
	}

	@Override
	public int insertProcFoodReq(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.insertProcFoodReq(param);
	}

	@Override
	public int updateProcFoodReq(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.updateProcFoodReq(param);
	}

	@Override
	public int updateProcFoodReqApprovalYn(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.updateProcFoodReqApprovalYn(param);
	}		
	
	@Override
	public List<Map<String, String>> getNutriAttchFileList(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.getNutriAttchFileList(param);
	}

	@Override
	public int insertCookFoodReq(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.insertCookFoodReq(param);
	}

	@Override
	public int updateCookFoodReq(Map<String, Object> param) throws Exception {
		return nutriCodeMngtServiceDAO.updateCookFoodReq(param);
	}

	@Override
	public int updateCookFoodReqApprovalYn(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.updateCookFoodReqApprovalYn(param);
	}

	@Override
	public int importExcelGridCookReqInsert(List<Map<String, Object>> param)throws Exception {
		return nutriCodeMngtServiceDAO.importExcelGridCookReqInsert(param);
	}

	@Override
	public int importExcelGridCookCompInsert(List<Map<String, Object>> param)throws Exception {
		return nutriCodeMngtServiceDAO.importExcelGridCookCompInsert(param);
	}		
	
	@Override
	public Map<String, Object> getNewCookFoodCompChk(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.getNewCookFoodCompChk(param);
	}		
		
	@Override
	public List<Map<String, String>> getNutriCodeReqMngtExcelList(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.getNutriCodeReqMngtExcelList(param);
	}	
	
	@Override
	public Map<String, Object> getFoodValidChk(Map<String, Object> param)throws Exception {
		return nutriCodeMngtServiceDAO.getFoodValidChk(param);
	}	
	
	@Override
	public int importExcelGridProcInsert(List<Map<String, Object>> param)throws Exception {
		return nutriCodeMngtServiceDAO.importExcelGridProcInsert(param);
	}		
	
}
