package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sm.service.ServeyReSearchMngtService;

/**
 * @Class Name :HealthDisValMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 게시판에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.ServeyReSearchMngtService")
public class ServeyReSearchMngtServiceImpl extends EgovAbstractServiceImpl implements ServeyReSearchMngtService {

	@Resource(name="web.sm.ServeyReSearchMngtDAO")
	private ServeyReSearchMngtDAO serveyReSearchMngtDAO;

	/**
	 * 설문조사 화면 설문개요 정보 목록 조회
	 */
	@Override
	public List<Map<String, Object>> serveyResearchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchList(param);
	}
	
	@Override
	public List<Map<String, Object>> serveyEndList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyEndList(param);
	}
	
	@Override
	public List<Map<String, Object>> serveyAppMeasureList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyAppMeasureList(param);
	}
	
	
	/**
	 * 설문개요 상세 정보 조회
	 */
	@Override
	public Map<String, Object> serveyResearchSumryDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchSumryDtls(param);
	}

	/**
	 * 설문개요 상세 기관 목록 조회
	 */
	@Override
	public List<Map<String, Object>> serveyResearchSumryList( Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchSumryList(param);
	}
	
	/**
	 * 설문개요 기관상세
	 */
	@Override
	public List<Map<String, Object>> serveyRegOrgList( Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyRegOrgList(param);
	}
	
	/**
	 * 설문항목 목록 조회
	 */
	@Override
	public List<Map<String, Object>> serveyResearchItemList( Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchItemList(param);
	}
	
	/**
	 * 설문개요 저장
	 */
	@Override
	public String serveyResearchInsert(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchInsert(param);
	}

	/**
	 * 설문조사 기관지정 조회
	 */
	@Override
	public void selectServeyOrgInsert(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		serveyReSearchMngtDAO.selectServeyOrgInsert(param);
	}
	
	/**
	 * 설문항목 삭제
	 */
	@Override
	public int serveyResearchItemDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchItemDel(param);
	}
	
	/**
	 * 설문대상 기관 삭제
	 */
	@Override
	public void deleteRegOrgList(Map<String, Object> param) throws Exception {
		serveyReSearchMngtDAO.deleteRegOrgList(param);
	}
	
	/**
	 * 설문조사 저장
	 */
	@Override
	public String serveyResearchRegit(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		int rsInt = 0;
		
		String serveyContSumry = StringUtil.nvl(String.valueOf(param.get("SERVEY_CONT_SUMRY")));
		
		//System.out.println("before replace ===> " + serveyContSumry);
		
		if(serveyContSumry != "") {
			//serveyContSumry = serveyContSumry.replace("\r\n", "<br>");
			
			//System.out.println("replace ===> " + serveyContSumry);
			
			param.put("SERVEY_CONT_SUMRY", serveyContSumry);
		}
		
		
		//설문 mastr insert
		String serveySn = serveyReSearchMngtDAO.serveyResearchInsert(param);
		serveyReSearchMngtDAO.deleteRegOrgList(param);
		String orgCd = null;
		param.put("SERVEY_SN", serveySn);
		param.put("SERVEY_CD_SN", "");
		
		if(param.get("orgList") != null) {			
			orgCd = (String) param.get("orgList");
			
			String orgList [] =orgCd.split(",");
			if(orgList.length>0) {
				for(int i=0; i<orgList.length; i++) {
					param.put("ORG_CD", orgList[i]);
					param.put("SERVEY_SN", serveySn);									
 					serveyReSearchMngtDAO.selectServeyOrgInsert(param);					
				}
			}
		}
		
		
		
		//param.get("test",asdasf) > 0 {
			
		/* serveyReSearchMngtDAO.insertOrg(param); */
		//}
		
		//설문항목 전체 delete
		serveyReSearchMngtDAO.serveyResearchItemDel(param);
		
		//설문항목 전체 insert
		String itemStr = (String)param.get("itemList");
		itemStr = itemStr.replaceAll("&quot;", "\"");
		ObjectMapper objMapper = new ObjectMapper();
		try{
			List<Map<String, Object>> itemList = objMapper.readValue(itemStr, new TypeReference<List<Map<String, Object>>>(){});
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("SERVEY_SN", param.get("SERVEY_SN"));
			itemMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			for(int i=0;i<itemList.size();i++){
				itemMap.put("SERVEY_CD_SN", 	String.format("%05d",i+1));
				itemMap.put("SERVEY_LVL", 		itemList.get(i).get("SERVEY_LVL") == null ? "" : itemList.get(i).get("SERVEY_LVL").toString());
				itemMap.put("SERVEY_CONT", 		itemList.get(i).get("SERVEY_CONT") == null ? "" : itemList.get(i).get("SERVEY_CONT").toString());
				itemMap.put("SERVEY_ANSWR_CLF", itemList.get(i).get("SERVEY_ANSWR_CLF") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_CLF").toString());
				itemMap.put("SERVEY_ANSWR_1", 	itemList.get(i).get("SERVEY_ANSWR_1") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_1").toString());
				itemMap.put("SERVEY_ANSWR_2", 	itemList.get(i).get("SERVEY_ANSWR_2") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_2").toString());
				itemMap.put("SERVEY_ANSWR_3", 	itemList.get(i).get("SERVEY_ANSWR_3") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_3").toString());
				itemMap.put("SERVEY_ANSWR_4", 	itemList.get(i).get("SERVEY_ANSWR_4") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_4").toString());
				itemMap.put("SERVEY_ANSWR_5", 	itemList.get(i).get("SERVEY_ANSWR_5") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_5").toString());
				itemMap.put("SERVEY_ANSWR_6", 	itemList.get(i).get("SERVEY_ANSWR_6") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_6").toString());
				itemMap.put("SERVEY_ANSWR_7", 	itemList.get(i).get("SERVEY_ANSWR_7") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_7").toString());
				itemMap.put("SERVEY_ANSWR_8", 	itemList.get(i).get("SERVEY_ANSWR_8") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_8").toString());
				itemMap.put("SERVEY_ANSWR_9", 	itemList.get(i).get("SERVEY_ANSWR_9") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_9").toString());
				itemMap.put("SERVEY_ANSWR_10", 	itemList.get(i).get("SERVEY_ANSWR_10") == null ? "" : itemList.get(i).get("SERVEY_ANSWR_10").toString());
				itemMap.put("SERVEY_NO", 		itemList.get(i).get("SERVEY_NO") == null ? "" : itemList.get(i).get("SERVEY_NO").toString());
				itemMap.put("SORT_ORD", 		itemList.get(i).get("SORT_ORD") == null ? "" : itemList.get(i).get("SORT_ORD").toString());
				itemMap.put("USE_YN", 			itemList.get(i).get("USE_YN") == null ? "" : itemList.get(i).get("USE_YN").toString());
				itemMap.put("UPPER_SERVEY_NO", 	itemList.get(i).get("UPPER_SERVEY_NO") == null ? "" : itemList.get(i).get("UPPER_SERVEY_NO").toString());
				itemMap.put("REQ_YN", 	itemList.get(i).get("REQ_YN") == null ? "" : itemList.get(i).get("REQ_YN").toString());				
				
				serveyReSearchMngtDAO.serveyResearchItemAdd(itemMap);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return serveySn;
	}

	/**
	 * 설문답변 정보 조회
	 */
	@Override
	public Map<String, Object> serveyResearchAnswrMastr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchAnswrMastr(param);
	}

	/**
	 * 설문답변 상세 목록 조회
	 */
	@Override
	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyResearchAnswrList(param);
	}


	/**
	 * 설문항목 답변 저장
	 */
	@Override
	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = 0;
		
		serveyReSearchMngtDAO.serveyResearchAnswrMastrInsert(param);
		serveyReSearchMngtDAO.serveyResearchAnswrDel(param);

		try{
			Set set = (Set) param.entrySet();
			Iterator iterator = (Iterator) set.iterator();
			Iterator iterator2 = (Iterator) set.iterator();
			
			Map<String, Object> answrMap = new HashMap<String, Object>();
			while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry) iterator.next();
				if(!entry.getKey().toString().matches(".*[0-9].*")){
					answrMap.put(entry.getKey().toString(), entry.getValue().toString());
				}
			}
			
			while(iterator2.hasNext()){
				Map.Entry entry = (Map.Entry) iterator2.next();
				
				if(entry.getKey().toString().matches(".*[0-9].*")){
					answrMap.put("SERVEY_CD_SN", entry.getKey().toString());
					if(entry.toString().length()<11 || !entry.toString().contains("dup")) {
						answrMap.put("ANSWR", entry.getValue().toString());
					}else {//중복
						if(entry.getValue().getClass().getName() == "[Ljava.lang.String;") {
							String [] temp = (String[]) entry.getValue();
							for(int i=0; i<temp.length; i++) {
								String result = (String)answrMap.get("SERVEY_CD_SN");
								if(result.contains("dup")) {
									answrMap.put("SERVEY_CD_SN",result.substring(0,result.indexOf("_dup")));									
								}else {
									answrMap.put("SERVEY_CD_SN",result);
								}
								answrMap.put("SERVEY_ANSWR_DUP"+temp[i], 'o');							
							}
						}else {
							String result = (String)answrMap.get("SERVEY_CD_SN");
							answrMap.put("SERVEY_CD_SN",result.substring(0,result.indexOf("_dup")));
							answrMap.put("SERVEY_ANSWR_DUP"+entry.getValue(), 'o');	
						}											
						
					}
					
					serveyReSearchMngtDAO.serveyResearchAnswrInsert(answrMap);
					
					answrMap.remove("SERVEY_ANSWR_DUP1");
					answrMap.remove("SERVEY_ANSWR_DUP2");
					answrMap.remove("SERVEY_ANSWR_DUP3");
					answrMap.remove("SERVEY_ANSWR_DUP4");
					answrMap.remove("SERVEY_ANSWR_DUP5");
					answrMap.remove("SERVEY_ANSWR_DUP6");
					answrMap.remove("SERVEY_ANSWR_DUP7");
					answrMap.remove("SERVEY_ANSWR_DUP8");
					answrMap.remove("SERVEY_ANSWR_DUP9");
					answrMap.remove("SERVEY_ANSWR_DUP10");
					answrMap.remove("ANSWR");
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rsInt;
	}

	@Override
	public List<Map<String, Object>> serveyAnswrStatsList(Map<String, Object> param) throws Exception {		
		return serveyReSearchMngtDAO.serveyAnswrStatsList(param);
	}
	
	@Override
	public List<Map<String, Object>> serveyAppDtlsMeasureList(Map<String, Object> param) throws Exception {		
		return serveyReSearchMngtDAO.serveyAppDtlsMeasureList(param);
	}

	@Override
	public List<Map<String, Object>> regOrgList(Map<String, Object> param) throws Exception {
		return serveyReSearchMngtDAO.regOrgList(param);
	}


	/**
	 * 대상자용 설문조사 화면 설문개요 정보 목록 조회
	 */
	@Override
	public List<Map<String, Object>> serveyTrgtResearchList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyTrgtResearchList(param);
	}


	/**
	 * 대상자용 설문개요 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> serveyTrgtReSearchMngtDtls(Map<String, Object> param) throws Exception {
		return serveyReSearchMngtDAO.serveyResearchSumryDtls(param);
	}

	/**
	 * 설문 주관식답변 상세 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> serveyAnswrInpStatsList(Map<String, Object> param) throws Exception {
		return serveyReSearchMngtDAO.serveyAnswrInpStatsList(param);
	}


	/**
	 * 설문개요 상세 정보 조회
	 */
	@Override
	public List<Map<String, Object>> serveyTrgtResearchSumryDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.serveyTrgtResearchSumryDtls(param);
	}

	/**
	 * 대상자용 설문조사 설문완료 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> trgtServeyEndList(Map<String, Object> param) throws Exception {
		return serveyReSearchMngtDAO.trgtServeyEndList(param);
	}

	@Override
	public String serveyResearchAnswrClf(Map<String, Object> param) throws Exception {		
		return serveyReSearchMngtDAO.serveyResearchAnswrClf(param);
	}

	@Override
	public int regOrgListCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.regOrgListCnt(param);
	}

	@Override
	public Map<String, Object> getServeyUserCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.getServeyUserCnt(param);
	}

	@Override
	public Map<String, Object> getTrgtServeyUserCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyReSearchMngtDAO.getTrgtServeyUserCnt(param);
	}

}
