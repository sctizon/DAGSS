package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id")
    private long citaId;

    @ManyToOne
    @JoinColumn(name = "healthCard_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = false)
    private Medico medico;

    @Column(name = "apointment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "state")
    private String state;

    // Constructor por defecto
    public Cita() {
    }

    public Cita(int citaId, Paciente paciente, Medico medico, Date appointmentDate, int duration, String state) {
        this.citaId = citaId;
        this.paciente = paciente;
        this.medico = medico;
        this.appointmentDate = appointmentDate;
        this.duration = duration;
        this.state = state;
    }

}

