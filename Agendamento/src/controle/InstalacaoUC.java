package controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import modelo.Projetor;
import modelo.Reserva;
import util.JPAUtil;
import dao.JPAReservaDao;

@SessionScoped
@ManagedBean(name="instalacaoUC")
public class InstalacaoUC {
	
	private Reserva reserva = new Reserva();
    private UIData select;
    private boolean mostrarTabela = false;
    private List<Reserva> reservasEmAberto = null;
    private int indiceReserva = 0;
    
    public InstalacaoUC() {}
   
    public UIData getSelect() {
        return select;
    }

    public void setSelect(UIData select) {
        this.select = select;
    }

    public Reserva getReserva(){
        return reserva;
    }
    
    public String reservas() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
            reservasEmAberto = new JPAReservaDao(jpa).listarReservasEmAberto(reserva);
    		mostrarTabela = true;	
		} finally {
			JPAUtil.finalizar();
		}
		return "listarInstalacao";
    }
    
    public List<Projetor> getProjetores() throws Exception{
    	if(indiceReserva == reservasEmAberto.size())
    		indiceReserva = 0;
    	return new JPAReservaDao(JPAUtil.getInstance()).listaProjetoresDisponiveis(reservasEmAberto.get(indiceReserva++));
    }
    
    public String novo(){
    	reserva = new Reserva();
    	return "formInstalacao";
    }

    
    public boolean isMostrar() {
    	if (mostrarTabela) { 
    		return true; 
    	} else { 
    		return false; 
    	}
    }

	public List<Reserva> getReservasEmAberto() {
    	return reservasEmAberto;
	}
	
	public String salvar() throws Exception{
		JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPAReservaDao daoReserva = new JPAReservaDao(jpa);
    		
    		for(int proximo = 0; proximo < reservasEmAberto.size(); proximo++){
    			if(reservasEmAberto.get(proximo) != null){
	    			for(int anterior = reservasEmAberto.size()-1; anterior > proximo; anterior--){
	    				if(reservasEmAberto.get(anterior).getProjetor() != null){
		        			if(reservasEmAberto.get(proximo).getHorario() == reservasEmAberto.get(anterior).getHorario() && 
		        					reservasEmAberto.get(proximo).getProjetor().getCodigo() == reservasEmAberto.get(anterior).getProjetor().getCodigo()){
		        				FacesContext.getCurrentInstance().addMessage("projetor", new FacesMessage("Você não pode selecionar o mesmo projetor para duas reservas para o mesmo horário!"));
		            			return null;
		        			}    			
	    				}
	        		}
    			}
    		}
    		
    		boolean houveInstalacao = false;
    		for(Reserva reservaAux: reservasEmAberto){
    			if(reservaAux.getProjetor() != null){
	    			reservaAux.setInstalado(true);
	    			daoReserva.gravar(reservaAux);
	    			houveInstalacao = true;
    			}
    		}
    		if(houveInstalacao){
    			return "instalacaoSucesso";
    		}
		} finally {
			JPAUtil.finalizar();
		}
		return null;
	}
}