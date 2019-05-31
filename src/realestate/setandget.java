/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;



/**
 *
 * @author mohamed.do.259
 */
public class setandget {

    /**
     *this is a object of price 
     */
    public double Price;

    /**
     *this is post code (Address) object 
     */
    public double Postcode;

    /**
     *this is land size object 
     */
    public double landsize;

    /**
     * this function to set price 
     * @param Price
     */
    public void setPrice(double Price) {
        this.Price = Price;
       // System.out.println(this.Price);
    }

    /**
     *this function to set post code
     * @param Postcode
     */
    public void setPostcode(double Postcode) {
        this.Postcode = Postcode;
    }

    /**
     *this function to set land size
     * @param landsize
     */
    public void setLandsize(double landsize) {
        this.landsize = landsize;
    }

    /**
     * this function to get the price
     * @return
     */
    public double getPrice() {
        return Price;
    }

    /**
     * this function to get postcode
     * @return
     */
    public double getPostcode() {
        return Postcode;
    }

    /**
     *this function to get land size
     * @return
     */
    public double getLandsize() {
        return landsize;
    }

}
