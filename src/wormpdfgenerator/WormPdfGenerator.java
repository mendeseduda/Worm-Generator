package wormpdfgenerator;

/**
 *
 * @author Eduardo
 */
public class WormPdfGenerator {

	public static void main(String[] args) {
		Connect connection = new Connect();
		PageReader pr = new PageReader();
		PdfWriter pw = new PdfWriter();
		String url;
		int cont = 0;
		String path = Path.getPath();
		pw.setPath(path);
		HtmlWriter htmlWriter = new HtmlWriter(path);
		// * Para HTML *
		System.out.println("Teste");
		do {
			pr.setDocument(connection.getDocument());
			pr.readHtml(htmlWriter);
			url = pr.nextPageUrl();
			connection.nextPage(url);
			cont++;
		} while (!PageReader.title.equals("Interlude: End"));
		htmlWriter.closeAll();
		
		// * Para PDF *
		// do {
		// pr.setDocument(connection.getDocument());
		// pr.readContent(pw);
		// url = pr.nextPageUrl();
		// connection.nextPage(url);
		// cont++;
		// } while (!PageReader.title.equals("Interlude: End"));
	}

}
