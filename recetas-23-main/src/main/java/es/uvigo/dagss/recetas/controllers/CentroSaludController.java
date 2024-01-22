package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.services.CentroSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/centros-salud", produces = MediaType.APPLICATION_JSON_VALUE)
public class CentroSaludController {

    @Autowired
    private CentroSaludService centroSaludService;

    @GetMapping
    public ResponseEntity<List<CentroSalud>> getAll() {
        List<CentroSalud> centrosSalud = centroSaludService.getAll();
        return ResponseEntity.ok(centrosSalud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroSalud> getById(@PathVariable long id) {
        Optional<CentroSalud> centroSalud = centroSaludService.getById(id);
        return centroSalud.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CentroSalud> create(@RequestBody CentroSalud centroSalud) {
        CentroSalud newCentroSalud = centroSaludService.create(centroSalud);
        URI uri = createCentroSaludUri(newCentroSalud);

        return ResponseEntity.created(uri).body(newCentroSalud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroSalud> update(@PathVariable long id, @RequestBody CentroSalud centroSalud) {
        if (!centroSaludService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        centroSalud.setCenterId(id);
        CentroSalud updatedCentroSalud = centroSaludService.update(centroSalud);
        return ResponseEntity.ok(updatedCentroSalud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!centroSaludService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        centroSaludService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI createCentroSaludUri(CentroSalud centroSalud) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(centroSalud.getCenterId()).toUri();
	}
}
