package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;



/**
 * @Class Name : GnrlUserInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 대상자 상태 정보 변경 관리하는 DAO Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.gn.GnrlUserInfoMngtDAO")
public class GnrlUserInfoMngtDAO extends DMultiEgovAbstractMapper{
	
	/**
	 * 일반대상자 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserInfoList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 운동 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserExcsList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserExcsList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 활동 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserActList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserActList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 체중 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserBodyCompList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserBodyCompList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 혈압 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserBloodPressList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserBloodPressList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 혈당 정보 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserBloodSugarList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserBloodSugarList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 식사 정보 목록 조회  (1119 추가)
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserMealDiaryList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserMealDiaryList",param);
		return rsList;  
	}
	/**
	 * 일반대상자 식사 정보 팝업 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlMealDiaryDtlsPop(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlMealDiaryDtlsPop",param);
		return rsList;  
	}
	
	/**
	 * 일반대상자 이름,생년월일 ,성별 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void updateUserInfo(Map<String, Object> param) throws Exception {
		update("mhc.web.gn.gnrluserinfomngt.updateUserInfo",param);		
	}

	/**
	 * 일반대상자 수면 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGnrlUserSleepList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrluserinfomngt.selectGnrlUserSleepList",param);
		return rsList;
	}
}
