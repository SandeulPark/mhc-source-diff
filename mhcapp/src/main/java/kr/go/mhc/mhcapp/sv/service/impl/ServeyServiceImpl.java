package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.*;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.sv.service.ServeyService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mhcapp.sv.ServeyService")
public class ServeyServiceImpl extends EgovAbstractServiceImpl implements ServeyService{
	
	@Resource(name="mhcapp.sv.ServeyDAO")
    private ServeyDAO serveyDAO;

	/**
	 * 설문 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyList(param);
	}
	
	/**
	 * 설문 코드 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyCodeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyCodeList(param);
	}
	
	/**
	 * 설문 마지막 코드 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyLstQnaCD(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyLstQnaCD(param);
	}

	/**
	 * 설문지 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyAwrInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyAwrInsert(param);
	}
	
	/**
	 * 설문지 마스터 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyMasterInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyMasterInsert(param);
	}
	
	/**
	 * 설문지 영양 체크리스트 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int serveyAnswrUpdate(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.serveyAnswrUpdate(param);
	}
	
	/**
	 * 설문지 마스터 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public int updateServeyMaster(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.updateServeyMaster(param);
	}
	
	/**
	 * 설문 답변 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectServeyAnwerList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return serveyDAO.selectServeyAnwerList(param);
	}

	/**
	 * 설문지 답변 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void serveyAwrDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		serveyDAO.serveyAwrDel(param);
	}

	@Override
	public List<Map<String, Object>> serveyResearchAnswrList(Map<String, Object> param) throws Exception {
		return serveyDAO.serveyResearchAnswrList(param);
	}

	@Override
	public List<Map<String, Object>> serveyResearchAnswrMastr(Map<String, Object> param) throws Exception {
		return serveyDAO.serveyResearchAnswrMastr(param);
	}

	@Override
	public int serveyResearchAnswrInsert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = 0;

		serveyDAO.serveyResearchAnswrMastrInsert(param);
		serveyDAO.serveyResearchAnswrDel(param);

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

					serveyDAO.serveyResearchAnswrInsert(answrMap);

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
}
