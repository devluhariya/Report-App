package in.devl.service;

import java.util.List;

import in.devl.entity.CitizenPlan;
import in.devl.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	public List<String> getPlanName();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;

	public boolean exportPdf(HttpServletResponse response)throws Exception;
}
