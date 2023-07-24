package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT obj FROM Review obj JOIN FETCH obj.user JOIN FETCH obj.movie WHERE obj.movie.id = :id")
    List<Review> searchReviewsByMovieId(Long id);

}
