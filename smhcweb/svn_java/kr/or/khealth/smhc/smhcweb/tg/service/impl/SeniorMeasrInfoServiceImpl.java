package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMeasrInfoService;

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

@Service(value="web.tg.SeniorMeasrInfoService")
public class SeniorMeasrInfoServiceImpl  implements SeniorMeasrInfoService{
	
	@Resource(name= "web.tg.SeniorMeasrInfoDAO")
	private SeniorMeasrInfoDAO seniorMeasrInfoDAO;
	
	
	//대상자정보관리 최근 7일간 측정 횟수 조회
	@Override
	public Map<String, Object> selectMeasrWeekCntInfo(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectMeasrWeekCntInfo(param);
	}	
	
	//대상자정보관리 미측정 경과일 조회
	@Override
	public Map<String, Object> selectMeasrNoMeasrInfo(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectMeasrNoMeasrInfo(param);
	}		

	//대상자총괄관리 대상자 할동 정보 조회
	@Override
	public List<Map<String, Object>> selectActChartList(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectActChartList(param);
	}
	
	//대상자총괄관리 대상자 체성분 정보 조회
	@Override
	public List<Map<String, Object>> selectBodyCompChartList(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectBodyCompChartList(param);
	}
	
	//대상자총괄관리 대상자 혈압 정보 조회
	@Override
	public List<Map<String, Object>> selectBloodPressChartList(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectBloodPressChartList(param);
	}
	
	//대상자총괄관리 대상자 혈 정보 조회
	@Override
	public List<Map<String, Object>> selectBloodSugarChartList(Map<String, Object> param) throws Exception {
		return seniorMeasrInfoDAO.selectBloodSugarChartList(param);
	}	

}
