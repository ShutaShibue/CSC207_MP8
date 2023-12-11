interface BitTreeNode {
    public boolean isLeaf();
} // BitTreeNode


class BitTreeInteriorNode implements BitTreeNode {
    BitTreeNode left;
    BitTreeNode right;

    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+
    public BitTreeInteriorNode() {
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return false;
    } // isLeaf()

    public BitTreeNode left() {
        return left;
    }

    public BitTreeNode right() {
        return right;
    }

} // class BitTreeInteriorNode


class BitTreeLeaf implements BitTreeNode {
    String bits;
    String value;

    public boolean isLeaf() {
        return true;
    } // isLeaf()

    public String bits() {
        return bits;
    }

    public String value() {
        return value;
    }

    public BitTreeLeaf(String bits, String value) {
        this.bits = bits;
        this.value = value;
    }

} // class BitTreeLea
