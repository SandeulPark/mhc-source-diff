package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.MissionCodeMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :MissionCodeMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 미션코드 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.11		이은주			최초생성
 * @    2016.10.17      허광일          수정보안
 * @author gst
 * @since 2016.08.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.sv.MissionCodeMngtService")
public class MissionCodeMngtServiceImpl extends EgovAbstractServiceImpl implements MissionCodeMngtService {
	
	@Resource(name= "web.sv.MissionCodeMngtDAO")
	private MissionCodeMngtDAO missionCodeMngtDAO;

	/**
	 * 미션코드관리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getMissionCodeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionCodeMngtDAO.getMissionCodeList(param);
	}

	/**
	 * 미션코드관리 신규 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */	
	@Override
	public void getMissionCodeInsert(Map<String, Object> param)	throws Exception {
		// TODO Auto-generated method stub
		missionCodeMngtDAO.getMissionCodeInsert(param);
	}

	/**
	 * 미션코드관리 수정
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@Override
	public void getMissionCodeUp(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		missionCodeMngtDAO.getMissionCodeUp(param);
	}

	/**
	 * 미션코드관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getMissionCodeDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return missionCodeMngtDAO.getMissionCodeDtls(param);
	}
	
	/**
	 * 미션코드관리 삭제	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	@Override
	public void getMissionCodeDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		missionCodeMngtDAO.getMissionCodeDel(param);
	}
	
	/**
	 * 신규 미션코드 조회
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 단일 ROW
	 * @throws Exception 
	 */
	@Override
	public Map<String,Object> selectNewMissionCode(Map<String, Object> param) throws Exception {
		return missionCodeMngtDAO.selectNewMissionCode(param);
	}

}
