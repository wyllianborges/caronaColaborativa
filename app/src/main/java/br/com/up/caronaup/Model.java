package br.com.up.caronaup;

/**
 * Created by Eduardo on 26/06/2016.
 */
public class Model {

    private String sNo;
    private String product;
    // private String category;
    private String price;

    public Model(String sNo, String product, String price) {
        //public Model(String sNo, String product, String category, String price) {
        this.sNo = sNo;
        this.product = product;
        // this.category = category;
        this.price = price;
    }

    public String getsNo() {
        return sNo;
    }

    public String getProduct() {
        return product;
    }

    // public String getCategory() {        return category;    }

    public String getPrice() {
        return price;
    }

}

