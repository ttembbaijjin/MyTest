package cn.poi.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.poi.pojo.Member;

@Controller
@RequestMapping
public class PoiController {

	@Autowired
	private PoiService poiService;


	/**
	 * @FunctionName: 从数据库导出excel到本地
	 * @Description: TODO
	 * @author: zhaojiaxin
	 * @date: 2018年1月4日 下午4:56:46
	 * @param response
	 * @return
	 */
	@SuppressWarnings("resource")
	@ResponseBody
	@Transactional
	@RequestMapping("export")
	public String exportData(HttpServletResponse response) {
		HSSFWorkbook workbook = null;
		try {
			List<Member> list = poiService.queryAllMember();
			// 创建一个workbook
			workbook = new HSSFWorkbook();
			// 创建第一个sheet
			HSSFSheet sheet = workbook.createSheet();
			// 构造第一行表头
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("主键ID");
			row.createCell(1).setCellValue("性别 , 1: 男 , 2: 女 ");
			row.createCell(2).setCellValue("昵称");
			row.createCell(3).setCellValue("创建时间");

			// 循环给表单添加信息
			for (int i = 0; i < list.size(); i++) {
				Member member = list.get(i);
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(member.getId());
				row.createCell(1).setCellValue(member.getSex());
				row.createCell(2).setCellValue(member.getNickName());
				row.createCell(3).setCellValue(member.getCreateTime());
			}
			response.setContentType("application/octet-stream");
			// 响应到浏览器,即显示下载效果
			// response.setHeader("Content-Disposition",
			// "attachment;filename=member.xls");
			// OutputStream out = response.getOutputStream();

			// 导出到本地某一位置
			OutputStream out = new FileOutputStream(new File("g:\\member.xls"));
			workbook.write(out);
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*
			 * try { workbook.close(); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */
		}
		return "fail";
	}

	/**
	 * @FunctionName: 从本地excel导入数据到数据库
	 * @Description: TODO
	 * @author: zhaojiaxin
	 * @date: 2018年1月4日 下午4:57:28
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@Transactional
	@RequestMapping("import")
	public String importData(MultipartHttpServletRequest multipartHttpServletRequest,
			HttpServletResponse response) throws IOException {
		Workbook workbook = null;
		try {
			MultipartFile file = multipartHttpServletRequest.getFile("memberXls");
			File tmpFile = File.createTempFile("member", "xls");
			file.transferTo(tmpFile);
			workbook = WorkbookFactory.create(tmpFile);
			Sheet sheet = workbook.getSheetAt(0);

			List<Member> list = new ArrayList<Member>();
			// 表单第一行不读
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				// 读完之后不读空行
				if (row.getCell(0).getStringCellValue() == "" || row.getCell(0) == null) {
					break;
				}
				Member member = new Member();
				member.setId(row.getCell(0).getStringCellValue());
				member.setSex(row.getCell(1).getStringCellValue());
				member.setNickName(row.getCell(2).getStringCellValue());
				member.setCreateTime(row.getCell(3).getStringCellValue());
				
				list.add(member);
			}
			int count = poiService.saveData(list);
			
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}

	}

}
