package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

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
    
    public InstalacaoUC() {
    }
   
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
    		JPAReservaDao daoReserva = new JPAReservaDao(jpa);
            reservasEmAberto = daoReserva.listarReservasEmAberto(reserva);
            for(Reserva reservaAux: reservasEmAberto){
            	reservaAux.setProjetoresDisponiveis(daoReserva.listaProjetoresDisponiveis(reservaAux));
            }
    		mostrarTabela = true;	
		} finally {
			JPAUtil.finalizar();
		}
		return "listarInstalacao";
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
    		for(Reserva reservaAux: reservasEmAberto){
    			if(reservaAux.getProjetor() != null){
	    			reservaAux.setInstalado(true);
	    			daoReserva.gravar(reserva);
    			}
    		}
    		/*if(daoReserva.projetoresReservados(reserva) < daoReserva.projetoresPossiveisReserva(reserva)){
	    		daoReserva.gravar(reserva);
	        	return "reservaSucesso";
    		}else
    			FacesContext.getCurrentInstance().addMessage("data", new FacesMessage("Não existem projetores disponíveis nesta data"));
    			return null;*/
		} finally {
			JPAUtil.finalizar();
		}
		return null;
	}
}