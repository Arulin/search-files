import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SearchWordTest {

    @Test
    void findWord() throws IOException {
        String word = "покупаю";
        String path = "D:/games/test.txt";
        SearchWord test = new SearchWord();
        Assert.assertTrue(test.findWord(word, path));
        word = "поокупаю";
        Assert.assertFalse(test.findWord(word, path));
        word = "я";
        Assert.assertTrue(test.findWord(word, path));
        word = "яя";
        Assert.assertFalse(test.findWord(word, path));
    }
}