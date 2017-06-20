package com.cuc2017.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.stormpath.sdk.account.Account;

@Service
public class HelloService {

	public static final String MY_GROUP = "GROUP_HREF_HERE";

	/**
	 * Only users who have a Custom Data entry in their Stormpath Account or
	 * Group containing something like
	 * <code>"springSecurityPermissions":["say:*"]</code> or
	 * <code>"springSecurityPermissions":["say:hello"]</code> will be allowed to
	 * execute this method.
	 */
	@PreAuthorize("hasAuthority('" + MY_GROUP + "') and hasPermission('say', 'hello')")
	public String sayHello(Account account) {
		return "Hello, " + account.getGivenName()
				+ ". You have the required permissions to access this restricted resource.";
	}
}
