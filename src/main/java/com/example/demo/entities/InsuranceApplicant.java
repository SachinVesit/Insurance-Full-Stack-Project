package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "insurance_application")
public class InsuranceApplicant extends AbstractEntity {

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "premium_amount")
	private double premiumAmount;

	@Column(name = "sum_assured_amount")
	private double sumAssuredAmount;

	private int age;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "no_of_years")
	private int noOfYears;
	

	public InsuranceApplicant(String fullName, String productName, double premiumAmount, double sumAssuredAmount,
			int age, String dateOfBirth, int noOfYears) {
		super();
		this.fullName = fullName;
		this.productName = productName;
		this.premiumAmount = premiumAmount;
		this.sumAssuredAmount = sumAssuredAmount;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.noOfYears = noOfYears;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public double getSumAssuredAmount() {
		return sumAssuredAmount;
	}

	public void setSumAssuredAmount(double sumAssuredAmount) {
		this.sumAssuredAmount = sumAssuredAmount;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}
}
