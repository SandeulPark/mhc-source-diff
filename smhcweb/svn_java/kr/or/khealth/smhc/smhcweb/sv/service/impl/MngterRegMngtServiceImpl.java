package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.MngterRegMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :MngterRegMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 화면에 필요한 DAO와 연동 관리하는 Class
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

@Service(value= "web.sv.MngterRegMngtService")
public class MngterRegMngtServiceImpl extends EgovAbstractServiceImpl implements MngterRegMngtService{

	@Resource(name="web.sv.MngterRegMngtDAO")
	private MngterRegMngtDAO mngterRegMngtDAO;

	@Override
	public List<Map<String, String>> selectMngterRegMngtList(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.selectMngterRegMngtList(param);
	}

	@Override
	public int saveManagerInfo(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.saveManagerInfo(param);
	}

	@Override
	public int updatedn1Use(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.updatedn1Use(param);
	}

	@Override
	public int updatedn2Use(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.updatedn2Use(param);
	}

	@Override
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.updateApprovalYn(param);
	}

	@Override
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getManagerDuplicationCnt(param);
	}

	@Override
	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getServiceRequestList(param);
	}

	@Override
	public int getServiceRequestListCount(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getServiceRequestListCount(param);
	}

	@Override
	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getServiceRequestExcelList(param);
	}

	@Override
	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getTrgtMenuCombo(param);
	}

	@Override
	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception {
		mngterRegMngtDAO.saveServiceRequestMngt(param);
	}

	@Override
	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception {
		return mngterRegMngtDAO.getServiceRequestDtls(param);
	}

	@Override
	public void delServiceRequest(Map<String, Object> param) throws Exception {
		mngterRegMngtDAO.delServiceRequest(param);
	}
	
	@Override
	public List<Map<String, String>> selectMngtList() throws Exception {
		return mngterRegMngtDAO.selectMngtList();
	}
}
