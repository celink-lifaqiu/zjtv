package com.magic.app.zjtv.async;

/**
 * Created by YinJianFeng on 14-6-16.
 */
public class AsyncAward implements Runnable {
    private Integer awardId;
    private Integer uid;
    private String channel;

    public AsyncAward(Integer awardId, Integer uid, String channel) {
        this.awardId = awardId;
        this.uid = uid;
        this.channel = channel;
    }

    @Override
    public void run() {

    }
}
