package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.ModeloTesteSala;
import modelo.Sala;
import util.JPAUtil;
import dao.JPASalaDAO;

@ManagedBean
@SessionScoped
public class TesteSala {

	private JPASalaDAO dao;

	public TesteSala() {
		try {
			dao = new JPASalaDAO(JPAUtil.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ModeloTesteSala modelo = new ModeloTesteSala();

	public ModeloTesteSala getModelo() {
		return modelo;
	}

	public void setModelo(ModeloTesteSala modelo) {
		this.modelo = modelo;
	}

	public List<Sala> getSalas() {
		List<Sala> l;
		try {
			l = dao.listarTodos();
		} catch (Exception e) {
			l = new ArrayList<Sala>();
			e.printStackTrace();
		}
		return l;
	}

	public String salvar() {
		return "indexAdmin";
	}

}
