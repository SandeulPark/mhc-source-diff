package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.MealRegService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : MealRegServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 식단등록 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.05		이은주			최초생성
 
 * @author gst
 * @since 2016.12.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.MealRegService")
public class MealRegServiceImpl implements MealRegService {
	
	@Resource(name= "web.mr.MealRegDAO")
	private MealRegDAO mealRegDAO;
	
	//식단등록 목록 조회
	@Override
	public List<Map<String, Object>> mealRegList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = mealRegDAO.mealRegList(param);
		return rsList;
	}
	
	//식단등록 상세 조회
	@Override
	public Map<String, Object> mealRegPopDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = mealRegDAO.mealRegPopDtls(param);
		return rsMap;
	}
	
	//식단등록 상세 조회 2
	@Override
	public List<Map<String, Object>> mealRegPopDtls2(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = mealRegDAO.mealRegPopDtls2(param);
		return rsList;
	}
}
