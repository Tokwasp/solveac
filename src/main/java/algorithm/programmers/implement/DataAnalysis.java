package algorithm.programmers.implement;

import java.util.ArrayList;
import java.util.List;

public class DataAnalysis {
    public int[][] solution(int[][] data, String ext, int valExt, String sortBy) {
        List<Data> datas = new ArrayList<>();

        for (int row = 0; row < data.length; row++) {
            int code = data[row][0];
            int date = data[row][1];
            int maxRemain = data[row][2];
            int curRemain = data[row][3];
            datas.add(new Data(code, date, maxRemain, curRemain));
        }

        List<Data> filteredDatas = filter(datas, ext, valExt);

        String sort = sortBy;
        return filteredDatas.stream()
                .sorted((a, b) -> a.getExt(sort) - b.getExt(sort))
                .map(a -> new int[]{a.code, a.date, a.maxRemain, a.curRemain})
                .toArray(int[][]::new);
    }

    static class Data {
        private int code;
        private int date;
        private int maxRemain;
        private int curRemain;

        public Data(int code, int date, int maxRemain, int curRemain) {
            this.code = code;
            this.date = date;
            this.maxRemain = maxRemain;
            this.curRemain = curRemain;
        }

        public int getExt(String ext) {
            if (ext.equals("code")) {
                return code;
            }

            if (ext.equals("date")) {
                return date;
            }

            if (ext.equals("maximum")) {
                return maxRemain;
            }

            if (ext.equals("remain")) {
                return curRemain;
            }
            return -1;
        }
    }

    private static List<Data> filter(List<Data> datas, String ext, int limitExt) {
        List<Data> newDatas = new ArrayList<>();
        for (Data data : datas) {
            if (data.getExt(ext) < limitExt) {
                newDatas.add(data);
            }
        }
        return newDatas;
    }
}
