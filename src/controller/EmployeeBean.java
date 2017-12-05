package controller;

import model.Employee;
import java.util.List;
import util.FacesUtil;
import dao.EmployeeDAO;
import model.Position;
import dao.PositionDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class EmployeeBean {
	Employee employee        = new Employee();
	EmployeeDAO employeeDAO  = new EmployeeDAO();
	PositionDAO positionDAO	 = new PositionDAO();
	List<Employee> employees = null;
	List<Position> positions = null;
	String action;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public void save(){
		try {
			this.employeeDAO.insert(employee);
			
			FacesUtil.addMessageInfo("Funcionário salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar o funcionário");
		}
	}
	
	public void edit(){
		try {
			this.employeeDAO.update(employee);

			FacesUtil.addMessageInfo("Funcionário alterado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar o funcionário.");
		}
	}
	
	public void findOne(){
		try {
			this.action = FacesUtil.getParam("action");
			String id   = FacesUtil.getParam("id");
			
			this.positions = positionDAO.fetchAll();
			
			if (id != null){
				this.employee = this.employeeDAO.find(Integer.parseInt(id));	
			}
		} catch (Exception e) {
			
			FacesUtil.addMessageInfo("não foi possível carregar.");
		}
	}
	
	public void fetchAll(){
		try {
			this.employees = employeeDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os produtos.");
		}
	}
}

