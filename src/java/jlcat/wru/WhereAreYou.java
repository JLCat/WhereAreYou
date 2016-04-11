package jlcat.wru;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WhereAreYou.MOD_ID, name = WhereAreYou.MOD_NAME, version = WhereAreYou.VERSION, clientSideOnly = true)
public class WhereAreYou {

	public static final String MOD_ID = "wru";
	public static final String MOD_NAME = "Where Are You";
	public static final String VERSION = "1.9-1.1.0";

	public boolean showPlayerIcon = false;
	public boolean showGameInfo = true;

	@Instance(WhereAreYou.MOD_ID)
	public static WhereAreYou instance = new WhereAreYou();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(KeyInputHandler.getInstance());
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(ModEventHandler.getInstance());
	}
}