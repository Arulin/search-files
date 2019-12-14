import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class SearchWordInFileTest {

    @Test
    void fileWithWord() throws IOException {
        SearchWordInFile files = new SearchWordInFile();
        List<File> fileList = files.fileWithWord("test", "D:\\games", ".txt");
        fileList.forEach(System.out::println);
    }
}