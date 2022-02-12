public class DNode {
    private int data;
	private DNode pLink;
	private DNode nLink;

	public DNode() {
		data = 0;
		pLink = null;
		nLink = null;
	}

    public void setData(int data) { this.data = data; }
	public int getData() { return data; }
	public void setNextLink(DNode link) { this.nLink = link; }
	public DNode getNextLink() { return nLink; }
	public void setPrevLink(DNode link) { this.pLink = link; }
	public DNode getPrevLink() { return pLink; }
}
