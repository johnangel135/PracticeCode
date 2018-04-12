package com.duong.CodeFights;

import java.util.*;


public class Intro {
    public static void main(String[] args) {
        Intro intro = new Intro();
        int[] a = new int[]{1, 3, 2};
        String r = "(2 (7 (2 () ()) (6 (5 () ()) (11 () ()))) (5 () (9 (4 () ()) ())))";

        System.out.println(intro.almostIncreasingSequence(a));
    }

    int matrixElementsSum(int[][] matrix) {

    }

    boolean almostIncreasingSequence(int[] sequence) {
        if(sequence.length <= 2) return true;
        if(isIncrease(sequence)) return true;
        else {
            int current = 0,next =0;
            for(int i=0; i < sequence.length - 1; i++){
                if(sequence[i] >= sequence[i+1]) {
                    current = i;
                    next = i+1;
                    break;
                }
            }
            int[] arr1 = new int[sequence.length-1];
            int[] arr2 = new int[sequence.length-1];
            int index1 = 0, index2 = 0;
            for(int i=0; i < sequence.length; i++){
                if(i == current) arr1[index1++] = sequence[i];
                else if(i == next) arr2[index2++] = sequence[i];
                else {
                    arr1[index1++] = sequence[i];
                    arr2[index2++] = sequence[i];
                }
            }
            if(isIncrease(arr1)) return true;
            else return isIncrease(arr2);
        }
    }

    boolean isIncrease(int[] list) {
        for(int i=0; i < list.length - 1; i++){
            if(list[i] >= list[i+1]) return false;
        }
        return true;
    }


    int makeArrayConsecutive2(int[] statues) {
        Set<Integer> list = new HashSet<>();
        if(statues.length <= 1) return 0;
        for(int num:statues){
            list.add(num);
        }
        int count = 0;
        Iterator it = list.iterator();
        int last = (int) it.next();
        while (it.hasNext()){
            int current = (int) it.next();
            count += (current - last -1);
            last = current;
        }
        return count;
    }
    int polishNotation(String[] tokens) {
        class Helper {
            boolean isNumber(String stringRepresentation) {
                return stringRepresentation.length() > 1 ||
                        '0' <= stringRepresentation.charAt(0) &&
                                stringRepresentation.charAt(0) <= '9';
            }
        };
        List<String> myStack = new ArrayList<>();
        Helper h = new Helper();
        int i = 0;
        while (myStack.size() != 1 && i <= tokens.length) {
            myStack.add(tokens[i++]);
            if (myStack.size() > 2 && h.isNumber(myStack.get(myStack.size() - 1))
                    && h.isNumber(myStack.get(myStack.size() - 2))) {
                int leftOperand = Integer.parseInt(myStack.get(myStack.size() - 2));
                int rightOperand = Integer.parseInt(myStack.get(myStack.size() - 1));
                int result = 0;
                if (myStack.get(myStack.size() - 3).equals("-")) {
                    result = leftOperand - rightOperand;
                }
                if (myStack.get(myStack.size() - 3).equals("+")) {
                    result = leftOperand + rightOperand;
                }
                if (myStack.get(myStack.size() - 3).equals("*")) {
                    result = leftOperand * rightOperand;
                }
                myStack = myStack.subList(0, myStack.size() - 3);
                myStack.add(Integer.toString(result));
            }
        }

        return Integer.parseInt(myStack.get(0));
    }

     class Pair{
        int key;
        int value;
        Pair(int key, int value){
            this.key = key;
            this.value = value;
        }

          int getValue() {
             return value;
         }

          int getKey() {
             return key;
         }
     }
    int[] treeBottom(String tree) {
        List<Pair> pairList = new ArrayList<>();
        String[] element = tree.split(" ");
        int level = -1;
        int max = 0;
        List<Integer> result = new ArrayList<>();
//        Stack<Integer> stack = new Stack<>();
        for(String s:element){
            if(s.charAt(1) != ')'){
                level++;
                int num = Integer.parseInt(s.substring(1));
                pairList.add(new Pair(num,level));
//                stack.push(num);
            }
            else {
                int out = s.length() - 2;
                max = (max > level)? max : level;
                level -= out;
            }
        }
        for(Pair p: pairList){
            if(p.getValue() == max){
                result.add(p.getKey());
            }
        }
        int[] tmp = new int[result.size()];
        for(int i=0; i < tmp.length; i++){
            tmp[i] = result.get(i);
        }
        return tmp;
//            List<Integer> list = new ArrayList<>();

//            for(int i=0; i < element.length; i++){
//                String e = element[i];
//                boolean open = false;
//                boolean close = false;
//                char num = e.charAt(1);
//                if(num != ')'){
//                    TreeNode node = new TreeNode(num);
//                }
//            }
//            int[] result = new int[list.size()];
//        for(int i = 0; i < result.length; i ++) result[i] = list.get(i);
//        return result;
    }

    int[] bankRequests(int[] accounts, String[] requests) {
            int size = accounts.length;
            int id  = 0;
            for(String request:requests){
                id++;
                String[] r = request.split(" ");
                String key = r[0];
                int i = Integer.parseInt(r[1]);
                if(i > accounts.length) return new int[]{-id};
                int sum = Integer.parseInt(r[r.length -1]);
                if(key.equals("transfer")){
                    if(accounts[i-1] < sum)return new int[]{-id};
                    int j = Integer.parseInt(r[2]);
                    if(j > accounts.length) return new int[]{-id};
                    accounts[i-1] -= sum;
                    accounts[j-1] += sum;
                }
                else if(key.equals("deposit")){
                    accounts[i-1] += sum;
                }
                else if(key.equals("withdraw")){
                    if(accounts[i-1] < sum) return new int[]{-id};
                    accounts[i-1] -= sum;

                }
                else {
                    return new int[]{-id};
                }
            }
            return accounts;
    }


    int[][] constructSubmatrix(int[][] matrix, int[] rowsToDelete, int[] columnsToDelete) {
        int rs = rowsToDelete.length;
        int cs = columnsToDelete.length;

        int rm = matrix.length;
        int cm = matrix[0].length ;



        if(rs == 0 && cs == 0) return matrix;

        if(rm + cm == rs + cs) return new int[][]{};

        List<Integer> row = new ArrayList<>();
        for(int num: rowsToDelete){
            row.add(num);
        }

        List<Integer> col = new ArrayList<>();
        for(int num: columnsToDelete){
            col.add(num);
        }
        int[][] result = new int[rm - rs][cm - cs];
        int r = 0;
        int c = 0;
        for(int i=0; i < rm; i++){
            if(row.contains(i)) continue;
            else{
                for(int j=0; j < cm; j++){
                    if(col.contains(j)) continue;
                    else {
                        result [r][c] = matrix[i][j];
                        c++;
                    }
                    if(c == cm - cs){
                        c = 0;
                        r++;
                    }
                }
            }
        }
        return result;
    }

    int zigzag(int[] a) {
        if(a.length <= 2) {
            if(a[0] == a[1]) return 1;
            else return 2;
        }
        int max = 1;
        int count = 0;
        for(int i =1; i < a.length -1; i++){
            int mul = (a[i] - a[i-1]) * (a[i] - a[i+1]);
            if(mul > 0){
                count ++;
                max = (max > count + 2) ? max : count + 2;
            }
            else count = 0;
        }
        return max;
    }

    int shapeArea(int n) {
        if(n == 1) return 1;
        return (n-1)*4 + shapeArea(n-1);
    }


    int adjacentElementsProduct(int[] inputArray) {
        int max = inputArray[0];
        for(int i=0; i < inputArray.length -1; i++){
            int mul = inputArray[i] * inputArray[i+1];
            max =  (max > mul) ? max : mul;
        }
        return max;
    }
    boolean checkPalindrome(String inputString) {
            if (inputString.length() < 2) return true;
            char[] chars = inputString.toCharArray();
            int size = chars.length;
            for(int i=0; i < size/2; i++){
                if(chars[i] != chars[size - i]) return false;
            }
            return true;

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            long time = System.currentTimeMillis();
            double result = 0.0;
            int[] sort = mergesort(nums1,nums2);
            int size = sort.length;
            if(size%2 != 0) result = sort[size/2];
            else result = (double) (sort[size/2] + sort[size/2 - 1])/2;
            System.out.println(System.currentTimeMillis()-time);
            return result;
    }

    private int[] mergesort(int[] a, int[] b) {
        int[] answer = new int[a.length + b.length];
        int i = a.length - 1, j = b.length - 1, k = answer.length;

        while (k > 0)
            answer[--k] =
                    (j < 0 || (i >= 0 && a[i] >= b[j])) ? a[i--] : b[j--];
        return answer;

    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for(int i=0; i <= s.length() -1; i++){
            for(int j=i+1; j <= s.length(); j++){
                String tmp = s.substring(i,j);
                if(duplicate(tmp)){
                    break;
                }
                max = Math.max(max,tmp.length());
                if(j == s.length()) return max; // no more to check
            }
        }
        return max;
    }

    private boolean duplicate(String tmp) {
        char last = tmp.charAt(tmp.length()-1);
        for(int i=0; i < tmp.length()-1; i++){
            if(last == tmp.charAt(i)) return true;
        }
        return false;
    }



    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i< nums.length; i++){
            for(int j=i+1; j< nums.length; j++){
                if(nums[i] + nums[j] == target) return new int[] {i,j};
            }
        }
        return null;
    }


}
