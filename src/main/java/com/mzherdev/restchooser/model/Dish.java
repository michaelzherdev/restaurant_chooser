package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by mzherdev on 07.06.2016.
 */
@NamedQueries({
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId"),
})
@Entity
@Table(name = "dishes")
@JsonRootName(value = "diet")
public class Dish {
    public static final String ALL = "Dish.getAll";
    public static final String GET = "Dish.get";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    private Menu menu;

    public Dish() {
    }

    public Dish(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
}
