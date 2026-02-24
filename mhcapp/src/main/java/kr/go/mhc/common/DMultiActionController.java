
package kr.go.mhc.common;

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

import kr.go.mhc.common.service.CommonService;
import kr.go.mhc.common.util.AddTag;
import kr.go.mhc.common.util.FileUtil;
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.common.util.StringUtil;

import org.apache.log4j.Logger;

import com.dreamsecurity.dstoolkit.DSToolkit;
import com.dreamsecurity.dstoolkit.crypto.Cipher;
import com.dreamsecurity.dstoolkit.crypto.PrivateKey;
import com.dreamsecurity.dstoolkit.exception.DSToolkitException;
import com.dreamsecurity.dstoolkit.storage.Disk;
import com.dreamsecurity.dstoolkit.util.Base64;
import com.dreamsecurity.magickeypad.MagicKeypadServer;
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
		insertCmmnLogInfo(req,result);
		
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
					sb.append(item + "=" + result.get(item) + ", ");
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
		
		if(!"".equals(param.get("USER_ID"))){
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
			
			//만성질환 추가
			sessMap.put("SESS_CHRONIC_DISEASES_YN",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_CHRONIC_DISEASES_YN"))));
			sessMap.put("SESS_CHRONIC_CD",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_CHRONIC_CD"))));
			//군분류 추가
			sessMap.put("SESS_MCLAS_CD",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_MCLAS_CD"))));
			sessMap.put("SESS_GCLAS_CD",	StringUtil.nvl(String.valueOf(session.getAttribute("SESS_GCLAS_CD"))));
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

	public Map decryptMap(Map rtParam, String paramKey){
		if(paramKey == null){
			System.out.println("decryptMap:: paramKey is null~");
			return rtParam;
		}
		if(rtParam == null){
			System.out.println("decryptMap:: rtParam is null~");
			return rtParam;
		}
		Iterator<String> keySet = rtParam.keySet().iterator();
		String key = "";
		String val = "";
		for(; keySet.hasNext();){
			key = keySet.next();
			val = rtParam.get(key).toString();
			if(key.equals(paramKey)){
				rtParam.put(key, decryptStr(val));
				break;
			}
		}       
		return rtParam;
	}
	
	public Map encryptMap(Map rtParam, String paramKey){
		if(paramKey == null){
			System.out.println("encryptMap:: paramKey is null~");
			return rtParam;
		}
		if(rtParam == null){
			System.out.println("encryptMap:: rtParam is null~");
			return rtParam;
		}
		Iterator<String> keySet = rtParam.keySet().iterator();
		String key = "";
		String val = "";
		for(; keySet.hasNext();){
			key = keySet.next();
			val = rtParam.get(key).toString();
			if(key.equals(paramKey)){
				rtParam.put(key, encryptStr(val));
				break;
			}
		}       
		return rtParam;
	}
	
	private String getKeypadLibPath(){
		String[] libPaths = new String[]{"/home/jeus/MagicKeypad/"   //운영/개발
										,"D:/MagicKeypad/"			//테스트1
										,"C:/MagicKeypad/"};		//테스트2
		String rtPath = libPaths[0];
		for(String path : libPaths){
			File file = new File(path); 
			if(file.isDirectory()){
				rtPath = path;
				break;
			}
		}		
		return rtPath;
	}
	
	
	private MagicKeypadServer keypad;
	private boolean isSuccessKeypad = false; 	
	public String keypadDecrypt(String encStr){
		String decStr = encStr;
		Base64 base64 = new Base64();
		if(!isSuccessKeypad){
			System.out.println("java.library.path="+System.getProperty("java.library.path"));		
			String libPath = getKeypadLibPath();			
			try {
				DSToolkit.init(libPath+"lib"); 
			} catch (DSToolkitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isSuccessKeypad = false;
			}catch(Exception e){
				isSuccessKeypad = false;
			}		
	
			
			try {
								
				String b64EncKeypadPlain = "aDA3h2WMLCuC2WJwH4kU/EjzGq6h/7F+LA833yO3secfcqnqkRvpzLTbPwFXsH0nqCuP/p/JqxwWE0kkQk7HKJqH31BZgbKShyB6NkxbfXNRTL3iwNVrjnUHaVgi5VVtnsdmLUKPSulXeMz0weGPbuOdJgsBibtjnexUPYnOtEHrUUMP/tkAxG6kubD/PMrY3wR9IkCkTSpoIQNlykcoAYGd6siQA6gQ8QOjVafnNfQds86w0suehpMszO16HnlBE4pzTWfvSp5yrNfXKOyDbwQmuf+fhucndXuIaDNGijegOLWWUsrAs15Ox9W9i3lq2Vat+4DuQcAieEhdms4/8Q==";				
				PrivateKey	prikey = Disk.readPriKey(libPath+"sample/prikey.enc.key", "1q2w3e4r");			
				// Plain Keypad Test					
				byte [] encKeypadPlain = base64.decode(b64EncKeypadPlain);
		
				Cipher rsa = Cipher.getInstance("RSA");
				rsa.init(Cipher.DECRYPT_MODE, prikey);
	
				// init MagicKeypad
				keypad = new MagicKeypadServer(libPath+"lib");
				keypad.setPrikey(libPath+"sample/prikey.enc.key", "1q2w3e4r");
				keypad.setPubkey(libPath+"sample/pubkey.key");
	
				// Mobile Test
				byte [] bsKeypadRecord_Plain = rsa.doFinal(encKeypadPlain);
				System.out.println("\n\n--- MagicKeypad Test ---");
				String plainKeypad = keypad.parseEncryptKeypadData(bsKeypadRecord_Plain);
				System.out.println("Mobile Keypad PIN = " + plainKeypad);		
				
				isSuccessKeypad = true;
				
	
	
			} catch (Exception e) {
				isSuccessKeypad = false;
				e.printStackTrace();
			}
		}
		
		if(isSuccessKeypad&&keypad!=null){		
			try {
				byte[] data = base64.decode(encStr);
				String plainKeypadStr = keypad.parseEncryptKeypadData(data);
				System.out.println("encStr = " + encStr);
				System.out.println("decStr = " + plainKeypadStr);
				decStr = plainKeypadStr;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return decStr;
	}
	
	public String decryptStr(String encStr){
		String decStr = encStr;
		if(encStr != null && encStr.indexOf("ENCINFO") == 0){ 
			try{
				E2EApiManager e2eManager = new E2EApiManager();
				decStr = e2eManager.e2eDecrypt(encStr);
				
				System.out.println("decStr===="+decStr);
			
			}catch(Exception e){
//				System.out.println("decryptStr E2EApiManager Exception~");
				if(encStr != null && encStr.indexOf("ENCINFO") == 0) e.printStackTrace();
			}
		}else{ //MagicKeypad
			
			decStr = keypadDecrypt(encStr);
		}
		
		return decStr;
	}
	
	public String encryptStr(String decStr){
		String encStr = decStr;
		try{
			E2EApiManager e2eManager = new E2EApiManager();
			encStr = e2eManager.e2eEncrypt(decStr);
			
			System.out.println("encStr===="+encStr);
		
		}catch(Exception e){
//			System.out.println("encryptStr E2EApiManager Exception~");
			if(decStr != null && decStr.indexOf("ENCINFO") == 0) e.printStackTrace();
		}
		return encStr;
	}
	
}
