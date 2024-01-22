package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    private String domicilio;
    private String localidad;
    private Integer codigoPostal;
    private String provincia;
}
