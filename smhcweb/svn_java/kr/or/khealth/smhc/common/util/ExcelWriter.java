package kr.or.khealth.smhc.common.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelWriter {

	private final static Logger logger = LoggerFactory.getLogger(ExcelWriter.class);
	private String[] titleArray;
	private String[] colNmArray;
	private List<Map<String,Object>> list;
	private String fileNm;
	private String sheetNm;
	
	public ExcelWriter(String sheetNm, String[] titleArray, String[] colNmArray, List<Map<String,Object>> list){
		this.sheetNm = sheetNm;
		this.titleArray = titleArray;
		this.colNmArray = colNmArray;
		this.list = list;
	}	
	
	public String[] getTitleArray() {
		return titleArray;
	}


	public void setTitleArray(String[] titleArray) {
		this.titleArray = titleArray;
	}


	public String[] getColNmArray() {
		return colNmArray;
	}


	public void setColNmArray(String[] colNmArray) {
		this.colNmArray = colNmArray;
	}


	public List<Map<String, Object>> getList() {
		return list;
	}


	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}


	public String getSheetNm() {
		return sheetNm;
	}


	public void setSheetNm(String sheetNm) {
		this.sheetNm = sheetNm;
	}
	
	
	public List<List<String>> getExcelContentList(){
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


	public static void downFile(HttpServletResponse response, OutputStream outputStream, List<List<String>> list, List<List<String>> list2, String fileNm) throws Exception {
		
		String orgFileName = URLEncoder.encode(fileNm, "UTF-8");
		String fileName="attachment;filename="+orgFileName+".xls";
		
		response.setHeader("Content-Disposition",fileName);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		Font defaultFont = workbook.createFont();		
		defaultFont.setFontHeightInPoints((short)10);
		defaultFont.setFontName("돋움");
		
		//헤더스타일
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
		
		//본문스타일
		CellStyle BodyStyle = workbook.createCellStyle();
		BodyStyle.setWrapText(true);
		BodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		BodyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		BodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		BodyStyle.setFont(defaultFont);
		
				
		if(list.size()>0){
			HSSFSheet firstSheet  = workbook.createSheet("Sheet1");
			//데이터 입력
			for (int s = 0; s < list.size(); s++){
				HSSFRow row = firstSheet.createRow(s);
				List<String> contentList = list.get(s);
	
				for (int i = 0; i < contentList.size(); i++){
					String content = contentList.get(i);
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(new HSSFRichTextString(content));
					
					if(s==0){
						cell.setCellStyle(HeadStyle);//헤더스타일
					}
					else{
						cell.setCellStyle(BodyStyle);//본문스타일
					}
				}
			}
			
			//컬럼 Width
			for (int i = 0; i <= list.get(0).size(); i++){
				firstSheet.autoSizeColumn(i);
			}
		}
		
		if(list2.size()>0){
			HSSFSheet secondtSheet  = workbook.createSheet("Sheet2");
			for (int s = 0; s < list2.size(); s++){
				HSSFRow row = secondtSheet.createRow(s);
				List<String> contentList2 = list2.get(s);
	
				for (int i = 0; i < contentList2.size(); i++){
					String content2 = contentList2.get(i);
					HSSFCell cell2 = row.createCell(i);
					cell2.setCellValue(new HSSFRichTextString(content2));
					
					if(s==0){
						cell2.setCellStyle(HeadStyle);//헤더스타일
					}
					else{
						cell2.setCellStyle(BodyStyle);//본문스타일
					}
				}
			}
			//컬럼 Width
			for (int i = 0; i <= list2.get(0).size(); i++){
				secondtSheet.autoSizeColumn(i);
			}
		}

		try{
			workbook.write(outputStream);
			outputStream.flush();
		}finally{
		}
	}
	

	
	
}