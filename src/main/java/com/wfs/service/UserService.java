package com.wfs.service;

import java.util.List;
import java.util.Locale;

import com.wfs.form.UserForm;

public interface UserService {

	public List<String> getResult(UserForm userForm,Locale locale);
}
