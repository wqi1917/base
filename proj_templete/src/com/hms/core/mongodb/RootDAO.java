package com.hms.core.mongodb;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.googlecode.mjorm.ObjectIterator;
import com.googlecode.mjorm.query.DaoModifier;
import com.googlecode.mjorm.query.DaoQuery;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.ReadPreference;
import com.mongodb.WriteResult;

public class RootDAO<T> extends BaseRootDAO {

    public String collection;

    public T      t;

    public RootDAO(T t) {
        this.t = t;
    }

    public static void initialize() {
    }

    public T createObject(T object) {
        return (T) (mongoDao.createObject(collection, object));
    }

    public T[] createObjects(T[] objects) {
        return (T[]) (mongoDao.createObjects(collection, objects));
    }

    public int deleteObject(String id) {

        DaoQuery daoQuery = mongoDao.createQuery().setCollection(collection);
        WriteResult result = daoQuery.eq("_id", new ObjectId(id)).modify().delete();
        return result.getN();
    }

    public ObjectIterator<T> findAll() {
        return getDaoQuery().findObjects((Class<T>) (t.getClass()));

    }

    public DaoQuery getDaoQuery() {
        return mongoDao.createQuery().setCollection(collection)
            .setReadPreference(ReadPreference.nearest());
    }

    public ObjectIterator<T> findObjects(DaoQuery daoQuery) {
        return daoQuery.findObjects((Class<T>) (t.getClass()));
    }

    public T findObject(String uuid, String... fields) {
        DBObject dbObject = new BasicDBObject();
        for (String field : fields) {
            dbObject.put(field, 1);
        }

        DaoQuery daoQuery = getDaoQuery().eq("_id", new ObjectId(uuid));

        DBObject object = daoQuery.findObject(dbObject);
        return mapObject(object);
    }

    /**
     * Executes the query and returns objects of the given type.
     * @param clazz the type of objects to return
     * @return the iterator.
     */
    public ObjectIterator<T> findObjectsFields(DaoQuery daoQuery, String... fields) {
        DBCursor cursor = daoQuery.findObjects(fields);
        return new ObjectIterator<T>(cursor, daoQuery.getObjectMapper(), (Class<T>) (t.getClass()));
    }

    public long pushObject(String uuid, String field, Object object) {
        DBObject dbObject = unmapObject(object);
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().push(field, dbObject).update()
            .getN();
    }

    public long pushObjectAndInc(String uuid, String field, Object object, String incField) {
        DBObject dbObject = unmapObject(object);
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().push(field, dbObject)
            .inc(incField, 1).update().getN();
    }

    public long pushObject(String uuid, String field, String str) {

        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().push(field, str).update()
            .getN();
    }

    public long pushJavaObject(String uuid, String field, Object object) {
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().push(field, object).update()
            .getN();
    }

    public long pullObject(String uuid, String field, Object object) {
        DBObject dbObject = unmapObject(object);
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().pull(field, dbObject).update()
            .getN();
    }

    public long pullObjectAndInc(String uuid, String field, Object object, String incField) {
        DBObject dbObject = unmapObject(object);
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().pull(field, dbObject)
            .inc(incField, -1).update().getN();
    }

    public long pullObject(String uuid, String field, String str) {

        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().pull(field, str).update()
            .getN();
    }

    public long pullJavaObject(String uuid, String field, Object object) {
        return getDaoQuery().eq("_id", new ObjectId(uuid)).modify().pull(field, object).update()
            .getN();
    }

    /**
     * 更改某个字段的值
     * 
     * @param daoQuery
     * @param field
     * @param object
     * @return
     */
    public long setFieldValue(DaoQuery daoQuery, String field, Object object) {
        return daoQuery.modify().set(field, object).updateMulti().getN();
    }

    /**
     * 分页查询子文档, 
     * 
     * 逆向查询时存在最后一页返回pagesize条数据问题,需要根据子文档的大小进行转换（建议在业务层进行控制）
     * 
     * @param uuid
     * @param field
     * @param pageSize
     * @param pageNum
     * @param sort
     * @return
     */
    public T findObjectSlice(String uuid, String field, Integer pageSize, Integer pageNum,
                             Boolean sort) {

        if (pageNum <= 0 || pageSize <= 0)
            return null;

        Integer startPoint = 0;
        Integer size = pageSize;
        if (sort) {
            startPoint = (pageNum - 1) * pageSize;
        } else {
            // 逆向，最后一页或者超出页数会返回最后pageSize条数据
            startPoint = -(pageNum * pageSize);
        }
        Number[] rang = new Number[2];
        rang[0] = startPoint;
        rang[1] = size;

        DBObject sliceObject = new BasicDBObject(field, new BasicDBObject("$slice", rang));

        DBObject object = mongoDao.getCollection(collection).findOne(
            new BasicDBObject("_id", new ObjectId(uuid)), sliceObject, ReadPreference.nearest());

        return mapObject(object);
    }

    /**
     * 分页查询子文档, 逆向查询根据countField字段来判断子文档大小
     * 
     * @param uuid
     * @param field
     * @param pageSize
     * @param pageNum
     * @param sort
     * @param countField  文档记录子文档大小的字段
     * @return
     */
    public T findObjectSlice(String uuid, String field, Integer pageSize, Integer pageNum,
                             Boolean sort, String countField) {

        if (pageNum <= 0 || pageSize <= 0)
            return null;

        Integer startPoint = 0;
        Integer size = pageSize;
        if (sort) {
            startPoint = (pageNum - 1) * pageSize;
        } else {
            startPoint = (pageNum * pageSize);
            try {
                T t = findObject(uuid, countField);
                Field tfild = t.getClass().getDeclaredField(countField);
                tfild.setAccessible(true);
                Integer count = (Integer) tfild.get(t);

                // 页数异常，直接返回null
                if (count == null || ((pageNum - 1) * pageSize) > count) {
                    return null;
                }

                if (count != null && ((pageNum - 1) * pageSize) < count && count < startPoint) {
                    size = count - ((pageNum - 1) * pageSize);
                    startPoint = count;
                }
            } catch (Exception e) {
            }
            startPoint = -startPoint;
        }
        Number[] rang = new Number[2];
        rang[0] = startPoint;
        rang[1] = size;

        DBObject sliceObject = new BasicDBObject(field, new BasicDBObject("$slice", rang));

        DBObject object = mongoDao.getCollection(collection).findOne(
            new BasicDBObject("_id", new ObjectId(uuid)), sliceObject, ReadPreference.nearest());

        return mapObject(object);
    }

    public ObjectIterator<T> findObjectsByPage(DaoQuery daoQuery, Integer pageSize, Integer pageNum) {
        Integer firstDocument = null;
        Integer maxDocuments = null;
        if (pageSize > 0) {
            firstDocument = (pageNum - 1) * pageSize;
            maxDocuments = pageSize;
        }
        daoQuery = daoQuery.setFirstDocument(firstDocument).setMaxDocuments(maxDocuments);
        return findObjects(daoQuery);
    }

    public ObjectIterator<T> findObjectsFieldByPage(DaoQuery daoQuery, Integer pageSize,
                                                    Integer pageNum, String... fields) {
        Integer firstDocument = null;
        Integer maxDocuments = null;
        if (pageSize > 0) {
            firstDocument = (pageNum - 1) * pageSize;
            maxDocuments = pageSize;
        }
        daoQuery = daoQuery.setFirstDocument(firstDocument).setMaxDocuments(maxDocuments);
        return findObjectsFields(daoQuery, fields);
    }

    public T findObject(DaoQuery daoQuery) {
        return daoQuery.findObject((Class<T>) (t.getClass()));
    }

    public T findObjectByUuid(String id) {
        if (!(ObjectId.isValid(id)))
            return null;

        DaoQuery daoQuery = getDaoQuery().eq("_id", new ObjectId(id));
        return findObject(daoQuery);
    }

    public long countCollection() {
        return getDaoQuery().countObjects();
    }

    public long countQuery(DaoQuery daoQuery) {
        return daoQuery.countObjects();
    }

    public long countObjectSlice(String uuid, String field) {
        return getDaoQuery().eq("_id", new ObjectId(uuid)).distinct(field).size();
    }

    public int updateFields(DaoQuery daoQuery, Map<String, Object> fieldMap) {
        DaoModifier modifier = daoQuery.modify();
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            String colName = entry.getKey();
            Object colValue = entry.getValue();
            modifier.set(colName, colValue);
        }
        return modifier.update().getN();
    }

    public int updateObject(DaoQuery daoQuery, T object) {

        Field[] fields = object.getClass().getDeclaredFields();

        Map<String, Object> fieldMap = new HashMap<String, Object>();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                if (field.get(object) != null) {
                    fieldMap.put(field.getName(), field.get(object));
                } else {
                    field.setAccessible(false);
                    continue;
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            field.setAccessible(false);
        }
        return updateFields(daoQuery, fieldMap);
    }

    public int updateById(String uuid, T object) {

        DaoQuery daoQuery = getDaoQuery().eq("_id", new ObjectId(uuid));

        return updateObject(daoQuery, object);
    }

    public DBObject unmapObject(Object object) {
        return mongoDao.createQuery().getObjectMapper().unmap(object);
    }

    public T mapObject(DBObject object) {
        return mongoDao.createQuery().getObjectMapper().map(object, (Class<T>) (t.getClass()));
    }

    public Object unmapValue(Object object) {
        return mongoDao.createQuery().getObjectMapper().unmapValue(object);
    }

    //For Jersey util
    public T mapToObject(Map<String, String> fieldMap, T object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            try {
                if (fieldMap.get(field.getName()) != null) {

                    if (field.getType() == Integer.class)
                        field.set(object, Integer.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == Long.class)
                        field.set(object, Long.valueOf(fieldMap.get(field.getName())));

                    if (field.getType() == String.class)
                        field.set(object, fieldMap.get(field.getName()));

                    if (field.getType() == Date.class) {

                        field.set(object, string2Date(fieldMap.get(field.getName())));
                    }

                } else {
                    field.setAccessible(false);
                    continue;
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            field.setAccessible(false);
        }
        return object;
    }

    public Date string2Date(String str) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sd.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
