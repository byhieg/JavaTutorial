package cn.byhieg.algorithmtutorialtest;

import cn.byhieg.algorithmtutorial.GraphMatrix;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/4/5.
 * Mail byhieg@gmail.com
 */
public class GraphMatrixTest extends TestCase {

    GraphMatrix graphMatrix;
    GraphMatrix.Weight[][] weights;
    public void setUp() throws Exception {
        super.setUp();
        weights = new GraphMatrix.Weight[5][5];
        for (int i = 0 ; i < weights.length;i++)
            for (int j = 0 ; j < weights[i].length;j++){
                weights[i][j] = new GraphMatrix.Weight();
                weights[j][i] = new GraphMatrix.Weight();
            }

        weights[0][1] = new GraphMatrix.Weight(100);
        weights[1][2] = new GraphMatrix.Weight(10);
        weights[2][3] = new GraphMatrix.Weight(20);
        weights[3][1] = new GraphMatrix.Weight(25);
        weights[2][4] = new GraphMatrix.Weight(20);

        weights[1][0] = new GraphMatrix.Weight(100);
        weights[2][1] = new GraphMatrix.Weight(10);
        weights[3][2] = new GraphMatrix.Weight(20);
        weights[1][3] = new GraphMatrix.Weight(25);
        weights[4][2] = new GraphMatrix.Weight(20);


        graphMatrix = new GraphMatrix(weights);
    }

    public void tearDown() throws Exception {

    }

    public void testBFS() throws Exception {
        graphMatrix.BFS(4);

    }

    public void testDFS() throws Exception {
        graphMatrix.DFS(0);
    }

    public void testDijkstra() throws Exception {
        graphMatrix.dijkstra(1,2);
    }

}