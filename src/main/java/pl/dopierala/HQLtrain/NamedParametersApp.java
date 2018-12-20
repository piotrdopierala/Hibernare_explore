package pl.dopierala.HQLtrain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HQLtrain.entity.Employee;

import java.util.List;

public class NamedParametersApp {
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
        String employeeFirstName = "Steven";
        String employeeLastName = "King";

        String select = "FROM Employee WHERE firstName=:fname AND lastName=:lname";
        Query<Employee> query = currentSession.createQuery(select,Employee.class)
                .setParameter("lname", employeeLastName)
                .setParameter("fname", employeeFirstName);
        List<Employee> resultList = query.getResultList();

        for (Employee emp : resultList) {
            System.out.println(emp);
        }

        /////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
