package com.kai.ling.studyrxjava.model;

public class Translation {
    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println("RxJava翻译结果：" + status);
        System.out.println("RxJava翻译结果：" + content.from);
        System.out.println("RxJava翻译结果：" + content.to);
        System.out.println("RxJava翻译结果：" + content.vendor);
        System.out.println("RxJava翻译结果：" + content.out);
        System.out.println("RxJava翻译结果：" + content.errNo);
    }
}