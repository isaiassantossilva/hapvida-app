package com.hapvida.hapvidaapp.application.repository;

import com.hapvida.hapvidaapp.application.entity.Veterinary;
import com.hapvida.hapvidaapp.application.valueobject.VeterinaryFilter;

import java.util.List;

public interface VeterinaryRepository {
    Veterinary save(Veterinary veterinary);
    Veterinary findByUsername(String username);
    List<Veterinary> containsStringInName(String name);
    List<Veterinary> findByFilter(VeterinaryFilter filter);
    Veterinary findById(String id);

    void delete(String id);
}
