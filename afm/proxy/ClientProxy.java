package afm.proxy;

import afm.client.render.TESR.TESRTestModel;
import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.data.ItemData;
import afm.tileEntity.TETestModel;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void writeChatMessageToPlayer(String s) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(s);
    }

    /**
     * Registers the textures for blocks and items
     */
    @Override
    public void registerTexuresAndRenderers() {
        AFMLogger.log("Registering textures");
        MinecraftForgeClient.preloadTexture(BlockData.TEXTURE);
        MinecraftForgeClient.preloadTexture(ItemData.TEXTURE);

        AFMLogger.log("Registering renderers");
        ClientRegistry.bindTileEntitySpecialRenderer(TETestModel.class, new TESRTestModel());
    }

}
