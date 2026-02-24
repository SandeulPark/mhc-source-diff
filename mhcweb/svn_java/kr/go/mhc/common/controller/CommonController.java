package kr.go.mhc.common.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.ChatService;
import kr.go.mhc.common.util.DateUtil;
import kr.go.mhc.common.util.EgovResourceCloseHelper;
import kr.go.mhc.common.util.EgovWebUtil;
import kr.go.mhc.common.util.FileUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.go.mhc.mhcweb.tg.service.BodyActObstyCnslService;
import kr.go.mhc.mhcweb.tg.service.HealthExamMngtService;
import kr.go.mhc.mhcweb.tg.service.HealthMngtCnslService;
import kr.go.mhc.mhcweb.tg.service.ServiceObjMngtService;

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

	@Resource(name= "web.tg.HealthExamMngtService")
	private HealthExamMngtService healthExamMngtService;

	@Resource(name = "fileUtil")
	protected FileUtil fileUtil;

	@Resource(name="common.pushService")
	private PushService pushService;
	
	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;
	
	@Resource(name = "web.tg.ServiceObjMngtService")
	private ServiceObjMngtService serviceObjMngtService;
	
	@Resource(name = "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;

	@Resource(name = "web.tg.BodyActObstyCnslService")
	private BodyActObstyCnslService bdyActObstyCnslService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	

	@RequestMapping( value="/multiAttchFileUpload.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> multiAttchFileUpload(final MultipartHttpServletRequest multiRequest
								, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		System.out.println("======= multiAttchFileUpload.do ==========");

		//List<MultipartFile> fileList = multiRequest.getFiles("attchFileSn0");
		List<MultipartFile> fileList = multiRequest.getFiles("uploadFiles");
		String attchFileSn = param.get("attchFileSn").toString();
		String isDoc = param.get("isDoc").toString();
		String isHealthExam = param.get("isHealthExam").toString();

		if(StringUtils.isEmpty(attchFileSn)) {	// 신규 글쓰기 
			//if(fileList.size() != 0) attchFileSn = cmmnService.selectAttchFileSnSeq();
			attchFileSn = cmmnService.selectAttchFileSnSeq();
		} else { // 글 수정
			// 전부 N 으로 업데이트
			cmmnService.updateAttchFileNoUseAll(param);

			String beforeFileDtlsSn = (String)param.get("beforeFileDtlsSn");

			if(!StringUtils.isEmpty(beforeFileDtlsSn)) {
				String[] fileDtlsSn = beforeFileDtlsSn.split("!");

				// fileDtlsSn 배열만큼 루프 돌아서 Y 로 업데이트
				for(int i=0; i<fileDtlsSn.length; i++) {
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("attchFileSn", attchFileSn);
					paramMap.put("attchFileDtlsSn", fileDtlsSn[i].replaceAll("dtls_sn_", ""));
					cmmnService.updateAttchFileUse(paramMap);
				}
			}
		}

		for(MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈
			int fileIndex = originFileName.lastIndexOf(".");
			String fileExt = originFileName.substring(fileIndex + 1);

			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);

            try {
            	// 1. 업로드한 파일데이터 DB에 기록
            	String svrFilePath = StringUtil.nvl(String.valueOf(param.get("svrFilePath")));
            	if (StringUtils.isEmpty(svrFilePath)) {
    				svrFilePath = DateUtil.getSysDatenf();
    			}
            	Map<String,Object> fileSnMap = new HashMap<>();
            	fileSnMap.put("attchFileSn", attchFileSn);
            	String attchFileDtlsSn = cmmnService.selectAttchFileDtlsSn(fileSnMap);
            	List<Map<String,String>> paramList = new ArrayList<>();
            	Map<String,String> paramMap = new HashMap<>();
            	paramMap.put("attchFileSn", attchFileSn);
				paramMap.put("attchFileDtlsSn", attchFileDtlsSn);
				paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID").toString());
				paramMap.put("fileClf", "20");
				paramMap.put("localFileNm", originFileName);
				paramMap.put("svrFilePath", svrFilePath);
				paramMap.put("svrFileNm", attchFileSn + "_" + paramMap.get("attchFileDtlsSn") + (fileExt.toLowerCase().equals("mp4") ?  "." + fileExt : ""));
				paramMap.put("extnsn", fileExt);
				paramMap.put("fileSize", String.valueOf(fileSize));
				paramList.add(paramMap);
				cmmnService.insertAttchFile(paramList);

            	// 2. 파일업로드
            	String filePath = "";
            	//String svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;            	
            	String svrFileFullPath = "";
            	if(isHealthExam.equalsIgnoreCase("Y")) {
            		svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath; 
            	}else {
            		svrFileFullPath = fileUtil.getFileNotDir() + File.separator + svrFilePath;
            	}
            	filePath = svrFileFullPath + File.separator + paramMap.get("svrFileNm");
            	System.out.println("-------------------------- upload ------------------------------");
            	System.out.println(filePath);
            	System.out.println("-------------------------- upload ------------------------------");

            	File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));

    			if (!saveFolder.exists() || saveFolder.isFile()) {
    				saveFolder.mkdirs();
    			}
    			
    			//이미지일 경우 파일 리사이징
    			if("IMG".equals(fileUtil.getFileType(originFileName))){
    				fileUtil.resizeImage(mf, filePath, fileExt, 1440, true);
    			}else {    			    			
    				mf.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
    			}

            } catch (IllegalStateException e) {
            	e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            }
		}
		// TODO : 검증해보자.
		Map<String,Object> rsMap = new HashMap<String,Object>();
		rsMap.put("attchFileSn", attchFileSn);
		return rsMap;
	}

	@RequestMapping( value="/attchFileUpload.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> attchFileUpload(final MultipartHttpServletRequest multiRequest
								, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		System.out.println("======= attchFileUpload.do ==========");
		
		int isnertCnt = 0;
		Map<String, Object> rsMap = new HashMap<String, Object>();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> imgFileChkItr = files.entrySet().iterator();
		int imgFileChkCnt = 0;
		int docFileChkCnt = 0;
		int MovFileChkCnt = 0;
		//20160928 윤봉훈 - 화면에서 삭제한 파일은 파일 상세 순번으로 TN_CM_ATTCH_FILE TABLE의 USE_YN을 N값으로 UPDATE 처리
		String attchFileSn = StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
		String delFile = StringUtil.nvl(String.valueOf(param.get("attchFileDel")));
				
		if (!"".equals(attchFileSn) && !"".equals(delFile)) {
			List<Map<String, String>> iter = StringUtil.makeStringToIterator(delFile);
			param.put("deleteIter", iter);
			cmmnService.updateAttchFileUseYn(param);
		}

		MultipartFile file;
		String svrFilePath;
		while (imgFileChkItr.hasNext()) {
			Entry<String, MultipartFile> imgEntry = imgFileChkItr.next();
			file = imgEntry.getValue();
			svrFilePath = file.getOriginalFilename();
			imgFileChkCnt += "IMG".equals(fileUtil.getFileType(svrFilePath)) ? 0 : 1;
			docFileChkCnt += "DOC".equals(fileUtil.getFileType(svrFilePath)) ? 0 : 1;
			MovFileChkCnt += "MOV".equals(fileUtil.getFileType(svrFilePath)) ? 0 : 1;
		}

		if ((files.isEmpty() || imgFileChkCnt != 0) && (files.isEmpty() || docFileChkCnt != 0)
				&& (files.isEmpty() || MovFileChkCnt != 0)) {
			if (imgFileChkCnt > 0) {
				rsMap.put("msg", "등록 가능한 파일이 아닙니다.");
			}
		} else {
			int attchFileDtlsSn = 1;
			svrFilePath = StringUtil.nvl(String.valueOf(param.get("svrFilePath")));
			String svrFileFullPath = "";
			List<Map<String, String>> result = new ArrayList<Map<String, String>>();

			String chkIsDoc = "N";

			if ("".equals(attchFileSn)) {
				attchFileSn = cmmnService.selectAttchFileSnSeq();
			} else {
				param.put("attchFileSn", attchFileSn);
				if ("Y".equals(String.valueOf(param.get("isSign")))) {
					param.put("fileClf", "12");
					// 기존 파일 정보 삭제 및 물리적 파일 삭제
										
					List<Map<String, Object>> fileList = cmmnService.selectAttchFile(param);
					if (fileList.size() > 0) {
						Map<String, Object> fileMap = fileList.get(0);
						fileUtil.deleteFile(StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_NM"))), StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_PATH"))));

						param.put("attchFileDtlsSn", String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
						cmmnService.deleteAttchFileInfo(param);

						attchFileDtlsSn = Integer.parseInt(String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));

					} else {
						attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
					}

				}else if("Y".equals(String.valueOf(param.get("isWarrent")))) { 
										
					List<Map<String, Object>> fileList = cmmnService.selectAttchFile(param);
					if (fileList.size() > 0) {
						Map<String, Object> fileMap = fileList.get(0);
						fileUtil.deleteFile(StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_NM"))), StringUtil.nvl(String.valueOf(fileMap.get("SVR_FILE_PATH"))));

						param.put("attchFileDtlsSn", String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));
						cmmnService.deleteAttchFileInfo(param);

						attchFileDtlsSn = Integer.parseInt(String.valueOf(fileMap.get("ATTCH_FILE_DTLS_SN")));

					} else {
						attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
					}
					
				}else {
					if ("Y".equals(String.valueOf(param.get("isDOC")))) {
						chkIsDoc = "Y";
						param.put("fileClf", "20");
					} else if ("Y".equals(String.valueOf(param.get("isMOV")))) {
						param.put("fileClf", "30");
					}

					//새로운 sn 조회
					attchFileDtlsSn = Integer.parseInt(cmmnService.selectAttchFileDtlsSn(param));
				}
			}
			
			if ("".equals(svrFilePath)) {
				svrFilePath = DateUtil.getSysDatenf();
			}

			svrFileFullPath = fileUtil.getFileDir() + File.separator + svrFilePath;
			
			if (docFileChkCnt == 0) {
				svrFileFullPath = fileUtil.getFileNotDir() + File.separator + svrFilePath;
			}

			File saveFolder = new File(EgovWebUtil.filePathBlackList(svrFileFullPath));
			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}

			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			String filePath = "";

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				String localFileNm = file.getOriginalFilename();
				String fileClf = "IMG".equals(fileUtil.getFileType(localFileNm)) ? ("Y".equals(String.valueOf(param.get("isSign"))) ? "12" : "10") : "MOV".equals(fileUtil.getFileType(localFileNm)) ? "30" : "20";
				
				if ("Y".equals(StringUtil.nvl(String.valueOf(param.get("isDOC"))))) {
					fileClf = "20";
				}

				//--------------------------------------
				// 원 파일명이 없는 경우 처리
				// (첨부가 되지 않은 input file type)
				//--------------------------------------
				if (!"".equals(localFileNm)) {
					int index = localFileNm.lastIndexOf(".");
					String extnsn = localFileNm.substring(index + 1);
					String svrFileNm = attchFileSn + "_" + attchFileDtlsSn + (extnsn.toLowerCase().equals("mp4") ? "." + extnsn : "");
					long fileSize = file.getSize();

					if (!"".equals(localFileNm)) {
						filePath = svrFileFullPath + File.separator + svrFileNm;
						
						//이미지일 경우 파일 리사이징
						if(fileClf.equals("10")) {							
							fileUtil.resizeImage(file, filePath, extnsn, 1440, true);
						}else {						
							file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
						}
					}

					Map<String, String> fileInfo = new HashMap<String, String>();
					fileInfo.put("attchFileSn", attchFileSn);
					fileInfo.put("attchFileDtlsSn", String.valueOf(attchFileDtlsSn));
					fileInfo.put("fileClf", fileClf);
					fileInfo.put("svrFilePath", svrFilePath);
					fileInfo.put("svrFileNm", svrFileNm);
					fileInfo.put("localFileNm", localFileNm);
					fileInfo.put("extnsn", extnsn);
					fileInfo.put("fileSize", Long.toString(fileSize));
					//로그인없이 기관등록,관리자등록 할때 추가
					try {
						fileInfo.put("SESS_USER_ID", param.get("SESS_USER_ID").toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

					result.add(fileInfo);
					attchFileDtlsSn++;

					if ("Y".equals(String.valueOf(param.get("isThumbNail"))) && "10".equals(fileClf)) {
						int thumbnail_width = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbWidth")), "164"));
						int thumbnail_height = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("thumbHeight")), "164"));
						fileUtil.getThumbnailImage(filePath, extnsn, thumbnail_width, thumbnail_height);

						fileInfo = new HashMap<String, String>();
						fileInfo.put("attchFileSn", attchFileSn);
						fileInfo.put("attchFileDtlsSn", String.valueOf(attchFileDtlsSn));
						fileInfo.put("fileClf", "11");
						fileInfo.put("svrFilePath", svrFilePath);
						fileInfo.put("svrFileNm", svrFileNm + "_thumb");
						fileInfo.put("localFileNm", localFileNm.substring(0, index) + "_thumb." + extnsn);
						fileInfo.put("extnsn", extnsn);
						fileInfo.put("fileSize", "0");

						result.add(fileInfo);
						attchFileDtlsSn++;
					}
				}
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

			String filePath = "";

			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				MultipartFile file = entry.getValue();
				String localFileNm = file.getOriginalFilename();

				//--------------------------------------
				// 원 파일명이 없는 경우 처리
				// (첨부가 되지 않은 input file type)
				//--------------------------------------
				if (!"".equals(localFileNm)) {
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

					Map<String,String> fileInfo = new HashMap<String,String>();
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

		String attchFileSn = StringUtil.nvl(String.valueOf(param.get("attchFileSn")));
		String attchFileDtlsSn = StringUtil.nvl(String.valueOf(param.get("attchFileDtlsSn")));
		
		attchFileSn = attchFileSn.replaceAll("[^0-9]", "");
		attchFileDtlsSn = attchFileDtlsSn.replaceAll("[^0-9]", "");
		
		param.put("attchFileSn", attchFileSn);
		param.put("attchFileDtlsSn", attchFileDtlsSn);
		
		int docFileChkCnt = 0;

		List<Map<String, Object>> fList = new ArrayList<Map<String, Object>>();
		if("".equals(StringUtil.nvl(String.valueOf(param.get("attchFileSn"))))){
			fList.add(param);
		}else{
			fList = cmmnService.selectAttchFile(param);
		}


		
		if(fList.size() > 0){
			Map<String,Object> fMap = fList.get(0);

//			String sysdate 	     = SimpleDateUtil.getSysDate("yyyyMMdd").substring(0,4);
			String svrFilePath   = fMap.get("SVR_FILE_PATH").toString().substring(0,4);
			String svfFileExtnsn = fMap.get("EXTNSN").toString();
			
			String downFileName = "";
//			if(sysdate.equals(svrFilePath)){
			if(!"2016".equals(svrFilePath) && !"2017".equals(svrFilePath)){
				downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}else{
				downFileName = fileUtil.getFileDirOld() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");
			}

			if("DOC".equals(fileUtil.getFileType(svfFileExtnsn)) && !"Log".equals(StringUtil.nvl(String.valueOf(param.get("pageClf"))))){
				downFileName = fileUtil.getFileNotDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");

			}
			
			if(param.get("errReportPage") !=null){
				if(param.get("errReportPage").equals("Y")) {
					downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");

				}
			}
			
			if(param.get("healthExamReq") !=null){
				if(param.get("healthExamReq").equals("Y")) {
					downFileName = fileUtil.getFileDir() + File.separator + (String)fMap.get("SVR_FILE_PATH") + File.separator + (String)fMap.get("SVR_FILE_NM");

				}
			}
			
			System.out.println("------------------------------------------------------------------------");
			System.out.println(downFileName);
			System.out.println("------------------------------------------------------------------------");


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
		} else {
			return header.contains("Opera") ? "Opera" : "Firefox";
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
	 * 채팅방 목록 조회
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
	public void insertChatRoom(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		Map<String, String> cnslSn = chatService.selectCnslSn();
		param.putAll(cnslSn);
		chatService.insertChatCnsl(param);		//상담 마스터 저장
		chatService.insertChatMastr(param);		//채팅 마스터 저장
		chatService.insertChatRoom(param);		//방 저장


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

	@RequestMapping( value="/pageViewer.do")
	public String pageViewer(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		List<Map<String, Object>> cnslAttchList = intensiveBodyActObstyCnslService.getCnslAttchList(param);
		param.put("cnslAttchList", cnslAttchList);
		model.addAllAttributes(param);
		return "web/cm/pageViewer";
	}

	@RequestMapping( value="/updatePoctData.do")
	public @ResponseBody Map<String,Object> updatePoctData(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{

		String POCT_RECV = StringUtil.nvl(String.valueOf(param.get("POCT_RECV")));
		if(!"".equals(POCT_RECV)){

			try {

				String[] arr = POCT_RECV.split("\r");
//				String managerId = "";
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
//						String sVal = StringUtil.nvl(obxVal[5],"0"); //20170508 윤봉훈 - 요청으로 인한 널처리 수정
						if("CHOL".indexOf(itemClf) > -1)		param.put("TOT_CHOL", sVal);		//UPDATE
						else if("GLU".indexOf(itemClf) > -1)	param.put("BLOOD_SUGAR", sVal);		//UPDATE
						else if("TRIG".indexOf(itemClf) > -1)	param.put("NEUTRAL_FAT", sVal);		//UPDATE
						else if("HDL".indexOf(itemClf) > -1)	param.put("HDL_CHOL", sVal);		//UPDATE
						else if("LDL".indexOf(itemClf) > -1)	param.put("LDL_CHOL", sVal);		//UPDATE

//						managerId = obxVal[16];
						examDt = obxVal[19];
					}
				}

				param.put("SESS_USER_ID", param.get("MANAGER_ID"));
				param.put("AUTO_MANU_CLF", "A");
				param.put("EXAM_DE", examDt.substring(0, 8));
				param.put("EXAM_DT", examDt);
				param.put("EXAM_METHOD", "POCT");
//				System.out.println("param ===================>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+param);
				if(arr.length > 0) {
					healthExamMngtService.updateHealthExamBld(param);
					healthExamMngtService.insertHealthExamDta(param);
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

		if(sendList != null && sendList.size() > 0 && pushMessageUtil.sendPushList(sendList)){
			pushService.updatePushHis(pushMessageUtil.getResultMap());
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
	@RequestMapping(value= "/crossEdVdPop.do")
	public String crossEdVdPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAttribute(param);
		return "common/crossEdVdPop";
	}

	/**
	 * 이미지 등록타입 선택 팝업(에디터)
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/crossEdImgPop.do")
	public String crossEdImgPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAttribute(param);
		return "common/crossEdImgPop";
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
	
	
	@RequestMapping(value="/downloadRsnPop.do")
	public String downloadRsnPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		System.out.println("param ===> " + param);
		model.addAttribute("id", param.get("id"));
		model.addAttribute("col", param.get("col"));
		model.addAttribute("menuCd", param.get("menuCd"));
		model.addAttribute("privacyInfoTrgtCnt", param.get("privacyInfoTrgtCnt"));
		
		model.addAttribute(param);
		return "common/downloadRsnPop";
	}
	
	/**
     * 다운로드 사유 저장
     * @param fileNm
     * @return
     */
	@RequestMapping(value="/insertDownloadRsn.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertDownloadRsn(@ModelAttribute Map<String,Object> param, ModelMap model, HttpServletRequest req) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String ip = req.getRemoteAddr();
		
		param.put("CONNECT_IP", ip);
		
		System.out.println("param ==> " + param);
		Map<String, String> menuMap = new HashMap<String,String>();
		String menuCd = (String) param.get("menuCd")==null? (req.getSession().getAttribute("menuCd")==null?"": (String) req.getSession().getAttribute("menuCd")) : (String) param.get("menuCd");
		
		String pMenuUrl = "";
		String menuUrl  = "";
		
		if("".equals(menuCd)){
			param.put("CONNECT_URL", req.getRequestURI()); 
		}else if(!"".equals(menuCd) || !menuCd.equals(null)){
			param.put("menuCd", menuCd);
			menuMap = cmmnService.selectCmmnMenuInfo(param);
			pMenuUrl = param.get("MENU_URL") == null ? "" : (String) param.get("MENU_URL");	//파라미터 url
			menuUrl = menuMap.get("MENU_URL") == null ? "" : menuMap.get("MENU_URL");			//메뉴코드 조회 url
			
			if(!req.getRequestURI().equals(menuUrl)){
				menuUrl = pMenuUrl != "" ? pMenuUrl : ("/pageNavi.do" == req.getRequestURI().trim() ? menuUrl : req.getRequestURI());
			}
		}
		
		
		try {
			cmmnService.insertDownloadRsn(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e) {
			e.printStackTrace();
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
    }
	
	@RequestMapping(value="/insertExcelDown.do", method=RequestMethod.POST)
	public @ResponseBody void insertExcelDown(@ModelAttribute Map<String,Object> param, ModelMap model, HttpServletRequest req) throws Exception{
	}
	
	
	/**
     * 자기 관리군 영역별 상담 완료 처리
	 * 2025년 군분류 개편
     * @param 
     * @return
     */
	@RequestMapping(value="/completeSelfMngtCnsl.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String,Object> completeSelfMngtCnsl(@ModelAttribute Map<String,Object> param, ModelMap model, HttpServletRequest req) throws Exception{

		System.out.println("##################################### completeSelfMngtCnsl 진입 ");
		System.out.println("##################################### completeSelfMngtCnsl param > " + param.toString());

		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	
			List<Map<String, Object>> rsList = new ArrayList<Map<String,Object>>();
	
			//임시(테스트용)
			/*param.put("USER_ID", "11141114");
			param.put("SESS_USER_ID", "11141114");
			param.put("SVC_MNGT_NO", "K0010428");
			param.put("GENDER", "F");
			param.put("SN", 1);
			param.put("TRGT_YY", "2025");
			param.put("LST_DML_ID", "SYSTEM");
			*/
			
			//임시(테스트용)
			/*param.put("USER_ID", "21141062");
			param.put("SESS_USER_ID", "21141062");
			param.put("SVC_MNGT_NO", "K0012374");
			param.put("GENDER", "M");
			param.put("SN", 1);
			param.put("TRGT_YY", "2025");
			param.put("LST_DML_ID", "SYSTEM");*/
	
			//기본 파라미터 설정		
			String userId = StringUtil.nvl(String.valueOf(param.get("USER_ID")),"");
			String orgCd = StringUtil.nvl(String.valueOf(param.get("ORG_CD")),"");
			String svcMngtNo = StringUtil.nvl(String.valueOf(param.get("SVC_MNGT_NO")),"");
			String gender = StringUtil.nvl(String.valueOf(param.get("GENDER")),"");
			String trgtYy = StringUtil.nvl(String.valueOf(param.get("TRGT_YY")),"");
			String surveySn = StringUtil.nvl(String.valueOf(param.get("SN")),"");
			String gclasCd = StringUtil.nvl(String.valueOf(param.get("GCLAS_CD")),"");
			String lstDmlId = "SYSTEM";
			int sn = Integer.parseInt(StringUtil.nvl(String.valueOf(param.get("SN")),""));
			String cnslDe = sdf.format(new Date());
	
			// 초기설문 && 자가관리군(G373) 일 때만 영역별상담 디폴트 값 insert 진행
			if(surveySn.equals("1") && gclasCd.equals("G373")){
				System.out.println("========== 영역별 상담 처리 시작 ==========");
		        System.out.println("USER_ID : "+userId+" SVC_MNGT_NO : "+svcMngtNo+", CNSL_DE : "+cnslDe);
		        System.out.println("param : "+param.toString());
	
				//1. 영양 영역별 상담 처리
				//CNSL_SN 확인
				String cnslSn = serviceObjMngtService.getCnslSn(param);
				param.put("CNSL_SN", cnslSn);
	
				//유저 정보 확인
				Map<String, String> uMap = serviceObjMngtService.getUserInfo(param);
				String bmi = String.valueOf(uMap.get("BMI"));
				String weight = String.valueOf(uMap.get("WEIGHT"));
	
				//설문조사 답변 리스트
				Map<String,Object> surMap = new HashMap<String,Object>();
				rsList = serviceObjMngtService.getSelfMngtSurveyList(param);
				for (Map<String, Object> row : rsList) {
					surMap.put(String.valueOf(row.get("SERVEY_CD")), String.valueOf(row.get("ANSWR_CONT_1")));
				}
	
				//영양 영역별 상담 처리
				Map<String, Object> nurMap = new HashMap<String, Object>();
				nurMap.put("CNSL_NO", sn);
				nurMap.put("CNSL_SN", cnslSn);
				nurMap.put("CNSL_ITEM_CLF", "20");
				nurMap.put("USER_ID", userId);
				nurMap.put("SESS_USER_ID", lstDmlId);
				nurMap.put("CNSL_DE", cnslDe);
	
				//실천 미션 설정 저장
				Map<String, Object> schMap = new HashMap<String, Object>();
				schMap.put("CUR_WEEK_NO", "0");
				schMap.put("SESS_ORG_CD", "T001"); // 일단 모바일헬스케어팀 T001 으로 넣었는데 SESS_ORG_CD 에 이 값을 넣는게 맞는지?
				schMap.put("SVC_MNGT_NO", svcMngtNo);
				schMap.put("SESS_USER_ID", lstDmlId);
				serviceObjMngtService.insertCreatePractMissionSch(schMap);
	
				//신체활동 수준 설정(1일 이하 : 비활동적(M100/F100), 2일 : 저활동적(M111/F112), 3일 : 활동적(M125/F127), 4일 이상 : 매우 활동적(M148/F145))
				int actDateCnt = Integer.parseInt(String.valueOf(surMap.get("L1203062"))); //설문조사 값
				String PA = (actDateCnt <= 1) ? (gender.equals("M") ? "M100" : "F100") 	// 비활동적
						: (actDateCnt == 2) ? (gender.equals("M") ? "M111" : "F112") 	// 저활동적
						: (actDateCnt == 3) ? (gender.equals("M") ? "M125" : "F127")	// 활동적
						: (gender.equals("M") ? "M148" : "F145");						// 매우활동적
				nurMap.put("PA", PA);
	
				//1일 에너지 필요량 계산
				Map<String, String> rsNeedAm = serviceObjMngtService.changeNeedam(nurMap);
				nurMap.put("DAY_ENG_NEED_AM", rsNeedAm.get("CAL_ENG_NEED_AM"));
				nurMap.put("CAL_DAY_ENG_NEED_AM", rsNeedAm.get("CTRL_ENG_NEED_AM"));
	
				//목표 체중 조절 비율 설정
				double BMI = Double.parseDouble(bmi);
				//체중조절율 설정(BMI 값 25 미만 : 0%(P000), 25~29.9 : -5%(M005), 30이상 : -10%(M010))
				String WEIGHT_CTRL_PER = BMI < 25 ? "P000" : (BMI < 30 ? "M005" : "M010");
				double WEIGHT_CTRL_PER_NUM = BMI < 25 ? 0 : (BMI < 30 ? 5 : 10);
				nurMap.put("WEIGHT_CTRL_PER", WEIGHT_CTRL_PER);
	
				//목표 1일 에너지 필요량 계산
				Map<String, String> objNeedAm = serviceObjMngtService.changeObjNeedam(nurMap);
				nurMap.put("CAL_OBJ_DAY_ENG_NEED_AM", objNeedAm.get("OBJ_CAL_ENG_NEED_AM"));
				nurMap.put("OBJ_DAY_ENG_NEED_AM", objNeedAm.get("OBJ_CTRL_ENG_NEED_AM"));
	
				//목표체중 계산
				double WEIGHT = Double.parseDouble(weight);
				int OBJ_WEIGHT = (int)Math.round(WEIGHT - (WEIGHT * WEIGHT_CTRL_PER_NUM/100));
				nurMap.put("WEIGHT", WEIGHT);
				nurMap.put("OBJ_WEIGHT", OBJ_WEIGHT);
				System.out.println("USER_ID : "+ userId+", BMI : "+bmi+", WEIGHT : "+weight);
	
				//섭취량 설정(설문조사 식생활 항목 실천 체크값)
				nurMap.put("GR_INTAKE_CNT", Integer.parseInt(String.valueOf(surMap.get("L120403010")))); // L120403010 : 곡류
				nurMap.put("MT_INTAKE_CNT", Integer.parseInt(String.valueOf(surMap.get("L120403070")))); // L120403070 : 고기류
				nurMap.put("VG_INTAKE_CNT", Integer.parseInt(String.valueOf(surMap.get("L120403020")))); // L120403020 : 채소류
				nurMap.put("FR_INTAKE_CNT", Integer.parseInt(String.valueOf(surMap.get("L120403030")))); // L120403030 : 과일류
				nurMap.put("MK_INTAKE_CNT", Integer.parseInt(String.valueOf(surMap.get("L120403040")))); // L120403040 : 우유,유제품
				nurMap.put("SG_INTAKE_CNT", 0); // 유지 당류는 0으로
	
				//추가 정보
				nurMap.put("FRST_MEAL_CRCTN", "");
				nurMap.put("SCND_MEAL_CRCTN", "");
				nurMap.put("EVAL_CONT", "");
				nurMap.put("MEMO", "");
				nurMap.put("AUTO_SEND_MID_YN", "N");
				nurMap.put("CHRONIC_DISEASES_YN", "N");
	
				//영양 영역별 상담 저장
				serviceObjMngtService.updateCnslNurtInfo(nurMap);
				System.out.println("USER_ID :"+userId+" / 영양 상담 정보 저장 완료");
	
				//2. 운동 영역별 상담 처리
				Map<String, Object> actMap = new HashMap<String, Object>();
				actMap.put("CNSL_SN", cnslSn);
				actMap.put("CNSL_NO", sn);
				actMap.put("USER_ID", userId);
				actMap.put("SESS_USER_ID", lstDmlId);
				actMap.put("SVC_MNGT_NO", svcMngtNo);
				actMap.put("CNSL_DE", cnslDe);
	
				// 운동시간에 따른 소모 칼로리 계산
				int actTm = 30; // 운동시간 30 분
				double minCal = 0.0175 * 6 * WEIGHT;
				int countActCal = (int)Math.round(actTm * minCal);
	
				// 목표 심박 계산용 나이 조회
				Map<String, Object> ageList = bdyActObstyCnslService.getHRCalAge(param);
				int calAge = Integer.parseInt(ageList.get("CAL_AGE").toString());
	
				// 검진데이터에서 안정심박수 가져오기 > js 에서 심박 없으면 70으로 계산함
				String pulse = StringUtil.nvl(String.valueOf(uMap.get("PULSE")), ""); // 맥박 (건강검진데이터)
				if(pulse.isEmpty()) pulse = "70";
	
				// 유효안전심박(min), 목표안전심박(max) 계산
				// TODO 이 값들이 js 에서 계산 되는 것과 1 차이가 남 .... 버림과 반올림의 차이인가
				int minHeartRate = Math.round((220 - calAge) * 60/100);
				int maxHeartRate = Math.round((220 - calAge) * 70/100);
	
	
				// 권장운동값, 칼로리, 분  계산
				actMap.put("ACT_PRSCRPT_CLF", "S"); // 설문을 통한 처방(S)
				actMap.put("DAY_ACT_CAL", countActCal); // 소모칼로리
				actMap.put("DAY_ACT_TM", 30);  // 운동 시간
				actMap.put("WALK_CNT", 8000); // 1일 목표 걸음수
				actMap.put("ACT_VALID_LIMIT", 60); // 유효한계 -> js 에서 60% 를 기본 셋팅값으로
				actMap.put("ACT_SAFETY_LIMIT", 70); // 안전한계 -> js 에서 70% 를 기본 셋팅값으로
				actMap.put("CALM_HR", pulse); // 안정심박수 검진결과에서 심박수 가져와야함
				actMap.put("HR_CLF", "MAX"); // 최대 심박수 공식(MAX)
				actMap.put("VALID_OBJ_HR", minHeartRate); // 유효 안전 심박수 (최저)
				actMap.put("SAFETY_OBJ_HR", maxHeartRate); // 목표 안전 심박수 (최대)
				actMap.put("AUTO_SEND_MID_YN", "N"); // 중간검진 대체 발송여부
				actMap.put("RECOM_EXCS_CD", "SAA0188"); // 추천운동 - 빠르게 걷기
	
				// 운동 영역별 상담 처리
				Map<String, String> cntMap = bdyActObstyCnslService.getCnslHistCnt(param);
				String histCnt = String.valueOf(cntMap.get("CNSL_CNT"));
				if("0".equals(histCnt)){
					bdyActObstyCnslService.updateBodyObstyCnsl(actMap);
				}
				bdyActObstyCnslService.getSaveActCnsl(actMap);
				System.out.println("USER_ID :"+userId+" / 운동 상담 정보 저장 완료");
				//3. 건강 영역별 상담 처리
				Map<String, Object> helMap = new HashMap<String, Object>();
				helMap.put("CNSL_SN", cnslSn);
				helMap.put("CNSL_NO", sn);
				helMap.put("USER_ID", userId);
				helMap.put("SESS_USER_ID", lstDmlId);
				helMap.put("SVC_MNGT_NO", svcMngtNo);
				helMap.put("NOSMK_CNSL_CONT", "");
				helMap.put("MEMO", "");
				helMap.put("CNCT_YN", "N");
				helMap.put("CNCT_CONT", "");
				helMap.put("CNSL_DE", cnslDe);
				helMap.put("DRUG_MISSION_YN", "N");
				helMap.put("INTENSIVE_CNSL_YN", "N");
				helMap.put("NODRINK_CNSL_YN", "N");
				helMap.put("AUTO_SEND_MID_YN", "N");
	
				healthMngtCnslService.updateCnslContMngt(helMap);
				healthMngtCnslService.updateCnsl(helMap);
				
				System.out.println("USER_ID :"+userId+" / 건강 상담 정보 저장 완료");

		        rsMap.put("result", "success");
		        System.out.println("========== 영역별 상담 처리 완료 ==========");
	
			}else {
				System.out.println("USER_ID : "+param.get("USER_ID")+", GCLAS_CD : "+param.get("GCLAS_CD")+", 자가관리군이 아닙니다.");
				rsMap.put("result", "success");
				rsMap.put("message", "surveySn != 1 / gClsCd != G373");
			}
		} catch (Exception e) {
	        rsMap.put("result", "error");
	        rsMap.put("message", e.getMessage());
		}
		
		return rsMap;
	}
}
