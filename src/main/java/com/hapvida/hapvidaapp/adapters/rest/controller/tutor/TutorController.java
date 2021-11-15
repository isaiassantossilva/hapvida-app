package com.hapvida.hapvidaapp.adapters.rest.controller.tutor;

import com.hapvida.hapvidaapp.adapters.rest.controller.tutor.request.TutorRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.tutor.request.TutorUpdateRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.tutor.response.TutorResponse;
import com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.request.VeterinaryRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.veterinary.response.VeterinaryResponse;
import com.hapvida.hapvidaapp.application.entity.Animal;
import com.hapvida.hapvidaapp.application.usecase.tutor.*;
import com.hapvida.hapvidaapp.application.valueobject.TutorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tutors")
@RequiredArgsConstructor
public class TutorController {
    private final CreateNewTutor createNewTutor;
    private final FindTutorById findTutorById;
    private final SearchTutorByFilter searchTutorByFilter;
    private final UpdateTutor updateTutor;
    private final DeleteTutor deleteTutor;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TutorResponse createNewTutor(@RequestBody TutorRequest tutorRequest){
        var tutorFromDb =  createNewTutor.execute(
                tutorRequest.getName(),
                tutorRequest.getEmail(),
                tutorRequest.getPhoneNumber(),
                tutorRequest.getAnimals().stream().map(TutorRequest.AnimalBriefRequest::toAnimal).collect(Collectors.toList())
        );
        return TutorResponse.fromTutor(tutorFromDb);
    }


    @GetMapping("/{tutorId}")
    public TutorResponse findById(@PathVariable String tutorId){
        var tutorFromDb = findTutorById.execute(tutorId);
        return TutorResponse.fromTutor(tutorFromDb);
    }


    @GetMapping
    public List<TutorResponse> findByFilter(TutorFilter filter){
        var tutors = searchTutorByFilter.execute(filter);
        return tutors.stream().map(TutorResponse::fromTutor).collect(Collectors.toList());
    }


    @PatchMapping("/{tutorId}")
    public TutorResponse updateTutor(@PathVariable String tutorId,
                                               @RequestBody TutorUpdateRequest tutorUpdateRequest){
        var tutor = updateTutor.execute(
                tutorId,
                tutorUpdateRequest.getName(),
                tutorUpdateRequest.getPhoneNumber(),
                tutorUpdateRequest.getEmail()
        );
        return TutorResponse.fromTutor(tutor);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{tutorId}")
    public void delete(@PathVariable String tutorId){
        deleteTutor.execute(tutorId);
    }
}
