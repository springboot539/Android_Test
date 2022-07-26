package com.example.bankservicedemo.actions.interfaces;

public interface IBankWorkerAction extends INormalUserAction {
    /**
     * 查询信用
     */
    void checkUserCredit();

    /**
     * 冻结账号
     */
    void freezeUserAccount();
}
