package algorithm.baekjoon.implement;

import java.io.*;
import java.util.*;

public class OverTake2002 {
    static class Car{
        private int order;
        private String carName;

        public Car(int order, String carName) {
            this.order = order;
            this.carName = carName;
        }

        public Car(String carName) {
            this.carName = carName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return Objects.equals(carName, car.carName);
        }

        public int getOrder() {
            return order;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Car> cars = new ArrayList<>();

        //터널에 들어갈떄
        for(int i=0; i<n; i++){
            String carName = br.readLine();
            cars.add(new Car(i,carName));
        }

        int overTake = 0;

        //터널에서 나올때 검사
        for(int i=0; i<n; i++){
            String carName = br.readLine();
            int index = cars.indexOf(new Car(carName));
            int min = cars.stream().mapToInt(Car::getOrder).min().getAsInt();
            if(cars.get(index).order >  min) overTake++;
            cars.remove(index);
        }
        System.out.print(overTake);
    }
}
