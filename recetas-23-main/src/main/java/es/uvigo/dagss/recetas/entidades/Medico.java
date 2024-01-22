package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Atributos propios
    @TableGenerator(name = "MEDICO_GEN", table = "MEDICO_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDICO_GEN")
    @Id
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private CentroSalud center;


    // Constructor por defecto
    public Medico() {
        super(TipoUsuario.MEDICO);
    }

    public Medico(String name, String surname, String dni, String registrationNumber, int phoneNumber, String email, CentroSalud center) {
        super(TipoUsuario.MEDICO);
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.center = center;
    }
}
