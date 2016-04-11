package jlcat.wru;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.translation.I18n;

public class GuiGameInfo {

	private static GuiGameInfo instance = new GuiGameInfo();
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fontRender = mc.fontRendererObj;

	public static GuiGameInfo getInstance() {
		return instance;
	}

	public void displayGameInfo() {

		EntityPlayer player = mc.thePlayer;
		WorldClient world = mc.theWorld;

		int posX = (int) player.posX;
		int posY = (int) player.posY;
		int posZ = (int) player.posZ;
		EnumFacing facing = player.getHorizontalFacing();

		long worldTime = world.getWorldTime();
		int hour = (6 + (int) worldTime / 1000) % 24;
		int min = (int) (worldTime % 1000 / 1000.0 * 60.0);

		fontRender.drawStringWithShadow(I18n.translateToLocal("info.time") + String.format("%02d:%02d", hour, min), 5, 10, 0xFFFFFF);
		fontRender.drawStringWithShadow(I18n.translateToLocal("info.coords") + String.format("< %d, %d >", posX, posZ), 5, 20, 0xFFFFFF);
		fontRender.drawStringWithShadow(I18n.translateToLocal("info.height") + String.format("< %d >", posY), 5, 30, 0xFFFFFF);
		fontRender.drawStringWithShadow(I18n.translateToLocal("info.facing") + String.format("%s", facing), 5, 40, 0xFFFFFF);
	}
}
