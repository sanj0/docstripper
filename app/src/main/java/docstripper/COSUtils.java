package docstripper;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSString;

public class COSUtils {
    /** Returns an "FYI" String of the object */
    public static String getFYI(COSBase o) {
        if (o instanceof COSString) {
            return ((COSString) o).getString();
        } else if (o instanceof COSName) {
            return ((COSName) o).getName();
        } else if (o instanceof COSNumber) {
            return String.valueOf(((COSNumber) o).intValue());
        } else if (o instanceof COSDictionary) {
            var dict = (COSDictionary) o;
            var s = new StringBuilder();
            for (var child : dict.entrySet()) {
                s.append(child.getKey().getName())
                    .append(": ")
                    .append(getFYI(child.getValue()))
                    .append("\n");
            }
            return s.toString();
        } else {
            return o.toString();
        }
    }
}

