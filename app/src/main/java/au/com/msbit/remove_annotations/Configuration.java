package au.com.msbit.remove_annotations;

import java.io.PrintStream;

class Configuration {
  String input;
  String output;

  boolean processArguments(String[] args) {
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
    return input != null && output != null;
  }

  void usage(PrintStream stream) {
    stream.println("Usage: app [options]");
    stream.println("    -i, --input INPUT       Input PDF file");
    stream.println("    -o, --output OUTPUT     Output PDF file");
  }
}
