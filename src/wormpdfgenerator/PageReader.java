package wormpdfgenerator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Eduardo
 */
public class PageReader {

    public static final String ANSI_RED = "\u001B[31m";
    public static String title;
    private Document document;

    public void readHtml(HtmlWriter htmlWriter){
    	try {
            Element article = document.getElementsByTag("article").get(0);
    		title = getTitle(article);
    		htmlWriter.writeTitle(title);
            Element content = article.getElementsByClass("entry-content").get(0);
            content.getElementsByTag("div").remove();
            content.children().get(0).remove();
            content.children().get(content.children().size() - 1).remove();

            System.out.println("==================================================================");
            System.out.println("<h1>"+title+"</h1>");
            System.out.println("==================================================================");

            Elements paragraphs = content.getElementsByTag("p");

            for (Element paragraph : paragraphs) {
            	String paragraphHtml = paragraph.html();
            	htmlWriter.writeParagraph(paragraphHtml);
            }

		} catch (Exception e) {

		}
    }

    public void readContent(PdfWriter pw) {
        try {
            Element article = document.getElementsByTag("article").get(0);
            title = getTitle(article);
            String path = pw.generateDir(title);
            pw.createFile(title, path);
            pw.writeTitle(title);
            Element content = article.getElementsByClass("entry-content").get(0);
            content.getElementsByTag("div").remove();
            content.children().get(0).remove();
            content.children().get(content.children().size() - 1).remove();

            System.out.println("==================================================================");
            System.out.println(title);
            System.out.println("==================================================================");

            Elements paragraphs = content.getElementsByTag("p");

            for (Element paragraph   : paragraphs) {
            	String paragraphText = paragraph.text();
            	pw.writeParagraph(paragraphText);
            }
            pw.closeChapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String nextPageUrl() {
        try {
            Element nav = document.getElementsByTag("nav").get(1);
            Element span = nav.getElementsByClass("nav-next").get(0);
            String nextUrl = span.child(0).attr("href");
            return nextUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public String getTitle(Element element){
    	return element.getElementsByClass("entry-title").get(0).text();
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
