package miro.bassscript.baritone;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.Settings;
import net.minecraft.block.Blocks;

import java.util.List;

public class BaritoneHandler {
     private final Settings settings;
     private final IBaritone baritone;

     public BaritoneHandler() {
          settings = BaritoneAPI.getSettings();
          baritone = BaritoneAPI.getProvider().getPrimaryBaritone();
     }

     public void initSettings() {
          getSettings().allowInventory.value = true;
          getSettings().autoTool.value = true;
          getSettings().itemSaver.value = true;

          getSettings().blockPlacementPenalty.value = 14D;
          getSettings().walkOnWaterOnePenalty.value = 4D;
          getSettings().allowWaterBucketFall.value = true;

          getSettings().allowDiagonalDescend.value = true;
          getSettings().allowDiagonalAscend.value = true;
          getSettings().allowParkour.value = true;
          getSettings().allowParkourPlace.value = true;

          getSettings().acceptableThrowawayItems.value = List.of(
                  Blocks.DIRT.asItem(),
                  Blocks.COBBLESTONE.asItem(),
                  Blocks.COBBLED_DEEPSLATE.asItem(),
                  Blocks.NETHERRACK.asItem(),
                  Blocks.STONE.asItem(),
                  Blocks.END_STONE.asItem()
          );

          // newish blocks
          getSettings().blocksToAvoid.value = List.of(
                  Blocks.FLOWERING_AZALEA, Blocks.AZALEA,
                  Blocks.POWDER_SNOW,
                  Blocks.BIG_DRIPLEAF, Blocks.BIG_DRIPLEAF_STEM,
                  Blocks.CAVE_VINES, Blocks.CAVE_VINES_PLANT,
                  Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT, Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT,
                  Blocks.SWEET_BERRY_BUSH,
                  Blocks.WARPED_ROOTS, Blocks.CRIMSON_ROOTS,
                  Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD, Blocks.AMETHYST_CLUSTER,
                  Blocks.SCULK, Blocks.SCULK_VEIN, Blocks.SCULK_SENSOR, Blocks.SCULK_SHRIEKER, Blocks.SCULK_CATALYST
          );

          getSettings().blocksToAvoidBreaking.value = List.of(
                  Blocks.CRAFTING_TABLE, Blocks.FURNACE, Blocks.CHEST, Blocks.TRAPPED_CHEST,
                  Blocks.SCULK, Blocks.SCULK_VEIN, Blocks.SCULK_SENSOR, Blocks.SCULK_SHRIEKER, Blocks.SCULK_CATALYST
          );

          getSettings().avoidance.value = true;
          getSettings().mobAvoidanceRadius.value = 6;
          getSettings().mobSpawnerAvoidanceRadius.value = 14;
          getSettings().mobAvoidanceCoefficient.value = 1.3;

          getSettings().rightClickContainerOnArrival.value = false;

          getSettings().renderPathAsLine.value = true;
          //          getSettings().freeLook.value = false;
          //          getSettings().randomLooking.value = 0D;
          //          getSettings().randomLooking113.value = 0D;
          //          getSettings().smoothLook.value = false;

          getSettings().mineScanDroppedItems.value = true;
          getSettings().mineDropLoiterDurationMSThanksLouca.value = 100L;

          getSettings().chatDebug.value = true;
     }

     public IBaritone getBaritone() {
          return baritone;
     }

     public Settings getSettings() {
          return settings;
     }
}
