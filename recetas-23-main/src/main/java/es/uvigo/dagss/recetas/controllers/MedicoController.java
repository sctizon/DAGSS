package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.exceptions.ResourceNotFoundException;
import es.uvigo.dagss.recetas.services.MedicoService;
import es.uvigo.dagss.recetas.entidades.Medico;

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
@RequestMapping(path = "/medicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAll() {
        List<Medico> res = medicoService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Medico> getById(@PathVariable("id") Long id) {
        Optional<Medico> medico = medicoService.findById(id);
        if (medico.isEmpty()) {
            throw new ResourceNotFoundException("Medico " + id + " not found");
        }
        return new ResponseEntity<>(medico.get(), HttpStatus.OK);

    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> create(@RequestBody Medico medico) {
        Medico newMedico = medicoService.create(medico);
		URI uri = createMedicoUri(newMedico);

		return ResponseEntity.created(uri).body(medico);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> update(@PathVariable("id") Long id, @RequestBody Medico medico) {
        Optional<Medico> optionalMedico = medicoService.findById(id);
        medico.setId(id);
		if (optionalMedico.isEmpty()) {
            throw new ResourceNotFoundException("No existe el médico. ID: " + id);
		}

        Medico newMedico = medicoService.update(medico);
        return new ResponseEntity<>(newMedico, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Medico> medico = medicoService.findById(id);
        if (medico.isEmpty()) {
            throw new ResourceNotFoundException("No existe el médico. ID: " + id);
        }

        medicoService.delete(medico.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createMedicoUri(Medico medico) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(medico.getId())
				.toUri();
	}

}
