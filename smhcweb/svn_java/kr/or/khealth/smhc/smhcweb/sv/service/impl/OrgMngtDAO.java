package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : OrgMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 기관 정보 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.22		양현우			최초생성
 *
 * @author theJoin
 * @since 2020.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.sv.OrgMngtDAO")
public class OrgMngtDAO extends DMultiEgovAbstractMapper{

	/**
	 * 기관리스트 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public List<Map<String, String>> selectOrgMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("smhc.web.sv.orgmngt.selectOrgMngtList", param);
		return rsList;
	}
	
	/**
	 * 기관 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngt(Map<String, Object> param) throws Exception {
		int rsInt = 0;		
		rsInt = update("smhc.web.sv.orgmngt.saveOrgInfo", param);
		return rsInt;
	}
	
}
