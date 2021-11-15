package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateVeterinary {

    private final FindVeterinaryById findVeterinaryById;
    private final VeterinaryRepository veterinaryRepository;

    public Veterinary execute(String id, String name, String phoneNumber, String email){
        var veterinaryToUpdate = findVeterinaryById.execute(id);
        veterinaryToUpdate.replace(name, phoneNumber, email);
        var veterinaryFromDb  = veterinaryRepository.findByUsername(veterinaryToUpdate.getEmail());
        if(veterinaryFromDb != null && !veterinaryToUpdate.getId().equals(veterinaryFromDb.getId()))
            throw new IllegalArgumentException("Veterinary with email " + email + " already exists");
        return veterinaryRepository.save(veterinaryToUpdate);
    }
}
