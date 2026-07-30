package com.recipe_generator.api;

import java.util.HashMap;
import java.util.Map;

public class RecipeParams {
    private final Map<String, Object> params = new HashMap<>();

    public RecipeParams set(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Object get(String key) {
        return params.get(key);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(params.get(key));
    }

    public boolean has(String key) {
        return params.containsKey(key);
    }

    public Map<String, Object> getAll() {
        return new HashMap<>(params);
    }
}
