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

@ManagedBean(name="reservaUC")
@SessionScoped
public class ReservaUC {

	private UIData select;
	private final ArrayList<Integer> horarios = new ArrayList<Integer>();
    private Reserva reserva = new Reserva();
    private JPASalaDAO dao;
    private List<String> blocosPeloCampus = null;
    private List<Sala> salasPeloBloco = null;
    private Sala dadosConfiguracao = new Sala();

	public ReservaUC() {
		try {
			dao = new JPASalaDAO(JPAUtil.getInstance());
			for(int i= 1; i <= 15; i++)
	    		horarios.add(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public List<Sala> getSalasPeloBloco(){
    	return salasPeloBloco;
    }
    
    public List<String> getBlocosPeloCampus(){
    	return blocosPeloCampus;
    }
        
    public void setDadosConfiguracao(Sala dadosConfiguracao) {
		this.dadosConfiguracao = dadosConfiguracao;
	}

	public Sala getDadosConfiguracao() {
		return dadosConfiguracao;
	}
    
	public List<Sala> getSalas() {
		List<Sala> l;
		try {
			l = dao.listarTodos();
		} catch (Exception e) {
			l = new ArrayList<Sala>();
			e.printStackTrace();
		}
		return l;
	}
	
	public void atualizouCampus(ValueChangeEvent event) throws Exception{
		dadosConfiguracao.setCampus(Integer.valueOf(String.valueOf(event.getNewValue())));
    	atualizarDadosCombo();
    }
	
	public void atualizarSalasPeloBloco(ValueChangeEvent event) throws Exception{
		dadosConfiguracao.setBloco((String)event.getNewValue());
    	salasPeloBloco = dao.listaSalasPeloBlocoECampus(dadosConfiguracao);            	
    }
    
    private void atualizarDadosCombo()throws Exception{
    	blocosPeloCampus = dao.listaBlocosPeloCampus(dadosConfiguracao);
		if(blocosPeloCampus.size() > 0)//atribuir o valor do primeiro bloco para vir a pesquisa com as salas
			dadosConfiguracao.setBloco(blocosPeloCampus.get(0));
		salasPeloBloco = dao.listaSalasPeloBlocoECampus(dadosConfiguracao);  
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
        dadosConfiguracao = new Sala();
        atualizarDadosCombo();
        return "formReserva";
    }

    public String cancelar(){
         return "indexAdmin";
    }
    


}
