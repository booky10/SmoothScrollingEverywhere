package me.shedaniel.smoothscrollingeverywhere.mixin;

import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.impl.EasingMethod;
import me.shedaniel.smoothscrollingeverywhere.SmoothScrollingEverywhere;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClothConfigInitializer.class)
public class ClothConfigInitializerMixin {

    @Inject(method = "getEasingMethod", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getEasingMethod(CallbackInfoReturnable<EasingMethod> returnable) {
        returnable.setReturnValue(SmoothScrollingEverywhere.getEasingMethod());
    }

    @Inject(method = "getScrollDuration", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getScrollDuration(CallbackInfoReturnable<Long> returnable) {
        returnable.setReturnValue(SmoothScrollingEverywhere.getScrollDuration());
    }

    @Inject(method = "getScrollStep", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getScrollStep(CallbackInfoReturnable<Double> returnable) {
        returnable.setReturnValue(SmoothScrollingEverywhere.getScrollStep());
    }

    @Inject(method = "getBounceBackMultiplier", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getBounceBackMultiplier(CallbackInfoReturnable<Double> returnable) {
        returnable.setReturnValue(SmoothScrollingEverywhere.getBounceBackMultiplier());
    }
}
