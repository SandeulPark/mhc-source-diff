package kr.or.khealth.smhc.common;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





//import kr.go.mhc.mhcweb.yt.service.YouthSynFitEvalService;


import kr.or.khealth.smhc.common.util.DateUtil;
import kr.or.khealth.smhc.common.util.ExcelWriter;
import kr.or.khealth.smhc.smhcweb.cm.service.MainService;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractView;


public class ExcelView extends AbstractView {
	@Resource(name="mainService")
	private MainService mainService;
	
//	@Resource(name = "web.cm.NutriCodeMngtService")
//	private NutriCodeMngtService nutriCodeMngtService;
//
//	@Resource(name = "web.tg.CallingMaterialService")
//	private CallingMaterialService callingMaterialService;


//	@Resource(name ="web.yt.YouthSynFitEvalService")
//	private YouthSynFitEvalService youthSynFitEvalService;
	
	
	public ExcelView() {
		super();
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// super.render(model, request, response);
		System.out.println("start ExcelView!!!");

		List<ExcelWriter> excelList = (List<ExcelWriter>) model.get("excelList");
		String fileNm = (String) model.get("fileNm");

		if (model.get("fileNm") == null) {
			System.out.println("fileNm not exist");
			return;
		}

		String orgFileName = URLEncoder.encode(fileNm, "UTF-8");
		String fileName = "attachment;filename=" + orgFileName + ".xls";

		response.setHeader("Content-Disposition", fileName);

		HSSFWorkbook workbook = new HSSFWorkbook();

		Font defaultFont = workbook.createFont();
		defaultFont.setFontHeightInPoints((short) 10);
		defaultFont.setFontName("돋움");

		// 헤더스타일
		CellStyle HeadStyle = workbook.createCellStyle();
		HeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HeadStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		HeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HeadStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		HeadStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HeadStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HeadStyle.setFont(defaultFont);

		// 본문스타일
		CellStyle BodyStyle = workbook.createCellStyle();
		BodyStyle.setWrapText(true);
		BodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		BodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		BodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setFont(defaultFont);

		int cnt = 0;
		for (ExcelWriter excel : excelList) {
			List<List<String>> list = excel.getExcelContentList();
			String sheetNm = excel.getSheetNm() == null ? "Sheet" + (++cnt)
					: excel.getSheetNm();

			if (list.size() > 0) {
				HSSFSheet sheet = workbook.createSheet(sheetNm);
				// 데이터 입력
				for (int s = 0; s < list.size(); s++) {
					HSSFRow row = sheet.createRow(s);
					List<String> contentList = list.get(s);

					for (int i = 0; i < contentList.size(); i++) {
						String content = contentList.get(i);
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(new HSSFRichTextString(content));

						if (s == 0) {
							cell.setCellStyle(HeadStyle);// 헤더스타일
						} else {
							cell.setCellStyle(BodyStyle);// 본문스타일
						}
					}
				}

				// 컬럼 Width
				for (int i = 0; i <= list.get(0).size(); i++) {
					sheet.autoSizeColumn(i);
				}
			}

		}

		ServletOutputStream os = response.getOutputStream();
		workbook.write(os);
		os.flush();

	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		// TODO Auto-generated method stub

	}

	// 2016.08.31 이태석 추가 - 그리드에서 넘어오는 데이터 List<Map<String, Object>> 가공 
	public List<Map<String, Object>> excelExportData(Map param) {
		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
		String ADDR = "";
		String[] TEL_NO = {};
		String[] MOBILE_NO = {};
		String BIRTH = "";
		String GENDER = "";
		String EXAM_CLF = "";
		String CNCT_CLF = "";
		String WORK_CLF = "";
		String MOBILE_OS = "";
		String examVisitDate = "";
		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dataLangth; i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (int j = 0; j < dataKeys.length; j++) {
				if (dataKeys[j].equals("ADDR")) {
					ADDR = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (ADDR.length() == 0) {
						paramMap.put(dataKeys[j], "");
					} else {
						paramMap.put(dataKeys[j], ADDR);
					}
					paramMap.put("ADDR_DTLS", "");
				} else if (dataKeys[j].equals("BIRTH")) {
					BIRTH = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					paramMap.put(dataKeys[j], BIRTH);
				} else if (dataKeys[j].equals("TEL_NO")) {
					TEL_NO = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().split("-", 3);
					if (param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
						paramMap.put(dataKeys[j] + "_1", "");
						paramMap.put(dataKeys[j] + "_2", "");
						paramMap.put(dataKeys[j] + "_3", "");
					} else {
						paramMap.put(dataKeys[j] + "_1", TEL_NO[0]);
						paramMap.put(dataKeys[j] + "_2", TEL_NO[1]);
						paramMap.put(dataKeys[j] + "_3", TEL_NO[2]);
					}
				} else if (dataKeys[j].equals("MOBILE_NO")) {
					MOBILE_NO = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().split("-", 3);
					paramMap.put(dataKeys[j] + "_1", MOBILE_NO[0]);
					paramMap.put(dataKeys[j] + "_2", MOBILE_NO[1]);
					paramMap.put(dataKeys[j] + "_3", MOBILE_NO[2]);
				} else if (dataKeys[j].equals("GENDER")) {
					GENDER = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					String gen = "F";
					if (GENDER.equals("남")) {
						gen = "M";
					}
					paramMap.put(dataKeys[j], gen);
				} else if (dataKeys[j].equals("EXAM_CLF")) {
					EXAM_CLF = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (EXAM_CLF.equals("공단")) {
						EXAM_CLF = "10";
					} else if (EXAM_CLF.equals("자체")) {
					}
					paramMap.put(dataKeys[j], EXAM_CLF);
				} else if (dataKeys[j].equals("CNCT_CLF")) {
					CNCT_CLF = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (CNCT_CLF.equals("국가건강검진")) {
						CNCT_CLF = "10";
					} else if (CNCT_CLF.equals("보건소내연계")) {
						CNCT_CLF = "20";
					} else if (CNCT_CLF.equals("보건소외연계")) {
						CNCT_CLF = "30";
					} else if (CNCT_CLF.equals("기타")) {
						CNCT_CLF = "90";
					}
					paramMap.put(dataKeys[j], CNCT_CLF);
				} else if (dataKeys[j].equals("WORK_CLF")) {
					WORK_CLF = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (WORK_CLF.equals("직장가입자")) {
						WORK_CLF = "10";
					} else if (WORK_CLF.equals("지역")) {
						WORK_CLF = "20";
					} else if (WORK_CLF.equals("직장피부양자")) {
						WORK_CLF = "30";
					} else if (WORK_CLF.equals("의료급여")) {
						WORK_CLF = "40";
					}
					paramMap.put(dataKeys[j], WORK_CLF);
				} else if (dataKeys[j].equals("MOBILE_OS")) {
					MOBILE_OS = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (MOBILE_OS.equals("IOS")) {
						MOBILE_OS = "IOS";
					} else if (MOBILE_OS.equals("안드로이드")) {
						MOBILE_OS = "AND";
					}
					paramMap.put(dataKeys[j], MOBILE_OS);
				} else if (dataKeys[j].equals("EXAM_DE")) {
					examVisitDate = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					paramMap.put(dataKeys[j], examVisitDate);
					if (examVisitDate.length() != 0) {
						paramMap.put("PROC_STTUS", "20");
					}
				} else if (dataKeys[j].equals("VISIT_EXPT_DE")) {
					examVisitDate = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					paramMap.put(dataKeys[j], examVisitDate);
				} else {
					if (param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
						paramMap.put(dataKeys[j], "");
					} else {
						paramMap.put(dataKeys[j], param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());
					}
				}
			}
			paramMap.put("SESS_USER_NM", param.get("SESS_USER_NM"));
			paramMap.put("SESS_AUTH_TYPE", param.get("SESS_AUTH_TYPE"));
			paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD"));
			paramMap.put("SESS_SVC_MNGT_NO", param.get("SESS_SVC_MNGT_NO"));
			paramMap.put("SESS_CMNTY_CD", param.get("SESS_CMNTY_CD"));
			paramMap.put("SESS_LOGIN_ID", param.get("SESS_LOGIN_ID"));
			paramList.add(paramMap);
		}
		return paramList;
	}
	
	// 2016.09.01 이태석 추가 - 엑셀 데이터 유효성 체크 
	public Map<Integer, List<Integer>> excelDataValidChk(Map param) {
		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
		String BIRTH = "";
		String GENDER = "";
		String ZIP_CD = "";
		String MOBILE_NO = "";
		String[] MOBILE_NO_split = {};
		String VISIT_EXPT_DE = "";
		String[] VISIT_EXPT_DE_split = {};
		String CNCT_CLF = "";
		String HEALTH_CENTER_SVC_JOIN_YN = "";
		String MOBILE_OS = "";
		String TEST_USER_YN = "";
		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
		Map<Integer, List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();
		DateUtil dateUtil = new DateUtil();
		for (int i = 0; i < dataLangth; i++) {
			List<Integer> colNumList = new ArrayList<Integer>();
			for (int j = 0; j < dataKeys.length; j++) {
				if (dataKeys[j].equals("REG_YN")) {
					Map<String, Object> regParam = new HashMap();
					String userNm = param.get("gridExcelData[" + i + "][USER_NM]").toString().trim();
					String[] mobileNo = param.get("gridExcelData[" + i + "][MOBILE_NO]").toString().trim().split("\\-", 3);
					String birth = param.get("gridExcelData[" + i + "][BIRTH]").toString().trim();
					int rsInt = 0;
					if (userNm.length() != 0 & birth.length() == 8 & mobileNo.length == 3) {
						regParam.put("USER_NM", userNm);
						regParam.put("BIRTH", birth);
						regParam.put("MOBILE_NO_1", mobileNo[0]);
						regParam.put("MOBILE_NO_2", mobileNo[1]);
						regParam.put("MOBILE_NO_3", mobileNo[2]);
						try {
							//rsInt = mainService.selectTrgterCheck(regParam);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (rsInt != 0) {

							colNumList.add(j);
						}
					}

				} else if (dataKeys[j].equals("USER_NM") & param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
					colNumList.add(j);
					// }else if(dataKeys[j].equals("ADDR") &
					// param.get("gridExcelData[" + i + "][" + dataKeys[j] +
					// "]").toString().trim().length() == 0){
					// colNumList.add(j); // 2019.02.25 유준영 주소 필수값 취소
				} else if (dataKeys[j].equals("BIRTH")) {
					BIRTH = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					boolean birthNumChk = Pattern.matches("^[0-9]*$", BIRTH.replaceAll("\\.", ""));
					if (BIRTH.length() != 8) {
						colNumList.add(j);
					} else if (dateUtil.getDaysBetween(BIRTH, BIRTH, "yyyyMMdd") == -999) {
						colNumList.add(j);
					} else if (!birthNumChk) {
						colNumList.add(j);
					} else if (dateUtil.calculateManAge(BIRTH) < 19) { // 2019.05.07
																		// 유준영
																		// 만나이
																		// 유효성
																		// 검사 추가
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("GENDER")) {
					GENDER = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (!GENDER.equals("남") & !GENDER.equals("여")) {
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("ZIP_CD")) {
					ZIP_CD = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					boolean zipcdNumChk = Pattern.matches("^[0-9]*$", ZIP_CD);
					if (ZIP_CD.length() != 0 & ZIP_CD.length() != 5) {
						colNumList.add(j);
					} else if (!zipcdNumChk) {
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("MOBILE_NO")) {
					MOBILE_NO = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					boolean mobilenoNumChk = Pattern.matches("^[0-9]*$", MOBILE_NO.replaceAll("-", ""));
					if (MOBILE_NO.length() == 0) {
						colNumList.add(j);
					} else if (MOBILE_NO.indexOf("-") == -1) {
						colNumList.add(j);
					} else if (MOBILE_NO.indexOf("-") != -1) {
						MOBILE_NO_split = MOBILE_NO.split("-", 3);
						if (!mobilenoNumChk) {
							colNumList.add(j);
						} else if (MOBILE_NO_split.length != 3) {
							colNumList.add(j);
						} else if (MOBILE_NO_split[0].length() != 3) {
							colNumList.add(j);
						} else if (MOBILE_NO_split[0].length() == 3 & !MOBILE_NO_split[0].equals("010") & !MOBILE_NO_split[0].equals("011") & !MOBILE_NO_split[0].equals("017") & !MOBILE_NO_split[0].equals("018") & !MOBILE_NO_split[0].equals("019") & !MOBILE_NO_split[0].equals("016")) {
							colNumList.add(j);
						} else if (MOBILE_NO_split[1].length() != 4 & MOBILE_NO_split[1].length() != 3) {
							colNumList.add(j);
						} else if (MOBILE_NO_split[2].length() != 4) {
							colNumList.add(j);
						}
					}
				} else if (dataKeys[j].equals("LOGIN_ID")) {
					Map<String, Object> chkParam = new HashMap();
					String loginId = param.get("gridExcelData[" + i + "][LOGIN_ID]").toString().trim();

					if (loginId.length() != 0) {
						chkParam.put("LOGIN_ID", loginId);
						int chkInt = 0;
						try {
							//chkInt = mainService.selectLoginIdCheck(chkParam);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (chkInt != 0) {
							colNumList.add(j);
						} else {
							for (int k = 0; k < dataLangth; k++) {
								if (i != k & loginId.equals(param.get("gridExcelData[" + k + "][LOGIN_ID]").toString().trim())) {
									colNumList.add(j);
								}
							}
						}
					}
				} else if (dataKeys[j].equals("VISIT_EXPT_DE")) {
					VISIT_EXPT_DE = param.get("gridExcelData[" + i + "][VISIT_EXPT_DE]").toString().trim();
					// boolean visitexptdeNumChk = false;
					if (VISIT_EXPT_DE.length() != 0) {
						if (VISIT_EXPT_DE.indexOf(".") == -1) {
							colNumList.add(j);
						}
						VISIT_EXPT_DE_split = VISIT_EXPT_DE.split("\\.", 3);
						if (VISIT_EXPT_DE_split.length != 3) {
							colNumList.add(j);
						} else if (dateUtil.getDaysBetween(VISIT_EXPT_DE_split[0] + VISIT_EXPT_DE_split[1] + VISIT_EXPT_DE_split[2], VISIT_EXPT_DE_split[0] + VISIT_EXPT_DE_split[1] + VISIT_EXPT_DE_split[2], "yyyyMMdd") == -999) {
							colNumList.add(j);
						} else {
							if (VISIT_EXPT_DE_split[0].length() != 4) {
								colNumList.add(j);
							} else if (VISIT_EXPT_DE_split[1].length() != 2) {
								colNumList.add(j);
							} else if (VISIT_EXPT_DE_split[2].length() != 2) {
								colNumList.add(j);
							}
						}
						// if(!visitexptdeNumChk){
						// colNumList.add(j);
						// }
					}
				} else if (dataKeys[j].equals("CNCT_CLF")) {
					CNCT_CLF = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (CNCT_CLF.length() != 0) {
						if (!CNCT_CLF.equals("국가건강검진") & !CNCT_CLF.equals("보건소내연계") & !CNCT_CLF.equals("보건소외연계") & !CNCT_CLF.equals("기타")) {
							colNumList.add(j);
						}
					} else {
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("HEALTH_CENTER_SVC_JOIN_YN")) {
					HEALTH_CENTER_SVC_JOIN_YN = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (HEALTH_CENTER_SVC_JOIN_YN.length() != 0) {
						if (!HEALTH_CENTER_SVC_JOIN_YN.equals("N") & !HEALTH_CENTER_SVC_JOIN_YN.equals("Y")) {
							colNumList.add(j);
						}
					} else {
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("MOBILE_OS")) {
					MOBILE_OS = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (MOBILE_OS.length() != 0) {
						if (!MOBILE_OS.equals("안드로이드") & !MOBILE_OS.equals("IOS")) {
							colNumList.add(j);
						}
					} else {
						colNumList.add(j);
					}
				} else if (dataKeys[j].equals("TEST_USER_YN")) {
					TEST_USER_YN = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
					if (TEST_USER_YN.length() != 0) {
						if (!TEST_USER_YN.equals("N") & !TEST_USER_YN.equals("Y")) {
							colNumList.add(j);
						}
					} else {
						colNumList.add(j);
					}
				}
			}
			if (colNumList.size() != 0) {
				validChkMap.put(i, colNumList);
			}
		}
		if (validChkMap.size() == 0) {
			validChkMap = null;
		}
		return validChkMap;
	}
		
		
	//조리식품 영양코드 신청 - 엑셀 데이터 유효성 체크.
	public Map<Integer,List<Integer>> excelValidFoodCookReqChk(Map param) {

		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));			
		String REQ_FOOD_NM = "";
		String FOOD_RESERV_MAIN = "";
		String FOOD_RESERV_SRC = "";
		String RECIPE = "";
		
		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
		Map<Integer,List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < dataLangth; i++) {
			List<Integer> colNumList = new ArrayList<Integer>();
			for (int j = 0; j < dataKeys.length; j++) {
				if (dataKeys[j].equals("REQ_FOOD_NM")) {
					REQ_FOOD_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");

					if(REQ_FOOD_NM.length() == 0){
						colNumList.add(j);
					}
				}else if (dataKeys[j].equals("FOOD_RESERV_MAIN")) {
					FOOD_RESERV_MAIN = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					if(FOOD_RESERV_MAIN.length() == 0){
						colNumList.add(j);
					}
				}else if (dataKeys[j].equals("FOOD_RESERV_SRC")) {
					FOOD_RESERV_SRC = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					if(FOOD_RESERV_SRC.length() == 0){
						colNumList.add(j);
					}
				}else if (dataKeys[j].equals("RECIPE")) {
					RECIPE = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
					if(RECIPE.length() == 0){
						colNumList.add(j);
					}
				}
			}
			if(colNumList.size() != 0){
				validChkMap.put(i, colNumList);
			}		
		}
		if(validChkMap.size() == 0){
			validChkMap = null;		
		}	
		
		
		return validChkMap;
	}
	
	//조리식품 영양코드 일괄 신청 등록
	public List<Map<String, Object>> excelExportDataReqList(Map param) {
		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
		
		String REQ_FOOD_NM = "";
		String SERCH_NM = "";
		String FOOD_RESERV_MAIN = "";
		String FOOD_RESERV_SRC = "";
		String RECIPE = "";
		String ORIGIN = "";
		String RMK = "";
		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");

		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dataLangth; i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (int j = 0; j < dataKeys.length; j++) {
				if(dataKeys[j].equals("REQ_FOOD_NM")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("SERCH_NM")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("FOOD_RESERV_MAIN")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("FOOD_RESERV_SRC")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("RECIPE")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("ORIGIN")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}else if(dataKeys[j].equals("RMK")){
					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
						paramMap.put(dataKeys[j],"");
					}else{
						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
					}
				}
			}
			paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
			paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD"));
			paramMap.put("SESS_LOGIN_ID", param.get("SESS_LOGIN_ID"));
			paramList.add(paramMap);
		}
		return paramList;
	}	
	
		
	//조리식품 영양코드 신청 결과 등록 - 엑셀 데이터 유효성 체크	
//	public Map<Integer,List<Integer>> excelValidFoodCookCompChk(Map param) throws Exception {
//
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));			
//		String INS_FOOD_CD = "";
//		String CHK_FOOD_NM = "";
//		String FOOD_NM     = "";
//		String CLASSIFICATION_NM 	= "";
//		String FOOD_CATE_NM 	 	= "";
//		String AMOUNT_NM 		 	= "";
//		String AMOUNT_NM2 		 	= "";
//		String ONCE_AMOUNT_CLF_NM 	= "";
//		String CAL = "";
//		
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		Map<Integer,List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();
//
//		for (int i = 0; i < dataLangth; i++) {
//			List<Integer> colNumList = new ArrayList<Integer>();
//			for (int j = 0; j < dataKeys.length; j++) {
//				if (dataKeys[j].equals("INS_FOOD_CD")) {
//					INS_FOOD_CD = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(INS_FOOD_CD.length() == 0){
//						colNumList.add(j);
//					}
//				}else if (dataKeys[j].equals("FOOD_NM")) {
//					CHK_FOOD_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//					
//					if(CHK_FOOD_NM.length() == 0){
//						colNumList.add(j);
//					}else{
//						param.put("CHK_FOOD_CD", INS_FOOD_CD);
//						param.put("CHK_FOOD_NM", CHK_FOOD_NM);
//							
//						Map<String, Object> chkMap = nutriCodeMngtService.getNewCookFoodCompChk(param);
//
//						if("N".equals(chkMap.get("CHK_YN"))) {
//							colNumList.add(j);
//						}
//					}
//				}else if (dataKeys[j].equals("AMOUNT_NM")) {
//					AMOUNT_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//			
//				}else if(dataKeys[j].equals("AMOUNT_NM2")){
//					AMOUNT_NM2 = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(AMOUNT_NM.length() == 0 && AMOUNT_NM2.length() == 0){
//						colNumList.add(j);
//					}else if(AMOUNT_NM.length() != 0 && AMOUNT_NM2.length() != 0){
//						colNumList.add(j);						
//					}	
//				}else if (dataKeys[j].equals("ONCE_AMOUNT_CLF_NM")) {
//					ONCE_AMOUNT_CLF_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//					
//					if(ONCE_AMOUNT_CLF_NM.length() == 0){
//						colNumList.add(j);
//					}else{
//						param.put("DIV", "CLF");
//						param.put("VALID_PARAM", ONCE_AMOUNT_CLF_NM);
//						
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//						if("N".equals(chkMap.get("CHK_YN"))) {
//							colNumList.add(j);
//						}						
//					}
//				}else if (dataKeys[j].equals("CAL") || dataKeys[j].equals("CARB") || dataKeys[j].equals("PROTEIN") || dataKeys[j].equals("PROTEIN") || dataKeys[j].equals("PROTEIN")|| 
//						  dataKeys[j].equals("FAT") || dataKeys[j].equals("VGT_FAT") || dataKeys[j].equals("ANI_FAT") || dataKeys[j].equals("WATER") || dataKeys[j].equals("POLLEN")||
//						  dataKeys[j].equals("TOTAL_DIETARY_FIBER") || dataKeys[j].equals("SOLUBLE_DIETARY_FIBER") || dataKeys[j].equals("INSOLUBLE_DIETARY_FIBER") || dataKeys[j].equals("VITA_A") || dataKeys[j].equals("RETINOL")||						
//						  dataKeys[j].equals("BETA_CAROTENE") || dataKeys[j].equals("VITA_D") || dataKeys[j].equals("VITA_E") || dataKeys[j].equals("VITA_K") || dataKeys[j].equals("THIAMIN")||
//						  dataKeys[j].equals("RIBOFLAVIN") || dataKeys[j].equals("NIACIN") || dataKeys[j].equals("VITA_C") || dataKeys[j].equals("VITA_B6") || dataKeys[j].equals("FOLATE")||										 
//						  dataKeys[j].equals("VITA_B12") || dataKeys[j].equals("PANTOTHENIC_ACID") || dataKeys[j].equals("BIOTIN") || dataKeys[j].equals("CALCIUM") || dataKeys[j].equals("VGT_CALCIUM")||
//						  dataKeys[j].equals("ANI_CALCIUM") || dataKeys[j].equals("PHOSPHORUS") || dataKeys[j].equals("FERRIC") || dataKeys[j].equals("VGT_FERRIC") || dataKeys[j].equals("ANI_FERRIC")||						
//						  dataKeys[j].equals("SALT") || dataKeys[j].equals("CHLORINE") || dataKeys[j].equals("POTASSIUM") || dataKeys[j].equals("MAGNESIUM") || dataKeys[j].equals("ZINC")||
//						  dataKeys[j].equals("COPPER") || dataKeys[j].equals("FLUORINE") || dataKeys[j].equals("MANGANESE") || dataKeys[j].equals("IODINE") || dataKeys[j].equals("SELENIUM")||
//						  dataKeys[j].equals("COBALT") || dataKeys[j].equals("MOLYBDENUM") || dataKeys[j].equals("CHOLESTEROL")){
//				}
//			}
//			if(colNumList.size() != 0){
//				validChkMap.put(i, colNumList);
//			}		
//		}
//		if(validChkMap.size() == 0){
//			validChkMap = null;		
//		}	
//		return validChkMap;
//	}	

//	public List<Map<String, Object>> excelExportDataCompList(Map param) throws Exception{
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
//		/*
//		String FOOD_CD = "";
//		String CLASSIFICATION = "";
//		String CLASSIFICATION_NM = "";
//		String FOOD_CATE = "";
//		String FOOD_CATE_NM = "";
//		String FOOD_NM = "";
//		String SERCH_NM = "";
//		String AMOUNT_NM = "";
//		String ONCE_AMOUNT_CLF_NM = "";
//		String CAL = "";
//		String CARB = "";
//		String PROTEIN = "";
//		String VGT_PROTEIN = "";
//		String ANI_PROTEIN = "";
//		String FAT = "";
//		String VGT_FAT = "";
//		String ANI_FAT = "";
//		String WATER = "";
//		String POLLEN = "";
//		String TOTAL_DIETARY_FIBER = "";
//		String SOLUBLE_DIETARY_FIBER = "";
//		String INSOLUBLE_DIETARY_FIBER = "";
//		String VITA_A = "";
//		String RETINOL = "";
//		String BETA_CAROTENE = "";
//		String VITA_D = "";
//		String VITA_E = "";
//		String VITA_K = "";
//		String THIAMIN = "";
//		String RIBOFLAVIN = "";
//		String NIACIN = "";
//		String VITA_C = "";
//		String VITA_B6 = "";
//		String FOLATE = "";
//		String VITA_B12 = "";
//		String PANTOTHENIC_ACID = "";
//		String BIOTIN = "";
//		String CALCIUM = "";
//		String VGT_CALCIUM = "";
//		String ANI_CALCIUM = "";
//		String PHOSPHORUS = "";
//		String FERRIC = "";
//		String VGT_FERRIC = "";
//		String ANI_FERRIC = "";
//		String SALT = "";
//		String CHLORINE = "";
//		String POTASSIUM = "";
//		String MAGNESIUM = "";
//		String ZINC = "";
//		String COPPER = "";
//		String FLUORINE = "";
//		String MANGANESE = "";
//		String IODINE = "";
//		String SELENIUM = "";
//		String COBALT = "";
//		String MOLYBDENUM = "";
//		String CHOLESTEROL = "";
//		*/
//		String ONCE_AMOUNT_CLF_NM = "";
//		String AMOUNT_NM = "";
//		String AMOUNT_NM2 = "";
//		
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
//		for (int i = 0; i < dataLangth; i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			for (int j = 0; j < dataKeys.length; j++) {
//				if(dataKeys[j].equals("INS_FOOD_CD")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CLASSIFICATION")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FOOD_CATE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FOOD_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SERCH_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("AMOUNT_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + "AMOUNT_NM2" + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "ml");								
//					}else{
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "g");									
//					}
//				/*		
//				}else if(dataKeys[j].equals("AMOUNT_NM2")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + "AMOUNT_NM" + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "g");									
//					}else{
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "ml");										
//					}	
//				*/				
//				}else if(dataKeys[j].equals("ONCE_AMOUNT_CLF_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());	
//						
//						ONCE_AMOUNT_CLF_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//						param.put("DIV", "CLF");
//						param.put("VALID_PARAM", ONCE_AMOUNT_CLF_NM);		
//
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//						if("Y".equals(chkMap.get("CHK_YN"))) {
//							paramMap.put("ONCE_AMOUNT", chkMap.get("ONCE_AMOUNT").toString());
//							paramMap.put("AMOUNT_CLF", chkMap.get("AMOUNT_CLF").toString());					
//						}								}
//				}else if(dataKeys[j].equals("CAL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"0");
//					}else{
//						paramMap.put(dataKeys[j], param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CARB")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//					
//				}else if(dataKeys[j].equals("FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("WATER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("POLLEN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("TOTAL_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SOLUBLE_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("INSOLUBLE_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_A")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("RETINOL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("BETA_CAROTENE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_D")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_E")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_K")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("THIAMIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("RIBOFLAVIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("NIACIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_C")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_B6")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FOLATE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_B12")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PANTOTHENIC_ACID")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("BIOTIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PHOSPHORUS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SALT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CHLORINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("POTASSIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MAGNESIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ZINC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("COPPER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FLUORINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MANGANESE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("IODINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SELENIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("COBALT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MOLYBDENUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CHOLESTEROL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SUGARS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SATURATED")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("TRANS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}
//			}	
//			paramMap.put("SESS_USER_ID", param.get("SESS_USER_ID"));
//			paramMap.put("SESS_ORG_CD", param.get("SESS_ORG_CD"));
//			paramMap.put("SESS_LOGIN_ID", param.get("SESS_LOGIN_ID"));
//			paramList.add(paramMap);
//		}
//		return paramList;
//	}		
	
	//가공식품 영양코드 DB 등록 - 엑셀 데이터 유효성 체크	
//	public Map<Integer,List<Integer>> excelValidFoodProcChk(Map param) throws Exception {
//		
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));			
//		String CLASSIFICATION_NM 	= "";
//		String FOOD_CATE_NM 	 	= "";
//		String AMOUNT_NM 		 	= "";
//		String AMOUNT_NM2 		 	= "";
//		String ONCE_AMOUNT_CLF_NM 	= "";
//		String CAL = "";
//		String FOOD_NM = "";
//
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		Map<Integer,List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();
//
//		for (int i = 0; i < dataLangth; i++) {
//			List<Integer> colNumList = new ArrayList<Integer>();
//			for (int j = 0; j < dataKeys.length; j++) {
//				if (dataKeys[j].equals("CLASSIFICATION_NM")) {
//					CLASSIFICATION_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(CLASSIFICATION_NM.length() == 0){
//						colNumList.add(j);
//					}
//				}else if (dataKeys[j].equals("FOOD_CATE_NM")) {
//					FOOD_CATE_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(FOOD_CATE_NM.length() == 0){
//						colNumList.add(j);
//					}else{
//						param.put("DIV", "CATE");
//						param.put("VALID_PARAM", FOOD_CATE_NM);
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//
//						if("N".equals(chkMap.get("CHK_YN"))) {
//							colNumList.add(j);
//						}
//					}
//				}else if (dataKeys[j].equals("FOOD_NM")) {
//					FOOD_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//						if(FOOD_NM.length() == 0){
//							colNumList.add(j);
//						}
//				}else if (dataKeys[j].equals("AMOUNT_NM")) {
//					AMOUNT_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//			
//				}else if(dataKeys[j].equals("AMOUNT_NM2")){
//					AMOUNT_NM2 = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(AMOUNT_NM.length() == 0 && AMOUNT_NM2.length() == 0){
//						colNumList.add(j);
//					}else if(AMOUNT_NM.length() != 0 && AMOUNT_NM2.length() != 0){
//						colNumList.add(j);						
//					}	
//					
//				}else if (dataKeys[j].equals("ONCE_AMOUNT_CLF_NM")) {
//					ONCE_AMOUNT_CLF_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//					
//					if(ONCE_AMOUNT_CLF_NM.length() == 0){
//						colNumList.add(j);
//					}else{
//						param.put("DIV", "CLF");
//						param.put("VALID_PARAM", ONCE_AMOUNT_CLF_NM);
//						
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//						if("N".equals(chkMap.get("CHK_YN"))) {
//							colNumList.add(j);
//						}						
//					}
//				}else if (dataKeys[j].equals("CAL")) {
//					CAL = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//
//					if(CAL.length() == 0){
//						colNumList.add(j);
//					}
//				}
//			}
//			if(colNumList.size() != 0){
//				validChkMap.put(i, colNumList);
//			}		
//		}
//		if(validChkMap.size() == 0){
//			validChkMap = null;		
//		}	
//		
//		return validChkMap;
//	}	
	
//	public List<Map<String, Object>> excelExportDataProcList(Map param) throws Exception {
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
//		String FOOD_CATE_NM = "";
//		String FOOD_CATE = "";
//		String ONCE_AMOUNT_CLF_NM = "";
//		
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
//		for (int i = 0; i < dataLangth; i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			for (int j = 0; j < dataKeys.length; j++) {
//				if (dataKeys[j].equals("FOOD_CATE_NM")) {
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{						
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());	
//						
//						FOOD_CATE_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//						param.put("DIV", "CATE");
//						param.put("VALID_PARAM", FOOD_CATE_NM);				
//						
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//						if("Y".equals(chkMap.get("CHK_YN"))) {
//							paramMap.put("FOOD_CATE", chkMap.get("FOOD_CATE").toString());
//						}												
//					}
//				}
//				if(dataKeys[j].equals("FOOD_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SERCH_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("AMOUNT_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + "AMOUNT_NM2" + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "ml");								
//					}else{
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "g");									
//					}
//				/*		
//				}else if(dataKeys[j].equals("AMOUNT_NM2")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + "AMOUNT_NM" + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "g");									
//					}else{
//						paramMap.put("AMOUNT",param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());		
//						paramMap.put("AMOUNT_UNIT", "ml");										
//					}	
//				*/					
//				}else if(dataKeys[j].equals("ONCE_AMOUNT_CLF_NM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());	
//						
//						ONCE_AMOUNT_CLF_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//						param.put("DIV", "CLF");
//						param.put("VALID_PARAM", ONCE_AMOUNT_CLF_NM);				
//						
//						Map<String, Object> chkMap = nutriCodeMngtService.getFoodValidChk(param);
//						if("Y".equals(chkMap.get("CHK_YN"))) {
//							paramMap.put("ONCE_AMOUNT", chkMap.get("ONCE_AMOUNT").toString());
//							paramMap.put("AMOUNT_CLF", chkMap.get("AMOUNT_CLF").toString());					
//						}						
//					}
//				}else if(dataKeys[j].equals("CAL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"0");
//					}else{
//						paramMap.put(dataKeys[j], param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CARB")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_PROTEIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//					
//				}else if(dataKeys[j].equals("FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_FAT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("WATER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("POLLEN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("TOTAL_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SOLUBLE_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("INSOLUBLE_DIETARY_FIBER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_A")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("RETINOL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("BETA_CAROTENE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_D")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_E")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_K")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("THIAMIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("RIBOFLAVIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("NIACIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_C")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_B6")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FOLATE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VITA_B12")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PANTOTHENIC_ACID")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("BIOTIN")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_CALCIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("PHOSPHORUS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("VGT_FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ANI_FERRIC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SALT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CHLORINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("POTASSIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MAGNESIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("ZINC")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("COPPER")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("FLUORINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MANGANESE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("IODINE")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SELENIUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("COBALT")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("MOLYBDENUM")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("CHOLESTEROL")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SUGARS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("SATURATED")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}else if(dataKeys[j].equals("TRANS")){
//					if(param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0){
//						paramMap.put(dataKeys[j],"");
//					}else{
//						paramMap.put(dataKeys[j],param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim());						
//					}
//				}			
//			}	
//			paramMap.put("SESS_USER_ID",  param.get("SESS_USER_ID"));
//			paramMap.put("SESS_ORG_CD",   param.get("SESS_ORG_CD"));
//			paramMap.put("SESS_LOGIN_ID", param.get("SESS_LOGIN_ID"));
//			paramList.add(paramMap);
//		}
//		return paramList;
//	}	
	
	// 2020.04.27 양현우 추가 - 엑셀 데이터 유효성 체크 
//	public Map<Integer,List<Integer>> excelValidYouthSynFitEvalChk(Map param) {
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
//		int SCORE=101;
//		String NAME ="";
//		String EXAM_SN="";
//		String BIRTH="";
//		String GENDER="";
//		String MOBILE_NO="";
//		String EXAM_DE="";
//		String HEIGHT="";
//		String WEIGHT="";
//		String BMI="";
//		String FLEX="";
//		String FLEX_SCORE="";
//		String MUSCLE_ENDURANCE="";
//		String MUSCLE_ENDURANCE_SCORE="";
//		String REACT_QUICKLY="";
//		String REACT_QUICKLY_SCORE="";
//		String QUICKNESS="";
//		String QUICKNESS_SCORE="";
//		String TOT_SCORE="";
//		String[] MOBILE_NO_SPLIT = {};
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		Map<Integer, List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();
//		for(int i=0;i<dataLangth;i++){
//		List<Integer> colNumList = new ArrayList<Integer>();
//		for( int j=0;j<dataKeys.length;j++){
//			if(dataKeys[j].equals("GENDER")){
//				GENDER = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!GENDER.equals("남")&!GENDER.equals("여")){
//					colNumList.add(j);
//				}
//			}else if(dataKeys[j].equals("USER_NM")){
//				Map<String, Object> regParam = new HashMap();
//				String userNm = param.get("gridExcelData["+i+"][USER_NM]").toString().trim();
//			    String[] mobileNo= param.get("gridExcelData["+i+"][PHONE_NUM]").toString().trim().split("\\-",3);
//				String  birth = param.get("gridExcelData["+i+"][BIRTH]").toString().trim();
//				int rsInt=0;
//				if(userNm.length()!=0 & birth.length()==8 & mobileNo.length==3){
//					regParam.put("USER_NM", userNm);
//					regParam.put("BIRTH", birth);
//					regParam.put("MOBILE_NO_1", mobileNo[0]);
//					regParam.put("MOBILE_NO_2", mobileNo[1]);
//					regParam.put("MOBILE_NO_3", mobileNo[2]);
//				}try{
//					rsInt = youthSynFitEvalService.selectYouthRegChk(regParam);
//				} catch(Exception e){
//					e.printStackTrace();
//				}if(rsInt!=1){
//					colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("FLEX")){
//				FLEX = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(FLEX)){
//					boolean FLEX_NumChk = Pattern.matches("^[0-9]*$", FLEX);
//					if(!FLEX_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int FLEX_INT = Integer.parseInt(FLEX);
//				    		if(FLEX_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				      }
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("FLEX_SCORE")){
//				FLEX_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(FLEX_SCORE)){
//					boolean FLEX_SCORE_NumChk = Pattern.matches("^[0-9]*$", FLEX_SCORE);
//					if(!FLEX_SCORE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int FLEX_SCORE_INT = Integer.parseInt(FLEX_SCORE);
//				    		if(FLEX_SCORE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("MUSCLE_ENDURANCE")){
//				MUSCLE_ENDURANCE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(MUSCLE_ENDURANCE)){
//					boolean MUSCLE_ENDURANCE_NumChk = Pattern.matches("^[0-9]*$", MUSCLE_ENDURANCE);
//					if(!MUSCLE_ENDURANCE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int MUSCLE_ENDURANCE_INT = Integer.parseInt(MUSCLE_ENDURANCE);
//				    		if(MUSCLE_ENDURANCE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("MUSCLE_ENDURANCE_SCORE")){
//				MUSCLE_ENDURANCE_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(MUSCLE_ENDURANCE_SCORE)){
//					boolean MUSCLE_ENDURANCE_SCORE_NumChk = Pattern.matches("^[0-9]*$", MUSCLE_ENDURANCE_SCORE);
//					if(!MUSCLE_ENDURANCE_SCORE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int MUSCLE_ENDURANCE_SCORE_INT = Integer.parseInt(MUSCLE_ENDURANCE_SCORE);
//				    		if(MUSCLE_ENDURANCE_SCORE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("REACT_QUICKLY")){
//				REACT_QUICKLY = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(REACT_QUICKLY)){
//					boolean REACT_QUICKLY_NumChk = Pattern.matches("^[0-9]*$", REACT_QUICKLY);
//					if(!REACT_QUICKLY_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int REACT_QUICKLY_INT = Integer.parseInt(REACT_QUICKLY);
//				    		if(REACT_QUICKLY_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("REACT_QUICKLY_SCORE")){
//				REACT_QUICKLY_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(REACT_QUICKLY_SCORE)){
//					boolean REACT_QUICKLY_SCORE_NumChk = Pattern.matches("^[0-9]*$", REACT_QUICKLY_SCORE);
//					if(!REACT_QUICKLY_SCORE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int REACT_QUICKLY_SCORE_INT = Integer.parseInt(REACT_QUICKLY_SCORE);
//				    		if(REACT_QUICKLY_SCORE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("QUICKNESS")){
//				QUICKNESS = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(QUICKNESS)){
//					boolean QUICKNESS_NumChk = Pattern.matches("^[0-9]*$", QUICKNESS);
//					if(!QUICKNESS_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int QUICKNESS_INT = Integer.parseInt(QUICKNESS);
//				    		if(QUICKNESS_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("QUICKNESS_SCORE")){
//				QUICKNESS_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(QUICKNESS_SCORE)){
//					boolean QUICKNESS_SCORE_NumChk = Pattern.matches("^[0-9]*$", QUICKNESS_SCORE);
//					if(!QUICKNESS_SCORE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int QUICKNESS_SCORE_INT = Integer.parseInt(QUICKNESS_SCORE);
//				    		if(QUICKNESS_SCORE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if (dataKeys[j].equals("TOT_SCORE")){
//				TOT_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//				if(!StringUtils.isEmpty(TOT_SCORE)){
//					boolean TOT_SCORE_NumChk = Pattern.matches("^[0-9]*$", TOT_SCORE);
//					if(!TOT_SCORE_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int TOT_SCORE_INT = Integer.parseInt(TOT_SCORE);
//				    		if(TOT_SCORE_INT>=SCORE){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//			}else if(dataKeys[j].equals("BIRTH")){
//				BIRTH = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//				boolean birth_NumChk = Pattern.matches("^[0-9]*$", BIRTH);
//			     if (BIRTH.length() != 8) {
//					colNumList.add(j);
//				} else if(!birth_NumChk){
//					colNumList.add(j);
//				}
//		}else if (dataKeys[j].equals("MOBILE_NO")) {
//			MOBILE_NO = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//			boolean mobilenoNumChk = Pattern.matches("^[0-9]*$", MOBILE_NO.replaceAll("-", ""));
//			if (MOBILE_NO.length() == 0) {
//				colNumList.add(j);
//			} else if (MOBILE_NO.indexOf("-") == -1) {
//				colNumList.add(j);
//			} else if (MOBILE_NO.indexOf("-") != -1) {
//				MOBILE_NO_SPLIT = MOBILE_NO.split("-", 3);
//				if (!mobilenoNumChk) {
//					colNumList.add(j);
//				} else if (MOBILE_NO_SPLIT.length != 3) {
//					colNumList.add(j);
//				} else if (MOBILE_NO_SPLIT[0].length() != 3) {
//					colNumList.add(j);
//				} else if (MOBILE_NO_SPLIT[0].length() == 3 & !MOBILE_NO_SPLIT[0].equals("010") & !MOBILE_NO_SPLIT[0].equals("011") & !MOBILE_NO_SPLIT[0].equals("017") & !MOBILE_NO_SPLIT[0].equals("018") & !MOBILE_NO_SPLIT[0].equals("019") & !MOBILE_NO_SPLIT[0].equals("016")) {
//					colNumList.add(j);
//				} else if (MOBILE_NO_SPLIT[1].length() != 4 & MOBILE_NO_SPLIT[1].length() != 3) {
//					colNumList.add(j);
//				} else if (MOBILE_NO_SPLIT[2].length() != 4) {
//					colNumList.add(j);
//				}
//			  }
//			} else if(dataKeys[j].equals("EXAM_DE")){
//				EXAM_DE = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().replaceAll("\\.", "");
//				boolean EXAM_DE_NumChk = Pattern.matches("^[0-9]*$", EXAM_DE);
//				if (EXAM_DE.length() != 8) {
//					colNumList.add(j);
//				} else if(!EXAM_DE_NumChk){
//					colNumList.add(j);
//				}
//			}else if(dataKeys[j].equals("HEIGHT")){
//				HEIGHT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//				if(!StringUtils.isEmpty(HEIGHT)){
//					boolean HEIGHT_NumChk = Pattern.matches("^[0-9]*$",HEIGHT.replaceAll("\\.", ""));
//					if(!HEIGHT_NumChk){
//					    colNumList.add(j);
//				    } else {
//				    	int HEIGHT_SPLIT_INT = Integer.parseInt(HEIGHT.replaceAll("\\.", ""));
//				    		if(HEIGHT_SPLIT_INT>=30000){
//				    			colNumList.add(j);
//				    		}
//				    	}
//				}else {
//					 colNumList.add(j);
//				}
//		}else if(dataKeys[j].equals("WEIGHT")){
//			WEIGHT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//			if(!StringUtils.isEmpty(WEIGHT)){
//				boolean WEIGHT_NumChk = Pattern.matches("^[0-9]*$",WEIGHT.replaceAll("\\.", ""));
//				if(!WEIGHT_NumChk){
//				    colNumList.add(j);
//			    } else {
//			    	int WEIGHT_SPLIT_INT = Integer.parseInt(WEIGHT.replaceAll("\\.", ""));
//			    		if(WEIGHT_SPLIT_INT>=30000){
//			    			colNumList.add(j);
//			    		}
//			    	}
//			}else {
//				 colNumList.add(j);
//			}
//		}else if(dataKeys[j].equals("BMI")){
//			BMI = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim();
//			if(!StringUtils.isEmpty(BMI)){
//				boolean BMI_NumChk = Pattern.matches("^[0-9]*$",BMI.replaceAll("\\.", ""));
//				if(!BMI_NumChk){
//				    colNumList.add(j);
//			    } else {
//			    	int BMI_SPLIT_INT = Integer.parseInt(BMI.replaceAll("\\.", ""));
//			    		if(BMI_SPLIT_INT>=10000){
//			    			colNumList.add(j);
//			    		}
//			    	}
//			}else {
//				 colNumList.add(j);
//			}
//		}else if(dataKeys[j].equals("EXAM_SN")){
//			EXAM_SN = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//			if(!EXAM_SN.equals("1")&!EXAM_SN.equals("2")){
//				colNumList.add(j);
//			}
//			if(StringUtils.isEmpty(EXAM_SN)){
//				colNumList.add(j);
//			}
//			Map<String, Object> regParam = new HashMap();
//			String userNm = param.get("gridExcelData["+i+"][USER_NM]").toString().trim();
//		    String[] mobileNo= param.get("gridExcelData["+i+"][PHONE_NUM]").toString().trim().split("\\-",3);
//			String  birth = param.get("gridExcelData["+i+"][BIRTH]").toString().trim();
//			String  examSn = param.get("gridExcelData["+i+"][EXAM_SN]").toString().trim();
//			int rsInt=0;
//			if(userNm.length()!=0 & birth.length()==8 & mobileNo.length==3){
//				regParam.put("USER_NM", userNm);
//				regParam.put("BIRTH", birth);
//				regParam.put("MOBILE_NO_1", mobileNo[0]);
//				regParam.put("MOBILE_NO_2", mobileNo[1]);
//				regParam.put("MOBILE_NO_3", mobileNo[2]);
//				regParam.put("EXAM_SN", examSn);
//			}try{
//				rsInt = youthSynFitEvalService.selectYouthRegChkExamSn(regParam);
//			} catch(Exception e){
//				e.printStackTrace();
//			}if(rsInt!=0){
//				colNumList.add(j);
//			}
//		}
//			if(colNumList.size() != 0){
//				validChkMap.put(i, colNumList);
//			}
//		}
//	}
//	if (validChkMap.size() == 0) {
//		validChkMap = null;
//	}
//	return validChkMap;
//	}
	
//	public List<Map<String, Object>> youthExportDataList(Map param){
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
//		String NAME ="";
//		String EXAM_SN="";
//		String BIRTH="";
//		String[] MOBILE_NO={};
//		String EXAM_DE="";
//		String HEIGHT="";
//		String WEIGHT="";
//		String BMI="";
//		String FLEX="";
//		String FLEX_SCORE="";
//		String MUSCLE_ENDURANCE="";
//		String MUSCLE_ENDURANCE_SCORE="";
//		String REACT_QUICKLY="";
//		String REACT_QUICKLY_SCORE="";
//		String QUICKNESS="";
//		String QUICKNESS_SCORE="";
//		String TOT_SCORE="";
//
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
//		for(int i=0;i<dataLangth;i++){
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			for(int j=0;j<dataKeys.length;j++){
//				if(dataKeys[j].equals("FLEX")){
//					FLEX = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(FLEX.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], FLEX);
//					}
//				}else if(dataKeys[j].equals("FLEX_SCORE")){
//					FLEX_SCORE= param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(FLEX_SCORE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], FLEX_SCORE);
//					}
//				}else if(dataKeys[j].equals("MUSCLE_ENDURANCE")){
//					MUSCLE_ENDURANCE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(MUSCLE_ENDURANCE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], MUSCLE_ENDURANCE);
//					}
//				}else if(dataKeys[j].equals("MUSCLE_ENDURANCE_SCORE")){
//					MUSCLE_ENDURANCE_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(MUSCLE_ENDURANCE_SCORE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j],MUSCLE_ENDURANCE_SCORE);
//					}
//				}else if(dataKeys[j].equals("REACT_QUICKLY")){
//					REACT_QUICKLY = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(REACT_QUICKLY.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], REACT_QUICKLY);
//					}
//				}else if(dataKeys[j].equals("REACT_QUICKLY_SCORE")){
//					REACT_QUICKLY_SCORE=param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(REACT_QUICKLY_SCORE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], REACT_QUICKLY_SCORE);
//					}
//				}else if(dataKeys[j].equals("QUICKNESS")){
//					QUICKNESS=param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(QUICKNESS.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], QUICKNESS);
//					}
//				}else if(dataKeys[j].equals("QUICKNESS_SCORE")){
//					QUICKNESS_SCORE = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(QUICKNESS_SCORE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else	{
//						paramMap.put(dataKeys[j],QUICKNESS_SCORE);
//					}
//				}else if(dataKeys[j].equals("TOT_SCORE")){
//					TOT_SCORE=param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					if(TOT_SCORE.length()==0){
//						paramMap.put(dataKeys[j], "");
//					}else {
//						paramMap.put(dataKeys[j], TOT_SCORE);
//					}
//				}else if(dataKeys[j].equals("BIRTH")){
//					BIRTH = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim().replaceAll("\\.", "");
//					paramMap.put(dataKeys[j], BIRTH);
//				}else if(dataKeys[j].equals("EXAM_DE")){
//					EXAM_DE =param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim().replaceAll("\\.", "");
//					paramMap.put(dataKeys[j], EXAM_DE);
//				}else if(dataKeys[j].equals("EXAM_SN")){
//					EXAM_SN = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					paramMap.put(dataKeys[j], EXAM_SN);
//				}else if (dataKeys[j].equals("PHONE_NUM")) {
//					MOBILE_NO = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().split("-", 3);
//					paramMap.put(dataKeys[j] + "_1", MOBILE_NO[0]);
//					paramMap.put(dataKeys[j] + "_2", MOBILE_NO[1]);
//					paramMap.put(dataKeys[j] + "_3", MOBILE_NO[2]);
//				}else if(dataKeys[j].equals("HEIGHT")){
//					HEIGHT = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					paramMap.put(dataKeys[j], HEIGHT);
//				}else if(dataKeys[j].equals("WEIGHT")){
//					WEIGHT = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					paramMap.put(dataKeys[j], WEIGHT);
//				}else if(dataKeys[j].equals("BMI")){
//					BMI = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					paramMap.put(dataKeys[j], BMI);
//				}else if(dataKeys[j].equals("USER_NM")){
//					NAME = param.get("gridExcelData["+i+"]["+dataKeys[j]+"]").toString().trim();
//					paramMap.put(dataKeys[j], NAME);
//				}
//			}
//			paramMap.put("SESS_USER_ID",  param.get("SESS_USER_ID"));
//			paramList.add(paramMap);
//		}
//		return paramList;
//	}

//	public Map<Integer, List<Integer>> callingMaterialExcelValidChk(Map param) {
//		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));
//
//		String OCCURRENCE_DT = "";       /*발생일자 rq. */
//		String REQUEST_DT = "";          /*요청일자 rq. */
////		String ORG_NM = "";              /*기관     rq. */
////		String SYSTEM_NM = "";           /*시스템명     */
////		String PUBLIC_HEALTH = "";       /*조직     rq. */
////		String USER_ID = "";             /*사번     rq. */
////		String USER_NM = "";             /*사원명   rq. */
////		String CONTROL_STANDARD = "";    /*관제기준 rq. */
////		String CALLING_REASON = "";      /*소명사유 rq. */
////		String CALLING_CONTENTS = "";    /*소명내용     */
////		String ORG_JUDGMENT = "";        /*기관판정     */
////		String ABUSE_PURPOSE = "";       /*오남용 목적  */
////		String ABUSE_TYPE = "";          /*오남용유형   */
////		String NEGLIGENCE_DEGREE = "";   /*과실정도     */
////		String JUDGMENT_OPINION = "";    /*판정의견     */
////		String rm_internal_uid
//
//		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
//		Map<Integer, List<Integer>> validChkMap = new HashMap<Integer, List<Integer>>();
//		DateUtil dateUtil = new DateUtil();
//		for (int i = 0; i < dataLangth; i++) {
//			List<Integer> colNumList = new ArrayList<Integer>();
//			for (int j = 0; j < dataKeys.length; j++) {
//                if(dataKeys[j].equals("REG_YN")){
//					Map<String, Object> regParam = new HashMap();
//					String occurrenceDt = param.get("gridExcelData[" + i + "][OCCURRENCE_DT]").toString();
//					String userId = param.get("gridExcelData[" + i + "][USER_ID]").toString();
//					String controlCd = param.get("gridExcelData[" + i + "][CONTROL_CD]").toString();
//					int rsInt = 0;
//					if (occurrenceDt.length() > 0 & userId.length() > 0 & controlCd.length() > 0) {
//						regParam.put("OCCURRENCE_DT", occurrenceDt);
//						regParam.put("USER_ID", userId);
//						regParam.put("CONTROL_CD", controlCd);
//						try {
//							rsInt = callingMaterialService.callingMaterialDupCheck(regParam);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						if (rsInt != 0) {
//							colNumList.add(j);
//						}
//					}
//				} else if(dataKeys[j].equals("OCCURRENCE_DT")) {
//                	OCCURRENCE_DT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
//					if (OCCURRENCE_DT.trim().length() == 0) {
//						/*발생일자     required */
//						colNumList.add(j);
//					} else if(dateUtil.getDaysBetween(OCCURRENCE_DT, OCCURRENCE_DT, "yyyy-MM-dd") == -999){
//						colNumList.add(j);
//				    } else {
//						String occurrenceDt = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
//						boolean dtChk = Pattern.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])", occurrenceDt);
//                        if(!dtChk){
//							colNumList.add(j);
//						}
//					}
//				} else if (dataKeys[j].equals("REQUEST_DT")){
//                	REQUEST_DT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
//					if(REQUEST_DT.trim().length() == 0){
//						/*요청일자     required */
//						colNumList.add(j);
//					} else if(dateUtil.getDaysBetween(REQUEST_DT, REQUEST_DT, "yyyy-MM-dd") == -999){
//						colNumList.add(j);
//					} else {
//						String requestDt = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
//						boolean dtChk = Pattern.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])", requestDt);
//						if(!dtChk){
//							colNumList.add(j);
//						}
//					}
//				} else if (dataKeys[j].equals("PUBLIC_HEALTH") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*조직     required */
//					colNumList.add(j);
//				} else if (dataKeys[j].equals("USER_ID") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*사번     required */
//					colNumList.add(j);
//				} else if (dataKeys[j].equals("USER_NM") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*사원명   required */
//					colNumList.add(j);
//				} else if (dataKeys[j].equals("CONTROL_CD") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*관제기준 코드 required */
//					colNumList.add(j);
//				} else if (dataKeys[j].equals("CONTROL_STANDARD") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*관제기준 required */
//					colNumList.add(j);
//				} else if (dataKeys[j].equals("CALLING_REASON") && param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString().trim().length() == 0) {
//					/*소명사유 required */
//					colNumList.add(j);
//				}
//			}
//			if (colNumList.size() != 0) {
//				validChkMap.put(i, colNumList);
//			}
//		}
//		if (validChkMap.size() == 0) {
//			validChkMap = null;
//		}
//		return validChkMap;
//	}

	public List<Map<String, Object>> callingMaterialExcelExportData(Map param) {
		int dataLangth = Integer.parseInt((String) param.get("gridExcelDataLength"));

		String OCCURRENCE_DT = "";
		String REQUEST_DT = "";
		String ORG_NM = "";
		String SYSTEM_NM = "";
		String PUBLIC_HEALTH = "";
		String USER_ID = "";
		String USER_NM = "";
		String CONTROL_CD = "";
		String CONTROL_STANDARD = "";
		String CALLING_REASON = "";
		String CALLING_CONTENTS = "";
		String ORG_JUDGMENT = "";
		String ABUSE_PURPOSE = "";
		String ABUSE_TYPE = "";
		String NEGLIGENCE_DEGREE = "";
		String JUDGMENT_OPINION = "";

		String[] dataKeys = param.get("girdExcelDataKeys").toString().split("\\,");
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dataLangth; i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (int j = 0; j < dataKeys.length; j++) {
				if (dataKeys[j].equals("OCCURRENCE_DT")) {
					OCCURRENCE_DT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], OCCURRENCE_DT);
				} else if (dataKeys[j].equals("REQUEST_DT")) {
					REQUEST_DT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], REQUEST_DT);
				} else if (dataKeys[j].equals("ORG_NM")) {
					ORG_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], ORG_NM);
				} else if (dataKeys[j].equals("SYSTEM_NM")) {
					SYSTEM_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], SYSTEM_NM);
				} else if (dataKeys[j].equals("PUBLIC_HEALTH")) {
					PUBLIC_HEALTH = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], PUBLIC_HEALTH);
				} else if (dataKeys[j].equals("USER_ID")) {
					USER_ID = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], USER_ID);
				} else if (dataKeys[j].equals("USER_NM")) {
					USER_NM = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], USER_NM);
				} else if (dataKeys[j].equals("CONTROL_CD")) {
					CONTROL_CD = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], CONTROL_CD);
				} else if (dataKeys[j].equals("CONTROL_STANDARD")) {
					CONTROL_STANDARD = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], CONTROL_STANDARD);
				} else if (dataKeys[j].equals("CALLING_REASON")) {
					CALLING_REASON = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], CALLING_REASON);
				} else if (dataKeys[j].equals("CALLING_CONTENTS")) {
					CALLING_CONTENTS = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], CALLING_CONTENTS);
				} else if (dataKeys[j].equals("ORG_JUDGMENT")) {
					ORG_JUDGMENT = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], ORG_JUDGMENT);
				} else if (dataKeys[j].equals("ABUSE_PURPOSE")) {
					ABUSE_PURPOSE = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], ABUSE_PURPOSE);
				} else if (dataKeys[j].equals("ABUSE_TYPE")) {
					ABUSE_TYPE = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], ABUSE_TYPE);
				} else if (dataKeys[j].equals("NEGLIGENCE_DEGREE")) {
					NEGLIGENCE_DEGREE = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], NEGLIGENCE_DEGREE);
				} else if (dataKeys[j].equals("JUDGMENT_OPINION")) {
					JUDGMENT_OPINION = param.get("gridExcelData[" + i + "][" + dataKeys[j] + "]").toString();
					paramMap.put(dataKeys[j], JUDGMENT_OPINION);
				}
			}

			paramList.add(paramMap);
		}
		return paramList;
	}
}