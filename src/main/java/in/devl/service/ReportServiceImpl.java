package in.devl.service;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.devl.entity.CitizenPlan;
import in.devl.repo.CitizenPlanRepository;
import in.devl.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CitizenPlanRepository planRepo;
	@Override
	public List<String> getPlanName() {
		List<String> planName = planRepo.getPlanName();
		return planName;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> planStatus = planRepo.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null!= request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//Convert String to LocalDate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		if(null!= request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//Convert String to LocalDate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
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
		List<CitizenPlan> records = planRepo.findAll();
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
		
		
//		FileOutputStream fos = new FileOutputStream(new File("plans.xls"));
//		workbook.write(fos);
//		workbook.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return true;
		
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Citizen Plans Info", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
          
        document.add(p);
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f,3.0f,3.0f,3.0f, 3.0f});
        table.setSpacingBefore(10);
        table.addCell("Id");
        table.addCell("Citizen Name");
        table.addCell("Citizen Last Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Benefit Amount");
        table.addCell("Start Date");
        table.addCell("End Date");
        List<CitizenPlan> plans = planRepo.findAll();
        for(CitizenPlan plan : plans) {
        	table.addCell(String.valueOf(plan.getCitizenId()));
            table.addCell(plan.getCitizenName());
            table.addCell(plan.getCitizenLastName());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlanStatus());
            if(null!=plan.getBenefitAmt()) {
            table.addCell(String.valueOf(plan.getBenefitAmt()));
            }else {
            	table.addCell("N/A");
            }
            table.addCell(plan.getPlanStartDate()+"");
            table.addCell(plan.getPlanEndDate()+"");
        }
        
        document.add(table);
        document.close();
		return false;
	}

}
