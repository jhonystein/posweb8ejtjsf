package dao;

import java.util.List;

import modelo.IModelo;



public interface IDao<T extends IModelo> {

	void gravar(T objeto) throws Exception;
	
	void excluir (T objeto) throws Exception;

	T ler(Long codigo) throws Exception;

	List<T> listarTodos() throws Exception;

}
