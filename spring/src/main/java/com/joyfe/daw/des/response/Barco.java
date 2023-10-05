package com.joyfe.daw.des.response;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.annotation.Nullable;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.List;
import lombok.Setter;
  
@Entity(name = "barcos")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Barco {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String num_matricula;

	@NonNull
	private String nombre;

	@NonNull
	private String num_amarre;

	@NonNull
	private Long cuota;

	@NonNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Salida> salidas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum_matricula() {
		return num_matricula;
	}

	public void setNum_matricula(String num_matricula) {
		this.num_matricula = num_matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNum_amarre() {
		return num_amarre;
	}

	public void setNum_amarre(String num_amarre) {
		this.num_amarre = num_amarre;
	}

	public Long getCuota() {
		return cuota;
	}

	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}

	public List<Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}
	
	
}