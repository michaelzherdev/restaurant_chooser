package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mzherdev.restchooser.util.json.DishDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@NamedQueries({
        @NamedQuery(name = Menu.ALL, query = "SELECT m FROM Menu m"),
        @NamedQuery(name = Menu.GET_ONE_FOR_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.GET_ALL_FOR_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId"),
})
@Entity
@Table(name = "menus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu {

    public static final String ALL = "Menu.getAll";
    public static final String GET_ONE_FOR_RESTAURANT = "Menu.getOneForRestaurant";
    public static final String GET_ALL_FOR_RESTAURANT = "Menu.getAllForRestaurant";

    public Menu() {    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Restaurant restaurant;

    @Column(name = "day", nullable = false)
    @NotNull
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime day;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "menu")
//    @JsonIgnore
    @JsonDeserialize(using = DishDeserializer.class)
    protected List<Dish> dishes;

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", day=" + day +
                ", dishes=" + dishes +
                '}';
    }
}
