package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.MajorResultIndexService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :AppServiceUseSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 주요성과 지표 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.11.21		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.11.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.pm.MajorResultIndexService")
public class MajorResultIndexServiceImpl implements MajorResultIndexService {
	
	@Resource(name= "web.pm.MajorResultIndexDAO")
	private MajorResultIndexDAO majorResultIndexDAO;	
	
	
	@Override
	public List<Map<String, Object>> selectSummaryList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectSummaryList(param);
	}

	@Override
	public List<Map<String, Object>> selectSummaryTrgterList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectSummaryTrgterList(param);
	}	
	

	@Override
	public List<Map<String, Object>> selectTrgterPartDropList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectTrgterPartDropList(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterPartDropTrgterList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectTrgterPartDropTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthResultImpList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthResultImpList(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthResultImpTrgterList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthResultImpTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthDangerDecList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthDangerDecList(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthDangerDecTrgterList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthDangerDecTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectServeySatisScoreList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectServeySatisScoreList(param);
	}

	@Override
	public List<Map<String, Object>> selectServeySatisScoreTrgterList(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectServeySatisScoreTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectSummaryTrgerList(Map<String, Object> param) throws Exception {		
		return majorResultIndexDAO.selectSummaryTrgerList(param);
	}

	@Override
	public List<Map<String, Object>> selectSummaryListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectSummaryListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterPartDropListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectTrgterPartDropListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthResultImpListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthResultImpListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthDangerDecListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectHealthDangerDecListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectServeySatisScoreListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectServeySatisScoreListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectSummaryTrgerListNew(Map<String, Object> param) throws Exception {
		return majorResultIndexDAO.selectSummaryTrgerListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterPartDropTrgterListNew(Map<String, Object> param) {
		return majorResultIndexDAO.selectTrgterPartDropTrgterListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthResultImpTrgterListNew(Map<String, Object> param) {
		return majorResultIndexDAO.selectHealthResultImpTrgterListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectHealthDangerDecTrgterListNew(Map<String, Object> param) {
		return majorResultIndexDAO.selectHealthDangerDecTrgterListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectServeySatisScoreTrgterListNew(Map<String, Object> param) {
		return majorResultIndexDAO.selectServeySatisScoreTrgterListNew(param);
	}
	

}
