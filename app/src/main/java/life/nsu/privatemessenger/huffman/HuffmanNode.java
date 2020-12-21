package life.nsu.privatemessenger.huffman;


class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;

    HuffmanNode left;
    HuffmanNode right;

    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }

}
