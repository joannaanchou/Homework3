package model;

import java.io.Serializable;

public class Porder implements Serializable{

	private Integer id;
	private String name;
	private int daily;
	private int season;
	private int annual;
	private String date;
	
	public Porder() {
		super();
	}
	
	public Porder(String name, int daily, int season, int annual,String date) {
		super();
		this.name = name;
		this.daily = daily;
		this.season = season;
		this.annual = annual;
		this.date=date;

	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDaily() {
		return daily;
	}

	public void setDaily(int daily) {
		this.daily = daily;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getAnnual() {
		return annual;
	}

	public void setAnnual(int annual) {
		this.annual = annual;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	
	public String show() {
		int dailyTotal = daily * 299;
		int seasonTotal = season * 1899;
		int annualTotal = annual * 6999;
		int finalAmount = dailyTotal + seasonTotal + annualTotal;

		
		return "【訂單內容】\n" +
	       "單日票：" + daily + "張x$299=$" + dailyTotal +
	       "\n季票：" + season + "張x$1899=$" + seasonTotal +
	       "\n年票：" + annual + "張x$6999=$" + annualTotal +
	       "\n_______________________________________" +
	       "\n總金額：$" + finalAmount;
	}
	
}
