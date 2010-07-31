package dao;

import java.util.List;

import modelo.Reserva;

public class JPAReservaDAO implements DAO<Reserva> {
	
	private JPAUtil util;

	public JPAReservaDAO() throws Exception {
		this.util = JPAUtil.getInstance();
	}
	
	@Override
	public void gravar(Reserva reserva) throws Exception {
		if (reserva.getCodigo() == 0)
			util.incluir(reserva);
		else
			util.alterar(reserva);
	}

	@Override
	public Reserva ler(long codigo) throws Exception {
		return (Reserva) util.ler(Reserva.class, codigo);
	}

	@Override
	public List<Reserva> listarTodos() throws Exception {
		return util.listarTodos(Reserva.class);
	}

}
