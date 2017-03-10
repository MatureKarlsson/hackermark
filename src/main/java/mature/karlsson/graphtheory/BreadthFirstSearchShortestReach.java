package mature.karlsson.graphtheory;

import java.util.*;

public class BreadthFirstSearchShortestReach {
	
	private static final int EDGE_WEIGHT = 6;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int q = in.nextInt(); //number of queries
		for (int i=1; i<=q; i++){ 
			int n = in.nextInt(); //number of vertices
			Graph graph = createGraph(n);
			
			int m = in.nextInt(); //number of edges
			for (int j=1; j<=m; j++){
				int u = in.nextInt(); //node u
				int v = in.nextInt(); //node v
				graph.addEdge(u, v);
			}
			
			int s = in.nextInt(); //starting vertex
			Map<Integer, Integer> distancesMap = new HashMap<>();
			distancesMap.put(s, 0); //distancesMap is also used to check if a vertex has been visited already, so putting our starting node to that Map
			
			Collection<Vertex> startLevelVerticies = new ArrayList<>(1);
			startLevelVerticies.add(graph.getVertexByLabel(s));
			evaluateDistances(startLevelVerticies, 1, distancesMap);
			
			printDistancesMap(distancesMap, graph);
		}	
		
		
		in.close();
	}
	
	private static void printDistancesMap(Map<Integer, Integer> distancesMap, Graph graph){
		Map<Integer, Vertex> verticies = graph.getVertexesMap();
		List<Integer> sortedLabels = new ArrayList<>(verticies.keySet());
		Collections.sort(sortedLabels);
		
		for (Integer label : sortedLabels)
			if (distancesMap.containsKey(label)){
				Integer distance = distancesMap.get(label);
				if (distance != 0)
					System.out.printf("%d ", distance);
			} else 
				System.out.print("-1 ");
		
		System.out.println();
	}
	
	private static void evaluateDistances(Collection<Vertex> currentLevelVerticies, int level, Map<Integer, Integer> distancesMap){
		Collection<Vertex> nextLevelVerticies = new ArrayList<>();
		for (Vertex currentLevelVertex : currentLevelVerticies)
			for (Edge edge : currentLevelVertex.getEdges()){
				Vertex adjacentVertex = edge.getAdjacentVertex(currentLevelVertex);
				if (!distancesMap.containsKey(adjacentVertex.getLabel())){
					nextLevelVerticies.add(adjacentVertex);
					distancesMap.put(adjacentVertex.getLabel(), level*EDGE_WEIGHT);
				}
			}
		
		//recursively evaluating distances for the next level verticies
		if (!nextLevelVerticies.isEmpty())
			evaluateDistances(nextLevelVerticies, level+1, distancesMap);
	}
	
	private static Graph createGraph(int vertexNum){
		Graph graph = new Graph();
		for (int i=1; i<=vertexNum; i++)
			graph.addVertex(new Vertex(i));
		
		return graph;
	}
	
	
	
//**************graph data structure classes*******************
	private static class Vertex{
		
		//it is assumed that the label is unique
		private int label;
		private Set<Edge> edges = new HashSet<>();
		
		public Vertex(int label){
			this.label = label;
		}
		
		public boolean addEdge(Edge edge){
			return edges.add(edge);
		}
		
		public boolean removeEdge(Edge edge){
			return edges.remove(edge);
		}
		
		public Set<Edge> getEdges(){
			return edges;
		}
		
		public int getLabel(){
			return label;
		}

		@Override
		public boolean equals(Object obj) {
			return ((obj != null)&&(obj instanceof Vertex)) ? label==((Vertex)obj).getLabel() : false;
		}

		@Override
		public int hashCode() {
			return label;
		}

		@Override
		public String toString() {
			return String.format("Vertex label %d", label);
		}
		
	}
	
	private static class Edge{
		
		//it is assumed that vertexA / vertexB pair is unique
		private Vertex vertexA, vertexB;
		
		public Edge(Vertex a, Vertex b){
			vertexA = a;
			vertexB = b;
		}
		
		public Vertex getVertexA(){
			return vertexA;
		}
		
		public Vertex getVertexB(){
			return vertexB;
		}
		
		public Vertex getAdjacentVertex(Vertex vertex){
			Vertex anotherVertex = null;
			
			if (vertexA.equals(vertex))
				anotherVertex = vertexB;
			else if (vertexB.equals(vertex))
				anotherVertex = vertexA;
			
			return anotherVertex;
		}

		@Override
		public int hashCode() {
			return (vertexA.getLabel() <= vertexB.getLabel())?(vertexA.hashCode()*10000+vertexB.hashCode()):(vertexB.hashCode()*10000+vertexA.hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (obj==null || !(obj instanceof Edge))
				return false;
			else{
				Edge edge = (Edge)obj;
				return (vertexA.equals(edge.getVertexA())&&vertexB.equals(edge.getVertexB()))||
						(vertexB.equals(edge.getVertexA())&&vertexA.equals(edge.getVertexB()));
			}
		}

		@Override
		public String toString() {
			return String.format("Edge: %s - %s", vertexA.toString(), vertexB.toString());
		}
		
	}
	
	private static class Graph{
		
		private Map<Integer, Vertex> vertexes = new HashMap<>();
		
		//adds vertex or returns reference to the existing one
		public Vertex addVertex(Vertex vertex){
			Vertex result = vertex;
			if (!vertexes.containsKey(vertex.getLabel()))
				vertexes.put(vertex.getLabel(), vertex);
			else
				result = vertexes.get(vertex.getLabel());
			
			return result;
		}
		
		public void removeVertex(Vertex vertex){
			vertexes.remove(vertex.getLabel());
		}
		
		//adds the vertexes if they do exist yet and then links them with an edge
		public Edge addEdge(Vertex a, Vertex b){
			Vertex vertexA = addVertex(a);
			Vertex vertexB = addVertex(b);
			
			Edge edge = new Edge(vertexA, vertexB);
			vertexA.addEdge(edge);
			vertexB.addEdge(edge);
			
			return edge;
		}
		
		//convenience method - creates vertexes from int and calls addEdge(Vertex a, Vertex b)
		public Edge addEdge(int a, int b){
			Vertex vertexA = new Vertex(a);
			Vertex vertexB = new Vertex(b);
			return addEdge(vertexA, vertexB);
		}
		
		public void removeEdge(Edge edge){
			edge.getVertexA().removeEdge(edge);
			edge.getVertexB().removeEdge(edge);
		}
		
		public Map<Integer, Vertex> getVertexesMap(){
			return vertexes;
		}
		
		public Vertex getVertexByLabel(int label){
			return vertexes.get(label);
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("Graph\n");
			for (Vertex vertex : vertexes.values()){
				sb.append(vertex.toString()).append("\n");
				for (Edge edge : vertex.getEdges())
					sb.append("    ").append(edge.toString()).append("\n");
			}
			
			return sb.toString();
		}
		
	}

}
