package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.SvcJoinRateService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :SvcJoinRateServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여율 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.15		이은주			최초생성
 
 * @author gst
 * @since 2016.11.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.SvcJoinRateService")
public class SvcJoinRateServiceImpl implements SvcJoinRateService {
	
	@Resource(name= "web.mr.SvcJoinRateDAO")
	private SvcJoinRateDAO svcJoinRateDAO;

	//서비스 참여율 목록 조회
	@Override
	public List<Map<String, Object>> svcJoinRateList(Map<String, Object> param) throws Exception {
		return svcJoinRateDAO.svcJoinRateList(param);
	}


}
