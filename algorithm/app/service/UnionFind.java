package service;

/**
 * Created by davidchang on 4/11/17.
 */
public interface UnionFind {
    void union(int p, int q);
    boolean connected(int p, int q);
    int find(int p);
    int count();
}
