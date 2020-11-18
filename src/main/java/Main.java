import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args)  {

        //source folder
        final File folder = new File("C:/photos");

        GettingData gettingData = new GettingData();
        //Getting files checksums from folder to List
        List<String> listFromFolder = gettingData.getFilesFromFolder(folder);

        //Searching duplicates in list and creating list of duplicates
        List<String> listWithDuplicate = gettingData.findDuplicate(listFromFolder);

        //Read files from list and delete from folder
        gettingData.removeFilesFromFolder(listWithDuplicate, folder);





    }
}
