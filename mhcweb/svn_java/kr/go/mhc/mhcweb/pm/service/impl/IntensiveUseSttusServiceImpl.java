package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.IntensiveUseSttusService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :IntensiveUseSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 이용형환 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		유준영			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.pm.IntensiveUseSttusService")
public class IntensiveUseSttusServiceImpl implements IntensiveUseSttusService {

	@Resource(name= "web.pm.IntensiveUseSttusDAO")
	private IntensiveUseSttusDAO intensiveUseSttusDAO;

	@Override
	public List<Map<String, Object>> selectIntensiveUseSttusList(Map<String, Object> param) throws Exception {
		return intensiveUseSttusDAO.selectIntensiveUseSttusList(param);
	}

	@Override
	public List<Map<String, Object>> selectIntensiveUseSttusTrgterList(Map<String, Object> param) throws Exception {
		return intensiveUseSttusDAO.selectIntensiveUseSttusTrgterList(param);
		
	}

	@Override
	public List<Map<String, Object>> selectIntensiveUseSttusListNew(Map<String, Object> param) {
		return intensiveUseSttusDAO.selectIntensiveUseSttusListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectIntensiveUseSttusTrgterListNew(Map<String, Object> param) {
		return intensiveUseSttusDAO.selectIntensiveUseSttusTrgterListNew(param);
	}
	
}
