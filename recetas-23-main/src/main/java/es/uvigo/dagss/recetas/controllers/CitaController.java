package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/citas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        List<Cita> citas = citaService.getAll();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable long id) {
        Optional<Cita> cita = citaService.getById(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        Cita newCita = citaService.create(cita);
        URI uri = createCitaUri(newCita);

        return ResponseEntity.created(uri).body(newCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable long id, @RequestBody Cita cita) {
        if (!citaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cita.setCitaId(id);
        Cita updatedCita = citaService.update(cita);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!citaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        citaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI createCitaUri(Cita cita) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cita.getCitaId()).toUri();
	}
}
