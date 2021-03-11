package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstTaskSolution implements FirstTask {

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(startIndex);
        while (!que.isEmpty()) {
            startIndex = que.removeFirst();
            if (!vertexList.contains(startIndex)) {
                vertexList.add(startIndex);
            }
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                boolean elem = adjacencyMatrix[startIndex][i];
                if (elem && !vertexList.contains(i)) {
                    que.addLast(i);
                    vertexList.add(i);
                }
            }
        }

        String result = "";
        for (int i = 0; i < vertexList.size(); i++) {
            int vertex = vertexList.get(i);
            result += vertex;
            if (i != vertexList.size() - 1) {
                result += ", ";
            }
        }
        return result;
    }
    public boolean [][] createAdjacencyMatrix() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of vertexes");
        int n = sc.nextInt();
        boolean [][] matrix = new boolean[n][n];
        System.out.println("Adjacency matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                if (x == 0) {
                    matrix[i][j] = false;
                }else{
                    matrix[i][j] = true;
                }
            }
        }
        System.out.println("Index start");
        return matrix;

    }

    @Override
    public Boolean validateBrackets(String s) {
        ArrayDeque<Character> arrayDeque= new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == '(' || symbol == '{' || symbol == '[') {
                arrayDeque.addFirst(symbol);
            }else if (symbol == ']') {
                if (arrayDeque.isEmpty() || arrayDeque.removeFirst() != '[') {
                    return false;
                }
            } else if (symbol == '}') {
                if (arrayDeque.isEmpty() || arrayDeque.removeFirst() != '{') {
                    return false;
                }
            } else if (symbol == ')') {
                if (arrayDeque.isEmpty() || arrayDeque.removeFirst() != '(') {
                    return false;
                }
            }
        }
        return arrayDeque.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Long> arrayDeque = new ArrayDeque<>();
        String[] ar = s.split(" ");
        long b = 0;
        for (int i = 0; i < (ar.length / 2) + 1; i++) {
            b = Long.parseLong(ar[i]);
            arrayDeque.addFirst(b);
        }
        for (int i = 0; i < ar.length; i++) {
            if (i > ar.length / 2) {
                long n1 = arrayDeque.pollFirst();
                long n2 = arrayDeque.pollFirst();
                long res = 0;
                switch (ar[i]) {
                    case "+":
                        res = n1 + n2;
                        break;
                    case "-":
                        res = n1 - n2;
                        break;
                    case "*":
                        res = n1 * n2;
                        break;
                    case "/":
                        res = n1 / n2;
                        break;
                    default:
                        break;
                }
                arrayDeque.addFirst(res);
            }
        }
        return arrayDeque.removeFirst();
    }
}
