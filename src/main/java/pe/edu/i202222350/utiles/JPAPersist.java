package pe.edu.i202222350.utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.i202222350.domain.Continent;

import pe.edu.i202222350.domain.Country;

public class JPAPersist {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world");
        EntityManager em = emf.createEntityManager();

        // Registrar los países
        try {
            em.getTransaction().begin();

            // Define países
            Country gotica = new Country(
                    "AB", "Gotica", Continent.Asia, "Innovation", 790000.0, 2024, 9000000,
                    85.0, 15000.0, 89000.0, "Gotica Local", "Republic", "Andy Sihuinta", 1, "SP1");

            Country azafran = new Country(
                    "AC", "Azafran", Continent.Antarctica, "Technology", 650000.0, 2024, 6000000,
                    90.0, 12000.0, 11300.0, "Azafran Local", "Republic", "Alejandro Suarez", 2, "BI2");

            Country wakanda = new Country(
                    "AD", "Wakanda", Continent.Asia, "Education", 800000.0, 2024, 7000000,
                    80.0, 13100.0, 12300.0, "Wakanda Local", "Republic", "Alexeyr Sihuinta", 3, "CT3");

            // Persiste países
            em.persist(gotica);
            em.persist(azafran);
            em.persist(wakanda);

            em.getTransaction().commit();

            System.out.println("Países persistidos con éxito.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

    }
}
