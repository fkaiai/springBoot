package cn.fk.te.utils;

/**
 * java 没有Pair
 * Created by tanxianlin@lakala.com at 2016/12/21
 */
public class Pair<L,R> {
    public L key;
    public R value;

    public Pair(L l, R value){this.key =l;this.value = value;}
}