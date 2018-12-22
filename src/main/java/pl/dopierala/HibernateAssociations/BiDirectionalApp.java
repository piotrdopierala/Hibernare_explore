package pl.dopierala.HibernateAssociations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.dopierala.HibernateAssociations.Entity.Company;
import pl.dopierala.HibernateAssociations.Entity.CompanyDetail;

public class BiDirectionalApp {
    public static void main(String[] args) {
        //tworzenie obiektu configuration
        Configuration cfg = new Configuration();
        //wczytanie pliku koknfiguracyjnego
        cfg.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        cfg.addAnnotatedClass(Company.class);
        cfg.addAnnotatedClass(CompanyDetail.class);
        //stworzenie obiektu Session Factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        //pobieranie sesji
        Session currentSession = sessionFactory.getCurrentSession();
        //rozpoczecie tranzakcji
        currentSession.beginTransaction();
        //odczytanie pracownika
        ////////////////////

//        Company company = new Company("PZU",1000000);
//        CompanyDetail detailPZU = new CompanyDetail("Poland",1900);
//        detailPZU.setCompany(company);
//        company.setDetails(detailPZU);
////zapisanie
//        currentSession.persist(detailPZU);
//odczytanie (poprzez szczegoły)
        CompanyDetail detailRead = currentSession.get(CompanyDetail.class,12);
        System.out.println(detailRead.getCompany());
//usuniecie (przez company detail)
        currentSession.remove(detailRead);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
