package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sv.service.SvcMngtService;

/**
 * @Class Name :SvcMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.SvcMngtService")
public class SvcMngtServiceImpl extends EgovAbstractServiceImpl implements SvcMngtService{
	
	@Resource(name="web.sv.SvcMngtDAO")
	private SvcMngtDAO svcMngtDAO;

	@Override
	public int getHealthInfoMngtListCount(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getHealthInfoMngtListCount(param);
	}

	@Override
	public List<Map<String, String>> getHealthInfoMngtList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getHealthInfoMngtList(param);
	}
	
	public Map<String, Object> getHealthInfoDtls(Map<String, Object> param) throws Exception {		
		return svcMngtDAO.getHealthInfoDtls(param);  
	}

	@Override
	public List<Map<String, String>> getCmntyGroupList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getCmntyGroupList(param);
	}
	
	@Override
	public List<Map<String, String>> getAllCmntyGroupList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getAllCmntyGroupList(param);
	}

	@Override
	public List<Map<String, String>> getCmntyMclasList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getCmntyMclasList(param);
	}

	@Override
	public List<Map<String, String>> getCmntyChronicList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getCmntyChronicList(param);
	}

	@Override
	public List<Map<String, String>> getHealthInfoCmmntList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getHealthInfoCmmntList(param);
	}
	
	@Override
	public List<Map<String, String>> getHealthInfoCmmntAttchList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getHealthInfoCmmntList(param);
	}

	@Override
	public List<Map<String, String>> getHealthInfoGoodList(Map<String, Object> param) throws Exception {
		return svcMngtDAO.getHealthInfoGoodList(param);
	}

	@Override
	public void getHealthInfoDtlsUpdate(Map<String, Object> param) throws Exception {
		svcMngtDAO.getHealthInfoDtlsUpdate(param);
	}
	
	@Override
	public void getHealthInfoReCmmntInsert(Map<String, Object> param) throws Exception {		
		svcMngtDAO.getHealthInfoReCmmntInsert(param);
	}

	@Override
	public void getnewHealthInfoInsert(Map<String, Object> param) throws Exception {
		svcMngtDAO.getnewHealthInfoInsert(param);
	}

	@Override
	public void gethealthInfoReCmmntDelete(Map<String, Object> param) throws Exception {
		svcMngtDAO.gethealthInfoReCmmntDelete(param);
	}

	@Override
	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcMngtDAO.getSelectAttchFileList(param);
	}

	@Override
	public void updateArticlePostClf(Map<String, Object> param) throws Exception {
		svcMngtDAO.updateArticlePostClf(param);
	}
	
	@Override
	public void getHealthInfoCmmntInsert(Map<String, Object> param) throws Exception {		
		svcMngtDAO.getHealthInfoCmmntInsert(param);
	}
	
	@Override
	public void healthInfoDtlsDelete(Map<String,Object> param) throws Exception{
		svcMngtDAO.healthInfoDtlsDelete(param);
	}
	
	/**
	 * 댓글 목록 수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getReCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcMngtDAO.getReCnt(param);
	}
	
	/**
	 * 좋아요 목록 수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getGoodCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return svcMngtDAO.getGoodCnt(param);
	}

	@Override
	public List<Map<String, String>> getAllCmntyMclasList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
