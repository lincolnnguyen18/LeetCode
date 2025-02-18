class Node {
  public Node prev;
  public Node next;
  public int value;

  public Node(int value) {
    this.value = value;
  }
}

class ExamRoom {
  public ExamRoom(int N) {
    this.N = N;
    join(head, tail);
  }

  public int seat() {
    if (head.next == tail) {
      Node node = new Node(0);
      join(head, node);
      join(node, tail);
      map.put(0, node);
      return 0;
    }

    int prevStudent = -1;
    int maxDistToClosest = 0;
    int val = 0;     // Inserted val
    Node pos = null; // Inserted position

    for (Node node = head; node != tail; node = node.next) {
      if (prevStudent == -1) {         // doesn't insert before
        maxDistToClosest = node.value; // Distance between it and the begining
        pos = node;
      } else if ((node.value - prevStudent) / 2 > maxDistToClosest) {
        maxDistToClosest = (node.value - prevStudent) / 2;
        val = (node.value + prevStudent) / 2;
        pos = node;
      }
      prevStudent = node.value;
    }

    if (N - 1 - tail.prev.value > maxDistToClosest) {
      pos = tail;
      val = N - 1;
    }

    Node insertedNode = new Node(val);
    join(pos.prev, insertedNode);
    join(insertedNode, pos);

    map.put(val, insertedNode);

    return val;
  }

  public void leave(int p) {
    Node removedNode = map.get(p);
    join(removedNode.prev, removedNode.next);
  }

  private int N;
  private Node head = new Node(-1);
  private Node tail = new Node(-1);
  private Map<Integer, Node> map = new HashMap<>(); // {p: student iterator}

  private void join(Node node1, Node node2) {
    node1.next = node2;
    node2.prev = node1;
  }

  private void remove(Node node) {
    join(node.prev, node.next);
  }
}
