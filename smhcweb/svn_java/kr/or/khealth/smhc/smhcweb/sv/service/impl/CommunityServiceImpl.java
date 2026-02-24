package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.CommunityService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.sv.CommunityService")
public class CommunityServiceImpl extends EgovAbstractServiceImpl implements CommunityService{
	
	@Resource(name="web.sv.CommunityDAO")
	private CommunityDAO communityDAO;

	@Override
	public int selectCommunityMyTabListCount(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityMyTabListCount(param);
	}

	@Override
	public List<Map<String, String>> selectCommunityMyTabList(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityMyTabList(param);
	}

	@Override
	public int selectCommunityOtherTabListCount(Map<String, Object> param)throws Exception {
		return communityDAO.selectCommunityOtherTabListCount(param);
	}

	@Override
	public List<Map<String, String>> selectCommunityOtherTabList(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityOtherTabList(param);
	}

	@Override
	public int communityBoardReg(Map<String, Object> param) throws Exception {
		return communityDAO.communityBoardReg(param);
	}

	@Override
	public Map<String, Object> selectBoardData(Map<String, Object> param)throws Exception {
		return communityDAO.selectBoardData(param);
	}

	@Override
	public List<Map<String, Object>> selectimgBoardData(Map<String, Object> param) throws Exception {
		return communityDAO.selectimgBoardData(param);
	}

	@Override
	public int communityBoardEdit(Map<String, Object> param) throws Exception {
		return communityDAO.communityBoardEdit(param);
	}

	@Override
	public int deleteBoard(Map<String, Object> param) throws Exception {
		return communityDAO.deleteBoard(param);
	}

	@Override
	public Map<String, Object> selectCommunityDetailBoardData(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityDetailBoardData(param);
	}

	@Override
	public List<Map<String, Object>> selectCommunityDetailBoardImg(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityDetailBoardImg(param);
	}

	@Override
	public List<Map<String, Object>> selectCommunityDetailCmmnt(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityDetailCmmnt(param);
	}

	@Override
	public List<Map<String, Object>> selectCommunityDetailLike(Map<String, Object> param) throws Exception {
		return communityDAO.selectCommunityDetailLike(param);
	}

	@Override
	public int insertCommunitySingleCmmnt(Map<String, Object> param) throws Exception {
		return communityDAO.insertCommunitySingleCmmnt(param);
	}

	@Override
	public int updateCommunitySingleCmmnt(Map<String, Object> param) throws Exception {
		return communityDAO.updateCommunitySingleCmmnt(param);
	}

	@Override
	public int insertCommunityMultiCmmnt(Map<String, Object> param) throws Exception {
		return communityDAO.insertCommunityMultiCmmnt(param);
	}

	@Override
	public int defAttchFile(Map<String, Object> param) throws Exception {
		return communityDAO.defAttchFile(param);
	}

	@Override
	public int getBoardSn(Map<String, Object> param) throws Exception {
		return communityDAO.getBoardSn(param);
	}

	@Override
	public List<Map<String, Object>> selectCommunityDetailBoardVideo(Map<String, Object> param) throws Exception {		
		return communityDAO.selectCommunityDetailBoardVideo(param);
	}
}
