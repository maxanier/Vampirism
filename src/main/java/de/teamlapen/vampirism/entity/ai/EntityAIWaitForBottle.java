package de.teamlapen.vampirism.entity.ai;

import de.teamlapen.vampirism.ModItems;
import de.teamlapen.vampirism.entity.minions.EntityRemoteVampireMinion;
import de.teamlapen.vampirism.entity.minions.IMinionLord;
import de.teamlapen.vampirism.entity.minions.MinionHelper;
import de.teamlapen.vampirism.item.ItemBloodBottle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Minion waits for a blood bottle, if it does not have one or it is full.
 * @author Max
 *
 */
public class EntityAIWaitForBottle extends EntityAIBase {

	private final EntityRemoteVampireMinion minion;
	private EntityLivingBase lord;
	
	public EntityAIWaitForBottle(EntityRemoteVampireMinion minion) {
		super();
		this.minion = minion;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		IMinionLord l=minion.getLord();
		if(l==null)return false;
		lord=l.getRepresentingEntity();
		if(lord.getDistanceSqToEntity(minion.getRepresentingEntity())>EntityAIMoveToLord.TELEPORT_SQ_DISTANCE+5||!lord.isEntityAlive())return false;
		
		return checkBottle();
	}
	
	public void resetTask(){
		MinionHelper.sendMessageToLord(minion, "stop beg bottle to "+lord);
		lord=null;
	}
	
	@Override
	public void startExecuting(){
		MinionHelper.sendMessageToLord(minion, "start beg bottle to "+lord);
	}
	
	@Override
	public void updateTask(){
		minion.getRepresentingEntity().getLookHelper().setLookPosition(lord.posX, lord.posY + (double)lord.getEyeHeight(), this.lord.posZ, 10.0F, (float)this.minion.getRepresentingEntity().getVerticalFaceSpeed());
	}
	
	@Override
	public boolean continueExecuting(){
		return lord.isEntityAlive()&&checkBottle();
	}
	
	/**
	 * @return if a bottle is required
	 */
	private boolean checkBottle(){
		ItemStack stack=minion.getRepresentingEntity().getEquipmentInSlot(0);
		if(stack!=null){
			if(stack.getItem().equals(Items.glass_bottle)){
				return false;
			}
			if(stack.getItem().equals(ModItems.bloodBottle)){
				return ItemBloodBottle.getBlood(stack)==ItemBloodBottle.MAX_BLOOD;
			}
		}
		return true;
	}

}
