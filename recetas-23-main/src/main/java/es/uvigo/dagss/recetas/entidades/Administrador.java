package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    private String name;
    private String email;

    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }

    public Administrador(String login, String password, String name, String email){
        super(TipoUsuario.ADMINISTRADOR, login, password);
        this.name = name;
        this.email = email;
    }
}
