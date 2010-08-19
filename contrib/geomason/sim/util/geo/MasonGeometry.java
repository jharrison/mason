package sim.util.geo; 

import com.vividsolutions.jts.geom.*; 

/** 
   A MasonGeometry is a wrapper for a JTS geometry and an associated userData field.  The userData field
   can be any MASON object, or general Java object, which will be included in the inspector by default.  
   
   <p> MasonGeometry implements sim.util.Proxiable to allow the hiding of various getXXX and setXXX methods 
   from the inspectors.  
*/

public class MasonGeometry implements sim.util.Valuable, sim.util.Proxiable  {

    /** Internal JTS geometry object */ 
    public Geometry geometry; 
        
   /** Arbitrary object set by the user */ 
    public Object userData; 
        
    /** Default constructors */ 
    public MasonGeometry() { this(null, null); }
    public MasonGeometry(Geometry g) { this(g, null); }
    public MasonGeometry(Geometry g, Object o) { 
    	geometry  = g; 
    	userData = o; 
    }

    /** Returns the type of the internal JTS geometry object (Point, Polygon, Linestring, etc) */ 
    public String toString() { return geometry.getGeometryType();  } 
             
    /** Returns the JTS geometry object.  */
    public Geometry getGeometry() { return geometry; }
    
    /** The returned value is used to handle per region coloring. */ 
    public double doubleValue() { return 1; }
	
    
    /** Inner class allows us to prevent certain getXXX and setXXX methods from 
     * appearing in the Inspector
     */
    public class GeomWrapperProxy
    {
    	/** Returns the area of the internal JTS geometry object.  
        The units are the same as same as the internal JTS geometry object */
    	public double getArea() { return geometry.getArea(); }
    	
    	/** Returns the length of the perimeter of the internal JTS geometry object. 
        The units are the same as same as the internal JTS geometry object */
    	public double getPerimeter() { return geometry.getLength(); }
    	
    	/** The number of vertices which make up the geometry */ 
    	public int getNumVertices() { return geometry.getNumPoints(); }
    }
    
	public Object propertiesProxy() { return new GeomWrapperProxy(); }
     
}