package br.com.borges.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_dishes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @Column(name = "order_value")
    private BigDecimal orderValue;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Menu menu;

    public OrderDishes(Order order, Menu menu, Integer quantity) {
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
        this.orderValue = menu.getValueDish().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "OrderDishes{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", orderValue=" + orderValue +
                ", menu=" + menu +
                '}';
    }
}
