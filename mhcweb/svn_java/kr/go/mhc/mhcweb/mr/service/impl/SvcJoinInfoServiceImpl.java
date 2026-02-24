package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.SvcJoinInfoService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :SvcJoinInfoServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.19		이은주			최초생성
 
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.SvcJoinInfoService")
public class SvcJoinInfoServiceImpl implements SvcJoinInfoService {

	@Resource(name= "web.mr.SvcJoinInfoDAO")
	private SvcJoinInfoDAO svcJoinInfoDAO;

	@Override
	public List<Map<String, Object>> svcJoinInfoList(Map<String, Object> param) throws Exception {
		return svcJoinInfoDAO.svcJoinInfoList(param);
	}
	
	@Override
	public List<Map<String, Object>> svcJoinInfoTwoWeeksNull(Map<String, Object> param) throws Exception {
		return svcJoinInfoDAO.svcJoinInfoTwoWeeksNull(param);
	}
}
