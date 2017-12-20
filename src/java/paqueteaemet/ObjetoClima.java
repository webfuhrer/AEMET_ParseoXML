/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteaemet;

/**
 *
 * @author luis
 */
public class ObjetoClima {
    private String fecha;
    private int t_min, t_max;

    public ObjetoClima(String fecha, int t_min, int t_max) {
        this.fecha = fecha;
        this.t_min = t_min;
        this.t_max = t_max;
    }

    public String getFecha() {
        return fecha;
    }

    public int getT_min() {
        return t_min;
    }

    public int getT_max() {
        return t_max;
    }

    
}
