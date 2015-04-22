package com.dontmindgames.buddhichal.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dontmindgames.buddhichal.BuddhiChal;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 800);
            return  cfg;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new BuddhiChal();
        }
}