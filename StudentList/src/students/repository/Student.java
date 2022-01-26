package students.repository;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "student", propOrder = {"id", "name", "birthDate", "gender", "areaCode", "city",
		"language", "html", "xml", "fxml", "comment" })
public class Student {

	private int id;
	private String name, city, language, comment;
	private int areaCode;
	private boolean xml, html, fxml;
	private LocalDate birthDate;
	private Gender gender;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isXml() {
		return xml;
	}

	public void setXml(boolean xml) {
		this.xml = xml;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public boolean isFxml() {
		return fxml;
	}

	public void setFxml(boolean fxml) {
		this.fxml = fxml;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(getId());
		builder.append(", name=");
		builder.append(getName());
		builder.append(", areaCode=");
		builder.append(getAreaCode());
		builder.append(", city=");
		builder.append(getCity());
		builder.append("\ngender=");
		builder.append(getGender());
		builder.append(", birthDate=");
		builder.append(getBirthDate());
		builder.append("\nxml=");
		builder.append(isXml());
		builder.append(", html=");
		builder.append(isHtml());
		builder.append(", fxml=");
		builder.append(isFxml());
		builder.append(", language=");
		builder.append(getLanguage());
		builder.append("\ncomment=");
		builder.append(getComment());
		builder.append("]");
		return builder.toString();
	}
//	@Override
//	public String toString() {
//		return String.format("%s (%s %s)", getName(), getAreaCode(), getCity());
//	}

}
