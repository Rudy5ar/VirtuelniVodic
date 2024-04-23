package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the epoha database table.
 * 
 */
@Entity
@NamedQuery(name="Epoha.findAll", query="SELECT e FROM Epoha e")
public class Epoha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEpoha;

	private String naziv;

	private String opis;

	@Temporal(TemporalType.DATE)
	private Date vremenskiPeriodDo;

	@Temporal(TemporalType.DATE)
	private Date vremenskiPeriodOd;

	//bi-directional many-to-many association to Umetnickodelo
	@ManyToMany(mappedBy="epohas")
	private List<Umetnickodelo> umetnickodelos;

	//bi-directional many-to-one association to Umetnik
	@OneToMany(mappedBy="epoha")
	private List<Umetnik> umetniks;

	public Epoha() {
	}

	public int getIdEpoha() {
		return this.idEpoha;
	}

	public void setIdEpoha(int idEpoha) {
		this.idEpoha = idEpoha;
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

	public Date getVremenskiPeriodDo() {
		return this.vremenskiPeriodDo;
	}

	public void setVremenskiPeriodDo(Date vremenskiPeriodDo) {
		this.vremenskiPeriodDo = vremenskiPeriodDo;
	}

	public Date getVremenskiPeriodOd() {
		return this.vremenskiPeriodOd;
	}

	public void setVremenskiPeriodOd(Date vremenskiPeriodOd) {
		this.vremenskiPeriodOd = vremenskiPeriodOd;
	}

	public List<Umetnickodelo> getUmetnickodelos() {
		return this.umetnickodelos;
	}

	public void setUmetnickodelos(List<Umetnickodelo> umetnickodelos) {
		this.umetnickodelos = umetnickodelos;
	}

	public List<Umetnik> getUmetniks() {
		return this.umetniks;
	}

	public void setUmetniks(List<Umetnik> umetniks) {
		this.umetniks = umetniks;
	}

	public Umetnik addUmetnik(Umetnik umetnik) {
		getUmetniks().add(umetnik);
		umetnik.setEpoha(this);

		return umetnik;
	}

	public Umetnik removeUmetnik(Umetnik umetnik) {
		getUmetniks().remove(umetnik);
		umetnik.setEpoha(null);

		return umetnik;
	}

}