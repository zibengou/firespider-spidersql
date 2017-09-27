package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.Gen;

import java.util.concurrent.Callable;

public abstract class Action implements Callable<Gen>{
    public abstract Gen call();
}
