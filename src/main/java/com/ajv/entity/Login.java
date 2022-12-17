package com.ajv.entity;

import lombok.*;

@NoArgsConstructor
@ToString
public class Login {
	private String account;
	private String password;
	private String type;

	public Login(String account, String password, String type) {
		this.account = account;
		this.password = password;
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
