package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    @Id
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
    private String address;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private boolean active;

    //Constructor de Farmacia

    public Farmacia() {

    }

    public Farmacia(int pharmacyId, String pharmacyName, String pharmaceuticName, String pharmaceuticSurname, String pharmaceuticDni, String registrationPhNumber, String address, int phoneNumber, String email, boolean active) {
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
        this.active = active;
    }
}