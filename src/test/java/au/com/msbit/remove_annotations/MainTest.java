package au.com.msbit.remove_annotations;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfBoolean;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MainTest {
  @Test
  public void handleMissingKey() {
    PdfDictionary obj = Mockito.mock();
    Mockito.when(obj.containsKey(PdfName.Annots)).thenReturn(false);

    Main.handle(obj);

    Mockito.verify(obj, Mockito.never()).remove(PdfName.Annots);
  }

  @Test
  public void handle() {
    PdfDictionary obj = Mockito.mock();
    Mockito.when(obj.containsKey(PdfName.Annots)).thenReturn(true);

    Main.handle(obj);

    Mockito.verify(obj, Mockito.times(1)).remove(PdfName.Annots);
  }

  @Test
  public void visitArray() {
    var obj1 = new PdfBoolean(true);
    var obj2 = new PdfBoolean(true);

    var arr = new PdfArray(List.of(
      obj1,
      obj2
    ));

    var visited = new HashSet<PdfObject>();

    Main.visit(arr, obj -> {
      visited.add(obj);
    });

    Assert.assertTrue(visited.contains(arr));
    Assert.assertTrue(visited.contains(obj1));
    Assert.assertTrue(visited.contains(obj2));
  }

  @Test
  public void visitDictionary() {
    var obj1 = new PdfBoolean(true);
    var obj2 = new PdfBoolean(true);

    var dict = new PdfDictionary(Map.of(
      new PdfName("first"), obj1,
      new PdfName("second"), obj2
    ));

    var visited = new HashSet<PdfObject>();

    Main.visit(dict, obj -> {
      visited.add(obj);
    });

    Assert.assertTrue(visited.contains(dict));
    Assert.assertTrue(visited.contains(obj1));
    Assert.assertTrue(visited.contains(obj2));
  }
}
