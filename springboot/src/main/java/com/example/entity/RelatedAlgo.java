package com.example.entity;

public class RelatedAlgo {
    /** User id */
    private Integer useId;
    /** Goods id */
    private Integer goodsId;
    /** Index */
    private Integer index;

    /**
     * Constructs a new RelateDTO with the specified user ID, goods ID, and index.
     *
     * @param userId the ID of the user
     * @param goodsId the ID of the goods
     * @param index the index value
     */

    public RelatedAlgo(Integer useId, Integer goodsId, Integer index) {
        this.useId = useId;
        this.goodsId = goodsId;
        this.index = index;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}