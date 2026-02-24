package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : GnrlGroupMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 일반사용자 그룹관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
  * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.23		이태석			최초생성
 * 
 * @author thejoin
 * @since 2019.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlGroupMngtDAO")
public class GnrlGroupMngtDAO extends DMultiEgovAbstractMapper{
	
	/**
	  * 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	public List<Map<String, String>> selectGnrlGroupList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectGnrlGroupList", param);		
		return rsList;  
	}
	
	/**
	  * 그룹 참여자 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	public List<Map<String, String>> selectGroupJoinList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectGroupJoinList", param);		
		return rsList;  
	}
	
	/**
	 * 그룹 참여 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updateJoinYn(Map<String, Object> param) throws Exception {
		int rsInt = 0;
		if(param.get("USE_YN").toString().equals("N")){
			rsInt += delete("mhc.web.gn.gnrlgroupmngt.deleteJoinGroup", param);	
			rsInt += delete("mhc.web.gn.gnrlgroupmngt.deleteGrpIdnfr", param);
		}else{
			Map<String,String> rsMap = selectOne("mhc.web.gn.gnrlgroupmngt.selectJoinGroupYn", param);		
			rsInt += update("mhc.web.gn.gnrlgroupmngt.updateJoinGroup", param);	
			rsInt += update("mhc.web.gn.gnrlgroupmngt.updateGrpIdnfr", param);	
			if(!param.get("GRP_SN").equals("0000") && rsMap.get("JOIN_YN").equals("N")){
				param.put("GRP_SN", "0000");
				rsInt = update("mhc.web.gn.gnrlgroupmngt.updateJoinGroup", param);
			}
		}
		return rsInt;
	}
	
	/**
	  * 추가 사용자 조회 (팝업)
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	public List<Map<String, String>> selectAddGroupUserList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectAddGroupUserList", param);		
		return rsList;  
	}
	
	/**
	 * 참여자 추가  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void insertGroupUser(Map<String, Object> param) throws Exception {
//		String[] modalwrapInfo = param.get("modalwrapInfo").toString().split(",");	
//		int modalwrapCnt = Integer.parseInt(param.get("modalwrapCnt").toString());
//		Map<String, Object> modalwrapInfoMap = new HashMap<String, Object>();
//			for(int i=0; i < modalwrapCnt; i++){
//				modalwrapInfoMap.put("SESS_CMNTY_CD", param.get("SESS_CMNTY_CD"));
//				modalwrapInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
//				modalwrapInfoMap.put("GRP_SN", param.get("GRP_SN"));
//				modalwrapInfoMap.put("USER_ID", modalwrapInfo[i]);
//				update("mhc.web.gn.gnrlgroupmngt.insertGroupUser", modalwrapInfoMap);
//			}
//		}
		
		update("mhc.web.gn.gnrlgroupmngt.insertGroupUser", param);
	}
	
	/**
	 * 참여자 제외  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void deleteJoinGroup(Map<String, Object> param) throws Exception {
		String[] trgterInfo = param.get("trgterInfo").toString().split(",");	
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			trgterInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			trgterInfoMap.put("SESS_CMNTY_CD", param.get("SESS_CMNTY_CD"));
			trgterInfoMap.put("USER_ID", trgterInfo[i*2]);
			trgterInfoMap.put("GRP_SN", trgterInfo[1]);
			delete("mhc.web.gn.gnrlgroupmngt.deleteJoinGroup", trgterInfoMap);
			delete("mhc.web.gn.gnrlgroupmngt.deleteGrpIdnfr", trgterInfoMap);
		}
	}
	
	/**
	  *  신규 그룹 순번 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
      */
	public Map<String,Object> selectNewGroupSn(Map<String, Object> param) throws Exception {		
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlgroupmngt.selectNewGroupSn", param);
		return rsMap;  
	}
	
	/**
	  *  신규 그룹 등록
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
      */
	public void insertNewGroup(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlgroupmngt.insertNewGroup", param);
	}
	
	/**
	  * 그룹 수정
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	public void updateGroup(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlgroupmngt.updateGroup", param);
	}
	
	/**
	 * 일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayAct(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectDayAct", param);
		return rsList;
	}
	
	/**
	 * 요일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayWeekAct(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectDayWeekAct", param);
		return rsList;
	}
	
	/**
	 * 시간대별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTmAct(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectTmAct", param);
		return rsList;
	}
	
	/**
	 * 성별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGenderUserCnt(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectGenderUserCnt", param);
		return rsList;
	}
	
	/**
	 * 연령별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAgeUserCnt(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectAgeUserCnt", param);
		return rsList;
	}
	
	/**
	 * 성별/연령별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGenderAgeAct(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectGenderAgeAct", param);
		return rsList;
	}
	
	/**
	 * 그룹 별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGruopRank(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectGruopRank", param);
		return rsList;
	}
	
	/**
	 * 개인 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectActCntList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectActCntList", param);
		return rsList;
	}

	/**
	 * 보편 기관 개인정보 동의서 조회
	 */
	public List<Map<String, Object>> selectOrgPrivacyAgree(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlgroupmngt.selectOrgPrivacyAgree", param);
		return rsList;
	}

	/**
	  * 보편 기관 개인정보 동의서 수정
	  *	@param param
	  * @return
	  * @throws Exception
	  */
	public void updateOrgPrivacyAgree(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrlgroupmngt.updateOrgPrivacyAgreeUseYn", param);
	}

	/**
	  * 기관 개인정보 동의서 저장
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
      */
	public void insertOrgPrivacyAgree(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlgroupmngt.insertOrgPrivacyAgree", param);
	}

}
