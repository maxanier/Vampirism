/*
 * Minecraft Forge
 * Copyright (c) 2016-2018.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package de.teamlapen.lib.lib.config.forge;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class ConfigCategory implements Map<String, Property> {
    public static final String COMMENT_SEPARATOR = "##########################################################################################################";
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String ALLOWED_CHARS = "._-";
    public static final CharMatcher allowedProperties = CharMatcher.javaLetterOrDigit().or(CharMatcher.anyOf(ALLOWED_CHARS));

    public static String getQualifiedName(String name, ConfigCategory parent) {
        return (parent == null ? name : parent.getQualifiedName() + Configuration.CATEGORY_SPLITTER + name);
    }

    public final ConfigCategory parent;
    private String name;
    private String comment;
    private String languagekey;
    private ArrayList<ConfigCategory> children = new ArrayList<ConfigCategory>();
    private Map<String, Property> properties = new TreeMap<String, Property>();
    @SuppressWarnings("unused")
    private int propNumber = 0;
    private boolean changed = false;
    private boolean requiresWorldRestart = false;
    private boolean showInGui = true;
    private boolean requiresMcRestart = false;
    private List<String> propertyOrder = null;

    public ConfigCategory(String name) {
        this(name, null);
    }

    public ConfigCategory(String name, ConfigCategory parent) {
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    @Override
    public void clear() {
        changed = true;
        properties.clear();
    }

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    @Override
    public boolean containsKey(Object key) {
        return properties.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return properties.containsValue(value);
    }

    @Override //Immutable copy, changes will NOT be reflected in this category
    public Set<Entry<String, Property>> entrySet() {
        return ImmutableSet.copyOf(properties.entrySet());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConfigCategory) {
            ConfigCategory cat = (ConfigCategory) obj;
            return name.equals(cat.name) && children.equals(cat.children);
        }

        return false;
    }

    public Property get(String key) {
        return properties.get(key);
    }

    @Override
    public Property get(Object key) {
        return properties.get(key);
    }

    public Set<ConfigCategory> getChildren() {
        return ImmutableSet.copyOf(children);
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ConfigCategory getFirstParent() {
        return (parent == null ? this : parent.getFirstParent());
    }

    public String getLanguagekey() {
        if (this.languagekey != null)
            return this.languagekey;
        else
            return getQualifiedName();
    }

    public String getName() {
        return name;
    }

    public List<Property> getOrderedValues() {
        if (this.propertyOrder != null) {
            ArrayList<Property> set = new ArrayList<Property>();
            for (String key : this.propertyOrder)
                if (properties.containsKey(key))
                    set.add(properties.get(key));

            return ImmutableList.copyOf(set);
        } else
            return ImmutableList.copyOf(properties.values());
    }

    public List<String> getPropertyOrder() {
        if (this.propertyOrder != null)
            return ImmutableList.copyOf(this.propertyOrder);
        else
            return ImmutableList.copyOf(properties.keySet());
    }

    public ConfigCategory setPropertyOrder(List<String> propertyOrder) {
        this.propertyOrder = propertyOrder;
        for (String s : properties.keySet())
            if (!propertyOrder.contains(s))
                propertyOrder.add(s);
        return this;
    }

    public String getQualifiedName() {
        return getQualifiedName(name, parent);
    }

    public Map<String, Property> getValues() {
        return ImmutableMap.copyOf(properties);
    }

    public boolean hasChanged() {
        if (changed) return true;
        for (Property prop : properties.values()) {
            if (prop.hasChanged()) return true;
        }
        return false;
    }

    public boolean isChild() {
        return parent != null;
    }

    @Override
    public boolean isEmpty() {
        return properties.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return properties.keySet();
    }

    @Override
    public Property put(String key, Property value) {
        changed = true;
        if (this.propertyOrder != null && !this.propertyOrder.contains(key))
            this.propertyOrder.add(key);
        return properties.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Property> m) {
        changed = true;
        if (this.propertyOrder != null)
            for (String key : m.keySet())
                if (!this.propertyOrder.contains(key))
                    this.propertyOrder.add(key);
        properties.putAll(m);
    }

    @Override
    public Property remove(Object key) {
        changed = true;
        return properties.remove(key);
    }

    public void removeChild(ConfigCategory child) {
        if (children.contains(child)) {
            children.remove(child);
            changed = true;
        }
    }

    /**
     * Gets whether or not this ConfigCategory requires Minecraft to be restarted when changed.
     * Defaults to false unless set to true.
     */
    public boolean requiresMcRestart() {
        return this.requiresMcRestart;
    }

    /**
     * Returns whether or not this category is able to be edited while a world is running using the in-game Mod Options screen
     * as well as the Mods list screen, or only from the Mods list screen.
     */
    public boolean requiresWorldRestart() {
        return this.requiresWorldRestart;
    }

    public ConfigCategory setLanguageKey(String languagekey) {
        this.languagekey = languagekey;
        return this;
    }

    /**
     * Sets whether or not this ConfigCategory requires Minecraft to be restarted when changed.
     * Defaults to false. Only set this flag to true if ALL child properties/categories require
     * Minecraft to be restarted when changed. Setting this flag will also prevent modification
     * of the child properties/categories while a world is running.
     */
    public ConfigCategory setRequiresMcRestart(boolean requiresMcRestart) {
        this.requiresMcRestart = this.requiresWorldRestart = requiresMcRestart;
        return this;
    }

    /**
     * Sets the flag for whether or not this category can be edited while a world is running. Care should be taken to ensure
     * that only properties that are truly dynamic can be changed from the in-game options menu. Only set this flag to
     * true if all child properties/categories are unable to be modified while a world is running.
     */
    public ConfigCategory setRequiresWorldRestart(boolean requiresWorldRestart) {
        this.requiresWorldRestart = requiresWorldRestart;
        return this;
    }

    /**
     * Sets whether or not this ConfigCategory should be allowed to show on config GUIs.
     * Defaults to true.
     */
    public ConfigCategory setShowInGui(boolean showInGui) {
        this.showInGui = showInGui;
        return this;
    }

    /**
     * Gets whether or not this ConfigCategory should be allowed to show on config GUIs.
     * Defaults to true unless set to false.
     */
    public boolean showInGui() {
        return showInGui;
    }

    //Map bouncer functions for compatibility with older mods, to be removed once all mods stop using it.
    @Override
    public int size() {
        return properties.size();
    }

    @Override
    public Collection<Property> values() {
        return properties.values();
    }

    public void write(BufferedWriter out, int indent) throws IOException {
        String pad0 = getIndent(indent);
        String pad1 = getIndent(indent + 1);
        String pad2 = getIndent(indent + 2);

        if (comment != null && !comment.isEmpty()) {
            write(out, pad0, COMMENT_SEPARATOR);
            write(out, pad0, "# ", name);
            write(out, pad0, "#--------------------------------------------------------------------------------------------------------#");
            Splitter splitter = Splitter.onPattern("\r?\n");

            for (String line : splitter.split(comment)) {
                write(out, pad0, "# ", line);
            }

            write(out, pad0, COMMENT_SEPARATOR, NEW_LINE);
        }

        String displayName = name;

        if (!allowedProperties.matchesAllOf(name)) {
            displayName = '"' + name + '"';
        }

        write(out, pad0, displayName, " {");

        Property[] props = getOrderedValues().toArray(new Property[]{});

        for (int x = 0; x < props.length; x++) {
            Property prop = props[x];

            if (prop.getComment() != null && !prop.getComment().isEmpty()) {
                if (x != 0) {
                    out.newLine();
                }

                Splitter splitter = Splitter.onPattern("\r?\n");
                for (String commentLine : splitter.split(prop.getComment())) {
                    write(out, pad1, "# ", commentLine);
                }
            }

            String propName = prop.getName();

            if (!allowedProperties.matchesAllOf(propName)) {
                propName = '"' + propName + '"';
            }

            if (prop.isList()) {
                char type = prop.getType().getID();

                write(out, pad1, String.valueOf(type), ":", propName, " <");

                for (String line : prop.getStringList()) {
                    write(out, pad2, line);
                }

                write(out, pad1, " >");
            } else if (prop.getType() == null) {
                write(out, pad1, propName, "=", prop.getString());
            } else {
                char type = prop.getType().getID();
                write(out, pad1, String.valueOf(type), ":", propName, "=", prop.getString());
            }

            prop.resetChangedState();
        }

        if (children.size() > 0)
            out.newLine();

        for (ConfigCategory child : children) {
            child.write(out, indent + 1);
        }

        write(out, pad0, "}", NEW_LINE);
    }

    private String getIndent(int indent) {
        StringBuilder buf = new StringBuilder();
        for (int x = 0; x < indent; x++) {
            buf.append("    ");
        }
        return buf.toString();
    }

    void resetChangedState() {
        changed = false;
        for (Property prop : properties.values()) {
            prop.resetChangedState();
        }
    }

    private void write(BufferedWriter out, String... data) throws IOException {
        write(out, true, data);
    }

    private void write(BufferedWriter out, boolean new_line, String... data) throws IOException {
        for (int x = 0; x < data.length; x++) {
            out.write(data[x]);
        }
        if (new_line) out.newLine();
    }
}