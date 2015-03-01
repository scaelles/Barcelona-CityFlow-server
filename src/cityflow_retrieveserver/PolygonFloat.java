/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;
import java.awt.Polygon;
import java.lang.Math;
import java.util.Arrays;

/**
 *
 * @author sergi
 */
public class PolygonFloat extends Polygon{
    Integer precision;

    public PolygonFloat(double[] x, double[] y, int npoints, int precision) {  
        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || "+
                                                "npoints > ypoints.length");
        }
        if (npoints < 0) {
            throw new NegativeArraySizeException("npoints < 0");
        }
                
        int[] x_temp = new int[npoints];
        int[] y_temp = new int[npoints];

        for(int i=0;i<npoints;i++){
            x_temp[i] = (int)Math.round(x[i]*Math.pow(10,precision));
            y_temp[i] = (int)Math.round(y[i]*Math.pow(10,precision));
        }
        this.xpoints = Arrays.copyOf(x_temp, npoints);
        this.ypoints = Arrays.copyOf(y_temp, npoints);
        this.precision = precision;
        this.npoints = npoints;
    }

    @Override
    public boolean contains(double x, double y){
        return super.contains(x*Math.pow(10,precision),y*Math.pow(10,precision));
    }   
}
