package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the umetnik database table.
 * 
 */
@Entity
@NamedQuery(name="Umetnik.findAll", query="SELECT u FROM Umetnik u")
public class Umetnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUmetnik;

	@Temporal(TemporalType.DATE)
	private Date godinaRodjenja;

	@Temporal(TemporalType.DATE)
	private Date godinaSmrti;

	private String ime;

	//bi-directional many-to-one association to Umetnickodelo
	@OneToMany(mappedBy="umetnik")
	private List<Umetnickodelo> umetnickodelos;

	//bi-directional many-to-many association to Clanak
	@ManyToMany
	@JoinTable(
		name="umetnikdeoclanka"
		, joinColumns={
			@JoinColumn(name="umetnik_idUmetnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="clanak_idclanak")
			}
		)
	private List<Clanak> clanaks;

	//bi-directional many-to-one association to Epoha
	@ManyToOne
	private Epoha epoha;

	public Umetnik() {
	}

	public int getIdUmetnik() {
		return this.idUmetnik;
	}

	public void setIdUmetnik(int idUmetnik) {
		this.idUmetnik = idUmetnik;
	}

	public Date getGodinaRodjenja() {
		return this.godinaRodjenja;
	}

	public void setGodinaRodjenja(Date godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public Date getGodinaSmrti() {
		return this.godinaSmrti;
	}

	public void setGodinaSmrti(Date godinaSmrti) {
		this.godinaSmrti = godinaSmrti;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Umetnickodelo> getUmetnickodelos() {
		return this.umetnickodelos;
	}

	public void setUmetnickodelos(List<Umetnickodelo> umetnickodelos) {
		this.umetnickodelos = umetnickodelos;
	}

	public Umetnickodelo addUmetnickodelo(Umetnickodelo umetnickodelo) {
		getUmetnickodelos().add(umetnickodelo);
		umetnickodelo.setUmetnik(this);

		return umetnickodelo;
	}

	public Umetnickodelo removeUmetnickodelo(Umetnickodelo umetnickodelo) {
		getUmetnickodelos().remove(umetnickodelo);
		umetnickodelo.setUmetnik(null);

		return umetnickodelo;
	}

	public List<Clanak> getClanaks() {
		return this.clanaks;
	}

	public void setClanaks(List<Clanak> clanaks) {
		this.clanaks = clanaks;
	}

	public Epoha getEpoha() {
		return this.epoha;
	}

	public void setEpoha(Epoha epoha) {
		this.epoha = epoha;
	}

}