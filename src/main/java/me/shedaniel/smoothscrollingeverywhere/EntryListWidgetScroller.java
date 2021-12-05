package me.shedaniel.smoothscrollingeverywhere;

import me.shedaniel.clothconfig2.api.ScrollingContainer;
import me.shedaniel.math.Rectangle;
import me.shedaniel.smoothscrollingeverywhere.mixin.AbstractSelectionListAccessor;
import net.minecraft.client.gui.components.AbstractSelectionList;

public class EntryListWidgetScroller extends ScrollingContainer {

    private final AbstractSelectionList<?> widget;
    private final AbstractSelectionListAccessor accessor;

    public EntryListWidgetScroller(AbstractSelectionList<?> widget) {
        this.widget = widget;
        this.accessor = (AbstractSelectionListAccessor) widget;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(accessor.getLeft(), accessor.getTop(), accessor.getRight() - accessor.getLeft(), accessor.getBottom() - accessor.getTop());
    }

    @Override
    public int getMaxScrollHeight() {
        return widget.getMaxPosition();
    }

    @Override
    public int getScrollBarX() {
        return widget.getScrollbarPosition();
    }
}
