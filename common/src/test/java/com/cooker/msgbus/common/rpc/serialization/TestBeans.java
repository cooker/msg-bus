package com.cooker.msgbus.common.rpc.serialization;

import java.io.Serializable;
import java.util.Map;

/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/06 下午2:36
 * 功能描述: 测试数据
 * 修改历史:
 */
public class TestBeans {
    public static Element[] arr = new Element[]{
        new Element("整数", 2123)
    };

    public static class A implements Serializable {

        private static final long serialVersionUID = -4335929795782696317L;

        int a;
        String b;
        Map<String, String> rmap;

        public A a(int x){
            this.a = x;
            return this;
        }

        public A b(String x){
            this.b = x;
            return this;
        }

        public A rmap(Map<String, String> x){
            this.rmap = x;
            return this;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public Map<String, String> getRmap() {
            return rmap;
        }

        public void setRmap(Map<String, String> rmap) {
            this.rmap = rmap;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    ", rmap=" + rmap +
                    '}';
        }
    }

    public static class Element implements Serializable{

        private static final long serialVersionUID = -3560203298688476894L;
        private String name;
        private Object value;

        public Element(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
