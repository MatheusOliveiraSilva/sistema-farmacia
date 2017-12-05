package controller;

import model.Product;
import java.util.List;
import util.FacesUtil;
import dao.ProductDAO;
import model.Manufacturer;
import dao.ManufacturerDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class ProductBean {
	Product product        				 = new Product();
	ProductDAO productDAO  			     = new ProductDAO();
	ManufacturerDAO manufacturerDAO		 = new ManufacturerDAO();
	List<Product> products 				 = null;
	List<Manufacturer> manufacturers     = null;
	String action;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public void save(){
		try {
			this.productDAO.insert(product);
			
			FacesUtil.addMessageInfo("Produto salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
	
	public void edit(){
		try {
			this.productDAO.update(product);

			FacesUtil.addMessageInfo("Produto alterado com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
	
	public void findOne(){
		try {
			this.action = FacesUtil.getParam("action");
			String id   = FacesUtil.getParam("id");
			
			this.manufacturers = manufacturerDAO.fetchAll();
			
			if (id != null){
				this.product = this.productDAO.find(Long.parseLong(id));	
			}
		} catch (Exception e) {
			
			FacesUtil.addMessageInfo("não foi possível carregar.");
		}
	}
	
	public void fetchAll(){
		try {
			this.products = productDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os produtos.");
		}
	}
}
