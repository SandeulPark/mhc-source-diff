package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import kr.or.khealth.smhc.smhcweb.tg.service.SeniorTrgterInfoService;

import org.springframework.stereotype.Service;

/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 조회 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.TrgterInfoMngtService")
public class SeniorTrgterInfoServiceImpl  implements SeniorTrgterInfoService{
	
	@Resource(name= "web.tg.TrgterInfoMngtDAO")
	private SeniorTrgterInfoDAO trgterInfoMngtDAO;

	//대상자총괄관리 대상자 목록 조회
	@Override
	public List<Map<String, Object>> selectSeniorTrgterInfoList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selectSeniorTrgterInfoList(param);
	}

	//대상자총괄관리 대상자 달력 조회 
	@Override
	public List<Map<String, Object>> selectSeniorTrgterCalendarList(Map<String, Object> param) throws Exception {
		return trgterInfoMngtDAO.selectSeniorTrgterCalendarList(param);
	}
}
