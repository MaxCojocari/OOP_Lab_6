package core;

import actors.EOAccount;
import core.interfaces.ITransaction;

abstract class Transaction implements ITransaction {
    private EOAccount sender;
    private EOAccount receiver;
    private boolean checked;

    public Transaction(EOAccount sender, EOAccount receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.checked = false;
    }

    public EOAccount sender() {
        return sender;
    }

    public EOAccount receiver() {
        return receiver;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        this.checked = true;
    }
}
