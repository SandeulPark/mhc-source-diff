package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


@Repository("web.tg.PreTrgterApplyDAO")
public class PreTrgterApplyDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> preTrgterApplyList(Map<String, Object> param) { 
		return selectList("mhc.web.tg.pretrgterapply.preTrgterApplyList",param);
	}
	
	public int updateApprovalYn(Map<String, Object> param) {
		int rsInt = update("mhc.web.tg.pretrgterapply.updateApprovalYn", param);		
		return rsInt;
	}
	

	public Map<String, Object> preTrgterApplyRegit(Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String userId = String.valueOf(param.get("USER_ID"));
		//신규 예비대상자 번호
		String trgterNo =  selectOne("mhc.web.tg.pretrgterapply.selectGetTrgterNo",param);
		//재등록 여부
		String reRegYn = "N";
		
		//1) 기존 PRE_TRGTER_NO 조회
		String orgPreTrgterNo = selectOne("mhc.web.tg.pretrgterapply.selectGetOrgTrgterNo",param);
		// 기존 PRE_TRGTER_NO가 있을 경우 재등록
		if(orgPreTrgterNo != "") {
			param.put("PRE_TRGTER_NO", orgPreTrgterNo);
			param.put("ori_USER_ID", userId);
			reRegYn = "Y";
			insert("mhc.web.tg.pretrgterapply.insPreTrgterHist", param);
		}
		
		param.put("RE_REG_YN", reRegYn);
		param.put("USER_ID", userId);
		param.put("PRE_TRGTER_NO", trgterNo);
				
		//2) pre_trgter_info 테이블 신규 정보 insert		
		param.put("CNCT_CLF", "30");
		insert("mhc.web.tg.pretrgterapply.preTrgterApplyEnter", param);
		
		//3) 검진의뢰신청
		param.put("CNSL_NO", 1);
		insert("mhc.web.tg.pretrgterapply.insertSelfHealthChkRequest", param);
		
		
		return rsMap;
	}

	
}
