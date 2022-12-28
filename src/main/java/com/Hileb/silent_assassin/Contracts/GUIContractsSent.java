package com.Hileb.silent_assassin.Contracts;

import com.Hileb.silent_assassin.Contracts.pack.CommandMessagePack;
import com.Hileb.silent_assassin.IdlFramework;
import com.Hileb.silent_assassin.gui.EmptyContainer;
import com.Hileb.silent_assassin.network.NetworkHandler;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class GUIContractsSent extends GuiContainer {
    public final int uuid;
    private EntityPlayer player;
    private static final String TEXTURE_PATH = IdlFramework.MODID + ":" + "textures/gui/ica.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    private GuiTextField textFieldOfTarget;
    private List<String> commandLine=new ArrayList<>();
    public GUIContractsSent(EntityPlayer playerIn){
        super(new EmptyContainer());
        this.xSize = 190;
        this.ySize = 180;
        player=playerIn;
        uuid=playerIn.getUniqueID().hashCode();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderCommandText();
        this.textFieldOfTarget.drawTextBox();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        this.textFieldOfTarget.drawTextBox(); // 绘制文本框
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    }
    protected void renderCommandText(){
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        List<String> trueRender=new ArrayList<>();
        if (commandLine.size()<=12){
            for(int i=0;i<commandLine.size();i++){
                this.fontRenderer.drawString(commandLine.get(i),offsetX+5,offsetY+15+i*10,0x404040,false);
                //IdlFramework.LogWarning("render:%s",commandLine.get(i));
            }
        }
        else {
            for(int i=1;i<=12;i++){
                this.fontRenderer.drawString(commandLine.get(commandLine.size()-i),offsetX+5,offsetY+135-10*i,0x404040,false);
                //IdlFramework.LogWarning("render:%s",commandLine.get(i));
            }
        }
    }

    @Override
    public void initGui() {
        commandLine.add("ICA.ioi[版本 10.0.19045.2364]");
        commandLine.add("(c) ICA Corporation。保留所有权利。");
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        textFieldOfTarget=new GuiTextField(1,this.fontRenderer,offsetX+3,offsetY+170,177,9);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        textFieldOfTarget.updateCursorCounter();
    }
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textFieldOfTarget.mouseClicked(mouseX,mouseY,mouseButton); // 打开文本框点击获得焦点的功能
    }
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (typedChar==13){
            commandEvent(textFieldOfTarget.getText());
            textFieldOfTarget.setText("");
        }
        else textFieldOfTarget.textboxKeyTyped(typedChar,keyCode); // 打开文本框输入功能
    }
    private void commandEvent(String s){
        commandLine.add(s);
        List<String> cmd=new ArrayList<>();
        cmd=Arrays.stream(s.split(" ")).collect(Collectors.toList());
        if (cmd.get(0)!=null){
            CommandMessagePack pack=new CommandMessagePack();
            pack.message=cmd;
            pack.player=player.getName();
            NetworkHandler.SendToServer(pack);
        }
    }
    @SubscribeEvent
    public void onMessage(ContractsEvent.Message e){
        if (e.message!=null)commandLine.add(e.message);
    }
}
