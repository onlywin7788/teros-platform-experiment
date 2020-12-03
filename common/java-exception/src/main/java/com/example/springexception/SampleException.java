package com.example.springexception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleException  extends Exception{

    Boolean result;
    String reasonCode;
    String reasonMessage;


    public SampleException(Exception e, String reasonCode, String reasonMessage) {

        super(e);
        this.result = false;
        this.reasonCode = reasonCode;
        this.reasonMessage = reasonMessage;
    }

    @Override
    public String toString() {
        return "SampleException{" +
                "result=" + result +
                ", reasonCode='" + reasonCode + '\'' +
                ", reasonMessage='" + reasonMessage + '\'' +
                '}';
    }
}
