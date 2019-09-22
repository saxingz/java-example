package org.saxing.a.algorithm2;

/**
 * leetcode 547
 *
 * @author saxing 2019/9/15 23:19
 */
public class FriendCircles {

    public static void main(String[] args) {
        int[][] M = new int[][]
                {{1,1,0},
                {1,1,0},
                {0,0,1}};

        System.out.println(new FriendCircles().findCircleNum(M));
    }

    class UnionFind547_2 {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind547_2(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }

        public int count() {
            return count;
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind547_2 uf = new UnionFind547_2(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }

        return uf.count();
    }

}
