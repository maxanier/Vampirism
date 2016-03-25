package de.teamlapen.vampirism.network;

import de.teamlapen.lib.HelperLib;
import de.teamlapen.lib.lib.network.ISyncable;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.player.IFactionPlayer;
import de.teamlapen.vampirism.api.entity.player.actions.IAction;
import de.teamlapen.vampirism.api.entity.player.actions.IActionHandler;
import de.teamlapen.vampirism.api.entity.player.skills.ISkill;
import de.teamlapen.vampirism.api.entity.player.skills.ISkillHandler;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import de.teamlapen.vampirism.entity.player.actions.ActionHandler;
import de.teamlapen.vampirism.entity.player.skills.SkillHandler;
import de.teamlapen.vampirism.entity.player.vampire.VampirePlayer;
import de.teamlapen.vampirism.inventory.HunterTrainerContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Sends any input related event to the server
 */
public class InputEventPacket implements IMessage {


    private final static String TAG = "InputEventPacket";
    public static String SUCKBLOOD = "sb";
    public static String TOGGLEACTION = "ta";
    public static String UNLOCKSKILL = "us";
    public static String RESETSKILL = "rs";
    public static String TRAINERLEVELUP = "tl";
    private final String SPLIT = "-";
    private String param;
    private String action;

    /**
     * Don't use
     */
    public InputEventPacket() {

    }

    public InputEventPacket(String action, String param) {
        this.action = action;
        this.param = param;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        String[] s = ByteBufUtils.readUTF8String(buf).split(SPLIT);
        action = s[0];
        if (s.length > 1) {
            param = s[1];
        } else {
            param = "";
        }

    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, action + SPLIT + param);

    }

    public static class Handler extends AbstractServerMessageHandler<InputEventPacket> {

        @Override
        public IMessage handleServerMessage(EntityPlayer player, InputEventPacket message, MessageContext ctx) {
            if (message.action == null)
                return null;
            IFactionPlayer factionPlayer = FactionPlayerHandler.get(player).getCurrentFactionPlayer();
            if (message.action.equals(SUCKBLOOD)) {
                int id = 0;
                try {
                    id = Integer.parseInt(message.param);
                } catch (NumberFormatException e) {
                    VampirismMod.log.e(TAG, e, "Receiving invalid param for %s", message.action);
                }
                if (id != 0) {
                    VampirePlayer.get(player).biteEntity(id);
                }
            } else if (message.action.equals(TOGGLEACTION)) {
                int id = -1;
                try {
                    id = Integer.parseInt(message.param);
                } catch (NumberFormatException e) {
                    VampirismMod.log.e(TAG, e, "Receiving invalid param for %s", message.action);
                }
                if (id != -1) {
                    if (factionPlayer != null) {
                        IActionHandler actionHandler = VampirismAPI.getFactionPlayerHandler(player).getCurrentFactionPlayer().getActionHandler();
                        IAction action = ((ActionHandler) actionHandler).getActionFromId(id);
                        if (action != null) {
                            IAction.PERM r = VampirePlayer.get(player).getActionHandler().toggleAction(action);
                            switch (r) {
                                case NOT_UNLOCKED:
                                    player.addChatMessage(new ChatComponentTranslation("text.vampirism.action.not_unlocked"));
                                    break;
                                case DISABLED:
                                    player.addChatMessage(new ChatComponentTranslation("text.vampirism.action.deactivated_by_serveradmin"));
                                    break;
                                case COOLDOWN:
                                    player.addChatMessage(new ChatComponentTranslation("text.vampirism.action.cooldown_not_over"));
                                    break;
                            }
                        } else {
                            VampirismMod.log.e(TAG, "Failed to find action with id %d", id);
                        }
                    } else {
                        VampirismMod.log.e(TAG, "Player %s is in no faction, so he cannot use action %d", player, id);
                    }


                }

            } else if (message.action.equals(UNLOCKSKILL)) {
                if (factionPlayer != null) {
                    ISkill skill = VampirismAPI.skillRegistry().getSkill(factionPlayer.getFaction(), message.param);
                    if (skill != null) {
                        ISkillHandler skillHandler = factionPlayer.getSkillHandler();
                        ISkillHandler.Result result = skillHandler.canSkillBeEnabled(skill);
                        if (result == ISkillHandler.Result.OK) {
                            skillHandler.enableSkill(skill);
                            if (factionPlayer instanceof ISyncable.ISyncableExtendedProperties && skillHandler instanceof SkillHandler) {
                                //TODO does this cause problems with addons?
                                NBTTagCompound sync = new NBTTagCompound();
                                ((SkillHandler) skillHandler).writeUpdateForClient(sync);
                                HelperLib.sync((ISyncable.ISyncableExtendedProperties) factionPlayer, sync, factionPlayer.getRepresentingPlayer(), false);
                            }

                        } else {
                            VampirismMod.log.w(TAG, "Skill %s cannot be activated for %s (%s)", skill, player, result);
                        }
                    } else {
                        VampirismMod.log.w(TAG, "Skill %s was not found so %s cannot activate it", message.param, player);
                    }
                } else {
                    VampirismMod.log.e(TAG, "Player %s is in no faction, so he cannot unlock skills");
                }


            } else if (message.action.equals(RESETSKILL)) {
                if (factionPlayer != null) {
                    ISkillHandler skillHandler = factionPlayer.getSkillHandler();
                    skillHandler.resetSkills();
                    if (factionPlayer instanceof ISyncable.ISyncableExtendedProperties && skillHandler instanceof SkillHandler) {
                        //TODO does this cause problems with addons?
                        NBTTagCompound sync = new NBTTagCompound();
                        ((SkillHandler) skillHandler).writeUpdateForClient(sync);
                        HelperLib.sync((ISyncable.ISyncableExtendedProperties) factionPlayer, sync, factionPlayer.getRepresentingPlayer(), false);
                    }
                } else {
                    VampirismMod.log.e(TAG, "Player %s is in no faction, so he cannot reset skills");
                }
            } else if (message.action.equals(TRAINERLEVELUP)) {
                if (player.openContainer instanceof HunterTrainerContainer) {
                    ((HunterTrainerContainer) player.openContainer).onLevelupClicked();
                }
            }
            return null;
        }

        @Override
        protected boolean handleOnMainThread() {
            return true;
        }
    }

}
