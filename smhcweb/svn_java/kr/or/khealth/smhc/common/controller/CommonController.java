package kr.or.khealth.smhc.common.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;











import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.DateUtil;
import kr.or.khealth.smhc.common.util.EgovResourceCloseHelper;
import kr.or.khealth.smhc.common.util.EgovWebUtil;
import kr.or.khealth.smhc.common.util.FileUtil;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import kr.or.khealth.smhc.smhcweb.sv.service.ForecastService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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


//	@Resource(name= "web.tg.HealthExamMngtService")
//	private HealthExamMngtService healthExamMngtService;
	
	@Resource(name = "fileUtil")
	protected FileUtil fileUtil;

	@Resource(name="common.pushService")
	private PushService pushService;
	
	@Resource(name="common.forecastService")
	private ForecastService forecastService;

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
		Iterator<Entry<String, MultipartFile>> imgFileChkItr = files.entrySet().iterator();
		MultipartFile file;
		int imgFileChkCnt = 0;
		int docFileChkCnt = 0;
		int MovFileChkCnt = 0;
		//20160928 윤봉훈 - 화면에서 삭제한 파일은 파일 상세 순번으로 TN_CM_ATTCH_FILE TABLE의 USE_YN을 N값으로 UPDATE 처리
		String attchFileSn = StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
		String delFile = StringUtil.nvl(String.valueOf(param.get("attchFileDel")));
		if(!"".equals(attchFileSn) && !"".equals(delFile)){
			List<Map<String,String>> iter = StringUtil.makeStringToIterator(delFile);
			param.put("deleteIter", iter);
			cmmnService.updateAttchFileUseYn(param);
		}
		
		while (imgFileChkItr.hasNext()) {
			Entry<String, MultipartFile> imgEntry = imgFileChkItr.next();
			file = imgEntry.getValue();
			String localFileNm = file.getOriginalFilename();
			imgFileChkCnt += "IMG".equals(fileUtil.getFileType(localFileNm)) ? 0 : 1;	
			docFileChkCnt += "DOC".equals(fileUtil.getFileType(localFileNm)) ? 0 : 1;
			MovFileChkCnt += "MOV".equals(fileUtil.getFileType(localFileNm)) ? 0 : 1;
		}
				
		if((!files.isEmpty() && (imgFileChkCnt == 0)) || (!files.isEmpty() && (docFileChkCnt == 0)) || (!files.isEmpty() && (MovFileChkCnt == 0))) {

			int attchFileDtlsSn = 1;
			String svrFilePath = StringUtil.nvl(String.valueOf(param.get("svrFilePath")));
			String svrFileFullPath = "";
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			String chkIsDoc = "N";
			
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
						
					}else{
						attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
					}
					
				}else {	
					if("Y".equals( String.valueOf( param.get("isDOC") ) )){
						
						chkIsDoc = "Y";
						param.put("fileClf", "20");
					}else{
						if("Y".equals( String.valueOf( param.get("isMOV") ) )){
							param.put("fileClf", "30");
						}
					}
					
					
					//새로운 sn 조회
					attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
				}
			}
			
			if ("".equals(svrFilePath)) {
				svrFilePath = DateUtil.getSysDatenf();
			}
			
			svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;

			
			if(docFileChkCnt == 0) {
				svrFileFullPath = fileUtil.getFileNotDir() + File.separator + svrFilePath;
			}

			
			File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));

			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			String filePath = "";
			Map<String,String> fileInfo;

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				String localFileNm = file.getOriginalFilename();
				String fileClf = "IMG".equals(fileUtil.getFileType(localFileNm)) ? ("Y".equals( String.valueOf( param.get("isSign") ) ) ? "12" : "10") : "MOV".equals(fileUtil.getFileType(localFileNm)) ? "30" : "20";
				
				if("Y".equals( StringUtil.nvl(String.valueOf( param.get("isDOC"))))){
					fileClf = "20";
				}	
				
				
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
				String svrFileNm = attchFileSn + "_" + attchFileDtlsSn + (extnsn.toLowerCase().equals("mp4") ?  "." + extnsn : "");
				long fileSize = file.getSize();

				if (!"".equals(localFileNm)) {
					filePath = svrFileFullPath + File.separator + svrFileNm;
					System.out.println("-------------------------- upload ------------------------------");
		            System.out.println(filePath);
		            System.out.println("-------------------------- upload ------------------------------");
					//이미지일 경우 파일 리사이징
					if(fileClf.equals("10")) {							
						fileUtil.resizeImage(file, filePath, extnsn, 1440, true);
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
				
				fileInfo.put("SESS_USER_ID",param.get("SESS_USER_ID").toString());  //2017.03.15 이태석 추가
				
				result.add(fileInfo);
				
				attchFileDtlsSn++;
				
				if("Y".equals(String.valueOf(param.get("isThumbNail")))  && "10".equals(fileClf)){ 
					int thumbnail_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbWidth")),"164"));
					int thumbnail_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbHeight")),"164"));
					fileUtil.getThumbnailImage(filePath, extnsn, thumbnail_width, thumbnail_height);
					
					fileInfo = new HashMap<String,String>();
					fileInfo.put("attchFileSn",attchFileSn);
					fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
					fileInfo.put("fileClf","11");
					fileInfo.put("svrFilePath",svrFilePath);
					fileInfo.put("svrFileNm",svrFileNm+"_thumb");
					fileInfo.put("localFileNm",localFileNm.substring(0,index)+"_thumb."+extnsn);
					fileInfo.put("extnsn",extnsn);
					fileInfo.put("fileSize","0");
					
					result.add(fileInfo);
					
					attchFileDtlsSn++;
				}
			}
			
			isnertCnt = cmmnService.insertAttchFile(result);
			rsMap.put("attchFileSn", attchFileSn);
			rsMap.put("rsList", result);
		}else if(imgFileChkCnt > 0){
			rsMap.put("msg", "등록 가능한 파일이 아닙니다.");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/attchFileUpload2.do", method=RequestMethod.POST)
	   public @ResponseBody Map<String,Object> attchFileUpload2(final MultipartHttpServletRequest multiRequest
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
	               System.out.println("-------------------------- upload ------------------------------");
	               System.out.println(filePath);
	               System.out.println("-------------------------- upload ------------------------------");
	               
	             //이미지일 경우 파일 리사이징
					if(fileClf.equals("10")) {							
						fileUtil.resizeImage(file, filePath, extnsn, 1440, true);
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
//	            if("Y".equals(String.valueOf(param.get("isRotate")))  && "10".equals(fileClf)){ 
	//
//	               int rotate_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("rotateWidth")),"300"));
//	               int rotate_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("rotateHeight")),"300"));
//	               
//	               fileUtil.getRotateImage(filePath, extnsn, rotate_width, rotate_height);
//	               
//	               fileInfo = new HashMap<String,String>();
//	               fileInfo.put("attchFileSn",attchFileSn);
//	               fileInfo.put("attchFileDtlsSn",String.valueOf(attchFileDtlsSn));
//	               fileInfo.put("fileClf","13");
//	               fileInfo.put("svrFilePath",svrFilePath);
//	               fileInfo.put("svrFileNm",svrFileNm+"_rotate");
//	               fileInfo.put("localFileNm",localFileNm.substring(0,index)+"_rotate."+extnsn);
//	               fileInfo.put("extnsn",extnsn);
//	               fileInfo.put("fileSize",Long.toString(fileSize));
//	               
//	               result.add(fileInfo);
//	               
//	               attchFileDtlsSn++;
//	            }
	         }
	         
	         isnertCnt = cmmnService.insertAttchFile(result);
	         rsMap.put("attchFileSn", attchFileSn);
	         rsMap.put("rsList", result);
	      }
//	      if(isnertCnt > 0){
//	         rsMap.put(MESSAGE_NAME, getMsg("common.write.succ"));
//	      }else{
//	         rsMap.put(MESSAGE_NAME, getMsg("common.write.err"));
//	      }
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

		int docFileChkCnt = 0;
		
		List<Map<String, Object>> fList = new ArrayList<Map<String, Object>>();
		if("".equals(StringUtil.nvl(String.valueOf(param.get("attchFileSn"))))){
			fList.add(param);
		}else{
			fList = cmmnService.selectAttchFile(param);
		}
		

		
		if(fList.size() > 0){
			Map<String,Object> fMap = fList.get(0);
			
			String svrFilePath   = fMap.get("SVR_FILE_PATH").toString().substring(0,4);
			String svfFileExtnsn = fMap.get("EXTNSN").toString();
			
			String downFileName = "";
			if(!"2016".equals(svrFilePath) && !"2017".equals(svrFilePath)){
				downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}else{
				downFileName = fileUtil.getFileDirOld() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}

			if("DOC".equals(fileUtil.getFileType(svfFileExtnsn)) && !"Log".equals(StringUtil.nvl(String.valueOf(param.get("pageClf"))))){
				downFileName = fileUtil.getFileNotDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");

			}
			
			
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
	}
	
	private String getBrowser(HttpServletRequest request) {
		String header =request.getHeader("User-Agent");
		if (header.contains("MSIE")) {
			return "MSIE";
		} else if(header.contains("Chrome")) {
			return "Chrome";
		} else if(header.contains("Opera")) {
			return "Opera";
		}
		return "Firefox";
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
//		if("APP".equals(String.valueOf(param.get("clientMode")))){
//			rtnUrl = "app/include/importResource";
//		}
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
	
	@RequestMapping( value="/pageViewer.do")
	public String pageViewer(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String url = StringUtil.nvl(String.valueOf(param.get("PAGE_URL")));
		url = url.replaceAll(",", "&");
		param.put("PAGE_URL",url);
		model.addAllAttributes(param);
		return "web/cm/pageViewer";
	}
	
	@RequestMapping( value="/updatePoctData.do")
	public @ResponseBody Map<String,Object> updatePoctData(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		String POCT_RECV = StringUtil.nvl(String.valueOf(param.get("POCT_RECV")));
		if(!"".equals(POCT_RECV)){
			
			try {
				
				String[] arr = POCT_RECV.split("\r");
				String examDt = "";
				for (int i = 0; i < arr.length; i++) {
					String val = arr[i];
					if(val.indexOf("MSH") == 0) param.put("MSH", arr[i]);		// INSERT
					else if(val.indexOf("PID") == 0) param.put("PID", arr[i]);	// INSERT
					else if(val.indexOf("SPM") == 0) param.put("SPM", arr[i]);	// INSERT
					else if(val.indexOf("OBR") == 0) param.put("OBR", arr[i]);	// INSERT
					else if(val.indexOf("SID") == 0) param.put("SID", arr[i]);	// INSERT
					else if(val.indexOf("OBX") == 0) {
						String[] obxVal = val.split("\\|");
						String loincCode = obxVal[3];
						String itemClf = loincCode.split("\\^")[1];
						param.put("OBX_" + itemClf, arr[i]);					// INSERT
						
						String sVal = StringUtil.nvl(obxVal[5],"");
						if("CHOL".indexOf(itemClf) > -1)		param.put("TOT_CHOL", sVal);		//UPDATE
						else if("GLU".indexOf(itemClf) > -1)	param.put("BLOOD_SUGAR", sVal);		//UPDATE
						else if("TRIG".indexOf(itemClf) > -1)	param.put("NEUTRAL_FAT", sVal);		//UPDATE
						else if("HDL".indexOf(itemClf) > -1)	param.put("HDL_CHOL", sVal);		//UPDATE
						else if("LDL".indexOf(itemClf) > -1)	param.put("LDL_CHOL", sVal);		//UPDATE
				
						examDt = obxVal[19];
					}
				}
				
				param.put("SESS_USER_ID", param.get("MANAGER_ID"));
				param.put("AUTO_MANU_CLF", "A");
				param.put("EXAM_DE", examDt.substring(0, 8));
				param.put("EXAM_DT", examDt);
				param.put("EXAM_METHOD", "POCT");
				if(arr.length > 0) {
//					healthExamMngtService.updateHealthExamBld(param);
//					healthExamMngtService.insertHealthExamDta(param);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	/**
     * 동영상 파일 정보 저장
     * @param fileNm
     * @return
     */
	@RequestMapping(value="/insertVideoAttchFile.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertVideoAttchFile(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		String attchFileSn = (String) param.get("ATTCH_FILE_SN");
		
		if("".equals(attchFileSn)){
			attchFileSn = cmmnService.selectAttchFileSnSeq();
		}			
		
		String localFileNm = (String) param.get("LOCAL_FILE_NM");
		int index = localFileNm.lastIndexOf(".");
		String extnsn = localFileNm.substring(index + 1);
		
		param.put("ATTCH_FILE_SN", attchFileSn);
		param.put("FILE_CLF", "30");
		param.put("EXTNSN", extnsn);
		
		cmmnService.insertVideoAttchFile(param);
		param.put("attchFileSn", attchFileSn);		

		//동영상 등록 시 상세 순번 조회
		String attchFileDtlsSn = cmmnService.selectAttchFileDtlsSn(param);
		rsMap.put("attchFileSn", attchFileSn);
		rsMap.put("attchFileDtlsSn", attchFileDtlsSn);

		
		return rsMap;
    }
	
	/**
     * 동영상 파일 정보 삭제
     * @param fileNm
     * @return
     */
	@RequestMapping(value="/deleteVideoAttchFile.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteVideoAttchFile(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();	
		cmmnService.deleteVideoAttchFile(param);
		
		return rsMap;
    }
	
	/**
	 * 동영상 파일 정보 삭제
	 * @param fileNm
	 * @return
	 */
	@RequestMapping(value="/callPushSend.do")
	public String callPushSend(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap.put("sndSttus", "12");
		rsMap.put("reqClf", "20");
		rsMap.put("autoManuClf", "A");
		rsMap.put("isScheduled", "true");
		List<Map<String,Object>> sendList = pushService.selectSendList(rsMap);

		if(sendList != null){
			if(sendList.size() > 0){
				if(pushMessageUtil.sendPushList(sendList)){// 푸시 전송
					pushService.updatePushHis(pushMessageUtil.getResultMap());
				}
			}
		}
		return "web/pageNavi";
	}
	
	/**
	 * 동영상 등록타입 선택 팝업(에디터)
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/ckEdVdPop.do")
	public String ckEdVdPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{		
		model.addAttribute(param);
		return "web/common/ckEdVdPop";
	}
	
	/**
	 * 이미지 등록타입 선택 팝업(에디터)
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/ckEdImgPop.do")
	public String crossEdImgPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAttribute(param);
		return "/web/common/ckEdImgPop";
	}
	
	/**
	 * 하이퍼 링크 팝업(에디터)
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/hyperLinkPop.do")
	public String hyperLinkPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAttribute(param);
		return "common/hyperLinkPop";
	}
	
	/**
	 * 메뉴 조회
	 * @param sysMode
	 * @return rsList
	 * @throws Exception 
	 */

	@RequestMapping( value="/insetExcelDownInfo.do")
	public void excelLogInsert(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

	}	
	
	/**
	 * ID 중복체크 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/idChk.do")
	public @ResponseBody Map<String, Object> idChk(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		Map<String, Object> rs = cmmnService.idChk(param);
		rsMap.put("result", rs);
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
	
	@RequestMapping(value= "/fetchForecastData.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> fetchForecastData(@RequestBody String forecastInfo) throws Exception {
	    System.out.println("컨트롤러 진입!");
	    System.out.println("받은 데이터: " + forecastInfo);
		
		System.out.println("#################################################################");
		System.out.println("### SCHEDULAR(CALL_KMA_API) START [ " + new Date() + " ] ");
		System.out.println("#################################################################");
		
        System.out.println("받은 데이터 길이: " + forecastInfo.length());
        System.out.println("받은 데이터 일부: " + (forecastInfo.length() > 100 ? forecastInfo.substring(0, 100) : forecastInfo));
        
		List<Map<String,String>> resultList = parseForecastData(forecastInfo);
		applyForecastData(resultList);
		
		System.out.println("#################################################################");
        System.out.println("### SCHEDULAR(CALL_KMA_API) END [ " + new Date() + " ] ");
        System.out.println("#################################################################");
        
        // 응답 반환
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "처리 완료");
        return response;
	}
	
	private List<Map<String, String>> parseForecastData(String forecastInfo) {
		String[] lines = forecastInfo.split("=");
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			if (line.isEmpty() || line.contains("#START7777") || line.contains("#7777END")) continue;

			if (line.matches("^\\d{12},\\s*\\d{8},.*")) {
				String[] parts = line.split(",");
				if (parts.length >= 7) {
					String regId = parts[3].trim();
					String riskLevel = parts[6].trim();
					if (regId != null && !regId.isEmpty() && riskLevel != null && !riskLevel.isEmpty()) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("TM_FC", parts[0].trim());
						map.put("TM_EF", parts[1].trim());
						map.put("STN", parts[2].trim());
						map.put("REG_ID", regId);
						map.put("IFPAR", parts[4].trim());
						map.put("IFAREA", parts[5].trim());
						map.put("ILVL", riskLevel);
						resultList.add(map);
					}
				}
			}
		}
		return resultList;
	}
	
	private void applyForecastData(List<Map<String,String>> resultList) {
		try {
			if (resultList == null || resultList.isEmpty()) {
				System.out.println("#################################################################");
				System.out.println("### SCHEDULAR(CALL_KMA_API) END [ " + new Date() + " ] 기상청 영향예보 수신 결과 없음");
				System.out.println("#################################################################");
				return;
			}
			
			System.out.println("### 영향예보 수신 DATA_CNT ===> " + resultList.size());
			
			//파싱한 첫 데이터 
			Map<String, String> mastrMap = resultList.get(0);
			String fcstAnmntTm	=	mastrMap.get("TM_FC"); 
			String fcstDt 		=	mastrMap.get("TM_EF");
			String fcstClf 		=	mastrMap.get("IFPAR");
			
			Map<String,Object> mastrParam = new HashMap<String, Object>();
			mastrParam.put("FCST_DT", fcstDt);
			mastrParam.put("FCST_CLF", fcstClf);
			mastrParam.put("FCST_ANMNT_TM", fcstAnmntTm);
			
			//예보 기준일로 가장 최근 발표 시간 조회				
		
			Map<String, String> lastFcstMap = forecastService.getLastFcstInfo(mastrParam);
			
			//최근 발표 시간이 없을 경우
			if(lastFcstMap == null || lastFcstMap.get("FCST_ANMNT_TM") == null) {
				
				//1.영향예보 FCST_SN 채번
				int fcstSn = forecastService.selectFcstSnSeq();
				mastrParam.put("FCST_SN", fcstSn);
				
				//2.영향예보 마스터 INSERT
				forecastService.mergeFcstInfo(mastrParam);
				
				//3.영향예보 상세 INSERT
				int totDataCnt = insertForecastDtls(resultList, fcstSn);
				System.out.println("### 영향예보 위험 등급 관심이상 DATA_CNT ===> " + totDataCnt);
				
				//3_1.영향예보 데이터 건수 업데이트
				mastrParam.put("FCST_DTA_CNT", totDataCnt);					
				forecastService.updateFcstDtaCnt(mastrParam); 
				
				//4.푸시 전송
				insertForecastPush(mastrParam);
				
				System.out.println("#################################################################");
				System.out.println("### SCHEDULAR(CALL_KMA_API) END [ " + new Date() + " ]");
				System.out.println("#################################################################");
				
			//수집된 시간과 최근 발표 시간이 다른 경우(추가 수집)	
			}else if(!fcstAnmntTm.equals(lastFcstMap.get("FCST_ANMNT_TM"))) {
				
				//1.영향예보 FCST_SN 채번
				int fcstSn = Integer.parseInt(String.valueOf(lastFcstMap.get("FCST_SN")));
				mastrParam.put("FCST_SN", fcstSn);
				
				//2.영향예보 마스터 UPDATE
				forecastService.mergeFcstInfo(mastrParam);
				
				//3.영향예보 상세 DELETE
				forecastService.delFcstInfoDtls(mastrParam);
				
				//4.영향예보 상세 INSERT
				int totDataCnt = insertForecastDtls(resultList, fcstSn);
				System.out.println("### 영향예보 위험 등급 관심이상 DATA_CNT ===> " + totDataCnt);
				
				//4_1.영향예보 마스터 상세 데이터 건수 업데이트
				mastrParam.put("FCST_DTA_CNT", totDataCnt);					
				forecastService.updateFcstDtaCnt(mastrParam); 
				
				//5.기존 PUSH 정보 삭제
				//5_1.SND_SN 리스트 조회
				List<Map<String,Object>> orgPushList = pushService.getFcstPushSndSnList(fcstSn);
				//5_2.SND_SN 리스트로 기등록된 푸시 정보 삭제
				for(Map<String,Object> pMap : orgPushList) {
					pushService.deletePushMasHist(pMap);
				}						
				
				//6.푸시 전송
				insertForecastPush(mastrParam);				

				System.out.println("#################################################################");
				System.out.println("### SCHEDULAR(CALL_KMA_API) END [ " + new Date() + " ]");
				System.out.println("#################################################################");
								
			}else {
				System.out.println("#################################################################");
				System.out.println("### SCHEDULAR(CALL_KMA_API) END [ " + new Date() + " ] 영향예보 발표시각 동일. 변경 없음");
				System.out.println("#################################################################");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertForecastPush(Map<String, Object> mastrParam) throws Exception {
		
		//영향예보 종류 및 위험등급 확인
		List<Map<String, Object>> riskList = forecastService.selectFcstRiskLevel(mastrParam);
		
		for (Map<String, Object> riskMap : riskList) {
			String dtlsFcstClf = (String.valueOf(riskMap.get("FCST_CLF"))).toUpperCase();
			String dtlsFcstDt = String.valueOf(mastrParam.get("FCST_DT"));
			int riskLvl = Integer.parseInt(String.valueOf(riskMap.get("RISK_LVL")));
			String sndUserId = "KMA_API";

			//1.푸시 SND_SN 채번
			String pushSndSn = pushService.selectPushMsgSeq();

			//2.푸시 템플릿 조회
			Map<String, Object> pushTempMap = new HashMap<String, Object>();
			pushTempMap.put("ORG_CD", "T001");
			pushTempMap.put("NOTICE_SN", riskLvl);
			if(dtlsFcstClf.equals("HW")) pushTempMap.put("NOTICE_CD", "FC01");
			else if(dtlsFcstClf.equals("CW")) pushTempMap.put("NOTICE_CD", "FC02");
			else {
				System.out.println("#################################################################");
				System.out.println("### SCHEDULAR(CALL_KMA_API) dtlsFcstClf: " + dtlsFcstClf);
				System.out.println("#################################################################");
				continue;
			}
			pushTempMap = pushService.getPushTemplete(pushTempMap);

			//3.푸시 마스터 INSERT
			Map<String, Object> insPushMasMap = new HashMap<String, Object>();
			insPushMasMap.put("sndSn", pushSndSn);
			insPushMasMap.put("sndUserId", sndUserId);
			insPushMasMap.put("noticeClf", "A");
			insPushMasMap.put("sndOrgCd", "T001");
			insPushMasMap.put("sndSttus", "");
			insPushMasMap.put("sndCnt", 0);
			insPushMasMap.put("msgClf", "NT");
			insPushMasMap.put("rcvClf", "I");
			insPushMasMap.put("autoManuClf", "A");
			insPushMasMap.put("reqClf", "20");
			insPushMasMap.put("resrvtDe", dtlsFcstDt);
			insPushMasMap.put("resrvtTm", "070000");
			insPushMasMap.put("fcstSn", String.valueOf(mastrParam.get("FCST_SN")));
			insPushMasMap.put("pushTitle", String.valueOf(pushTempMap.get("PUSH_TITLE")));
			insPushMasMap.put("pushCont", String.valueOf(pushTempMap.get("PUSH_CONT")));
			insPushMasMap.put("pushLinkPage", String.valueOf(pushTempMap.get("PUSH_LINK_PAGE")));
			insPushMasMap.put("noticeTitle", String.valueOf(pushTempMap.get("NOTICE_TITLE")));
			insPushMasMap.put("noticeCont", String.valueOf(pushTempMap.get("NOTICE_CONT")));
			insPushMasMap.put("noticeLinkPage", String.valueOf(pushTempMap.get("NOTICE_LINK_PAGE")));

			pushService.insertResvrtPushMas(insPushMasMap);

			//4.푸시 상세 INSERT
			Map<String, Object> insPushHisMap = new HashMap<String, Object>();
			insPushHisMap.put("sndSn", pushSndSn);
			insPushHisMap.put("sndUserId", sndUserId);
			insPushHisMap.put("riskLvl", riskLvl);
			insPushHisMap.put("fcstClf", dtlsFcstClf);
			insPushHisMap.put("fcstDt", dtlsFcstDt);
			pushService.insertFcstResvrtPushHis(insPushHisMap);

			//5.푸시 발송 대상자 카운팅
			int pushSndCnt = pushService.selectPushSendHistCnt(pushSndSn);

			//6.대상자 카운팅 마스터 테이블 업데이트
			Map<String, Object> updPushMasMap = new HashMap<String,Object>();
			updPushMasMap.put("sndCnt", pushSndCnt);
			updPushMasMap.put("sndSn", pushSndSn);
			pushService.updatePushSendCnt(updPushMasMap);
		}
	}
	
	private int insertForecastDtls(List<Map<String, String>> resultList, int fcstSn) {
		int count = 0;

		try {
			for (Map<String, String> map : resultList) {
				String riskLevel = map.get("ILVL");
				if (Integer.parseInt(riskLevel) >= 1) { // 관심 이상
					Map<String, Object> dtlsParam = new HashMap<String, Object>();
					dtlsParam.put("FCST_SN", fcstSn);
					dtlsParam.put("FCST_ANMNT_TM", map.get("TM_FC"));
					dtlsParam.put("FCST_DT", map.get("TM_EF"));
					dtlsParam.put("FCST_CLF", map.get("IFPAR"));
					dtlsParam.put("KMA_AREA_CD", map.get("REG_ID"));
					dtlsParam.put("RISK_LVL", riskLevel);

					forecastService.insertFcstInfoDtls(dtlsParam);
					count++;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
