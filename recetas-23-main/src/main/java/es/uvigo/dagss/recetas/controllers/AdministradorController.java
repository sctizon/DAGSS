package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.exceptions.ResourceNotFoundException;
import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.services.AdministradorService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/administradores", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Administrador Controller", description = "Endpoint de Admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    @Operation(method = "GET", summary = "Lista de Admins")
    @ApiResponse(responseCode = "200", description = "Devuelve lista de Admins")
    public ResponseEntity<List<Administrador>> getAll() {
        List<Administrador> res = administradorService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Administrador> getById(@PathVariable("id") Long id) {
        Optional<Administrador> admin = administradorService.getById(id);
        if (admin.isEmpty()) {
            throw new ResourceNotFoundException("Administrador " + id + " not found");
        }
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);

    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> create(@RequestBody Administrador administrador) {
        Administrador newAdministrador = administradorService.create(administrador);
		URI uri = createAdminstradorUri(newAdministrador);

		return ResponseEntity.created(uri).body(administrador);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> update(@PathVariable("id") Long id, @RequestBody Administrador administrador) {
        Optional<Administrador> optionalAdministrador = administradorService.getById(id);
        administrador.setId(id);
		if (optionalAdministrador.isEmpty()) {
            throw new ResourceNotFoundException("No existe el administrador con id " + id);
		}

        Administrador newAdministrador = administradorService.update(administrador);
        return new ResponseEntity<>(newAdministrador, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Administrador> admin = administradorService.getById(id);
        if (admin.isEmpty()) {
            throw new ResourceNotFoundException("No existe el administrador con id " + id);
        }

        administradorService.delete(admin.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createAdminstradorUri(Administrador administrador) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(administrador.getId())
				.toUri();
	}

}
