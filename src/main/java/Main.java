import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args)  {

        //source folder
        final File folder = new File("C:/photos");

        Services services = new Services();
        //Getting files checksums from folder to List
        List<String> listFromFolder = services.getFilesFromFolder(folder);

        //Searching duplicates in list and creating list of duplicates
        List<String> listWithDuplicate = services.findDuplicate(listFromFolder);

        //Read files from list and delete from folder
        services.removeFilesFromFolder(listWithDuplicate, folder);





    }
}
