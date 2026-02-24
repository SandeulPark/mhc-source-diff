package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : GnrlGroupMngtDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 그룹관리에 DataBase 연동 관리하는 Class
 * @Modification Information @ @ 수정일 수정자 수정내용 
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.7		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.10.7
 * @version 1.0
 * @see Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.gn.GnrlGroupMngtDAO")
public class GnrlGroupMngtDAO extends DMultiEgovAbstractMapper {
	
	/**
	 * 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGroupList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.gnrlgroupmngt.selectGroupList", param);	
		return rsList;  
	}
	
	public String isExistGroup(Map<String, Object> param) throws Exception {
		String isExist = selectOne("mhcapp.gn.gnrlgroupmngt.isExistGroup", param);
		return isExist;
	}
	
	public String isExistGrpSn(Map<String, Object> param) throws Exception {
		String isExist = selectOne("mhcapp.gn.gnrlgroupmngt.isExistGrpSn", param);
		return isExist;
	}
	
	
	/**
	 * 그룹 참여 신청
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertJoinGroup(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
//		Map<String,String> rsMap = selectOne("mhcapp.gn.gnrlgroupmngt.selectJoinGroupYn", param);		
		insert("mhcapp.gn.gnrlgroupmngt.insertJoinGroup", param);
//		if(!param.get("GRP_SN").equals("0000") && rsMap.get("JOIN_YN").equals("N")){
//			param.put("GRP_SN", "0000");
//			insert("mhcapp.gn.gnrlgroupmngt.insertJoinGroup", param);
//		}
	}
	
	/**
	 * 참여 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectJoinGroupList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.gnrlgroupmngt.selectJoinGroupList", param);	
		return rsList;  
	}
	
	/**
	 * 참여 그룹 탈퇴
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void deleteJoinGroup(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		delete("mhcapp.gn.gnrlgroupmngt.deleteJoinGroup", param);		
	}

	/**
	 * 소속 식별 번호 INSERT
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertGrpIndfr(Map<String, Object> param) {
		// TODO Auto-generated method stub
		insert("mhcapp.gn.gnrlgroupmngt.insertGrpIndfr", param);
	}
	
	/**
	 * 소속 식별 번호 DELETE
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	public void deleteGrpIdnfr(Map<String, Object> param) {
		// TODO Auto-generated method stub
		delete("mhcapp.gn.gnrlgroupmngt.deleteGrpIdnfr", param);
	}

	/**
	 * 소속 식별 번호 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	public String isExistGrpIdnfr(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String isExist = selectOne("mhcapp.gn.gnrlgroupmngt.isExistGrpIdnfr", param);
		return isExist;
	}

	/**
	 * 기관 개인정보 동의서 조회
	 * jeeeeey 20231128 추가
	 * @param param
	 * @return
	 */
	public List<Map<String,String>> selectOrgPrivacyAgree(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("mhcapp.gn.gnrlgroupmngt.selectOrgPrivacyAgree", param);
		return rsList;
	}
}
