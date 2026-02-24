package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.AppServiceUseSttusService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :AppServiceUseSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 App서비스 이용형환 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
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

@Service(value= "web.pm.AppServiceUseSttusService")
public class AppServiceUseSttusServiceImpl implements AppServiceUseSttusService {

	@Resource(name= "web.pm.AppServiceUseSttusDAO")
	private AppServiceUseSttusDAO appServiceUseSttusDAO;

	@Override
	public List<Map<String, Object>> selectAppServiceUseSttusList(Map<String, Object> param) throws Exception {
		return appServiceUseSttusDAO.selectAppServiceUseSttusList(param);
	}

	@Override
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterList(Map<String, Object> param) throws Exception {
		return appServiceUseSttusDAO.selectAppServiceUseSttusTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectAppServiceUseSttusListNew(Map<String, Object> param) throws Exception {
		return appServiceUseSttusDAO.selectAppServiceUseSttusListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterListNew(Map<String, Object> param) {
		return appServiceUseSttusDAO.selectAppServiceUseSttusTrgterListNew(param);
	}
	
}
