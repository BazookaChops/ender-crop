package io.github.mathiasdj.endercrop.handler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import io.github.mathiasdj.endercrop.init.ModBlocks;

public class UseHoeEventHandler {

    @SubscribeEvent
    public void useHoe(UseHoeEvent event) {
        if (event.getResult() != Event.Result.DEFAULT || event.isCanceled()) {
            return;
        }

        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block == Blocks.END_STONE) {
            boolean canHoe = false;
            ItemStack[] heldItems = {player.getHeldItem(EnumHand.MAIN_HAND), player.getHeldItem(EnumHand.OFF_HAND)};
            for (ItemStack heldItem : heldItems) {
                if (heldItem != null && heldItem.getItem() instanceof ItemHoe)
                    canHoe = canHoe || (EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByID(34), heldItem) > 0 || player.capabilities.isCreativeMode);
            }
            if (canHoe) {
                world.setBlockState(pos, ModBlocks.TILLED_END_STONE.getDefaultState());
                event.setResult(Event.Result.ALLOW);
            } else {
                if (!world.isRemote) {
                    player.addChatComponentMessage(
                            new TextComponentString("\u00A77\u00A7o" + I18n.translateToLocal("endercrop.alert.hoe"))
                    );
                }
            }
        }
    }
}