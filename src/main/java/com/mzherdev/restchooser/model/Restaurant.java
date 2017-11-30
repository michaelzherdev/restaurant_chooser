package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL, query = "SELECT DISTINCT(r) FROM Restaurant r ORDER BY r.name"),
})
@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@ToString(exclude = {"votes"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant implements AbstractEntity {
    public static final String DELETE = "Restaurant.delete";
    public static final String ALL = "Restaurant.getAll";

    @Id
    @SequenceGenerator(name = "restaurant_seq", sequenceName = "restaurant_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    @Getter @Setter
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String name;

    @NotEmpty
    @Column(name = "description", nullable = false)
    @Getter @Setter
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @Getter @Setter
    @JsonIgnore
    protected List<Menu> menus = new ArrayList<>();

    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @Getter @Setter
    @JsonIgnore
    private List<Vote> votes = new ArrayList<>();

    public List<Menu> getMenu() {
        return menus;
    }

    public Integer getVoteCount() { return votes.size(); }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    public Restaurant(String name, String description) {
        this.name = name;
        this.description = description;
        this.votes = new ArrayList<>();
        this.menus = new ArrayList<>();
    }

    public Restaurant(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.votes = new ArrayList<>();
        this.menus = new ArrayList<>();
    }

    public Restaurant(Restaurant restaurant) {
        this.id = restaurant.id;
        this.name = restaurant.name;
        this.description = restaurant.description;
        this.votes = restaurant.votes;
        this.menus = restaurant.menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // for testing rest
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
