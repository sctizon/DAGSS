package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    @TableGenerator(name = "FARMACIA_GEN", table = "FARMACIA_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FARMACIA_GEN")
    @Id
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_id")
    private int pharmacyId;

    @Column(name = "pharmacy_name")
    private String pharmacyName;

    @Column(name = "pharmaceutic_name")
    private String pharmaceuticName;

    @Column(name = "pharmaceutic_surname")
    private String pharmaceuticSurname;

    @Column(name = "pharmaceutic_dni")
    private String pharmaceuticDni;

    @Column(name = "registration_ph_number")
    private String registrationPhNumber;

    @Column(name = "address")
    @Embedded
    private Direccion address;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "email")
    private String email;


    //Constructor de Farmacia

    public Farmacia() {

    }

    public Farmacia(int pharmacyId, String pharmacyName, String pharmaceuticName, String pharmaceuticSurname, String pharmaceuticDni, String registrationPhNumber, Direccion address, int phoneNumber, String email) {
        super(TipoUsuario.FARMACIA);
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.pharmaceuticName = pharmaceuticName;
        this.pharmaceuticSurname = pharmaceuticSurname;
        this.pharmaceuticDni = pharmaceuticDni;
        this.registrationPhNumber = registrationPhNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}