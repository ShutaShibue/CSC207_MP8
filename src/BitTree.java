import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class BitTree {

    BitTreeNode root;
    int size;

    public BitTree(int n) {
        size = n;
        root = new BitTreeInteriorNode();
    }

    public void set(String bits, String value) {
        if (bits == null) {
            throw new NullPointerException("null key");
        }
        this.root = set(bits, value, root, 0);
    } // set(String, String)

    BitTreeNode set(String bits, String value, BitTreeNode node, int depth) {
        if (bits.length() == depth) {
            return new BitTreeLeaf(bits, value);
        } // set leaf

        BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) node;
        if (interiorNode == null)
            interiorNode = new BitTreeInteriorNode();
        char b = bits.toCharArray()[depth];

        if (b == '0') {
            interiorNode.left = set(bits, value, interiorNode.left, ++depth);
        } else if (b == '1') {
            interiorNode.right = set(bits, value, interiorNode.right, ++depth);
        } else
            throw new IllegalArgumentException(bits);

        return interiorNode;
    } // set(K,V)

    public String get(String bits) {
        return get(bits, root, 0);
    } // get(String)

    String get(String bits, BitTreeNode node, int depth) {
        if (node == null)
            throw new NoSuchElementException();
        if (node.isLeaf()) {
            BitTreeLeaf leaf = (BitTreeLeaf) node;
            return leaf.value();
        } else {
            BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) node;
            char b = bits.toCharArray()[depth];

            if (b == '0') {
                return get(bits, interiorNode.left, ++depth);
            } else if (b == '1') {
                return get(bits, interiorNode.right, ++depth);
            } else
                throw new IllegalArgumentException(bits);
        }
    }

    public void dump(PrintWriter pen) {
        dump(pen, root);
    } // dump(PrintWriter)


    /**
     * Dump a portion of the tree to some output location.
     */
    void dump(PrintWriter pen, BitTreeNode node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            BitTreeLeaf leaf = (BitTreeLeaf) node;
            pen.println(leaf.bits() + "," + leaf.value());
        } else {
            BitTreeInteriorNode interiorNode = (BitTreeInteriorNode) node;
            if ((interiorNode.left() != null) || (interiorNode.right() != null)) {
                dump(pen, interiorNode.left());
                dump(pen, interiorNode.right());
            } // if has children
        }
    } // dump

    public void load(InputStream source) {
        try {
            byte data[] = new byte[source.available()];
            source.read(data);
            source.close();
            String[] str = new String(data).split("\n");
            for (String string : str) {
                String[] elems = string.split(",");
                set(elems[0], elems[1]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
