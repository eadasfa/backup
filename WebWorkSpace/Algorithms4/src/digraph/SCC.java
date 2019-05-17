package digraph;

public interface SCC {
	public boolean stronglyConnected(int v,int w);
	public int count();
	public int id(int v);
}
