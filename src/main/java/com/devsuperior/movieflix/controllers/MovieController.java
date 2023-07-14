package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findAll(Pageable pageable,
                                                      @RequestParam(value = "title", defaultValue = "") String title,
                                                      @RequestParam(value = "genreId", defaultValue = "0") String genreId) {
        Page<MovieCardDTO> list = movieService.findAllPaged(title, genreId, pageable);
        return ResponseEntity.ok().body(list);
    }

}
