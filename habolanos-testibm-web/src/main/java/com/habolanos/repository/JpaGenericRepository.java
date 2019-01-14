package com.habolanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Query;


/**
 *
 * @author <a href="mailto:haroldadrian@gmail.com">Harold Adrian Bolaños Rodriguez</a>
 * @project habolanos-testing-web
 * @class JpaGenericRepository
 *
 */
@NoRepositoryBean
public interface JpaGenericRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID> {
    void update(T entity);

    void deleteByProperty(String tableName, String propertyName, Object value);

    List<T> findPage(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults);

    List<T> findByCriteria(String whereCondition);

    List<T> findByProperty(String propertyName, Object value);

    int getMaxResults();

    void setMaxResults(int maxResults);

    Query createQuery(String queryString);

    void prepareQuery(Query queryObject);
}
