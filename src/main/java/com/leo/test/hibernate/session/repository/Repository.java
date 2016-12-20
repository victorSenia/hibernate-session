package com.leo.test.hibernate.session.repository;

import com.leo.test.hibernate.session.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public abstract class Repository<T> {

    private final Class<T> c;

    public Repository(Class<T> c) {
        this.c = c;
    }

    private Session session() {
        return HibernateUtil.session();
    }

    public Iterable<T> list() {
        try (Session session = session()) {
            CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(c);
            query.select(query.from(c));
            return session.createQuery(query).list();
        }
    }

    public T get(Serializable id) {
        try (Session session = session()) {
            return (T) session.get(c, id);
        }
    }

    public T edit(T object) {
        try (Session session = session()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            object = (T) session.merge(object);
            session.flush();
            return object;
        }
    }

    public void delete(T object) {
        try (Session session = session()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(object);
            session.flush();
        }
    }

    protected Iterable<T> custom(Customisation<T> customisation) {
        try (Session session = session()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(c);
            Root<T> root = query.from(c);
            customisation.custom(criteriaBuilder, query, root);
            return session.createQuery(query).list();
        }
    }

    protected interface Customisation<T> {
        void custom(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> query, Root<T> root);
    }
}
