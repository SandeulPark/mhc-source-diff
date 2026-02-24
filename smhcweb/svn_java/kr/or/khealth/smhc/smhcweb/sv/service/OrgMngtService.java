package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : OrgMngtService.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 관리하는 서비스 interface
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
public interface OrgMngtService {
	
	/**
	 * 기관 목록 조회
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectOrgMngtList(Map<String, Object> param) throws Exception;	

	/**
	 * 기관 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveOrgMngt(Map<String, Object> param) throws Exception;
}
