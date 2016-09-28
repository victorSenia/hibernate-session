package com.leo.test.hibernate.session.util;

import com.leo.test.hibernate.session.controller.HomeController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
public class HibernateUtil {
    private static final SessionFactory FACTORY;

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class.getSimpleName());

    static {
        try {
            FACTORY = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            LOGGER.log(Level.SEVERE, "Session factory can't be created", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        int delete = 114;
        HomeController controller = new HomeController();
        System.out.println(controller.get(delete));
        System.out.println(controller.edit(delete, "{\"browser\":\"testhhgj\", \"cssGrade\":\"test\", \"engine\":\"test\", \"engineVersion\":\"-\", \"platform\":\"-\"}"));
        System.out.println(controller.get(delete));
        controller.delete(delete);
        System.out.println(controller.create("{\"browser\":\"test\", \"cssGrade\":\"test\", \"engine\":\"test\", \"engineVersion\":\"-\", \"platform\":\"-\"}"));
        System.out.println(controller.list());
    }

    public static Session session() {
        return FACTORY.openSession();
    }
}
