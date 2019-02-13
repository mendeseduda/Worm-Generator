package wormpdfgenerator;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author Eduardo
 */
public class Connect {

    private String url;
    private Document document;

    public Connect() {
        this("https://parahumans.wordpress.com/2011/06/11/1-1/");
    }

    public Connect(String url) {
        this.url = url;
        try {
            this.document = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextPage(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
