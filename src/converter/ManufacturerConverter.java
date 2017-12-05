package converter;

import model.Manufacturer;
import dao.ManufacturerDAO;
import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;

@FacesConverter("manufacturerConverter")
public class ManufacturerConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		try {
			ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
			
			Manufacturer manufacturer = manufacturerDAO.find(Integer.parseInt(value));
			
			return manufacturer;
		} catch(RuntimeException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		try {
			Manufacturer manufacturer = (Manufacturer) object;
			
			return Integer.toString(manufacturer.getId());
		} catch (RuntimeException e) {
			return null;
		}
	}

}
