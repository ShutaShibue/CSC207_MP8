import java.io.PrintWriter;

public class BrailleASCII {
    public static void main(String[] args) {
        BrailleASCIITables tables = new BrailleASCIITables();
        PrintWriter pen = new PrintWriter(System.out, true);

        if(args[0].equals("braille")){
            char[] elem = args[1].toCharArray();

            for (char e : elem) {
                //pen.println(tables.toBraille(e));  // When I try to do print(), outputs are overwritten and only the last character remains.
                pen.print(tables.toBraille(e));
            }

            pen.println();
        }

        if(args[0].equals("ascii")){
            
            String[] elem = args[1].split("(?<=\\G.{6})");
            for (int i = 0; i < elem.length; i++) {
                pen.println(tables.toASCII(elem[i]).toLowerCase()); // When I try to do print(), outputs are overwritten and only the last character remains.
            }
        }

        if(args[0].equals("unicode")){
            
            char[] elem = args[1].toCharArray();
            for (int i = 0; i < elem.length; i++) {
                String braille = tables.toBraille(elem[i]);
                String unicode = tables.toUnicode(braille);
                pen.println(Character.toChars(2818)); // When I try to do print(), outputs are overwritten and only the last character remains.
            }
        }
    }
}
