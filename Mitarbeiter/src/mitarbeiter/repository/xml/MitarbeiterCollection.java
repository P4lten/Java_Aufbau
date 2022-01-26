package mitarbeiter.repository.xml;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import mitarbeiter.repository.Mitarbeiter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employeeRepository")

public class MitarbeiterCollection {

	@XmlElementWrapper(name = "employees")
	@XmlElement(name = "employee")
	private List<Mitarbeiter> employee = new ArrayList<>();

	public List<Mitarbeiter> getEmployees() {
		return employee;
	}

	private int nextEmployeeId = 1;

	public int getNextEmplpyee() {
		return nextEmployeeId;
	}

	public int incrementNextEmployee() {
		return nextEmployeeId++;
	}
}
