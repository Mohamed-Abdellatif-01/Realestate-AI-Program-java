/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realestate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mohamed.do.259
 */
public class Realestate {

    static ArrayList<setandget> inputList = new ArrayList();
    static ArrayList<Double> Xdata = new ArrayList();
    static ArrayList<Double> Ydata = new ArrayList();
    static ArrayList<Double> sum = new ArrayList();
    static ArrayList<Double> ab = new ArrayList();

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        inputList = readDataLineByLine("resources/data.csv");
        DecimalFormat df = new DecimalFormat("#.####");
        Scanner sc = new Scanner(System.in);
        double Z = sc.nextInt();
        double X = sc.nextInt();
        double y = 0;
        boolean c = check(Z, X);
        if (c == true) {
            ArrayList<Double> sum = new ArrayList();
            ArrayList<Double> ab = new ArrayList();
            sum = sum();
            ab = AandB(sum);
            double a = ab.get(0);
            double b = ab.get(1);
            System.out.println("a : " + df.format(a));
            System.out.println("b : " + df.format(b));

            y = (a * X) + b;
            System.out.println("Post code : " + Z);
            System.out.println("Land size : " + X);
            System.out.println("Cost : " + df.format(y));
        } else {
            System.out.println("The post code doesn't exist");
        }

    }

    /**
     * this method check the user input specifically it check if the post code
     * which is user entered it in the data or not then if it is in the data we
     * will take the information of this post code and save it into two arrays
     * list and set the boolean (True) if it isn't in the data keep boolean ads=
     * false
     *
     *
     * @param Z (post code from the user)
     * @param X (Land size from the user)
     * @return boolean ads
     */
    static public boolean check(double Z, double X) {
        boolean ads = false;
        for (int i = 0; i < inputList.size(); i++) //loop for check if the post code in the dataset or not
        {
            if (Z == inputList.get(i).Postcode) // if it is in the dataset asd = true
            {

                ads = true;
                break;

            }

        }

        if (ads == true) {
            for (int j = 0; j < inputList.size(); j++) {
                if (Z == inputList.get(j).Postcode && inputList.get(j).landsize != 0) {
                    Xdata.add(inputList.get(j).landsize);
                    Ydata.add(inputList.get(j).Price);

                }
            }
        }
        return ads;
    }

    /**
     * this method sum the land size and price and also get sum of price and
     * land size and sum of land size power of 2
     *
     * @return Array list has (sum of land size, sum of price , sum of price
     * multiply land size and sum of land size power of 2)
     */
    public static ArrayList<Double> sum() {

        double sumx = 0;
        double sumy = 0;
        double sumxy = 0;
        double sumxp2 = 0;
        double n = Xdata.size();
        int i;
        for (i = 0; i < Xdata.size(); i++) {
            double xy = Xdata.get(i) * Ydata.get(i);
            sumx = sumx + Xdata.get(i);
            sumy = sumy + Ydata.get(i);
            sumxy = sumxy + xy;
            sumxp2 = sumxp2 + Math.pow(Xdata.get(i), 2);

        }
        sum.add(sumx);
        sum.add(sumy);
        sum.add(sumxy);
        sum.add(sumxp2);

        return sum;
    }

    //this method to calculate the mean of Price, land size and Post code 
    /**
     * this function take Array list sum and make a calculation to get a and b
     * to get y(price) equation ( Y = a*X + b)
     *
     * @param sum
     * @return ab (Array list has a(Slope) and b )
     */
    public static ArrayList<Double> AandB(ArrayList<Double> sum) {

        double sumx = sum.get(0);
        double sumy = sum.get(1);
        double sumxy = sum.get(2);
        double sumxp2 = sum.get(3);
        //double sumyp2 = sum.get(4);
        int j;
        //the sum of each var - mean for example X1m = (x(i)- mean(x))

        double meanx = sumx / Xdata.size();// divided buy count of them
        double meany = sumy / Ydata.size();
        double Xm = 0;
        double XYm = 0;
        double Xmp2 = 0;
        double Ym = 0;

        for (j = 0; j < Xdata.size(); j++) {

            // to get (X1) - mean(X1)  X1 = postcode
            double xc = (Xdata.get(j) - meanx);
            Xm = Xm + xc;// get the sum of all X1-meanX1

            double yc = (Xdata.get(j) - meany);//to get  y - mean(y) 
            Ym = Ym + yc; // the sum of all Y - mean(y)

            // (X1(i)-mean(X1))*(Y(i)-mean(Y)
            double xy = xc * yc;
            XYm = XYm + xy;// sum all X1 - mean X1 * Y - mean y

            // get the sum of (X1 - mean(X1)) power of 2
            double xmp2 = Math.pow(xc, 2);
            Xmp2 = Xmp2 + xmp2;

        }

        DecimalFormat df = new DecimalFormat("#.####");

        double a = ((sumxy - (Xdata.size() * meanx * meany)) / ((sumxp2 - (Xdata.size() * meanx * meanx))));
        double b = meany - (a * meanx);

        ab.add(a);
        ab.add(b);

        return ab;
    }

    /**
     * this method to reset all program and make all arrays clear this is react
     * to reset button in interface
     */
    public static void clear() {
        inputList.clear();
        Xdata.clear();
        Ydata.clear();
        sum.clear();
        ab.clear();
    }

    /**
     * this method simply read the data file and save it in the Arraylist of
     * type setandget which is has 3 objects price and land size and post code
     * and then return the Array list that has the data saved in it.
     *
     * @param file
     * @return data
     * @throws FileNotFoundException
     */
    public static ArrayList readDataLineByLine(String file) throws FileNotFoundException {

        Scanner fileinput = new Scanner(new BufferedReader(new FileReader(file)));
        ArrayList<setandget> data = new ArrayList();

        while (fileinput.hasNextLine()) {

            setandget toAdd = new setandget();
            String newline = fileinput.nextLine();

            String[] line = newline.trim().split(",");

            toAdd.setPrice(Double.parseDouble(line[4]));
            toAdd.setPostcode(Double.parseDouble(line[9]));
            toAdd.setLandsize(Double.parseDouble(line[13]));

            data.add(toAdd);

        }
        return data;

    }
}
