package dao;

import java.util.List;

import modelo.IModelo;
import util.JPAUtil;

public class JPACrudDao<T extends IModelo> implements IDao<T> {

	protected JPAUtil jpa;
	private Class<T> domainClass;

	public JPACrudDao(JPAUtil jpa, Class<T> domainClass) {
		super();
		this.jpa = jpa;
		this.domainClass = domainClass;
	}

	@Override
	public void gravar(T objeto) throws Exception {
		if (objeto.getCodigo() == null) {
			jpa.incluir(objeto);
		} else {
			jpa.alterar(objeto);
		}
	}

	@Override
	public T ler(Long codigo) throws Exception {
		return (T)jpa.ler(domainClass, codigo);
	}

	@Override
	public List<T> listarTodos() throws Exception {
		return jpa.listarTodos(domainClass);
	}

	@Override
	public void excluir(T objeto) throws Exception {
		jpa.excluir(objeto);
	}

}
