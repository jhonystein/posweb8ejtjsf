package dao;

import java.util.List;

import javax.persistence.Query;

import modelo.Usuario;
import util.JPAUtil;

public class JPAUsuarioDao extends JPACrudDao<Usuario> {
	
	public JPAUsuarioDao(JPAUtil jpa){
		super(jpa, Usuario.class);
	}
	
	@SuppressWarnings("unchecked")
	public Usuario buscarUsuario(Usuario usuario) {
		Query q = jpa.getEntityManager().createNamedQuery("usuarioLogado");
		q.setParameter(1, usuario.getNick());
		q.setParameter(2, usuario.getSenha());
		List<Usuario> lstObjetos = q.getResultList();
		return  (lstObjetos.size() > 0 ? (Usuario)lstObjetos.get(0) : null);		
	}

}
