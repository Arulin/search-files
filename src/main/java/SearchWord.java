import java.io.*;

public class SearchWord {

    public boolean findWord(String word, String path) throws IOException {
        if(word == null || path == null){
            throw new IllegalArgumentException("некорректные данные");
        }
        Reader reader = new FileReader(new File(path));
        //Слово из одной буквы
        if(word.length() == 1){
            char oneLetter =  word.charAt(0);
            while(reader.ready()){
                if((char)reader.read() == oneLetter){
                    switch ((char)reader.read()){
                        case ' ':
                        case '.':
                        case ',':
                            return true;
                        default:
                            return false;
                    }
                }
            }
        }
        //больше 1 буквы
        char[] first = word.toCharArray();
        while(reader.ready()){
            if((char)reader.read() == first[0]){
                for(int i = 1; i < word.length();i++){
                    if(reader.read() == first[i]){
                        if(i == word.length()-1) {
                            switch ((char)reader.read()){
                                case ' ':
                                case '.':
                                case ',':
                                    return true;
                                default:
                                    break;
                            }
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
