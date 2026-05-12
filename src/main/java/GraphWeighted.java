import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Undirected weighted graph with Dijkstra's shortest-path algorithm.
 * Uses an adjacency-list map where each edge stores a destination vertex and an integer weight.
 *
 * @param <T> the vertex data type; must implement {@code equals} and {@code hashCode}
 */
public class GraphWeighted<T> {

    private class Vertex<T> {
        T data;

        Vertex(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object v) {
            return this.data.equals(((Vertex) v).data);
        }

        public int hashCode() {
            return data.hashCode();
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    '}';
        }
    }

    private class Edge{
        Vertex<T> destination;
        int weight;

        Edge(Vertex<T> destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "destination=" + destination +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight &&
                    destination.equals(edge.destination);
        }

        @Override
        public int hashCode() {
            return Objects.hash(destination, weight);
        }
    }

    Map<Vertex<T>, LinkedList<Edge>> graphMap = new HashMap<>();

    /** Adds an isolated vertex with value {@code v} if it does not already exist. */
    public void addVertex(T v) {
        graphMap.put(new Vertex<T>(v), new LinkedList<Edge>());
    }

    /**
     * Adds an undirected weighted edge between {@code source} and {@code destination}.
     * Creates either vertex if it does not yet exist.
     *
     * @param weight non-negative edge cost
     */
    public void addEdge(T source, T destination, int weight) {

        Vertex<T> sourceV = new Vertex<T>(source);
        Vertex<T> destinationV = new Vertex<T>(destination);

        if (!graphMap.containsKey(sourceV)) {
            graphMap.put(sourceV, new LinkedList<Edge>());
        }
        if (!graphMap.containsKey(destinationV)) {
            graphMap.put(destinationV, new LinkedList<Edge>());
        }

        graphMap.get(sourceV).add(new Edge(destinationV, weight));
        graphMap.get(destinationV).add(new Edge(sourceV, weight));
    }

    public void printGraph() {
        for (Map.Entry<Vertex<T>, LinkedList<Edge>> entry : graphMap.entrySet()) {

            Vertex v = entry.getKey();
            LinkedList<Edge> edges = entry.getValue();

            System.out.print(v.data + "->");
            for (Edge edge : edges) {
                System.out.print(edge.destination + "["+edge.weight+"]"+"-->");
            }
            System.out.println();
        }
    }

    /**
     * Removes the vertex with value {@code val} and all edges connected to it.
     * Does nothing if the vertex does not exist.
     */
    public void removeVertex(T val) {

        Vertex<T> tVertex = new Vertex<T>(val);

        if (graphMap.containsKey(tVertex)) {
            LinkedList<Edge> edges = graphMap.get(tVertex);

            if (edges.size() > 0) {
                for (Edge edge : edges) {
                    graphMap.get(edge.destination).remove(new Edge(tVertex,edge.weight));
                }
                graphMap.remove(tVertex);
            }
        }
    }

    /**
     * Runs Dijkstra's algorithm from the vertex with value {@code t} and prints
     * the shortest distance from that source to every other vertex.
     *
     * @param t source vertex value
     */
    public void shortestPath(T t){

        Vertex<T> source = new Vertex<>(t);

        final Map<Vertex<T>, Integer> distance = new HashMap<>();

        for(Vertex<T> vertex: graphMap.keySet()){
            distance.put(vertex, Integer.MAX_VALUE);
        }

        distance.put(source,0);

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue(
                new Comparator<Vertex<T>>(){
                    public int compare(Vertex<T> first, Vertex<T> second){
                        return distance.get(first) - distance.get(second);
                    }
                }
        );

        priorityQueue.add(source);

        while(priorityQueue.size() > 0){

            Vertex<T> vertex = priorityQueue.poll();

            List<Edge> adjacentList = graphMap.get(vertex);

            for(Edge e: adjacentList){

                Vertex<T> neighbour = e.destination;
                int weight = e.weight;

                int sum = distance.get(vertex)+weight;
                if(sum < distance.get(neighbour)){
                    distance.put(neighbour,sum);
                    priorityQueue.add(neighbour);
                }
            }

        }

        for(Map.Entry<Vertex<T>, Integer> entry: distance.entrySet()){
            System.out.println(entry.getKey() +"-->"+ entry.getValue());
        }

    }

    public static void main(String[] args) {
        GraphWeighted<Integer> integerGraph = new GraphWeighted<Integer>();
        integerGraph.addVertex(10);
        integerGraph.addVertex(11);
        integerGraph.addVertex(12);
        integerGraph.addVertex(13);

        integerGraph.addEdge(10, 12, 100);
        integerGraph.addEdge(11, 13, 110);
        integerGraph.addEdge(10, 14, 120);
        integerGraph.addEdge(10, 11, 80);

        integerGraph.addEdge(13, 10, 130);
        integerGraph.addEdge(14, 11, 90);
        integerGraph.addEdge(12, 14, 110);


        integerGraph.addEdge(11, 12, 100);
        integerGraph.addEdge(14, 13, 110);
        integerGraph.addEdge(12, 14, 120);
        integerGraph.addEdge(13, 11, 80);


        integerGraph.printGraph();
        System.out.println();
        //integerGraph.printGraph();

        integerGraph.shortestPath(12);
    }
}
