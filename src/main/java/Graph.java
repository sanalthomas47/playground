import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

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
    public void addVertex(T v){
        graphMap.put(new Vertex<T>(v), new LinkedList<Vertex<T>>());
    }

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
