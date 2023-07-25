package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.projections.ReviewProjection;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
        entity.setText(dto.getText());
        entity.setMovie(new Movie(dto.getMovieId()));
        entity.setUser(authService.authenticated());
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);
    }


    public List<ReviewDTO> findReviews(Long id) {
        List<ReviewProjection> list = reviewRepository.searchReviewsByMovieId(id);
        return list.stream().map(ReviewDTO::new).toList();
    }
}
