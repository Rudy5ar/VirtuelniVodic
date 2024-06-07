package model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}