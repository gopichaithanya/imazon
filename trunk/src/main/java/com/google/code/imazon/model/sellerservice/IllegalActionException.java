package com.google.code.imazon.model.sellerservice;

@SuppressWarnings("serial")
public class IllegalActionException extends Exception {

    private Long orderId;

    public IllegalActionException(Long orderId) {
        super("Illegal action exception => orderId = " + orderId);
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }


}
