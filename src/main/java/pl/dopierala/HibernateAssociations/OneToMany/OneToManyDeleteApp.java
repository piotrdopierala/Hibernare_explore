package pl.dopierala.HibernateAssociations.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Property;

import java.util.List;

public class OneToManyDeleteApp {
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
        //get Orlen's properties

        List<Property> orlensProperties= currentSession.createQuery("SELECT p FROM Company c JOIN Property p ON c.id_company=p.company  WHERE c.name='Orlen'",Property.class).getResultList();
        Property orlenWarsawProperty = orlensProperties.stream().filter(p->p.getCity().equals("Warsaw")).findFirst().orElse(null);
        currentSession.delete(orlenWarsawProperty);


        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
