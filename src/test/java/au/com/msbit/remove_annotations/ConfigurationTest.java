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
  public void processArgumentsEmpty() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{});

    Assert.assertFalse(result);
  }

  @Test
  public void processArgumentsIncompleteInputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--input"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsInputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--input", "input"});

    Assert.assertFalse(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsIncompleteShortInputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-i"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsShortInputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-i", "input"});

    Assert.assertFalse(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsIncompleteOutputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--output"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsOutputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--output", "output"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void processArgumentsIncompleteShortOutputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-o"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertNull(configuration.output);
  }

  @Test
  public void processArgumentsShortOutputOnly() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-o", "output"});

    Assert.assertFalse(result);
    Assert.assertNull(configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void processArgumentsInputOutput() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--input", "input", "--output", "output"});

    Assert.assertTrue(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void processArgumentsShortInputOutput() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-i", "input", "--output", "output"});

    Assert.assertTrue(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void processArgumentsInputShortOutput() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"--input", "input", "-o", "output"});

    Assert.assertTrue(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void processArgumentsShortInputShortOutput() {
    var configuration = new Configuration();

    var result = configuration.processArguments(new String[]{"-i", "input", "-o", "output"});

    Assert.assertTrue(result);
    Assert.assertEquals("input", configuration.input);
    Assert.assertEquals("output", configuration.output);
  }

  @Test
  public void usage() {
    var configuration = new Configuration();

    PrintStream stream = Mockito.mock();

    var order = Mockito.inOrder(stream);

    configuration.usage(stream);

    order.verify(stream).println("Usage: app [options]");
    order.verify(stream).println("    -i, --input INPUT       Input PDF file");
    order.verify(stream).println("    -o, --output OUTPUT     Output PDF file");
  }
}
