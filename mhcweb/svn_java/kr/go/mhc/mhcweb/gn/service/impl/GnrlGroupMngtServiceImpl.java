package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.gn.service.GnrlGroupMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :GnrlGroupMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 일반사용자 그룹관리에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.23		이태석			최초생성
 * 
 * @author thejoin
 * @since 2019.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.gn.GnrlGroupMngtService")
public class GnrlGroupMngtServiceImpl extends EgovAbstractServiceImpl implements GnrlGroupMngtService{
	
	@Resource(name="web.gn.GnrlGroupMngtDAO")
	private GnrlGroupMngtDAO gnrlGroupMngtDAO;
	
	/**
	  * 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	
	@Override
	public List<Map<String, String>> selectGnrlGroupList(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectGnrlGroupList(param);
	}
	
	/**
	  * 그룹 참여자 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public List<Map<String, String>> selectGroupJoinList(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectGroupJoinList(param);
	}
	
	/**
	 * 그룹 참여 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	@Override
	public int updateJoinYn(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.updateJoinYn(param);
	}
	
	/**
	  * 추가 사용자 조회 (팝업)
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public List<Map<String, String>> selectAddGroupUserList(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectAddGroupUserList(param);
	}
	
	/**
	 * 참여자 추가  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void insertGroupUser(Map<String, Object> param) throws Exception {
		gnrlGroupMngtDAO.insertGroupUser(param);
	}
	
	/**
	 * 참여자자 제외  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void deleteJoinGroup(Map<String, Object> param) throws Exception {
		gnrlGroupMngtDAO.deleteJoinGroup(param);		
	}
	
	/**
	  *  신규 그룹 순번 조회
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public Map<String,Object> selectNewGroupSn(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectNewGroupSn(param);
	}
	
	/**
	  * 신규 그룹 등록
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public void insertNewGroup(Map<String, Object> param) throws Exception {
		gnrlGroupMngtDAO.insertNewGroup(param);
	}
	
	/**
	  * 그룹 수정
	  *	@param param 저장 정보
	  * @return 
	  * @throws Exception 
	  */
	@Override
	public void updateGroup(Map<String, Object> param) throws Exception {
		gnrlGroupMngtDAO.updateGroup(param);
	}
	
	/**
	 * 일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectDayAct(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectDayAct(param);
	}
	
	/**
	 * 요일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectDayWeekAct(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectDayWeekAct(param);
	}
	
	/**
	 * 시간대별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectTmAct(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectTmAct(param);
	}
	
	/**
	 * 성별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectGenderUserCnt(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectGenderUserCnt(param);
	}
	
	/**
	 * 연령별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectAgeUserCnt(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectAgeUserCnt(param);
	}
	
	/**
	 * 성별/연령별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectGenderAgeAct(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectGenderAgeAct(param);
	}
	
	/**
	 * 그룹 별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectGruopRank(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectGruopRank(param);
	}
	
	/**
	 * 개인 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectActCntList(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectActCntList(param);
	}

	/**
	 * 보편 기관 개인정보 동의서 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> selectOrgPrivacyAgree(Map<String, Object> param) throws Exception {
		return gnrlGroupMngtDAO.selectOrgPrivacyAgree(param);
	}

	/**
	 * 보편 기관 개인정보 동의서 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void insertOrgPrivacyAgree(Map<String, Object> param) throws Exception {
		gnrlGroupMngtDAO.updateOrgPrivacyAgree(param);
		gnrlGroupMngtDAO.insertOrgPrivacyAgree(param);
	}
}
