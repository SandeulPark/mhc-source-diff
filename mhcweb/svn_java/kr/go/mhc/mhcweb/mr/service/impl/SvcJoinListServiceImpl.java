package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.SvcJoinListService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :SvcJoinListServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.06.20		나연이			최초생성
 
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.SvcJoinListService")
public class SvcJoinListServiceImpl implements SvcJoinListService {

	@Resource(name= "web.mr.SvcJoinListDAO")
	private SvcJoinListDAO svcJoinListDAO;

	
	/**
	 * 서비스 참여목록 조회
	 */
	public List<Map<String,String>> selectSvcJoinList(Map<String, Object> param) throws Exception{
		return svcJoinListDAO.selectSvcJoinList(param);
	}
	
}
