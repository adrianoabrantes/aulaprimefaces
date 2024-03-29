package br.com.framework.util;

import java.io.Serializable;
/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
public class UtilFramework implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}
	
}
