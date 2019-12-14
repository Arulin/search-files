import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

class SearchFileWithExtensionTest {

    @Test
    void findFiles() throws FileNotFoundException, MalformedURLException, URISyntaxException {
        SearchFileWithExtension search =new SearchFileWithExtension();
        search.findFiles("file:///D:/games/", ".txt");
        List<File> list = search.getListFile();
        for(File file : list){
            System.out.println(file.getPath());
        }

    }
}