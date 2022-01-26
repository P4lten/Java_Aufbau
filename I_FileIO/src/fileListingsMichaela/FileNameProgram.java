package fileListingsMichaela;

public class FileNameProgram {

	public static void main(String[] args) {
		String dirName = args.length == 0 ? "." : args[0];
		
		FileNameStatistics stats = new FileNameStatistics(dirName);
		stats.showAll();
		
		
		stats.showFiles(".java");
		stats.showFiles(".txt");
		
		stats.showSizes();
		
	
	}

}
