/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package docstripper;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;

public class App {
    public static void main(String[] args) throws IOException {
        var srcFile = DialogUtils.chooseFile()
            .orElseThrow(() -> new IllegalArgumentException("No file chosen"));
        var pdf = Loader.loadPDF(srcFile);

        var metadataFYI =
            COSUtils.getFYI(pdf.getDocumentInformation().getCOSObject()) +
            COSUtils.getFYI(pdf.getDocumentCatalog().getMetadata().getCOSObject());

        if (!DialogUtils.confirm(metadataFYI))
            System.exit(0);

        var dstFile = DialogUtils.chooseNewFile()
            .orElseThrow(() -> new IllegalArgumentException("No destination file chosen!"));

        pdf.setDocumentInformation(new PDDocumentInformation());
        pdf.getDocumentCatalog().setMetadata(new PDMetadata(pdf));
        pdf.save(dstFile);
        pdf.close();
    }
}