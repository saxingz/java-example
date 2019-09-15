package org.saxing.a.algorithm2;

/**
 * leetcode 200
 */
public class NumberofIslands {


    class UnionFind200{
        int[] father;
        int m, n;
        int count  = 0;
        UnionFind200(char[][] grid){
            m = grid.length;
            n = grid[0].length;
            father = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1'){
                        int id = i * n + j;
                        father[id] = id;
                        count++;
                    }
                }
            }
        }

        public int find(int node){
            if (father[node] == node){
                return node;
            }
            father[node] = find(father[node]);
            return father[node];
        }

        public void union(int node1, int node2){
            int find1 = find(node1);
            int find2 = find(node2);
            if (find1 != find2){
                father[find1] = find2;
                count--;
            }
        }
    }

}
