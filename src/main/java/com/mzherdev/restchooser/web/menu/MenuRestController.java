package com.mzherdev.restchooser.web.menu;

import com.mzherdev.restchooser.model.Menu;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.mzherdev.restchooser.web.restaurant.RestaurantRestController.RESTAURANT_URL;

@RestController
public class MenuRestController extends AbstractMenuController {
    public static final String MENU_URL = "/menus";

    @RequestMapping(path = RESTAURANT_URL + "/{restaurantId}" +  MENU_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllForRestaurant(@PathVariable("restaurantId") int restaurantId) {
        return super.getAllForRestaurant(restaurantId);
    }

    @RequestMapping(path = MENU_URL + "/actual", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getActuals() {
        return super.getActual();
    }

    @RequestMapping(path = RESTAURANT_URL + "/{restaurantId}" +  MENU_URL + "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("restaurantId") int restaurantId,
                    @PathVariable("id") int id) {
        return super.getOneForRestaurant(id, restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = MENU_URL + "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu,
                       @PathVariable("id") int id) throws ReflectiveOperationException {
        super.update(menu, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = RESTAURANT_URL + "/{restaurantId}" +  MENU_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId) {
        Menu created = super.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MENU_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
