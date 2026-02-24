package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.MealDiaryRegService;

import org.springframework.stereotype.Service;

/**
 * @Class Name : MealDiaryRegServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 식사일기 등록 조회 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.05.11					최초생성
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.mr.MealDiaryRegService")
public class MealDiaryRegServiceImpl implements MealDiaryRegService {
	
	@Resource(name= "web.mr.MealDiaryRegDAO")
	private MealDiaryRegDAO mealDiaryRegDAO;
	
	//식사일기 등록 목록 조회
	@Override
	public List<Map<String, Object>> getMealDiaryRegList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = mealDiaryRegDAO.getMealDiaryRegList(param);
		return rsList;
	}
	
	//2018.06.19 유준영 추가
	//식사일기 등록정보 엑셀다운로드   조회
	@Override
	public List<Map<String, Object>> getMealDiaryExcel(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = mealDiaryRegDAO.getMealDiaryExcel(param);
		return rsList;
	}

}
