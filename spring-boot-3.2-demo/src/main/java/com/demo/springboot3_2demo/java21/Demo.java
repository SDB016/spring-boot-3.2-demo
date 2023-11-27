package com.demo.springboot3_2demo.java21;

public class Demo {

}

sealed interface Shape permits Circle, Square {

}


final class Circle implements Shape {  // class로 선언시 selaed, non-sealed, final만 허용
    private final int RADIUS = 10;

    public String getSize() {
        return Double.toString(RADIUS * RADIUS * 3.14);
    }

}

record Square(int size) implements Shape {

}