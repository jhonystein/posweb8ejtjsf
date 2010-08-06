package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import modelo.Sala;
import util.JPAUtil;
import dao.JPASalaDAO;

@SessionScoped
@ManagedBean(name="salaUC")
public class SalaUC {
	
	private Sala sala = new Sala();
    private UIData select;
    
    public SalaUC() {
    }
   
    public UIData getSelect() {
        return select;
    }

    public void setSelect(UIData select) {
        this.select = select;
    }

    public String selecione(){
        sala = (Sala)select.getRowData();
        return "formSala";
    }

    public Sala getSala(){
        return sala;
    }

    public List<Sala> getSalas() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPASalaDAO daoSala = new JPASalaDAO(jpa);
            return daoSala.listarTodos();		
		} finally {
			JPAUtil.finalizar();
		}
    }

    public String salvar() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPASalaDAO daoSala = new JPASalaDAO(jpa);
        	if(daoSala.verificarSalaUnicaBloco(sala)){
    	    	daoSala.gravar(sala);
    	    	return "listarSala";
        	}else{
        		FacesContext.getCurrentInstance().addMessage("formSala:sala", new FacesMessage("Sala já cadastrada!"));
    			return null;
        	}
		} finally {
			JPAUtil.finalizar();
		}    	
    }

    public String novo(){
        sala = new Sala();
        return "formSala";
    }

    public String cancelar(){
         return "listarSala";
    }
}