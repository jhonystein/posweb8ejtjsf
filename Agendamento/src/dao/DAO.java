package dao;

import java.util.List;



public interface DAO<T> {

	void gravar(T objeto) throws Exception;

	T ler(long codigo) throws Exception;

	List<T> listarTodos() throws Exception;

}
