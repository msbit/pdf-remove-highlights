package au.com.msbit.remove_annotations;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mockito;

public class ConfigurationTest {
  @Test
  public void fromArgumentsEmpty() {
    var result = Configuration.fromArguments(new String[]{});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsIncompleteInputOnly() {
    var result = Configuration.fromArguments(new String[]{"--input"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsInputOnly() {
    var result = Configuration.fromArguments(new String[]{"--input", "input"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsIncompleteShortInputOnly() {
    var result = Configuration.fromArguments(new String[]{"-i"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsShortInputOnly() {
    var result = Configuration.fromArguments(new String[]{"-i", "input"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsIncompleteOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"--output"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"--output", "output"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsIncompleteShortOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"-o"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsShortOutputOnly() {
    var result = Configuration.fromArguments(new String[]{"-o", "output"});

    MatcherAssert.assertThat(result, new IsEmpty());
  }

  @Test
  public void fromArgumentsInputOutput() {
    var result = Configuration.fromArguments(new String[]{"--input", "input", "--output", "output"});

    MatcherAssert.assertThat(result, new IsPresent());
    result.ifPresent(r -> {
      Assert.assertEquals("input", r.input);
      Assert.assertEquals("output", r.output);
    });
  }

  @Test
  public void fromArgumentsShortInputOutput() {
    var result = Configuration.fromArguments(new String[]{"-i", "input", "--output", "output"});

    MatcherAssert.assertThat(result, new IsPresent());
    result.ifPresent(r -> {
      Assert.assertEquals("input", r.input);
      Assert.assertEquals("output", r.output);
    });
  }

  @Test
  public void fromArgumentsInputShortOutput() {
    var result = Configuration.fromArguments(new String[]{"--input", "input", "-o", "output"});

    MatcherAssert.assertThat(result, new IsPresent());
    result.ifPresent(r -> {
      Assert.assertEquals("input", r.input);
      Assert.assertEquals("output", r.output);
    });
  }

  @Test
  public void fromArgumentsShortInputShortOutput() {
    var result = Configuration.fromArguments(new String[]{"-i", "input", "-o", "output"});

    MatcherAssert.assertThat(result, new IsPresent());
    result.ifPresent(r -> {
      Assert.assertEquals("input", r.input);
      Assert.assertEquals("output", r.output);
    });
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
