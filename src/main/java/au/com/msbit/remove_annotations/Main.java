package au.com.msbit.remove_annotations;

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

class Main {
  public static void main(String[] args) {
    Configuration.fromArguments(args).ifPresentOrElse(configuration -> {
      PdfDocument document;

      try {
        document = new PdfDocument(
          new PdfReader(configuration.input),
          new PdfWriter(configuration.output)
        );
      } catch (IOException e) {
        System.err.println("Unable to create PdfDocument: " + e.getMessage());
        return;
      }

      visit(document.getCatalog().getPdfObject(), Main::handle);

      document.close();
    }, () -> {
      Configuration.usage(System.err);
      return;
    });
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

      switch (obj) {
        case PdfDictionary dict -> {
          for (var v: dict.values()) { fifo.offerFirst(v); }
        }
        case PdfArray array -> {
          for (var c: array) { fifo.offerFirst(c); }
        }
        default -> {}
      }
    }
  }

  static void handle(PdfObject obj) {
    if (obj instanceof PdfDictionary dict) {
      if (!dict.containsKey(PdfName.Annots)) { return; }

      dict.remove(PdfName.Annots);
    }
  }
}
