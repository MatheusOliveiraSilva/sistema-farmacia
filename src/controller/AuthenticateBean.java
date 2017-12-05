package controller;

import model.Employee;
import util.FacesUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.EmployeeDAO;

@ManagedBean
@SessionScoped
public class AuthenticateBean {
	private Employee employee       = new Employee();
	private EmployeeDAO employeeDAO = new EmployeeDAO();


	public Employee getEmployee() {
		if (this.employee == null) {
			this.employee = new Employee();
		}
	
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String login() {
		try {
			this.employee = employeeDAO.authenticate(this.employee.getCpf(), this.employee.getPassword());
			
			if (this.employee != null) {
				return "/pages/home.xhtml?faces-redirect=true";
			}
			
			FacesUtil.addMessageInfo("CPF ou/e senha incorretos.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os produtos.");
		}
		
		return "#";
	}
	
	public String exit() {
		this.employee = null;
		
		return "/pages/login.xhtml?faces-redirect=true";
	}
}
