package in.devl.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.devl.entity.CitizenPlan;
import in.devl.repo.CitizenPlanRepository;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	@Autowired
	private CitizenPlanRepository planRepo;
	
	public void generate(HttpServletResponse response,List<CitizenPlan> plans, File f) throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
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
	}
}
