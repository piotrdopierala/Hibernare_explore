package pl.dopierala.FetchTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.FetchTypes.Entity.Company;
import pl.dopierala.FetchTypes.Entity.CompanyDetail;
import pl.dopierala.FetchTypes.Entity.Property;

import java.util.List;

public class FetchTypeApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Company.class);
        cfg.addAnnotatedClass(CompanyDetail.class);
        cfg.addAnnotatedClass(Property.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////

        int id=31;

        System.out.println("Pobieranie obiektu Company");
        Company company = currentSession.get(Company.class, id);
        System.out.println("Obiekt Company został pobrany");
        System.out.println(company);
        System.out.println("Nieruchomosci:");
        List<Property> properties = company.getProperties();
        properties.forEach(System.out::println);


        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
