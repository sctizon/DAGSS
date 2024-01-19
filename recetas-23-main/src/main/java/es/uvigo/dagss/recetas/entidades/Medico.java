package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Anadir atributos propios
    private String name;
    private String surname;
    private String dni;
    private String registrationNumber;
    private int phoneNumber;
    private String email;
    private String centerId; // Id del centro médico donde trabaja el médico
    
    public Medico() {
        super(TipoUsuario.MEDICO);
    }

    public Medico(String login, String password, String name, String surname, String dni, String registrationNumber, int phoneNumber, String email){
        super(TipoUsuario.MEDICO, login, password);

        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.centerId = "";
    }

    public String getCenterId() {
        if(centerId.equals("")) {
            System.out.println("Medico is not assigned to any Care Center");
        }
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    

}
