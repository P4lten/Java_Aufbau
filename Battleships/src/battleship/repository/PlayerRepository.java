package battleship.repository;

import java.util.List;
import java.util.Optional;




public interface PlayerRepository {

	List<Player> selectAllFromPlayers() throws BattleshipsException;
	
	List<Player> selectAllFromGames() throws BattleshipsException;
	
	List<Player> selectPlayersFromGames(String name) throws BattleshipsException;
	
	Player selectByName(String name) throws BattleshipsException;
	
	void insertPlayer(Player player) throws BattleshipsException;
	
	void updatePlayer (Player player) throws BattleshipsException;
	
	void deletePlayer (Player player) throws BattleshipsException;

	void updatePlayerRank(int rank, String name) throws BattleshipsException;

	void insertPlayerInGames(Player player) throws BattleshipsException;
	
	void updatePlayerInGames (Player player) throws BattleshipsException;
	
	void deletePlayerInGames (Player player) throws BattleshipsException;
	
	void updatePlayerGamesTime(long time, Player player) throws BattleshipsException;

}
