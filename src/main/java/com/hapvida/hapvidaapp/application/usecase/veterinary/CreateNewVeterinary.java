package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateNewVeterinary {
    private final VeterinaryRepository veterinaryRepository;

    public Veterinary execute(String name, String email, String phoneNumber){
        var veterinaryToSave = new Veterinary(name, email, phoneNumber);
        var veterinaryFromDb = veterinaryRepository.findByUsername(veterinaryToSave.getEmail());
        if(veterinaryFromDb != null)
            throw new IllegalArgumentException("Veterinary with email " + email + " already exists");
        return veterinaryRepository.save(veterinaryToSave);
    }
}
