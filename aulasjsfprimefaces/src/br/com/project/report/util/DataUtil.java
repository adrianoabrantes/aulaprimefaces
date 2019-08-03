package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
public class DataUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getDateAtualReportName() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public static String formatDateSql(Date data) {
		StringBuffer retorno = new StringBuffer();
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		retorno.append("'");
		retorno.append(dateFormat.format(data));
		retorno.append("'");
		return retorno.toString();
	}
}
