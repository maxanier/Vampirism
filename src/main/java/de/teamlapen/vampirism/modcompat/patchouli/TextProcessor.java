package de.teamlapen.vampirism.modcompat.patchouli;

import de.teamlapen.lib.lib.util.UtilLib;
import de.teamlapen.vampirism.core.ModBlocks;
import de.teamlapen.vampirism.core.ModEntities;
import de.teamlapen.vampirism.core.ModItems;
import de.teamlapen.vampirism.player.hunter.HunterLevelingConf;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;

@SuppressWarnings("unused")
public class TextProcessor implements IComponentProcessor {

    private static String translate(String key) {
        String context = key.replace("guide.vampirism.", "");
        Object[] params;

        switch (context) {
            case "hunter.getting_started.become":
                params = new Object[]{ModEntities.hunter_trainer.getName().getFormattedText(), ModItems.injection_garlic.getName().getFormattedText()};
                break;
            case "hunter.leveling.train2.text":
                params = new Object[]{ModBlocks.hunter_table.getNameTextComponent().getFormattedText()};
                break;
            case "hunter.leveling.to_reach1":
                params = new Object[]{HunterLevelingConf.instance().BASIC_HUNTER_MIN_LEVEL + "-" + HunterLevelingConf.instance().BASIC_HUNTER_MAX_LEVEL};
                break;
            case "hunter.leveling.to_reach2":
                params = new Object[]{HunterLevelingConf.instance().TABLE_MIN_LEVEL + "-" + HunterLevelingConf.instance().TABLE_MAX_LEVEL};
                break;
            default:
                params = new Object[0];
        }

        return UtilLib.translate(key, params);
    }

    private String translationKey;
    private String header;

    @Override
    public String process(String s) {
        if ("parsedText".equals(s)) {
            if (translationKey != null) {
                return translate(translationKey);
            }
        } else if ("header".equals(s)) {
            return header == null ? null : translate(header);
        }
        return null;
    }

    @Override
    public void setup(IVariableProvider<String> iVariableProvider) {
        translationKey = iVariableProvider.get("key");
        header = iVariableProvider.has("header") ? iVariableProvider.get("header") : null;
    }
}
