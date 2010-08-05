package dao;

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

}
