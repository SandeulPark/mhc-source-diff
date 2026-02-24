package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.07		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface CommunityService {

	int selectCommunityMyTabListCount(Map<String, Object> param) throws Exception;

	List<Map<String, String>> selectCommunityMyTabList(Map<String, Object> param) throws Exception;

	int selectCommunityOtherTabListCount(Map<String, Object> param) throws Exception;

	List<Map<String, String>> selectCommunityOtherTabList(Map<String, Object> param) throws Exception;

	int communityBoardReg(Map<String, Object> param) throws Exception;

	Map<String, Object> selectBoardData(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectimgBoardData(Map<String, Object> param) throws Exception;

	int communityBoardEdit(Map<String, Object> param) throws Exception;

	int deleteBoard(Map<String, Object> param) throws Exception;

	Map<String, Object> selectCommunityDetailBoardData(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectCommunityDetailBoardImg(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectCommunityDetailCmmnt(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectCommunityDetailLike(Map<String, Object> param) throws Exception;

	int insertCommunitySingleCmmnt(Map<String, Object> param) throws Exception;

	int updateCommunitySingleCmmnt(Map<String, Object> param) throws Exception;

	int insertCommunityMultiCmmnt(Map<String, Object> param) throws Exception;

	int defAttchFile(Map<String, Object> param) throws Exception;

	int getBoardSn(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> selectCommunityDetailBoardVideo(Map<String, Object> param) throws Exception;

}
