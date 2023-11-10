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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.messaging.producers.MessageProducer;
import com.kdsAPI.messaging.producers.order.OrderEvent;
import com.kdsAPI.models.CanceledOrderDetails;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.order.OrderStatus;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.responses.ControllerResponse;
import com.kdsAPI.responses.PaginatedContentResponse;
import com.kdsAPI.responses.Response;
import com.kdsAPI.services.AbstractService;
import com.kdsAPI.services.state.FoodOrderCompleteStateService;
import com.kdsAPI.services.state.FoodOrderState;
import com.kdsAPI.services.state.FoodOrderWaitingStateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final AbstractService<FoodOrder, OrderDTO> storeOrderService;
    private final ControllerResponse<FoodOrder> response;
    private final PaginatedContentResponse<List<FoodOrder>> paginatedContentResponse;
    private FoodOrderState foodOrderState;
    private final MessageProducer<OrderEvent> orderMessageProducer;
    private final Integer DEFAULT_PAGE_SIZE = 10;
    
    @GetMapping
    public PaginatedContentResponse<List<FoodOrder>> getOrders(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        foodOrderState = new FoodOrderWaitingStateService((OrderRepository)storeOrderService.getRepository());
        paginatedContentResponse.setContent(foodOrderState.getAll(pageNumber, DEFAULT_PAGE_SIZE));
        paginatedContentResponse.setPagination(foodOrderState.getPaginationResponse(pageNumber));
        return paginatedContentResponse;
    }

    @GetMapping("/archive")
    public PaginatedContentResponse<List<FoodOrder>> getCompleteOrders(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        foodOrderState = new FoodOrderCompleteStateService((OrderRepository)storeOrderService.getRepository());
        paginatedContentResponse.setContent(foodOrderState.getAll(pageNumber, DEFAULT_PAGE_SIZE));
        paginatedContentResponse.setPagination(foodOrderState.getPaginationResponse(pageNumber));
        return paginatedContentResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> getOrder(@PathVariable Long id) {
        FoodOrder foundOrder = storeOrderService.getById(id);
        return response.ok(foundOrder);
    }

    @PostMapping
    public ResponseEntity<Response<FoodOrder>> save(@Valid @RequestBody OrderDTO order) {
        FoodOrder saveOrder = storeOrderService.save(order);
        return response.ok(saveOrder);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Response<FoodOrder>> confirmOrder(@PathVariable Long id) {
        FoodOrder updatedOrder = updatOrderStatusById(id, OrderStatus.CONFIRMED);
        if(updatedOrder.getIfoodOrderId() != null) {
            OrderEvent event = new OrderEvent(updatedOrder.getIfoodOrderId(), updatedOrder.getFoodOrderStatus());
            emmitOrderEvent(updatedOrder, event);
        }
        return response.ok(updatedOrder);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Response<FoodOrder>> cancelOrder(@PathVariable Long id, @Valid @RequestBody CanceledOrderDetails canceledOrderEvent) {
        FoodOrder updatedOrder = updatOrderStatusById(id, OrderStatus.CANCELED);
        if(updatedOrder.getIfoodOrderId() != null) {
            OrderEvent event = new OrderEvent(
                updatedOrder.getIfoodOrderId(), 
                updatedOrder.getFoodOrderStatus(),
                canceledOrderEvent.getCancellationCode(),
                canceledOrderEvent.getReason()
            );
            emmitOrderEvent(updatedOrder, event);
        }
        return response.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> delete(@PathVariable Long id) {
        storeOrderService.deleteById(id);
        return response.customResponse(HttpStatus.NO_CONTENT, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FoodOrder>> update(@PathVariable Long id, @Valid @RequestBody OrderDTO order) {
        order.setId(id);
        FoodOrder updatedFoodOrder = storeOrderService.update(order);
        if(updatedFoodOrder.getIfoodOrderId() != null) {
            OrderEvent event = new OrderEvent(order.getIfoodOrderId(), order.getFoodOrderStatus());
            emmitOrderEvent(updatedFoodOrder, event);
        }
        return response.ok(updatedFoodOrder);
    }
    
    private FoodOrder updatOrderStatusById(Long id, OrderStatus orderStatus) {
        FoodOrder order = storeOrderService.getById(id);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.convertToDTO(order);
        orderDTO.setFoodOrderStatus(orderStatus);
        return storeOrderService.update(orderDTO);
    }

    private void emmitOrderEvent(FoodOrder order, OrderEvent orderEvent) {
        if(order.getFoodOrderStatus() == OrderStatus.WAITING) {
            return;
        }
        if(order.getFoodOrderStatus() == OrderStatus.CANCELED) {
             orderMessageProducer.sendMessage(orderEvent, "order.client.canceled");
             return;
        }
        orderMessageProducer.sendMessage(orderEvent, "order.updated");
    }

}
