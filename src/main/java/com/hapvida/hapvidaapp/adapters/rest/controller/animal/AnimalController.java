package com.hapvida.hapvidaapp.adapters.rest.controller.animal;

import com.hapvida.hapvidaapp.adapters.rest.controller.animal.request.AnimalRequest;
import com.hapvida.hapvidaapp.adapters.rest.controller.animal.response.AnimalResponse;
import com.hapvida.hapvidaapp.application.entity.enums.Specie;
import com.hapvida.hapvidaapp.application.usecase.animal.CreateNewAnimal;
import com.hapvida.hapvidaapp.application.usecase.animal.DeleteAnimal;
import com.hapvida.hapvidaapp.application.usecase.animal.FindAnimalById;
import com.hapvida.hapvidaapp.application.usecase.animal.UpdateAnimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final CreateNewAnimal createNewAnimal;
    private final FindAnimalById findAnimalById;
    private final DeleteAnimal deleteAnimal;
    private final UpdateAnimal updateAnimal;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AnimalResponse createNewAnimal(@RequestBody AnimalRequest animalRequest){
        var animal = createNewAnimal.execute(
                animalRequest.getName(),
                animalRequest.getSpecie(),
                animalRequest.getBreed(),
                animalRequest.getBirthDate(),
                animalRequest.getTutor()
        );
        return AnimalResponse.fromAnimal(animal);
    }


    @GetMapping("/{animalId}")
    public AnimalResponse findById(@PathVariable String animalId){
        var animal = findAnimalById.execute(animalId);
        return AnimalResponse.fromAnimal(animal);
    }


    @PatchMapping("/{animalId}")
    public AnimalResponse update(@PathVariable String animalId, @RequestBody AnimalRequest animalRequest){
        var animal = updateAnimal.execute(
                animalId,
                animalRequest.getName(),
                animalRequest.getSpecie(),
                animalRequest.getBreed(),
                animalRequest.getBirthDate(),
                animalRequest.getTutor()
        );
        return AnimalResponse.fromAnimal(animal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{animalId}")
    public void delete(@PathVariable String animalId){
        deleteAnimal.execute(animalId);
    }

    @GetMapping("/species")
    public Specie[] getAllSpecies(){
        return Specie.values();
    }

}
