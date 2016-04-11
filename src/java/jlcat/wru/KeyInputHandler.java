package jlcat.wru;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler {

	private static KeyInputHandler instance = new KeyInputHandler();
	private Minecraft mc = Minecraft.getMinecraft();

	private static KeyBinding showPlayer;
	private static KeyBinding showInfo;
	private static KeyBinding sendCood;

	private KeyInputHandler() {
	}

	public static KeyInputHandler getInstance() {
		init();
		return instance;
	}

	public static void init() {
		showPlayer = new KeyBinding("key.showplayer", 41, "key.categories.wru");
		ClientRegistry.registerKeyBinding(showPlayer);

		showInfo = new KeyBinding("key.showInfo", Keyboard.KEY_J, "key.categories.wru");
		ClientRegistry.registerKeyBinding(showInfo);

		sendCood = new KeyBinding("key.sendCoords", Keyboard.KEY_K, "key.categories.wru");
		ClientRegistry.registerKeyBinding(sendCood);
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {

		WorldClient world = mc.theWorld;
		EntityPlayer player = mc.thePlayer;

		if (!world.isRemote)
			return;

		if (showPlayer.isPressed()) {
			WhereAreYou.instance.showPlayerIcon = !WhereAreYou.instance.showPlayerIcon;
			if (WhereAreYou.instance.showPlayerIcon)
				player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + I18n.translateToLocal("msg.mod") + TextFormatting.WHITE.toString() + I18n.translateToLocal("msg.showplayericon_on")));
			else
				player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + I18n.translateToLocal("msg.mod") + TextFormatting.WHITE.toString() + I18n.translateToLocal("msg.showplayericon_off")));
		}

		if (showInfo.isPressed()) {
			WhereAreYou.instance.showGameInfo = !WhereAreYou.instance.showGameInfo;
			if (WhereAreYou.instance.showGameInfo)
				player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + I18n.translateToLocal("msg.mod") + TextFormatting.WHITE.toString() + I18n.translateToLocal("msg.showgameinfo_on")));
			else
				player.addChatComponentMessage(new TextComponentString(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + I18n.translateToLocal("msg.mod") + TextFormatting.WHITE.toString() + I18n.translateToLocal("msg.showgameinfo_off")));
		}

		if (sendCood.isPressed())
			((EntityPlayerSP) player).sendChatMessage(I18n.translateToLocal("msg.mycoords") + String.format("< %d, %d > , < %d >", (int) mc.thePlayer.posX, (int) mc.thePlayer.posZ, (int) mc.thePlayer.posY));
	}

}