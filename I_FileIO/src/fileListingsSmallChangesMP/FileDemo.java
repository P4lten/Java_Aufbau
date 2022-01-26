package fileListingsSmallChangesMP;

public class FileDemo {

	public static void main(String[] args) {
		
		String dirName = args.length == 0 ? "." :args[0];
		// Michaela: richtige Klasse und eine Funktion aufrufen 
		FileStatistics stats = new FileStatistics(dirName);
		stats.showAll();
	}

}
