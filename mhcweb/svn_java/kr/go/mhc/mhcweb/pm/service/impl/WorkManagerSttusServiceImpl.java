package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.WorkManagerSttusService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :WorkManagerSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 업무담당자 현황 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
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

@Service(value= "web.pm.WorkManagerSttusService")
public class WorkManagerSttusServiceImpl implements WorkManagerSttusService {

	@Resource(name= "web.pm.WorkManagerSttusDAO")
	private WorkManagerSttusDAO workManagerSttusDAO;

	@Override
	public List<Map<String, Object>> selectWorkManagerSttusList(Map<String, Object> param) throws Exception {
		return workManagerSttusDAO.selectWorkManagerSttusList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectWorkManagerSttusTrgterList(Map<String, Object> param) throws Exception {
		return workManagerSttusDAO.selectWorkManagerSttusTrgterList(param);
	}
	
}
