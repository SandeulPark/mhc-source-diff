
package kr.or.khealth.smhc.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;








import kr.or.khealth.smhc.common.service.CommonService;
import kr.or.khealth.smhc.common.util.AddTag;
import kr.or.khealth.smhc.common.util.FileUtil;
import kr.or.khealth.smhc.common.util.PaginationUtil;
import kr.or.khealth.smhc.common.util.PushMessageUtil;
import kr.or.khealth.smhc.common.util.StringUtil;

import org.apache.log4j.Logger;

public abstract class DMultiActionController{
	
	protected final  Logger LOG = Logger.getLogger(this.getClass()); 
	protected String MESSAGE_NAME = "cmmnMsg";

	@Resource(name="common.cmmnService")
	protected CommonService cmmnService;

	@Resource(name = "msg") 
	private DMessage msg;   
	
	@Resource(name = "pagination")
	protected PaginationUtil pagination;
	
	@Resource(name = "pagination2")
	protected PaginationUtil pagination2;   
	
	@Resource(name = "cookieUtil")
	protected DCookieUtil cookieUtil;    
	
	@Resource(name = "fileUtil")
	protected FileUtil fileUtil; 
	
	@Resource(name = "blobView")
	protected BlobView blobView; 
	
	@Resource(name = "excelView")
	protected ExcelView excelView; 
	
	@Resource(name = "pushMessageUtil")
	protected PushMessageUtil pushMessageUtil;
	
	protected AddTag addTag;
	
	LoginManager loginManager = LoginManager.getInstance();
	
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{		
		addTag = new AddTag(req.getParameterMap());  
		cookieUtil.setCookies(req); 
		String name = "";
	    Map<String,Object> result = new HashMap<String,Object>(); 
		for(Enumeration names = req.getParameterNames(); names.hasMoreElements(); ){          
    		name = (String)names.nextElement();
    		if(req.getParameterValues(name).length>1 || name.indexOf("arr_")>-1){ 
    			result.put(name,req.getParameterValues(name));  
    		}else{
    			result.put(name,req.getParameter(name));       
    		} 	          		 
 	    } 
		result.putAll(getSessionInfo(req));		
		result.put("SESS_ISMOBILE",	StringUtil.nvl(String.valueOf(req.getSession().getAttribute("SESS_ISMOBILE"))));
		LOG.info("requestMap::"+result);
		
		// 접속 정보 저장
		insertCmmnLogInfo(req,result);
		
		return result;  
	}
	
	
	public void insertCmmnLogInfo(HttpServletRequest req, Map<String,Object> result){
		if(req.getRequestURI().indexOf(".do") > 0 && "/login/loginPage.do|/cmmn/selectCmmnMenu.do|/cmmn/selectCmmnCd.do|/login/loginPageOld.do".indexOf(req.getRequestURI()) < 0){
			String mapStr = "";
			for (String names : result.keySet()) {
				mapStr += ("".equals(mapStr) ? "" : ", ") + names + "=" + StringUtil.nvl(String.valueOf(result.get(names)));
			}

			System.out.println("req.getRequestURI() == " + req.getRequestURI());
			System.out.println("req.getRequestURL() == " + req.getRequestURL().toString());
			System.out.println("req.getQueryString() == " + req.getQueryString());
			System.out.println("req.getMethod() == " + req.getMethod());
			System.out.println("req.getRemoteAddr() == " + req.getRemoteAddr());
			
			System.out.println("req.getHeader(X-FORWARDED-FOR))== " + req.getHeader("X-FORWARDED-FOR"));			
			

			System.out.println("mapStr == " + mapStr);
			
			
			String ip = req.getRemoteAddr();

			try {
				HashMap<String,Object> param = new HashMap<String,Object>();
				Map<String, String> menuMap = new HashMap<String,String>(); 
				String menuCd = (String) result.get("menuCd")==null? (req.getSession().getAttribute("menuCd")==null?"": (String) req.getSession().getAttribute("menuCd")) : (String) result.get("menuCd");
				String pMenuUrl = "";
				String menuUrl  = "";
				
				//이력업무 관련 항목 추가(20191016)
				String excelUrl = "Excel";
				String infoYn = "";
				String schCon = "";
				String schParam = "";
				String authType = "";

				
				if("".equals(menuCd)){
					param.put("CONNECT_URL", req.getRequestURI()); 
				}else if(!"".equals(menuCd) || !menuCd.equals(null)){
					result.put("menuCd", menuCd);
					menuMap = cmmnService.selectCmmnMenuInfo(result);
					pMenuUrl = result.get("MENU_URL") == null ? "" : (String) result.get("MENU_URL");	//파라미터 url
					menuUrl = menuMap.get("MENU_URL") == null ? "" : menuMap.get("MENU_URL");			//메뉴코드 조회 url
					infoYn  = menuMap.get("INFO_YN") == null ? ""  : menuMap.get("INFO_YN");			//개인정보 포함 여부
					
					if(!req.getRequestURI().equals(menuUrl)){
						menuUrl = pMenuUrl != "" ? pMenuUrl : ("/pageNavi.do" == req.getRequestURI().trim() ? menuUrl : req.getRequestURI());
					}
				}
				
				param.put("CONNECT_URL", menuUrl); 					
				param.put("USER_ID", (String)result.get("SESS_USER_ID"));
				param.put("MENU_CD", menuCd);
				param.put("PROGRAM_PATH", menuMap.get("PROGRAM_PATH")==null?"":menuMap.get("PROGRAM_PATH"));	
				param.put("PROGRAM_ID", menuMap.get("PROGRAM_ID")==null?"":menuMap.get("PROGRAM_ID"));	
				param.put("CONNECT_IP", ip);


				StringBuffer sb = new StringBuffer();
				for (String item : result.keySet()) {					
					sb.append(item + "=" + result.get(item) + ", ");
					
					if(("USER_ID".equals(item) || "SVC_MNGT_NO".equals(item) || "CNSL_SN".equals(item) || "PRE_TRGTER_NO".equals(item)) && result.get(item) != null){
						schCon   = item.toString();
						schParam = result.get(item).toString();						
					}
				}

				 authType = (String)result.get("SESS_AUTH_TYPE");
				
				//2019.05.14 유준영 로그 저장시 CLOB데이터 파람으로 넘어로경우애러나서 기존 3900을 1800자까지만 자르게 수정
				//2019.10.16 오샘이 로그 저장시 CLOB데이터 파람으로 넘어로경우애러나서 기존 3900을 1500자까지만 자르게 수정 
				param.put("REQ_PARAM", sb.toString().length() > 3900 ? sb.toString().substring(0, 1500) : sb.toString()); 

				cmmnService.insertCmmnLogInfo(param);

				if(!"HLTH100".equals(authType) && !"KHPF099".equals(authType)){					
					if("Y".equals(infoYn) && !"".equals(schCon) && schCon != null && !"".equals(schParam) && schParam != null){
						param.put("SCH_CON", schCon);
						param.put("SCH_PARAM", schParam);
						cmmnService.insertCmmnLogPerSchInfo(param);										
					}
					
					if("Y".equals(infoYn) && param.get("CONNECT_URL").toString().contains(excelUrl)){
						param.put("SCH_CON", "EXCEL");
						param.put("SCH_PARAM", "");
						param.put("SCH_TYPE", "02");
						cmmnService.insertCmmnLogPerSchInfo(param);					
					}
				}	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public String getMsg(String code){
		return msg.getMsg(code);
	}
	
	public String getMsg(String code, Object[] args){ 
		return msg.getMsg(code, args);    
	}
	
	public String getLogMsg(String code){
		return msg.getLogMsg(code);    
	}
	
	public void setSessionInfo(HttpServletRequest req, HttpSession session, Map<String,String> param){
		if(!"".equals(param.get("USER_ID"))){
			//현재 세션 저장
			session.setAttribute(param.get("USER_ID"), loginManager);
			String sessGb = req.getParameter("sessGb").toString();
			if("DY".equals(sessGb)){//기존 세션 삭제
				loginManager.setLoginMapping(param.get("USER_ID"), req, session);
				
			}
			
			
			String filter = "iphone|ipod|android|windows ce|blackberry|symbian|windows phone|webos|opera mini|opera mobi|polaris|iemobile|lgtelecom|nokia|sonyericsson|lg|samsung";
			String filters[] = filter.split("\\|");
			for(String tmp : filters){
				
				System.out.println("DMULTI :::::: " + req.getHeader("User-Agent").toLowerCase().indexOf(tmp));
						
				if ( req.getHeader("User-Agent").toLowerCase().indexOf(tmp) != -1) {

					session.setAttribute("SESS_ISMOBILE", "mobile");
					break;
							
				} else {

					session.setAttribute("SESS_ISMOBILE", "");
				    //webType = "PC";

				}
			}

			
			for(String name: param.keySet()){
				session.setAttribute("SESS_"+name, param.get(name));
			}
			
			
			
			
			//loginManager.printloginUsers();
		}
	}
	
	public Map<String,Object> getSessionInfo(HttpServletRequest req){
		Map<String,Object> sessMap = new HashMap<String,Object>();
		HttpSession session = req.getSession();		
		String userId = StringUtil.nvl(String.valueOf(session.getAttribute("SESS_USER_ID")));
		if(!"".equals(userId)){
			sessMap.put("SESS_USER_ID",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_USER_ID"))));
			sessMap.put("SESS_USER_NM",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_USER_NM"))));
			sessMap.put("SESS_AUTH_TYPE",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_AUTH_CD"))));
			sessMap.put("SESS_ORG_CD",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_ORG_CD"))));
			sessMap.put("SESS_SVC_MNGT_NO",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_SVC_MNGT_NO"))));
			sessMap.put("SESS_CMNTY_CD",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_CMNTY_CD"))));
			sessMap.put("SESS_LOGIN_ID",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_LOGIN_ID"))));
			sessMap.put("SESS_GENDER",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_GENDER"))));
			sessMap.put("SESS_JOB_CLF",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_JOB_CLF"))));
			sessMap.put("SESS_SIDO_CD",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_SIDO_CD")), String.valueOf(session.getAttribute("SESS_SIDO_CD"))));			
			sessMap.put("SESS_SIDO_NM",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_SIDO_NM"))));
			//담당업무명 추가
			sessMap.put("SESS_JOB_CLF_NM",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_JOB_CLF_NM"))));	
		}
		return sessMap;
	}
	
	
	public List getArrayParamToList(Map param){
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		Iterator<String> keySet = param.keySet().iterator();
		String key = "";
		int rowCnt = param.get("rowCnt")!=null?Integer.parseInt(param.get("rowCnt").toString()):0;
		List dataList = new ArrayList();
		
		List<String> keyArr = new ArrayList();
		for(; keySet.hasNext();){
			keyArr.add(keySet.next());
		}
		
		for(int i=0; i<rowCnt; i++){
			Map data = new HashMap();
			for(int j=0; j<keyArr.size(); j++){
				key = keyArr.get(j);
				if (param.get(key) instanceof String[]){
					data.put(key, ((String[])param.get(key))[i]);
				}else{
					data.put(key, param.get(key));
				}
			}
			dataList.add(data);
		}
		
		return dataList;
	}
	
	
	public List<List<String>> getExcelContentList(String[] titleArray, String[] colNmArray,List<Map<String,Object>> list){
		List<List<String>> allContentList = new ArrayList<List<String>>();
		allContentList.add(Arrays.asList(titleArray));
		String temp = "";
		
		List<String> colNmList = new ArrayList<String>();
		
		if(colNmArray!=null&&colNmArray.length>0){
			colNmList = Arrays.asList(colNmArray);
			
			for(Map data : list){
				List<String> contentList = new ArrayList<String>();
				for(String col : colNmList){
					contentList.add(data.get(col)!=null ? String.valueOf(data.get(col)) : "");
				}

				allContentList.add(contentList);
			}
		}
		

		return allContentList;
	}
	
	// 2016.07.22 이태석 추가 - 그리드에서 넘어오는 데이터 List<Map<String, Object>> 가공 
	public List<Map<String, Object>> excelExportDataList(Map param){		
		List<Map<String, Object>> paramList = excelView.excelExportData(param);
		return paramList;		
	}

	// 2016.09.01 이태석 추가 - 엑셀 데이터 유효성 체크 
	public Map<Integer,List<Integer>> excelDataValidChk(Map param){		
		Map<Integer,List<Integer>> validChkMap = excelView.excelDataValidChk(param);
		return validChkMap;		
	}
	
	//조리식품 영양코드 신청 - 엑셀 데이터 유효성 체크
	public Map<Integer,List<Integer>> excelValidFoodCookReqChk(Map param){		
		Map<Integer,List<Integer>> validChkMap = excelView.excelValidFoodCookReqChk(param);
		return validChkMap;		
	}
	
	//조리식품 영양코드 신청 결과 등록 - 엑셀 데이터 유효성 체크
//	public Map<Integer,List<Integer>> excelValidFoodCookCompChk(Map param) throws Exception{		
//		Map<Integer,List<Integer>> validChkMap = excelView.excelValidFoodCookCompChk(param);
//		return validChkMap;		
//	}
	
	//그리드에서 넘어오는 데이터 List<Map<String, Object>> 가공 
	public List<Map<String, Object>> excelExportDataReqList(Map param){		
		List<Map<String, Object>> paramList = excelView.excelExportDataReqList(param);
		return paramList;		
	}	
	
	//그리드에서 넘어오는 데이터 List<Map<String, Object>> 가공 
//	public List<Map<String, Object>> excelExportDataCompList(Map param)throws Exception{		
//		List<Map<String, Object>> paramList = excelView.excelExportDataCompList(param);
//		return paramList;		
//	}	


	//가공식품 영양코드 신청 결과 등록 - 엑셀 데이터 유효성 체크	
//	public Map<Integer,List<Integer>> excelValidFoodProcChk(Map param)throws Exception{		
//		Map<Integer,List<Integer>> validChkMap = excelView.excelValidFoodProcChk(param);
//		return validChkMap;		
//	}	
	
	//그리드에서 넘어오는 데이터 List<Map<String, Object>> 가공_조리식품
//	public List<Map<String, Object>> excelExportDataProcList(Map param)throws Exception{		
//		List<Map<String, Object>> paramList = excelView.excelExportDataProcList(param);
//		return paramList;		
//	}		
	
	//아동 청소년 체력 평가서 결과 등록 - 엑셀 데이터 유효성 체크	
//	public Map<Integer,List<Integer>> excelValidYouthSynFitEvalChk(Map param)throws Exception{
//		Map<Integer,List<Integer>> validChkMap = excelView.excelValidYouthSynFitEvalChk(param);
//		return validChkMap;
//	}
	
	//아동 청소년 체력 평가서 결과 등록 - 그리드에서 넘어오는 데이터 List<Map<String, Object>> 엑셀 데이터 유효성 체크
//	public List<Map<String, Object>> youthExportDataList(Map param)throws Exception{
//		List<Map<String, Object>> paramList = excelView.youthExportDataList(param);
//		return paramList;
//	}

//	public Map<Integer,List<Integer>> callingMaterialExcelValidChk(Map param){
//		Map<Integer,List<Integer>> validChkMap = excelView.callingMaterialExcelValidChk(param);
//		return validChkMap;
//	}

//	public List<Map<String, Object>> callingMaterialExcelExportData(Map param){
//		List<Map<String, Object>> paramList = excelView.callingMaterialExcelExportData(param);
//		return paramList;
//	}

	
}
