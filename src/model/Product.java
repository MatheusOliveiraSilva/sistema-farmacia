package model;

import java.math.BigDecimal;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotEmpty(message = "O campo descrição não pode estar vazio.")
	@Size(min = 3, max = 100, message = "O campo descrição deve conter entre 3 e 100 caracterers.")
	@Column(name = "description", length = 255, nullable = false)
	private String description;
	
	@NotNull(message = "O campo price não pode ser nulo")
	@DecimalMin(value = "0.00", message = "O preço não pode ser menor que 0. ")
	@DecimalMax(value = "99999.99", message = "O preço deve estar abaixo de 100.000.")
	@Column(name = "price", precision = 7, scale = 2, nullable = false)
	private BigDecimal price;
	
	@NotNull(message = "O campo fabricante é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manufacurerId", referencedColumnName = "id", nullable = false)
	private Manufacturer manufacturer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
