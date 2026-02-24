package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.TrgterRegSttusService;

import org.springframework.stereotype.Service;


/**
 * @Class Name :TrgterRegSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자 등록현황 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.pm.TrgterRegSttusService")
public class TrgterRegSttusServiceImpl implements TrgterRegSttusService {

	@Resource(name= "web.pm.TrgterRegSttusDAO")
	private TrgterRegSttusDAO trgterRegSttusDAO;

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusGenList(Map<String, Object> param) throws Exception {
		return trgterRegSttusDAO.selectTrgterRegSttusGenList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectTrgterRegSttusDenList(Map<String, Object> param) throws Exception {
		return trgterRegSttusDAO.selectTrgterRegSttusDenList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectTrgterRegSttusDivList(Map<String, Object> param) throws Exception {
		return trgterRegSttusDAO.selectTrgterRegSttusDivList(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterList(Map<String, Object> param) throws Exception {
		return trgterRegSttusDAO.selectTrgterRegSttusGenTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusGenListNew(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterRegSttusDAO.selectTrgterRegSttusGenListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusDenListNew(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterRegSttusDAO.selectTrgterRegSttusDenListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusDivListNew(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterRegSttusDAO.selectTrgterRegSttusDivListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterListNew(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return trgterRegSttusDAO.selectTrgterRegSttusGenTrgterListNew(param);
	}

}
