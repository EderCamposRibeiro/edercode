package br.com.eder.store.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.eder.store.dao.SecurityDao;
import br.com.eder.store.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	
	@Inject
	private  SecurityDao securityDao;
	
	private SystemUser systemUser;
	
	public SystemUser get() {
		return systemUser;
	}
	
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public String logout() {
		request.getSession().invalidate();
		return "/books/list.xhtml?faces-redirect=true";
	}
	
	@PostConstruct
	public void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String login = principal.getName();
			systemUser = securityDao.findByLogin(login);
		}
	}
}
