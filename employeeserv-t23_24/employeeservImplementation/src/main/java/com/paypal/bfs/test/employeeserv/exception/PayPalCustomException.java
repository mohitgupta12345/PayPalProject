package com.paypal.bfs.test.employeeserv.exception;

public class PayPalCustomException extends RuntimeException {

    private String messg;

    public PayPalCustomException(String messg) {
        this.messg = messg;
    }

    public String getMessg() {
        return messg;
    }

    public void setMessg(String messg) {
        this.messg = messg;
    }
}
