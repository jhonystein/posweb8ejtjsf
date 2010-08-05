package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controle.ProjetorUC;

import modelo.Projetor;

@FacesConverter(forClass=Projetor.class)
public class ProjetorConversor implements Converter{

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int index = Integer.parseInt(value);
        try {
			return new ProjetorUC().getProjetores().get(index);
		} catch (Exception e) {
			return null;
		}

    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ""+((Projetor)value).getCodigo();
    }



}
