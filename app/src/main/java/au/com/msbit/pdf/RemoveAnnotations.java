package au.com.msbit.pdf;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.function.Consumer;

import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

class RemoveAnnotations {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Requires two arguments, input PDF file and output PDF file");
      return;
    }

    PdfDocument document;

    try {
      document = new PdfDocument(new PdfReader(args[0]), new PdfWriter(args[1]));
    } catch (IOException e) {
      System.err.println("Unable to create PdfDocument: " + e.getMessage());
      return;
    }

    visit(document.getCatalog().getPdfObject(), (obj) -> {
      if (!(obj instanceof PdfDictionary)) { return; }

      var dict = (PdfDictionary)obj;
      if (!dict.containsKey(PdfName.Annots)) { return; }
      
      dict.remove(PdfName.Annots);
    });

    document.close();
  }

  static void visit(PdfObject root, Consumer<PdfObject> consumer) {
    var fifo = new ArrayDeque<PdfObject>();
    fifo.offerFirst(root);

    var seen = new HashSet<PdfObject>();
    while (!fifo.isEmpty()) {
      var obj = fifo.pollLast();
      if (seen.contains(obj)) { continue; }

      seen.add(obj);

      consumer.accept(obj);

      if (obj instanceof PdfDictionary) {
        for (var v: ((PdfDictionary)obj).values()) { fifo.offerFirst(v); }
      }

      if (obj instanceof PdfArray) {
        for (var c: (PdfArray)obj) { fifo.offerFirst(c); }
      }
    }
  }
}
