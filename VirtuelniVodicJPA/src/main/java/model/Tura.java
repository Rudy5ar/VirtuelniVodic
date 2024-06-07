package model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/**
 * The persistent class for the tura database table.
 * 
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name="Tura.findAll", query="SELECT t FROM Tura t")
public class Tura implements Serializable {

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

}