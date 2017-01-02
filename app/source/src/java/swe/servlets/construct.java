/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rdf4j.query.GraphQuery;
import org.eclipse.rdf4j.query.GraphQueryResult;
import org.eclipse.rdf4j.query.Query;
import org.eclipse.rdf4j.query.QueryLanguage;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

/**
 *
 * @author admin
 */
@WebServlet(name = "construct", urlPatterns = {"/construct"})
public class construct extends HttpServlet {

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
        Repository repo = new SPARQLRepository("http://localhost:8080/rdf4j-server/repositories/hrachjar");
        repo.initialize();
        
        String queryString = "PREFIX country: <http://hrachjar.cz/country/#>\n" +
                            "PREFIX foaf: <http://xmlns.com/foaf/0.1/#>\n" +
                            "PREFIX company: <http://hrachjar.cz/company/#>\n" +
                            "PREFIX economy: <http://hrachjar.cz/economy/#>\n" +
                            "PREFIX dbo: <http://dbpedia.org/ontology/#>\n" +
                            "\n" +
                            "construct {\n" +
                            "  ?country a country:Country;\n" +
                            "  foaf:name ?countryName;\n" +
                            "  economy:potentialEmployees ?potentialEmployees;\n" +
                            "}\n" +
                            "where {\n" +
                            "  ?company a company:Company ;\n" +
                            "    foaf:name ?companyName .\n" +
                            "  ?company country:country ?country .\n" +
                            "    ?country foaf:name ?countryName ;\n" +
                            "      		dbo:population ?population .\n" +
                            "    ?country owl:sameAs ?economy .\n" +
                            "  ?economy economy:unemployment ?unemployment ;\n" +
                            "    BIND( (floor(?unemployment)  ) AS ?unem).\n" +
                            "    BIND( (floor(?population / 100) ) AS ?populationOne).\n" +
                            "	BIND( (?unem * ?populationOne) AS ?potentialEmployees).\n" +
                            "}";
               
            
        List<String> listConstruct = new ArrayList<>();
        try (RepositoryConnection con = repo.getConnection()) {

            Query query = con.prepareQuery(QueryLanguage.SPARQL, queryString);
            GraphQueryResult result = ((GraphQuery) query).evaluate();

            while (result.hasNext()) {
                // The result is an iterator of Statement, which is a RDF triple or quad.
                listConstruct.add(result.next().toString());
            }

            // Do not forget!
            result.close();
        }
    
        request.setAttribute("listConstruct", listConstruct);
        RequestDispatcher rd = request.getRequestDispatcher("construct.jsp");
        rd.forward(request, response);

      
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

}
