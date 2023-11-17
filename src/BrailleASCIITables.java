import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BrailleASCIITables {

    BitTree BtoA;
    BitTree AtoB;
    BitTree BtoU;

    /**
     * Constructor
     */
    public BrailleASCIITables(){
        BtoA = new BitTree(6);
        AtoB = new BitTree(8);
        BtoU = new BitTree(6);

        File ba = new File("src/dict/BtoA.txt");
        File ab = new File("src/dict/AtoB.txt");
        File bu = new File("src/dict/BtoU.txt");

        try {
            InputStream fis = new FileInputStream(ba);
            BtoA.load(fis);
            
            fis = new FileInputStream(ab);
            AtoB.load(fis);

            fis = new FileInputStream(bu);
            BtoU.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Ascii → Braille
    public String toBraille(char letter){
        return AtoB.get(String.format("%8s", Integer.toBinaryString(letter)).replace(' ', '0'));
    }

    // Braille → Ascii
    public String toASCII(String bits){
        return BtoA.get(bits);
    }

    // Braille → Unicode dots
    public String toUnicode(String bits){
        return BtoU.get(bits);
    }
}
