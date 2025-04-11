package algorithm.programmers.enterprise.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileNameSort {
    private static final char MIN_NUMBER = '0';
    private static final char MAX_NUMBER = '9';
    private static final int MAX_NUMBER_SIZE = 5;

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();

        for(String file : files){
            int numberStartIndex = findNumberStartIndex(file);
            int numberEndIndex = findNumberEndIndex(file, numberStartIndex);

            String head = file.substring(0, numberStartIndex);
            String number = file.substring(numberStartIndex, numberEndIndex + 1);
            String tail = file.substring(numberEndIndex + 1);

            fileList.add(new File(head,number,tail));
        }
        Collections.sort(fileList);

        String[] results = new String[fileList.size()];
        for(int index = 0; index < fileList.size(); index++){
            results[index] = fileList.get(index).toString();
        }

        return results;
    }

    static int findNumberStartIndex(String file){
        for(int index = 0; index < file.length(); index++){
            char ch = file.charAt(index);
            if(MIN_NUMBER <= ch && ch <= MAX_NUMBER){
                return index;
            }
        }
        return -1;
    }

    static int findNumberEndIndex(String file, int startIndex){
        int numberCount = 0;
        for(int index = startIndex; index < file.length(); index++){
            char ch = file.charAt(index);
            if(MIN_NUMBER <= ch && ch <= MAX_NUMBER){
                numberCount++;
            }
            else{
                return index - 1;
            }

            if(numberCount >= MAX_NUMBER_SIZE){
                return index;
            }
        }
        return startIndex + numberCount - 1;
    }

    static class File implements Comparable<File>{
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail){
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public int compareTo(File file){
            int headCompare = this.head.toUpperCase()
                    .compareTo(file.head.toUpperCase());

            if(headCompare != 0) return headCompare;
            return Integer.compare(Integer.parseInt(this.number),
                    Integer.parseInt(file.number));
        }

        public String toString(){
            return head + number + tail;
        }
    }
}
