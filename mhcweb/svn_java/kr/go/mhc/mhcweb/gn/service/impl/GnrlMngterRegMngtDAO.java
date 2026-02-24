package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;


@Repository("web.gn.GnrlMngterRegMngtDAO")
public class GnrlMngterRegMngtDAO extends DMultiEgovAbstractMapper{

	/**
	 * 등록된 관리자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getRegMngterList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.selectRegMngterList", param);
		return rsList;
	}

	/**
	 * 관리자 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception
	 */
	public int saveMngter(Map<String, Object> param) throws Exception {
		int rsInt;

		System.out.println("param ::: " + param);

		// 신규
		if(param.get("USER_ID").equals("")){
			Map<String,String> rsMap = selectOne("mhc.web.gn.gnrlmngterregmngt.getNewUserId");
			param.put("USER_ID", rsMap.get("USER_ID"));
			rsInt = insert("mhc.web.gn.gnrlmngterregmngt.insertUserInfo", param);
			  if(param.get("CHECK").equals("A")){                                   //20191231양현우 추가
			      rsInt += insert("mhc.web.gn.gnrlmngterregmngt.insertDigiSign", param);
			}
		}else{ // 수정
			Map<String, Object> rsMap = selectOne("common.cmmn.selectPwChangeChk", param);
			param.put("PW_CNT", rsMap.get("PW_CNT"));
			rsInt = update("mhc.web.gn.gnrlmngterregmngt.updateUserInfo", param);
			insert("mhc.web.gn.gnrlmngterregmngt.insertManagerInfoHist", param);

			if(param.get("changeAck").equals("Y")|| param.get("changeAppYN").equals("N")){     //20191231양현우 추가
				rsInt += insert("mhc.web.gn.gnrlmngterregmngt.insertDigiSign", param);
			}
		}
		if(param.get("changeAck").equals("Y") || param.get("changeAppYN").equals("N")){
			rsInt += update("mhc.web.gn.gnrlmngterregmngt.saveManagerInfo", param);

		}else{
			rsInt += update("mhc.web.gn.gnrlmngterregmngt.saveManagerInfo2", param);
		}
		rsInt += update("mhc.web.gn.gnrlmngterregmngt.saveManagerAuth", param);


		return rsInt;
	}

	/**
	 * 관리자 중복 체크
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlmngterregmngt.selectManagerDuplicationCnt",param);
		return rsMap;
	}

	/**
	 * 관리자 등록 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception
	 */
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.gn.gnrlmngterregmngt.updateApprovalYn", param);
		return rsInt;
	}

	/**
	 * 관리자 공인인증서1 제한 해제
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception
	 */
	public int updatedn1Use(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.gn.gnrlmngterregmngt.updatedn1Use", param);
		return rsInt;
	}

	/**
	 * 관리자 공인인증서2 제한 해제
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception
	 */
	public int updatedn2Use(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.gn.gnrlmngterregmngt.updatedn2Use", param);
		return rsInt;
	}
	/**
	 * 관리자 팝업 데이터 조회 조회
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception
	 */

	public Map<String, Object> getdigiSignPopUp(Map<String, Object> param) throws Exception {
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlmngterregmngt.selectDignSigninfo", param);
		return rsMap;
	}

	/**
	  * 권한 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> permissionsList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.permissionsList", param);
		return rsList;
	}

	/**
	  * 권한 그룹 목록 조회
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> userAuthList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.userAuthList", param);
		return rsList;
	}

	/**
	  * 권한 그룹 목록  상세 조회
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> permissionsTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.permissionsTrgterList", param);
		return rsList;
	}

	/**
	  * 권한 메뉴 조회
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> perMenuList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.perMenuList", param);
		return rsList;
	}

	/**
	  * 권한 메뉴 리스트
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> selectPerMenuList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.selectPerMenuList", param);
		return rsList;
	}

	/**
	  * PK조회
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> selectAuthKey(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.selectAuthKey", param);
		return rsList;
	}

	/**
	  * 권한 메뉴 카운트
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> selectPerMenuCnt(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.selectPerMenuCnt", param);
		return rsList;
	}

	/**
	  *  권한  속성 등록
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void perMenuGInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmngterregmngt.perMenuGInsert", param);
	}

	/**
	  *  권한목록등록
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void authListInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmngterregmngt.authListInsert", param);
	}

	/**
	  *  권한목록수정
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void authListUpdate(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmngterregmngt.authListUpdate", param);
	}

	/**
	  *  권한목록수정
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void authListUpdate2(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmngterregmngt.authListUpdate2", param);
	}

	/**
	  *  권한목록삭제
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void authDelete(Map<String, Object> param) throws Exception {
		delete("mhc.web.gn.gnrlmngterregmngt.authDelete", param);
	}

	/**
	  *  권한정보삭제
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void authList(Map<String, Object> param) throws Exception {
		delete("mhc.web.gn.gnrlmngterregmngt.authList", param);
	}

	/**
	  *  유저권한 삭제
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void userAuthDelete(Map<String, Object> param) throws Exception {
		delete("mhc.web.gn.gnrlmngterregmngt.userAuthDelete", param);
	}

	/**
	  *  관리자 권한 등록
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public void orgAuthUsersInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.gn.gnrlmngterregmngt.orgAuthUsersInsert", param);
	}

	/**
	  * 유저 권한 체크
	  *	@param param 저장 정보
	  * @return
	  * @throws Exception
	  */

	public List<Map<String, String>> userPerCheck(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.userPerCheck", param);
		return rsList;
	}

	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.selectRegMngterList2", param);
		return rsList;
	}

	public List<Map<String, Object>> slectErrReport(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.slectErrReport", param);
		return rsList;
	}

	public List<Map<String, Object>> checkRegUser(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.gnrlmngterregmngt.checkRegUser", param);
		return rsList;
	}
}
