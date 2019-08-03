package br.com.project.util.all;

/**
 * 
 * @author adrianoabrantesdeandrade
 *
 */
public enum StatusPersistencia {
	ERRO("Erro"), 
	SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Este objeto nao pode ser apagado por possuir referencia ao mesmo.");

	private String name;

	private StatusPersistencia(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
