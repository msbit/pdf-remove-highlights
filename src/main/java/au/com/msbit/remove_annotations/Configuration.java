package au.com.msbit.remove_annotations;

import java.io.PrintStream;

class Configuration {
  String input;
  String output;

  private Configuration(String input, String output) {
    this.input = input;
    this.output = output;
  }

  static Configuration fromArguments(String[] args) {
    String input = null;
    String output = null;

    for (var i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-i", "--input" -> {
          if (++i < args.length) { input = args[i]; }
        }
        case "-o", "--output" -> {
          if (++i < args.length) { output = args[i]; }
        }
      }
    }

    if (input == null || output == null) {
      return null;
    }

    return new Configuration(input, output);
  }

  static void usage(PrintStream stream) {
    stream.println("Usage: app [options]");
    stream.println("    -i, --input INPUT       Input PDF file");
    stream.println("    -o, --output OUTPUT     Output PDF file");
  }
}
