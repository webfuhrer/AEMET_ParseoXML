/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteaemet;

import conexion.ConexionURL;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 *
 * @author luis
 */
@WebServlet(name = "ServletAEMET", urlPatterns = {"/ServletAEMET"})
public class ServletAEMET extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="http://www.aemet.es/xml/municipios/localidad_28079.xml";
        String xml_respuesta=ConexionURL.peticionWeb(url);
        List<ObjetoClima> lista_objetos_clima=tratarXML(xml_respuesta);
        request.setAttribute("lista_objetos_clima", lista_objetos_clima);
        request.getRequestDispatcher("verclima.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<ObjetoClima> tratarXML(String xml_respuesta) {
        List<ObjetoClima> lista_climas=new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(new InputSource(new ByteArrayInputStream(xml_respuesta.getBytes("utf-8"))));
            Element raiz=document.getRootElement();
            List lista_hijos=raiz.getChildren();
            Element elemento_prediccion=(Element)lista_hijos.get(4);//
            Element elemento_prediccion_otra_forma=raiz.getChild("prediccion");
            List lista_elementos_dia=elemento_prediccion.getChildren();
            for (int i=0; i<lista_elementos_dia.size(); i++)
                    {
                        Element elemento_dia=(Element)lista_elementos_dia.get(i);
                        String fecha=elemento_dia.getAttributeValue("fecha");
                        Element elemento_temperatura=elemento_dia.getChild("temperatura");
                        Element elemento_t_min=elemento_temperatura.getChild("minima");
                        Element elemento_t_max=elemento_temperatura.getChild("maxima");
                        String t_min=elemento_t_min.getText();
                        String t_max=elemento_t_max.getText();
                        ObjetoClima oc=new ObjetoClima(fecha, Integer.parseInt(t_min), Integer.parseInt(t_max));
                        lista_climas.add(oc);
                    }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServletAEMET.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(ServletAEMET.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServletAEMET.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_climas;
    }
}
