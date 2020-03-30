package com.jianli.util;

public class CloneTest implements Cloneable{
    private double value;

    public CloneTest(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneTest cloneTest = null;
        try {
            cloneTest = (CloneTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneTest;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest ct = new CloneTest(21.1);
        CloneTest ctclome = (CloneTest) ct.clone();
        System.out.println("Original = Clone? : " + (ct==ctclome));//false

        System.out.println("Original: " + ct.getValue());
        System.out.println("Clone: " + ctclome.getValue());

        ct.setValue(23.4);
        ctclome.setValue(56.8);

        System.out.println("Original: " + ct.getValue());
        System.out.println("Clone: " + ctclome.getValue());
    }
}
