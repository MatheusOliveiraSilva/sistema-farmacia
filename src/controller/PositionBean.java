package controller;

import java.util.List;
import util.FacesUtil;
import model.Position;
import dao.PositionDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class PositionBean {
	Position position        = new Position();
	PositionDAO positionDAO  = new PositionDAO();
	List<Position> positions = null;
	String action;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public void setPositionDAO(PositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void save(){
		try {
			this.positionDAO.insert(position);
			
			FacesUtil.addMessageInfo("Cargo salvo com sucesso.");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar o cargo.");
		}
	}
	
	public void edit(){
		try {
			this.positionDAO.update(position);

			FacesUtil.addMessageInfo("Cargo alterado com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível alterar o cargo.");
		}
	}
	
	public void findOne(){
		try {
			this.action = FacesUtil.getParam("action");
			String id   = FacesUtil.getParam("id");
		
			if (id != null){
				this.position = this.positionDAO.find(Integer.parseInt(id));	
			}
		} catch (Exception e) {
			
			FacesUtil.addMessageInfo("não foi possível carregar");
		}
	}
	
	public void fetchAll(){
		try {
			this.positions = positionDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os fabricantes");
		}
	}
}
