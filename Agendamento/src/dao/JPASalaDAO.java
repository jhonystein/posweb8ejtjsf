package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Sala;

public class JPASalaDAO implements DAO<Sala> {
	
	private JPAUtil util;

	public JPASalaDAO() throws Exception {
		this.util = JPAUtil.getInstance();
	}
	
	@Override
	public void gravar(Sala sala) throws Exception {
		if (sala.getCodigo() == 0)
			util.incluir(sala);
		else
			util.alterar(sala);
	}

	@Override
	public Sala ler(long codigo) throws Exception {
		return (Sala) util.ler(Sala.class, codigo);
	}

	@Override
	public List<Sala> listarTodos() throws Exception {
		return util.listarTodos(Sala.class);
	}
	
	public boolean verificarSalaUnicaBloco(Sala sala) throws Exception{
		Query q = JPAUtil.getInstance().getEntityManager().createNamedQuery("salaCampus");
		q.setParameter(1, "pro%");
		q.setParameter(2, "pro%");
		q.setParameter(3, "pro%");
		Long contador = (Long) q.getSingleResult();
		if(contador > 0)
			return false;
		else
			return true;
	}

}
