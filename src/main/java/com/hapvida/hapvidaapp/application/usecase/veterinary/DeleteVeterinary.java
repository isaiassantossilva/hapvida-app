package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteVeterinary {

    private final VeterinaryRepository veterinaryRepository;


    public void execute(String id){
        veterinaryRepository.delete(id);
    }
}
