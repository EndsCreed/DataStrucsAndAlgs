public class Node {
    private int data;
    private Node link;
    public Node() {
        data = 0;
		link = null; 
	}
    public void setData(int data) {
        this.data = data;
    }
	 public int getData() {
        return data;
    }
    public void setLink(Node link) {
        this.link = link;
    }
    public Node getLink() {
        return link;
    }
}