package com.admire.live.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 提供查询工具方法
 * Created by ljp on 17/11/13.
 */
@Service
public class EntityOperationTemplate {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * 根据ID查询实体类
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> entityClass, Serializable id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * 根据jpql语句进行查询
     * @param sqlText
     * @param params
     * @return 返回实体需要根据需要转换
     */
    public List findBySql(String sqlText, Map<Integer, Object> params) {
        Query query = entityManager.createQuery(sqlText);
        params.keySet().stream().forEach(key->{
            query.setParameter(key, params.get(key));
        });

        return query.getResultList();
    }

    /**
     * 批量保存新Entity
     * 同一事务
     * @param entities
     */

    @Transactional
    public void saveEntities(List entities) {
        entities.stream().forEach(entity -> entityManager.persist(entity));
        entityManager.flush();
        entityManager.clear();
    }

    /**
     * 批量更新Entity
     * 同一事务
     * @param entities
     */

    @Transactional
    public void updateEntities(List entities) {
        entities.stream().forEach(entity -> entityManager.merge(entity));
        entityManager.flush();
        entityManager.clear();
    }

}