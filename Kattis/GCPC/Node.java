public class Node {
    // node contains a Team key, which has:
    // 1) a team's number
    // 2) # of problems solved by that team
    // 3) total penalty earned by the team
    Team key;
    int height, size;
    // because each Node should have a L/R child and a parent
    Node left, right, parent;
  
    public Node(Team key) {
      this.key = key;
      // all Nodes start with height 0. null = -1
      this.height = 0;
      this.size = 1;
      this.left = null;
      this.right = null;
      this.parent = null;
    }
  }