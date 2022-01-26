package fileListingsMichaela;

public class FileStatisticsProgram {

	public static void main(String[] args) {
		String dirName = args.length == 0 ? "." : args[0];
		
		FileStatistics stats = new FileStatistics(dirName);
		stats.showAll();
		
		
		stats.showFiles(".java");
		stats.showFiles(".txt");
		
		stats.showFileCount();
		
		stats.showNewestOldest();
		
		stats.showNewestOldest(".java");
		stats.showNewestOldest(".txt");
		
		stats.showExitWithMostFiles();
		stats.showExitWithMostFiles2();
		
	}

}
