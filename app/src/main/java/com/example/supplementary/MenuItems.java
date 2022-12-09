package com.example.supplementary;

import android.provider.BaseColumns;

public final class MenuItems {
    private MenuItems(){}

    public static class Items implements BaseColumns{
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_ITEM = "Item";
        public static final String COLUMN_NAME_PRICE = "Price";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";

    }


}