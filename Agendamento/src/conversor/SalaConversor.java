package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Sala;
import util.JPAUtil;
import dao.JPASalaDAO;

@FacesConverter(forClass=Sala.class)
public class SalaConversor implements Converter{

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
        	Sala s = new JPASalaDAO(JPAUtil.getInstance()).ler(Long.parseLong(value));
			return s;
		} catch (Exception e) {
			return null;
		}
		
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	return ""+((Sala)value).getCodigo();
    }
}
