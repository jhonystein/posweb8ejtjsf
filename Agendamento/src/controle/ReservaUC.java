package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import modelo.Reserva;
import util.JPAUtil;
import dao.JPAReservaDao;

@SessionScoped
@ManagedBean(name="reservaUC")
public class ReservaUC {
	
	private Reserva reserva = new Reserva();
    private UIData select;
    private final ArrayList<Integer> horarios = new ArrayList<Integer>();
    private boolean mostrarTabela = false;
    private List<Reserva> reservasEmAberto = null;
		
    public ReservaUC() {
    	for(int i= 1; i <= 15; i++)
    		horarios.add(i);
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
    
    public List<Integer> getHorarios(){
    	return horarios;
    }
    
    public String salvar() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPAReservaDao daoReserva = new JPAReservaDao(jpa);
    		if(daoReserva.projetoresReservados(reserva) < daoReserva.projetoresPossiveisReserva(reserva)){
	    		daoReserva.gravar(reserva);
	        	return "reservaSucesso";
    		}else
    			FacesContext.getCurrentInstance().addMessage("data", new FacesMessage("Não existem projetores disponíveis nesta data"));
    			return null;
		} finally {
			JPAUtil.finalizar();
		}
    }

    public String novo(){
        reserva = new Reserva();
        return "formReserva";
    }

    public String cancelar(){
         return "indexAdmin";
    }
    
    public List<Reserva> reservas() throws Exception{
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
		return getReservasEmAberto();
    }
    
    public String novaInstalacao(){
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
}