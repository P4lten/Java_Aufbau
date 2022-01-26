package fileListings.preperation;

import java.time.Instant;

public class FileData {
	
	//TODO: Attribute für name, path, size und lastModified
	String name, path;
	long size;
	Instant lastModified;
	
	//TODO: passender Konstruktor
	
	public FileData() {
		
	}
	
	public FileData(String name, String path, long size, Instant lastModified) {
		super();
		this.name = name;
		this.path = path;
		this.size = size;
		this.lastModified = lastModified;
	}
	
	
	//TODO: getter für alle Attribute
	
	public String getName() {
		return name;
	}
	
	
	public String getPath() {
		return path;
	}
	
	
	public long getSize() {
		return size;
	}
	
	
	public Instant getLastModified() {
		//TODO: das Attribute zurückliefern
		return lastModified;
	}

	
	//TODO toString

	public String toString() {
		return "\nName: " + getName() + "\nPath: " + getPath() + "\nSize: " + getSize() + "\nLastModified: " + getLastModified();
	}
	
}
