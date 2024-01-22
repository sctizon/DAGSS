package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "prescripciones")
public class Prescripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private long prescriptionId;

    @ManyToMany
    @JoinTable(
            name = "prescripcion_medicina",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicina> medicinas;

    @ManyToOne
    @JoinColumn(name = "healthCard_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = false)
    private Medico medico;

    @Column(name = "dose")
    private double dose;

    @Column(name = "indications")
    private String indications;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "final_date")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    @Column(name = "state", columnDefinition = "boolean default true")
    private boolean state;

    // Constructor por defecto
    public Prescripcion() {
    }

    public Prescripcion(int prescriptionId, List<Medicina> medicinas, Paciente paciente, Medico medico, double dose, String indications, Date startDate, Date finalDate, boolean state) {
        this.prescriptionId = prescriptionId;
        this.medicinas = medicinas;
        this.paciente = paciente;
        this.medico = medico;
        this.dose = dose;
        this.indications = indications;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.state = state;
    }
}
