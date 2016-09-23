package com.mzherdev.restchooser.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzherdev.restchooser.model.Dish;

import java.io.IOException;
import java.util.List;

/**
 * Created by mzherdev on 22.09.16.
 */
public class DishDeserializer extends JsonDeserializer<List<Dish>> {
    @Override
    public List<Dish> deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);
        List<Dish> diets = mapper.convertValue(node.findValues("diet"), new TypeReference<List<Dish>>() {});
        return diets;
    }
}
