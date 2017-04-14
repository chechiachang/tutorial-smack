package lecture;

import java.util.Scanner;

/**
 * Created by davidchang on 4/11/17.
 */
public class UnionFind1 implements UnionFind{
    public UnionFind1(int N) {
    }

    @Override
    public void union(int p, int q) {

    }

    @Override
    public boolean connected(int p, int q) {
        return false;
    }

    @Override
    public int find(int p) {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        UnionFind uf = new UnionFind1(N);
        while(in.hasNext()){

        }

    }
}
