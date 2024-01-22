package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.exceptions.ResourceNotFoundException;
import es.uvigo.dagss.recetas.services.PacienteService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@RequestMapping(path = "/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        List<Paciente> res = pacienteService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable("id") Long id) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        if (paciente.isEmpty()) {
            throw new ResourceNotFoundException("Paciente " + id + " not found");
        }
        return new ResponseEntity<>(paciente.get(), HttpStatus.OK);

    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        Paciente newPaciente = pacienteService.create(paciente);
		URI uri = createPacienteUri(newPaciente);

		return ResponseEntity.created(uri).body(paciente);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> update(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        Optional<Paciente> optionalPaciente = pacienteService.findById(id);
        paciente.setId(id);
		if (optionalPaciente.isEmpty()) {
            throw new ResourceNotFoundException("No existe el paciente. ID: " + id);
		}

        Paciente newPaciente = pacienteService.update(paciente);
        return new ResponseEntity<>(newPaciente, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Paciente> paciente = pacienteService.findById(id);
        if (paciente.isEmpty()) {
            throw new ResourceNotFoundException("No existe el paciente. ID: " + id);
        }

        pacienteService.delete(paciente.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createPacienteUri(Paciente paciente) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(paciente.getId())
				.toUri();
	}
    
}
