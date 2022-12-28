package com.Hileb.silent_assassin.Contracts;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class ContractsEvent extends Event {
    public final ContractsBase contracts;
    public ContractsEvent(ContractsBase var){
        contracts=var;
    }
    public static class Pre extends ContractsEvent
    {
        public Pre(ContractsBase var){super(var);}

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
    public static class Post extends ContractsEvent{
        public Post(ContractsBase var){
            super(var);
        }

        @Override
        public boolean isCancelable() {
            return false;
        }
    }
    @SideOnly(Side.CLIENT)
    public static class Request extends ContractsEvent{
        public Request(ContractsBase var){
            super(var);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
    //server
    public static class Apply extends ContractsEvent{
        public List<ContractsBase> contracts;
        public Apply(List<ContractsBase> var){
            super(var.get(0));
            contracts=var;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
    @SideOnly(Side.CLIENT)
    public static class Send extends ContractsEvent{
        public Send(ContractsBase var){
            super(var);
        }

        @Override
        public boolean isCancelable() {
            return false;
        }
    }
    @SideOnly(Side.CLIENT)
    public static class Message extends ContractsEvent{
        public final String message;
        public Message(String messageIn){
            super(ContractsBase.EMPTY);
            message=messageIn;
        }

        @Override
        public boolean isCancelable() {
            return false;
        }
    }
}
