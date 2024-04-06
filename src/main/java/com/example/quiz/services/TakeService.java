package com.example.quiz.services;

import com.example.quiz.models.Take;
import com.example.quiz.repositories.TakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeService {
    public final TakeRepository takeRepository;

    public TakeService(TakeRepository takeRepository) {
        this.takeRepository = takeRepository;
    }

    // get all takes
    public List<Take> getAllTakes() {
        return takeRepository.findAll();
    }

    // create take
    public Take createTake(Take take) {
        return takeRepository.save(take);
    }

    // get take by id
    public Take getTakeById(Long id) {
        return takeRepository.findById(id).orElse(null);
    }

    // update take
    public Take updateTake(Long id, Take take) {
        Take existingTake = takeRepository.findById(id).orElse(null);
        if (existingTake == null) {
            return null;
        }
        existingTake.setScore(take.getScore());
        existingTake.setQuiz(take.getQuiz());
        existingTake.setUser(take.getUser());
        return takeRepository.save(existingTake);
    }

    // delete take
    public void deleteTake(Long id) {
        takeRepository.deleteById(id);
    }
}
