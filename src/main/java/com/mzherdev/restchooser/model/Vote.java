package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by mzherdev on 07.06.2016.
 */

@NamedQueries({
        @NamedQuery(name = Vote.ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.ALL_FOR_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant=:id"),
})
@Entity
@Table(name = "votes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vote {

    public static final String ALL = "Vote.getAll";
    public static final String ALL_FOR_RESTAURANT = "Vote.getAllForRestaurant";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vote_time", nullable = false)
    @NotNull
    private LocalDateTime voteTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteTime=" + voteTime +
                ", user=" + user +
                '}';
    }
}
