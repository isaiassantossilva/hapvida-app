package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchVeterinariansContainStringInName {

    private final VeterinaryRepository veterinaryRepository;


    public List<Veterinary> execute(String name){
        return veterinaryRepository.containsStringInName(name);
    }
}
