package pl.dopierala.HibernateAssociations.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.dopierala.HibernateAssociations.OneToOne.Entity.Company;
import pl.dopierala.HibernateAssociations.OneToOne.Entity.CompanyDetail;

import java.util.List;

public class OneToOneHQLapp {
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

        String selectAllCompaniesSqlQuery = "SELECT c FROM Company AS c INNER JOIN c.details AS d ON c.details=d WHERE d.residence='Italy'";
        String selectValuePolishCompaniesSqlQuery = "SELECT SUM(c.value) FROM Company AS c INNER JOIN c.details AS d WHERE d.residence='Poland'";
        String select = "SELECT c.name FROM CompanyDetail d JOIN d.company c WHERE d.employeeNumber<35000 ORDER BY c.value";

        Query query = currentSession.createQuery(select);
        List resultList = query.getResultList();
        //Object result = query.getSingleResult();

        resultList.forEach(System.out::println);
        //System.out.println(result);

        ////////////////////
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session Factory
        sessionFactory.close();
    }
}
