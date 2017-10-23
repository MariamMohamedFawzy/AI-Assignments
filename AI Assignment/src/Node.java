
public class Node implements Comparable{
	
	
	Node parent;
	int depth;
	double cost;
	Object currentState;
	Object actionPerformed;
	
	public Node() {
		parent = null;
		depth = 0;
		cost = 0;
		currentState = null;
		actionPerformed = null;
	}
	
	public Node(Node parent, int depth, double cost, Object currentState,
			Object actionPerformed) {
		super();
		this.parent = parent;
		this.depth = depth;
		this.cost = cost;
		this.currentState = currentState;
		this.actionPerformed = actionPerformed;
	}

	@Override
	public int compareTo(Object o) {
		Node node = (Node) o;
		if (this.cost == node.cost) {
			return 0;
		} else if (this.cost > node.cost) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}
