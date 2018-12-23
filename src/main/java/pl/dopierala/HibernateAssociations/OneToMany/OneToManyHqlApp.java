package pl.dopierala.HibernateAssociations.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Company;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.CompanyDetail;
import pl.dopierala.HibernateAssociations.OneToMany.Entity.Property;

import java.util.List;

public class OneToManyHqlApp {
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
        //names of all companies that have properties in Sevilla

//        String companiesHavePropInSevilla = "SELECT c.name FROM Property p JOIN p.company c WHERE p.city='Sevilla'";
//        List<String> resultList = currentSession.createQuery(companiesHavePropInSevilla, String.class).getResultList();
//        System.out.println(resultList);

        //nazwy firm ktore posiadaja nieruchomisci w Barcelonie i siedziba glowna jest w szwajcarii
//
//        String query = "SELECT c.name FROM Property p JOIN p.company c JOIN c.details d  WHERE p.city='Barcelona' AND d.residence='Switzerland'";
//        List<String> resultList2 = currentSession.createQuery(query,String.class).getResultList();
//        System.out.println(resultList2);

        //nazwy firm posiadaja wiecej niz 4 nieruchomisci
        //String query2 = "SELECT c.name FROM Property p JOIN p.company c GROUP BY c HAVING count(1)>4";
        String query2 = "SELECT c.name FROM Company c WHERE size(c.properties)>4";
        List<String> resultList3 = currentSession.createQuery(query2,String.class).getResultList();
        System.out.println(resultList3);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
