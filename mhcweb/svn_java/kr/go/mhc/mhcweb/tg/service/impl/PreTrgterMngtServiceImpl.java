package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.tg.service.PreTrgterMngtService;

@Service("web.tg.PreTrgterMngtService")
public class PreTrgterMngtServiceImpl implements PreTrgterMngtService{
	
	@Resource(name="web.tg.PreTrgterMngtServiceDAO")
	private PreTrgterMngtServiceDAO preTrgterMngtServiceDAO;

	@Override
	public Map<String, String> getSttusCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getSttusCnt(param);
	}

	@Override
	public List<Map<String, String>> getPreTrgtMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getPreTrgtMngtList(param);
	}

	@Override
	public Map<String, String> getPreTrgterInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getPreTrgterInfo(param);
	}

	@Override
	public Map<String, String> getChkHealthResult(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getChkHealthResult(param);
	}

	@Override
	public void updateChoiceRequest(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.updateChoiceRequest(param);
	}

	@Override
	public Map<String, String> insertSelfHealthChkRequest(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.insertSelfHealthChkRequest(param);
	}

	@Override
	public void updateDecisionEnter(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.updateDecisionEnter(param);
	}

	@Override
	public void updateDenyEnter(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.updateDenyEnter(param);
	}

	@Override
	public void preTrgterInfoCorrect(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.preTrgterInfoCorrect(param);
	}

	@Override
	public void preTrgterClfCorrect(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.preTrgterClfCorrect(param);
	}

	@Override
	public Map<String, Object> newPreTrgterRegit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.newPreTrgterRegit(param);
	}

	@Override
	public String getUserId(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		
		return preTrgterMngtServiceDAO.getUserId(param);
	}

	@Override
	public String getTrgterNo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getTrgterNo(param);
	}

	@Override
	public void newPreTrgterEnter(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		preTrgterMngtServiceDAO.newPreTrgterEnter(param);
	}

	@Override
	public int importExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.importExcelGridInsert(param);
	}

	@Override
	public List<Map<String, String>> requestComboBox(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.requestComboBox(param);
	}

	@Override
	public Map<String, String> checkOverlapId(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.checkOverlapId(param);
	}

	@Override
	public List<Map<String, Object>> excelExportDataList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> selectHealthExamCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.selectHealthExamCnt(param);
	}
	
	@Override
	public List<Map<String, String>> getPreTrgterDuplicationChkList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getPreTrgterDuplicationChkList(param);
	}

	@Override
	public int delPreTrgterInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.delPreTrgterInfo(param);
	}
	
	@Override
	public Map<String, Object> getDuplicationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getDuplicationCnt(param);
	}
	
	@Override
	public void insPreTrgterHist(Map<String, Object> param) throws Exception{
		preTrgterMngtServiceDAO.insPreTrgterHist(param);
	}
	
	
	@Override
	public List<Map<String, Object>> selectphisAllRegitPreTrgterList(Map<String, Object> param) throws Exception {
		return preTrgterMngtServiceDAO.selectphisAllRegitPreTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> getphisExcel(Map<String, Object> param)throws Exception {
		return preTrgterMngtServiceDAO.getphisExcel(param);
	}
	
	
	@Override
	public int phisImportExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.phisImportExcelGridInsert(param);
	}

	@Override
	public int updateNoexamProc(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.updateNoexamProc(param);
	}

	@Override
	public Map<String, String> getNoexamDtlsInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getNoexamDtlsInfo(param);
	}

	@Override
	public int updatefinNoexamProc(Map<String, Object> param) throws Exception {
		return preTrgterMngtServiceDAO.updatefinNoexamProc(param);
	}

	@Override
	public Map<String, String> getFinNoexamDtlsInfo(Map<String, Object> param) throws Exception {
		return preTrgterMngtServiceDAO.getFinNoexamDtlsInfo(param);
	}

	@Override
	public void updateTrgterGraduation(Map<String, Object> param) throws Exception {
		preTrgterMngtServiceDAO.updateTrgterGraduation(param);
	}

	@Override
	public Map<String, Object> getChangeUserNm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterMngtServiceDAO.getChangeUserNm(param);
	}

	@Override
	public void updateChgUserNmCnfm(Map<String, Object> param) throws Exception {
		preTrgterMngtServiceDAO.updateChgUserNmCnfm(param);
	}
}

