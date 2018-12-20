package pl.dopierala.HQLtrain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HQLtrain.entity.Employee;

import java.util.List;

public class SelectApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Employee.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////
        String select = "SELECT firstName, lastName FROM Employee";
        String select2 = "SELECT e.firstName, e.lastName FROM Employee AS e";
        String select3 = "SELECT e.firstName, e.lastName FROM Employee e";

        Query<Object[]> query = currentSession.createQuery(select3, Object[].class);
        List<Object[]> resultList = query.getResultList();
        for (Object[] result : resultList) {
            System.out.println("first name:" + result[0] + " last name:" + result[1]);
        }
        /////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
