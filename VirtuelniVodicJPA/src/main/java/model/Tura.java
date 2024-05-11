package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the tura database table.
 * 
 */
@Entity
@NamedQuery(name="Tura.findAll", query="SELECT t FROM Tura t")
public class Tura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTura;

	private String naziv;

	private String opis;

	private String tip;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-many association to Umetnickodelo
	@ManyToMany(mappedBy="turas")
	private List<Umetnickodelo> umetnickodelos;

	public Tura() {
	}

	public int getIdTura() {
		return this.idTura;
	}

	public void setIdTura(int idTura) {
		this.idTura = idTura;
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

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Umetnickodelo> getUmetnickodelos() {
		return this.umetnickodelos;
	}

	public void setUmetnickodelos(List<Umetnickodelo> umetnickodelos) {
		this.umetnickodelos = umetnickodelos;
	}

}