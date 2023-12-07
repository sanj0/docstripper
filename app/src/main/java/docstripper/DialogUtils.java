package docstripper;

import java.io.File;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogUtils {
    private static final FileNameExtensionFilter PDF_FILE_FILTER =
            new FileNameExtensionFilter("PDF Documents", "pdf");

    public static boolean confirm(String metadata) {
        var status = JOptionPane.showConfirmDialog(
            null,
            metadata,
            "Delete all metadata?",
            JOptionPane.OK_CANCEL_OPTION
        );

        return status == JOptionPane.OK_OPTION;
    }

    public static Optional<File> chooseFile() {
        var fileChooser = createFileChooser();
        int status = fileChooser.showOpenDialog(null);

        if (status == JFileChooser.APPROVE_OPTION) {
            return Optional.of(fileChooser.getSelectedFile());
        } else {
            return Optional.empty();
        }
    }

    public static Optional<File> chooseNewFile() {
        var fileChooser = createFileChooser();
        int status = fileChooser.showSaveDialog(null);

        if (status == JFileChooser.APPROVE_OPTION) {
            var file = fileChooser.getSelectedFile();
            if (file.getName().toLowerCase().endsWith("pdf")) {
                return Optional.of(file);
            } else {
                return Optional.of(new File(file.getAbsolutePath() + ".pdf"));
            }
        } else {
            return Optional.empty();
        }
    }

    private static JFileChooser createFileChooser() {
        var fileChooser = new JFileChooser();
        fileChooser.setFileFilter(PDF_FILE_FILTER);
        return fileChooser;
    }
}
