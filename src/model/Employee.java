package model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@NotEmpty(message = "O campo nome não pode estar vazio.")
	@Size(min = 3, max = 100, message = "O campo nome deve conter entre 3 e 100 caracteres.")
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@NotEmpty(message = "O campo CPF não pode estar vazio.")
	@Size(min = 14, max = 14, message = "O CPF deve conter 14 caracteres.")
	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;
	
	@NotEmpty(message = "O campo senha não pode estar vazio.")
	@Size(min = 6, max = 14, message = "O campo senha deve conter no mínimo 6 caracteres.")
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@NotNull(message = "Selecione um cargo para este funcionário.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "positionId", referencedColumnName = "id", nullable = false)
	private Position position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
