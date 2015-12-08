package de.teamlapen.vampirism.config;

import de.teamlapen.lib.config.BalanceValues;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Main balance configuration handler
 */
public class Balance {
    public static BalanceLeveling leveling;
    public static BalanceMobProps mobProps;
    public static BalanceVampirePlayer vp;
    public static BalanceHunterPlayer hp;
    private final static String TAG="Balance";
    private final static Map<String,BalanceValues> categories=new HashMap<String, BalanceValues>();

    public static void init(File configDir, boolean inDev){
        File balanceDir=new File(configDir,"balance");
       leveling=new BalanceLeveling(balanceDir);
        mobProps=new BalanceMobProps(balanceDir);
        vp=new BalanceVampirePlayer(balanceDir);
        hp=new BalanceHunterPlayer(balanceDir);
        categories.put(leveling.getName(),leveling);
        categories.put(mobProps.getName(),mobProps);
        categories.put(vp.getName(),vp);
        categories.put(hp.getName(),hp);
        if(inDev&&Configs.resetConfigurationInDev){
            reset(null);
        }
        loadConfiguration();
        VampirismMod.log.i(TAG,"Loaded balance configuration");
    }

    private static void loadConfiguration(){
        for(BalanceValues values:categories.values()) {
            values.loadBalance();
        }
    }

    public static void onConfigurationChanged(){
        VampirismMod.log.i(TAG,"Reloading changed balance configuration");
        loadConfiguration();
    }

    /**
     * Resets the matching balance category.
     * @param category False if category is not found
     * @return
     */
    public static  boolean reset(String category){
        if (category == null || category.equals("all")) {
            for(BalanceValues values:categories.values()){
                values.reset();
            }
            return true;
        }
        BalanceValues values=categories.get(category);
        if(values!=null){
            values.reset();
            return true;
        }
        return false;
    }

    public static Map<String,BalanceValues> getCategories(){
        return categories;
    }

}
