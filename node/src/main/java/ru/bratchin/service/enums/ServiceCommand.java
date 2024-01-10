package ru.bratchin.service.enums;

public enum ServiceCommand {
    HELP("/help");

    private final String cmd;

    ServiceCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return cmd;
    }

    public boolean equals(String cmd) {
        return cmd.equals(this.cmd);
    }
}
