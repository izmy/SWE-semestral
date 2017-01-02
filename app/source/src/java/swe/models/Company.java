/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.models;

/**
 *
 * @author admin
 */
public class Company {
    protected String countryName;
    protected String countCompany;

    public Company(String countryName, String countCompany) {
        String[] countryNameSplit = countryName.split("\"");
        String[] countCompanySplit = countCompany.split("\"");

        this.countryName = countryNameSplit[1];
        this.countCompany = countCompanySplit[1];
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountCompany() {
        return countCompany;
    }
}
