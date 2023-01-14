package au.com.msbit.remove_annotations;

import java.io.PrintStream;
import java.util.Optional;

class Configuration {
  String input;
  String output;

  private Configuration(String input, String output) {
    this.input = input;
    this.output = output;
  }

  static Optional<Configuration> fromArguments(String[] args) {
    var input = Optional.<String>empty();
    var output = Optional.<String>empty();

    for (var i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-i", "--input" -> {
          if (++i < args.length) { input = Optional.of(args[i]); }
        }
        case "-o", "--output" -> {
          if (++i < args.length) { output = Optional.of(args[i]); }
        }
      }
    }

    if (input.isEmpty() || output.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(new Configuration(input.get(), output.get()));
  }

  static void usage(PrintStream stream) {
    stream.println("Usage: app [options]");
    stream.println("    -i, --input INPUT       Input PDF file");
    stream.println("    -o, --output OUTPUT     Output PDF file");
  }
}
