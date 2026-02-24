package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcapp.gn.service.GnrlGroupMngtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : GnrlGroupMngtServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 그룹관리에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.7		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.10.7
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.gn.GnrlGroupMngtService")
public class GnrlGroupMngtServiceImpl extends EgovAbstractServiceImpl implements GnrlGroupMngtService{

	@Resource(name="mhcapp.gn.GnrlGroupMngtDAO")
    private GnrlGroupMngtDAO gnrlGroupMngtDAO;
	
	/**
	 * 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlGroupMngtDAO.selectGroupList(param);
	}
	
	@Override
	public String isExistGroup(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlGroupMngtDAO.isExistGroup(param);
	}
	
	@Override
	public String isExistGrpSn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
				return gnrlGroupMngtDAO.isExistGrpSn(param);
	}
	/**
	 * 그룹 참여 신청
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertJoinGroup(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlGroupMngtDAO.insertJoinGroup(param);
	}
	
	/**
	 * 참여 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectJoinGroupList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlGroupMngtDAO.selectJoinGroupList(param);
	}
	
	/**
	 * 참여 그룹 탈퇴
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void deleteJoinGroup(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlGroupMngtDAO.deleteJoinGroup(param);
	}
	
	/**
	 * 소속 식별 번호 INSERT
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertGrpIndfr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlGroupMngtDAO.insertGrpIndfr(param);
	}

	/**
	 * 소속 식별 번호 DELETE
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void deleteGrpIdnfr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		gnrlGroupMngtDAO.deleteGrpIdnfr(param);
	}

	@Override
	public String isExistGrpIdnfr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlGroupMngtDAO.isExistGrpIdnfr(param);
	}

	@Override
	public List<Map<String, String>> selectOrgPrivacyAgree(Map<String, Object> param)
		throws Exception {
		return gnrlGroupMngtDAO.selectOrgPrivacyAgree(param);
	}
}
