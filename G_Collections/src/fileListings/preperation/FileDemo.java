package fileListings.preperation;

public class FileDemo {

	public static void main(String[] args) {
		
		String dirName = args.length == 0 ? "." :args[0];

		new FileNameStatistics(dirName);
	}

}
