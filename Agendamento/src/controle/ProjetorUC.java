package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import modelo.Projetor;
import dao.JPAProjetorDAO;

@SessionScoped
@ManagedBean(name="projetorUC")
public class ProjetorUC {
	
	private JPAProjetorDAO daoProjetor = null;
	private Projetor projetor = new Projetor();
    private UIData select;
    
    public ProjetorUC() throws Exception{
    	daoProjetor = new JPAProjetorDAO();
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
        return daoProjetor.listarTodos();		
    }

    public String salvar() throws Exception{
		daoProjetor.gravar(projetor);
    	return "listarProjetor";
    }

    public String novo(){
        projetor = new Projetor();
        return "formProjetor";
    }

    public String cancelar(){
         return "listarProjetor";
    }
}