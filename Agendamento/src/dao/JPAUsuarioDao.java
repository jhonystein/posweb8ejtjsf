package dao;

import javax.persistence.Query;

import modelo.Usuario;
import util.JPAUtil;

public class JPAUsuarioDao extends JPACrudDao<Usuario> {
	
	public JPAUsuarioDao(JPAUtil jpa){
		super(jpa, Usuario.class);
	}
	
	public Usuario buscarUsuario(Usuario usuario) {
		Query q = jpa.getEntityManager().createNamedQuery("usuarioLogado");
		q.setParameter(1, usuario.getNick());
		q.setParameter(2, usuario.getSenha());
		return (Usuario) q.getSingleResult();		
	}

}
