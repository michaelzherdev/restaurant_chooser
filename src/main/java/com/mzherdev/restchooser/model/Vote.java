package com.mzherdev.restchooser.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.ALL_FOR_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId"),
        @NamedQuery(name = Vote.ALL_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId")
})
@Entity
@Table(name = "votes")
@ToString(exclude = {"user", "restaurant"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vote implements AbstractEntity {

    public static final String ALL = "Vote.getAll";
    public static final String ALL_FOR_RESTAURANT = "Vote.getAllForRestaurant";
    public static final String ALL_BY_USER = "Vote.getAllByUser";

    @Id
    @SequenceGenerator(name = "vote_seq", sequenceName = "vote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    @Getter @Setter
    private Integer id;

    @Column(name = "vote_time", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Getter @Setter
    private LocalDateTime voteTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @Getter @Setter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @Getter @Setter
    private Restaurant restaurant;

    public Vote() {
        this.voteTime = LocalDateTime.now();
    }

    public Vote(Vote vote) {
        this.id = vote.id;
        this.voteTime = vote.voteTime;
        this.user = vote.user;
        this.restaurant = vote.restaurant;
    }

    public Vote(int id, LocalDateTime dateTime, User user,  Restaurant restaurant) {
        this.id = id;
        this.voteTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return id != null ? id.equals(vote.id) : vote.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
