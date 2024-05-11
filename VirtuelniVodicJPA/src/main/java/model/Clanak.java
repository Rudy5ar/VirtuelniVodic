package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the clanak database table.
 * 
 */
@Entity
@NamedQuery(name="Clanak.findAll", query="SELECT c FROM Clanak c")
public class Clanak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClanak;

	@Temporal(TemporalType.DATE)
	private Date datumKreiranja;

	private String naziv;

	private String tekst;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-many association to Umetnik
	@ManyToMany(mappedBy="clanaks")
	private List<Umetnik> umetniks;

	public Clanak() {
	}

	public int getIdClanak() {
		return this.idClanak;
	}

	public void setIdClanak(int idClanak) {
		this.idClanak = idClanak;
	}

	public Date getDatumKreiranja() {
		return this.datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Umetnik> getUmetniks() {
		return this.umetniks;
	}

	public void setUmetniks(List<Umetnik> umetniks) {
		this.umetniks = umetniks;
	}

}