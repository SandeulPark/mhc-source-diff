package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황  DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.sv.CommunityDAO")
public class CommunityDAO extends DMultiEgovAbstractMapper{

	public int selectCommunityMyTabListCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.community.selectCommunityMyTabListCount",param);
		return rsInt;
	}

	public List<Map<String, String>> selectCommunityMyTabList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("smhc.web.sv.community.selectCommunityMyTabList", param);		
		return rsList;
	}

	public int selectCommunityOtherTabListCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.community.selectCommunityOtherTabListCount",param);
		return rsInt;
	}

	public List<Map<String, String>> selectCommunityOtherTabList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("smhc.web.sv.community.selectCommunityOtherTabList", param);		
		return rsList;
	}

	public int communityBoardReg(Map<String, Object> param) {
		int rsInt = insert("smhc.web.sv.community.communityBoardReg", param);
		insert("smhc.web.sv.community.communityBoardTargetReg", param);
		return rsInt;
	}

	public Map<String, Object> selectBoardData(Map<String, Object> param) {
		return selectOne("smhc.web.sv.community.selectBoardData", param);
	}

	public List<Map<String, Object>> selectimgBoardData(Map<String, Object> param) {
		return selectList("smhc.web.sv.community.selectimgBoardData", param);
	}

	public int communityBoardEdit(Map<String, Object> param) {
		int rsInt = update("smhc.web.sv.community.communityBoardEdit", param);//게시물업데이트
		if(param.get("asisTarget") != ""){
			update("smhc.web.sv.community.communityBoardEditFlagN", param);//기존그룹 USE_YN ='N'
		}
		insert("smhc.web.sv.community.communityBoardEditGrp", param);//새로등록된 그룹 insert or update
		
		return rsInt;
	}

	public int deleteBoard(Map<String, Object> param) {
		return update("smhc.web.sv.community.deleteBoard", param);
	}

	public Map<String, Object> selectCommunityDetailBoardData(Map<String, Object> param) {
		return selectOne("smhc.web.sv.community.selectCommunityDetailBoardData", param);
	}

	public List<Map<String, Object>> selectCommunityDetailBoardImg(Map<String, Object> param) {
		return selectList("smhc.web.sv.community.selectCommunityDetailBoardImg", param);
	}

	public List<Map<String, Object>> selectCommunityDetailCmmnt(Map<String, Object> param) {
		return selectList("smhc.web.sv.community.selectCommunityDetailCmmnt", param);
	}

	public List<Map<String, Object>> selectCommunityDetailLike(Map<String, Object> param) {
		return selectList("smhc.web.sv.community.selectCommunityDetailLike", param);
	}

	public int insertCommunitySingleCmmnt(Map<String, Object> param) {
		param.put("CMMNT_SN", selectOne("smhc.web.sv.community.getCmmntSn", param));
		int rsInt = insert("smhc.web.sv.community.insertCommunitySingleCmmnt", param);	
		return rsInt;  
	}

	public int updateCommunitySingleCmmnt(Map<String, Object> param) {
		return update("smhc.web.sv.community.updateCommunitySingleCmmnt", param);
	}

	public int insertCommunityMultiCmmnt(Map<String, Object> param) {
		int rsInt = insert("smhc.web.sv.community.insertCommunityMultiCmmnt", param);
		return rsInt;
	}
	
	public int defAttchFile(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		rsInt = update("smhc.web.sv.community.defAttchFile", param);
		return rsInt;
	}

	public int getBoardSn(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.community.getBoardSn", param);
		return rsInt;
	}

	public List<Map<String, Object>> selectCommunityDetailBoardVideo(Map<String, Object> param) {
		return selectList("smhc.web.sv.community.selectCommunityDetailBoardVideo", param);
	}
}