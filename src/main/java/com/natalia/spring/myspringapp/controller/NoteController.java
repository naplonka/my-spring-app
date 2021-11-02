package com.natalia.spring.myspringapp.controller;

import com.natalia.spring.myspringapp.exercise.Note;
import com.natalia.spring.myspringapp.exercise.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

@RestController
public class NoteController {
    public static final Logger logger = LoggerFactory.getLogger(NoteRepository.class);
    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/notes")
    ResponseEntity<List<Note>> readAllTasks(Pageable pageable) {
        logger.warn("Custom pageable");
        return ResponseEntity.ok(repository.findAll((org.springframework.data.domain.Pageable) pageable).getContent());
    }

    @PutMapping("/notes/{id}")
    ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Note toUpdate) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        repository.save(toUpdate);//zapisujemy to korzystająć z repository
        return ResponseEntity.noContent().build();//zwracamy 204 not content i budujemy
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Note> findById (@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    ResponseEntity<?> postTask(@RequestBody @Valid Note toPost) {
        Note result = repository.save(toPost);
        return ResponseEntity.created(URI.create("/" + result.getId())).build();
    }


}
