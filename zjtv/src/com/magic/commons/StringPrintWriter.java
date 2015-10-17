package com.magic.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-7
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public class StringPrintWriter extends PrintWriter{

    public StringPrintWriter(){
        super(new StringWriter());
    }

    public StringPrintWriter(int initialSize) {
        super(new StringWriter(initialSize));
    }

    public String getString() {
        flush();
        return ((StringWriter) this.out).toString();
    }

    @Override
    public String toString() {
        return getString();
    }
}
