package cn.byhieg.algorithmtutorial;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shiqifeng on 2017/4/5.
 * Mail byhieg@gmail.com
 */
public class GraphMatrix {

    Weight[][] graph;
    boolean[] isVisited;

    private static final int UNREACH = Integer.MAX_VALUE - 1000;

    public GraphMatrix(Weight[][] graph) {
        this.graph = graph;
    }

    /**
     * 图的BFS，算法流程
     * 1. 首先将begin节点入队
     * 2. 然后判断队列是否为空,不为空，则出队一个元素，输出。
     * 3. 将出队的元素的所有相邻的元素且没有访问的都放进队列中，重复第二步
     *
     * 广度优先遍历，从V0出发，访问V0的各个未曾访问的邻接点W1，W2，…,Wk;然后,依次从W1,W2,…,Wk出发访问各自未被访问的邻接点；
     * @param begin
     */
    public void BFS(Integer begin) {
        isVisited = new boolean[graph.length];
        for (int i = 0 ; i < isVisited.length;i++) {
            isVisited[i] = false;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(begin);
        isVisited[begin] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + "-->");
            for (int i = 0 ; i < graph[current].length;i++) {
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
     * @param begin
     */
    public void DFS(Integer begin) {
        isVisited = new boolean[graph.length];
        for (int i = 0 ; i < isVisited.length;i++) {
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
     * @param begin
     */
    private void doDFS(Integer begin) {
        isVisited[begin] = true;
        System.out.print(begin + "-->");
        for (int i = 0 ; i < graph[begin].length;i++) {
            if (graph[begin][i].weight != UNREACH && !isVisited[i]) {
                doDFS(i);
            }
        }
    }

    public void dijkstra(int start) {
        //接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
        //返回一个int[] 数组，表示从start到它的最短路径长度
        int n = graph.length;        //顶点个数
        int[] shortPath = new int[n];    //存放从start到其他各点的最短路径
        String[] path = new String[n]; //存放从start到其他各点的最短路径的字符串表示
        for (int i = 0; i < n; i++)
            path[i] = new String(start + "-->" + i);
        int[] visited = new int[n];   //标记当前该顶点的最短路径是否已经求出,1表示已求出

        //初始化，第一个顶点求出
        shortPath[start] = 0;
        visited[start] = 1;

        for (int count = 1; count <= n - 1; count++)  //要加入n-1个顶点
        {

            int k = -1;    //选出一个距离初始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && graph[start][i].weight < dmin) {
                    dmin = graph[start][i].weight;

                    k = i;
                }

            }
            //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;

            visited[k] = 1;

            //以k为中间点，修正从start到未访问各点的距离
            for (int i = 0; i < n; i++) {                 // System.out.println("k="+k);
                if (visited[i] == 0 && graph[start][k].weight + graph[k][i].weight < graph[start][i].weight) {
                    graph[start][i].weight = graph[start][k].weight + graph[k][i].weight;
                    path[i] = path[k] + "-->" + i;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("从" + start + "出发到" + i + "的最短路径为：" + path[i] + " 距离为" + shortPath[i]);
        }
    }






    public static class Weight{
        int weight;
        public Weight(){
            this(UNREACH);
        }

        public Weight(int weight) {
            this.weight = weight;
        }
    }
}
