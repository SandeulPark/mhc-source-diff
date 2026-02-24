package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface PreTrgterMngtService {

	public Map<String, String> getSttusCnt(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getPreTrgtMngtList(Map<String, Object> param)throws Exception;

	public Map<String, String> getPreTrgterInfo(Map<String, Object> param)throws Exception;

	public Map<String, String> getChkHealthResult(Map<String, Object> param)throws Exception;

	public void updateChoiceRequest(Map<String, Object> param)throws Exception;

	public Map<String, String> insertSelfHealthChkRequest(Map<String, Object> param)throws Exception;

	public void updateDecisionEnter(Map<String, Object> param)throws Exception;

	public void updateDenyEnter(Map<String, Object> param)throws Exception;

	public void preTrgterInfoCorrect(Map<String, Object> param)throws Exception;

	public void preTrgterClfCorrect(Map<String, Object> param)throws Exception;

	public Map<String, Object> newPreTrgterRegit(Map<String, Object> param)throws Exception;

	public String getUserId(Map<String, Object> param)throws Exception;
	
	public String getTrgterNo(Map<String, Object> param)throws Exception;

	public void newPreTrgterEnter(Map<String, Object> param)throws Exception;

	public int importExcelGridInsert(List<Map<String, Object>> param)throws Exception;

	public List<Map<String, String>> requestComboBox(Map<String, Object> param)throws Exception;

	public Map<String, String> checkOverlapId(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> excelExportDataList(Map<String, Object> param)throws Exception;

	public Map<String, String> selectHealthExamCnt(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> getPreTrgterDuplicationChkList(Map<String, Object> param)throws Exception;
	
	public int delPreTrgterInfo(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getDuplicationCnt(Map<String, Object> param)throws Exception;
	
	public void insPreTrgterHist(Map<String, Object> param) throws Exception;

	public List<Map<String,Object>> selectphisAllRegitPreTrgterList(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getphisExcel(Map<String, Object> param) throws Exception;
	
	public int phisImportExcelGridInsert(List<Map<String, Object>> param)throws Exception;

	int updateNoexamProc(Map<String, Object> param)throws Exception;

	Map<String, String> getNoexamDtlsInfo(Map<String, Object> param)throws Exception;

	int updatefinNoexamProc(Map<String, Object> param) throws Exception;

	Map<String, String> getFinNoexamDtlsInfo(Map<String, Object> param) throws Exception;

	/* 최종설문 & 최종검진 미수검 졸업처리 */
	public void updateTrgterGraduation(Map<String, Object> param) throws Exception;

	public Map<String, Object> getChangeUserNm(Map<String, Object> param) throws Exception;

	public void updateChgUserNmCnfm(Map<String, Object> param) throws Exception;
}
