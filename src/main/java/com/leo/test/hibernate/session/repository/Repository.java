package com.leo.test.hibernate.session.repository;

import com.leo.test.hibernate.session.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public abstract class Repository<T> {

    public Repository(Class<T> c) {
        this.c = c;
    }

    private final Class<T> c;

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
}
