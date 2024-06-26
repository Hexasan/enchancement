/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.enchancement.mixin.scatter;

import moriyashiine.enchancement.common.init.ModEnchantments;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Predicate;

@Mixin({PlayerEntity.class, HostileEntity.class})
public class ScatterCrossbowMixin {
	@ModifyVariable(method = "getProjectileType", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/RangedWeaponItem;getHeldProjectiles()Ljava/util/function/Predicate;"))
	private Predicate<ItemStack> enchancement$scatter(Predicate<ItemStack> value, ItemStack stack) {
		if (EnchancementUtil.hasEnchantment(ModEnchantments.SCATTER, stack)) {
			return value.or(projectile -> projectile.isOf(Items.AMETHYST_SHARD));
		}
		return value;
	}
}
