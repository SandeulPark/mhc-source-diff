package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.RankPymntService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :RankPymntServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 배송및지급 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.28		이은주			최초생성
 
 * @author gst
 * @since 2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.RankPymntService")
public class RankPymntServiceImpl implements RankPymntService {

	@Resource(name= "web.mr.RankPymntDAO")
	private RankPymntDAO rankPymntDAO;
	
	//배송 및 지급 보건기관 목록
	@Override
	public List<Map<String, Object>> orgCdList(Map<String, Object> param) throws Exception {
		return rankPymntDAO.orgCdList(param);
	}
	//배송 및 지급 목록 조회
	@Override
	public List<Map<String, Object>> rankPymntList(Map<String, Object> param) throws Exception {
		return rankPymntDAO.rankPymntList(param);
	}
	//배송 및 지급 목록 count
	@Override
	public Map<String, Object> rankPymntCnt(Map<String, Object> param) throws Exception {
		return rankPymntDAO.rankPymntCnt(param);
	}
	
	//배송 및 지급 배송완료 업데이트
	@Override
	public void updatePymnt(Map<String, Object> param) throws Exception {
		rankPymntDAO.updatePymnt(param);
	}
	//배송 및 지급 건강포인트 내역
	@Override
	public List<Map<String, Object>> healthPointList(Map<String, Object> param) throws Exception {
		return rankPymntDAO.healthPointList(param);
	}
	//배송 및 지급 수상내역
	@Override
	public List<Map<String, Object>> awardList(Map<String, Object> param) throws Exception {
		return rankPymntDAO.awardList(param);
	}
}
