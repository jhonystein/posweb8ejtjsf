package dao;

import java.util.List;

import util.JPAUtil;

import modelo.Projetor;

public class JPAProjetorDAO implements DAO<Projetor> {
	
	private JPAUtil util;

	public JPAProjetorDAO() throws Exception {
		this.util = JPAUtil.getInstance();
	}
	
	@Override
	public void gravar(Projetor projetor) throws Exception {
		if (projetor.getCodigo() == 0)
			util.incluir(projetor);
		else
			util.alterar(projetor);
	}

	@Override
	public Projetor ler(long codigo) throws Exception {
		return (Projetor) util.ler(Projetor.class, codigo);
	}

	@Override
	public List<Projetor> listarTodos() throws Exception {
		return util.listarTodos(Projetor.class);
	}

}
