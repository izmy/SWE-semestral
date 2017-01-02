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
public class GDP {
    protected String countryName;
    protected String GDPValue;

    public GDP(String countryName, String GDPValue) {
        
        String[] countryNameSplit = countryName.split("\"");
        String[] GDPValueSplit = GDPValue.split("\"");

        this.countryName = countryNameSplit[1];
        this.GDPValue = GDPValueSplit[1];
    }

    public String getCountryName() {
        return countryName;
    }

    public String getGDPValue() {
        return GDPValue;
    }
    
}
