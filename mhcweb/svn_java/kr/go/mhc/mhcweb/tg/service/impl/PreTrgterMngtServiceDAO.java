package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.tg.PreTrgterMngtServiceDAO")
public class PreTrgterMngtServiceDAO extends DMultiEgovAbstractMapper{

	public Map<String, String> getSttusCnt(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectSttusCnt",param);	
		return rsMap;
	}

	public List<Map<String, String>> getPreTrgtMngtList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.tg.pretrgtermngt.selectPreTrgtMngtList",param);
		return rsList;
	}

	public Map<String, String> getPreTrgterInfo(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectPreTrgterInfo",param);	
		return rsMap;
	}

	public Map<String, String> getChkHealthResult(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectChkHealthResult",param);
		return rsMap;
	}

	public void updateChoiceRequest(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.tg.pretrgtermngt.updateChoiceRequest", param);
	}

	public Map<String, String> insertSelfHealthChkRequest(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		insert("mhc.web.tg.pretrgtermngt.insertSelfHealthChkRequest", param);
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectHealthExamCnt",param);	
		return rsMap;
	}

	public void updateDecisionEnter(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.tg.pretrgtermngt.updateDecisionEnter", param);
	}

	public void updateDenyEnter(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.tg.pretrgtermngt.updateDenyEnter", param);
	}

	public void preTrgterInfoCorrect(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = selectOne("common.cmmn.selectPwChangeChk", param);
		param.put("PW_CNT", rsMap.get("PW_CNT"));
		update("mhc.web.tg.pretrgtermngt.preTrgterInfoCorrect", param);
	}

	public void preTrgterClfCorrect(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.tg.pretrgtermngt.preTrgterClfCorrect", param);
		int rsInt = selectOne("mhc.web.tg.pretrgtermngt.selectTrgterChackCnt", param);
		if(rsInt == 1){
			update("mhc.web.tg.pretrgtermngt.updateTrgterInfo", param);
			int chckInt = selectOne("mhc.web.tg.pretrgtermngt.selectTrgterDigiSignChackCnt", param);
			if(!param.get("ATTCH_FILE_SN").toString().equals("") || chckInt >= 1){
				update("mhc.web.tg.pretrgtermngt.updateTrgterDigiSign", param);
			}
		}
	}

	public Map<String, Object> newPreTrgterRegit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String uId =  selectOne("mhc.web.tg.pretrgtermngt.selectGetUserId",param);	
		String trgterNo =  selectOne("mhc.web.tg.pretrgtermngt.selectGetTrgterNo",param);	
		//재등록 여부
		String reRegYn = param.get("reRegitChkYn") == null ? "" :(String) param.get("reRegitChkYn");
		
		// 1) history insert 후 tn_cm_pre_trgter_info delete
		if("Y".equals(reRegYn)){
			uId = (String) param.get("ori_USER_ID");
			param.put("PRE_TRGTER_NO", param.get("ori_PRE_TRGTER_NO"));
			param.put("RE_REG_YN", reRegYn);
			insert("mhc.web.tg.pretrgtermngt.insPreTrgterHist", param);
		}
		
		// 2) 신규 user_id, pre_trgter_no ( 재등록자 인 경우 기존 user_id, 신규 pre_trgter_no )
		param.put("USER_ID", uId);
		param.put("PRE_TRGTER_NO", trgterNo);

		// 3) 신규인 경우 tn_cm_user_info 테이블에 정보 insert
		if(!"Y".equals(reRegYn)){
			insert("mhc.web.tg.pretrgtermngt.newPreTrgterRegit", param);
		}
		
		// 4) pre_trgter_info 테이블  신규 정보 insert (재등록자 포함)
		insert("mhc.web.tg.pretrgtermngt.newPreTrgterEnter", param);
		
		// 5) 검진의뢰신청
		param.put("CNSL_NO", 1);
		insert("mhc.web.tg.pretrgtermngt.insertSelfHealthChkRequest", param);
		
		int chckInt = selectOne("mhc.web.tg.pretrgtermngt.selectTrgterDigiSignChackCnt", param);
		if(!param.get("ATTCH_FILE_SN").toString().equals("") || chckInt >= 1){
			update("mhc.web.tg.pretrgtermngt.updateTrgterDigiSign", param);
		}
		
		rsMap.put("new_USER_ID", uId);
		rsMap.put("new_PRE_TRGTER_NO", trgterNo);
		
		return rsMap;
	}

	public String getUserId(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		String rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectGetUserId",param);	
		return rsMap;
	}

	public String getTrgterNo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectGetTrgterNo",param);	
		return rsMap;
	}

	public void newPreTrgterEnter(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("mhc.web.tg.pretrgtermngt.newPreTrgterEnter", param);
	}

	public int importExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		
		int dataCount = param.size();
		int InsertCount =0;
		for(int i=0; i < dataCount; i++){
			String chkpa = selectOne("mhc.web.tg.pretrgtermngt.selectExcelDuplicationCnt",param.get(i)); //2018.01.30 유준영 엑셀파일 일괄등록 유효성 체크 1이상이면 중복 데이터 있음
				if("0".equals(chkpa)){
					String userid = selectOne("mhc.web.tg.pretrgtermngt.selectGetUserId");
					param.get(i).put("USER_ID", userid);
					insert("mhc.web.tg.pretrgtermngt.newPreTrgterRegit", param.get(i));
					
					String preTrgterNo = selectOne("mhc.web.tg.pretrgtermngt.selectGetTrgterNo", param.get(i));
					param.get(i).put("PRE_TRGTER_NO", preTrgterNo);
					
					//검진의뢰 신청 - 모든 대상자 
					param.get(i).put("CNSL_NO", 1);
					param.get(i).put("PRE_TRGTER_STTUS", "10");
					insert("mhc.web.tg.pretrgtermngt.insertSelfHealthChkRequest", param.get(i));	
					insert("mhc.web.tg.pretrgtermngt.newPreTrgterEnter", param.get(i));
					
					// 신규 연계대상자 일괄 등록 저장   phisImportExcelGridInsert 로직변경 20241022 jeeeeey 
					insert("mhc.web.tg.pretrgtermngt.phisImportExcelGridUpdate",param.get(i));
					
					InsertCount ++;
				}
			
		}
		
		return InsertCount;  
	}
	//20200204 양현우 추가
	public int phisImportExcelGridInsert(List<Map<String, Object>> param) throws Exception {
		int dataCount = param.size();
		int updateCount=0;
		for(int i=0;i<dataCount;i++){
			insert("mhc.web.tg.pretrgtermngt.phisImportExcelGridUpdate",param.get(i));
			updateCount++;
		}
		return updateCount;
	}
	public List<Map<String, String>> requestComboBox(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.pretrgtermngt.selectRequestComboBox",param);
		return rsList;
	}

	public Map<String, String> checkOverlapId(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectCheckOverlapId",param);	
		return rsMap;
	}
	
	public Map<String, String> selectHealthExamCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectHealthExamCnt",param);	
		return rsMap;
	}
	
	public List<Map<String, String>> getPreTrgterDuplicationChkList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.tg.pretrgtermngt.selectPreTrgterDuplicationChkList",param);
		return rsList;
	}
	
	public int delPreTrgterInfo(Map<String, Object> param) throws Exception {
		int delChk;
		if("Y".equals(param.get("RE_REG_YN"))){
			delChk = insert("mhc.web.tg.pretrgtermngt.oldPreTrgterMngtRollBack", param);
		}else{
			delete("mhc.web.tg.pretrgtermngt.deletePreTrgterInfo", param);
			delChk = update("mhc.web.tg.pretrgtermngt.updateUserInfoYn", param);	
		}
		return delChk; 
		 
	}
	
	public Map<String, Object> getDuplicationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectPreTrgterDuplicationCnt",param);	
		return rsMap;
	}
	
	public void insPreTrgterHist(Map<String, Object> param) throws Exception{
		insert("mhc.web.tg.pretrgtermngt.insPreTrgterHist", param);
	}
	
	/**
	 * PHIS 연계 대상자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectphisAllRegitPreTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.pretrgtermngt.selectphisAllRegitPreTrgterList",param);
		return rsList;  
	}
	/**
	 * PHIS 연계 대상자 엑셀 다운로드
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getphisExcel(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.pretrgtermngt.selectgetphisExcel", param);
		return rsList;
	}

	public int updateNoexamProc(Map<String, Object> param) throws Exception {
//		String userIds[] = param.get("USER_IDS").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String svcMngtNos[] = param.get("SVC_MNGT_NOS").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		List userList = new ArrayList();
		for(int i=0; i < svcMngtNos.length; i++) {
			userList.add(svcMngtNos[i]);
		}

		param.put("SVC_MNGT_NOS", userList);

		return insert("mhc.web.tg.pretrgtermngt.updateNoexam",param);
	}

	public Map<String, String> getNoexamDtlsInfo(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectNoexamDtlsInfo",param);
		return rsMap;
	}

	/**
	 * 최종검진 미수검 처리 update 210831
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int updatefinNoexamProc(Map<String, Object> param) throws Exception {
//		String userIds[] = param.get("USER_IDS").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String svcMngtNos[] = param.get("SVC_MNGT_NOS").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		List userList = new ArrayList();
		for(int i=0; i < svcMngtNos.length; i++) {
			userList.add(svcMngtNos[i]);
		}

		param.put("SVC_MNGT_NOS", userList);

		return insert("mhc.web.tg.pretrgtermngt.updateFinNoexam",param);
	}

	/**
	 * 최종검진 미수검 상세정보 조회 210831
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getFinNoexamDtlsInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.pretrgtermngt.selectFinNoexamDtlsInfo",param);
		return rsMap;
	}

	public void updateTrgterGraduation(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.pretrgtermngt.updateTrgterGraduation", param);

	}

	public Map<String, Object> getChangeUserNm(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.tg.pretrgtermngt.getChangeUserNm",param);
		return rsMap;
	}

	public void updateChgUserNmCnfm(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhc.web.tg.pretrgtermngt.updateChgUserNmCnfm", param);
	}
}
