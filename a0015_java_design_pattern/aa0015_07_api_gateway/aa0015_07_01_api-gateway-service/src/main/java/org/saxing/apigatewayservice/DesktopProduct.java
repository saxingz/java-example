package org.saxing.apigatewayservice;

/**
 * Encapsulates all of the information that a desktop client needs to display a product.
 * 
 * @author saxing  2018/10/23 21:44
 */
public class DesktopProduct {

    private String price;

    private String imagePath;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
