package kr.or.khealth.smhc.common.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.service.ChatService;
import kr.or.khealth.smhc.common.util.DateUtil;
import kr.or.khealth.smhc.common.util.EgovResourceCloseHelper;
import kr.or.khealth.smhc.common.util.EgovWebUtil;
import kr.or.khealth.smhc.common.util.FileUtil;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcapp.cm.service.AppDashboardService;
import kr.or.khealth.smhc.smhcapp.sv.service.MissionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
/**
 * @Class Name : CommonController.java
 * @Description : 모바일 헬스케어에서 사용하는 통합공통업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/cmmn")
public class CommonController extends DMultiActionController{ 
	
	public static final int BUFF_SIZE = 2048;

	@Resource(name="common.ChatService")
	private ChatService chatService;
	
	@Resource(name = "smhcapp.cm.AppDashboardService")
	private AppDashboardService appDashboardService;
	
	@Resource(name = "smhcapp.sv.MissionService")
	private MissionService missionService;
	
	@Resource(name = "fileUtil")
	protected FileUtil fileUtil;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	@RequestMapping( value="/attchFileUpload.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> attchFileUpload(final MultipartHttpServletRequest multiRequest
								, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		int isnertCnt = 0;
		Map<String,Object> rsMap = new HashMap<String,Object>();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if(!files.isEmpty()){
			String attchFileSn = StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
			int attchFileDtlsSn = 1;
			String svrFilePath = StringUtil.nvl(String.valueOf(param.get("svrFilePath")));
			String svrFileFullPath = "";
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			if("".equals(attchFileSn)){
				attchFileSn = cmmnService.selectAttchFileSnSeq();
			}else{
				param.put("attchFileSn", attchFileSn);
				if("Y".equals( String.valueOf( param.get("isSign") ) )){
					param.put("fileClf", "12");
					
					// 기존 파일 정보 삭제 및 물리적 파일 삭제
					List<Map<String,Object>> fileList = cmmnService.selectAttchFile(param);
					if(fileList.size() > 0){
						Map<String,Object> fileMap = fileList.get(0);
						fileUtil.deleteFile(StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_NM"))), StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_PATH"))));
						
						param.put("attchFileDtlsSn", String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
						cmmnService.deleteAttchFileInfo(param);
						
						attchFileDtlsSn = Integer.parseInt(String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
					}
				} else {
					//새로운 sn 조회
					attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
				}
			}
			
			if ("".equals(svrFilePath)) {
				svrFilePath = DateUtil.getSysDatenf();
			}
			
			svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;
			
			File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));

			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			String filePath = "";
			Map<String,String> fileInfo;

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				String localFileNm = file.getOriginalFilename();
				String fileClf = "IMG".equals(fileUtil.getFileType(localFileNm)) ? ("Y".equals( String.valueOf( param.get("isSign") ) ) ? "12" : "10") : "MOV".equals(fileUtil.getFileType(localFileNm)) ? "30" : "20";

				
				//--------------------------------------
				// 원 파일명이 없는 경우 처리
				// (첨부가 되지 않은 input file type)
				//--------------------------------------
				if ("".equals(localFileNm)) {
					continue;
				}
				////-----------------------------------

				int index = localFileNm.lastIndexOf(".");
				String extnsn = localFileNm.substring(index + 1);
				String svrFileNm = attchFileSn + "_" + attchFileDtlsSn;
				long fileSize = file.getSize();

				if (!"".equals(localFileNm)) {
					filePath = svrFileFullPath + File.separator + svrFileNm;
					
					//이미지일 경우 파일 리사이즈
					if(fileClf.equals("10")) {							
						fileUtil.resizeImage(file, filePath, extnsn, 600, true);
					}else {	
						file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
					}

				}
				
				fileInfo = new HashMap<String,String>();
				fileInfo.put("attchFileSn",attchFileSn);
				fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
				fileInfo.put("fileClf",fileClf);
				fileInfo.put("svrFilePath",svrFilePath);
				fileInfo.put("svrFileNm",svrFileNm);
				fileInfo.put("localFileNm",localFileNm);
				fileInfo.put("extnsn",extnsn);
				fileInfo.put("fileSize",Long.toString(fileSize));
				result.add(fileInfo);
				
				attchFileDtlsSn++;
				
				if("10".equals(fileClf)){
					param.put("isThumbNail", "Y");
					param.put("isRotate", "Y");
				}
				
				
				//썸네일 이미지
				if("Y".equals(String.valueOf(param.get("isThumbNail")))  && "10".equals(fileClf)){ 

					int thumbnail_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbWidth")),"180"));
					int thumbnail_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbHeight")),"180"));
					fileUtil.getThumbnailImage(filePath, extnsn, thumbnail_width, thumbnail_height);

					
					fileInfo = new HashMap<String,String>();
					fileInfo.put("attchFileSn",attchFileSn);
					fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
					fileInfo.put("fileClf","11");
					fileInfo.put("svrFilePath",svrFilePath);
					fileInfo.put("svrFileNm",svrFileNm+"_thumb");
					fileInfo.put("localFileNm",localFileNm.substring(0,index)+"_thumb."+extnsn);
					fileInfo.put("extnsn",extnsn);
					fileInfo.put("fileSize",Long.toString(fileSize));

					result.add(fileInfo);
					
					attchFileDtlsSn++;
				}
				
				//회전 이미지 처리
//				if("Y".equals(String.valueOf(param.get("isRotate")))  && "10".equals(fileClf)){ 
//
//					int rotate_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("rotateWidth")),"300"));
//					int rotate_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("rotateHeight")),"300"));
//					
//					fileUtil.getRotateImage(filePath, extnsn, rotate_width, rotate_height);
//					
//					fileInfo = new HashMap<String,String>();
//					fileInfo.put("attchFileSn",attchFileSn);
//					fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
//					fileInfo.put("fileClf","13");
//					fileInfo.put("svrFilePath",svrFilePath);
//					fileInfo.put("svrFileNm",svrFileNm+"_rotate");
//					fileInfo.put("localFileNm",localFileNm.substring(0,index)+"_rotate."+extnsn);
//					fileInfo.put("extnsn",extnsn);
//					fileInfo.put("fileSize",Long.toString(fileSize));
//					
//					result.add(fileInfo);
//					
//					attchFileDtlsSn++;
//				}
			}
			
			isnertCnt = cmmnService.insertAttchFile(result);
			rsMap.put("attchFileSn", attchFileSn);
			rsMap.put("rsList", result);
		}
//		if(isnertCnt > 0){
//			rsMap.put(MESSAGE_NAME, getMsg("common.write.succ"));
//		}else{
//			rsMap.put(MESSAGE_NAME, getMsg("common.write.err"));
//		}
		return rsMap;
	}	

    /**
     * 썸네일 파일 업로드
     * @param fileNm
     * @return
     */
	@RequestMapping( value="/attchFileUploadThumbnail.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> attchFileUploadThumbnail(final MultipartHttpServletRequest multiRequest
								, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if(!files.isEmpty()){
			String attchFileSn	= StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
			String svrFilePath	= StringUtil.nvl(String.valueOf(param.get("svrFilePath")));
			int attchFileDtlsSn = 1;
			String svrFileFullPath = "";
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			if("".equals(attchFileSn)){
				attchFileSn = cmmnService.selectAttchFileSnSeq();
			}else{
				param.put("attchFileSn", attchFileSn);
				param.put("fileClf", "11");
				
				// 기존 파일 정보 삭제 및 물리적 파일 삭제
				List<Map<String,Object>> fileList = cmmnService.selectAttchFile(param);
				if(fileList.size() > 0){
					Map<String,Object> fileMap = fileList.get(0);
					fileUtil.deleteFile(StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_NM"))), StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_PATH"))));
					
					param.put("attchFileDtlsSn", String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
					cmmnService.deleteAttchFileInfo(param);
					
					attchFileDtlsSn = Integer.parseInt(String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
				} else {
					//새로운 sn 조회
					attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
				}
			}
			
			if ("".equals(svrFilePath)) {
				svrFilePath = DateUtil.getSysDatenf();
			}
			
			svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;
			
			File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));

			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			String filePath = "";
			Map<String,String> fileInfo;

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				String localFileNm = file.getOriginalFilename();

				//--------------------------------------
				// 원 파일명이 없는 경우 처리
				// (첨부가 되지 않은 input file type)
				//--------------------------------------
				if ("".equals(localFileNm)) {
					continue;
				}
				////------------------------------------

				int index = localFileNm.lastIndexOf(".");
				String extnsn = localFileNm.substring(index + 1);
				String svrFileNm = attchFileSn + "_" + attchFileDtlsSn;
				long fileSize = file.getSize();

				int thumbnail_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbWidth")),"164"));
				int thumbnail_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbHeight")),"164"));

	            //생성할 썸네일파일의 경로+썸네일파일명
				filePath = svrFileFullPath + File.separator + svrFileNm;
	            File thumb_file_name = new File(EgovWebUtil.filePathBlackList(filePath)+"_thumb");
	            BufferedImage buffer_original_image = ImageIO.read(file.getInputStream());
	            BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
	            Graphics2D graphic = buffer_thumbnail_image.createGraphics();
	            graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
	            ImageIO.write(buffer_thumbnail_image, extnsn, thumb_file_name);
				
				fileInfo = new HashMap<String,String>();
				fileInfo.put("attchFileSn",attchFileSn);
				fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
				fileInfo.put("fileClf","11");
				fileInfo.put("svrFilePath",svrFilePath);
				fileInfo.put("svrFileNm",svrFileNm+"_thumb");
				fileInfo.put("localFileNm",localFileNm.substring(0,index)+"_thumb."+extnsn);
				fileInfo.put("extnsn",extnsn);
				fileInfo.put("fileSize",Long.toString(fileSize));
				
				result.add(fileInfo);
				
				attchFileDtlsSn++;
			}
			
			cmmnService.insertAttchFile(result);
			rsMap.put("attchFileSn", attchFileSn);
			rsMap.put("rsList", result);
		}
//		if(isnertCnt > 0){
//			rsMap.put(MESSAGE_NAME, getMsg("common.write.succ"));
//		}else{
//			rsMap.put(MESSAGE_NAME, getMsg("common.write.err"));
//		}
		return rsMap;
    }
    
	/**
	 * 서버의 파일을 다운로드한다.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value="/attchFileDownload.do", method = {RequestMethod.GET, RequestMethod.HEAD})
	public void downFile(@ModelAttribute Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Map<String, Object>> fList = new ArrayList<Map<String, Object>>();
		
		// 파일번호 존재유무 체크 및 없을 시번호 부여하기
		if("".equals(StringUtil.nvl(String.valueOf(param.get("attchFileSn"))))){
			fList.add(param);
		}else{
			fList = cmmnService.selectAttchFile(param);
		}
		
		// 번호 부여 이후 
		if(fList.size() > 0){
			Map<String,Object> fMap = fList.get(0);
//			String downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			
//			String sysdate =  SimpleDateUtil.getSysDate("yyyyMMdd").substring(0,4);
			String svrFilePath = fMap.get("SVR_FILE_PATH").toString().substring(0,4);
			String downFileName = "";
//			if(sysdate.equals(svrFilePath)){
			if(!"2016".equals(svrFilePath) && !"2017".equals(svrFilePath)){
				downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}else{
				downFileName = fileUtil.getFileDirOld() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}
			
			String orgFileName = (String)fMap.get("LOCAL_FILE_NM");
			orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");
			String encodeFileName = null;
			
			File file = new File(EgovWebUtil.filePathBlackList(downFileName));
			if (!file.exists()) {
				throw new FileNotFoundException(downFileName);
			}
			if (!file.isFile()) {
				throw new FileNotFoundException(downFileName);
			}
			
			response.setContentType("application/octet-stream;charset=UTF-8");
			
			try {
			    LOG.debug("User-Agent : " + request.getHeader("User-Agent"));
			    if(getBrowser(request).equals("MSIE")) {
			    	encodeFileName=URLEncoder.encode(orgFileName, "UTF-8").replaceAll("\\+", "%20"); 
			    } else if(getBrowser(request).equals("Chrome")){
			    	StringBuffer sbuf=new StringBuffer();
	                for(int i=0; i < orgFileName.length(); i++){
	                    char c = orgFileName.charAt(i);
	                    if (c > '~') {
	                        sbuf.append(URLEncoder.encode("" + c, "UTF-8"));
	                    } else {
	                        sbuf.append(c);
	                    }
	                }
	                encodeFileName = sbuf.toString();
			    } else {
			    	encodeFileName = new String(orgFileName.getBytes("UTF-8"), "8859_1"); 
			    }
			}
			catch (UnsupportedEncodingException ignored) {
			    // do nothing
			}
			
			
			response.addHeader("Content-Disposition", "attachment;filename=\""+encodeFileName+"\"");
//			response.setHeader("Content-Transfer-Encoding", "binary");
			LOG.debug("Content Type : " + response.getContentType());
			
			
			byte[] buffer = new byte[BUFF_SIZE]; //buffer size 2K.
			BufferedInputStream fin = null;
			BufferedOutputStream outs = null;
			try {
				fin = new BufferedInputStream(new FileInputStream(file));
				outs = new BufferedOutputStream(response.getOutputStream());
				int read = 0;
	
				while ((read = fin.read(buffer)) != -1) {
					outs.write(buffer, 0, read);
				}
				
			} catch(Exception e) {
				//EgovResourceCloseHelper.close(outs, fin);
			} finally {
				EgovResourceCloseHelper.close(outs, fin);
				response.flushBuffer();
			}
		}
	}
	
	 // HTTP/1.1 헤더로부터 브라우저를 가져온다.
    private String getBrowser(HttpServletRequest request) {
    	
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        // IE8 ~ IE11
        } else if (header.indexOf("Trident") > -1) {
            return "MSIE";
        } else if (header.indexOf("OPR") > -1) {
            return "OPR";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        } else if (header.indexOf("Firefox") > -1) {
            return "Firefox";
        } else if (header.indexOf("Safari") > -1) {
            return "Safari";
        } else{
            return "UNKOWN";
        }
    }  
    
	/**
	 * js, css import 페이지 호출
	 * @param clientMode
	 * @return rtnUrl
	 * @throws Exception 
	 */
	@RequestMapping( value="/importResourceFile.do")
	public String importResourceFile(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String rtnUrl = "web/include/importResource";
		if("APP".equals(String.valueOf(param.get("clientMode")))){
			rtnUrl = "app/include/importResource";
		}
		model.addAttribute("pageMode", param.get("pageMode"));
		model.addAllAttributes(param);
		return rtnUrl;
	}	

	/**
	 * 공통코드 조회
	 * @param codeId
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectCmmnCd.do")
	public @ResponseBody List<Map<String,String>> selectCmmnCd(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String,String>> rsList = cmmnService.selectCmmnCd(param);
		
		return rsList;
	}	

	/**
	 * 메뉴 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectCmmnMenu.do" ,method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectCmmnMenu(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = cmmnService.selectCmmnMenu(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 메뉴 화면 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/makeMenuList.do" ,method={RequestMethod.GET, RequestMethod.POST})
	public String makeMenuList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String,String>> rsList = cmmnService.selectCmmnMenu(param);
		model.addAttribute("menuList", rsList);
		return "web/include/leftMenu";
	}
	
	
	// 채팅 컨트롤러 -- 허광일 
	
	/**
	 * 커뮤니티 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatMain.do", method = RequestMethod.GET)
	public String chatMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		return "app/cm/chat/chatMain";
	}	
	
	/**
	 * 커뮤니티 상세 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatDtls.do", method = RequestMethod.GET)
	public String chatDetail(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		model.addAllAttributes(param);
		return "app/cm/chat/chatDtls";
	}
	
	/**
	 * 커뮤니티 이력 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatHist.do", method = RequestMethod.GET)
	public String chatHist(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		model.addAllAttributes(param);
		return "app/cm/chat/chatHist";
	}
	
	/**
	 * 커뮤니티 이력 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatHistList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> chatHistList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = chatService.selectChatHistList(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 실시간상담 이력 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatRoomList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> chatRoomList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = chatService.selectRoomList(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 방 번호 조회(sequence)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/chatRoomNumber.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> chatRoomNumber(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = chatService.selectRoomNumber(param);      
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 방 등록 및 맴버에 추가시키기(sequence)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertChatRoom.do" , method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> insertChatRoom(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String, String> cnslSn = chatService.selectCnslSn();
		param.put("CNSL_SN",cnslSn.get("CNSL_SN"));
		chatService.insertChatCnsl(param);		//상담 마스터 저장
		chatService.insertChatMastr(param);		//채팅 마스터 저장
		chatService.insertChatRoom(param);		//방 저장 
		return param; //2019.04.01 유준영 리턴값 void를 param으로 수정 하여 cnsl_sn조회되게 수정
	}	
	
	/**
	 * 채팅 내용 입력 시 상담 푸시 알림 상태 값 변경
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping( value="/updateChatAlt.do", method = RequestMethod.POST)
	public void updateChatAlt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		chatService.insertChatCnsl(param);
	}
	
	/**
	 * 채팅 내용 저장하기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertChatMessage.do" , method = RequestMethod.POST)
	public void insertChatMessage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		chatService.insertChatMessage(param); // 채팅 대화 내용 넣기
	}	
	
	/**
	 * 채팅방 대화내용 조회하기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectChatTalkList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectChatTalkList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		//20170915 윤봉훈 - 채팅 목록 조회 시 확인 안한 내용 확인 업데이트 실행
		chatService.updateChatCnfm(param);
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = chatService.selectChatTalkList(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 채팅 내 썸네일 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectThumNail.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String,Object>  selectThumNail(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		//20170915 윤봉훈 - 채팅 목록 조회 시 확인 안한 내용 확인 업데이트 실행
		String userJoin = StringUtil.nvl(String.valueOf(param.get("USER_JOIN_YN")));
		if("Y".equals(userJoin)){
			chatService.updateChatCnfm(param);
		}
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = chatService.selectThumNail(param);      
		rsMap.put("rsList", rsList);
		return rsMap;
	}	

	/**
	 * 서버 파일 복사
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/makeSource.do" , method = RequestMethod.GET)
	public String makeSource(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		model.addAllAttributes(param);
		return "web/cm/makeSourceToReal";
	}	
	
	/**
	 * 서버 파일 복사
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/makeSourceToReal.do" , method = RequestMethod.POST)
	public void makeSourceToReal(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		FileInputStream inputStream = null;         
		FileOutputStream outputStream = null;
		FileChannel fcin =  null;
		FileChannel fcout = null;
		
		String areaData = StringUtil.nvl(String.valueOf(param.get("areaData")));
		String MAKE_FOLDER = fileUtil.getFileDir() + File.separator + "realUpload" + File.separator + DateUtil.getSysDate();
		try{
			//디렉토리 생성 
//			File desti = new File(MAKE_FOLDER);
//			//해당 디렉토리의 존재여부를 확인
//			if(!desti.exists()){
//				//없다면 생성
//				desti.mkdirs(); 
//			}else{
//				//있다면 현재 디렉토리 파일을 삭제 
//				deleteAllFiles(MAKE_FOLDER);
//			}
			
			String[] arr = areaData.split("\n");
			for (int i = 0; i < arr.length; i++) {
				if(arr[i].indexOf("Deleted") == 0) continue;
				String fileFullPath = arr[i].substring(arr[i].indexOf("C:\\")).replace("  text/plain", "").replace("  application/octet-stream", "").trim();
				if("".equals(StringUtil.nvl(fileFullPath))) continue;
				
				String copyFullPath = MAKE_FOLDER + File.separator + "WEB-INF" + File.separator;

				//+ fileFullPath.substring(fileFullPath.lastIndexOf("\\src")+1);//fileFullPath.replaceAll("mhcweb_real", "mhcweb_upload");
				if(fileFullPath.indexOf("\\java\\") > 0){
					fileFullPath = fileFullPath.replace("\\src\\main\\java\\", "\\target\\classes\\").replace(".java", ".class");
					copyFullPath = copyFullPath + "classes" + File.separator + fileFullPath.substring(fileFullPath.indexOf("\\kr\\") + 1);
				}else if(fileFullPath.indexOf("\\resources\\") > 0){
					copyFullPath = copyFullPath + "classes" + File.separator + fileFullPath.substring(fileFullPath.indexOf("\\egovframework\\") + 1);
				}else if(fileFullPath.indexOf("\\jsp\\") > 0){
					copyFullPath = copyFullPath + fileFullPath.substring(fileFullPath.indexOf("\\jsp\\") + 1);
				}else{// if(fileFullPath.indexOf("\\js\\") > 0 || fileFullPath.indexOf("\\css\\") > 0 || fileFullPath.indexOf("\\images\\") > 0){
					copyFullPath = MAKE_FOLDER + File.separator + fileFullPath.substring(fileFullPath.indexOf("\\webapp\\") + 8);
				}
				
				File copyFolder = new File(copyFullPath.substring(0, copyFullPath.lastIndexOf("\\")));
				if(!copyFolder.exists()){
					copyFolder.mkdirs();
				}

				File fromFile = new File(fileFullPath);
				File copyFile = new File(copyFullPath);
				
				inputStream = new FileInputStream(fromFile);         
				outputStream = new FileOutputStream(copyFile);
				
				fcin =  inputStream.getChannel();
				fcout = outputStream.getChannel();

				long size = fcin.size();
				fcin.transferTo(0, size, fcout);
				
				if(fcout != null) fcout.close();
				if(fcin != null) fcin.close();
				if(outputStream != null) outputStream.close();
				if(inputStream != null) inputStream.close();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(fcout != null) fcout.close();
			if(fcin != null) fcin.close();
			if(outputStream != null) outputStream.close();
			if(inputStream != null) inputStream.close();
		}
	}
	
	public static void deleteAllFiles(String path){
		File file = new File(path);
		//폴더내 파일을 배열로 가져온다.
		File[] tempFile = file.listFiles();
		if(tempFile.length >0){
			for (int i = 0; i < tempFile.length; i++) {
				if(tempFile[i].isFile()){
					tempFile[i].delete();
				}else{
					//재귀함수
					deleteAllFiles(tempFile[i].getPath());
				}
				tempFile[i].delete();
			}
			file.delete();
		}
	}
	
	/**
	 * 주소검색  화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/jusoPopup.do", method = RequestMethod.GET)
	public String juso_pop1(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		return "web/cm/jusoPopup";
	}
	
	@RequestMapping( value="/jusoPopup.do", method = RequestMethod.POST)
	public String juso_pop2(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		return "web/cm/jusoPopup";
	}
	
	@RequestMapping( value="/myhealthChart.do", method = RequestMethod.GET)
	public ModelAndView iframe_chart(@ModelAttribute Map param, ModelMap model) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app/iframeChart/myhealthChart");
		modelAndView.addAllObjects(param);
		return modelAndView;
	}

	/**
	 * 서버의 파일을 다운로드한다.(에디터)
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value="/editorAttchFile.do", method = {RequestMethod.GET, RequestMethod.HEAD})
	public void editorDownFile(@ModelAttribute Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		int docFileChkCnt = 0;
		String[] imgFileInfo = param.get("imgFileInfo").toString().split("/");
			
			Map<String,Object> fMap = new HashMap<String, Object>();
			
			fMap.put("SVR_FILE_PATH", imgFileInfo[1] + File.separator +imgFileInfo[2]);
			fMap.put("SVR_FILE_NM", imgFileInfo[3]);
			fMap.put("LOCAL_FILE_NM", imgFileInfo[3]);
			
			
			String downFileName = "";
			downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			
			
			
			

			String orgFileName = (String)fMap.get("LOCAL_FILE_NM");
			orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

			File file = new File(EgovWebUtil.filePathBlackList(downFileName));
			if (!file.exists()) {
				throw new FileNotFoundException(downFileName);
			}
	
			if (!file.isFile()) {
				throw new FileNotFoundException(downFileName);
			}
	
			byte[] buffer = new byte[BUFF_SIZE]; //buffer size 2K.

			
			response.setContentType("application/x-download");
			try {
			    LOG.debug("User-Agent : " + request.getHeader("User-Agent"));
			    if(request.getHeader("User-Agent").contains("Firefox")) {
			        response.setHeader("Content-Disposition",
			                "attachment;filename=\"" + new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1") + "\";");
			    } else {
			        response.setHeader("Content-Disposition",
			                "attachment;filename=\"" + URLEncoder.encode(orgFileName, "utf-8") + "\";");
			    }
			}
			catch (UnsupportedEncodingException ignored) {
			    // do nothing
			}
			response.setHeader("Content-Transfer-Encoding", "binary");
			LOG.debug("Content Type : " + response.getContentType());
			
			
			BufferedInputStream fin = null;
			BufferedOutputStream outs = null;
			try {
				fin = new BufferedInputStream(new FileInputStream(file));
				outs = new BufferedOutputStream(response.getOutputStream());
				int read = 0;
	
				while ((read = fin.read(buffer)) != -1) {
					outs.write(buffer, 0, read);
				}
				
			} catch(Exception e) {
				//EgovResourceCloseHelper.close(outs, fin);
			} finally {
				EgovResourceCloseHelper.close(outs, fin);
				response.flushBuffer();
			}
	}
	
	/**
	 * 연동 로그파일 업로드
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value="/pairingLogFileUpload.do", method=RequestMethod.POST)
	public void connectLogFileUpload(final MultipartHttpServletRequest multiRequest, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(!files.isEmpty()) {

			String svrFilePath = "connectLogFile";
			String svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			String attchFileSn = cmmnService.selectAttchFileSnSeq();
			
			File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));

			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			String filePath = "";
			Map<String,String> fileInfo;

			Entry<String, MultipartFile> entry = itr.next();

			MultipartFile file = entry.getValue();
			String localFileNm[] = file.getOriginalFilename().toString().split("\\_");
			String fileClf = "20";
			int index = localFileNm[1].lastIndexOf(".");
			String extnsn = localFileNm[1].substring(index + 1);
			String svrFileNm = DateUtil.getSysDatenf() + "_" + localFileNm[0];
			long fileSize = file.getSize();

			if (!"".equals(localFileNm[1])) {
				filePath = svrFileFullPath + File.separator + svrFileNm;
					file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
			}
			
			fileInfo = new HashMap<String,String>();
			fileInfo.put("attchFileSn",attchFileSn);
			fileInfo.put("attchFileDtlsSn","1");
			fileInfo.put("fileClf",fileClf);
			fileInfo.put("svrFilePath",svrFilePath);
			fileInfo.put("svrFileNm",svrFileNm);
			fileInfo.put("localFileNm",localFileNm[1]);
			fileInfo.put("extnsn",extnsn);
			fileInfo.put("fileSize",Long.toString(fileSize));
			
			fileInfo.put("SESS_USER_ID",localFileNm[0]);
			
			result.add(fileInfo);
			
			cmmnService.insertAttchFile(result);
			
			param.put("SESS_USER_ID", localFileNm[0]);
			param.put("LOG_DE"	   , DateUtil.getSysDatenf());
			param.put("ATTCH_FILE_SN", attchFileSn);
			
			cmmnService.updatePairingLogInfo(param);
		}
	}
	
	
	/**
	 * 푸시 알람 클릭 후 진입 시 notification 읽음 처리
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value="/readNotification.do", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void readNotification(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		System.out.println("##########################################");
		System.out.println(String.valueOf(param.get("SESS_USER_ID")));
		System.out.println("##########################################");
		
		param.put("USER_ID", String.valueOf(param.get("SESS_USER_ID")));
		
		appDashboardService.updateNotification(param);
	}

	@RequestMapping( value="/checkMeasrAmount.do", method=RequestMethod.POST)
	public Map<String,Object> checkMeasrAmount(@ModelAttribute Map<String,Object> param, ModelMap model) {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int result = 404;
		try {
			String cmd = param.get("CMD").toString();
			if (cmd.equals("CHKSUGAR")) {
				result = 1031;
				rsMap = cmmnService.getBloodSugar(param);
			} else if (cmd.equals("CHKPRESSURE")) {
				result = 1041;
				rsMap =  cmmnService.getBloodPress(param);
			} else if (cmd.equals("CHKBDCOMP")) {
				result = 1051;
				rsMap =  cmmnService.getWeight(param);
			} else if (cmd.equals("CHKACTCNT")) {
				result = 1061;
				rsMap =  cmmnService.getActCnt(param);
			} else if (cmd.equals("CHKACTTIME")) {
				result = 1071;
				rsMap =  cmmnService.getActTime(param);
			}
		} catch (Exception e) {
		}
		rsMap.put("result", result);
		return rsMap;
	}
	
	@RequestMapping( value="/checkPoint.do", method=RequestMethod.POST)
	public Map<String,Object> checkPoint(@ModelAttribute Map<String,Object> param, ModelMap model) {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int result = 404;
		try {
			String cmd = param.get("CMD").toString();
			if (cmd.equals("CHKPOINT")) {
				result = 1081;
				rsMap = cmmnService.getPoint(param);
			}
		} catch (Exception e) {
		}
		rsMap.put("result", result);
		return rsMap;
	}
	
	@RequestMapping( value="/exeMission.do", method=RequestMethod.POST)
	public Map<String,Object> exeMission(@ModelAttribute Map<String,Object> param, ModelMap model) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int result = 404;
		try {
			String cmd = param.get("CMD").toString();
			String missionCdDtls = "01";
			param.put("SVC_NO", "1");
			param.put("MISSION_CD_DTLS", missionCdDtls);
			
			if (cmd.equals("WATMISSION")) {
				int savedWaterCount = missionService.getWaterCount(param);
				int waterCount = savedWaterCount + 1;
				result = 1301;
				
				param.put("MISSION_CD", "M007");
				param.put("WATER_CONT", waterCount);
				
				Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
				
				if(savedWaterCount < 8) {
					if(waterCount > 7){
						param.put("MISSION_SUCC_YN", "Y");
						missionService.mergeWaterCount(param);
						if(missionService.thisMissionCompleteYn(param).equals("Y")){
							if(missionService.selectGetMonthMissionPoint(param)>=155){
								// 포인트 미부여
							} else {
								if(missionService.getMissionPoint(param) == 0) {
									param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
									missionService.insertMissionPoint(param);
								}
							}
						}
					} else if(waterCount < 8){
						param.put("MISSION_SUCC_YN", "N");
						missionService.mergeWaterCount(param);
					}
				}
			} else {
				if (cmd.equals("MORMISSION")) {
					missionCdDtls = "01";
					result = 1091;
				} else if (cmd.equals("AFNMISSION")) {
					missionCdDtls = "02";
					result = 1101;
				} else if (cmd.equals("EVNMISSION")) {
					missionCdDtls = "03";
					result = 1201;
				}
				param.put("MISSION_CD", "M006");
				param.put("MISSION_CD_DTLS", missionCdDtls);
				param.put("MISSION_SUCC_YN", 'Y');
	
				Map<String, Object> missionInf = missionService.selectMissionPopInf(param);
				if(missionService.getMealCount(param) < 1) {
					missionService.insertClickMissionMngt(param);
					rsMap.put("MISSION_SUCC_YN", missionService.thisMissionCompleteYn(param));
					if(rsMap.get("MISSION_SUCC_YN").equals("Y")){
						if(missionService.getMissionPoint(param) == 0) {
							param.put("OBTAIN_POINT", missionInf.get("ALWNC_POINT"));
							missionService.insertMissionPoint(param);
						}
					}
				}
			}
		} catch (Exception e) {
		}
		rsMap.put("result", result);
		return rsMap;
	}
	
	@RequestMapping( value="/remainMission.do", method=RequestMethod.POST)
	public Map<String,Object> remainMission(@ModelAttribute Map<String,Object> param, ModelMap model) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int result = 404;
		try {
			String cmd = param.get("CMD").toString();
			if (cmd.equals("REMMISSION")) {
				result = 1401;
				rsMap.put("missioncode", cmmnService.remainMission(param));
			}
		} catch (Exception e) {
		}
		rsMap.put("result", result);
		return rsMap;
	}
	
	/**
	 * 공통코드 조회
	 * @param codeId
	 * @return rsList
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectCmmnCdUseYn.do")
	public Map<String,Object> selectCmmnCdUseYn(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = cmmnService.selectCmmnCdUseYn(param);
		
		return rsMap;
	}
}
