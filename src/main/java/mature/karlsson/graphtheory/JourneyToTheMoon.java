package mature.karlsson.graphtheory;

import java.util.*;

public class JourneyToTheMoon {

	private Graph graph = new Graph();
	private Collection<Vertex> visitedVertexes = new ArrayList<>();
	private static int n;
	
	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int l = in.nextInt();
		
		JourneyToTheMoon task = new JourneyToTheMoon();
		//reading and processing the A B pairs
		for (int i=1; i<=l; i++){
			int a = in.nextInt();
			int b = in.nextInt();
			task.processInputPair(a, b);
		}
		
//		task.printGraph();
		
		DFTGraphEvaluationStrategy evaluationStrategy = task.new DFTGraphEvaluationStrategy();
		Collection<Collection<Vertex>> countries = evaluationStrategy.evaluateCountries();

					
//		System.out.println("\nCountries\n");
//		System.out.println(countries);
		
		long res = task.possibleSelectionsCount(countries);
		System.out.println(res);
		
		in.close();
	}
	
	private long possibleSelectionsCount(Collection<Collection<Vertex>> countries){
		//for some insane reason the task implies that all single numbers are separate countries
		//covering that below
		Collection<Vertex> dummy = new ArrayList<>();
		dummy.add(new Vertex(-1));
		for (int i=0; i<n; i++){
			if (!graph.vertexes.containsKey(i))
				countries.add(dummy);
		}
		
		//the formula is res = a*b+(a+b)*c+(a+b+c)*d...
		long res = 0;
		if (countries.size()>=2){
			long oldValueSum = 0;
			boolean first2Values = true;
			for (Iterator<Collection<Vertex>> iter = countries.iterator(); iter.hasNext(); ){
				if (first2Values){
					long a = iter.next().size();
					long b = iter.next().size();
					res += a*b;
					oldValueSum += (a+b);
					first2Values = false;
				} 
				else{
					long next = iter.next().size();
					res += oldValueSum*next;
					oldValueSum += next;
				}
			}
		}
		
		return res;
	}
	
	private void processInputPair(int a, int b){
		graph.addEdge(a, b);
	}
	
	private void printGraph(){
		System.out.println(graph.toString());
	}
	
	private class Vertex{
		
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
	
	private class Edge{
		
		//assuming that vertexA / vertexB pair is unique
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
			return vertexA.hashCode()*10000+vertexB.hashCode();
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
	
	private class Graph{
		
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
	
	private class DFTGraphEvaluationStrategy{
		
		private Collection<Vertex> visitedVertexes = new ArrayList<>();
		
		public Collection<Collection<Vertex>> evaluateCountries(){
			Collection<Collection<Vertex>> countries = new ArrayList<>();
			for (Vertex vertex : graph.getVertexesMap().values())
				if (!visitedVertexes.contains(vertex)){
					Collection<Vertex> country = new ArrayList<>();
					processVertex(vertex, country);
					countries.add(country);
				}
			
			return countries;
		}
		
		private void processVertex(Vertex vertex, Collection<Vertex> country){
			country.add(vertex);
			visitedVertexes.add(vertex);
			for (Edge edge : vertex.getEdges()){
				Vertex adjacentVertex = edge.getAdjacentVertex(vertex);
				if (!visitedVertexes.contains(adjacentVertex))
					processVertex(adjacentVertex, country);
			}
		}
		
	}
	
}
