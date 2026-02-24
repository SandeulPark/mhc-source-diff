package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("web.sv.VisitSchResrvtMngtServiceDAO")
public class VisitSchResrvtMngtServiceDAO extends EgovAbstractMapper{

	public List<Map<String, Object>> getVisitSchResrvtMonthList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitSchResrvtMonthList", param);
		return rsList;
	}

	public List<Map<String, Object>> selectVisitResrvtRMK(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitResrvtRMK", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getVisitSchResrvtDayList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitSchResrvtDayList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getVisitSchResrvtTimeList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitSchResrvtTimeList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getBundleSetTimeList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectBundleSetTimeList", param);
		return rsList;
	}

	public List<Map<String, Object>> getBundleSetPlaceList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectBundleSetPlaceList", param);
		return rsList;
	}

	public int saveVisitSchBuldleInfo(Map<String, Object> param) {
		int rsInt = 0;
		update("mhc.web.sv.visitschresrvtmngt.saveVisitSchBuldleInfo", param);
		return rsInt;
	}
	
	public List<Map<String, Object>> getVisitResrvtTrgterAddList(Map<String, Object> param) throws Exception {
		
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitResrvtTrgterAddList", param);
		return rsList;
	}		
	
	public void saveVisitSchResrvtCancel(Map<String, Object> param) throws Exception {
		String[] trgterId     = param.get("trgterId").toString().split(",");	
		String[] trgterCnslNo = param.get("trgterCnslNo").toString().split(",");			

		
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			trgterInfoMap.put("SESS_USER_ID", 	param.get("SESS_USER_ID"));
			trgterInfoMap.put("SESS_ORG_CD",  	param.get("SESS_ORG_CD"));					
			trgterInfoMap.put("SEL_DATE", 		param.get("SEL_DATE"));
			trgterInfoMap.put("SEL_TM",   		param.get("SEL_TM"));
			trgterInfoMap.put("USER_ID",  		trgterId[i]);
			trgterInfoMap.put("CNSL_NO",  		trgterCnslNo[i]);

			update("mhc.web.sv.visitschresrvtmngt.saveVisitSchResrvtCancel", trgterInfoMap);

		}		
		
	}
	
	public int saveVisitSchSetInfo(Map<String, Object> param) {
		int rsInt = 0;

		if(!param.get("new").toString().equals("new")){

			String[] timeInfo = param.get("SET_TIME_STR").toString().split(",");
			String[] reqCnt = param.get("SET_CNT_STR").toString().split(",");
			String[] weekCdStr = param.get("WEEK_CD_STR").toString().split(",");

			int timeCnt = Integer.parseInt(param.get("TIME_CNT").toString());

			Map<String, Object> setInfo = new HashMap<String, Object>();
			for(int i=0; i < timeCnt; i++){
				setInfo.put("SESS_USER_ID", 	param.get("SESS_USER_ID"));
				setInfo.put("SESS_ORG_CD",   	param.get("SESS_ORG_CD"));
				setInfo.put("SEL_DATE", 		param.get("SEL_DATE"));
				setInfo.put("SEL_TM",   		param.get("SEL_TM"));
				setInfo.put("VISIT_TM_CD",    	timeInfo[i]);
				setInfo.put("VISIT_SET_CNT",  	reqCnt[i]);
				setInfo.put("VISIT_WEEK_CD",  	weekCdStr[i]);

				update("mhc.web.sv.visitschresrvtmngt.saveVisitSchSetInfo", setInfo);

			}
		} else {
			update("mhc.web.sv.visitschresrvtmngt.saveVisitSchSetInfo", param);
		}

		return rsInt;
	}

	public int saveVisitSchDtls(Map<String, Object> param) throws Exception {
		int rsInt = 0;
		rsInt = update("mhc.web.sv.visitschresrvtmngt.saveVisitSchDtls", param);

		return rsInt;
	}


	
	public List<Map<String, Object>> getVisitSchResrvtExcel(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitSchResrvtExcel", param);
		return rsList;
	}
	public int saveVisitResrvtTrgter(Map<String, Object> param) throws Exception {
		int rsInt = 0;
		
		String[] trgterId     = param.get("trgterId").toString().split(",");	
		String[] trgterCnslNo = param.get("trgterCnslNo").toString().split(",");			

		
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			trgterInfoMap.put("SESS_USER_ID", 	param.get("SESS_USER_ID"));
			trgterInfoMap.put("SESS_ORG_CD",  	param.get("SESS_ORG_CD"));				
			trgterInfoMap.put("SEL_DATE", 		param.get("SEL_DATE"));
			trgterInfoMap.put("SEL_TM",   		param.get("SEL_TM"));	
			trgterInfoMap.put("SEL_PLACE",   	param.get("SEL_PLACE"));
			trgterInfoMap.put("USER_ID",  		trgterId[i]);
			trgterInfoMap.put("CNSL_NO",  		trgterCnslNo[i]);			
			

			update("mhc.web.sv.visitschresrvtmngt.saveVisitResrvtTrgter", trgterInfoMap);

		}
		return rsInt;
		
	}	
	
	public int insertVisitResrvtTrgter(Map<String, Object> param) throws Exception {
		int rsInt = 0;
		
		String[] addTrgterInfo = param.get("addTrgterInfo").toString().split(",");	
		int addTrgterCnt = Integer.parseInt(param.get("addTrgterCnt").toString());

		
		try{
			int j=0;
			Map<String, Object> addMap = new HashMap<String, Object>();
			for(int i=0; i < addTrgterCnt; i++){
				// TODO 시간 코드 겹치는지 확인 필요
				String selTm = param.get("SEL_TM").toString();


				addMap.clear();
				addMap.put("SEL_DATE", 		param.get("SEL_DATE").toString());
				addMap.put("SEL_TM",   		param.get("SEL_TM").toString());			
				addMap.put("SESS_ORG_CD",   param.get("SESS_ORG_CD").toString());		
				addMap.put("SESS_USER_ID",  param.get("SESS_USER_ID").toString());						
				addMap.put("SEL_PLACE",  param.get("SEL_PLACE").toString());

				addMap.put("USER_ID",  addTrgterInfo[i+j]);
				addMap.put("CNSL_NO",  addTrgterInfo[i+j+1]);
				addMap.put("CNSL_SN",  addTrgterInfo[i+j+2]);
				
				rsInt += insert("mhc.web.sv.visitschresrvtmngt.insertVisitResrvtTrgter", addMap);

				j+=2;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rsInt;
		
	}


	
	public List<Map<String, Object>> getVisitDeTmList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.getVisitDeTmList", param);
		return rsList;
	}
	
	public void saveVisitSchResrvtChange(Map<String, Object> param) throws Exception {
		String[] trgterId     = param.get("trgterId").toString().split(",");	
		String[] trgterCnslNo = param.get("trgterCnslNo").toString().split(",");			

		System.out.println("param ::::::: " + param);
		
		
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			trgterInfoMap.put("SEL_DATE",     param.get("SEL_DATE"));
			trgterInfoMap.put("SEL_TM",       param.get("SEL_TM"));
			trgterInfoMap.put("SEL_PLACE",       param.get("SEL_PLACE"));
			trgterInfoMap.put("USER_ID",      trgterId[i]);
			trgterInfoMap.put("CNSL_NO",      trgterCnslNo[i]);
			trgterInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			trgterInfoMap.put("SESS_ORG_CD",  param.get("SESS_ORG_CD"));

			update("mhc.web.sv.visitschresrvtmngt.saveVisitResrvtTrgter", trgterInfoMap);

		}		
		
	}
	
	
	public List<Map<String, Object>> getVisitSchResrvtChangeChk(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();			
		
		String[] trgterId     = param.get("trgterId").toString().split(",");	
		String[] trgterCnslNo = param.get("trgterCnslNo").toString().split(",");	
		
		int trgterCnt = Integer.parseInt(param.get("trgterCnt").toString());
		Map<String, Object> trgterInfoMap = new HashMap<String, Object>();
		for(int i=0; i < trgterCnt; i++){
			
			Map<String, Object> chkMap = new HashMap<String,Object>();
			
			trgterInfoMap.put("SEL_DATE",     param.get("SEL_DATE"));
			trgterInfoMap.put("USER_ID",      trgterId[i]);
			trgterInfoMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			trgterInfoMap.put("SESS_ORG_CD",  param.get("SESS_ORG_CD"));
			
			//방문예약 구분이 초기상담일 경우, 일정변경에 따른 유효성 체크 제외
			if(trgterCnslNo[i] != "1"){					
				chkMap = selectOne("mhc.web.sv.visitschresrvtmngt.getChkVisitResrvtTrgter", trgterInfoMap);
			}
			if(chkMap != null){
				rsList.add(chkMap);
			}
		}
		return rsList;
	}	
	
	
	public List<Map<String, Object>> getVisitMidWeekList() throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.getVisitMidWeekList");
		return rsList;		
	}

	public Map<String, String> selectMyWeek(Map<String, Object> param)
			throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.sv.visitschresrvtmngt.selectMyWeek", param);	
		return rsMap;  
	}
	
	public List<Map<String, String>> selectVisitResrvtDeTm(Map<String, Object> param)
			throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitResrvtDeTm", param);	
		return rsList;  
	}

	public List<Map<String, Object>> getVisitPlace(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectVisitPlace", param);
		return rsList;
	}

	public Map<String, Object> getNewVisitPlaceSn(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.visitschresrvtmngt.selectNewVisitPlaceSn");
		return rsMap;
	}

	public int saveVisitPlace(Map<String, Object> param) throws Exception {
		int rs = update("mhc.web.sv.visitschresrvtmngt.saveVisitPlace", param);
		return rs;
	}

	public int updateVisitPlaceUseYN(Map<String, Object> param) throws Exception {
		int rs = update("mhc.web.sv.visitschresrvtmngt.updateVisitPlaceUseYN", param);
		return rs;
	}

	public List<Map<String, Object>> selectResrvtVisitPlace(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.visitschresrvtmngt.selectResrvtVisitPlace", param);
		return rsList;
	}

	public Map<String, Object> selectResrvtUserCount(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.visitschresrvtmngt.selectResrvtUserCount", param);
		return rsMap;
	}

	public Map<String, Object>  selectResrvtCountByVisitDe(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.visitschresrvtmngt.selectResrvtCountByVisitDe", param);
		return rsMap;
	}
}
