package afm.core;

import afm.blocks.Blocks;
import afm.command.CommandAFM;
import afm.data.Properties;
import afm.gui.GUIHandler;
import afm.i18n.Localization;
import afm.items.Items;
import afm.network.PacketHandler;
import afm.proxy.CommonProxy;
import afm.world.WorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = Properties.MOD_NAME, modid = Properties.MOD_ID, version = Properties.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {Properties.Network.CHANNEL}, packetHandler = PacketHandler.class)
public class AFM {

    Configuration config;

    final WorldGenerator worldGen = new WorldGenerator();

    @Instance(Properties.MOD_ID)
    public static AFM afm = new AFM();

    @SidedProxy(clientSide = "afm.proxy.ClientProxy", serverSide = "afm.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs tabAFM = new TabAFM(CreativeTabs.getNextID());

    public static final GUIHandler guiHandler = new GUIHandler();

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new CommandAFM());
    }

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        AFMLogger.init();

        Properties.init(event.getSuggestedConfigurationFile());

        Localization.loadLocales();

        MinecraftForge.EVENT_BUS.register(new EventHandler());

        NetworkRegistry.instance().registerGuiHandler(AFM.afm, AFM.guiHandler);
    }

    @Init
    public void init(FMLInitializationEvent event) {

        this.showDebugGroup(true, event.getSide().toString());

        AFM.proxy.registerTexuresAndRenderers();

        Items.init();
        Blocks.init();

        GameRegistry.registerWorldGenerator(this.worldGen);

        this.showDebugGroup(false, event.getSide().toString());
    }

    private void showDebugGroup(boolean opening, String side) {

        String s = String.format("%s Initialization for Minecraft %s and Forge %s %s in %s", Properties.MOD_NAME, Properties.MC_VERSION,
                Properties.FORGE_VERSION, opening ? "started" : "finished", side);

        // Just aesthetics...
        String b1 = "", b2 = "";
        for (int i = 0; i < s.length(); i++) {
            b1 = b1 + (opening ? "=" : "-");
            b2 = b2 + (opening ? "-" : "=");
        }

        AFMLogger.log(b1);
        AFMLogger.log(s);
        AFMLogger.log(b2);
    }
}
