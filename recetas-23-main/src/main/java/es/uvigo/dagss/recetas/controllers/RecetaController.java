package es.uvigo.dagss.recetas.controllers;

import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/recetas", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<Receta>> getAll() {
        List<Receta> recetas = recetaService.getAll();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getById(@PathVariable long id) {
        Optional<Receta> receta = recetaService.getById(id);
        return receta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receta> create(@RequestBody Receta receta) {
        Receta newReceta = recetaService.create(receta);
        URI uri = createRecetaUri(newReceta);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newReceta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> update(@PathVariable long id, @RequestBody Receta receta) {
        if (!recetaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        receta.setRecipeId(id);
        Receta updatedReceta = recetaService.update(receta);
        return ResponseEntity.ok(updatedReceta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!recetaService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private URI createRecetaUri(Receta receta) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(receta.getRecipeId()).toUri();
    }
}

