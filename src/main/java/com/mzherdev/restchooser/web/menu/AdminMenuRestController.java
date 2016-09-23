package com.mzherdev.restchooser.web.menu;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.web.ExceptionInfoHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by mzherdev on 21.09.2016.
 */
@RestController
@RequestMapping(AdminMenuRestController.REST_URL)
public class AdminMenuRestController extends AbstractMenuController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/menus";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "{id}/restaurant/{restaurantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getForRestaurant(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return super.getForRestaurant(id, restaurantId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu, @PathVariable("id") int id) {
        super.update(menu, id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu created = super.create(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
