package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import modelo.Projetor;
import util.JPAUtil;
import dao.JPACrudDao;

@SessionScoped
@ManagedBean(name="projetorUC")
public class ProjetorUC {
	
	private Projetor projetor = new Projetor();
    private UIData select;
    
    public ProjetorUC() throws Exception{
    }
   
    public UIData getSelect() {
        return select;
    }

    public void setSelect(UIData select) {
        this.select = select;
    }

    public String selecione(){
        projetor = (Projetor)select.getRowData();
        return "formProjetor";
    }

    public Projetor getProjetor(){
        return projetor;
    }

    public List<Projetor> getProjetores() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPACrudDao<Projetor> daoProjetor = new JPACrudDao<Projetor>(jpa , Projetor.class);
            return daoProjetor.listarTodos();		
		} finally {
			JPAUtil.finalizar();
		}
    }

    public String salvar() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPACrudDao<Projetor> daoProjetor = new JPACrudDao<Projetor>(jpa , Projetor.class);
    		daoProjetor.gravar(projetor);
        	return "listarProjetor";
		} finally {
			JPAUtil.finalizar();
		}
    }

    public String novo(){
        projetor = new Projetor();
        return "formProjetor";
    }

    public String cancelar(){
         return "listarProjetor";
    }
}