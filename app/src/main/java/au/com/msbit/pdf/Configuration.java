package au.com.msbit.pdf;

import java.io.PrintStream;

class Configuration {
  String input;
  String output;

  boolean processArguments(String[] args) {
    for (var i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-i":
        case "--input":
          if (++i < args.length) { input = args[i]; }
          break;
        case "-o":
        case "--output":
          if (++i < args.length) { output = args[i]; }
          break;
      }
    }
    return input != null && output != null;
  }

  void usage(PrintStream stream) {
    stream.println("Usage: RemoveAnnotations [options]");
    stream.println("    -i, --input INPUT       Input PDF file");
    stream.println("    -o, --output OUTPUT     Output PDF file");
  }
}
