import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchWordInFile {
    private SearchFileWithExtension searchFileWithExtension;
    private SearchWord findWord;
    private List<File> fileList;
    public SearchWordInFile(){
        searchFileWithExtension = new SearchFileWithExtension();
        findWord = new SearchWord();
    }
    //список файлов по адресу, расширению и содержащиму слову
    public List<File> fileWithWord(String word, String path, String ext) throws IOException {
        searchFileWithExtension.findFiles(path, ext);
        fileList = searchFileWithExtension.getListFile();
        List<File> newList = new ArrayList<>();
        for (File file: fileList) {
            if(findWord.findWord(word, file.getPath())){
                newList.add(file);
            }
        }
        return newList;
    }
}
