package converter;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;

import dao.PositionDAO;
import model.Position;

@FacesConverter("positionConverter")
public class PositionConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		try {
			PositionDAO positionDAO = new PositionDAO();
			
			Position position = positionDAO.find(Integer.parseInt(value));
			
			return position;
		} catch(RuntimeException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		try {
			Position position = (Position) object;
			
			return Integer.toString(position.getId());
		} catch (RuntimeException e) {
			return null;
		}
	}

}
