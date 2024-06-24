package br.com.borges.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean available;
    @Column(name = "value_dish")
    private BigDecimal valueDish;
    @Column(name = "date_register")
    private final LocalDateTime dtRegister = LocalDateTime.now();

    @ManyToOne
    private Category category;

    /*@ManyToMany(mappedBy = "dishes")
    private List<Order> orders;*/

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", valueDish=" + valueDish +
                ", dtRegister=" + dtRegister +
                ", category=" + category +
                '}';
    }
}
