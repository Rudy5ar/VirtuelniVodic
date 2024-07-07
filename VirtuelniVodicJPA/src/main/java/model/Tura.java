package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
	private List<Umetnickodelo> umetnickodelos = new ArrayList<>();

	public List<Umetnickodelo> getUmetnickodelos() {
		return umetnickodelos;
	}
	
	// Add a helper method to add an Umetnickodelo
    public void addUmetnickodelo(Umetnickodelo delo) {
        this.umetnickodelos.add(delo);
        delo.getTuras().add(this);
    }

    // Add a helper method to remove an Umetnickodelo
    public void removeUmetnickodelo(Umetnickodelo delo) {
        this.umetnickodelos.remove(delo);
        delo.getTuras().remove(this);
    }

}