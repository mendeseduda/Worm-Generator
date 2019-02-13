package wormpdfgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class HtmlWriter {

	private OutputStream os = null;
	private OutputStreamWriter osw = null;
	private BufferedWriter bw = null;
	private ArrayList<String> arcs;
	private String currentArc;

	public HtmlWriter(String path) {
		try {
			this.os = new FileOutputStream(path + "/Worm_Ebook.html");
			this.osw = new OutputStreamWriter(os);
			this.bw = new BufferedWriter(osw);
			this.arcs = new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeTitle(String title) {
		try {
			String id = title.replace(":", "").replace(" ", "");
			bw.write("<h1 id=\"" + id + "\" >" + title + "</h1>");
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeParagraph(String paragraph) {
		try {
			bw.write("<p>" + paragraph + "</p>");
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeTable(String title) {
		title = title.replace(":", "");
		String arc = title.split(" ")[0];
		if (!arcs.contains(arc)) {
			arcs.add(arc);
			if (!arc.equals("Interlude")) {
				currentArc = arc;
				try {
					bw.write(String.format("<p style=\"text-align: left;\"><strong>%s</strong></p>", arc));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("\t" + title);
	}

	public void closeAll() {
		try {
			this.bw.close();
			this.osw.close();
			this.os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
