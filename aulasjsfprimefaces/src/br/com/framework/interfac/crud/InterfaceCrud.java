package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author adrianoa
 *
 * @param <T>
 */
@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
	void save(T obj) throws Exception;

	void persist(T obj) throws Exception;

	void saveOrUpdate(T obj) throws Exception;

	void update(T obj) throws Exception;

	void delete(T obj) throws Exception;

	T merge(T obj) throws Exception;

	List<T> findList(Class<T> objs) throws Exception;

	Object findById(Class<T> entidade, Long id) throws Exception;

	T findByPorId(Class<T> entidade, Long id) throws Exception;

	List<T> findListByQueryDinamic(String s) throws Exception;

	// Executar update com HQL
	void execteUpdateQueryDinamic(String s) throws Exception;

	// Executar update com SQL
	void executeUpdateSQLDinamic(String s) throws Exception;

	// Limpar sesao do hibernate
	void clearSession() throws Exception;

	void evict(Object objs) throws Exception;

	Session getSession() throws Exception;

	List<?> getListSQLDInamic(String sql) throws Exception;

	// JDBC do Spring
	JdbcTemplate getJdbcTemplate();

	SimpleJdbcTemplate getSimpleJdbcTemplate();

	SimpleJdbcInsert getSipleJdbcInsert();

	Long totalRegistro(String table) throws Exception;

	Query obterQuery(String query) throws Exception;

	List<Object[]> getListSQLDinamicArray(String sql) throws Exception;

	// Carregamento dinamico via JSF e primefaces
	List<T> findListByQueryDinamic(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;
}
