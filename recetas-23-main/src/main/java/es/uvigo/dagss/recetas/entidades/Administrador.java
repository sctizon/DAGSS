package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {
    @TableGenerator(name = "ADMIN_GEN", table = "ADMIN_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADMIN_GEN")
    @Id
    private Long id;
    private String name;
    private String email;

    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }

    public Administrador(String login, String password){
        super(TipoUsuario.ADMINISTRADOR,login,password);
    }
}
