package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import modelo.Sala;
import dao.JPASalaDAO;

@SessionScoped
@ManagedBean(name="salaUC")
public class SalaUC {
	
	private JPASalaDAO daoSala = null;
	private Sala sala = new Sala();
    private UIData select;
    
    public SalaUC() throws Exception{
    	daoSala = new JPASalaDAO();
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
        return daoSala.listarTodos();		
    }

    public String salvar() throws Exception{
    	if(daoSala.verificarSalaUnicaBloco(sala)){
	    	daoSala.gravar(sala);
	    	return "listarSala";
    	}else
    		return null;
    }

    public String novo(){
        sala = new Sala();
        return "formSala";
    }

    public String cancelar(){
         return "listarSala";
    }
}