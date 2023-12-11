import java.io.PrintWriter;

public class BrailleASCII {
    public static void main(String[] args) {
        BrailleASCIITables tables = new BrailleASCIITables();
        PrintWriter pen = new PrintWriter(System.out, true);

        if (args[0].equals("braille")) {
            char[] elem = args[1].toCharArray();

            for (char e : elem) {
                pen.print(tables.toBraille(e));
            }
            pen.println();
        }

        if (args[0].equals("ascii")) {

            String[] elem = args[1].split("(?<=\\G.{6})");
            for (int i = 0; i < elem.length; i++) {
                pen.print(tables.toASCII(elem[i]).toLowerCase()); // When I try to do print(),
                                                                  // outputs are overwritten and
                                                                  // only the last character
                                                                  // remains.
            }
            pen.println();
        }

        if (args[0].equals("unicode")) {

            char[] elem = args[1].toCharArray();
            for (int i = 0; i < elem.length; i++) {
                String braille = tables.toBraille(elem[i]);
                String unicode = tables.toUnicode(braille);
                pen.print(Character.toChars(Integer.parseInt(unicode, 16))); // When I try to do
                                                                             // print(), outputs are
                                                                             // overwritten and only
                                                                             // the last character
                                                                             // remains.
            }
            pen.println();
        }
    }
}
