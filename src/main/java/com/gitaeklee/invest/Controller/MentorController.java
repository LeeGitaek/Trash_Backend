package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Mentor;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.MentorDto;
import com.gitaeklee.invest.repository.dto.request.MentorRequest;
import com.gitaeklee.invest.service.MentorService;
import com.gitaeklee.invest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;
    private final UserService userService;

    // FIND BY ID , FIND BY NAME , FIND ALL , SAVE , UPDATE PRICE
    @PostMapping("/api/v2/mentor/register")
    public MentorResponse registerMentor(@RequestBody @Validated MentorRequest request) {
        User user = userService.findOne(request.getUserId());

        Mentor mentor = new Mentor();
        mentor.setUser(user);
        mentor.setPrice(request.getPrice());
        mentor.setDatetime();

        Long id = mentorService.saveMentor(mentor);
        return new MentorResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class MentorResponse {
        private Long id;
    }

    @PostMapping("/api/v2/mentor/update/price")
    public MentorDto updatePrice(@RequestBody @Validated MentorUpdateRequest request) {
        Mentor mentor = mentorService.findMentorByid(request.getMentorId());
        mentor.setPrice(request.getPrice());
        return new MentorDto(mentor);
    }

    @Data
    @AllArgsConstructor
    static class MentorUpdateRequest {
        private Long mentorId;
        private int price;
    }

    @GetMapping("/api/v2/mentor/{id}")
    public MentorDto findMentor(@PathVariable(value = "id") Long mentorId) {
        Mentor findMentor = mentorService.findMentorByid(mentorId);
        return new MentorDto(findMentor);
    }

    @GetMapping("/api/v2/mentor")
    public MentorDto findMentorByName(@RequestParam(value = "name") String userName) {
        Mentor findMentor = mentorService.findMentorByName(userName);
        return new MentorDto(findMentor);
    }

    @GetMapping("/api/v2/mentors")
    public List<MentorDto> findAll() {
        List<Mentor> mentors = mentorService.findAll();
        System.out.println("mentor list = " + mentors);
        return mentors.stream()
                .map(o -> new MentorDto(o))
                .collect(Collectors.toList());
    }

}
