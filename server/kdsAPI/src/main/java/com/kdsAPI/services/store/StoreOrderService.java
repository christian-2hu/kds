package com.kdsAPI.services.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdsAPI.dto.item.ItemDTO;
import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.order.OrderStatus;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.services.AbstractService;

@Service
public class StoreOrderService extends AbstractService<FoodOrder, OrderDTO> {

    private AbstractService<FoodItem, ItemDTO> foodItemService;

    public StoreOrderService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public FoodOrder save(OrderDTO order) {
        // TODO: Pass this funcionallity to a IfoodOrderService or something like that, this doesn't belong to the store
        if(order.getFoodOrderStatus() == OrderStatus.CANCELED) {
            return updateCanceledOrder(order);
        }
        List<FoodItem> items = processItems(order.getOrders());
        order.setOrders(items);
        return repository.save(order.convertToDAO());
    }

    private FoodOrder findByIfoodOrderId(String findByIfoodOrderId) {
        OrderRepository repository = (OrderRepository) this.repository;
        return repository.findByIfoodOrderId(findByIfoodOrderId);
    }

    private FoodOrder updateCanceledOrder(OrderDTO order) {
        FoodOrder canceledOrder = findByIfoodOrderId(order.getIfoodOrderId());
        if(canceledOrder != null) {
            order.setId(canceledOrder.getId());
        }
        return update(order);        
    }

    
    private List<FoodItem> processItems(List<FoodItem> items) {
        for(int i = 0; i < items.size(); i++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO = (ItemDTO)itemDTO.convertToDTO(items.get(i));
            FoodItem foodItem = foodItemService.save(itemDTO);
            items.set(i, foodItem);
        }
        return items;
    }

    @Autowired
    public void setFoodItemService(AbstractService<FoodItem, ItemDTO> foodItemService) {
        this.foodItemService = foodItemService;
    }

}
