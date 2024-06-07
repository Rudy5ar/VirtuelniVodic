package model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the umetnickodelo database table.
 * 
 */
@Entity
@NamedQuery(name="Umetnickodelo.findAll", query="SELECT u FROM Umetnickodelo u")
public class Umetnickodelo implements Serializable {
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

	public int getIdUmetnickoDelo() {
		return this.idUmetnickoDelo;
	}

	public void setIdUmetnickoDelo(int idUmetnickoDelo) {
		this.idUmetnickoDelo = idUmetnickoDelo;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public double getGeografskaDuzina() {
		return this.geografskaDuzina;
	}

	public void setGeografskaDuzina(double geografskaDuzina) {
		this.geografskaDuzina = geografskaDuzina;
	}

	public double getGeografskaSirina() {
		return this.geografskaSirina;
	}

	public void setGeografskaSirina(double geografskaSirina) {
		this.geografskaSirina = geografskaSirina;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Epoha> getEpohas() {
		return this.epohas;
	}

	public void setEpohas(List<Epoha> epohas) {
		this.epohas = epohas;
	}

	public List<Tura> getTuras() {
		return this.turas;
	}

	public void setTuras(List<Tura> turas) {
		this.turas = turas;
	}

	public Umetnik getUmetnik() {
		return this.umetnik;
	}

	public void setUmetnik(Umetnik umetnik) {
		this.umetnik = umetnik;
	}

}