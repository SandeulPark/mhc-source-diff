package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorDtlsInfoService;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SeniorDtlsInfoController.java
 * @Description : 대면평가 등록 - 대상자 상세 정보
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.29		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.09.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */



@Repository("web.tg.SeniorDtlsInfoDAO")
public class SeniorDtlsInfoDAO extends DMultiEgovAbstractMapper {

	public int regUserDetail(Map<String, Object> param) throws Exception {
		int rsInt = update("smhc.web.tg.seniordtlsinfo.regUserDetail", param);
		return rsInt;
	}

	public int updateServiceManageDetail(Map<String, Object> param) throws Exception {
		return update("smhc.web.tg.seniordtlsinfo.updateServiceManageDetail", param);
	}
	
	public int insertUserFormInfo(Map<String, Object> param) throws Exception {
		int rsInt = insert("smhc.web.tg.seniordtlsinfo.insertUserFormInfo", param);
		return rsInt;
	}	

	public Map<String, Object> mngtUserInfoDetailChk(Map<String, Object> param) throws Exception {
		return selectOne("smhc.web.tg.seniordtlsinfo.mngtUserInfoDetailChk",param);
	}

	public Map<String, Object> setUserInfoDetail(Map<String, Object> param) throws Exception {
		return selectOne("smhc.web.tg.seniordtlsinfo.setUserInfoDetail",param);
	}

	public Map<String, Object> setMultipleTimes(Map<String, Object> param) {
		return selectOne("smhc.web.tg.seniordtlsinfo.setMultipleTimes",param);
	}

	public Map<String, Object> selectUserInfoDetail(Map<String, Object> param) throws Exception {
		return selectOne("smhc.web.tg.seniordtlsinfo.selectUserInfoDetail",param);
	}
	

}
