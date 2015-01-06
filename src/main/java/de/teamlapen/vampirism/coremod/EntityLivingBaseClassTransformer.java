package de.teamlapen.vampirism.coremod;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import de.teamlapen.vampirism.util.Logger;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * EntityLivingBase class transformer, which inserts a few hooks 
 * @author Max
 *
 */
public class EntityLivingBaseClassTransformer implements IClassTransformer {

	private final static String TAG="EntityLivingBaseTransformer";
	private final static String CLASS_ENTITYLIVINGBASE="net.minecraft.entity.EntityLivingBase";
	private final static String CLASS_ENTITYLIVINGBASE_NOTCH="sv";
	private final static String METHOD_IPA="isPotionActive";
	private final static String METHOD_IPA_NOTCH="func_70644_a";
	//private final static String OB_METHOD_IPA="func_82165_m";
	private final static String CLASS_POTION_SRG="net/minecraft/potion/Potion";
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		
		
		if(name.equals(CLASS_ENTITYLIVINGBASE_NOTCH)){
			Logger.i(TAG, "INSIDE OBFUSCATED ENTITYLIVINGBASE CLASS - ABOUT TO PATCH: "+name+" "+transformedName);
			return applyPatch(name, basicClass,true);
		}
		else if(name.equals(CLASS_ENTITYLIVINGBASE)){
			Logger.i(TAG, "INSIDE ENTITYLIVINGBASE CLASS - ABOUT TO PATCH: "+name);
			return applyPatch(name, basicClass,false);
		}
		return basicClass;
		
	}
	
	public byte[] applyPatch(String name,byte[] basicClass,boolean obfuscated){
		
		String iPAMethodName="";
		if(obfuscated){
			iPAMethodName=METHOD_IPA_NOTCH;
		}
		else{
			iPAMethodName=METHOD_IPA;
		}
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader=new ClassReader(basicClass);
		classReader.accept(classNode, 0);
		
		Iterator<MethodNode> methods = classNode.methods.iterator();
		while(methods.hasNext()){
			MethodNode m = methods.next();
			if(m.name.equals(iPAMethodName)&&m.desc.equals("(L"+CLASS_POTION_SRG+";)Z")){
				Logger.i(TAG, "INSIDE isPotionActive METHOD");
				
				//Inject if clause
				InsnList toIn = new InsnList();
				toIn.add(new VarInsnNode(Opcodes.ALOAD,0));
				toIn.add(new VarInsnNode(Opcodes.ALOAD,1));
				toIn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,"de/teamlapen/vampirism/coremod/CoreHandler", "shouldOverrideNightVision", "(Ljava/lang/Object;L"+CLASS_POTION_SRG+";)Z", false));
				LabelNode l1=new LabelNode();
				toIn.add(new JumpInsnNode(Opcodes.IFEQ,l1));
				LabelNode l2=new LabelNode();
				toIn.add(l2);
				toIn.add(new InsnNode(Opcodes.ICONST_1));
				toIn.add(new InsnNode(Opcodes.IRETURN));
				toIn.add(l1);
				toIn.add(new FrameNode(Opcodes.F_SAME,0,null,0,null));
				
				
				m.instructions.insert(toIn);
				Logger.i(TAG, "PATCH COMPLETE");
				break;
			}
		}
		

		//ASM specific for cleaning up and returning the final bytes for JVM processing.
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS );
		classNode.accept(writer);
		return writer.toByteArray();
			
	}

}
