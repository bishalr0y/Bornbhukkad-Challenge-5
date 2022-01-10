package com.lovafood.bornbhukkad.privatelimited.merchantPortal;

public class MerchantModel {
    private String competitorName;
    private float productPrice;

    public MerchantModel(String competitorName, float productPrice) {
        this.competitorName = competitorName;
        this.productPrice = productPrice;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}