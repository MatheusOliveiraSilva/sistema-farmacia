package controller;

import java.util.List;
import util.FacesUtil;
import model.Manufacturer;
import dao.ManufacturerDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class ManufacturerBean {
	Manufacturer manufacturer        = new Manufacturer();
	ManufacturerDAO manufacturerDAO  = new ManufacturerDAO();
	List<Manufacturer> manufacturers = null;
	String action;
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public ManufacturerDAO getManufacturerDAO() {
		return manufacturerDAO;
	}

	public void setManufacturerDAO(ManufacturerDAO manufacturerDAO) {
		this.manufacturerDAO = manufacturerDAO;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void save(){
		try {
			this.manufacturerDAO.insert(manufacturer);
			
			FacesUtil.addMessageInfo("Fabricante salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar o fabricante.");
		}
	}
	
	public void edit(){
		try {
			this.manufacturerDAO.update(manufacturer);

			FacesUtil.addMessageInfo("Fabricante alterado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível alterar o fabricante.");
		}
	}
	
	public void findOne(){
		try {
			this.action = FacesUtil.getParam("action");
			String id   = FacesUtil.getParam("id");

			if (id != null){
				this.manufacturer = this.manufacturerDAO.find(Integer.parseInt(id));	
			}
		} catch (Exception e) {
			FacesUtil.addMessageInfo("não foi possível carregar.");
		}
	}
	
	public void fetchAll(){
		try {
			this.manufacturers = manufacturerDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os fabricantes");
		}
	}
}
