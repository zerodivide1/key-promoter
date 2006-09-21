package org.intellij.contest.keypromoter;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.ui.ColorPanel;
import com.sun.javaws.jnl.JREDesc;

import javax.swing.*;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;
import org.jdom.Element;


/**
 * Date: 07.09.2006
 * Time: 11:19:46
 */
public class KeyPromoterConfiguration implements Configurable, ApplicationComponent, JDOMExternalizable  {

    JPanel myConfigPanel;
    private JSpinner myDisplayTime;
    private JCheckBox myAllButtons;
    private JCheckBox myToolWindowButtons;
    private JCheckBox myToobarButtons;
    private JCheckBox myMenus;
    private JCheckBox myFixedTipPosition;

    private JSpinner myAnimationDelay;
    private ColorPanel myTextColor;
    private ColorPanel myBackgroundColor;
    private ColorPanel myBorderColor;
    private JSpinner myFontSize;

    private KeyPromoterSettings mySettings = new KeyPromoterSettings();
    private JCheckBox checkBox1;

    public String getDisplayName() {
        return "KeyPromoter";
    }

    public Icon getIcon() {
        return null;
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        return myConfigPanel;
    }

    public boolean isModified() {
        if (myMenus.isSelected() != mySettings.isMenusEnabled()) return true;
        if (myToobarButtons.isSelected() != mySettings.isToolbarButtonsEnabled()) return true;
        if (myToolWindowButtons.isSelected() != mySettings.isToolWindowButtonsEnabled()) return true;
        if (myAllButtons.isSelected() != mySettings.isAllButtonsEnabled()) return true;
        if (myTextColor.getSelectedColor() != mySettings.getTextColor()) return true;
        if (myBackgroundColor.getSelectedColor() != mySettings.getBackgroundColor()) return true;
        if (myBorderColor.getSelectedColor() != mySettings.getBorderColor()) return true;
        if (!myDisplayTime.getValue().equals(mySettings.getDisplayTime())) return true;
        if (!myAnimationDelay.getValue().equals(mySettings.getFlashAnimationDelay())) return true;
        if (!myFontSize.getValue().equals(mySettings.getFontSize())) return true;
        if (myFixedTipPosition.isSelected() != mySettings.isFixedTipPosistion()) return true;
        return false;
    }

    public void apply() throws ConfigurationException {
        mySettings.setMenusEnabled(myMenus.isSelected());
        mySettings.setToolbarButtonsEnabled(myToobarButtons.isSelected());
        mySettings.setToolWindowButtonsEnabled(myToolWindowButtons.isSelected());
        mySettings.setAllButtonsEnabled(myAllButtons.isSelected());
        mySettings.setTextColor(myTextColor.getSelectedColor());
        mySettings.setBackgroundColor(myBackgroundColor.getSelectedColor());
        mySettings.setBorderColor(myBorderColor.getSelectedColor());
        mySettings.setDisplayTime(new Integer(myDisplayTime.getValue().toString()));
        mySettings.setFlashAnimationDelay(new Integer(myAnimationDelay.getValue().toString()));
        mySettings.setFontSize(new Integer(myFontSize.getValue().toString()));
        mySettings.setFixedTipPosistion(myFixedTipPosition.isSelected());
    }

    public void reset() {
        myMenus.setSelected(mySettings.isMenusEnabled());
        myToobarButtons.setSelected(mySettings.isToolbarButtonsEnabled());
        myToolWindowButtons.setSelected(mySettings.isToolWindowButtonsEnabled());
        myAllButtons.setSelected(mySettings.isAllButtonsEnabled());
        myTextColor.setSelectedColor(mySettings.getTextColor());
        myBackgroundColor.setSelectedColor(mySettings.getBackgroundColor());
        myBorderColor.setSelectedColor(mySettings.getBorderColor());
        myDisplayTime.setValue(mySettings.getDisplayTime());
        myAnimationDelay.setValue(mySettings.getFlashAnimationDelay());
        myFontSize.setValue(mySettings.getFontSize());
        myFixedTipPosition.setSelected(mySettings.isFixedTipPosistion());
    }

    public void disposeUIResources() {
    }

    @NonNls
    public String getComponentName() {
        return "KeyPromoter.Config";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    public KeyPromoterSettings getSettings() {
        return mySettings;
    }

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(mySettings, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(mySettings, element);
    }

}
