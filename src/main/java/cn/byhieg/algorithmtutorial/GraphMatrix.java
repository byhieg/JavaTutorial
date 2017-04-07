package cn.byhieg.algorithmtutorial;

import java.util.*;

/**
 * Created by shiqifeng on 2017/4/5.
 * Mail byhieg@gmail.com
 */
public class GraphMatrix {

    Weight[][] graph;
    boolean[] isVisited;

    private static final int UNREACH = Integer.MAX_VALUE >> 1;

    public GraphMatrix(Weight[][] graph) {
        this.graph = graph;
    }

    /**
     * 图的BFS，算法流程
     * 1. 首先将begin节点入队
     * 2. 然后判断队列是否为空,不为空，则出队一个元素，输出。
     * 3. 将出队的元素的所有相邻的元素且没有访问的都放进队列中，重复第二步
     * <p>
     * 广度优先遍历，从V0出发，访问V0的各个未曾访问的邻接点W1，W2，…,Wk;然后,依次从W1,W2,…,Wk出发访问各自未被访问的邻接点；
     *
     * @param begin
     */
    public void BFS(Integer begin) {
        isVisited = new boolean[graph.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(begin);
        isVisited[begin] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + "-->");
            for (int i = 0; i < graph[current].length; i++) {
                if (i == begin) {
                    continue;
                }
                if (graph[current][i].weight != UNREACH && !isVisited[i]) {
                    queue.offer(i);
                    isVisited[i] = true;
                }
            }
        }
        System.out.println("结束");
    }


    /**
     * 图的DFS算法，算法流程
     * 1. 从begin节点出发，输出begin节点。
     * 2. 循环遍历所有与begin节点相邻并且没有被访问的节点
     * 3. 找到一个节点，就以他为begin,递归调用DFS
     *
     * @param begin
     */
    public void DFS(Integer begin) {
        isVisited = new boolean[graph.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        doDFS(begin);
        System.out.println("结束");
    }

    /**
     * 假设给定图G的初态是所有顶点均未曾访问过。
     * 在G中任选一顶点v为初始出发点(源点)，则深度优先遍历可定义如下：
     * 首先访问出发点v，并将其标记为已访问过；然后依次从v出发搜索v的每个邻接点w。
     * 若w未曾访问过，则以w为新的出发点继续进行深度优先遍历，直至图中所有和源点v有路径相通的顶点(亦称为从源点可达的顶点)均已被访问为止。
     * 若此时图中仍有未访问的顶点，则另选一个尚未访问的顶点作为新的源点重复上述过程，直至图中所有顶点均已被访问为止。
     *
     * @param begin
     */
    private void doDFS(Integer begin) {
        isVisited[begin] = true;
        System.out.print(begin + "-->");
        for (int i = 0; i < graph[begin].length; i++) {
            if (graph[begin][i].weight != UNREACH && !isVisited[i]) {
                doDFS(i);
            }
        }
    }

    /**
     * dijkstra 从指定起点到指定终点的最短路
     * paths变量，key为节点，value为start到key节点的最短路径
     * values变量，key为节点，value为start到key节点的最小值
     * 1. dijkstra的核心思想，是广义搜索，先遍历所有与start相邻的节点，且没有找过的点
     * 2. 找出最短的节点k，然后以最短的节点为中间节点，如果start-k-i的距离短于start-i,则修改start到i的值，并且修改start到i的路径
     * 3. 然后在此基础上，继续从start节点去没有找过的点，重复1
     * @param start
     * @param end
     */
    public void dijkstra(int start, int end) {


        int n = graph.length;
        isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            isVisited[i] = false;
        }
        isVisited[start] = true;
        HashMap<Integer,String> paths = new HashMap<>();
        HashMap<Integer, Integer> values = new HashMap<>();
        for(int i = 0 ; i < n;i++) {
            if (i == start) {
                paths.put(start, start + "");
            }else{
                paths.put(i, start + "" + i + "");
            }
        }
        values.put(start,0);
        while (!values.containsKey(end)) {
            int k = -1;
            int min = UNREACH;
            for (int i = 0; i < n; i++) {
                if (!isVisited[i] && graph[start][i].weight < min) {
                    min = graph[start][i].weight;
                    k = i;
                }
            }
            values.put(k, min);
            isVisited[k] = true;

            for (int i = 0; i < n; i++) {
                if (!isVisited[i] && graph[start][k].weight + graph[k][i].weight < graph[start][i].weight) {
                    graph[start][i].weight = graph[start][k].weight + graph[k][i].weight;
                    String path = paths.get(k);
                    path += i + "";
                    paths.put(i,path);
                }
            }
        }
        System.out.println("从起始点 " + start + " 到终点 " + end + " 的最短路");
        String path = paths.get(end);
        for(int i = 0; i < path.length();i++) {
            System.out.print(path.charAt(i));
            if (i != path.length() - 1){
                System.out.print("-->");
            }
        }
        System.out.println("最短路径的值为 " + values.get(end));
    }


    public static class Weight {
        int weight;

        public Weight() {
            this(UNREACH);
        }

        public Weight(int weight) {
            this.weight = weight;
        }
    }
}
