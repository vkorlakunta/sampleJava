

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String file = "D:\\testProj\\docs\\files.txt";
    	String errorFileDir = "D:\\testProj\\docs";
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);
        
        System.out.println("lines : "+ lines.get(0));
        System.out.println("lines : "+ lines.get(1));
        System.out.println("lines : "+ lines.get(2));
        
        String dirPath = "D:\\TimeSheets";
        List<File> filesList = listFilesUsingFilesList(dirPath);
        System.out.println("filesList : "+ filesList.toString());
        for(String line : lines) {
        	if (filesList.stream().anyMatch(s -> s.toString().contains(line))) {
                System.out.println(line + " Found");
            } else {
            	System.out.println(line + " Not Found");
            	
            	File errorFile = new File(errorFileDir + "\\errorFile.txt");
            	errorFile.createNewFile();
            	FileWriter writer = new FileWriter(errorFile, true);
            	writer.write(line);
            	writer.close();
            }
        }
        
    }
    
    public static List<File> listFilesUsingFilesList(String dir) throws IOException {
    	 List<File> files = Files.list(Paths.get(dir))
                 .filter(Files::isRegularFile)
                 .filter(path -> path.toString().endsWith(".pdf"))
                 .map(Path::toFile)
                 .collect(Collectors.toList());
		return files;
    }
}
