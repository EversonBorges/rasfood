package br.com.borges.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal amount = BigDecimal.ZERO;
    @Column(name = "dt_order")
    private LocalDateTime dtOrder = LocalDateTime.now();

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderDishes> orderDishes = new ArrayList<>();

    /*@ManyToMany
    @JoinTable(
            name = "orders_menu",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> dishes;
    */

    public Order(Client client) {
        this.client = client;
    }

    public void addOrderDishes(OrderDishes orderDishes){
        orderDishes.setOrder(this);
        this.orderDishes.add(orderDishes);
    }

    public void setTotalValue(BigDecimal amount){
       this.amount = this.amount.add(amount);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", dtOrder=" + dtOrder +
                ", client=" + client +
                ", orderDishes=" + orderDishes +
                '}';
    }
}
