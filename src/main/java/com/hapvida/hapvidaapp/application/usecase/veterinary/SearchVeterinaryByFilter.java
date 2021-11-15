package com.hapvida.hapvidaapp.application.usecase.veterinary;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.repository.VeterinaryRepository;
import com.hapvida.hapvidaapp.application.valueobject.VeterinaryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchVeterinaryByFilter {

    private final VeterinaryRepository veterinaryRepository;

    public List<Veterinary> execute(VeterinaryFilter filter){
        return veterinaryRepository.findByFilter(filter);
    }
}
