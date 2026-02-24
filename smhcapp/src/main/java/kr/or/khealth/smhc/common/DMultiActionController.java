
package kr.or.khealth.smhc.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
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
import kr.or.khealth.smhc.common.util.CryptoUtil;
import kr.or.khealth.smhc.common.util.FileUtil;
import kr.or.khealth.smhc.common.util.PushMessageUtil;
import kr.or.khealth.smhc.common.util.StringUtil;

import org.apache.log4j.Logger;
//
//import com.dreamsecurity.dstoolkit.DSToolkit;
//import com.dreamsecurity.dstoolkit.crypto.Cipher;
//import com.dreamsecurity.dstoolkit.crypto.PrivateKey;
//import com.dreamsecurity.dstoolkit.exception.DSToolkitException;
//import com.dreamsecurity.dstoolkit.storage.Disk;
//import com.dreamsecurity.dstoolkit.util.Base64;
//import com.dreamsecurity.magickeypad.MagicKeypadServer;
//import com.extrus.exafe.e2e.api.E2EApiManager;

import com.dreamsecurity.magicvkeypad.MagicVKeypadServer;
import com.extrus.exafe.e2e.api.E2EApiManager;

public abstract class DMultiActionController{
	
	protected final  Logger LOG = Logger.getLogger(this.getClass()); 
	protected String MESSAGE_NAME = "cmmnMsg";

	@Resource(name="common.cmmnService")
	protected CommonService cmmnService;

	@Resource(name = "msg") 
	private DMessage msg;   
	
//	@Resource(name = "pagination")
//	protected PaginationUtil pagination;
	
//	@Resource(name = "pagination2")
//	protected PaginationUtil pagination2;   
	
	@Resource(name = "cookieUtil")
	protected DCookieUtil cookieUtil;    
	
	@Resource(name = "fileUtil")
	protected FileUtil fileUtil; 
	
	@Resource(name = "blobView")
	protected BlobView blobView; 
	
	@Resource(name = "pushMessageUtil")
	protected PushMessageUtil pushMessageUtil;
	
	protected AddTag addTag;
	
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
		result.put("SESS_IPADDR",	StringUtil.nvl(String.valueOf(req.getRemoteAddr())));
		LOG.info("requestMap::"+result);
		
		// 접속 정보 저장
		//insertCmmnLogInfo(req,result);
		
		return result;  
	}
	
	
	public void insertCmmnLogInfo(HttpServletRequest req, Map<String,Object> result){
//		System.out.println("req.getRequestURI() == " + req.getRequestURI());
//		System.out.println("req.getRequestURL() == " + req.getRequestURL().toString());
//		System.out.println("req.getQueryString() == " + req.getQueryString());
//		System.out.println("req.getRequestURI() == " + req.getMethod());
//		System.out.println("req.getRequestURI() == " + req.getRemoteAddr());
//		System.out.println("req.getRequestURI() == " + req.getRequestURI());
//		System.out.println("req.getRequestURI() == " + req.getRequestURI());
		try {
			HashMap<String,Object> param = new HashMap<String,Object>();
			String path = StringUtil.nvl(result.get("curPageNm"));
			if(!"".equals(path)){
				param.put("USER_ID", (String)result.get("SESS_USER_ID"));
				param.put("CONNECT_URL", req.getRequestURI());
				param.put("PROGRAM_PATH", path.substring(0, path.lastIndexOf("/")));
				param.put("PROGRAM_ID", path.substring(path.lastIndexOf("/") + 1));
				StringBuffer sb = new StringBuffer();
				for (String item : result.keySet()) {
					if(!"pw".equals(item)){
						sb.append(item + "=" + result.get(item) + ", ");
					}
				}
				param.put("REQ_PARAM", sb.toString());
				cmmnService.insertCmmnLogInfo(param);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	
	public void setSessionInfo(HttpSession session, Map<String,String> param){
		String userId = (String)param.get("USER_ID");
        if(userId != null && !"".equals(userId)) {
			for(String name: param.keySet()){
				session.setAttribute("SESS_"+name, param.get(name));
			}
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
			sessMap.put("SESS_TRGT_YY", 	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_TRGT_YY"))));
			sessMap.put("SESS_TRGT_ORD",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_TRGT_ORD"))));
			sessMap.put("SESS_HEIGHT",		StringUtil.nvl(String.valueOf(session.getAttribute("SESS_HEIGHT"))));
			
			sessMap.put("SESS_NICKNAME",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_NICKNAME"))));
			sessMap.put("SESS_NICKNAME_USE_YN",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_NICKNAME_USE_YN"))));			
		}
//		테스트를 위한 임시 세선정보 설정 로그인 개발 후 삭제
//		else{
//			req.getSession().setAttribute("SESS_USER_ID",         "TEST0002"     );
//			req.getSession().setAttribute("SESS_USER_NM",         "관리자"       );
//			req.getSession().setAttribute("SESS_GENDER",          "M"            );
//			req.getSession().setAttribute("SESS_BIRTH",           "20000101"     );
//			req.getSession().setAttribute("SESS_EMAIL",           "ADMIN@kkk.com");
//			req.getSession().setAttribute("SESS_TEL_NO_1",        "02"           );
//			req.getSession().setAttribute("SESS_TEL_NO_2",        "1234"         );
//			req.getSession().setAttribute("SESS_TEL_NO_3",        "1234"         );
//			req.getSession().setAttribute("SESS_MOBILE_NO_1",     "010"          );
//			req.getSession().setAttribute("SESS_MOBILE_NO_2",     "1111"         );
//			req.getSession().setAttribute("SESS_MOBILE_NO_3",     "2222"         );
//			req.getSession().setAttribute("SESS_LOGIN_ID",        "admin"        );
//			req.getSession().setAttribute("SESS_PW",              "gst10102"     );
//			req.getSession().setAttribute("SESS_PW_VALID_DT",     ""             );
//			req.getSession().setAttribute("SESS_PW_UPD_DT",       ""             );
//			req.getSession().setAttribute("SESS_TRGTER_USER_CLF", "US"           );
//			req.getSession().setAttribute("SESS_ORG_CD",          "T001"         );
//			req.getSession().setAttribute("SESS_AUTH_CD",         "HLTH099"      );
//			req.getSession().setAttribute("SESS_THUMB_SVR_PATH",  "20160712"     );
//			req.getSession().setAttribute("SESS_THUMB_SVR_NM",    "59_1"         );
//			req.getSession().setAttribute("SESS_THUMB_LOCAL_NM",  "sample.png"   );
//			req.getSession().setAttribute("SESS_SIGN_SVR_PATH",   ""             );
//			req.getSession().setAttribute("SESS_SIGN_SVR_NM",     ""             );
//			req.getSession().setAttribute("SESS_SIGN_LOCAL_NM",   ""             );
//			req.getSession().setAttribute("SESS_SVC_MNGT_NO",     "T0010002"     );
//		}
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
}
