package com.example.marketwatchsystem.Model;

public class ProductInfo {
    private String productName;
    private String productCode;
    private String message;

    public ProductInfo(String productName, String productCode, String message) {
        this.productName = productName;
        this.productCode = productCode;
        this.message = message;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
