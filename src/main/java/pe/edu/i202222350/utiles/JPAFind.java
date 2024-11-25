package pe.edu.i202222350.utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import pe.edu.i202222350.Main;
import pe.edu.i202222350.domain.City;
import pe.edu.i202222350.domain.Country;

import java.util.List;

public class JPAFind {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();

        try {
            Country country = em.find(Country.class, "PE");

            if (country != null) {
                String jpql = "SELECT c FROM City c WHERE c.country.code = :countryCode AND c.Population > 700000";
                TypedQuery<City> query = em.createQuery(jpql, City.class);
                query.setParameter("countryCode", "PER");

                List<City> cities = query.getResultList();

                cities.stream()
                        .map(City::getName)
                        .forEach(System.out::println);
            } else {
                System.out.println("No se encontró el país con código PER.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }



    }

