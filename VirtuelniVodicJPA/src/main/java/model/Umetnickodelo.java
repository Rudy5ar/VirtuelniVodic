package model;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the umetnickodelo database table.
 * 
 */
@Setter
@Getter
@Entity
@NamedQuery(name="Umetnickodelo.findAll", query="SELECT u FROM Umetnickodelo u")
public class Umetnickodelo implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUmetnickoDelo;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private double geografskaDuzina;

	private double geografskaSirina;

	private String naziv;

	private String opis;

	//bi-directional many-to-many association to Epoha
	@ManyToMany
	@JoinTable(
		name="epoheudelu"
		, joinColumns={
			@JoinColumn(name="Umetnickodelo_idUmetnickoDelo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="epoha_idepoha")
			}
		)
	private List<Epoha> epohas;

	//bi-directional many-to-many association to Tura
	@ManyToMany
	@JoinTable(
		name="delotura"
		, joinColumns={
			@JoinColumn(name="Umetnickodelo_idUmetnickoDelo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Tura_idTura")
			}
		)
	private List<Tura> turas;

	//bi-directional many-to-one association to Umetnik
	@ManyToOne
	private Umetnik umetnik;

	public Umetnickodelo() {
	}

	@Override
	public String toString() {
		return naziv + " " + opis + " " + datum + " " + geografskaDuzina;
	}

}