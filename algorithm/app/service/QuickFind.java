package service;

/**
 * Created by davidchang on 4/11/17.
 */
public class QuickFind implements UnionFind{

    int[] conn1and2 = {0, 1, 1, 3, 4, 5, 6, 7, 8, 9};

    int[] conn1357 = {0, 1, 2, 1, 4, 1, 6, 1, 8, 9};

    int[] array;

    public QuickFind(int N) {
        array = new int[N];
        // initialize N
        for(int i = 0; i < N; i++){
            array[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        // union N
        for(int i = 0; i < array.length; i++){
            if(array[i] == array[q]){
                array[i] = array[p];
            }
        }
    }

    @Override
    public boolean connected(int p, int q) {
        /// find 1
        return array[p] == array[q];
    }

    @Override
    public int find(int p) {
        return array[p];
    }

    @Override
    public int count() {
        return 0;
    }
}
