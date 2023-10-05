package com.kdsAPI.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractService<T, U extends DTO<T>> {

    protected final JpaRepository<T, Long> repository;

    public List<T> getAll() {
        return repository.findAll();
    }

    public abstract T save(U dto);

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public T update(U dto) {
        return repository.save(dto.convertToDAO());
    }
    public T getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NotFoundException());
    }
}
