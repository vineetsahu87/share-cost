package com.cost.share.dao;

import com.cost.share.model.User;

/**
 * Data Access Object for User Collection
 * 
 * @author Vineet Sahu
 *
 */
public interface UserDao {

	/**
	 * Adds a new user to the User Collection
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * Retrieves the user from the User Collection given the email address.
	 * 
	 * @param emailAddress
	 * @return User
	 */
	public User getUser(String emailAddress);

	/**
	 * Retrieves the user from the User Collection given the userId.
	 * 
	 * @param emailAddress
	 * @return User
	 */
	public User getUserById(String userId);
}
