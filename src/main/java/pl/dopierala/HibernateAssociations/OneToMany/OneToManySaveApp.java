package pl.dopierala.HibernateAssociations.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Property;

public class OneToManySaveApp {
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

        Company company = currentSession.createQuery("FROM Company WHERE name='Orlen'",Company.class).getSingleResult();
        Property property = new Property("Gdansk",290);
        company.addProperty(property);

        currentSession.persist(company);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
