package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Reserva;
import util.JPAUtil;

public class JPAReservaDao extends JPACrudDao<Reserva> {
	
	public JPAReservaDao(JPAUtil jpa){
		super(jpa, Reserva.class);
	}
	
	public boolean temProjetorDisponivel(Reserva reserva) throws Exception{
		Query q = jpa.getEntityManager().createNamedQuery("projetorDisponivel");
		q.setParameter(1, reserva.getData());
		q.setParameter(2, reserva.getCampus());
		q.setParameter(3, reserva.getHorario());
		Long contador = (Long) q.getSingleResult();
		if(contador > 0)
			return true;
		else
			return false;
	}
	
	public Long projetoresReservados(Reserva reserva) {
		Query q = jpa.getEntityManager().createNamedQuery("projetoresReservados");
		q.setParameter(1, reserva.getData());
		q.setParameter(2, reserva.getCampus());
		q.setParameter(3, reserva.getHorario());
		return (Long)q.getSingleResult();
	}
	
	@Override
	public void gravar(Reserva reserva) throws Exception {
		if (projetoresPossiveisReserva(reserva) > projetoresReservados(reserva))
			super.gravar(reserva);
	}

	public Long projetoresPossiveisReserva(Reserva reserva) {
		Query q = jpa.getEntityManager().createNamedQuery("projetoresCount");
		q.setParameter(1, reserva.getCampus());
		return (Long)q.getSingleResult();
	}
	
	public List<Reserva> listarReservasEmAberto(Reserva reserva) throws Exception{
		Query q = jpa.getEntityManager().createNamedQuery("reservaEmAberto");
		q.setParameter(1, reserva.getData());
		q.setParameter(2, reserva.getCampus());
		System.out.println("Aqui 1");
		return q.getResultList();
	}

}
