package com.kdsAPI.dto;

public interface DTO<T> {
    public T convertToDAO();
    public DTO<T> convertToDTO(T dao);
}
