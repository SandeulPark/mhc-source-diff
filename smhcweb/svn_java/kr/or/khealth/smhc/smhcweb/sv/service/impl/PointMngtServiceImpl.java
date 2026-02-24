package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.PointMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :PointRankingServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 * 
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.sv.PointMngtService")
public class PointMngtServiceImpl extends EgovAbstractServiceImpl implements PointMngtService {
	
	@Resource(name= "web.sv.PointMngtDAO")
	private PointMngtDAO pointMngtDAO;

	@Override
	public List<Map<String, Object>> selectPointMngtList(Map<String, Object> param) throws Exception {
		return pointMngtDAO.selectPointMngtList(param);
	}

	@Override
	public List<Map<String, Object>> searchPointLogPop(Map<String, Object> param)throws Exception {
		return pointMngtDAO.searchPointLogPop(param);
	}

}