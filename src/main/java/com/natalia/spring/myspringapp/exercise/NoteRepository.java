package com.natalia.spring.myspringapp.exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


public interface NoteRepository{
    List<Note> findAll();

    Page<Note> findAll(Pageable pageable);

    Optional<Note> findById(Integer id);

    Note save(Note entity);

    boolean existsById(Integer id);

//    List<Note> findByDone(@Param("state") boolean desc);
}
