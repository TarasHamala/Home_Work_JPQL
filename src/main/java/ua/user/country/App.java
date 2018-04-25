package ua.user.country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import ua.user.country.entity.City;
import ua.user.country.entity.Country;
import ua.user.country.entity.User;

public class App 
{
	static Random random = new Random();
	static String PROJECT_PATH = System.getProperty("user.dir");
	static String SEPARATOR = System.getProperty("file.separator");
	static String ROOT_PATH = PROJECT_PATH + SEPARATOR + "src" + SEPARATOR + "main" + SEPARATOR + "java";
    public static void main( String[] args ){
      
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
    	EntityManager em = entityManagerFactory.createEntityManager();
    	em.getTransaction().begin();
    	
//    	writeFields(em);
    	
//      em.createQuery("SELECT u FROM User u", User.class).getResultList().forEach(System.out::println);
    	
//    	em.createQuery("SELECT c FROM Country c ORDER BY c.id DESC", Country.class).getResultList().forEach(System.out::println);
    	
//    	em.createQuery("SELECT c FROM City c ORDER BY c.name ASC", City.class).getResultList().forEach(System.out::println);  
    	
//   	em.createQuery("SELECT u FROM User u ORDER BY u.fullName DESC", User.class).getResultList().forEach(System.out::println);

/*    	em.createQuery("SELECT c FROM Country c WHERE LOWER(c.name) LIKE LOWER(:like_param)", Country.class)
    	                  .setParameter("like_param", "a%").getResultList().forEach(System.out::println);*/

/*    	em.createQuery("SELECT c FROM City c WHERE LOWER(c.name) Like LOWER(:like1) OR LOWER(c.name) Like LOWER(:like2)", City.class)
							.setParameter("like1", "%n_")
							.setParameter("like2", "%r_")
							.getResultList().forEach(System.out::println);*/
    	
//     	System.out.println(em.createQuery("SELECT min(u.age) From User u", Integer.class).getSingleResult());
    	
//      System.out.println(em.createQuery("SELECT avg(u.age) FROM User u", Double.class).getSingleResult()); 
    	
//      em.createQuery("SELECT u FROM User u JOIN FETCH u.city uc", User.class).getResultList().forEach(System.out::println);
    	 
    	em.createQuery("SELECT u FROM User u JOIN u.city uc WHERE u.id NOT IN (:ids)", User.class)
		.setParameter("ids", Arrays.asList(2, 5, 9, 12, 13, 16))
		.getResultList().forEach(System.out::println);
  
	    	
//        em.createQuery("SELECT u FROM User u JOIN FETCH u.city uc JOIN FETCH uc.country ucc", User.class)
//                         .getResultList().forEach(System.out::println);
    	 
    	em.getTransaction().commit();
    	entityManagerFactory.close();
    	em.close();
    	
    }
    
    public static void writeFields(EntityManager em) {
    	List<String> countries = readFile("countries");
		List<String> cities = readFile("cities");
		List<String> users = readFile("users");
		
		for(int i = 0; i < countries.size(); i++) {
			Country country = new Country();
			country.setName(countries.get(i).trim());
			em.persist(country);
		}
		
		List<Country> countriesQ = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
		for(int i = 0; i < countries.size(); i++) {
			City city = new City();
			city.setName(cities.get(i).trim());
			int countryIndex = random.nextInt(countriesQ.size());
			city.setCountry(countriesQ.get(countryIndex));
			em.persist(city);
		}
		
		List<City> citiesQ = em.createQuery("SELECT c FROM City c", City.class).getResultList();
		for(int i = 10; i < users.size(); i++) {
			User user = new User();
	
			user.setFullName(users.get(i).trim());
			user.setAge(i + 4);
			
			int countryId = random.nextInt(countriesQ.size());
			user.setCountry(countriesQ.get(countryId));
			
			int cityId = random.nextInt(citiesQ.size());
			user.setCity(citiesQ.get(cityId));
			
			em.persist(user);
		}
	}
	
	static List<String> readFile(String fileName) {
		List<String> fileData = new ArrayList<String>();
		String filePath = ROOT_PATH + SEPARATOR + fileName + ".txt";

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			String line;
			while ((line = br.readLine()) != null) {
				fileData.add(line);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileData;
	   }
	}

