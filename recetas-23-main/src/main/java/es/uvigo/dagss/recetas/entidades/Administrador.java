package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }

    public Administrador(String login, String password){
        super(TipoUsuario.ADMINISTRADOR,login,password);
    }
}
