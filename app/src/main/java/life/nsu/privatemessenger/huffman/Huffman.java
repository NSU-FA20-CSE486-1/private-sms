package life.nsu.privatemessenger.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffman {

    private Map<Character, String> charPrefixHashMap;
    private HuffmanNode root;

    private HuffmanNode buildTree(Map<Character, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();

        for (Character c : keySet) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = c;
            huffmanNode.frequency = freq.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;
            priorityQueue.offer(huffmanNode);
        }

        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '-';

            sum.left = x;
            sum.right = y;

            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }

    private void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        charPrefixHashMap = new HashMap<>();

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    public String encode(String message) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < message.length(); i++) {
            if (!freq.containsKey(message.charAt(i))) {
                freq.put(message.charAt(i), 0);
            }
            freq.put(message.charAt(i), freq.get(message.charAt(i)) + 1);
        }

        System.out.println("Character Frequency Map = " + freq);
        root = buildTree(freq);

        setPrefixCodes(root, new StringBuilder());
        System.out.println("Character Prefix Map = " + charPrefixHashMap);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            stringBuilder.append(charPrefixHashMap.get(c));
        }

        return stringBuilder.toString();
    }

    public String decode(String message) {

        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = root;

        System.out.println("Encoded: " + message);

        for (int i = 0; i < message.length(); i++) {
            int j = Integer.parseInt(String.valueOf(message.charAt(i)));

            if (j == 0) {
                temp = temp.left;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root;
                }
            }
            if (j == 1) {
                temp = temp.right;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root;
                }
            }
        }

        return stringBuilder.toString();

    }
}