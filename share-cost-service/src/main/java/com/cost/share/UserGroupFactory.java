package com.cost.share;

import java.util.ServiceLoader;

import com.cost.share.service.UserGroupService;

/**
 * @author Vineet Sahu
 *
 */
public class UserGroupFactory {

	public static UserGroupService getUserGroupImpl() {
		 ServiceLoader<UserGroupService> serviceLoader =
	              ServiceLoader.load(UserGroupService.class);
		 return (UserGroupService) serviceLoader.iterator().next();
	}
}
