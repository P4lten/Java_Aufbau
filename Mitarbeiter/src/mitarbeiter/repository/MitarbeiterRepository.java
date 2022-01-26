package mitarbeiter.repository;

import java.util.List;


public interface MitarbeiterRepository {

	List<Mitarbeiter> selectAll() throws MitarbeiterRepositoryException;

	/**
	 * ein Mitarbeiter per ID laden
	 * 
	 * @param id die ID des Students
	 * @return
	 */
	Mitarbeiter selectById(int id) throws MitarbeiterRepositoryException;

	int insertEmployee(Mitarbeiter student) throws MitarbeiterRepositoryException;

	void updateEmployee(Mitarbeiter student) throws MitarbeiterRepositoryException;

	void deleteEmployee(int id) throws MitarbeiterRepositoryException;

}

