package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.mr.service.PointRankingService;
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

@Service(value="web.mr.PointRankingService")
public class PointRankingServiceImpl extends EgovAbstractServiceImpl implements PointRankingService {
	
	@Resource(name= "web.mr.PointRankingDAO")
	private PointRankingDAO pointRankingDAO;

	@Override
	public Map<String, Object> getPointRankingMsg(Map<String, Object> param) throws Exception {
		return pointRankingDAO.getPointRankingMsg(param);
	}
	
	@Override
	public List<Map<String, Object>> getPointRankingList(Map<String, Object> param) throws Exception {
		
		return pointRankingDAO.getPointRankingList(param);
	}
	
	@Override
	public List<Map<String, Object>> getRankingAllPointList(Map<String, Object> param) throws Exception {
		
		return pointRankingDAO.getRankingAllPointList(param);
	}
	
	@Override
	public int updatepymntTrgtY(Map<String, Object> param) throws Exception {
		
		return pointRankingDAO.updatepymntTrgtY(param);
	}
	
	@Override
	public Map<String, Object> getStepRankingMsg(Map<String, Object> param) throws Exception{
		return pointRankingDAO.getStepRankingMsg(param);
	}
	
	@Override
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception{
		return pointRankingDAO.getStepRankingList(param);
	}
}