package com.github.profile.searchapp.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
/**
 * Currently both service layer and dao layer are in this class only
 * Provides save user, search history and search history removal feature
 */
@Service
@Transactional
public class UserService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
    /**
     * Saves user to DB
     */
	public boolean saveUser(Map<String, String> userInfo) {
		entityManager.createNativeQuery("INSERT INTO USER_INFO(HANDLE, INFO) VALUES (?,?)")
			      .setParameter(1, userInfo.get("name"))
			      .setParameter(2, userInfo.get("info"))
			      .executeUpdate(); 
		return true;		
	}
	
    /**
     * Find user by handle name
     */
	public List<String> getSearch() {
		Query q = entityManager.createNativeQuery("SELECT HANDLE FROM USER_INFO");
		List<String> handles = q.getResultList();		 		
		return handles;
	}
	
    /**
     * Deletes search history based on handle name
     */
	public boolean removeSearch(String userInfo) {
		entityManager.createNativeQuery("DELETE FROM USER_INFO WHERE HANDLE = :VALUE")
	      .setParameter("VALUE", userInfo)
	      .executeUpdate(); 		
		return true;
	}
}
