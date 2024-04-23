package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String email;

	private String korisnickoIme;

	private String sifra;

	private int uloga;

	//bi-directional many-to-one association to Clanak
	@OneToMany(mappedBy="korisnik")
	private List<Clanak> clanaks;

	//bi-directional many-to-one association to Tura
	@OneToMany(mappedBy="korisnik")
	private List<Tura> turas;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public int getUloga() {
		return this.uloga;
	}

	public void setUloga(int uloga) {
		this.uloga = uloga;
	}

	public List<Clanak> getClanaks() {
		return this.clanaks;
	}

	public void setClanaks(List<Clanak> clanaks) {
		this.clanaks = clanaks;
	}

	public Clanak addClanak(Clanak clanak) {
		getClanaks().add(clanak);
		clanak.setKorisnik(this);

		return clanak;
	}

	public Clanak removeClanak(Clanak clanak) {
		getClanaks().remove(clanak);
		clanak.setKorisnik(null);

		return clanak;
	}

	public List<Tura> getTuras() {
		return this.turas;
	}

	public void setTuras(List<Tura> turas) {
		this.turas = turas;
	}

	public Tura addTura(Tura tura) {
		getTuras().add(tura);
		tura.setKorisnik(this);

		return tura;
	}

	public Tura removeTura(Tura tura) {
		getTuras().remove(tura);
		tura.setKorisnik(null);

		return tura;
	}

}