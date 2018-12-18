package pl.dopierala.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.hibernatedemo1.entity.Employee;

public class PrimaryKeyApp {
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
        //storzenie 3 obiektow
        Employee emp = new Employee();
        emp.setFirstName("Andrzej");
        emp.setLastName("Sienkiewicz");
        emp.setSalary(10000);

        Employee emp2 = new Employee();
        emp2.setFirstName("Krzysztof");
        emp2.setLastName("Nowak");
        emp2.setSalary(10000);

        Employee emp3 = new Employee();
        emp3.setFirstName("Janina");
        emp3.setLastName("Kowalska");
        emp3.setSalary(10000);
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //zapisanie 3 pracownikow
        currentSession.save(emp);
        currentSession.save(emp2);
        currentSession.save(emp3);
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
