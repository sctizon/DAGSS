package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.Medicina;
import es.uvigo.dagss.recetas.services.MedicinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/medicinas", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicinaController {

    @Autowired
    private MedicinaService medicinaService;

    @GetMapping
    public ResponseEntity<List<Medicina>> getAll() {
        List<Medicina> medicinas = medicinaService.getAll();
        return ResponseEntity.ok(medicinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicina> getById(@PathVariable long id) {
        Optional<Medicina> medicina = medicinaService.getById(id);
        return medicina.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medicina> create(@RequestBody Medicina medicina) {
        Medicina newMedicina = medicinaService.create(medicina);
        URI uri = createMedicinaUri(newMedicina);

        return ResponseEntity.created(uri).body(newMedicina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicina> update(@PathVariable long id, @RequestBody Medicina medicina) {
        if (!medicinaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicina.setMedicineId(id);
        Medicina updatedMedicina = medicinaService.update(medicina);
        return ResponseEntity.ok(updatedMedicina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicina(@PathVariable long id) {
        if (!medicinaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI createMedicinaUri(Medicina medicina) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(medicina.getMedicineId()).toUri();
	}
}

