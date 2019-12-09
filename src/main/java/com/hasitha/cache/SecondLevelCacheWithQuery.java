package com.hasitha.cache;

import com.hasitha.cache.entity.Student;
import com.hasitha.cache.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


/**
 * Hello world!
 *
 */
public class SecondLevelCacheWithQuery
{
    public static void main( String[] args )
    {

        try{

            System.out.println("Session1 start...");

            Session session1= HibernateUtil.getSessionFactory().openSession();
            //Student table should have a object with ID as 1.
            Query q1=session1.createQuery("from Student where id=1");
            //Enable cache for q1
            q1.setCacheable(true);
            Student std1=(Student)q1.uniqueResult();
            session1.close();

            System.out.println("Session1 end...");


            System.out.println("Session2 start...");

            Session session2= HibernateUtil.getSessionFactory().openSession();
            /*Here again I am querying the same student object within another session.
            Since I have implemented second level cache with query,
            Hibernate will give the result without querying the database again.*/
            Query q2=session2.createQuery("from Student where id=1");
            //Using cached query.
            q2.setCacheable(true);
            Student std2=(Student)q2.uniqueResult();
            session2.close();

            System.out.println("Session2 end...");

        }catch(Exception ex){
            System.out.println("ex "+ex.getMessage());

        }

    }
}
