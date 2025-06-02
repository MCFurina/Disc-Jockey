package semmiedev.disc_jockey.gui.hud;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;

public class BlocksOverlay {
    public static ItemStack[] itemStacks;
    public static int[] amounts;
    public static int amountOfNoteBlocks;

    private static final ItemStack NOTE_BLOCK = Blocks.NOTE_BLOCK.asItem().getDefaultStack();

    // 新增ARGB工具方法，兼容Fabric/Minecraft所有版本
    public static int getArgb(int a, int r, int g, int b) {
        return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        if (itemStacks != null) {
            context.fill(2, 2, 62, (itemStacks.length + 1) * 20 + 7, getArgb(255, 22, 22, 27));
            context.fill(4, 4, 60, (itemStacks.length + 1) * 20 + 5, getArgb(255, 42, 42, 47));

            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer textRenderer = client.textRenderer;
            ItemRenderer itemRenderer = client.getItemRenderer();

            context.drawText(textRenderer, " × "+amountOfNoteBlocks, 26, 13, 0xFFFFFF, true);
            context.drawItem(NOTE_BLOCK, 6, 6);

            for (int i = 0; i < itemStacks.length; i++) {
                context.drawText(textRenderer, " × "+amounts[i], 26, 13 + 20 * (i + 1), 0xFFFFFF, true);
                context.drawItem(itemStacks[i], 6, 6 + 20 * (i + 1));
            }
        }
    }
}