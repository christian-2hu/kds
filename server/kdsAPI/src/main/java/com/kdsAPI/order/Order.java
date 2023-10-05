package com.kdsAPI.order;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.FoodOrder;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToMany
    @NotNull(message = "\"This field cannot be empty\"")
    protected List<FoodItem> foodItem;
    @ElementCollection
    @NotNull(message = "\"This field cannot be empty\"")
    protected List<Integer> quantity;
    protected OrderStatus foodOrderStatus;

    public Order() {
        this.foodOrderStatus = OrderStatus.WAITING;
        this.foodItem = new LinkedList<>();
        this.quantity = new LinkedList<>();
    }

    public Order(Long id, OrderStatus fooOrderStatus) {
        this.id = id;
        this.foodOrderStatus = fooOrderStatus;
        this.foodItem = new LinkedList<>();
        this.quantity = new LinkedList<>();
    }

    public Order(Long id, List<FoodItem> foodItem, List<Integer> quantity, OrderStatus fooOrderStatus) {
        this.id = id;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.foodOrderStatus = fooOrderStatus;
    }

    public void addFoodItem(FoodItem item, Integer quantity) {
        this.foodItem.add(item);
        this.quantity.add(quantity);
    }
    public void deleteFoodItem(FoodItem item) {
        int quantityIndex = FoodOrder.<FoodItem>getIndexFromIterator(this.foodItem.iterator(), item);
        this.quantity.remove(quantityIndex);
        this.foodItem.remove(item);
    }
    
    protected static <T> int getIndexFromIterator(Iterator<T> iterator, T object) {
        int count = 0;
        while(iterator.hasNext()) {
            if(iterator.next().equals(object)) {
                return count;
            }
            count++;
        }
        return -1;
    }
}
