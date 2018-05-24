package lab4.service;

import java.util.Arrays;
import java.util.Random;

public class SchedulerService {

    private int[] cpu;
    private int[] task;
    private int[][] startMatrix;
    private int[][] matrix;

    public SchedulerService(int n) {
        this.cpu = new int[n];
        this.task = new int[n];
        this.startMatrix = new int[n][];
        this.matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            cpu[i] = i;
            task[i] = i;
        }
        this.randMatrix();
        for (int i = 0; i < n; i++)
            startMatrix[i] = matrix[i].clone();

        //this.startMatrix = new int[][]{{0, 1, 0, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 1, 1, 1, 0}};
        //this.matrix = new int[][]{{0, 1, 0, 1, 0}, {1, 0, 1, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 1, 1, 1, 0}};
    }

    private void randMatrix() {
        Random r = new Random();
        for (int i = 0; i < matrix.length; i++)
            matrix[i] = r.ints(0, 2).limit(matrix.length).toArray();
    }

    public void doScheduling() {
        for (int itr = 0; itr < matrix.length; itr++) {
            // find row with min sum
            int minRowIndex = itr;
            int minRowSum = Integer.MAX_VALUE;
            for (int i = itr; i < matrix.length; i++) {
                int sum = 0;
                for (int j = itr; j < matrix.length; j++)
                    sum += matrix[i][j];
                if (sum < minRowSum) {
                    minRowSum = sum;
                    minRowIndex = i;
                }
            }

            // swap first row with min sum row
            this.swapRows(itr, minRowIndex, this.matrix);
            this.swap(itr, minRowIndex, this.cpu);


            // find col that starts with 1 with min sum
            int minColIndex = itr;
            int minColSum = Integer.MAX_VALUE;
            for (int i = itr; i < matrix.length; i++) {
                if (matrix[itr][i] == 1) {
                    int sum = 0;
                    for (int j = itr; j < matrix.length; j++) {
                        sum += matrix[j][i];
                    }
                    if (sum < minColSum) {
                        minColSum = sum;
                        minColIndex = i;
                    }
                }
            }

            // swap first col with min sum col that starts with 1
            this.swapCols(itr, minColIndex, this.matrix);
            this.swap(itr, minColIndex, this.task);
            for (int i = itr + 1 ; i < matrix.length; i++) {
                matrix[itr][i] = 0;
                matrix[i][itr] = 0;
            }
        }
    }

    private void swapRows(int i, int j, int[][] matrix) {
        int[] buf = matrix[i].clone();
        matrix[i] = matrix[j].clone();
        matrix[j] = buf;
    }

    private void swapCols(int i, int j, int[][] matrix) {
        for (int k = 0; k < matrix.length; k++) {
            this.swap(i, j, matrix[k]);
        }
    }

    private void swap(int i, int j, int[] arr) {
        int buf = arr[i];
        arr[i] = arr[j];
        arr[j] = buf;
    }

    public int[] getCpu() {
        return cpu;
    }

    public int[] getTask() {
        return task;
    }

    public int[][] getStartMatrix() {
        return startMatrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
