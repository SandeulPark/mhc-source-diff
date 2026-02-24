package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MngterRegMngtController.java
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

public interface MngterRegMngtService {

	public List<Map<String, String>> selectMngterRegMngtList(Map<String, Object> param) throws Exception;	
	
	public int saveManagerInfo(Map<String, Object> param) throws Exception;

	public int updatedn1Use(Map<String, Object> param) throws Exception;

	public int updatedn2Use(Map<String, Object> param) throws Exception;
	
	public int updateApprovalYn(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param)throws Exception;

	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception;

	public int getServiceRequestListCount(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception;

	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception;

	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception;

	public void delServiceRequest(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectMngtList()throws Exception;
}
