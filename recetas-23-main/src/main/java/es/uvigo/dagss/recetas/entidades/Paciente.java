package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

    // Atributos propios
    @TableGenerator(name = "PACIENTE_GEN", table = "PACIENTE_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PACIENTE_GEN")
    @Id
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "DNI")
    private String dni;


    @Column(name = "healthCard_id")
    private String healthCardId;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "address")
    @Embedded
    private Direccion address;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private CentroSalud center;

    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = false)
    private Medico registeredDoctor;


    // Constructor por defecto
    public Paciente() {
        super(TipoUsuario.PACIENTE);
    }

    public Paciente(String name, String surname, String dni, String healthCardId, String ssn, Direccion address, int phoneNumber, Date birthdayDate, CentroSalud center, Medico registeredDoctor) {
        super(TipoUsuario.PACIENTE);
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.healthCardId = healthCardId;
        this.ssn = ssn;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthdayDate = birthdayDate;
        this.center = center;
        this.registeredDoctor = registeredDoctor;
    }
}