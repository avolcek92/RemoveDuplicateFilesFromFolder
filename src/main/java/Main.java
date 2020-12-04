import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {


       final File folder = new File(args[0]);


        Services services = new Services();
        services.getFilesFromFolder(folder);

    }
}
