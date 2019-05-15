package br.notebook.comparer.model;

//JavaBean - POJO

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notebook implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	long id;
	
	String name,
	       email;
	
	Date   dob;
	
	String color,
	       cardType,
	       gender,
	       period;
	
    float value;
	
	
	public Notebook(Long id, String name, String email, String dob, Float value, String color, String cardType, String gender,
			String period) {
		
		super();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setDob(dob);
		this.setValue(value);
		this.setColor(color);
		this.setCardType(cardType);
		this.setGender(gender);
		this.setPeriod(period);
	}

	public Notebook(String id, String name, String email, String dob, String value, String color, String cardType, String gender,
			String period) {
		
		super();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setDob(dob);
		this.setValue(Float.valueOf(value));
		this.setColor(color);
		this.setCardType(cardType);
		this.setGender(gender);
		this.setPeriod(period);
	}

	public Notebook() { //Default constructor
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setId(String id) {
		if (id != null)
		   this.id = Long.valueOf(id);
		else
		   this.id = 0;
	}	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
        
		//formato: 2018-09-20 mascara yyyy-MM-dd
		//formato: 20/09/2018 mascara dd/MM/yyyy
		//referencia: https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
		String dataPattern;
		
		if(dob.indexOf("-") > 0)
			dataPattern = "yyyy-MM-dd"; 
		else
			dataPattern = "dd/MM/yyyy"; 
		
		SimpleDateFormat formatter = new SimpleDateFormat(dataPattern);
		
		try { 
			this.dob = formatter.parse(dob);
		} catch (Exception e) {
			System.err.println("Ops! Problema com a data: " + dob);
			e.printStackTrace();
		} //try
		
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = Float.valueOf(value);
	}
	
	public void setValue(Float value) {
		this.value = value;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null && obj instanceof Notebook) {

            return (this.getId() == ((Notebook) obj).getId());

        }  else {
        	
            return false;

        } //if

	} //equals
	
	@Override
    public String toString() {
        return "Notebook ["
                + "  id=" + this.id 
        		+ ", name=" + this.name
                + ", email=" + this.email 
                + ", dob=" + this.dob 
                + ", color=" + this.color
                + ", cardType=" + this.cardType
                + ", gender=" + this.gender
                + ", period=" + this.period
                + ", value=" + this.value
                + "]";
    } //toString    
	
} //Notebook
