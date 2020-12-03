package com.example.springexception;

public class Test {
    public void test()throws SampleException
    {
        try {
            System.out.println( 10 / 0 );
            System.out.println( "끝" );
        }
        catch(Exception e) {
            throw new SampleException(e, "404","BAD_REQUEST");


        }
    }
}
