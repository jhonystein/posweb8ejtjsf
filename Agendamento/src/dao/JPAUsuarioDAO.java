package dao;

import java.util.List;

import javax.persistence.Query;

import util.JPAUtil;

import modelo.Usuario;

public class JPAUsuarioDAO implements DAO<Usuario> {
	
	private JPAUtil util;

	public JPAUsuarioDAO() throws Exception {
		this.util = JPAUtil.getInstance();
	}
	
	@Override
	public void gravar(Usuario usuario) throws Exception {
		if (usuario.getCodigo() == 0)
			util.incluir(usuario);
		else
			util.alterar(usuario);
	}

	@Override
	public Usuario ler(long codigo) throws Exception {
		return (Usuario) util.ler(Usuario.class, codigo);
	}

	@Override
	public List<Usuario> listarTodos() throws Exception {
		return util.listarTodos(Usuario.class);
	}

	public Usuario buscarUsuario(Usuario usuario) {
		Query q = util.getEntityManager().createNamedQuery("usuarioLogado");
		q.setParameter(1, usuario.getNick());
		q.setParameter(2, usuario.getSenha());
		return (Usuario) q.getSingleResult();		
	}

}
