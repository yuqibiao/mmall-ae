package com.yyyu.product.pojo;

/**
 * 功能：
 *
 * @author yu
 * @date 2018/3/16.
 */
public class MallProductReturn extends MallProductWithBLOBs{

    private Long categoryId;

    private String categoryName;

    private String serverAddress;


    @Override
    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
