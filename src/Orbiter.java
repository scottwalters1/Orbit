import java.awt.Color;
import java.util.*;

/**
 * An Orbiter is an object that orbits some other object, called its parent. 
 * The center of an orbital system is an Orbiter with no parent. 
 * Each Orbiter may have child Oribters that orbit it. 
 *
 * An Orbiter stores its orbital radius and current orbit angle.
 */

public class Orbiter {
  
  public enum Type {
    CIRCLE, SQUARE, TRIANGLE
  }

  private final double orbitRadius;
  private final Type type; 
  private final Color fillColor; 
  private double orbitAngle; 
  private double orbitSpeed;
  
  private final List<Orbiter> children = new LinkedList<Orbiter>();
  private final Orbiter parent; 
  
  public Orbiter(Orbiter parent, double orbitRadius, double orbitAngle, double orbitSpeed, Type type, Color fillColor) {
    this.orbitRadius = orbitRadius; 
    this.orbitAngle = orbitAngle; 
    this.type = type;
    this.fillColor = fillColor; 
    this.parent = parent; 
    this.orbitSpeed = orbitSpeed; 
    if (parent != null) parent.children.add(this);
  }
  
  public double getOrbitRadius() { return orbitRadius; }
  public double getOrbitAngle() { return orbitAngle; }
  public Color getFillColor() { return fillColor; }
  public Type getType() { return type; }
  public Orbiter getParent() { return parent; }
  public List<Orbiter> getChildren() { return children; }
  
  /**
   * Updates the rotation of this orbiter by the amount specified in the deltaAngle parameter. 
   * @param timeDelta The amount of rotation angle to add the to the current rotation.
   */
  public void updateRotation(double timeDelta) {
    orbitAngle += (timeDelta * orbitSpeed); 
  }
  
  public Matrix getMatrix() throws UndefinedMatrixOpException {
    if (this.getParent() == null) {
      return Matrix.identity(3);
    }

    Orbiter parent = this.getParent();
//    return parent.getMatrix().dot(Matrix.rotationH2D(orbitAngle).dot(Matrix.translationH2D(orbitRadius, 0)));

     Matrix p = parent.getMatrix();
     Matrix rotateM = Matrix.rotationH2D(orbitAngle);
     Matrix translateM = Matrix.translationH2D(orbitRadius, 0);

//     Matrix t = Matrix.translationH2D(p.entry(0, 2), p.entry(1, 2));
    Matrix t = Matrix.rotationH2D(getOrbitAngle()).dot(Matrix.translationH2D(getOrbitRadius(), 0));

//     return t.dot(rotateM).dot(translateM);
    return p.dot(t);



//    translate(entry 0 2 and entry 1 2 of parent) multiply that by rotate and translate



    // TODO
    // If this is the root node, then return the 3x3 identity matrix
    // If this is not the root node, should return the transformation
    // matrix for this orbiter (see the writeup for an idea of how to
    // do this). Make sure you've coded the Matrix class first.
  }

  public static void main(String[] args) throws UndefinedMatrixOpException {
    Orbiter root = new Orbiter(null, 10, 5, 10, Type.CIRCLE, Color.RED);
    Orbiter a = new Orbiter(root, 5, 10, 5, Type.CIRCLE, Color.RED);
    Orbiter b = new Orbiter(a, 10, 5, 10, Type.CIRCLE, Color.GREEN);
    System.out.println(b.getMatrix().toString());
  }

//  private Matrix recurse(Orbiter o, Matrix result) throws UndefinedMatrixOpException {
//
//    while (o.getChildren() != null) {
//      for (Orbiter next : o.getChildren()) {
//        result = result.dot(Matrix.rotationH2D(o.getOrbitAngle()).dot(Matrix.translationH2D(o.getOrbitRadius(), 0)));
//        result = recurse(next, result);
//      }
//    }
//    return result;
//  }
}
