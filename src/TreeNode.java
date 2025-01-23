public class TreeNode {
    private final String type;
    private final int depth;
    private final int evaluation;

    public TreeNode(String type, int depth, int evaluation) {
        this.type = type;
        this.depth = depth;
        this.evaluation = evaluation;
    }

    public String getType() { return type; }
    public int getDepth() { return depth; }
    public int getEvaluation() { return evaluation; }
}