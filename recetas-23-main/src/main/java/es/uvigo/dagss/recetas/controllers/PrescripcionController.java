package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.services.PrescripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/prescripciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescripcionController {

    @Autowired
    private PrescripcionService prescripcionService;

    @GetMapping
    public ResponseEntity<List<Prescripcion>> getAll() {
        List<Prescripcion> prescripciones = prescripcionService.getAll();
        return ResponseEntity.ok(prescripciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescripcion> getById(@PathVariable long id) {
        Optional<Prescripcion> prescripcion = prescripcionService.getById(id);
        return prescripcion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prescripcion> create(@RequestBody Prescripcion prescripcion) {
        Prescripcion newPrescripcion = prescripcionService.create(prescripcion);
        URI uri = createPrescripcionUri(newPrescripcion);

        return ResponseEntity.created(uri).body(newPrescripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescripcion> update(@PathVariable long id, @RequestBody Prescripcion prescripcion) {
        if (!prescripcionService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prescripcion.setPrescriptionId(id);
        Prescripcion updatedPrescripcion = prescripcionService.update(prescripcion);
        return ResponseEntity.ok(updatedPrescripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!prescripcionService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        prescripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI createPrescripcionUri(Prescripcion prescripcion) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(prescripcion.getPrescriptionId()).toUri();
    }
}

