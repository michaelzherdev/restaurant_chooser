package com.mzherdev.restchooser.web.restaurant;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.web.ExceptionInfoHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by mzherdev on 07.06.2016.
 */
@RestController
@RequestMapping(AdminRestaurantRestController.REST_URL)
public class AdminRestaurantRestController extends AbstractRestaurantController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/restaurants";


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        super.update(restaurant, id);
    }

    @RequestMapping(value = "{id}/menus/{menuId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable("id") int id, @PathVariable("menuId") int menuId) {
        return super.getMenu(id, menuId);
    }

    @RequestMapping(value = "{id}/menus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getMenus(@PathVariable("id") int id) {
        return super.getMenus(id);
    }
}
