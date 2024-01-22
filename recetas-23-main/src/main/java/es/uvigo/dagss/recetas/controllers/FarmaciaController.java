package es.uvigo.dagss.recetas.controllers;

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

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.exceptions.ResourceNotFoundException;
import es.uvigo.dagss.recetas.services.FarmaciaService;


@RestController
@RequestMapping(path = "/farmacias", produces = MediaType.APPLICATION_JSON_VALUE)
public class FarmaciaController {
    
    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping
    public ResponseEntity<List<Farmacia>> getAll() {
        List<Farmacia> res = farmaciaService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Farmacia> getById(@PathVariable("id") Long id) {
        Optional<Farmacia> farmacia = farmaciaService.findById(id);
        if (farmacia.isEmpty()) {
            throw new ResourceNotFoundException("Farmacia " + id + " not found");
        }
        return new ResponseEntity<>(farmacia.get(), HttpStatus.OK);

    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farmacia> create(@RequestBody Farmacia farmacia) {
        Farmacia newFarmacia = farmaciaService.create(farmacia);
		URI uri = createFarmaciaUri(newFarmacia);

		return ResponseEntity.created(uri).body(farmacia);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farmacia> update(@PathVariable("id") Long id, @RequestBody Farmacia farmacia) {
        Optional<Farmacia> optionalFarmacia = farmaciaService.findById(id);
        farmacia.setId(id);
		if (optionalFarmacia.isEmpty()) {
            throw new ResourceNotFoundException("No existe la farmacia. ID: " + id);
		}

        Farmacia newFarmacia = farmaciaService.update(farmacia);
        return new ResponseEntity<>(newFarmacia, HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Optional<Farmacia> farmacia = farmaciaService.findById(id);
        if (farmacia.isEmpty()) {
            throw new ResourceNotFoundException("No existe la farmacia. ID: " + id);
        }

        farmaciaService.delete(farmacia.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI createFarmaciaUri(Farmacia farmacia) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(farmacia.getId())
				.toUri();
	}

}
