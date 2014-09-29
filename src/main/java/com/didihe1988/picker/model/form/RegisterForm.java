package com.didihe1988.picker.model.form;

public class RegisterForm {
	private String email;

	private String name;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterForm() {

	}

	public RegisterForm(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

}
