package com.example.bankservicedemo.actions.interfaces;

public interface INormalUserAction {
    /**
     * 存钱
     *
     * @param money
     */
    void saveMoney(float money);

    /**
     * 取钱
     *
     * @return
     */
    float getMoney();

    /**
     * 借贷
     *
     * @return
     */
    float loanMoeny();
}
