package com.mzherdev.restchooser.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    private JpaUtil() {}

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
//        sf.evict(User.class);
//        sf.getCache().evictEntity(User.class, BaseEntity.START_SEQ);
//        sf.getCache().evictEntityRegion(User.class);
        sf.getCache().evictQueryRegions();
        sf.getCache().evictDefaultQueryRegion();
        sf.getCache().evictCollectionRegions();
        sf.getCache().evictEntityRegions();
    }


/**
    Check if a new entity has a null fields and do not update it
 */
    public static <E> E updateEntity(E oldEntity, E newEntity) throws IllegalAccessException, NoSuchFieldException {

        Field[] newEntityFields = newEntity.getClass().getDeclaredFields();
        Map<String, Object> map = mapFields(newEntityFields, newEntity);

        Class oldEntityClass = oldEntity.getClass();
        Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

        for (Field field : oldEntityFields){
            if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                Object o = map.get(field.getName());
                if (o != null) {
                    Field f = oldEntityClass.getDeclaredField(field.getName());
                    f.setAccessible(true);
                    f.set(oldEntity, o);
                }
            }
        }

        return oldEntity;
    }

    private static Map<String, Object> mapFields(Field[] fields, Object obj) throws IllegalAccessException {
        Map<String,Object> map = new HashMap<>();
        for (Field field: fields){
            if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                Object retrievedObject = field.get(obj);
                if (retrievedObject != null) {
                    map.put(field.getName(), field.get(obj));
                }
            }
        }
        return map;
    }
}
