package com.hasitha.cache.util;

import com.hasitha.cache.entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_ex");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root@123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                //Below two annotations are used for enabling hibernate second level cache
                settings.put(Environment.USE_SECOND_LEVEL_CACHE,true);
                settings.put(Environment.CACHE_REGION_FACTORY,"org.hibernate.cache.ehcache.EhCacheRegionFactory");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("e "+e.getMessage());
            }
        }
        return sessionFactory;
    }
}
