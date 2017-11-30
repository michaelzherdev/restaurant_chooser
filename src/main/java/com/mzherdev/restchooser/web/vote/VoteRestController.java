package com.mzherdev.restchooser.web.vote;

import com.mzherdev.restchooser.AuthorizedUser;
import com.mzherdev.restchooser.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.mzherdev.restchooser.web.restaurant.RestaurantRestController.RESTAURANT_URL;

@RestController
public class VoteRestController extends AbstractVoteController {
    public static final String VOTE_URL = "/votes";

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = VOTE_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        return super.getAll();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = VOTE_URL + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = RESTAURANT_URL + "/{restaurantId}/votes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllForRestaurant(@PathVariable("restaurantId") int restaurantId) {
        return super.getAllForRestaurant(restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = RESTAURANT_URL + "/{restaurantId}" + VOTE_URL + "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) throws ReflectiveOperationException {
        super.update(vote, id, AuthorizedUser.id(), restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = RESTAURANT_URL +  "/{restaurantId}" + VOTE_URL, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createVote(@PathVariable("restaurantId") Integer restaurantId) {
        Vote created = super.create(new Vote(), AuthorizedUser.id(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTE_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
