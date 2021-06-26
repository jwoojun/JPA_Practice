package com.example.jpashop.domain.item;

public class NoEnoughStock extends RuntimeException {
    public NoEnoughStock() {
        super();
    }

    public NoEnoughStock(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEnoughStock(Throwable cause) {
        super(cause);
    }

//    protected NoEnoughStock(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }

    public NoEnoughStock(String s) {
    }
}
