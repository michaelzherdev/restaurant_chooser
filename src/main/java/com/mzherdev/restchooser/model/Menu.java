package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mzherdev.restchooser.util.json.DishDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Menu.ALL, query = "SELECT m FROM Menu m"),
        @NamedQuery(name = Menu.GET_ONE_FOR_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.GET_ALL_FOR_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "menus")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu implements AbstractEntity {

    public static final String ALL = "Menu.getAll";
    public static final String GET_ONE_FOR_RESTAURANT = "Menu.getOneForRestaurant";
    public static final String GET_ALL_FOR_RESTAURANT = "Menu.getAllForRestaurant";

    @Id
    @SequenceGenerator(name = "menu_seq", sequenceName = "menu_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @Getter @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter @Setter
    private Restaurant restaurant;

    @Column(name = "day", nullable = false)
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @Getter @Setter
    private LocalDate day;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_dish_link", joinColumns = {
            @JoinColumn(name = "menu_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "dish_id",
                    nullable = false, updatable = false) })
    @JsonDeserialize(using = DishDeserializer.class)
    @Getter @Setter
    protected List<Dish> dishes = new ArrayList<>();

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    public Menu(LocalDate day) {
        this.day = day;
    }

    public Menu(int id, LocalDate day) {
        this.id = id;
        this.day = day;
    }

    public Menu(Menu menu) {
        this.id = menu.id;
        this.day = menu.day;
        this.restaurant = menu.restaurant;
        this.dishes = menu.dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", day=" + day +
                ", dishes=" + dishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return id.equals(menu.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
