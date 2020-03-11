package edu.uci.Inf122.TileMatchingMania.GUI.Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Ini {
    private static final String COMMENT_CHARS = ";#";
    private static final String VALUE_SEPARATOR = ",";
    private static final String DEFAULT_SECTION_KEY = transformSectionKey("Logins");
    private static final boolean SECTION_KEY_CASE_SENSITIVE = false;

    private final Map<String, Section> mSections = new HashMap<>();

    public Ini(File file) throws IOException {
        load(file);
    }

    public Ini(String filename) throws IOException {
        load(new File(filename));
    }

    private void load(File file) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String sectionKey = DEFAULT_SECTION_KEY;
        final Section defaultSection = new Section();
        mSections.put(sectionKey, defaultSection);

        String line;
        while ((line = br.readLine()) != null) {
            if (isCommentLine(line)) { continue; }

            if (isSectionLine(line)) {
                sectionKey = getSectionKey(line);

                if (!mSections.containsKey(sectionKey)) {
                    final Section section = new Section();
                    section.setDefaultSection(defaultSection);

                    mSections.put(sectionKey, section);
                }

                continue;
            }

            String[] pair = line.split("=", 2);
            if (pair.length == 2) {
                mSections.get(sectionKey).put(pair[0].trim(), pair[1].trim());
            }
        }
    }

    public Set<String> getSectionKeys() {
        return Collections.unmodifiableSet(mSections.keySet());
    }


    public boolean hasSection(String sectionKey) {
        return mSections.containsKey(transformSectionKey(sectionKey));
    }

    public Section getSection(String sectionKey) {
        sectionKey = transformSectionKey(sectionKey);
        if (!mSections.containsKey(sectionKey)) {
            throw new NoSuchElementException("Section '" + sectionKey + "' not found.");
        }

        return mSections.get(sectionKey);
    }

    static String transformSectionKey(String sectionKey) {
        return SECTION_KEY_CASE_SENSITIVE ? sectionKey : sectionKey.toLowerCase();
    }

    static boolean isCommentLine(String line) {
        if (line == null) { return false; }
        line = line.trim();
        if (line.isEmpty()) { return true; }

        String firstChar = line.substring(0, 1);
        return COMMENT_CHARS.contains(firstChar);
    }

    static boolean isSectionLine(String line) {
        if (line == null) { return false; }
        line = line.trim();
        return line.startsWith("[") && line.endsWith("]");
    }

    static String getSectionKey(String line) {
        if (!isSectionLine(line)) { throw new IllegalArgumentException("Not a section."); }
        line = line.trim();
        String sectionKey = line.substring(1, line.length() - 1).trim();
        if (sectionKey.isEmpty()) { throw new IllegalArgumentException("Section key cannot be empty."); }

        return transformSectionKey(sectionKey);
    }

    /**
     * Represents a *section* of an INI file.
     */
    public static class Section {

        private static final boolean KEY_CASE_SENSITIVE = false;
        private final Map<String, String> mProperties;
        private Section mDefaultSection;

        private Section() {
            mProperties = new HashMap<>();
        }

        /**
         * Sets a fallback section for *this* section. When a key is not
         * found in *this* section, the key is looked up in the default
         * section.
         *
         * @param defaultSection The default section.
         */
        private void setDefaultSection(Section defaultSection) {
            mDefaultSection = defaultSection;
        }

        public Set<String> getKeys(boolean includeDefault) {
            if (!includeDefault || mDefaultSection == null) {
                return Collections.unmodifiableSet( mProperties.keySet() );
            } else {
                Set<String> allKeys = new HashSet<>(mProperties.keySet());
                allKeys.addAll(mDefaultSection.getKeys());

                return allKeys;
            }
        }

        public Set<String> getKeys() {
            return getKeys(false);
        }

        public boolean containsKey(String key, boolean includeDefault) {
            key = transformKey(key);

            if (!includeDefault || mDefaultSection == null) {
                return mProperties.containsKey(key);
            } else {
                return mProperties.containsKey(key) || mDefaultSection.containsKey(key);
            }
        }

        public boolean containsKey(String key) { return containsKey(key, false); }

        private String put(String key, String value) { return mProperties.put(transformKey(key), value); }


        public String getString(String key) {
            key = transformKey(key);

            if (!containsKey(key)) {
                if (mDefaultSection == null || !mDefaultSection.containsKey(key)) {
                    throw new NoSuchElementException("Key '" + key + "' not found.");
                }

                return mDefaultSection.getString(key);
            }

            return mProperties.get(key);
        }

        public String getString(String key, String defaultValue) {
            try {
                return getString(key);
            } catch (NoSuchElementException e) {
                return defaultValue;
            }
        }

        /**
         * Get the property value stored against {@code key} formatted as int.
         * <p>
         * Also see {@link #getString(String)}
         *
         * @throws NoSuchElementException If key is not present.
         * @throws NumberFormatException  If value cannot be converted to a int.
         */
        public int getInt(String key) {
            return Integer.parseInt(getString(key));
        }

        public int getInt(String key, int defaultValue) {
            try {
                return getInt(key);
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        public Integer getInteger(String key, Integer defaultValue) {
            try {
                return Integer.valueOf(getString(key));
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        public float getFloat(String key) {
            return Float.parseFloat(getString(key));
        }

        public float getFloat(String key, float defaultValue) {
            try {
                return getFloat(key);
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        public Float getFloat(String key, Float defaultValue) {
            try {
                return Float.valueOf(getString(key));
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        public double getDouble(String key) {
            return Double.parseDouble(getString(key));
        }

        public double getDouble(String key, double defaultValue) {
            try {
                return getDouble(key);
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        public Double getDouble(String key, Double defaultValue) {
            try {
                return Double.valueOf(getString(key));
            } catch (NoSuchElementException | NumberFormatException e) {
                return defaultValue;
            }
        }

        static String transformKey(String key) {
            //noinspection ConstantConditions
            return KEY_CASE_SENSITIVE ? key : key.toLowerCase();
        }
    }
}