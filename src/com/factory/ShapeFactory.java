package com.factory;

public class ShapeFactory {
    /**
     * 工厂模式是一种创建模式，因为此模式提供了更好的方法来创建对象。
     *
     * 在工厂模式中，我们创建对象而不将创建逻辑暴露给客户端。
     * @param shapeType
     * @return
     */
    public Shape getShape(String shapeType){
        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }else if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        }
        return null;
    }
}
