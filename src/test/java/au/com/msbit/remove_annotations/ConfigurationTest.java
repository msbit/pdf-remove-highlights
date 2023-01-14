package au.com.msbit.remove_annotations;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ConfigurationTest {
  @Test
  public void fromArgumentsEmpty() {
    var result = Configuration.fromArguments(new String[]{});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsIncompleteInputOnly() {
    var result = Configuration.fromArguments(new String[]{"--input"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsInputOnly() {
    var result = Configuration.fromArguments(new String[]{"--input", "input"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsIncompleteShortInputOnly() {
    var result = Configuration.fromArguments(new String[]{"-i"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsShortInputOnly() {
    var result = Configuration.fromArguments(new String[]{"-i", "input"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsIncompleteOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"--output"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"--output", "output"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsIncompleteShortOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"-o"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsShortOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"-o", "output"});

    Assert.assertNull(result);
  }

  @Test
  public void fromArgumentsInputOutput() {
    var result = Configuration.fromArguments(new String[]{"--input", "input", "--output", "output"});

    Assert.assertNotNull(result);
    Assert.assertEquals("input", result.input);
    Assert.assertEquals("output", result.output);
  }

  @Test
  public void fromArgumentsShortInputOutput() {
    var result = Configuration.fromArguments(new String[]{"-i", "input", "--output", "output"});

    Assert.assertNotNull(result);
    Assert.assertEquals("input", result.input);
    Assert.assertEquals("output", result.output);
  }

  @Test
  public void fromArgumentsInputShortOutput() {
    var result = Configuration.fromArguments(new String[]{"--input", "input", "-o", "output"});

    Assert.assertNotNull(result);
    Assert.assertEquals("input", result.input);
    Assert.assertEquals("output", result.output);
  }

  @Test
  public void fromArgumentsShortInputShortOutput() {
    var result = Configuration.fromArguments(new String[]{"-i", "input", "-o", "output"});

    Assert.assertNotNull(result);
    Assert.assertEquals("input", result.input);
    Assert.assertEquals("output", result.output);
  }

  @Test
  public void usage() {
    PrintStream stream = Mockito.mock();

    var order = Mockito.inOrder(stream);

    Configuration.usage(stream);

    order.verify(stream).println("Usage: app [options]");
    order.verify(stream).println("    -i, --input INPUT       Input PDF file");
    order.verify(stream).println("    -o, --output OUTPUT     Output PDF file");
  }
}
