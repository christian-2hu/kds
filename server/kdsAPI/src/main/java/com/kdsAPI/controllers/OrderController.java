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

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.responses.ControllerResponse;
import com.kdsAPI.responses.Response;
import com.kdsAPI.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService storeOrderService;
    private final ControllerResponse<FoodOrder> response;
    
    @GetMapping
    public List<FoodOrder> getOrders() {
        return storeOrderService.getOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> getOrder(@PathVariable Long id) {
        FoodOrder foundOrder = storeOrderService.getOrderById(id);
        return response.ok(foundOrder);
    }

    @PostMapping
    public ResponseEntity<Response<FoodOrder>> save(@Valid @RequestBody OrderDTO order) {
        FoodOrder saveOrder = storeOrderService.saveOrder(order);
        return response.ok(saveOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> delete(@PathVariable Long id) {
        storeOrderService.deleteOrder(id);
        return response.customResponse(HttpStatus.NO_CONTENT, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> update(@PathVariable Long id, @Valid @RequestBody OrderDTO order) {
        order.setId(id);
        FoodOrder updatedFoodOrder = storeOrderService.updateOrder(order);
        return response.ok(updatedFoodOrder);
    }
}