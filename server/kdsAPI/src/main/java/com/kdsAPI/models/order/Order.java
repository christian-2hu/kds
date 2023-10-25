package com.kdsAPI.models.order;

import java.util.Date;
import java.util.List;

import com.kdsAPI.models.FoodItem;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
public abstract class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String costumerName;
    @NotNull(message = "\"This field cannot be empty\"")
    @Column(name="foodOrder")
    @OneToMany
    @JoinColumn(name = "items_id")
    protected List<FoodItem> orders;

    protected OrderStatus foodOrderStatus;
    protected String ifoodOrderId;
    protected Date createdAt;
    protected String observations;

    public Order() {
        this.foodOrderStatus = OrderStatus.WAITING;
    }

    public Order(Long id, OrderStatus fooOrderStatus) {
        this.id = id;
        this.foodOrderStatus = fooOrderStatus;
    }

    public void addFoodItem(FoodItem item) {
        this.orders.add(item);
    }

    public void deleteFoodItem(FoodItem item) {
        this.orders.remove(item);
    }

}
