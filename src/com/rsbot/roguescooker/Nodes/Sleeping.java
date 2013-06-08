package com.rsbot.roguescooker.nodes;

import com.rsbot.roguescooker.utils.Methods;
import com.rsbot.roguescooker.vars.Variables;
import org.powerbot.core.script.job.state.Node;

public class Sleeping extends Node {
    public boolean activate() {
        return Variables.guiDisposed
                && Methods.isCooking();
    }

    public void execute() {
        sleep(100, 200);
    }
}
