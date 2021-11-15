package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.exception.ResourceNotFound;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindVeterinaryById {

    private final VeterinaryRepository veterinaryRepository;

    public Veterinary execute(String id){
        var veterinary = veterinaryRepository.findById(id);
        if(veterinary == null)
            throw new ResourceNotFound("Veterinary with id " + id + " not found");
        return veterinary;
    }
}
