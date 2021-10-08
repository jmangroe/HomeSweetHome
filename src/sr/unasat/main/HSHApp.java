package sr.unasat.main;

import sr.unasat.home.sweet.home.*;
import sr.unasat.special.purpose.datastructures.Stack;

import java.util.ArrayList;
import java.util.List;

public class HSHApp {
  public static void main(String[] args) {
    System.out.println("HOME SWEET HOME");
    WeightedGraph weightedGraph = new WeightedGraph();
    weightedGraph.addVertex("Paramaribo");//0
    weightedGraph.addVertex("Noord");//1
    weightedGraph.addVertex("Centrum");//2
    weightedGraph.addVertex("Zuid");//3
    weightedGraph.addVertex("Elite");//4
    weightedGraph.addVertex("Hoofdweg");//5
    weightedGraph.addVertex("Zijweg");//6
    weightedGraph.addVertex("2bhk");//7
    weightedGraph.addVertex("4bhk");//8
    weightedGraph.addVertex("6bhk");//9
    weightedGraph.addVertex("1washroom");//10
    weightedGraph.addVertex("2washrooms");//11



    weightedGraph.addEdge(0, 1, 100000);
    weightedGraph.addEdge(0, 2, 150000);
    weightedGraph.addEdge(0, 3, 125000);
    weightedGraph.addEdge(1, 4, 50000);
    weightedGraph.addEdge(1, 5, 30000);
    weightedGraph.addEdge(1, 6, 20000);
    weightedGraph.addEdge(2, 5, 70000);
    weightedGraph.addEdge(3, 5, 40000);
    weightedGraph.addEdge(3, 6, 20000);
    weightedGraph.addEdge(4, 9, 150000);
    weightedGraph.addEdge(5, 7, 75000);
    weightedGraph.addEdge(5, 8, 110000);
    weightedGraph.addEdge(6, 8, 72000);
    weightedGraph.addEdge(6, 9, 122000);
    weightedGraph.addEdge(7, 10, 500);
    weightedGraph.addEdge(8, 11, 900);
    weightedGraph.addEdge(9, 10, 500);
    weightedGraph.addEdge(9, 11, 900);

//    weightedGraph.displayAdjacencyMatrix();
//   weightedGraph.removeVertex(0);
//    weightedGraph.displayAdjacencyMatrix();

    //Breadth first search
//    System.out.println("Price according to the surrounding");
//    weightedGraph.bfs(); // breadth-first search
//    System.out.println(" ");

     //Depth-first search
//      weightedGraph.printAllPaths(0,11);
//        System.out.println();
////
//    System.out.println("Shortest path to the cheapest house");
//    weightedGraph.dijkstra();


    //Rapportage1 Meest bezochte huis per omgeving
//    List<Path> list = new ArrayList<>();
//    list.add(new Path(4, 9,"Mei"));//source4=Elite destination9=6bhk
//    list.add(new Path(4, 9,"Mei"));//
//    list.add(new Path(5, 8,"Februari"));//1//source5=hoofweg destination8=4bhk
//    list.add(new Path(5, 7,"Februari"));//2destination7=2bhk
//    list.add(new Path(5, 7,"Februari"));
//    list.add(new Path(5, 7,"Februari"));
//    list.add(new Path(6, 8,"September"));//source6=zijweg
//    weightedGraph.mostVisited(list);

    //Rapportage2 Bestseller per maand
//        List<Path> path = new ArrayList<>();
//        path.add(new Path(5, 7,"February"));//source5=hoodweg destination7=2bhk
//        path.add(new Path(6, 9,"August"));//source6=zijweg destination9=6bhk
//        path.add(new Path(6, 9,"August"));
//        path.add(new Path(6, 8,"September"));//destination8=4bhk
//        path.add(new Path(6, 9,"September"));
//        path.add(new Path(6, 8,"September"));
//        weightedGraph.bestsellers(path);

    //Rapportage3 Meest bezochte omgeving
//    ArrayList<Integer> arrayList=new ArrayList();
//    arrayList.add(4);
//    arrayList.add(3);
//    arrayList.add(3);
//    System.out.println(weightedGraph.mostVisitedNeighbourhood(arrayList));


//      weightedGraph.displayVertex(3);
//     weightedGraph.displayAllVertices();
//      weightedGraph.displayWeights();

  }
}
