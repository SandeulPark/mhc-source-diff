package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.gn.service.GnrlPointRankingService;
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

@Service(value="web.gn.GnrlPointRankingService")
public class GnrlPointRankingServiceImpl extends EgovAbstractServiceImpl implements GnrlPointRankingService {
	
	@Resource(name= "web.gn.GnrlPointRankingDAO")
	private GnrlPointRankingDAO gnrlPointRankingDAO;

	@Override
	public Map<String, Object> getPointRankingMsg(Map<String, Object> param) throws Exception {
		return gnrlPointRankingDAO.getPointRankingMsg(param);
	}
	
	@Override
	public List<Map<String, Object>> getPointRankingList(Map<String, Object> param) throws Exception {
		
		return gnrlPointRankingDAO.getPointRankingList(param);
	}
	
	@Override
	public List<Map<String, Object>> getRankingAllPointList(Map<String, Object> param) throws Exception {
		
		return gnrlPointRankingDAO.getRankingAllPointList(param);
	}
	
	@Override
	public int updatepymntTrgtY(Map<String, Object> param) throws Exception {
		
		return gnrlPointRankingDAO.updatepymntTrgtY(param);
	}
	
	@Override
	public Map<String, Object> getStepRankingMsg(Map<String, Object> param) throws Exception{
		return gnrlPointRankingDAO.getStepRankingMsg(param);
	}
	
	@Override
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception{
		return gnrlPointRankingDAO.getStepRankingList(param);
	}
}