package in.devl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.devl.entity.CitizenPlan;
import in.devl.repo.CitizenPlanRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	@Autowired
	private CitizenPlanRepository planRepo;
	
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception{
		//Workbook workbook = new XSSFWorkbook();	--------> xlsx
				//Workbook workbook = new HSSFWorkbook();	--------> xls
				Workbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet("plans-data");
				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("ID");
				headerRow.createCell(1).setCellValue("Citizen Name");
				headerRow.createCell(2).setCellValue("Citizen Lastname");
				headerRow.createCell(3).setCellValue("Plan Name");
				headerRow.createCell(4).setCellValue("Plan Status");
				headerRow.createCell(5).setCellValue("Benefit Amount");
				headerRow.createCell(6).setCellValue("Start Date");
				headerRow.createCell(7).setCellValue("End Date");
				
				int dataRowIndex = 1;
				for(CitizenPlan plan : records) {
					Row dataRow = sheet.createRow(dataRowIndex);
					dataRow.createCell(0).setCellValue(plan.getCitizenId());
					dataRow.createCell(1).setCellValue(plan.getCitizenName());
					dataRow.createCell(2).setCellValue(plan.getCitizenLastName());
					dataRow.createCell(3).setCellValue(plan.getPlanName());
					dataRow.createCell(4).setCellValue(plan.getPlanStatus());
					if(null!= plan.getBenefitAmt()) {
					dataRow.createCell(5).setCellValue(plan.getBenefitAmt());
					}else {
						dataRow.createCell(5).setCellValue("N/A");
					}
					dataRow.createCell(6).setCellValue(plan.getPlanStartDate()+"");
					dataRow.createCell(7).setCellValue(plan.getPlanEndDate()+"");
					
					dataRowIndex++;	
				}
				
				
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				workbook.close();
				
				ServletOutputStream outputStream = response.getOutputStream();
				workbook.write(outputStream);
				workbook.close();
				
	}
}
