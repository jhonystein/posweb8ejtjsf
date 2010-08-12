package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import modelo.Reserva;
import modelo.Sala;
import util.JPAUtil;
import dao.JPAReservaDao;
import dao.JPASalaDAO;

@ManagedBean(name="reservaUC")
@SessionScoped
public class ReservaUC {

	private UIData select;
	private final ArrayList<Integer> horarios = new ArrayList<Integer>();
    private Reserva reserva = null;
    private Sala dadosConfiguracao = null;

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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva modelo) {
		this.reserva = modelo;
	}

	public List<Integer> getHorarios(){
    	return horarios;
    }
	public List<Sala> getSalasPeloBloco() throws Exception{
    	return new JPASalaDAO(JPAUtil.getInstance()).listaSalasPeloBlocoECampus(dadosConfiguracao);
    }
    
    public List<String> getBlocosPeloCampus() throws Exception{
    	return new JPASalaDAO(JPAUtil.getInstance()).listaBlocosPeloCampus(dadosConfiguracao);
    }
        
    public void setDadosConfiguracao(Sala dadosConfiguracao) {
		this.dadosConfiguracao = dadosConfiguracao;
	}

	public Sala getDadosConfiguracao() {
		return dadosConfiguracao;
	}
    
	public String salvar() throws Exception{
    	if(reserva.getSala() == null){
    		FacesContext.getCurrentInstance().addMessage("data", new FacesMessage("Selecione uma sala válida!"));
			return null;
    	}
    	
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
    
    public String novo() throws Exception{
        reserva = new Reserva();
        dadosConfiguracao = new Sala();
        return "formReserva";
    }

    public String cancelar(){
         return "indexAdmin";
    }
    


}
