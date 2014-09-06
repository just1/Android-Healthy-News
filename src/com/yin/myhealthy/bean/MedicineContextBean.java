package com.yin.myhealthy.bean;

public class MedicineContextBean {

	private String name;
	private String image;
	private String message; // ฯ๊ว้
	private String ANumber;
	private String PType;
	private String categoryName;
	private String tag;
	private String factory;
	private int count;
	private int fcount;
	private int rcount;
	private int id;
	private float price;

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getANumber() {
		return ANumber;
	}

	public void setANumber(String aNumber) {
		ANumber = aNumber;
	}

	public String getPType() {
		return PType;
	}

	public void setPType(String pType) {
		PType = pType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFcount() {
		return fcount;
	}

	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
