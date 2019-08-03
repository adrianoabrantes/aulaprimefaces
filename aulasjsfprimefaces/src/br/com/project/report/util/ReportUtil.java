package br.com.project.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
@SuppressWarnings("deprecation")
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String UNDERLINE = "_";
	private static final String FOLDER_REPORT = "/reports";
	private static final String SUBREPORT_FOLDER = "subreport_folder";
	private static final String EXTENCION_ODS = "ods";
	private static final String EXTENCION_XLS = "xls";
	private static final String EXTENCION_PDF = "pdf";
	private static final String EXTENCION_HTML = "html";
	private static String SEPARATOR = File.separator;
	private static final int REPORT_SHEET_OPEN_OFFICE = 1;
	private static final int REPORT_EXCEL = 2;
	private static final int REPORT_PDF = 3;
	private static final int REPORT_HTML = 4;
	private static final String POINT = ".";
	private StreamedContent returnFile = null;
	private String locationFileReport = null;
	private JRExporter typeFileExport = null;
	private String extFileExport = "";
	private String locationSubReportFolder = "";
	private File fileGeneration = null;

	public StreamedContent generationReport(List<?> listDataBeanCollectionReport, HashMap paramReport,
			String nameReportJasper, String nameReportOutput, int typeReport) throws Exception {

		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanCollectionReport);

		FacesContext context = FacesContext.getCurrentInstance();

		context.responseComplete();

		ServletContext sContext = (ServletContext) context.getExternalContext().getContext();

		String locationReport = sContext.getRealPath(FOLDER_REPORT);

		File file = new File(locationReport + SEPARATOR + nameReportJasper + POINT + "jasper");

		if (locationReport == null || (locationReport != null && locationReport.isEmpty())) {
			locationReport = this.getClass().getResource(FOLDER_REPORT).getPath();
			SEPARATOR = "";
		}

		paramReport.put("REPORT_PARAMETERS_IMG", locationReport);

		String locationFileJasper = locationReport + SEPARATOR + nameReportJasper + POINT + "jasper";

		JasperReport reportJasper = (JasperReport) JRLoader.loadObjectFromFile(locationFileJasper);

		locationSubReportFolder = locationReport + SEPARATOR;
		paramReport.put(SUBREPORT_FOLDER, locationSubReportFolder);

		JasperPrint printJasper = JasperFillManager.fillReport(reportJasper, paramReport, jrbcds);

		switch (typeReport) {
		case REPORT_SHEET_OPEN_OFFICE:
			typeFileExport = new JROdtExporter();
			extFileExport = EXTENCION_ODS;
			break;

		case REPORT_EXCEL:
			typeFileExport = new JRXlsExporter();
			extFileExport = EXTENCION_XLS;
			break;

		case REPORT_PDF:
			typeFileExport = new JRPdfExporter();
			extFileExport = EXTENCION_PDF;
			break;

		case REPORT_HTML:
			typeFileExport = new JRHtmlExporter();
			extFileExport = EXTENCION_HTML;
			break;

		default:
			typeFileExport = new JRPdfExporter();
			extFileExport = EXTENCION_PDF;
			break;
		}

		nameReportOutput += UNDERLINE + DataUtil.getDateAtualReportName();

		locationFileReport = locationReport + SEPARATOR + nameReportOutput + POINT + extFileExport;

		fileGeneration = new File(locationFileReport);

		typeFileExport.setParameter(JRExporterParameter.JASPER_PRINT, printJasper);

		typeFileExport.setParameter(JRExporterParameter.OUTPUT_FILE, fileGeneration);

		typeFileExport.exportReport();

		fileGeneration.deleteOnExit();

		InputStream contentReport = new FileInputStream(fileGeneration);

		returnFile = new DefaultStreamedContent(contentReport, "application/" + extFileExport,
				nameReportOutput + POINT + extFileExport);

		return returnFile;
	}
}
