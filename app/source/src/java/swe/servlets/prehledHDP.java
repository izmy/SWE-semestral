package swe.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.*;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.*;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import swe.models.GDP;

/**
 *
 * @author admin
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main-servlet"})
public class prehledHDP extends HttpServlet {
   

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
                            "PREFIX economy: <http://hrachjar.cz/economy/#>\n" +
                            "\n" +
                            "select ?name ?gdp where {\n" +
                            "  ?country a country:Country .\n" +
                            "    ?country owl:sameAs ?economy .\n" +
                            "    ?economy a economy:Economy ;\n" +
                            "    foaf:name ?name ;\n" +
                            "    economy:gdp ?gdp .\n" +
                            "} order by desc(?gdp)";
        
        List<GDP> listGDP = new ArrayList<>();
        
        try (RepositoryConnection con = repo.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            TupleQueryResult result = tupleQuery.evaluate();
            List<BindingSet> bindings = QueryResults.stream(result).collect(Collectors.toList());
            for (BindingSet binding : bindings) {
                Value valueName = binding.getValue("name");
                Value valueGDP = binding.getValue("gdp");
                GDP gdp = new GDP(valueName.toString(), valueGDP.toString());
                listGDP.add(gdp);
            }
        }
        request.setAttribute("listGDP", listGDP);
        RequestDispatcher rd = request.getRequestDispatcher("prehled-hdp.jsp");
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
