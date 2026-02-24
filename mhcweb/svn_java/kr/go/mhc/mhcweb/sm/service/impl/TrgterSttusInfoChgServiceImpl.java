package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sm.service.TrgterSttusInfoChgService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :TrgterSttusInfoChgServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자 정보 변경에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04				최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Service("web.sm.TrgterSttusInfoChgService")
public class TrgterSttusInfoChgServiceImpl  extends EgovAbstractServiceImpl implements TrgterSttusInfoChgService{

	@Resource(name="web.sm.TrgterSttusInfoChgDAO")
	private TrgterSttusInfoChgDAO trgterSttusInfoChgDAO;
	
	@Override
	public List<Map<String, Object>> selectTrgterSttusInfoList(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.selectTrgterSttusInfoList(param);
	}

	@Override
	public int trgterSttusInfoTestChg(Map<String, Object> param)throws Exception {
		return trgterSttusInfoChgDAO.trgterSttusInfoTestChg(param);
	}
	@Override
	public int trgterSttusInfoTestChange(Map<String, Object> param)throws Exception {
		return trgterSttusInfoChgDAO.trgterSttusInfoTestChange(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterSttusInfoServiceSchedule(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.selectTrgterSttusInfoServiceSchedule(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterSttusInfoExamList(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.selectTrgterSttusInfoExamList(param);
	}

	@Override
	public int trgterSttusChange(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.trgterSttusChange(param);
	}

	@Override
	public String trgterScheduleChg(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.trgterScheduleChg(param);
	}

	@Override
	public int trgterScheduleDelete(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.trgterScheduleDelete(param);
	}
	@Override
	public List<Map<String, Object>> selectTrgterSttusInfoExamSn(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.selectTrgterSttusInfoExamSn(param);
	}
	@Override
	public int updateTrgterSttusInfoExamSn(Map<String, Object> param) throws Exception {
		return trgterSttusInfoChgDAO.updateTrgterSttusInfoExamSn(param);
	}
	
}
