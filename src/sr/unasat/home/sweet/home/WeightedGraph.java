package sr.unasat.home.sweet.home;

import sr.unasat.special.purpose.datastructures.Queue;
import java.util.*;
import sr.unasat.special.purpose.datastructures.Stack;


   public class WeightedGraph {
       private final int MAX_VERTS = 15;
       private final int INFINITY = 4000000;
       private Vertex vertexList[];
       private int adjacencyMatrix[][];
       private int nVertices;
       private final Queue queue;
       private final Stack stack;


       public WeightedGraph() {
           vertexList = new Vertex[MAX_VERTS];
           adjacencyMatrix = new int[MAX_VERTS][MAX_VERTS];
           nVertices = 0;
           for (int index1 = 0; index1 < MAX_VERTS; index1++) {
               for (int index2 = 0; index2 < MAX_VERTS; index2++) {
                   adjacencyMatrix[index1][index2] = INFINITY;
               }
           }
           stack = new Stack();
           queue = new Queue();
       }

       public void addVertex(String label) {
           vertexList[nVertices++] = new Vertex(label);
       }

       public void addEdge(int start, int end, int weight) {
           adjacencyMatrix[start][end] = weight;
       }

       public void removeVertex(int index) {
           if (index > nVertices) {
               System.out.println("Vertex not present!");
               return;
           } else {
               int i;

               while (index < nVertices) {

                   for (i = 0; i < nVertices; ++i) {
                       adjacencyMatrix[i][index] = adjacencyMatrix[i][index + 1];
                   }

                   for (i = 0; i < nVertices; ++i) {
                       adjacencyMatrix[index][i] = adjacencyMatrix[index + 1][i];
                   }
                   index++;
               }
               nVertices--;
           }
       }

       public void displayAdjacencyMatrix() {
           System.out.print("\n\n Adjacency Matrix:");
           // displaying the 2D array
           for (int i = 0; i < nVertices; ++i) {
               System.out.println();
               for (int j = 0; j < nVertices; ++j) {
                   System.out.print(" " + adjacencyMatrix[i][j]);
               }
           }
       }

       public int displayVertex(int v) {
           System.out.print(vertexList[v].label);
           if (adjacencyMatrix[0][v] < INFINITY) {
               System.out.println(" ,the price is Euro " + adjacencyMatrix[0][v]);

           }
           return v;
       }

       public void displayAllVertices() {
           for (Vertex vertex : vertexList) {
               System.out.println(vertex.label);
           }
       }

       public void displayWeights() {
           for (int index = 0; index < vertexList.length; index++) {
               for (int index1 = 0; index1 < vertexList.length; index1++) {
                   if (adjacencyMatrix[index][index1] < INFINITY) {
                       System.out.println(adjacencyMatrix[index][index1]);
                   }

               }

           }

       }
       // Breadth-first search
       public void bfs() {
           vertexList[0].wasVisited = true;
           queue.insert(0);
           int v2;
           while (!queue.isEmpty()) {
               int v1 = queue.remove();
               while ((v2 = getAdjUnvisitedVertexBfs(v1)) != -1) {
                   displayVertex(0);
                   System.out.print(" - ");
                   displayVertex(v2);
                   vertexList[v2].wasVisited = true;
                   queue.insert(v2);
               }
           }
           for (int index = 0; index < nVertices; index++) // reset flags
               vertexList[index].wasVisited = false;
       }

       public int getAdjUnvisitedVertexBfs(int v) {
           for (int index = 0; index < nVertices; index++)
               if (adjacencyMatrix[0][index] < INFINITY && !vertexList[index].wasVisited)
                   return index;
           return -1;
       }

       //Depth-first search
       public void printAllPaths(int source, int destanation) {
           boolean[] isVisited = new boolean[12];
           Stack stack = new Stack();
           stack.push(source);
           printAllPathsUtil(source, destanation, isVisited, stack);
       }

       public void printAllPathsUtil(int source, int destination, boolean[] isVisited, Stack stack) {
           if (source == destination) {
               System.out.println();
               List<Integer> list = new ArrayList<>();
               list.add(0);
               for (int index = 0; index < stack.size()-1; index++) {
                   if (index > 0 && stack.peekAtIndex(index) == 0) {
                       return;
                   } else {
                       System.out.print(vertexList[stack.peekAtIndex(index)].label + " - ");
                       int weight = adjacencyMatrix[stack.peekAtIndex(index)][stack.peekAtIndex(index+1)];
                       list.add(weight);

                   }
                   if (index==4){
                       int total = total(list);
                       System.out.println( total- INFINITY);
                   }
               }
               return;
           }

           isVisited[source] = true;

           for (Integer i = 0; i < nVertices; i++) {
               if (adjacencyMatrix[source][i] != INFINITY && !vertexList[i].wasVisited) {
                   stack.push(i);

                   printAllPathsUtil(i, destination, isVisited, stack);
                   stack.pop();
               }
           }
           isVisited[source] = false;
       }

       public int total(List<Integer> list) {
           int total = 0;
           for (int index = 0; index < list.size(); index++) {
               total += list.get(index);

           }
           return total;
       }



       public void dijkstra() {
           int nVertices = adjacencyMatrix[0].length;
           int[] smallestWeights = new int[nVertices];
           boolean[] added = new boolean[nVertices];

           for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
               smallestWeights[vertexIndex] = Integer.MAX_VALUE;
               added[vertexIndex] = false;
           }

           smallestWeights[0] = 0;

           int[] parents = new int[nVertices];

           parents[0] = -1;

           for (int i = 0; i < nVertices; i++) {

               int nearestVertex = -1;
               int smallestWeight = Integer.MAX_VALUE;
               for (int vertexIndex= 0; vertexIndex < nVertices; vertexIndex++) {
                   if (!added[vertexIndex] && smallestWeights[vertexIndex] < smallestWeight) {
                       nearestVertex = vertexIndex;
                       smallestWeight = smallestWeights[vertexIndex];
                   }
               }

               added[nearestVertex] = true;

               for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                   int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                   if (edgeDistance > 0 && ((smallestWeight + edgeDistance) < smallestWeights[vertexIndex])) {
                       parents[vertexIndex] = nearestVertex;
                       smallestWeights[vertexIndex] = smallestWeight + edgeDistance;
                   }
               }
           }
           printSolution(smallestWeights, parents);
       }

       private static void printSolution(int[] weights, int[] parents) {
           int nVertices = weights.length;
           System.out.print("Vertex\tWeight\tPath");
           int[] list = {weights[7], weights[8], weights[9],
                   weights[10], weights[11]};

           int min = getSmallest(list);

           for (int vertexIndex = 7; vertexIndex < nVertices; vertexIndex++) {
               if (weights[vertexIndex] == min) {
                   System.out.print("\n" + 0 + " -> ");
                   System.out.print(vertexIndex + " \t\t ");
                   System.out.print(weights[vertexIndex] + "\t\t");
                   printPath(vertexIndex, parents);
               }
           }
       }

       public static int getSmallest(int[] distance) {
           int temp;
           for (int i = 0; i < distance.length; i++) {
               for (int j = i + 1; j < distance.length; j++) {
                   if (distance[i] > distance[j]) {
                       temp = distance[i];
                       distance[i] = distance[j];
                       distance[j] = temp;
                   }
               }
           }
           return distance[0];
       }

       private static void printPath(int currentVertex, int[] parents) {
           if (currentVertex == -1) {
               return;
           }
           printPath(parents[currentVertex], parents);
           System.out.print(currentVertex + " ");
       }


       // Rapportage1 meest bezochte huis per omgeving
       public void mostVisited(List<Path> list) {
           List<Integer> destination4 = new ArrayList<>();//alle destinations die behoren bij source 4
           List<Integer> destination5 = new ArrayList<>();
           List<Integer> destination6 = new ArrayList<>();

           for (int i = 0; i < list.size(); i++) {
               if (list.get(i).source == 4) {
                   destination4.add(list.get(i).destination);

               } else if (list.get(i).source == 5) {
                   destination5.add(list.get(i).destination);

               } else if (list.get(i).source == 6) {
                   destination6.add(list.get(i).destination);

               } else {
                   System.out.println("Error!!!");
               }

           }
           System.out.println("In neighbourhood Elite, " + mostFrequents(destination4));
           System.out.println("In neighbourhood Hoofdweg, " + mostFrequents(destination5));
           System.out.println("In neighbourhood Zijweg, " + mostFrequents(destination6));

       }
       public String mostFrequents(List<Integer> list) {
           String display = "The most visited house is: ";
           if (list == null || list.isEmpty())
               return null;
           Map<Integer, Integer> map = new HashMap<Integer, Integer>();

           for (int i = 0; i < list.size(); i++) {

               Integer frequency = map.get(list.get(i));
               if (frequency == null) {
                   map.put(list.get(i), 1);
               } else {
                   map.put(list.get(i), frequency + 1);
               }
           }

           Integer mostCommonKey = null;
           int maxValue = -1;
           for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

               if (entry.getValue() > maxValue) {
                   mostCommonKey = entry.getKey();//key waarde
                   maxValue = entry.getValue();//maxValue is hoeveel keer de waarde voorkomt
               }
           }

           return display + vertexList[mostCommonKey].label;
       }

       public void bestsellers(List<Path> path) {
           List<Integer> destinationJan = new ArrayList<>();
           List<Integer> destinationFeb = new ArrayList<>();
           List<Integer> destinationMar = new ArrayList<>();
           List<Integer> destinationApr = new ArrayList<>();
           List<Integer> destinationMay = new ArrayList<>();
           List<Integer> destinationJun = new ArrayList<>();
           List<Integer> destinationJul = new ArrayList<>();
           List<Integer> destinationAug = new ArrayList<>();
           List<Integer> destinationSep = new ArrayList<>();
           List<Integer> destinationOct = new ArrayList<>();
           List<Integer> destinationNov = new ArrayList<>();
           List<Integer> destinationDec = new ArrayList<>();

           for (int i = 0; i < path.size(); i++) {
               switch (path.get(i).month) {
                   case "January":
                       destinationJan.add(path.get(i).destination);
                       break;
                   case "February":
                       destinationFeb.add(path.get(i).destination);
                       break;
                   case "March":
                       destinationMar.add(path.get(i).destination);
                       break;
                   case "April":
                       destinationApr.add(path.get(i).destination);
                       break;
                   case "May":
                       destinationMay.add(path.get(i).destination);
                       break;
                   case "June":
                       destinationJun.add(path.get(i).destination);
                       break;
                   case "July":
                       destinationJul.add(path.get(i).destination);
                       break;
                   case "August":
                       destinationAug.add(path.get(i).destination);
                       break;
                   case "September":
                       destinationSep.add(path.get(i).destination);
                       break;

                   case "October":
                       destinationOct.add(path.get(i).destination);
                       break;
                   case "November":
                       destinationNov.add(path.get(i).destination);
                       break;
                   case "December":
                       destinationDec.add(path.get(i).destination);
                       break;
                   default:
                       System.out.println("Error!!!");
                       break;
               }
           }
           if (mostSold(destinationJan) != null) {
               System.out.println("Bestseller in month January, " + vertexList[mostSold(destinationJan)].label);
           }
           if (mostSold(destinationFeb) != null) {
               System.out.println("Bestseller in month February, " + vertexList[mostSold(destinationFeb)].label);
           }
           if (mostSold(destinationMar) != null) {
               System.out.println("Bestseller in month March, " + vertexList[mostSold(destinationMar)].label);
           }
           if (mostSold(destinationApr) != null) {
               System.out.println("Bestseller in month April, " + vertexList[mostSold(destinationApr)].label);
           }
           if (mostSold(destinationMay) != null) {
               System.out.println("Bestseller in month May, " + vertexList[mostSold(destinationMay)].label);
           }
           if (mostSold(destinationJun) != null) {
               System.out.println("Bestseller in month June, " + vertexList[mostSold(destinationJun)].label);
           }
           if (mostSold(destinationJul) != null) {
               System.out.println("Bestseller in month July, " + vertexList[mostSold(destinationJul)].label);
           }
           if (mostSold(destinationAug) != null) {
               System.out.println("Bestseller in month August, " + vertexList[mostSold(destinationAug)].label);
           }
           if (mostSold(destinationSep) != null) {
               System.out.println("Bestseller in month September, " + vertexList[mostSold(destinationSep)].label);
           }
           if (mostSold(destinationOct) != null) {
               System.out.println("Bestseller in month October, " + vertexList[mostSold(destinationOct)].label);
           }
           if (mostSold(destinationNov) != null) {
               System.out.println("Bestseller in month November, " + vertexList[mostSold(destinationNov)].label);
           }
           if (mostSold(destinationDec) != null) {
               System.out.println("Bestseller in month December, " + vertexList[mostSold(destinationDec)].label);
           }

       }

       public Integer mostSold(List<Integer> list) {
           if (list == null || list.isEmpty())
               return null;
           Map<Integer, Integer> map = new HashMap<Integer, Integer>();

           for (int i = 0; i < list.size(); i++) {

               Integer frequency = map.get(list.get(i));
               if (frequency == null) {
                   map.put(list.get(i), 1);
               } else {
                   map.put(list.get(i), frequency + 1);
               }
           }

           Integer mostCommonKey = null;
           int maxValue = -1;
           for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

               if (entry.getValue() > maxValue) {
                   mostCommonKey = entry.getKey();//key waarde
                   maxValue = entry.getValue();//maxValue is hoeveel keer de waarde voorkomt
               }
           }

           return mostCommonKey;
       }


       //Rapportage 3 meest bezochte huis per omgeving
       public String mostVisitedNeighbourhood(ArrayList<Integer> arrayList) {
           String display = "The most visited neighbourhood is: ";
           if (arrayList == null || arrayList.isEmpty())
               return null;
           Map<Integer, Integer> map = new HashMap<Integer, Integer>();

           for (int i = 0; i < arrayList.size(); i++) {

               Integer frequency = map.get(arrayList.get(i));
               if (frequency == null) {
                   map.put(arrayList.get(i), 1);
               } else {
                   map.put(arrayList.get(i), frequency + 1);
               }
           }

           Integer mostCommonKey = null;
           int maxValue = -1;
           for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

               if (entry.getValue() > maxValue) {
                   mostCommonKey = entry.getKey();//key waarde
                   maxValue = entry.getValue();//maxValue is hoeveel keer de waarde voorkomt
               }
           }

           return display + vertexList[mostCommonKey].label;
       }


   }


















