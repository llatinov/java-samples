package com.automationrhapsody.junit;

public class PowerMockDemo {

    public Point publicMethod() {
        return new Point(11, 11);
    }

    public Point callPrivateMethod() {
        return privateMethod(new Point(1, 1));
    }

    private Point privateMethod(Point point) {
        return new Point(point.getX() + 1, point.getY() + 1);
    }
}
