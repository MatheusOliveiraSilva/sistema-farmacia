package controller;

import model.Sale;
import dao.SaleDAO;
import model.Product;
import java.util.List;
import java.util.Date;
import model.Employee;
import model.SaleItem;
import util.FacesUtil;
import dao.ProductDAO;
import dao.SaleItemDAO;
import java.util.ArrayList;
import java.math.BigDecimal;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ViewScoped
public class SaleBean {
	List<Product> products  = null;
	ProductDAO productDAO   = new ProductDAO();
	SaleDAO saleDao         = new SaleDAO();
	SaleItemDAO saleItemDAO = new SaleItemDAO();
	List<SaleItem> items;
	Sale sale;
	
	@ManagedProperty(value = "#{authenticateBean}")
	private AuthenticateBean authenticateBean;

	public AuthenticateBean getAuthenticateBean() {
		return authenticateBean;
	}

	public void setAuthenticateBean(AuthenticateBean authenticateBean) {
		this.authenticateBean = authenticateBean;
	}
	
	public Sale getSale() {
		if (this.sale == null) {
			this.sale = new Sale();
			this.sale.setTotal(new BigDecimal("0.00"));
		}
		
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void fetchAllProducts(){
		try {
			this.products = productDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os produtos.");
		}
	}
	
	public List<SaleItem> getItems() {
		if (items == null) {
			items = new ArrayList<SaleItem>();
		}
		
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}

	public void addProduct(Product product) {
		int foundPosition = - 1;
		
		for (int i = 0; i < items.size() && foundPosition < 0; i++) {
			SaleItem saleItemTemp = items.get(i);
			
			if (saleItemTemp.getProduct().equals(product)) {
				foundPosition = i;
			}
		}
		
		SaleItem item = new SaleItem();
		item.setProduct(product);
		
		if (foundPosition < 0 ) {
			item.setQuantity(1);
			item.setValue(product.getPrice());
			this.items.add(item);
		} else {
			SaleItem saleItemTemp = this.items.get(foundPosition);
			item.setQuantity(saleItemTemp.getQuantity() + 1);
			item.setValue(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
			
			this.items.set(foundPosition, item);
		}
		
		this.sale.setTotal(sale.getTotal().add(product.getPrice()));
	}
	
	public void removeProduct(SaleItem item) {
		int foundPosition = - 1;
		
		for (int i = 0; i < this.items.size() && foundPosition < 0; i++) {
			SaleItem saleItemTemp = this.items.get(i);
			
			if (saleItemTemp.getProduct().equals(item.getProduct())) {
				foundPosition = i;
			}
		}
		if (foundPosition > -1 ) {
			this.items.remove(foundPosition);
			this.sale.setTotal(sale.getTotal().subtract(item.getValue()));
		} 
	}
	
	public void loadSaleData() {
		this.sale.setTime(new Date());
		Employee employee = authenticateBean.getEmployee();
		this.sale.setEmployee(employee);
	}
	
	public void save(){
		try {
			this.saleDao.insert(this.sale);
			 
			for (SaleItem item : items) {
				item.setSale(this.sale);
				
				this.saleItemDAO.insert(item);
			}
			
			this.sale = new Sale();
			this.sale.setTotal(new BigDecimal("0.00"));
			this.items = new ArrayList<SaleItem>();
			
			FacesUtil.addMessageInfo("Venda realizada com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível realizar venda");
		}
	}
	
}