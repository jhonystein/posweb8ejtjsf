package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import modelo.Reserva;
import modelo.Sala;
import util.JPAUtil;
import dao.JPAReservaDao;
import dao.JPASalaDAO;

@SessionScoped
@ManagedBean(name="reservaUC")
public class ReservaUC {
	
	private Reserva reserva = new Reserva();
    private UIData select;
    private final ArrayList<Integer> horarios = new ArrayList<Integer>();
    private boolean mostrarTabela = false;
    private List<Reserva> reservasEmAberto = null;
    private List<String> blocosPeloCampus = null;
    private List<Sala> salasPeloBloco = null;
    
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
    			System.out.println(reserva.getSala().getCodigo());
	    		daoReserva.gravar(reserva);
	        	return "reservaSucesso";
    		}else
    			FacesContext.getCurrentInstance().addMessage("data", new FacesMessage("Não existem projetores disponíveis nesta data"));
    			return null;
		} finally {
			JPAUtil.finalizar();
		}
    }
    
    

    public String novo() throws Exception{
        reserva = new Reserva();
        atualizarDadosCombo(reserva.getSala());
        return "formReserva";
    }

    public String cancelar(){
         return "indexAdmin";
    }
    
    public List<String> getBlocosPeloCampus(){
    	return blocosPeloCampus;
    }
    
    public void atualizarSalasPeloBloco(ValueChangeEvent event) throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		reserva.getSala().setBloco((String)event.getNewValue());
    		salasPeloBloco = new JPASalaDAO(jpa).listaSalasPeloBlocoECampus(reserva.getSala());            	
    	} finally {
			JPAUtil.finalizar();
		}		
    }
    
    public void atualizouCampus(ValueChangeEvent event) throws Exception{
    	reserva.getSala().setCampus(Integer.valueOf(String.valueOf(event.getNewValue())));
    	atualizarDadosCombo(reserva.getSala());
    }
    
    private void atualizarDadosCombo(Sala sala)throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPASalaDAO jpaSala = new JPASalaDAO(jpa);
    		blocosPeloCampus = jpaSala.listaBlocosPeloCampus(sala);
    		if(blocosPeloCampus.size() > 0)//atribuir o valor do primeiro bloco para vir a pesquisa com as salas
    			sala.setBloco(blocosPeloCampus.get(0));
    		salasPeloBloco = jpaSala.listaSalasPeloBlocoECampus(sala);            	
		} finally {
			JPAUtil.finalizar();
		}
    }
    
    public List<Sala> getSalasPeloBloco(){
    	return salasPeloBloco;
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
	
	public String salvarInstalacao() throws Exception{
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