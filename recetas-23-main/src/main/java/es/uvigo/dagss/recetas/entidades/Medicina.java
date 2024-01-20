package es.uvigo.dagss.recetas.entidades;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "medicines")
public class Medicina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private long medicineId;

    @Column(name = "name")
    private String name;

    @Column(name = "active_ingredient")
    private String activeIngredient;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "family")
    private String family;

    @Column(name = "doses")
    private int doses;

    @Column(name = "active")
    private boolean active;

    public Medicina() {
    }

    public Medicina(int medicineId, String name, String activeIngredient, String manufacturer, String family, int doses, boolean active) {
        this.medicineId = medicineId;
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.manufacturer = manufacturer;
        this.family = family;
        this.doses = doses;
        this.active = active;
    }

}
