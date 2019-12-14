import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchFileWithExtension {

    private volatile List<File> targetFiles = null;
    private List<File> subDirectories = null;

    public SearchFileWithExtension(){
        targetFiles = new ArrayList<>();
    }

    //основной метод поиска файлов
    public void findFiles(String dir, String ext) {
        File directory = checkDir(dir);
        if(directory.isDirectory()){
            targetFiles.addAll(Arrays.asList(directory.listFiles(new ExtFilter(ext))));
        }
        subDirectories = getSubDirectories(directory);
        do{
            List<File> subDirectories2 = new ArrayList<>();
            for(File subDir : subDirectories){
                targetFiles.addAll(Arrays.asList(subDir.listFiles(new ExtFilter(ext))));
                subDirectories2.addAll(getSubDirectories(subDir));
            }
            subDirectories = subDirectories2;

        }while (subDirectories != null && !subDirectories.isEmpty());

    }
    //проверяем полученный путь
    private File checkDir(String dir){
        try {
            if (dir == null) {
                throw new FileNotFoundException("пустой путь");
            }
            if (dir.contains("file:/")) {
                URI uri = new URL(dir).toURI();
                return new File(uri);
            }
        }catch (FileNotFoundException notFoundEx){
            notFoundEx.printStackTrace();
        }catch (MalformedURLException URLEx){
            System.out.println("URL");
            URLEx.printStackTrace();
        }catch (URISyntaxException URIEx){
            System.out.println("URI");
            URIEx.printStackTrace();
        }
        return new File(dir);
    }

    //ищем файлы с нужным расширением в папке
    private class ExtFilter implements FilenameFilter{
         private String extension= "";
        ExtFilter(String ext){
            extension = ext;
        }

        @Override
        public boolean accept(File file, String name) {
                return name.toLowerCase().endsWith(extension);
        }
    }
    //ищем все доп. папки
    private List<File> getSubDirectories(File directory){
        File[] subDir = directory.listFiles((current, name) -> new File(current, name).isDirectory());
        return Arrays.asList(subDir);
    }
    //получаем неизменяемый список файлов
    public List<File> getListFile(){
        return Collections.unmodifiableList(targetFiles);
    }


}
