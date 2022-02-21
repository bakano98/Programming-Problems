// added predecessor function for practice + added comments for easy reading
public class AVLTree {
    public Node root;

    // a new AVL tree starts with an empty root
    public AVLTree() {
        this.root = null;
    }

    // returns the height of the current node
    public int height(Node current) {
        // by definition, if empty, height = -1
        return current == null ? -1 : current.height;
    }

    // returns the size of the current node
    public int size(Node current) {
        // by definition, if empty, size = 0
        return current == null ? 0 : current.size;
    }

    // to insert a new node into the AVL tree
    public void insert(Team key) {
        root = insert(root, key);
    }

    public Node insert(Node current, Team key) {
        if (current == null) {
            // just return a new node to be inserted
            return new Node(key);
        }
        if (key.compareTo(current.key) == 1) {
            // bigger than, so go left
            current.right = insert(current.right, key);
            // setting the parent pointer
            current.right.parent = current;
        } else {
            // smaller than, so go left
            current.left = insert(current.left, key);
            // setting the parent pointer
            current.left.parent = current;
        }

        // update node info
        updateNode(current);
        // return balanced node
        return balance(current);
    }

    // to delete a node from the AVL tree
    public void delete(Team key) {
        root = delete(root, key);
    }

    public Node delete(Node current, Team key) {
        if (current == null) {
            // nothing to delete
            current = null;
            return current;
        }
        if (key.compareTo(current.key) == -1) {
            // smaller than, so recurse left
            current.left = delete(current.left, key);
        } else if (key.compareTo(current.key) == 1) {
            // bigger than, so recurse right
            current.right = delete(current.right, key);
        } else {
            // otherwise this is the node we want to delete
            // # of child determines how to delete
            if (current.left == null && current.right == null) {
                // no child, so set to null and return
                current = null;
                return current;
            } else if (current.left == null && current.right != null) {
                // right child only
                current.right.parent = current.parent;
                current = current.right;
            } else if (current.left != null && current.right == null) {
                // left child only
                current.left.parent = current.left;
                current = current.left;
            } else {
                // two children
                Team temp = successor(key);
                current.key = temp;
                current.right = delete(current.right, temp);
            }
        }
        // update node info
        updateNode(current);
        // return balanced node
        return balance(current);
    }

    // searches for the key, returns null if it can't be found
    public Team search(Team key) {
        Node node = search(root, key);
        return node == null ? null : node.key;
    }

    public Node search(Node current, Team key) {
        if (current == null) {
            // key cannot be found
            return null;
        } else if (key.compareTo(current.key) == 0) {
            // current.key matches with key
            return current;
        } else if (key.compareTo(current.key) == 1) {
            // key is greater than current key
            return search(current.right, key);
        } else {
            // key is lesser than current key
            return search(current.left, key);
        }
    }

    // finds the minimum key in the tree
    public Team findMin(Node current) {
        return current.left == null ? current.key : findMin(current.left);
    }

    // finds the maximum key in the tree
    public Team findMax(Node current) {
        return current.right == null ? current.key : findMax(current.right);
    }

    // get the successor of this key
    public Team successor(Team key) {
        Node node = search(root, key);
        return node == null ? null : successor(node);
    }

    // finds the next node that is exactly bigger than the current
    // aids in removal of any nodes
    public Team successor(Node current) {
        if (current.right != null) {
            // perform findMin() on right child if it exists
            return findMin(current.right);
        } else {
            // otherwise head upstream and find the first parent bigger than itself
            Node parent = current.parent;
            Node temp = current;

            while ((parent != null) && (parent.key).compareTo(current.key) == -1) {
                temp = parent;
                // move upstream
                parent = temp.parent;
            }
            // if there is nothing bigger than itself, then return null
            return parent == null ? null : parent.key;
        }
    }

    // find the predecessor of this key
    public Team predecessor(Team key) {
        Node node = search(root, key);
        return node == null ? null : predecessor(node);
    }

    // finds the next node that is exactly smaller than this node
    public Team predecessor(Node current) {
        if (current.left != null) {
            // perform findMax() on left child
            return findMax(current.left);
        } else {
            // otherwise, head upstream and find the first parent smaller than itself
            Node parent = current.parent;
            Node temp = current;

            while ((parent != null) && (parent.key).compareTo(current.key) == -1) {
                temp = parent;
                parent = temp.parent;
            }
            return parent == null ? null : parent.key;
        }
    }

    // according to lecture notes' pseudocode
    // equivalent of turning clockwise
    public Node leftRotate(Node current) {
        // pre-req: current != null and current.right != null
        // otherwise there's nothing to rotate
        if (current == null || current.right == null) {
            return null;
        }
        // current = T, child = w
        Node child = current.right;
        child.parent = current.parent;
        if (current.parent == null) {
            // if current.parent = null, then it is the root
            // so set root to child since they've swapped position
            root = child;
        }
        current.parent = child;
        current.right = child.left;
        if (child.left != null) {
            child.left.parent = current;
        }
        child.left = current;
        // updating T, then w
        updateNode(current);
        updateNode(child);

        return child;
    }

    // mirror of leftRotate
    public Node rightRotate(Node current) {
        // pre-req: current != null and current.left != null
        // otherwise there's nothing to rotate
        if (current == null || current.left == null) {
            return null;
        }
        // current = T, child = w
        Node child = current.left;
        child.parent = current.parent;
        if (current.parent == null) {
            // if current.parent = null, then it is the root
            // so set root to child since they've swapped position
            root = child;
        }
        current.parent = child;
        current.left = child.right;
        if (child.right != null) {
            child.right.parent = current;
        }
        child.right = current;
        // updating T, then w
        updateNode(current);
        updateNode(child);

        return child;
    }

    // call balance to do all necessary rotations
    public Node balance(Node current) {
        if (bf(current) == 2) {
            if (bf(current.left) == -1) {
                // left right case, execute leftRotate then rightRotate
                current.left = leftRotate(current.left);
            }
            // left left case, rightRotate only
            current = rightRotate(current);
        }
        else if (bf(current) == -2) {
            if (bf(current.right) == 1) {
                // right left case, rightRotate then leftRotate
                current.right = rightRotate(current.right);
            }
            // right right case, leftRotate
            current = leftRotate(current);
        }

        return current;
    }

    // gets the rank of this key
    public int rank(Team key) {
        return rank(root, key);
    }

    // follows the pseudocode in tutorial 7
    // rank of the node. rank = how many nodes are smaller than current
    public int rank(Node current, Team key) {
        if (current == null) {
            return 0;
        } else if (key.compareTo(current.key) == -1) {
            // key is smaller than current, ignore and recurse
            return rank(current.left, key);
        } else if (key.compareTo(current.key) == 1) {
            // key is bigger than the current node
            return size(current.left) + 1 + rank(current.right, key);
        } else {
            // otherwise we found the node
            return size(current.left) + 1;
        }
    }

    // gets the balance factor of the current node
    public int bf(Node current) {
        return height(current.left) - height(current.right);
    }

    // updates the details of the node
    // according to the lecture nodes
    public void updateNode(Node current) {
        current.height = Math.max(height(current.left), height(current.right)) + 1;
        current.size = size(current.left) + size(current.right) + 1;
    }
}
