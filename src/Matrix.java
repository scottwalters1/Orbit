/**
 * A simple m x n matrix class. 
 *
 * TODO All of the methods currently just return default values. You need to make them match the Javadoc comments. 
 *
 * @author Scott Walters
 * @version Apr. 2023
 */

public class Matrix {

  private int m, n;
  private double[][] M;

  public Matrix(double[][] array) {
    M = array;
    m = array.length;
    n = array[0].length;
  }

  /**
   * @return The number of columns in the matrix. X
   */
  public int nCols() { return m; }

  /**
   * @return the number of rows. X
   */
  public int nRows() { return n; }

  /**
   * @param i
   * @param j
   * @return The entry at row i column j. X
   */
  public double entry(int i, int j) {
    return M[i][j];
  }

  /**
   * Computes the dot product of this matrix with the parameter that. (Return value is this . that) TENTATIVE X
   * Recall that the dot product is the typical matrix multiplication.
   * @param that The matrix to apply this matrix to.
   * @throws BadDimensionException If this.nCols() != that.nRows() because the dot product is not defined
   * @return The dot product of this matrix with that.
   */
  public Matrix dot(Matrix that) throws UndefinedMatrixOpException {
    if (this.nCols() != that.nRows()) {
      throw new UndefinedMatrixOpException("Bad Dimension", this, that);
    }

    int RnCols = this.nCols();
    int RnRows = that.nRows();
    double[][] result = new double[RnCols][RnRows];
    for (int i = 0; i < RnCols; i++) {
      for (int j = 0; j < RnRows; j++) {
        for (int k = 0; k < that.nCols(); k++) {
          result[i][j] += this.entry(i, k) * that.entry(k, j);
        }
      }
    }
    Matrix matrixResult = new Matrix(result);
    return matrixResult;
  }

  /**
   * Add this matrix to that and returns the result. (Return value is this + that) X
   * @param that the matrix to add this matrix to.
   * @throws BadDimensionException If the dimension of the two matrices are not identical.
   * @return The sum of the this and that.
   */
  public Matrix plus(Matrix that) throws UndefinedMatrixOpException {

    if ((this.nCols() != that.nCols()) || this.nRows() != that.nRows()) {
      throw new UndefinedMatrixOpException("Bad Dimension", this, that);
    }
    double[][] result = new double[this.nCols()][this.nRows()];
    for (int i = 0; i < this.nCols(); i++) {
      for (int j = 0; j < this.nRows(); j++) {
        result[i][j] = this.entry(i, j) + that.entry(i,j);
      }
    }
    Matrix resultMatrix = new Matrix(result);
    return resultMatrix;
  }

  /**
   * @param theta The rotation angle.
   * @return The homogeneous rotation matrix for a given value for theta.
   */
  public static Matrix rotationH2D(double theta) {
    double[][] R = {{Math.cos(theta), -Math.sin(theta), 0}, {Math.sin(theta), Math.cos(theta), 0}, {0, 0, 1}};
    return new Matrix(R);
  }

  /**
   * @param tx The amount to translate in the x direction.
   * @param ty The amount to translate in the y direction.
   * @return The matrix representing a translation of tx, ty.
   */
  public static Matrix translationH2D(double tx, double ty) {
    double[][] t = {{1, 0, tx}, {0, 1, ty}, {0, 0, 1}};
    return new Matrix(t);
  }

  /**
   * @param x The x coordinate
   * @param y The y coordinate
   * @return The column matrix representing in homogeneous coordinates the point (x, y).
   */
  public static Matrix vectorH2D(double x, double y) {
    double[][] v = {{x}, {y}, {1}};
    return new Matrix(v);
  }

  /**
   * @param n The dimension of the matrix. Recall that the identity matrix has 1's for any entry that is in the same row index as its column index, 0's everywhere else.
   * @return the nxn identity matrix
   */
  public static Matrix identity(int n) {
    double[][] d = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          d[i][j] = 1;
        } else {
          d[i][j] = 0;
        }
      }
    }
    return new Matrix(d);
  }

  /**
   * Computes the mxn identity matrix which has 1's for every entry at the same row and column index and  X
   * 0 for all other entries.
   * @param m
   * @param n
   * @return the mxn identity matrix.
   */
  public static Matrix identity(int m, int n) {
    double[][] d = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          d[i][j] = 1;
        } else {
          d[i][j] = 0;
        }
      }
    }
    return new Matrix(d);
  }

  /**
   * A little helpful toString() in case you want to print your matrix to System.out
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sb.append(M[i][j]);
        sb.append('\t');
      }
      sb.append('\n');
    }
    return sb.toString();
  }
  public static void main(String[] args) throws UndefinedMatrixOpException {
    double[][] d = new double[3][5];
    for (int i = 0; i < d.length; i++) {
      for (int j = 0; j < d[i].length; j++) {
        d[i][j] = i;
      }
    }
    Matrix m = new Matrix(d);

    double[][] d1 = new double[5][3];
    for (int i = 0; i < d1.length; i++) {
      for (int j = 0; j < d1[i].length; j++) {
        d1[i][j] = 5;
      }
    }
    Matrix m1 = new Matrix(d1);
//
//    System.out.println(m.toString());
//    System.out.println(m1.toString());

//    System.out.println(translationH2D(5, 10).toString());
//    System.out.println(vectorH2D(5, 10).toString());

    System.out.println(identity(10, 5));


  }
}