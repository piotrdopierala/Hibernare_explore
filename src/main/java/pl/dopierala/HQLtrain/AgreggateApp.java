package pl.dopierala.HQLtrain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HQLtrain.entity.Employee;

import java.util.List;

public class AgreggateApp {
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

        String avgQuery = "SELECT AVG(salary) FROM Employee ";
        String sumQuery = "SELECT SUM(salary) FROM Employee ";
        String minQuery = "SELECT MIN(salary) FROM Employee ";
        String maxQuery = "SELECT MAX(salary) FROM Employee ";
        String countQuery = "SELECT count(1) FROM Employee ";

        Query query = currentSession.createQuery(countQuery);
        Object singleResult = query.getSingleResult();
        System.out.println("Zapytanie zwrocilo: ");
        System.out.println(singleResult);

        /////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
