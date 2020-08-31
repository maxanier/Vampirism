package de.teamlapen.vampirism.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import de.teamlapen.lib.lib.util.BasicCommand;
import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.api.entity.player.IFactionPlayer;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;


public class LordCommand extends BasicCommand {

    private static final SimpleCommandExceptionType NO_FACTION = new SimpleCommandExceptionType(new TranslationTextComponent("command.vampirism.base.lord.no_faction"));
    private static final SimpleCommandExceptionType LEVEL_UP_FAILED = new SimpleCommandExceptionType(new TranslationTextComponent("command.vampirism.base.lord.level_failed"));
    private static final SimpleCommandExceptionType LORD_FAILED = new SimpleCommandExceptionType(new TranslationTextComponent("command.vampirism.base.lord.failed"));


    public static ArgumentBuilder<CommandSource, ?> register() {

        return Commands.literal("lord-level")
                .requires(context -> context.hasPermissionLevel(PERMISSION_LEVEL_CHEAT))
                .then(Commands.argument("level", IntegerArgumentType.integer(0))
                        .executes(context -> setLevel(context, IntegerArgumentType.getInteger(context, "level"), Lists.newArrayList(context.getSource().asPlayer())))
                        .then(Commands.argument("player", EntityArgument.entities())
                                .executes(context -> setLevel(context, IntegerArgumentType.getInteger(context, "level"), EntityArgument.getPlayers(context, "player")))));

    }

    private static int setLevel(CommandContext<CommandSource> context, int level, Collection<ServerPlayerEntity> players) throws CommandSyntaxException {
        for (ServerPlayerEntity player : players) {
            FactionPlayerHandler handler = FactionPlayerHandler.get(player);
            IPlayableFaction<? extends IFactionPlayer<?>> faction = handler.getCurrentFaction();
            if (faction == null) {
                throw NO_FACTION.create();
            }
            int maxLevel = faction.getHighestReachableLevel();
            if (handler.getCurrentLevel() < maxLevel) {
                if (!handler.setFactionLevel(faction, maxLevel)) {
                    throw LEVEL_UP_FAILED.create();
                }
            }
            level = Math.min(level, faction.getHighestLordLevel());

            if (handler.setLordLevel(level)) {
                context.getSource().sendFeedback(new TranslationTextComponent("command.vampirism.base.lord.successful", player.getName(), faction.getName(), level), true);
            } else {
                throw LORD_FAILED.create();
            }


        }
        return 0;
    }

}
