package com.kdsAPI.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdsAPI.dto.item.ItemDTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.responses.ControllerResponse;
import com.kdsAPI.responses.Response;
import com.kdsAPI.services.AbstractService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order/item")
@RequiredArgsConstructor
public class ItemController {
    private final AbstractService<FoodItem, ItemDTO> itemService;
    private final ControllerResponse<FoodItem> response;

    @GetMapping
    public List<FoodItem> getAll() {
        return itemService.getAll();
    }

    @PostMapping
    public ResponseEntity<Response<FoodItem>> save(@Valid @RequestBody ItemDTO item) {
        FoodItem saveditem = itemService.save(item);
        return response.ok(saveditem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FoodItem>> update(@PathVariable Long id, @Valid @RequestBody ItemDTO item) {
        item.setId(id);
        FoodItem updatedItem = itemService.update(item);
        return response.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodItem>> delete(@PathVariable Long id) {
        itemService.deleteById(id);
        return response.customResponse(HttpStatus.NO_CONTENT, null);
    }
}
