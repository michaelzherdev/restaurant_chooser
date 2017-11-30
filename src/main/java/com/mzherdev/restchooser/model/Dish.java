package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
})
@Entity
@Table(name = "dishes")
@NoArgsConstructor
@JsonRootName(value = "diet")
public class Dish implements AbstractEntity {
    public static final String ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";

    @Id
    @SequenceGenerator(name = "dish_seq", sequenceName = "dish_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_seq")
    @Getter @Setter
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @NotNull
    @Column(name = "price", nullable = false)
    @Digits(integer=10, fraction=2)
    @Getter @Setter
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    @JsonIgnore
    @Getter @Setter
    private List<Menu> menu;

    public Dish(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Dish(int id, double price, String name) {
        this(price, name);
        this.id = id;
    }

    public Dish(Dish dish) {
        this.id = dish.id;
        this.price = dish.price;
        this.name = dish.name;
        this.menu = dish.menu;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (!id.equals(dish.id)) return false;
        return name.equals(dish.name);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
