package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


@Repository("web.tg.PhisPreTrgterMngtDAO")
public class PhisPreTrgterMngtDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> phisPreTrgterRegitList(Map<String, Object> param) { 
		return selectList("mhc.web.tg.phispretrgtermngt.phisPreTrgterRegitList",param);
	}

	public Map<String, Object> phisPreTrgterRegitExamInfo(Map<String, Object> param) {
		return selectOne("mhc.web.tg.phispretrgtermngt.phisPreTrgterRegitExamInfo", param);
	}

	public Map<String, Object> phisNewPreTrgterRegit(Map<String, Object> param) {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		//2026.01. 국가건강검진 리스트 중 예비대상자 신청(보편화면에서)을 한 인원만 등록 가능
		
		//String uId =  selectOne("mhc.web.tg.pretrgtermngt.selectGetUserId",param);
		String userId = String.valueOf(param.get("USER_ID"));
		// 신규 예비대상자 번호
		String trgterNo =  selectOne("mhc.web.tg.pretrgtermngt.selectGetTrgterNo",param);
		
		//재등록 여부
		String reRegYn = "N";
		
		//1) 기존 PRE_TRGTER_NO 조회
		String orgPreTrgterNo = selectOne("mhc.web.tg.pretrgterapply.selectGetOrgTrgterNo",param);
		// 기존 PRE_TRGTER_NO가 있을 경우 재등록
		if(orgPreTrgterNo != "") {
			param.put("PRE_TRGTER_NO", orgPreTrgterNo);
			param.put("ori_USER_ID", userId);
			reRegYn = "Y";
			insert("mhc.web.tg.pretrgtermngt.insPreTrgterHist", param);
		}
		
		param.put("RE_REG_YN", reRegYn);
		param.put("USER_ID", userId);
		param.put("PRE_TRGTER_NO", trgterNo);
				
		// 2) pre_trgter_info 테이블  신규 정보 insert (재등록자 포함)
		insert("mhc.web.tg.phispretrgtermngt.phisNewPreTrgterEnter", param);
		
		// 3) 검진의뢰신청
		param.put("CNSL_NO", 1);
		insert("mhc.web.tg.pretrgtermngt.insertSelfHealthChkRequest", param);
		
		// 4) 검진결과 등록 후 선정 의뢰
		update("mhc.web.tg.phispretrgtermngt.updatePhisNewPreTrgterExam", param);
		
		// 5) 연계테이블에 USER_ID, PRE_TRGTER_NO 업데이트
		update("mhc.web.tg.phispretrgtermngt.updatePhisPreTrgterInfo", param);
		
		return rsMap;
	}

	public Map<String, Object> phisHealthExamRsltPop(Map<String, Object> param) {	
		return selectOne("mhc.web.tg.phispretrgtermngt.selectUserInfoForPhis", param);
	}

	public Map<String, Object> selectUserPhisExamRslt(Map<String, Object> param) {
		return selectOne("mhc.web.tg.phispretrgtermngt.selectUserPhisExamRslt", param);
	}

	public Map<String, Object> regitExamPhisRslt(Map<String, Object> param) {	
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		// 1) 검진 결과 등록
		update("mhc.web.tg.phispretrgtermngt.updatePhisNewPreTrgterExam", param);
		// 2) 연계테이블에 USER_ID, PRE_TRGTER_NO 업데이트
		update("mhc.web.tg.phispretrgtermngt.updatePhisPreTrgterInfo", param);
				
		return rsMap;		
	}

}
