/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;
import java.awt.Polygon;
import java.util.Arrays;

/**
 *
 * @author sergi
 */
public class PolygonFloat extends Polygon{
    Integer precision;

    public PolygonFloat(double[] x, double[] y, int np, int precision) {  
        if (np > x.length || np > y.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || "+
                                                "npoints > ypoints.length");
        }
        if (np < 0) {
            throw new NegativeArraySizeException("npoints < 0");
        }
                
        int[] x_temp = new int[np];
        int[] y_temp = new int[np];

        for(int i=0;i<np;i++){
            x_temp[i] = (int)Math.round(x[i]*Math.pow(10,precision));
            y_temp[i] = (int)Math.round(y[i]*Math.pow(10,precision));
        }
        this.xpoints = Arrays.copyOf(x_temp, np);
        this.ypoints = Arrays.copyOf(y_temp, np);
        this.precision = precision;
        this.npoints = np;
    }

    @Override
    public boolean contains(double x, double y){
        return super.contains(x*Math.pow(10,precision),y*Math.pow(10,precision));
    }   
}
