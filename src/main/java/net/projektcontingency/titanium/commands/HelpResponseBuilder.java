package net.projektcontingency.titanium.commands;

import java.util.HashMap;
import java.util.Map;

public class HelpResponseBuilder {
    private Map<String, String> params;
    private String command;
    private String subCommand;
    private String info;

    public HelpResponseBuilder() {
        this.params = new HashMap<>();
        this.info = null;
        this.subCommand = "";
    }

    public HelpResponseBuilder addParam(String name, String help) {
        this.params.put(name, help);
        return this;
    }

    public HelpResponseBuilder setInfo(String info) {
        this.info = info;
        return this;
    }

    public HelpResponseBuilder setCommand(String command) {
        this.command = command;
        return this;
    }


    public HelpResponseBuilder setSubCommand(String subCommand) {
        this.subCommand = subCommand;
        return this;
    }

    public HelpResponse build() {
        return new HelpResponse(params, command, subCommand, info);
    }
}
