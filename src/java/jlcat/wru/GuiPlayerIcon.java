package jlcat.wru;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPlayerIcon extends Gui {

	private static GuiPlayerIcon instance = new GuiPlayerIcon();;
	private Minecraft mc = Minecraft.getMinecraft();
	private final int icon_size = 16;
	private final int icon_space = 10;

	private GuiPlayerIcon() {
		super();
	}

	public static GuiPlayerIcon getInstance() {
		return instance;
	}

	public void displayPlayerIcon() {

		this.mc.renderEngine.bindTexture(new ResourceLocation(WhereAreYou.MOD_ID, "textures/playericon.png"));

		ScaledResolution scaledresolution = new ScaledResolution(mc);

		int width = scaledresolution.getScaledWidth() - icon_size;
		int height = scaledresolution.getScaledHeight() - icon_size;

		int cWidth = Math.round(width / 2);
		int cHeight = Math.round(height / 2);

		List<Entity> entities = mc.theWorld.loadedEntityList;

		for (int z = 0; z < entities.size(); z++) {

			Entity entity = entities.get(z);
			if (mc.thePlayer.getDistanceSqToEntity(entity) > 20 * 20) {
				if (entity != mc.thePlayer && entity instanceof EntityPlayer) {

					int pPosX = (int) Math.round(mc.thePlayer.posX);
					int pPosZ = (int) Math.round(mc.thePlayer.posZ);
					int ePosX = (int) Math.round(entity.posX);
					int ePosZ = (int) Math.round(entity.posZ);
					int dPosX = pPosX - ePosX;
					int dPosZ = pPosZ - ePosZ;

					// X, Z
					double playerRotaXZ = Math.toRadians(mc.thePlayer.rotationYaw);
					double vPlayerX = -Math.sin(playerRotaXZ);
					double vPlayerZ = Math.cos(playerRotaXZ);
					double angleXZ = Math.atan2(vPlayerX, vPlayerZ) - Math.atan2(dPosX, dPosZ) + Math.PI;

					int showX = cWidth + (int) (cWidth * Math.sin(angleXZ));

					if (showX < icon_space) {
						showX = icon_space;
					} else if (showX > width - (icon_space + icon_size)) {
						showX = width - (icon_space + icon_size);
					}

					int showY = cHeight - (int) (cHeight * Math.cos(angleXZ));

					if (showY < icon_space) {
						showY = icon_space;
					} else if (showY > height - (icon_space + icon_size)) {
						showY = height - (icon_space + icon_size);
					}

					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glPushMatrix();
					this.drawTexturedModalRect(showX, showY, 0, 0, icon_size, icon_size);
					GL11.glPopMatrix();
				}
			}
		}
	}
}
