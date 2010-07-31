package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.VariavelGeral;

import dao.JPAUtil;

@SessionScoped
@ManagedBean(name="InicializarBanco")
public class InicializarBanco {
	
	public void inicializarBanco() throws Exception{
		JPAUtil.init(VariavelGeral.BANCODADOS);
	}

}
