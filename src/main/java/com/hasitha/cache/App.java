package com.hasitha.cache;

import com.hasitha.cache.entity.Student;
import com.hasitha.cache.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try{
            System.out.println("Session1 start...");

            Session session1= HibernateUtil.getSessionFactory().openSession();
            //Student table should have a object with ID as 1.
            Student std1= session1.get(Student.class,1);
            session1.close();

            System.out.println("Session1 end...");


            System.out.println("Session2 start...");

            Session session2= HibernateUtil.getSessionFactory().openSession();
            /*Here again I am querying the same student object within another session.
            Since I have implemented second level cache,
            Hibernate will give the result without querying the database again.*/
            Student std2= session2.get(Student.class,1);
            session2.close();

            System.out.println("Session2 end...");

        }catch(Exception ex){
            System.out.println("ex "+ex.getMessage());

        }

    }
}
