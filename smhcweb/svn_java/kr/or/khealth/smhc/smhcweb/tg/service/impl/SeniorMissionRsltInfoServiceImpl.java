package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMissionRsltInfoService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 미션실천현황 에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19	
 
 * @author thejoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.SeniorMissionRsltInfoService")
public class SeniorMissionRsltInfoServiceImpl implements SeniorMissionRsltInfoService{

	@Resource(name= "web.tg.SeniorMissionRsltInfoDAO")
	private SeniorMissionRsltInfoDAO seniorMissionRsltInfoDAO;

	@Override
	public List<Map<String, Object>> selectMissionRsltInfoList(Map<String, Object> param) throws Exception {
		return seniorMissionRsltInfoDAO.selectMissionRsltInfoList(param);
	}

	@Override
	public List<Map<String, Object>> selectPhotoRsltInfoList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return seniorMissionRsltInfoDAO.selectPhotoRsltInfoList(param);
	}
	
}
