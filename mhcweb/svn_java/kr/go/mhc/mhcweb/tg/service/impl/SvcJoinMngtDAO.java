package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SvcJoinMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여 관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.22		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.SvcJoinMngtDAO")
public class SvcJoinMngtDAO extends DMultiEgovAbstractMapper  {

	//서비스참여관리 예비대상자 건수 조회
	public Map<String, Object> getSvcJoinMngtCnt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.svcJoinMngtCnt", param);
		return rsMap;
	}
	
	//서비스참여관리 예비대상자 목록 조회
	public List<Map<String, Object>> getSvcJoinMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.svcJoinMngtList", param);
		return rsList;
	}
	
	//서비스참여관리 상세화면 조회 기본정보, 선정의뢰정보 중 신장,체중,BMI 조회
	public Map<String, Object> getSvcJoinMngtDtlsBasic(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.svcJoinMngtDtlsBasic", param);
		return rsMap;
	}
	
	//서비스참여관리 상세화면 조회 위험요인 조회
	public List<Map<String, Object>> getSvcJoinMngtDtlsRisk(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.svcJoinMngtDtlsRisk", param);
		return rsList;
	}
	
	//서비스참여관리 상세화면 조회 상담내역 조회
	public List<Map<String, Object>> getSvcJoinMngtDtlsCnsl(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.svcJoinMngtDtlsCnsl", param);
		return rsList;
	}
	
	//서비스참여관리 상세화면 조회 일자에 따른 상담내역 조회
	public Map<String, Object> getSvcJoinMngtDtlsCnslDate(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.svcJoinMngtDtlsCnsl", param);
		return rsMap;
	}
	
	//서비스참여관리 상세화면 조회 질환정보 조회
	public List<Map<String, Object>> getSvcJoinMngtDtlsDiss(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.svcJoinMngtDtlsDiss", param);
		return rsList;
	}
	
	
	//서비스참여 대상자 신규 판정 시
	public void newInsertSvcJoinMngt(Map<String, Object> param) throws Exception{
		
		//1. 서비스 참여 관리 서비스_관리_번호 조회
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.selSvcJoinMngtNo", param);
		param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		int rsInt = 0;
				
		Map<String, Object> rsPreTrgterNo = selectOne("mhc.web.tg.svcjoinmngt.preTrgterNoYearChk", param);
		param.put("OLD_PRE_TRGTER_NO", param.get("PRE_TRGTER_NO"));
		param.put("PRE_TRGTER_NO", rsPreTrgterNo.get("PRE_TRGTER_NO"));
		
		//2. pre_trgter_sttus 20->50 : 대상자선정, 30->60 : 진료 및 약물치료 체크, 40->70 : 기타 체크
		update("mhc.web.tg.svcjoinmngt.updateNewPreTrgterSttus", param);	//updateSvcJoinMngtTrgter
		
		//3. 맨위 체크인 경우 - 확정
		if("20".equals(param.get("TRGTER_STTUS"))){
			//3_1. 신규 상담순번 조회
			Map<String, Object> rsMap2 = selectOne("mhc.web.tg.svcjoinmngt.selSvcJoinMngtCnslNo", param);
			param.put("CNSL_SN", rsMap2.get("CNSL_SN"));
						
			//3_2. 초기 방문상담 정보 insert
			insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtCnslCnfm", param);
			
			//커뮤니티 기본그룹 들어가있는지 확인
			int dupCnt = selectOne("mhc.web.tg.svcjoinmngt.cheDupGrpNo", param);
						
			//3_3. 보건소 커뮤니티 기본 그룹 맴버 추가 			
			if(dupCnt == 0) {	
				insert("mhc.web.tg.svcjoinmngt.trgterCmntyGrpMbAdd", param);	
			}
		}
			
		//4. 서비스 참여 관리 테이블 INSERT , 대상자 정보 테이블 insert
		insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngt", param);
		
		//5. 의사상담 정보 insert
		insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtCnsl", param);
		
		//질환정보 insert
		param.put("CNSL_DE", param.get("CNSL_DE").toString());		
		int cnt = Integer.parseInt(param.get("cntData").toString());
		for(int i=1; i<=cnt/2; i++){
			if(i <= 9){
				param.put("DISS_CD", "0"+i);
				if(param.get("0"+i+"_1") != null){
					param.put("DIAG_YN", param.get("0"+i+"_1"));					
				} else {
					param.put("DIAG_YN", 'N');
				}
				if(param.get("0"+i+"_2") != null){
					param.put("DRUG_TAKE_YN", param.get("0"+i+"_2"));					
				} else {
					param.put("DRUG_TAKE_YN", 'N');
				}
			} else {
				param.put("DISS_CD", i);
				if(param.get(i+"_1") != null){
					param.put("DIAG_YN", param.get(i+"_1"));
				} else {
					param.put("DIAG_YN", 'N');
				}
				if(param.get(i+"_2") != null){
					param.put("DRUG_TAKE_YN", param.get(i+"_2"));
				} else {
					param.put("DRUG_TAKE_YN", 'N');
				}
			}
			//6. 질환정보 insert
			insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtDiss", param);
		}
		
		//7. 탈락
		if("30".equals(param.get("TRGTER_STTUS"))){
			if("Y".equals(param.get("RE_REG_YN"))){
				insert("mhc.web.tg.svcjoinmngt.oldSvcJoinMngtRollBack", param);
			}else{
				//7_1. 서비스참여 정보 테이블 기관정보 update
				update("mhc.web.tg.svcjoinmngt.updateDropJoinMngt", param);
				//7_2. 예비대상자 정보 테이블 기관정보 및 탈락정보 update
				update("mhc.web.tg.svcjoinmngt.updateDropPreTrgterInfo", param);
				//7_3. user정보 테이블 개인정보 삭제 update
				update("mhc.web.tg.svcjoinmngt.updateDropUserInfo", param);
			}
		}
		
	}	
	
	
	
	//서비스참여관리 update 저장
	public void updateSvcJoinMngt(Map<String, Object> param) throws Exception {
		
		// 21.06.18 chyoon 두 계정으로 동시에 같은 대상자를 선정하였을 경우 하나는 insert / 하나는 update로.
		// 후 처리 되는 트랜젝션은 서비스 넘버가 없어서 조회 후 map에 넣음.		
		if("DUP".equals(StringUtil.nvl(param.get("DUP")))) {			
			//user_id로 서비스 번호 조회
			Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.getSvcMngtNoForUserId", param);
			param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		}
		
		//1. 현재 서비스참여 상태 이력 저장
		insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtHist", param); //insertTrgterHist
		
		if("40".equals(param.get("PRE_TRGTER_STTUS"))){
			Map<String, Object> rsPreTrgterNo = selectOne("mhc.web.tg.svcjoinmngt.preTrgterNoYearChk", param);
			param.put("OLD_PRE_TRGTER_NO", param.get("PRE_TRGTER_NO"));
			param.put("PRE_TRGTER_NO", rsPreTrgterNo.get("PRE_TRGTER_NO"));
			
			update("mhc.web.tg.svcjoinmngt.updateNewPreTrgterSttus", param);
		}else{
			//2. pre_trgter_sttus 20->50 : 대상자선정, 30->60 : 진료 및 약물치료 체크, 40->70 : 기타 체크
			update("mhc.web.tg.svcjoinmngt.updatePreTrgterSttus", param);	
		}
		
		//3_1. 상담내역 삭제
		delete("mhc.web.tg.svcjoinmngt.deleteSvcJoinMngtCnsl", param);
		//3_2. 상담내역 저장
		insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtCnsl", param);
			
		//질환정보 저장
		param.put("CNSL_DE", param.get("CNSL_DE").toString());
		param.put("SVC_MNGT_NO", param.get("SVC_MNGT_NO").toString());
		
		int cnt = Integer.parseInt(param.get("cntData").toString());
		for(int i=1; i<=cnt/2; i++){
			if(i <= 9){
				param.put("DISS_CD", "0"+i);
				if(param.get("0"+i+"_1") != null){
					param.put("DIAG_YN", param.get("0"+i+"_1"));					
				} else {
					param.put("DIAG_YN", 'N');
				}
				if(param.get("0"+i+"_2") != null){
					param.put("DRUG_TAKE_YN", param.get("0"+i+"_2"));					
				} else {
					param.put("DRUG_TAKE_YN", 'N');
				}
			} else {
				param.put("DISS_CD", i);
				if(param.get(i+"_1") != null){
					param.put("DIAG_YN", param.get(i+"_1"));
				} else {
					param.put("DIAG_YN", 'N');
				}
				if(param.get(i+"_2") != null){
					param.put("DRUG_TAKE_YN", param.get(i+"_2"));
				} else {
					param.put("DRUG_TAKE_YN", 'N');
				}
			}
			//4_1. 질환정보 삭제
			delete("mhc.web.tg.svcjoinmngt.deleteSvcJoinMngtDiss", param);
			//4_2. 질환정보 저장
			insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtDiss", param);
		}
		
		//5. 서비스참여관리 TRGTER_STTUS update
		update("mhc.web.tg.svcjoinmngt.updateSvcJoinMngtTrgterSttus", param);
		
		//6. 맨위 체크인 경우 - 확정
		if("20".equals(param.get("TRGTER_STTUS"))){
			//6_1. 방문상담 정보 조회
			int cnslChkInt = selectOne("mhc.web.tg.svcjoinmngt.selectSvCnslCheck", param);
			//6_2. 커뮤니티 기본그룹 정보 조회
			int cmntyGrpMbChkInt = selectOne("mhc.web.tg.svcjoinmngt.selectTrgterAllCmntyGrpMb", param);
			
			if(cnslChkInt == 0){
				Map<String, Object> rsMap2 = selectOne("mhc.web.tg.svcjoinmngt.selSvcJoinMngtCnslNo", param);
				param.put("CNSL_SN", rsMap2.get("CNSL_SN"));
				//6_3. 초기 방문상담 정보 insert
				insert("mhc.web.tg.svcjoinmngt.insertSvcJoinMngtCnslCnfm", param);
			}
			
			if(cmntyGrpMbChkInt == 0){
				//6_4. 보건소 커뮤니티 기본 그룹 맴버 추가
				insert("mhc.web.tg.svcjoinmngt.trgterCmntyGrpMbAdd", param);
			}
		}else if("30".equals(param.get("TRGTER_STTUS"))){
			if("Y".equals(param.get("RE_REG_YN"))){
				insert("mhc.web.tg.svcjoinmngt.oldSvcJoinMngtRollBack", param);
			}else{
				//7_1. 서비스참여 정보 테이블 기관정보 update
				update("mhc.web.tg.svcjoinmngt.updateDropJoinMngt", param);
				//7_2. 예비대상자 정보 테이블 기관정보 및 탈락정보 update
				update("mhc.web.tg.svcjoinmngt.updateDropPreTrgterInfo", param);
				//7_3. user정보 테이블 개인정보 삭제 update
				update("mhc.web.tg.svcjoinmngt.updateDropUserInfo", param);
			}
			//7_4. 커뮤니티 모든 그룹 맴버 삭제
			delete("mhc.web.tg.svcjoinmngt.trgterAllCmntyGrpMbDel", param);	
		}else{
			//8. 보건소 커뮤니티 모든 그룹 맴버 삭제 
			delete("mhc.web.tg.svcjoinmngt.trgterAllCmntyGrpMbDel", param);			
		}
	}	
	
	
	//서비스참여관리 검진결과 조회 팝업
	public List<Map<String, Object>> getSvcJoinMngtRsltPop(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.svcJoinMngtRsltPop", param);
		return rsList;
	}

	//서비스참여관리 신규 클릭 시 군분류 조회
	public Map<String, Object> getSelSvcJoinMngtGunClas(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.selSvcJoinMngtGunClas", param);
		return rsMap;
	}
	
	//서비스참여관리 군분류 이력 insert
	public void insertHistory(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.svcjoinmngt.insertHistory", param);
	}

/*	public int checkCnslClf(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = selectOne("mhc.web.sv.svcjoinmngt.selCnsl_clf", param);
		return rsInt;
	}*/
	
	//탈락자대상자 정보백업  목록 조회
	public List<Map<String, Object>> droptrageterInfoExcelList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.droptrageterInfoExcelList", param);
		return rsList;
	}
	
	//서비스참여관리 건강위험요인 감소 조회 팝업
	public List<Map<String, Object>> getHealthRiskReducePop(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.healthRiskReducePop", param);
		return rsList;
	}
	
	//서비스참여관리 건강행태 변화 결과  조회 팝업
	public List<Map<String, Object>> getHealthBehevImpPop(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.svcjoinmngt.healthBehevImpPop", param);
		return rsList;
	}
	
	public Map<String, Object> getSvcMngtNoForUserId(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.svcjoinmngt.getSvcMngtNoForUserId", param);
		return rsMap;
	}

	public int chkDupSvcNo(Map<String, Object> param) {
		int rsInt = selectOne("mhc.web.tg.svcjoinmngt.chkDupSvcNo", param);
		return rsInt;
	}

	public void updateTrgterFlag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhc.web.tg.svcjoinmngt.updateTrgterFlag", param);
	}
		
}