package com.mzherdev.restchooser.web.dish;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.web.ExceptionInfoHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mzherdev on 21.09.2016.
 */
@RestController
@RequestMapping(AdminDishRestController.REST_URL)
public class AdminDishRestController extends AbstractDishController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/dishes";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}/menus/{menuId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getForMenu(@PathVariable("id") int id, @PathVariable("menuId") int menuId) {
        return super.getForMenu(id, menuId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        super.update(dish, id);
    }

}
