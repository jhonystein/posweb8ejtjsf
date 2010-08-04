package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import modelo.Reserva;
import util.JPAUtil;
import dao.JPACrudDao;

@SessionScoped
@ManagedBean(name="projetorUC")
public class ReservaUC {
	
	private Reserva reserva = new Reserva();
    private UIData select;
    
    public ReservaUC() {
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

    public String salvar() throws Exception{
    	JPAUtil jpa = JPAUtil.getInstance();
    	try {
    		JPACrudDao<Reserva> daoReserva = new JPACrudDao<Reserva>(jpa , Reserva.class);
    		daoReserva.gravar(reserva);
        	return "sucessoReserva";
		} finally {
			JPAUtil.finalizar();
		}
    }

    public String novo(){
        reserva = new Reserva();
        return "formReserva";
    }

    public String cancelar(){
         return "indexProfessor";
    }
}