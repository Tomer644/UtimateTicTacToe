package com.example.utimatetictactoe;

public class Skin {
    private String id;
    private boolean owned;
    //private String path;
    private SkinType skinType;
    private int price;

    //0 = 0 (common)
    //1,2...make 5 total = 50 (rare)
    //3...make 1or2 more = 150 (epic)
    //4,5 = 200 (legendary)

    public Skin(String id, boolean owned, SkinType skinType){
        this.id = id;
        this.owned = owned;
        this.skinType = skinType;
        setPriceByType();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPriceByType() {
        switch (this.skinType){
            default: this.price = 0; break;
            case rare: this.price = 50; break;
            case epic: this.price = 150; break;
            case legendary: this.price = 200; break;
        }
        this.price = price;
    }


    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }
}
