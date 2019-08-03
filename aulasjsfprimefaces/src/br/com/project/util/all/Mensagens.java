package br.com.project.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
public abstract class Mensagens extends FacesContext implements Serializable {

	private static final long serialVersionUID = 1L;

	public Mensagens() {

	}

	public static void responseOperation(StatusPersistencia statusPersistencia) {
		if (statusPersistencia != null && statusPersistencia.equals(statusPersistencia.SUCESSO)) {
			sucesso();
			
		} else if (statusPersistencia != null && statusPersistencia.equals(statusPersistencia.OBJETO_REFERENCIADO)) {
			msgServerityFatal(statusPersistencia.OBJETO_REFERENCIADO.toString());
			
		} else {
			erroNaOperacao();
		}
	}

	public static void msg(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}
	}

	public static void sucesso() {
		msgServerityInfor(Constante.OPERACAO_REALIZADA_COM_SUCESSO);
	}

	public static void erroNaOperacao() {
		if (facesContextValido()) {
			msgServerityFatal(Constante.ERRO_NA_OPERACAO);
		}
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}

	public static void msgServerityWarn(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}

	public static void msgServerityFatal(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}

	public static void msgServerityError(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}

	public static void msgServerityInfor(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}
}
