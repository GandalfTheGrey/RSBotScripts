package com.rsbot.roguescooker.Nodes;

import com.rsbot.roguescooker.Utils.Methods;
import com.rsbot.roguescooker.Variables.Variables;
import org.powerbot.core.script.job.state.Node;
/**
 * Created with IntelliJ IDEA.
 * User: Romi Grace
 * Date: 01/06/13
 * Time: 09:08
 * To change this template use File | Settings | File Templates.
 */
public class Sleeping extends Node {
    public boolean activate() {
        return Variables.guiDisposed
                && Methods.isCooking();
    }

    public void execute() {

    }
}
