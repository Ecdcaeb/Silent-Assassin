package com.Hileb.silent_assassin.Contracts;

import javax.annotation.Nonnull;

public class ContractsBase {
    public static final ContractsBase EMPTY=new ContractsBase("",new ContractsInv());
    public final String target;
    public final ContractsInv money;
    public ContractsBase(@Nonnull String targetIn,@Nonnull ContractsInv moneyIn){
        target=targetIn;
        money=moneyIn;
    }
    public boolean isEmpty(){
        if (this==EMPTY)return true;
        //if (this.money.isEmpty())return true;
        return false;
    }
}
