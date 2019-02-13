package wormpdfgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class PdfWriter {

	private String path;
	private ArrayList<String> arcs;
	private String currentArc;
	private Document chapter;
	private OutputStream os;
	Font font;
	private int cont;

	public PdfWriter() {
		arcs = new ArrayList<>();
		font = new Font(FontFamily.TIMES_ROMAN, 26.0f);
		os = null;
		cont = 0;
	}

	public String generateDir(String title) {
		title = title.replace(":", "");
		String arc = title.split(" ")[0];
		String path;
		if (!arcs.contains(arc)) {
			arcs.add(arc);
			if (!arc.equals("Interlude")) {
				currentArc = arc;
				cont++;
			}
			path = this.path + "\\" + cont + ". " + (arc.equals("Interlude") ? currentArc : arc);
			File directory = new File(path);
			directory.mkdir();
		}
		path = this.path + "\\" + cont + ". " + (arc.equals("Interlude") ? currentArc : arc);
		System.out.println(path);
		return path;
	}

	public void createFile(String title, String path) {
		try {
			path += "\\" + title + ".pdf";
			os = new FileOutputStream(path);
			Document chapter = new Document();

			com.itextpdf.text.pdf.PdfWriter.getInstance(chapter, os);
			chapter.open();
			this.chapter = chapter;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeTitle(String title) {
		try {
			Phrase phrase = new Phrase(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36));
			Paragraph titleParagraph = new Paragraph(phrase);
			titleParagraph.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
			chapter.add(titleParagraph);
			chapter.add(Chunk.NEWLINE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeParagraph(String text) {
		try {
			Paragraph paragraph = new Paragraph(text, font);
			paragraph.setSpacingAfter(15f);
			chapter.add(paragraph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeChapter() {
		chapter.close();
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

}
