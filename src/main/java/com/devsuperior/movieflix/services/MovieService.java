package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(String title, String genreId, Pageable pageable) {
        List<Long> genreIds = List.of();
        if (!"0".equals(genreId)) {
            genreIds = Arrays.stream(genreId.split(",")).map(Long::parseLong).toList();
        }
        Page<MovieProjection> page = movieRepository.searchMovies(genreIds, title, pageable);
        List<Long> movieIds = page.map(MovieProjection::getId).toList();

        List<Movie> entities = movieRepository.searchMoviesWithGenre(movieIds);
        List<MovieCardDTO> dtos = entities.stream().map(MovieCardDTO::new).toList();

        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }

}
