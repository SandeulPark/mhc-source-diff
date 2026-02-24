package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.FaceToFaceInfoRegService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대면평가정보등록에 필요한 DAO와 연동 관리하는 Class
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

@Service(value="web.tg.FaceToFaceInfoRegService")
public class FaceToFaceInfoRegServiceImpl extends EgovAbstractServiceImpl implements FaceToFaceInfoRegService{
	
	@Resource(name="web.tg.FaceToFaceInfoRegDAO")
	private FaceToFaceInfoRegDAO faceToFaceInfoRegDAO;
	
	@Override
	public List<Map<String, Object>> selectFaceToFaceInfoList( Map<String, Object> param) throws Exception {
		return faceToFaceInfoRegDAO.selectFaceToFaceInfoList(param);
	}

	@Override
	public Map<String, Object> selectFaceToFaceSeniorDtls(Map<String, Object> param) throws Exception {
		return faceToFaceInfoRegDAO.selectFaceToFaceSeniorDtls(param);
	}

}
