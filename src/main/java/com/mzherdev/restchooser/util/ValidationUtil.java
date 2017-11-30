package com.mzherdev.restchooser.util;

import com.mzherdev.restchooser.model.AbstractEntity;
import com.mzherdev.restchooser.util.exception.NotFoundException;

public class ValidationUtil {
    private ValidationUtil() {}

    public static void check(boolean found, int id) {
        check(found, "id=" + id);
    }

    public static <T> T check(T object, int id) {
        return check(object, "id=" + id);
    }

    public static <T> T check(T object, String msg) {
        check(object != null, msg);
        return object;
    }

    public static void check(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdConsistent(AbstractEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
