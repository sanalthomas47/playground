import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

/**
 * Undirected, unweighted graph backed by an adjacency-list map.
 * Vertices are automatically created when referenced in {@link #addEdge}.
 *
 * @param <T> the vertex data type; must implement {@code equals} and {@code hashCode}
 */
public class Graph<T> {
    private class Vertex<T> {
        T data;
        Vertex(T data){
            this.data = data;
        }

        @Override
        public boolean equals(Object v){
            return this.data.equals(((Vertex)v).data);
        }
        public int hashCode(){
            return data.hashCode();
        }
    }
    Map<Vertex<T>, LinkedList<Vertex<T>>> graphMap = new HashMap<Vertex<T>, LinkedList<Vertex<T>>>();
    /** Adds an isolated vertex with value {@code v} if it does not already exist. */
    public void addVertex(T v){
        graphMap.put(new Vertex<T>(v), new LinkedList<Vertex<T>>());
    }

    /**
     * Adds an undirected edge between {@code source} and {@code destination},
     * creating either vertex if it does not yet exist.
     */
    public void addEdge(T source, T destination){

        Vertex<T> sourceV = new Vertex<T>(source);
        Vertex<T> destinationV = new Vertex<T>(destination);
        if(!graphMap.containsKey(sourceV)){
            graphMap.put(sourceV, new LinkedList<Vertex<T>>());
        }
        if(!graphMap.containsKey(destinationV)){
            graphMap.put(destinationV, new LinkedList<Vertex<T>>());
        }

        graphMap.get(sourceV).add(destinationV);
        graphMap.get(destinationV).add(sourceV);
    }

    public void printGraph(){
        for(Map.Entry<Vertex<T>, LinkedList<Vertex<T>>> entry: graphMap.entrySet()){

            Vertex v = entry.getKey();
            LinkedList<Vertex<T>> edges = entry.getValue();

            System.out.print(v.data+ "->");
            for(Vertex<T> tVertex : edges){
                System.out.print(tVertex.data+"-->");
            }
            System.out.println();
        }
    }

    /**
     * Removes the vertex with value {@code val} and all edges connected to it.
     * Does nothing if the vertex does not exist.
     */
    public void removeVertex(T val){

        Vertex<T> tVertex = new Vertex<T>(val);

        if(graphMap.containsKey(tVertex)) {
            LinkedList<Vertex<T>> edges = graphMap.get(tVertex);

            if (edges.size() > 0) {
                for(Vertex<T> edge: edges){
                    graphMap.get(edge).remove(tVertex);
                }
                graphMap.remove(tVertex);
            }
        }
    }

    public static void main(String[] args){
        Graph<Integer> integerGraph = new Graph<Integer>();
        integerGraph.addVertex(10);
        integerGraph.addVertex(11);
        integerGraph.addVertex(12);
        integerGraph.addVertex(13);

        integerGraph.addEdge(10, 12);
        integerGraph.addEdge(11, 13);
        integerGraph.addEdge(10,14);
        integerGraph.addEdge(10,11);

        integerGraph.addEdge(13, 10);
        integerGraph.addEdge(14,11);
        integerGraph.addEdge(12,14);
        integerGraph.printGraph();
        System.out.println();
        integerGraph.removeVertex(10);
        integerGraph.printGraph();
    }
}
