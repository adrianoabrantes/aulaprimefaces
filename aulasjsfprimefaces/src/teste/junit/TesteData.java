package teste.junit;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DataUtil;

/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
public class TesteData {

	@Test
	public void testData() {
		try {
			assertEquals("02082019", DataUtil.getDateAtualReportName());
			assertEquals("'2019-08-02'", DataUtil.formatDateSql(Calendar.getInstance().getTime()));

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
