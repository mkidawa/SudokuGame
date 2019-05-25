public class PascalTriangle {
    private int[][] triangleState;
    public void compute(int n) {
        if (n<= 0) {
            throw new IllegalArgumentException("Liczba wierszy musi być większa od zera");
        }
        triangleState = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (i == 0 || j == 0 || j == i + 1) {
                    triangleState[i][j] = 1;
                } else triangleState[i][j] = triangleState[i - 1][j - 1] + triangleState[i - 1][j];
            }
        }
    }
    public int[][] getTriangleState() {
        return triangleState;
    }
}