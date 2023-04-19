package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Mentor;
import com.gitaeklee.invest.repository.repo.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    @Transactional
    public Long saveMentor(Mentor mentor) {
        // validationMentor(mentor.getUser().getId());
        mentorRepository.saveMentor(mentor);
        return mentor.getId();
    }

    public void validationMentor(Long id) {
        Mentor validMentor = mentorRepository.findById(id);
        if (validMentor != null) {
            throw new IllegalStateException("Exist Mentor");
        }
    }

    public Mentor findMentorByid(Long id) {
        return mentorRepository.findById(id);
    }

    public Mentor findMentorByName(String name) {
        return mentorRepository.findByName(name);
    }

    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }

    @Transactional
    public void updatePrice(int price, Long id) {
        Mentor mentor = mentorRepository.findById(id);
        mentor.setPrice(price);
    }
}
