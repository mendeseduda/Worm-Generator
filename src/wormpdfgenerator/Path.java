package wormpdfgenerator;

import java.io.File;

import javax.swing.JFileChooser;

public class Path {

	private static String path;

	public static String getPath() {
		JFileChooser jf = new JFileChooser();
		jf.setCurrentDirectory(new File("."));
		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jf.setDialogTitle("Select Folder");
		jf.setAcceptAllFileFilterUsed(false);
		if (jf.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
			System.exit(0);
		}
		path = jf.getSelectedFile().toString();
		return path;
	}
}
