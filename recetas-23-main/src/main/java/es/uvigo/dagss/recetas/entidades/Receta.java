package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private int recipeId;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescripcion prescripcion;

    @Column(name = "initial_date")
    @Temporal(TemporalType.DATE)
    private Date initialDate;

    @Column(name = "final_date")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    @Column(name = "units")
    private int units;

    @Column(name = "state", columnDefinition = "boolean default true")
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "pharmacy_Id")
    private Farmacia pharmacy;

    // Constructor por defecto
    public Receta() {
    }

    public Receta(int recipeId, Prescripcion prescripcion, Date initialDate, Date finalDate, int units, boolean state, Farmacia pharmacy) {
        this.recipeId = recipeId;
        this.prescripcion = prescripcion;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.units = units;
        this.state = state;
        this.pharmacy = pharmacy;
    }
}
