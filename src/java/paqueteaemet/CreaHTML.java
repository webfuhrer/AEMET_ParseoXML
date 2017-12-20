/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteaemet;

import java.util.List;

/**
 *
 * @author luis
 */
public class CreaHTML {
    public static String crearTabla(List<ObjetoClima> lista_clima)
    {
        String aux="<table><tr><th>Fecha</th><th>Temperatura</th></tr>";
        for (ObjetoClima oc: lista_clima)
        {
            aux+="<tr><td>"+oc.getFecha()+"</td><td>"+oc.getT_max()+"/"+oc.getT_min()+"</td></tr>";
        }
        aux+="</table>";
        return aux;
    }
}
