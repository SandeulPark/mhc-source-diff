package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.ExcsRecService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :ExcsRecServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.10		허광일			최초생성
 
 * @author gst
 * @since 2016.11.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.ExcsRecService")
public class ExcsRecServiceImpl implements ExcsRecService {

	@Resource(name= "web.mr.ExcsRecDAO")
	private ExcsRecDAO excsRecDAO;

	@Override
	public List<Map<String, Object>> selectExcsRecList(Map<String, Object> param) throws Exception {
		return excsRecDAO.selectExcsRecList(param);
	}
	
}
